package com.yugorsk.school6.view.fragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.yugorsk.school6.CalendarDate;
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        model = ViewModelProviders.of(this).get(MainViewModel.class);

        loadAnimation();

        NetworkState networkState = new NetworkState(getActivity());
        if(networkState.isOnline()) {
            getDateFromServer();
        }
        showDate();
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
        model.getDateFromServer().observe(this, dates -> {

            CalendarDate calendarDate = new CalendarDate(dates);
            model.insertDate(calendarDate.toDate());
        });
    }


    private void showDate() {
        LiveData<Date> date = model.getDate();
        date.observe(this, dates -> {
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
}
