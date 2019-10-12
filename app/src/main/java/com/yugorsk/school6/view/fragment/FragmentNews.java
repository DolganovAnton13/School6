package com.yugorsk.school6.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.yugorsk.school6.R;
import com.yugorsk.school6.adapter.NewsAdapter;
import com.yugorsk.school6.data.News;
import com.yugorsk.school6.databinding.FragmentNewsBinding;
import com.yugorsk.school6.network.NetworkState;
import com.yugorsk.school6.view.MainActivity;
import com.yugorsk.school6.viewmodel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNews extends Fragment implements NewsAdapter.NewsClickListener {

    private MainViewModel model;
    private FragmentNewsBinding binding;
    private NewsAdapter newsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        setRecyclerView();
        NetworkState networkState = new NetworkState(getActivity());
        if (networkState.isOnline()) {
            getNewsFromServer();
        }
        else {
            Snackbar.make(binding.toolbarNews, getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG).show();
        }
        showNews();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).setToolbarWithDrawerLayout(binding.toolbarNews, "Новости");
        ((MainActivity) getActivity()).navigationView.getMenu().getItem(4).setChecked(true);
        setHasOptionsMenu(true);
    }

    @Override
    public void onDestroyView() {
        ((MainActivity) getActivity()).setToolbarWithDrawerLayout(null, "");
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        if (FragmentLogin.admin)
            inflater.inflate(R.menu.add_notes, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addNotes:
                ((MainActivity) getActivity()).replaceFragment(new FragmentAddNote());
                return true;
            case R.id.addNotificationNotes:
                ((MainActivity) getActivity()).replaceFragment(new FragmentNotification());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setRecyclerView() {
        newsAdapter = new NewsAdapter();
        newsAdapter.setNewsClickListener(this);

        RecyclerView contactList = binding.listNews;
        contactList.setLayoutManager(new LinearLayoutManager(getActivity()));
        contactList.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        contactList.setAdapter(newsAdapter);
        contactList.setHasFixedSize(true);
        contactList.setItemAnimator(new DefaultItemAnimator());

    }

    private void getNewsFromServer() {

        model.getNewsFromServer().observe(getViewLifecycleOwner(), news -> {
            model.insertNews(news);
        });

    }

    private void showNews() {

        model.getNews().observe(getViewLifecycleOwner(), news -> {
            newsAdapter.setNews(news);
        });
    }

    @Override
    public void onNewsClick(News news) {

    }
}
