package com.example.raed.flickrbrowser.di;

import com.example.raed.flickrbrowser.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Raed Saeed on 11/6/2019.
 */
@ActivityScope
@Subcomponent()
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    @Subcomponent.Factory
    interface Factory {
        ActivityComponent create();
    }
}
