package com.example.raed.flickrbrowser;

import java.io.Serializable;

/**
 * Created by Raed on 28/07/2017.
 */

class Photo implements Serializable{
    private static final long serialVersionUID = 1L;
    private String mTitle;
    private String mAuthor;
    private String mAuthorID;
    private String mLink;
    private String mTag;
    private String mImage;

    public Photo(String title, String author, String authorID, String link, String tag, String image) {
        mTitle = title;
        mAuthor = author;
        mAuthorID = authorID;
        mLink = link;
        mTag = tag;
        mImage = image;
    }

    String getTitle() {
        return mTitle;
    }

    String getAuthor() {
        return mAuthor;
    }

    String getAuthorID() {
        return mAuthorID;
    }

    String getLink() {
        return mLink;
    }

    String getTag() {
        return mTag;
    }

    String getImage() {
        return mImage;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "mTitle='" + mTitle + '\'' +
                ", mAuthor='" + mAuthor + '\'' +
                ", mAuthorID='" + mAuthorID + '\'' +
                ", mLink='" + mLink + '\'' +
                ", mTag='" + mTag + '\'' +
                ", mImage='" + mImage + '\'' +
                '}';
    }
}
