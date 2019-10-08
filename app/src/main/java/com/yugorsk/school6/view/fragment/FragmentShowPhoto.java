package com.yugorsk.school6.view.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yugorsk.school6.R;
import com.yugorsk.school6.adapter.ViewPagerAdapterShowPhoto;
import com.yugorsk.school6.databinding.FragmentShowPhotoBinding;
import com.yugorsk.school6.view.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentShowPhoto extends Fragment {

    private FragmentShowPhotoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_photo, container, false);
        setViewPager();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).setToolbarWithButtonHome(binding.toolbarShowPhoto, "Просмотр фотографий");
    }

    @Override
    public void onDestroyView() {
        ((MainActivity) getActivity()).setToolbarWithDrawerLayout(null, "");
        super.onDestroyView();
    }

    private void setViewPager()
    {
        Bundle arguments = getArguments();
        int numberFragment=arguments.getInt("fragment");
        int position=arguments.getInt("position");
        ViewPagerAdapterShowPhoto viewPagerAdapter = new ViewPagerAdapterShowPhoto(getContext(),numberFragment);
        binding.viewPagerShowPhoto.setAdapter(viewPagerAdapter);
        binding.viewPagerShowPhoto.setCurrentItem(position);
    }
}
