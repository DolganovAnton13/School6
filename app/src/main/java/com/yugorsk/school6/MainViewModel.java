package com.yugorsk.school6;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Database;


import com.yugorsk.school6.data.Date;
import com.yugorsk.school6.data.Login;
import com.yugorsk.school6.data.News;
import com.yugorsk.school6.data.Schedule;
import com.yugorsk.school6.repository.DataRepository;
import com.yugorsk.school6.repository.DatabaseRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private DataRepository data;
    private DatabaseRepository database;

    public MainViewModel(){
        database=App.getComponent().getDatabaseRepository();
        //data=App.getComponent().getDataRepository();

    }

    public LiveData<List<Date>> getDate()
    {
        return database.getDate();
    }

    public LiveData<List<Login>> getLogin()
    {
        return database.getLogin();
    }

    public LiveData<List<News>> getNews()
    {
        return database.getNews();
    }

    public LiveData<List<Schedule>> getSchedule()
    {
        return database.getSchedule();
    }

    public void updateDate(List<Date> dates)
    {
        database.saveDateList(dates);
    }

}
