package com.example.raed.flickrbrowser;

import com.example.raed.flickrbrowser.objects.Photo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Raed Saeed on 18/09/2020.
 */
public class PhotoRepository {
    private ApiService mApiService;

    @Inject
    public PhotoRepository(ApiService apiService) {
        mApiService = apiService;
    }

    public Observable<List<Photo>> getPhotos(String tag) {
        return mApiService.getPhotos("flickr.photos.search", "be3763b71a482ecfdc1be37e13a5e568", tag, "url_m", "json", 1)
                .map(response -> response.getPhotos().getPhotoList());
    }
}
