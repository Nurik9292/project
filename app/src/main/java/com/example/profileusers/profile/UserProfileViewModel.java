package com.example.profileusers.profile;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class UserProfileViewModel extends AndroidViewModel {

    private final MutableLiveData<Event> showStandardPhotoGallery = new MutableLiveData<>();
    private final MutableLiveData<Event> showPermission = new MutableLiveData<>();
    private final MutableLiveData<Event> showPhotoGallery = new MutableLiveData<>();
    private final MutableLiveData<Uri> photoUri = new MutableLiveData<>();
    private final MutableLiveData<String> photoPathString = new MutableLiveData<>();


    //галерея фотографий
    public LiveData<Event> getShowPhotoGallery() {
        return showPhotoGallery;
    }

    //стандартная галерея фотографий
    public LiveData<Event> getShowStandardPhotoGallery() {
        return showStandardPhotoGallery;
    }

    //фотография path
    public LiveData<String> getPhotoPathString() {
        return photoPathString;
    }
    public void setPhotoPathString(String photoPath){
        photoPathString.setValue(photoPath);
    }

    //фотография Uri
    public LiveData<Uri> getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(Uri uriPhoto) {
        photoUri.setValue(uriPhoto);
    }

    //разрешение
    public LiveData<Event> getShowPermission() {
        return showPermission;
    }




    public UserProfileViewModel(Application application) {
        super(application);
    }


    public void onSelectPhotoClicked() {
        if (isPermission())
            showStandardPhotoGallery.setValue(new Event(new Bundle()));
        else
            showPermission.setValue(new Event(new Bundle()));
    }

    public void onSelectPhotoGalleryClicked() {
        if (isPermission())
            showPhotoGallery.setValue(new Event(new Bundle()));
        else
            showPermission.setValue(new Event(new Bundle()));
    }


    private boolean isPermission() {
        return ActivityCompat.checkSelfPermission(
                getApplication(), Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_DENIED;
    }

}
