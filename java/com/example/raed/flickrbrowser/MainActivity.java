package com.example.raed.flickrbrowser;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.raed.flickrbrowser.di.ViewModelFactory;
import com.example.raed.flickrbrowser.objects.Photo;
import com.example.raed.flickrbrowser.objects.Result;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity implements RecyclerItemClicklistener.OnRecyclerClickListener {
    private static final String TAG = "MainActivity";
    @Inject
    ViewModelFactory mViewModelFactory;
    private FlickrRecyclerViewAdapter mFlickrRecyclerViewAdapter;
    private ProgressBar mProgressBar;
    private PhotoViewModel mPhotoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivityComponent().inject(this);

        setContentView(R.layout.activity_main);
        activeActionBar(false);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(new RecyclerItemClicklistener(this, recyclerView, this));

        mProgressBar = findViewById(R.id.progress_bar);

        mFlickrRecyclerViewAdapter = new FlickrRecyclerViewAdapter(this, new ArrayList<Photo>());
        recyclerView.setAdapter(mFlickrRecyclerViewAdapter);

        mPhotoViewModel = new ViewModelProvider(this, mViewModelFactory).get(PhotoViewModel.class);
        mPhotoViewModel.getPhotoData().observe(this, this::populateUI);
        mPhotoViewModel.getPhotos("dog");
    }

    private void populateUI(Result<List<Photo>> result) {
        switch (result.getStatus()) {
            case ERROR:
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                break;

            case LOADING:
                mProgressBar.setVisibility(View.VISIBLE);
                break;

            case SUCCESS:
                mProgressBar.setVisibility(View.GONE);
                mFlickrRecyclerViewAdapter.loadNewData(result.getData());
                break;
        }
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: Starts");
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        Log.d(TAG, "onCreateOptionsMenu() returned: " + true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d(TAG, "onItemClick: starts");
//        Toast.makeText(MainActivity.this, "Normal tap at position "+ position,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PhotoDetailActivity.class);
        intent.putExtra(PHOTO_TRANSFER, mFlickrRecyclerViewAdapter.getPhoto(position));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Log.d(TAG, "onItemLongClick: starts");
//        Toast.makeText(MainActivity.this,"Long tap at position "+ position, Toast.LENGTH_SHORT).show();
    }
}
