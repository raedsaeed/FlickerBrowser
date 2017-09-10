package com.example.raed.flickrbrowser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements GetFlickrJsonData.OnAvailableData, RecyclerItemClicklistener.OnRecyclerClickListener {
    private static final String TAG = "MainActivity";
    private FlickrRecyclerViewAdapter mFlickrRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activeActionBar(false);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(new RecyclerItemClicklistener(this, recyclerView, this));

        mFlickrRecyclerViewAdapter = new FlickrRecyclerViewAdapter(this,new ArrayList<Photo>());
        recyclerView.setAdapter(mFlickrRecyclerViewAdapter);
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: Starts");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String queryResult = sharedPreferences.getString(FLICKER_QUERY,"");
        if(queryResult.length() > 0 ) {
            GetFlickrJsonData getFlickrJsonData = new GetFlickrJsonData(this, "https://api.flickr.com/services/feeds/photos_public.gne", "en-us", true);
            getFlickrJsonData.execute(queryResult);
        }
        Log.d(TAG, "onResume: Ends");
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
        if(id == R.id.action_search){
            Intent intent = new Intent(this,SearchActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAvailableData(List<Photo> data, DownloadStatus status) {
        Log.d(TAG, "onAvailableData: starts");
        if(status == DownloadStatus.OK) {
            mFlickrRecyclerViewAdapter.loadNewData(data);
        }
        else Log.d(TAG, "onAvailableData: Error downloading data");
        Log.d(TAG, "onAvailableData: ends");
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d(TAG, "onItemClick: starts");
//        Toast.makeText(MainActivity.this, "Normal tap at position "+ position,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,PhotoDetailActivity.class);
        intent.putExtra(PHOTO_TRANSFER,mFlickrRecyclerViewAdapter.getPhoto(position));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Log.d(TAG, "onItemLongClick: starts");
//        Toast.makeText(MainActivity.this,"Long tap at position "+ position, Toast.LENGTH_SHORT).show();
    }
}
