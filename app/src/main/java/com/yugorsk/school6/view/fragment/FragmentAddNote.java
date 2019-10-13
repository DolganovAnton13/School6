package com.yugorsk.school6.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.yugorsk.school6.R;
import com.yugorsk.school6.callback.SnackbarCallback;
import com.yugorsk.school6.databinding.FragmentAddNoteBinding;
import com.yugorsk.school6.network.NetworkState;
import com.yugorsk.school6.view.MainActivity;
import com.yugorsk.school6.viewmodel.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAddNote extends Fragment implements SnackbarCallback {

    private FragmentAddNoteBinding binding;
    private MainViewModel model;
    private Bundle arguments;
    private String date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false);
        binding.setEvent(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        setArguments();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).setToolbarWithButtonHome(binding.toolbarAddNote, "Добавить новость");
    }

    @Override
    public void onDestroyView() {
        ((MainActivity) getActivity()).setToolbarWithDrawerLayout(null, "");
        super.onDestroyView();
    }

    private void setArguments() {
        arguments = getArguments();
        if (arguments != null) {
            String description = arguments.getString("description");
            date = arguments.getString("date");
            binding.textDescription.setText(description);
        }
    }

    public void onFABClick(View view) {

        String textDescription = binding.textDescription.getText().toString();

        if (textDescription.isEmpty()) {
            Snackbar.make(binding.textDescription, getResources().getString(R.string.emptyField), Snackbar.LENGTH_LONG).show();
        } else {
            NetworkState networkState = new NetworkState(getActivity());
            if (networkState.isOnline()) {
                if (arguments == null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm d MMMM yyyy");
                    model.LoadNews(sdf.format(new Date()), textDescription, this);
                }
                else
                {
                    model.LoadNews(date, textDescription, this);
                }
            } else {
                Snackbar.make(binding.toolbarAddNote, getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void SnackbarShow(String text) {
        Snackbar.make(binding.toolbarAddNote, text, Snackbar.LENGTH_LONG).show();
        ((MainActivity) getActivity()).onBackPressed();
    }
}
