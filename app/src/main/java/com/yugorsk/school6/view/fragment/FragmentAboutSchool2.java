package com.yugorsk.school6.view.fragment;


import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yugorsk.school6.R;
import com.yugorsk.school6.adapter.ViewPagerAdapterAboutSchool;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAboutSchool2 extends Fragment {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    public FragmentAboutSchool2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about_school2, container, false);
        viewPager = view.findViewById(R.id.viewPagerAboutSchool2);

        sliderDotspanel = view.findViewById(R.id.SliderDots);
        ViewPagerAdapterAboutSchool viewPagerAdapterAboutSchool = new ViewPagerAdapterAboutSchool(getContext(), 1);
        viewPager.setAdapter(viewPagerAdapterAboutSchool);

        dotscount = viewPagerAdapterAboutSchool.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);
            dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
            sliderDotspanel.addView(dots[i], params);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.nonactive_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return view;
    }
}