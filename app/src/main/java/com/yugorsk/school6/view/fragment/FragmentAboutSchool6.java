package com.yugorsk.school6.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yugorsk.school6.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAboutSchool6 extends Fragment {


    public FragmentAboutSchool6() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_school6, container, false);
    }

}
