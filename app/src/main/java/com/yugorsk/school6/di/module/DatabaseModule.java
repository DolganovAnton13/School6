package com.yugorsk.school6.di.module;

import android.content.Context;

import androidx.room.Room;

import com.yugorsk.school6.data.Date;
import com.yugorsk.school6.db.AppDatabase;
import com.yugorsk.school6.db.dao.DateDao;
import com.yugorsk.school6.db.dao.LoginDao;
import com.yugorsk.school6.db.dao.NewsDao;
import com.yugorsk.school6.db.dao.ScheduleDao;
import com.yugorsk.school6.di.module.ContextModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class DatabaseModule {

    @Singleton
    @Provides
    AppDatabase appDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "database")
                .fallbackToDestructiveMigration() // обнуляет базу, если нет подходящей миграции
                .build();
    }

    @Provides
    DateDao dateDao(AppDatabase appDatabase) {
        return appDatabase.dateDao();
    }

    @Provides
    LoginDao loginDao(AppDatabase appDatabase) {
        return appDatabase.loginDao();
    }

    @Provides
    NewsDao newsDao(AppDatabase appDatabase) {
        return appDatabase.newsDao();
    }

    @Provides
    ScheduleDao scheduleDao(AppDatabase appDatabase) {
        return appDatabase.scheduleDao();
    }
}
