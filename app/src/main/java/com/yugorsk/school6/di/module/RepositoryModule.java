package com.yugorsk.school6.di.module;

import com.yugorsk.school6.di.module.DatabaseModule;
import com.yugorsk.school6.di.module.NetworkModule;

import dagger.Module;

@Module(includes = {NetworkModule.class, DatabaseModule.class})
public class RepositoryModule {

}
