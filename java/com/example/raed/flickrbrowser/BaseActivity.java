package com.example.raed.flickrbrowser;

import android.os.Bundle;

import com.example.raed.flickrbrowser.di.ActivityComponent;
import com.example.raed.flickrbrowser.di.BaseApplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by Raed on 31/07/2017.
 */

public class BaseActivity extends AppCompatActivity {
    public static final String FLICKER_QUERY = "flicker_query";
    public static final String PHOTO_TRANSFER = "photo_transfer";
    private static final String TAG = "BaseActivity";

    private ActivityComponent mActivityComponent;

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    void activeActionBar(boolean enableHome) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                actionBar = getSupportActionBar();
            }
        }

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(enableHome);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mActivityComponent = ((BaseApplication) getApplicationContext())
                .getApplicationComponent()
                .activityComponent()
                .create();

        super.onCreate(savedInstanceState);
    }
}
