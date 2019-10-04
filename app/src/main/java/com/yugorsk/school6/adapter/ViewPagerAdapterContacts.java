package com.yugorsk.school6.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yugorsk.school6.R;
import com.yugorsk.school6.view.fragment.FragmentCallSchedule1;
import com.yugorsk.school6.view.fragment.FragmentCallSchedule2;
import com.yugorsk.school6.view.fragment.FragmentContacts1;
import com.yugorsk.school6.view.fragment.FragmentContacts2;

public class ViewPagerAdapterContacts extends FragmentPagerAdapter {

    private final String titles[] = new String[]{"Школа №6", "Дошкольные группы"};
    private final Fragment frags[] = new Fragment[titles.length];

    public ViewPagerAdapterContacts(FragmentManager mgr) {
        super(mgr);
        frags[0] = new FragmentContacts1();
        frags[1] = new FragmentContacts2();
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