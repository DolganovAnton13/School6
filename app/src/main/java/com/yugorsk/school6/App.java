package com.yugorsk.school6;

import android.app.Application;

import com.yugorsk.school6.di.AppComponent;
import com.yugorsk.school6.di.DaggerAppComponent;
import com.yugorsk.school6.di.module.ContextModule;

public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build();
    }
}
