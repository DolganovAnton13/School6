package com.yugorsk.school6.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Database;


import com.google.firebase.storage.StorageReference;
import com.yugorsk.school6.App;
import com.yugorsk.school6.data.Date;
import com.yugorsk.school6.data.Login;
import com.yugorsk.school6.data.News;
import com.yugorsk.school6.data.Schedule;
import com.yugorsk.school6.repository.DataRepository;
import com.yugorsk.school6.repository.DatabaseRepository;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainViewModel extends ViewModel {

    private DataRepository data;
    private DatabaseRepository database;

    public MainViewModel() {
        database = App.getComponent().getDatabaseRepository();
        data = App.getComponent().getDataRepository();
    }

    public LiveData<Date> getDate() {
        return database.getDate();
    }

    public LiveData<List<Login>> getLogin() {
        return database.getLogin();
    }

    public LiveData<List<News>> getNews() {
        return database.getNews();
    }

    public LiveData<Schedule> getSchedule() {
        return database.getSchedule();
    }

    public void insertSchedule(Schedule schedule) {
        database.insertSchedule(schedule);
    }

    public void insertDate(Date dates) {
        database.insertDate(dates);
    }

    public LiveData<List<String>> getDateFromServer() {
        return data.getDateLiveData();
    }

    public LiveData<List<StorageReference>> getScheduleFromServer()
    {
        return data.getScheduleLiveData();
    }
}
