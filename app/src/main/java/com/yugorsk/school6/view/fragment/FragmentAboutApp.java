package com.yugorsk.school6.view.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yugorsk.school6.R;
import com.yugorsk.school6.databinding.FragmentAboutAppBinding;
import com.yugorsk.school6.view.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAboutApp extends Fragment {

    private FragmentAboutAppBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_app, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).setToolbar(binding.toolbarAboutApp, "О приложении");
        ((MainActivity) getActivity()).navigationView.getMenu().getItem(7).setChecked(true);
    }

    @Override
    public void onDestroyView() {
        ((MainActivity) getActivity()).setToolbar(null, "");
        super.onDestroyView();
    }
}
