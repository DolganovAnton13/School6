package com.yugorsk.school6.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yugorsk.school6.view.fragment.FragmentCallSchedule1;
import com.yugorsk.school6.view.fragment.FragmentCallSchedule2;

public class ViewPagerAdapterCallSchedule extends FragmentPagerAdapter {

    private final String titles[] = new String[]{"Основное расписание", "Сокращенное расписание"};
    private final Fragment frags[] = new Fragment[titles.length];

    public ViewPagerAdapterCallSchedule(FragmentManager mgr) {
        super(mgr);
        frags[0] = new FragmentCallSchedule1();
        frags[1] = new FragmentCallSchedule2();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return frags[position];
    }

    @Override
    public int getCount() {
        return frags.length;
    }

}