package com.yugorsk.school6.di;

import com.yugorsk.school6.di.module.DatabaseModule;
import com.yugorsk.school6.di.module.RepositoryModule;
import com.yugorsk.school6.di.module.ContextModule;
import com.yugorsk.school6.repository.DataRepository;
import com.yugorsk.school6.repository.DatabaseRepository;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class, DatabaseModule.class, ContextModule.class})
public interface AppComponent {

        DataRepository getDataRepository();
        DatabaseRepository getDatabaseRepository();
}
