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
}
