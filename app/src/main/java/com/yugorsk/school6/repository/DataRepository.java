package com.yugorsk.school6.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.yugorsk.school6.network.FirebaseQueryLiveData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private static final DatabaseReference Date_REF = FirebaseDatabase.getInstance().getReference().child("Даты");
    List<String> listDate;

    MutableLiveData<List<StorageReference>> storageLiveData;

    public DataRepository() {
        listDate = new ArrayList<>();
        storageLiveData = new MutableLiveData<>();
    }

    @NonNull
    public LiveData<List<String>> getDateLiveData(){
        FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(Date_REF);
        LiveData<List<String>> venuesLiveData = Transformations.map(liveData, new Deserialiser());
        return venuesLiveData;
    }

    public LiveData<List<StorageReference>> getScheduleLiveData()
    {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        List<StorageReference> listStorage = new ArrayList<>();
        listStorage.add(storage.getReferenceFromUrl("gs://newschool6-dd806.appspot.com").child("расписание уроков/1.jpg"));
        listStorage.add(storage.getReferenceFromUrl("gs://newschool6-dd806.appspot.com").child("расписание уроков/2.jpg"));
        listStorage.add(storage.getReferenceFromUrl("gs://newschool6-dd806.appspot.com").child("расписание уроков/3.jpg"));
        listStorage.add(storage.getReferenceFromUrl("gs://newschool6-dd806.appspot.com").child("расписание уроков/4.jpg"));
        listStorage.add(storage.getReferenceFromUrl("gs://newschool6-dd806.appspot.com").child("расписание уроков/11.jpg"));

        storageLiveData.setValue(listStorage);
        return storageLiveData;
    }

    private class Deserialiser implements Function<DataSnapshot, List<String>> {
        @Override
        public List<String> apply(DataSnapshot dataSnapshot) {
            listDate.clear();
            for(DataSnapshot dsp: dataSnapshot.getChildren()){
                listDate.add(dsp.getValue().toString());
            }
            return listDate;
        }
    }


}
