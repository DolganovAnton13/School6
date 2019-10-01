package com.yugorsk.school6.view.fragment;


import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FileDownloadTask;
import com.squareup.picasso.Picasso;
import com.yugorsk.school6.R;
import com.yugorsk.school6.data.Schedule;
import com.yugorsk.school6.databinding.FragmentScheduleBinding;
import com.yugorsk.school6.network.NetworkState;
import com.yugorsk.school6.view.MainActivity;
import com.yugorsk.school6.viewmodel.MainViewModel;

import java.io.File;
import java.io.IOException;

import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSchedule extends Fragment {

    private MainViewModel model;
    private FragmentScheduleBinding binding;
    String[] numberClass = {"1 класс", "2 класс", "3 класс", "4 класс", "5-11 класс"};
    private final int totalProgressTime = 5;

    public FragmentSchedule() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        model = ViewModelProviders.of(this).get(MainViewModel.class);

        setSpinner();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MainActivity) getActivity()).setToolbar(binding.toolbarSchedule, "Расписание уроков");
        ((MainActivity) getActivity()).navigationView.getMenu().getItem(2).setChecked(true);
    }

    @Override
    public void onDestroyView() {
        ((MainActivity) getActivity()).setToolbar(null, "");
        super.onDestroyView();
    }

   @Override
    public void onPause() {
        model.insertIndexSchedule(new Schedule((int)binding.spinnerSchedule.getSelectedItemId()));
        super.onPause();
    }

    private void showShedule() {

        model.getIndexSchedule().observe(this, schedule -> {
            if (schedule != null)
                binding.spinnerSchedule.setSelection(schedule.getIndexPicture());
            else
                binding.spinnerSchedule.setSelection(0);
        });
    }

    private void setSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, numberClass);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerSchedule.setAdapter(adapter);

        showShedule();
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                NetworkState networkState = new NetworkState(getActivity());
                if (networkState.isOnline()) {
                    getSchedule((int)id);
                } else {
                    Snackbar.make(binding.spinnerSchedule, getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        binding.spinnerSchedule.setOnItemSelectedListener(itemSelectedListener);

        PhotoViewAttacher photoView = new PhotoViewAttacher(binding.imageshedule);
        photoView.update();
    }

    private void getSchedule(int id) {
        model.getScheduleFromServer().observe(this, storageReferences -> {
            try {
                final File localFile = File.createTempFile("images", "jpg");
                storageReferences.get(id).getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                startProgress();
                                Picasso.with(getContext()).load(Uri.fromFile(localFile)).into(binding.imageshedule);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        binding.imageshedule.setImageResource(R.drawable.noschedule);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void startProgress() {

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Загрузка расписания");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
        progressDialog.setMax(totalProgressTime);
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int counter = 0;
                while (counter < totalProgressTime) {
                    try {
                        Thread.sleep(300);
                        counter++;
                        progressDialog.setProgress(counter);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                progressDialog.dismiss();
            }
        }).start();
    }
}
