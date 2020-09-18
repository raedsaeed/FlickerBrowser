package com.example.raed.flickrbrowser.di;

import android.app.Application;

/**
 * Created by Raed Saeed on 18/09/2020.
 */
public class BaseApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .application(this)
                .create();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
