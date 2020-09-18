package com.example.raed.flickrbrowser;

import android.appwidget.AppWidgetHost;

import com.example.raed.flickrbrowser.objects.Photo;
import com.example.raed.flickrbrowser.objects.Result;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Raed Saeed on 18/09/2020.
 */
public class PhotoViewModel extends ViewModel {
    private PhotoRepository mRepository;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private MutableLiveData<Result<List<Photo>>> photoData = new MutableLiveData<>();
    private Result<List<Photo>> mResult = new Result<>();

    @Inject
    public PhotoViewModel(PhotoRepository repository) {
        mRepository = repository;
    }

    public void getPhotos(String tag) {
        mCompositeDisposable.add(mRepository.getPhotos(tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> mResult.loading())
                .subscribe(response -> photoData.postValue(mResult.setData(response)),
                        error -> photoData.postValue(mResult.setError(error))));
    }

    public MutableLiveData<Result<List<Photo>>> getPhotoData() {
        return photoData;
    }
}
