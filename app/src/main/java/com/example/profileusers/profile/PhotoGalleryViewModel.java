package com.example.profileusers.profile;

import android.app.Application;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PhotoGalleryViewModel extends AndroidViewModel {

    public PhotoGalleryViewModel(@NonNull Application application) {
        super(application);
        loadListPhotoGallery();
    }

    private final PhotoGalleryModel model = new PhotoGalleryModel();
    private final MutableLiveData<List<Photo>> listPhotosGallery = new MutableLiveData<>();
    private final List<Photo> listPhotos = new ArrayList<>();
    private final MutableLiveData<Event> ResultEventPhotoGallery = new MutableLiveData<>();

    public LiveData<List<Photo>> getListPhotosGallery() {
        return listPhotosGallery;
    }

    public LiveData<Event> getResultEventPhotoGallery() {
        return ResultEventPhotoGallery;
    }

    public void setResultEventPhotoGallery(Photo photo) {
        Bundle result = new Bundle();
        result.putString(UserProfileFragment.PHOTO_FILE_PATH_REQUEST, photo.getPhotoFilePath());
        ResultEventPhotoGallery.setValue(new Event(result));

    }

    private void loadListPhotoGallery() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
            Log.d("TEST", "MEDIA_MOUNTED: OK");
            Log.d("TEST", root.getAbsolutePath());
            listPhotos.addAll(model.getListPhotos(root));
            listPhotosGallery.setValue(listPhotos);
        } else {
            Log.d("TEST", "MEDIA_MOUNTED: ERR");
        }

    }

}
