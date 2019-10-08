package com.yugorsk.school6.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yugorsk.school6.R;
import com.yugorsk.school6.adapter.ViewPagerAdapterCallSchedule;
import com.yugorsk.school6.databinding.FragmentCallScheduleBinding;
import com.yugorsk.school6.view.MainActivity;
import com.yugorsk.school6.viewmodel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCallScheduleMain extends Fragment {

    private MainViewModel model;
    private FragmentCallScheduleBinding binding;
    private PagerAdapter pagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_call_schedule, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        setPagerAdapter();
    }

    private void setPagerAdapter()
    {
        pagerAdapter = new ViewPagerAdapterCallSchedule(getChildFragmentManager());
        binding.pagerCallSchedule.setAdapter(pagerAdapter);
        binding.pagerCallSchedule.setCurrentItem(0);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).setToolbarWithDrawerLayout(binding.toolbarCallSchedule, "Расписание звонков");
        ((MainActivity) getActivity()).navigationView.getMenu().getItem(3).setChecked(true);
    }

    @Override
    public void onDestroyView() {
        ((MainActivity) getActivity()).setToolbarWithDrawerLayout(null, "");
        super.onDestroyView();
    }
}
