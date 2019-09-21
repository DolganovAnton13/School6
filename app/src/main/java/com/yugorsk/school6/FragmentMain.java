package com.yugorsk.school6;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.yugorsk.school6.data.Date;
import com.yugorsk.school6.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMain extends Fragment {

    private MainActivity activity;
    private MainViewModel model;
    private FragmentMainBinding binding;
    Animation uptodown, downtoup, lefttoright, righttoleft;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater ,R.layout.fragment_main,container , false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        model = ViewModelProviders.of(this).get(MainViewModel.class);

        loadAnimation();
        //если есть интернет, то
        //updateDateList();
        //иначе
        loadDateList();
    }

    private void updateDateList()
    {
        model.getDateFromServer().observe(this,dates ->{

            CalendarDate calendarDate = new CalendarDate(dates);
            model.updateDate(calendarDate.toDate());
        });
    }


    private void loadDateList()
    {
        LiveData<List<Date>> date = model.getDate();
        date.observe(this,dates -> {

            CalendarDate calendarDate = new CalendarDate(dates.get(0));
            binding.newTextForDate.setText(calendarDate.CurrentDate());

        });
    }

    private void loadAnimation()
    {
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
