package com.yugorsk.school6.repository;

import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yugorsk.school6.FirebaseQueryLiveData;
import com.yugorsk.school6.MainActivity;
import com.yugorsk.school6.data.Date;

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
