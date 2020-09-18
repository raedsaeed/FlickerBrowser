package com.example.raed.flickrbrowser;

import com.example.raed.flickrbrowser.objects.PhotoResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Raed Saeed on 18/09/2020.
 */
public interface ApiService {
    @GET("rest/")
    Observable<PhotoResponse> getPhotos(@Query("method") String method,
                                        @Query("api_key") String key,
                                        @Query("tags") String tag,
                                        @Query("extras") String extras,
                                        @Query("format") String format,
                                        @Query("nojsoncallback") int callback);
}
