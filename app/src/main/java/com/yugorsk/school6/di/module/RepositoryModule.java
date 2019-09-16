package com.yugorsk.school6.di.module;

import com.yugorsk.school6.db.dao.DateDao;
import com.yugorsk.school6.db.dao.LoginDao;
import com.yugorsk.school6.db.dao.NewsDao;
import com.yugorsk.school6.db.dao.ScheduleDao;
import com.yugorsk.school6.repository.DataRepository;
import com.yugorsk.school6.repository.DatabaseRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {DatabaseModule.class})
public class RepositoryModule {

    @Singleton
    @Provides
    DatabaseRepository databaseRepository(DateDao dateDao, LoginDao loginDao, NewsDao newsDao, ScheduleDao scheduleDao)
    {
        return new DatabaseRepository(dateDao,loginDao,newsDao,scheduleDao);
    }

    @Singleton
    @Provides
    DataRepository dataRepository()
    {
        return new DataRepository();
    }
}
