package com.example.raed.flickrbrowser;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raed on 28/07/2017.
 */

class GetFlickrJsonData extends AsyncTask<String, Void, List<Photo>> implements GetDrawData.OnDownloadComplete {

    private static final String TAG = "GetFlickrJsonData";
    private String mBaseURL;
    private String mLang;
    private List<Photo> mPhotoList;
    private boolean mMatchAll;
    private final OnAvailableData mCallBack;
    private boolean runOnSameThread = false;


    interface OnAvailableData{
        void onAvailableData (List<Photo> photoList, DownloadStatus status);
    }

    public GetFlickrJsonData(OnAvailableData callBack, String baseURL, String lang, boolean matchAll) {
        Log.d(TAG, "GetFlickrJsonData: called");
        mCallBack = callBack;
        mBaseURL = baseURL;
        mLang = lang;
        mMatchAll = matchAll;
    }

    void executeOnSameThread (String searchCriteria)
    {
        Log.d(TAG, "executeOnSameThread: started");
        String destinationUri = createUri(searchCriteria,mLang,mMatchAll);
        runOnSameThread = true;

        GetDrawData getDrawdata = new GetDrawData(this);
        getDrawdata.execute(destinationUri);
    }

    private String createUri(String searchCriteria, String lang, boolean matchAll)
    {
        return Uri.parse(mBaseURL).buildUpon()
                .appendQueryParameter("tags",searchCriteria)
                .appendQueryParameter("tagmode",matchAll? "ALL" : "ANY")
                .appendQueryParameter("lang",lang)
                .appendQueryParameter("format","json")
                .appendQueryParameter("nojsoncallback","1")
                .build().toString();
    }

    @Override
    protected List<Photo> doInBackground(String... params) {
        Log.d(TAG, "doInBackground: starts");
        String destinationUrl = createUri(params[0],mLang,mMatchAll);
        GetDrawData getDrawData = new GetDrawData(this);
        getDrawData.runInSameThread(destinationUrl);
        Log.d(TAG, "doInBackground: ends");
        return mPhotoList;
    }

    @Override
    protected void onPostExecute(List<Photo> photoList) {
        Log.d(TAG, "onPostExecute: starts");
        if(mCallBack != null)
        {
            mCallBack.onAvailableData(mPhotoList,DownloadStatus.OK);
        }
        Log.d(TAG, "onPostExecute: ends");
    }

    @Override
    public void onDownloadDataComplete(String data, DownloadStatus status) {
        Log.d(TAG, "onDownloadDataComplete: starts , status "+status);
        if(status == DownloadStatus.OK)
        {
            mPhotoList = new ArrayList<>();
            try {
                JSONObject jsonData = new JSONObject(data);
                JSONArray itemsArray = jsonData.getJSONArray("items");

                for(int i =0; i<itemsArray.length(); i++)
                {
                    JSONObject jsonPhoto = itemsArray.getJSONObject(i);
                    String title = jsonPhoto.getString("title");
                    String author = jsonPhoto.getString("author");
                    String authorId = jsonPhoto.getString("author_id");
                    String tags = jsonPhoto.getString("tags");

                    JSONObject jsonMedia = jsonPhoto.getJSONObject("media");
                    String photoUrl = jsonMedia.getString("m");
                    String link = photoUrl.replace("_m.","_b.");

                    Photo photoObject = new Photo(title,author,authorId,link,tags,photoUrl);
                    mPhotoList.add(photoObject);

                    Log.d(TAG, "onDownloadDataComplete: Processing complete "+photoObject.toString());
                }
            }catch (JSONException e){
                Log.e(TAG, "onDownloadDataComplete: Error "+e.getMessage() );
                status = DownloadStatus.FAILED_OR_EMPTY ;
            }
            if(runOnSameThread && mCallBack != null)
            {
                mCallBack.onAvailableData(mPhotoList,status);
            }
            Log.d(TAG, "onDownloadDataComplete: ends");
        }
    }
}






























