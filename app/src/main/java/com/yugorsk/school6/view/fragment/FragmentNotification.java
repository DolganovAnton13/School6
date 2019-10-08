package com.yugorsk.school6.view.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yugorsk.school6.R;
import com.yugorsk.school6.databinding.FragmentNotificationBinding;
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
        setWebView();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).setToolbarWithButtonHome(binding.toolbarNotification, "Отправить уведомление");
    }

    @Override
    public void onDestroyView() {
        ((MainActivity) getActivity()).setToolbarWithDrawerLayout(null, "");
        super.onDestroyView();
    }

    private void setWebView()
    {
        binding.webViewNotification.getSettings().setJavaScriptEnabled(true);
        binding.webViewNotification.setWebViewClient(new WebViewClient());
        binding.webViewNotification.loadUrl("https://console.firebase.google.com/project/newschool6-dd806/notification");
    }
}
