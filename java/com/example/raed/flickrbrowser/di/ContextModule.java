package com.example.raed.flickrbrowser.di;

import android.content.Context;


import dagger.Module;
import dagger.Provides;

/**
 * Created by Raed Saeed on 11/5/2019.
 */
@Module
public class ContextModule {
    @ApplicationScope
    @Provides
    Context getContext(BaseApplication application) {
        return application.getApplicationContext();
    }
}
