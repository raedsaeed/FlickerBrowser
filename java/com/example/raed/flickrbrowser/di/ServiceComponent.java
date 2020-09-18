package com.example.raed.flickrbrowser.di;

import dagger.Subcomponent;

/**
 * Created by Raed Saeed on 11/16/2019.
 */
@ServiceScope
@Subcomponent()
public interface ServiceComponent {

    @Subcomponent.Factory
    interface Factory {
        ServiceComponent create();
    }
}
