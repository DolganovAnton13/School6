package com.yugorsk.school6.view.fragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.yugorsk.school6.CalendarDate;
import com.yugorsk.school6.data.Login;
import com.yugorsk.school6.network.NetworkState;
import com.yugorsk.school6.viewmodel.MainViewModel;
import com.yugorsk.school6.R;
import com.yugorsk.school6.data.Date;
import com.yugorsk.school6.databinding.FragmentMainBinding;
import com.yugorsk.school6.view.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMain extends Fragment {

    private MainViewModel model;
    private FragmentMainBinding binding;
    private Animation uptodown, downtoup, lefttoright, righttoleft;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.setEvent(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        loadAnimation();

        NetworkState networkState = new NetworkState(getActivity());
        if(networkState.isOnline()) {
            getDateFromServer();
        }
        showDate();
        getLoginForCheck();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity)getActivity()).setToolbar(binding.toolbarMain,"Главная");
        ((MainActivity)getActivity()).navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onDestroyView() {
        ((MainActivity)getActivity()).setToolbar(null,"");
        super.onDestroyView();
    }

    private void getDateFromServer() {
        model.getDateFromServer().observe(getViewLifecycleOwner(), dates -> {
            model.insertDate(dates);
        });
    }


    private void showDate() {
        LiveData<Date> date = model.getDate();
        date.observe(getViewLifecycleOwner(), dates -> {
            if (dates != null) {

                CalendarDate calendarDate = new CalendarDate(dates);
                binding.newTextForDate.setText(calendarDate.CurrentDate());
            }
            else
            {
                binding.newTextForDate.setText(R.string.app_name);
            }
        });
    }

    private void getLoginForCheck()
    {
        model.getLogin().observe(getViewLifecycleOwner(),login ->{
            if(login!=null) {
                CheckLogin(login.getLogin(), login.getPassword());
            }
        });
    }

    private void CheckLogin(String login, String password) {
        NetworkState networkState = new NetworkState(getActivity());
        if (networkState.isOnline()) {
            model.getLoginFromServer().observe(getViewLifecycleOwner(),listLogin -> {
                for (Login buf: listLogin) {
                    if (buf.getLogin().equals(login) && buf.getPassword().equals(password)){

                        FragmentLogin.admin = true;
                        ((MainActivity)getActivity()).navigationView.getMenu().getItem(6).setTitle("Администрирование - выйти");
                    }
                }
            });
        }
    }

    private void loadAnimation() {
        uptodown = AnimationUtils.loadAnimation(getContext(), R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(getContext(), R.anim.downtoup);
        lefttoright = AnimationUtils.loadAnimation(getContext(), R.anim.lefttoright);
        righttoleft = AnimationUtils.loadAnimation(getContext(), R.anim.righttoleft);

        binding.cardTop.setAnimation(uptodown);
        binding.cardLeft1.setAnimation(righttoleft);
        binding.cardLeft2.setAnimation(lefttoright);
        binding.cardRight.setAnimation(downtoup);
    }

    public void onCardViewClick(View view) {
        switch (view.getId())
        {
            case R.id.cardLeft1:
                ((MainActivity)getActivity()).replaceFragment(new FragmentCallScheduleMain());
                break;
            case R.id.cardLeft2:
                ((MainActivity)getActivity()).replaceFragment(new FragmentNews());
                break;
            case R.id.cardRight:
                ((MainActivity)getActivity()).replaceFragment(new FragmentSchedule());
                break;
        }
    }
}
