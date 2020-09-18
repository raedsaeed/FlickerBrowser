package com.example.raed.flickrbrowser;

import android.content.Context;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Raed on 30/07/2017.
 */

class RecyclerItemClicklistener extends RecyclerView.SimpleOnItemTouchListener {
    private static final String TAG = "RecyclerItemClicklisten";

    interface OnRecyclerClickListener {
        void onItemClick (View view, int position);
        void onItemLongClick (View view, int position );
    }

    private final OnRecyclerClickListener mListener;
    private final GestureDetectorCompat mGestureDetector;

    public RecyclerItemClicklistener(Context context, final RecyclerView recyclerView, OnRecyclerClickListener onRecyclerClickListener) {
        mListener = onRecyclerClickListener;
        mGestureDetector = new GestureDetectorCompat(context, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                Log.d(TAG, "onSingleTapUp: starts");
                View childView = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());
                if(mListener != null && childView != null )
                {
                    Log.d(TAG, "onSingleTapUp: calling Listener onItemClick");
                    mListener.onItemClick(childView,recyclerView.getChildAdapterPosition(childView));
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                Log.d(TAG, "onLongPress: starts");
                View childView = recyclerView.findChildViewUnder(motionEvent.getX(),motionEvent.getY());
                if(childView != null && mListener != null){
                    Log.d(TAG, "onLongPress: calling Listener onItemLongClick");
                    mListener.onItemLongClick(childView,recyclerView.getChildAdapterPosition(childView));
                }
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        Log.d(TAG, "onInterceptTouchEvent: starts");
        if(mGestureDetector != null ){
            boolean result = mGestureDetector.onTouchEvent(e);
            Log.d(TAG, "onInterceptTouchEvent() returned: " + result);
            return result;
        }
        else {
            return false;
        }
    }
}
