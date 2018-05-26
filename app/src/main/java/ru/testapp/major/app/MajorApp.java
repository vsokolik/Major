package ru.testapp.major.app;

import android.app.Application;

import ru.testapp.major.di.AppComponent;
import ru.testapp.major.di.DaggerAppComponent;
import ru.testapp.major.di.modules.RetrofitModule;

public class MajorApp extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .retrofitModule(new RetrofitModule())
                .build();

        appComponent.inject(this);
    }


    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
