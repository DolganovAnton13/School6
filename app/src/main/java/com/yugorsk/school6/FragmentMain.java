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
    //private FragmentMainBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity = (MainActivity) context;
    }

    public FragmentMain() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       // binding = DataBindingUtil.setContentView(activity, R.layout.fragment_main);
        model = ViewModelProviders.of(activity).get(MainViewModel.class);
       // updateDateList();
        //loadDateList();
    }

    /*private void updateDateList()
    {
        List<String> listDate = model.getDateFromServer();
       // LiveData<List<Date>> dateFromServer = model.getDateFromServer();

        dateFromServer.observe(this,dates -> {
            model.updateDate(dates);
            Log.i("eeeeeeeee", "update");
        });
        List<Date> newDate = new ArrayList<>();
       // newDate.add(new Date(listDate.get(0),listDate.get(1),"","","","","",""));
        //model.updateDate(newDate);
        Log.d("eeeeeeeee", "hello");
        //binding.newTextForDate.setText(listDate.get(0).toString());
    }

    private void loadDateList()
    {
        LiveData<List<Date>> date = model.getDate();
        date.observe(this,dates -> {
           // binding.newTextForDate.setText(dates.get(0).toString());
        });
    }*/
}
