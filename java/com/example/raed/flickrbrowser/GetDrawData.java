package com.example.raed.flickrbrowser;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

enum DownloadStatus {IDlE, NOT_INITIALIZED, PROCESSING, FAILED_OR_EMPTY, OK }

/**
 * Created by Raed on 27/07/2017.
 */

class GetDrawData extends AsyncTask<String, Void, String> {
    private static final String TAG = "GetDrawData";
    private DownloadStatus mDownloadStatus;
    private OnDownloadComplete mCallback;

    public interface OnDownloadComplete {
         void onDownloadDataComplete(String data, DownloadStatus status);
    }

    public GetDrawData(OnDownloadComplete callBack) {
        this.mDownloadStatus = DownloadStatus.IDlE;
        mCallback = callBack;
    }

    public void runInSameThread (String s)
    {
        Log.d(TAG, "runInSameThread: starts");
        onPostExecute(doInBackground(s));
        Log.d(TAG, "runInSameThread: ends");
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d(TAG, "onPostExecute: started");
        if(s!=null)
        {
            mCallback.onDownloadDataComplete(s, mDownloadStatus);
        }
        Log.d(TAG, "onPostExecute: ended");
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader reader = null ;
        if(strings==null)
        {
            mDownloadStatus = DownloadStatus.NOT_INITIALIZED ;
            return null;
        }
        try {
            mDownloadStatus = DownloadStatus.PROCESSING;
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            Log.d(TAG, "doInBackground: Response Code "+responseCode);
            StringBuilder result = new StringBuilder();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputReader);
            String line;
            while (null != (line = reader.readLine())){
                result.append(line).append('\n');
            }
            mDownloadStatus = DownloadStatus.OK;
            return result.toString();

        }catch (MalformedURLException e){
            Log.e(TAG, "doInBackground: Invalid URL "+e.getMessage());
        }catch (IOException e){
            Log.e(TAG, "doInBackground: IOException reading data "+e.getMessage() );
        }catch (SecurityException e){
            Log.e(TAG, "doInBackground: Need permissions? " +e.getMessage());
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
            if(reader!=null){
                try {
                    reader.close();
                }catch (IOException e){
                    Log.e(TAG, "doInBackground: Error in closing reader"+e.getMessage());
                }
            }
        }
        mDownloadStatus = DownloadStatus.FAILED_OR_EMPTY;
        return null;
    }
}
