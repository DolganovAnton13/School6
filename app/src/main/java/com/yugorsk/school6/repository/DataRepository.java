package com.yugorsk.school6.repository;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yugorsk.school6.network.FirebaseQueryLiveData;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private static final DatabaseReference Date_REF = FirebaseDatabase.getInstance().getReference().child("Даты");
    List<String> listDate;

    public DataRepository() {
        listDate = new ArrayList<>();
    }

    @NonNull
    public LiveData<List<String>> getDateLiveData(){
        FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(Date_REF);
        LiveData<List<String>> venuesLiveData = Transformations.map(liveData, new Deserialiser());
        return venuesLiveData;
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
