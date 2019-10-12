package com.yugorsk.school6.viewmodel;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.google.firebase.storage.StorageReference;
import com.yugorsk.school6.App;
import com.yugorsk.school6.callback.ProgressDialogCallback;
import com.yugorsk.school6.callback.SnackbarCallback;
import com.yugorsk.school6.data.Call;
import com.yugorsk.school6.data.Date;
import com.yugorsk.school6.data.Login;
import com.yugorsk.school6.data.News;
import com.yugorsk.school6.data.Schedule;
import com.yugorsk.school6.repository.DataRepository;
import com.yugorsk.school6.repository.DatabaseRepository;

import java.util.ArrayList;
import java.util.List;

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

    public LiveData<Login> getLogin() {
        return database.getLogin();
    }

    public LiveData<List<News>> getNews() {
        return database.getNews();
    }

    public LiveData<Schedule> getIndexSchedule() {
        return database.getIndexSchedule();
    }

    public void insertIndexSchedule(Schedule schedule) {
        database.insertIndexSchedule(schedule);
    }

    public void insertDate(Date dates) {
        database.insertDate(dates);
    }

    public void insertLogin(Login login) {
        database.insertLogin(login);
    }

    public LiveData<Date> getDateFromServer() {
        return data.getDateLiveData();
    }

    public LiveData<StorageReference> getScheduleFromServer(int id) {
        return data.getScheduleLiveData(id);
    }

    public LiveData<Call> getCallFromServer() {
        return data.getCallLiveData();
    }

    public LiveData<List<Login>> getLoginFromServer() {
        return data.getLoginLiveData();
    }

    public void deleteLogin() {
        database.deleteLogin();
    }

    public void LoadPicture(Uri filePath, String number, ProgressDialogCallback callback, SnackbarCallback snackbarCallback) {
        data.LoadPicture(filePath, number, callback, snackbarCallback);
    }

    public LiveData<List<News>> getNewsFromServer() {
        return data.getNewsLiveData();
    }

    public void insertNews(List<News> news) {
        database.insertNews(news);
    }

    public void insertNews(News news) {
        database.insertNews(news);
    }
    public void deleteNews(News news) {
        database.deleteNews(news);
    }
}
