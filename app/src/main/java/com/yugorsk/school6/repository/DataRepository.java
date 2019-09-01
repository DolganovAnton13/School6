package com.yugorsk.school6.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yugorsk.school6.data.Date;

import java.util.List;

public class DataRepository {

    DatabaseReference myRef;
    MutableLiveData<List<Date>> listDate;
    MutableLiveData<List<Integer>> listDays;

    private boolean isUpdateInProgress;

    public DataRepository()
    {
        listDate = new MutableLiveData<>();
        listDays = new MutableLiveData<>();
    }

    public LiveData<List<Date>> getDate(){

        myRef = FirebaseDatabase.getInstance().getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return null;
    }
}
