package com.yugorsk.school6;

import android.app.Application;

import com.yugorsk.school6.di.AppComponent;
import com.yugorsk.school6.di.DaggerAppComponent;
import com.yugorsk.school6.di.module.ContextModule;

import io.reactivex.plugins.RxJavaPlugins;

public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        //RxJavaPlugins.setErrorHandler(throwable ->{});

        component = DaggerAppComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build();
    }

    public static AppComponent getComponent()
    {
        return component;
    }
}
