package com.yugorsk.school6.view.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yugorsk.school6.R;
import com.yugorsk.school6.databinding.FragmentContacts2Binding;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentContacts2 extends Fragment {

    private FragmentContacts2Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contacts2, container, false);
        binding.setEvent(this);
        return binding.getRoot();
    }

    public void onPhoneNumberClick(View view) {

        if( view instanceof TextView) {
            TextView textView = (TextView) view;
            String clearPhone = textView.getText().toString().replaceAll("-", "").replace("(приёмная)", "");
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + clearPhone));
            startActivity(intent);
        }
    }
}
