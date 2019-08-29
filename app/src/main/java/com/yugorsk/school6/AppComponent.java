package com.yugorsk.school6;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class, NetworkModule.class, DatabaseModule.class, ContextModule.class})
public interface AppComponent {


}
