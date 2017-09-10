package com.example.raed.flickrbrowser;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Raed on 30/07/2017.
 */

class FlickrRecyclerViewAdapter extends RecyclerView.Adapter <FlickrRecyclerViewAdapter.FlickrImageViewHolder>{
    private static final String TAG = "FlickrRecyclerViewAdapt";
    private Context mContext;
    private List<Photo> mPhotoList ;


    public FlickrRecyclerViewAdapter(Context context, List<Photo> photoList) {
        mContext = context;
        mPhotoList = photoList;
    }

    @Override
    public int getItemCount() {
        return ((mPhotoList != null) && (mPhotoList.size() != 0) ? mPhotoList.size() : 1);
    }

    @Override
    public void onBindViewHolder(FlickrImageViewHolder holder, int position) {
        if((mPhotoList == null) || (mPhotoList.size() == 0 )){
            holder.thumbnail.setImageResource(R.drawable.imageholder);
            holder.title.setText(R.string.empty_photo_message);
        }else {
            Photo photoItem = mPhotoList.get(position);
            Log.d(TAG, "onBindViewHolder: " + photoItem.getTitle() + "----> " + position);

            Picasso.with(mContext).load(photoItem.getImage())
                    .error(R.drawable.imageholder)
                    .placeholder(R.drawable.imageholder)
                    .into(holder.thumbnail);

            holder.title.setText(photoItem.getTitle());
        }
    }

    @Override
    public FlickrImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.browse,parent,false);
        return new FlickrImageViewHolder(view);
    }

    void loadNewData (List<Photo> photos)
    {
        mPhotoList = photos;
        notifyDataSetChanged();
    }

    public Photo getPhoto (int position){
        return ((mPhotoList != null) && (mPhotoList.size() != 0 ) ? mPhotoList.get(position) : null ) ;
    }
    static class FlickrImageViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "FlickrImageViewHolder";
        ImageView thumbnail = null;
        TextView title = null;

        public FlickrImageViewHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
