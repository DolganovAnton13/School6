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
import com.yugorsk.school6.data.Call;
import com.yugorsk.school6.data.Date;
import com.yugorsk.school6.network.FirebaseQueryLiveData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private static final DatabaseReference Date_REF = FirebaseDatabase.getInstance().getReference().child("Даты");
    private static final DatabaseReference Call_REF = FirebaseDatabase.getInstance().getReference().child("Звонки");

    MutableLiveData<StorageReference> storageLiveData;

    public DataRepository() {
        storageLiveData = new MutableLiveData<>();
    }

    @NonNull
    public LiveData<Date> getDateLiveData() {
        FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(Date_REF);
        LiveData<Date> dateLiveData = Transformations.map(liveData, new DataSnapshotDate());
        return dateLiveData;
    }

    @NonNull
    public LiveData<Call> getCallLiveData() {
        FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(Call_REF);
        LiveData<Call> callLiveData = Transformations.map(liveData, new DataSnapshotCall());
        return callLiveData;
    }

    public LiveData<StorageReference> getScheduleLiveData(int id) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = null;
        switch (id) {
            case 0:
                storageRef = storage.getReferenceFromUrl("gs://newschool6-dd806.appspot.com").child("расписание уроков/1.jpg");
                break;
            case 1:
                storageRef = storage.getReferenceFromUrl("gs://newschool6-dd806.appspot.com").child("расписание уроков/2.jpg");
                break;
            case 2:
                storageRef = storage.getReferenceFromUrl("gs://newschool6-dd806.appspot.com").child("расписание уроков/3.jpg");
                break;
            case 3:
                storageRef = storage.getReferenceFromUrl("gs://newschool6-dd806.appspot.com").child("расписание уроков/4.jpg");
                break;
            case 4:
                storageRef = storage.getReferenceFromUrl("gs://newschool6-dd806.appspot.com").child("расписание уроков/11.jpg");
                break;
        }

        storageLiveData.setValue(storageRef);
        return storageLiveData;
    }

    private class DataSnapshotDate implements Function<DataSnapshot, Date> {
        @Override
        public Date apply(DataSnapshot dataSnapshot) {

            Date date = new Date();
            for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                switch (dsp.getKey()) {
                    case "1-ая четверть до":
                        date.setQuarterOneTo(dsp.getValue().toString());
                        break;
                    case "1-ая четверть с":
                        date.setQuarterOneFrom(dsp.getValue().toString());
                        break;
                    case "2-ая четверть до":
                        date.setQuarterTwoTo(dsp.getValue().toString());
                        break;
                    case "2-ая четверть с":
                        date.setQuarterTwoFrom(dsp.getValue().toString());
                        break;
                    case "3-я четверть до":
                        date.setQuarterThreeTo(dsp.getValue().toString());
                        break;
                    case "3-я четверть с":
                        date.setQuarterThreeFrom(dsp.getValue().toString());
                        break;
                    case "4-ая четверть до":
                        date.setQuarterFourTo(dsp.getValue().toString());
                        break;
                    case "4-ая четверть с":
                        date.setQuarterFourFrom(dsp.getValue().toString());
                        break;
                }
            }
            return date;
        }
    }

    private class DataSnapshotCall implements Function<DataSnapshot, Call> {
        @Override
        public Call apply(DataSnapshot dataSnapshot) {
            Call call = new Call();

            for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                switch (dsp.getKey().toString()) {
                    case "1_1":
                        call.setLesson1_1(dsp.getValue().toString());
                        break;
                    case "1_2":
                        call.setLesson1_2(dsp.getValue().toString());
                        break;
                    case "1_3":
                        call.setLesson1_3(dsp.getValue().toString());
                        break;
                    case "1_4":
                        call.setLesson1_4(dsp.getValue().toString());
                        break;
                    case "1_5":
                        call.setLesson1_5(dsp.getValue().toString());
                        break;
                    case "1_6":
                        call.setLesson1_6(dsp.getValue().toString());
                        break;
                    case "2_1":
                        call.setLesson2_1(dsp.getValue().toString());
                        break;
                    case "2_2":
                        call.setLesson2_2(dsp.getValue().toString());
                        break;
                    case "2_3":
                        call.setLesson2_3(dsp.getValue().toString());
                        break;
                    case "2_4":
                        call.setLesson2_4(dsp.getValue().toString());
                        break;
                    case "2_5":
                        call.setLesson2_5(dsp.getValue().toString());
                        break;
                    case "дата":
                        call.setDateText(dsp.getValue().toString());
                        break;
                }
            }
            return call;
        }
    }
}
