package com.example.raed.flickrbrowser.di;


import dagger.Subcomponent;

/**
 * Created by Raed Saeed on 11/7/2019.
 */
@DialogScope
@Subcomponent()
public interface DialogComponent {
    @Subcomponent.Factory
    interface Factory {
        DialogComponent create();
    }
}
