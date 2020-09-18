package com.example.raed.flickrbrowser.di;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;


/**
 * Created by Raed Saeed on 11/3/2019.
 */
@ApplicationScope
@Component(modules = {NetworkModule.class,
        ContextModule.class,
        LocalModule.class,
        ViewModelModule.class,
        SubComponentModule.class,
        AndroidInjectionModule.class})
public interface ApplicationComponent {

    ActivityComponent.Factory activityComponent();

    FragmentComponent.Factory fragmentComponent();

    ServiceComponent.Factory serviceComponent();

    DialogComponent.Factory dialogComponent();

    void inject(BaseApplication app);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(BaseApplication application);

//        @BindsInstance
//        Builder networkModule(NetworkModule networkModule);
//
//        @BindsInstance
//        Builder localModule(LocalModule localModule);
//
//        @BindsInstance
//        Builder firebaseModule(FirebaseModule firebaseModule);

        ApplicationComponent create();
    }
}
