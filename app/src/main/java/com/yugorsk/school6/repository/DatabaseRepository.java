package com.yugorsk.school6.repository;

import androidx.lifecycle.LiveData;

import com.yugorsk.school6.data.Date;
import com.yugorsk.school6.data.Login;
import com.yugorsk.school6.data.News;
import com.yugorsk.school6.data.Schedule;
import com.yugorsk.school6.db.dao.DateDao;
import com.yugorsk.school6.db.dao.LoginDao;
import com.yugorsk.school6.db.dao.NewsDao;
import com.yugorsk.school6.db.dao.ScheduleDao;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class DatabaseRepository {

    private DateDao dateDao;
    private LoginDao loginDao;
    private NewsDao newsDao;
    private ScheduleDao scheduleDao;

    public DatabaseRepository(DateDao dateDao, LoginDao loginDao, NewsDao newsDao, ScheduleDao scheduleDao) {
        this.dateDao = dateDao;
        this.loginDao = loginDao;
        this.newsDao = newsDao;
        this.scheduleDao = scheduleDao;
    }

    public LiveData<List<Date>> getDate() {return dateDao.getAllDate();}

    public LiveData<List<Login>> getLogin() {return loginDao.getAllLogin();}

    public LiveData<List<News>> getNews() {return newsDao.getAllNews();}

    public LiveData<List<Schedule>> getSchedule() {return scheduleDao.getAllSchedule();}

    public void saveDateList(final List<Date> dates)
    {
        /*Completable.fromAction(() -> dateDao.insert(dates))
                .subscribeOn(Schedulers.io())
                .subscribe();*/
    }
}