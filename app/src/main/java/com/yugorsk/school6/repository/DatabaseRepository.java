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
import io.reactivex.functions.Action;
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

    public LiveData<Date> getDate() {return dateDao.getAllDate();}

    public LiveData<List<Login>> getLogin() {return loginDao.getLogin();}

    public LiveData<List<News>> getNews() {return newsDao.getAllNews();}

    public LiveData<Schedule> getIndexSchedule() {return scheduleDao.getIndexSchedule();}

    public void insertDate(final Date dates)
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                dateDao.deleteAll();
                dateDao.insert(dates);
            }
        }).subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void insertIndexSchedule(final Schedule schedule)
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                scheduleDao.deleteAll();
                scheduleDao.insert(schedule);
            }
        }).subscribeOn(Schedulers.io())
                .subscribe();
    }

    public void insertLogin(final Login login)
    {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                loginDao.deleteAll();
                loginDao.insert(login);
            }
        }).subscribeOn(Schedulers.io())
                .subscribe();
    }
}
