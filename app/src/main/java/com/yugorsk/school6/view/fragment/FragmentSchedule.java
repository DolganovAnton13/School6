package com.yugorsk.school6.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yugorsk.school6.R;
import com.yugorsk.school6.databinding.FragmentScheduleBinding;
import com.yugorsk.school6.view.MainActivity;
import com.yugorsk.school6.viewmodel.MainViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSchedule extends Fragment {

    private MainViewModel model;
    private FragmentScheduleBinding binding;

    public FragmentSchedule() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).setToolbar(binding.toolbarSchedule, "Расписание уроков");
        ((MainActivity) getActivity()).navigationView.getMenu().getItem(2).setChecked(true);
    }

    @Override
    public void onDestroyView() {
        ((MainActivity) getActivity()).setToolbar(null, "");
        super.onDestroyView();
    }
}
