package com.yugorsk.school6;

import dagger.Module;

@Module(includes = {NetworkModule.class,DatabaseModule.class})
public class RepositoryModule {
}
