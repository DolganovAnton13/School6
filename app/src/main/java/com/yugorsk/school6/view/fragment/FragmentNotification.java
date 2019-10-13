package com.yugorsk.school6.view.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.yugorsk.school6.R;
import com.yugorsk.school6.databinding.FragmentNotificationBinding;
import com.yugorsk.school6.network.NetworkState;
import com.yugorsk.school6.view.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNotification extends Fragment {

    private FragmentNotificationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).setToolbarWithButtonHome(binding.toolbarNotification, "Отправить уведомление");
        NetworkState networkState = new NetworkState(getActivity());
        if (networkState.isOnline()) {
            setWebView();
        } else {
            Snackbar.make(binding.toolbarNotification, getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroyView() {
        ((MainActivity) getActivity()).setToolbarWithDrawerLayout(null, "");
        super.onDestroyView();
    }

    private void setWebView() {
        binding.webViewNotification.getSettings().setJavaScriptEnabled(true);
        binding.webViewNotification.setWebViewClient(new WebViewClient());
        binding.webViewNotification.loadUrl("https://console.firebase.google.com/project/newschool6-dd806/notification");
    }
}
