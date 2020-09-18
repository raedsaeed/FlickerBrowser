package com.example.raed.flickrbrowser.di;


import com.example.raed.flickrbrowser.PhotoViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Raed Saeed on 11/6/2019.
 */
@SuppressWarnings("unused")
@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(PhotoViewModel.class)
    abstract ViewModel bindPhotoViewModel(PhotoViewModel photoViewModel);
}
