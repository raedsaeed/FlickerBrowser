package com.example.raed.flickrbrowser.di;


import dagger.Subcomponent;

/**
 * Created by Raed Saeed on 11/5/2019.
 * this class for injecting fragments
 * Note : every fragment should has it's injection method
 * e.g if I want to inject MyFragment
 * the injection method should be like this
 * void inject(MyFragment fragment)
 */
@FragmentScope
@Subcomponent
public interface FragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        FragmentComponent create();
    }
}
