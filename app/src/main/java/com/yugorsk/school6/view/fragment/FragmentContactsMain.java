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
import com.yugorsk.school6.adapter.ViewPagerAdapterContacts;
import com.yugorsk.school6.databinding.FragmentContactsBinding;
import com.yugorsk.school6.view.MainActivity;
import com.yugorsk.school6.viewmodel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentContactsMain extends Fragment {

    private MainViewModel model;
    private FragmentContactsBinding binding;
    private PagerAdapter pagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        setPagerAdapter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).setToolbarWithDrawerLayout(binding.toolbarContacts, "Контакты");
        ((MainActivity) getActivity()).navigationView.getMenu().getItem(5).setChecked(true);
    }

    @Override
    public void onDestroyView() {
        ((MainActivity) getActivity()).setToolbarWithDrawerLayout(null, "");
        super.onDestroyView();
    }

    private void setPagerAdapter()
    {
        pagerAdapter = new ViewPagerAdapterContacts(getChildFragmentManager());
        binding.pagerContacts.setAdapter(pagerAdapter);
        binding.pagerContacts.setCurrentItem(0);
    }
}
