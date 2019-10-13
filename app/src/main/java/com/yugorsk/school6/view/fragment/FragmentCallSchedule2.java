package com.yugorsk.school6.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.yugorsk.school6.R;
import com.yugorsk.school6.databinding.FragmentCallSchedule2Binding;
import com.yugorsk.school6.network.NetworkState;
import com.yugorsk.school6.viewmodel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCallSchedule2 extends Fragment {

    private MainViewModel model;
    private FragmentCallSchedule2Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_call_schedule2, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        showCall();
    }

    private void showCall() {
        NetworkState networkState = new NetworkState(getActivity());
        if (networkState.isOnline()) {
            model.getCallFromServer().observe(getViewLifecycleOwner(), call -> {
                binding.dateText.setText(call.getDateText());
                binding.lesson11.setText(call.getLesson1_1());
                binding.lesson12.setText(call.getLesson1_2());
                binding.lesson13.setText(call.getLesson1_3());
                binding.lesson14.setText(call.getLesson1_4());
                binding.lesson15.setText(call.getLesson1_5());
                binding.lesson16.setText(call.getLesson1_6());
                binding.lesson21.setText(call.getLesson2_1());
                binding.lesson22.setText(call.getLesson2_2());
                binding.lesson23.setText(call.getLesson2_3());
                binding.lesson24.setText(call.getLesson2_4());
                binding.lesson25.setText(call.getLesson2_5());
            });
        } else {
            Snackbar.make(binding.activityCallSchedule2, getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG).show();
        }
    }

}
