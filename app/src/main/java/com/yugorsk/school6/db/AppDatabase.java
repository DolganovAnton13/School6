package com.yugorsk.school6.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.yugorsk.school6.data.Date;
import com.yugorsk.school6.data.Login;
import com.yugorsk.school6.data.News;
import com.yugorsk.school6.data.Schedule;
import com.yugorsk.school6.db.dao.DateDao;
import com.yugorsk.school6.db.dao.LoginDao;
import com.yugorsk.school6.db.dao.NewsDao;
import com.yugorsk.school6.db.dao.ScheduleDao;

@Database(entities = {Date.class, Login.class, News.class, Schedule.class},version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DateDao dateDao();
    public abstract LoginDao loginDao();
    public abstract NewsDao newsDao();
    public abstract ScheduleDao scheduleDao();

}
