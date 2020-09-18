package com.example.raed.flickrbrowser;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.raed.flickrbrowser.objects.Photo;
import com.squareup.picasso.Picasso;

public class PhotoDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        activeActionBar(true);

        Intent intent = getIntent();
        Photo photo = (Photo) intent.getSerializableExtra(PHOTO_TRANSFER);
        if(photo != null )
        {
            Resources resources = getResources();
            TextView photoTitle = (TextView) findViewById(R.id.photo_title);
            String title = resources.getString(R.string.photo_title_text,photo.getTitle());
            photoTitle.setText(title);

            TextView photoAuthor = (TextView) findViewById(R.id.photo_author);
//            photoAuthor.setText(photo.getAuthor());

            TextView photoTags = (TextView) findViewById(R.id.photo_tags);
//            String tags = resources.getString(R.string.photo_tags_text,photo.getTag());
//            photoTags.setText(tags);

            ImageView photoImage = (ImageView) findViewById(R.id.photo_image);
            Picasso.with(this).load(photo.getUrlM())
                    .error(R.drawable.imageholder)
                    .placeholder(R.drawable.imageholder)
                    .into(photoImage);
        }

    }

}
