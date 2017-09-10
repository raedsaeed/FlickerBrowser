package com.example.raed.flickrbrowser;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by Raed on 31/07/2017.
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    public static final String FLICKER_QUERY = "flicker_query";
    public static final String PHOTO_TRANSFER = "photo_transfer";

    void activeActionBar (boolean enableHome)
    {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar == null){
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            if(toolbar != null){
                setSupportActionBar(toolbar);
                actionBar = getSupportActionBar();
            }
        }

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(enableHome);
        }
    }
}
