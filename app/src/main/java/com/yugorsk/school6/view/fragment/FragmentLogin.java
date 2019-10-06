package com.yugorsk.school6.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.yugorsk.school6.R;
import com.yugorsk.school6.data.Login;
import com.yugorsk.school6.databinding.FragmentLoginBinding;
import com.yugorsk.school6.network.NetworkState;
import com.yugorsk.school6.view.MainActivity;
import com.yugorsk.school6.viewmodel.MainViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment {

    private MainViewModel model;
    private FragmentLoginBinding binding;
    public static boolean admin = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setEvent(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).setToolbar(binding.toolbarLogin, "Администрирование");
        ((MainActivity) getActivity()).navigationView.getMenu().getItem(6).setChecked(true);
        if(admin) ((MainActivity)getActivity()).navigationView.getMenu().getItem(6).setTitle("Администрирование");
    }

    @Override
    public void onDestroyView() {
        ((MainActivity) getActivity()).setToolbar(null, "");
        super.onDestroyView();
    }

    public void onButtonLoginClick(View view) {
        String textLogin = binding.textLogin.getText().toString();
        String textPassword = binding.textPassword.getText().toString();

        if (textLogin.isEmpty() || textPassword.isEmpty()) {
            Snackbar.make(binding.buttonLogin, getResources().getString(R.string.emptyField), Snackbar.LENGTH_LONG).show();
        } else {
            CheckData(textLogin,textPassword);
        }
    }

    private void CheckData(String login, String password) {
        NetworkState networkState = new NetworkState(getActivity());
        if (networkState.isOnline()) {
            model.getLoginFromServer().observe(getViewLifecycleOwner(),listLogin -> {
                for (Login buf: listLogin) {
                    if (buf.getLogin().equals(login) && buf.getPassword().equals(password)){
                        binding.progressBarLogin.setVisibility(View.VISIBLE);
                        binding.buttonLogin.setVisibility(View.INVISIBLE);
                        admin = true;
                        ((MainActivity)getActivity()).navigationView.getMenu().getItem(6).setTitle("Администрирование - выйти");
                        //insertbd
                        ((MainActivity)getActivity()).replaceFragment(new FragmentMain());
                    }
                }
                if (!admin)
                    Snackbar.make(binding.buttonLogin, "Введен неверный логин или пароль", Snackbar.LENGTH_LONG).show();
            });
        }
        else {
            Snackbar.make(binding.buttonLogin, getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG).show();
        }
    }

}
