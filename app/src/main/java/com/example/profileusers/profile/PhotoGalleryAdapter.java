package com.example.profileusers.profile;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.profileusers.R;
import com.example.profileusers.databinding.PhotoItemGalleryFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class PhotoGalleryAdapter extends RecyclerView.Adapter<PhotoGalleryAdapter.PhotoGalleryHolder> {


    private List<Photo> listPhoto = new ArrayList<>();
    final PhotoClickListener photoClickListener;

    public PhotoGalleryAdapter(PhotoClickListener photoClickListener) {
        this.photoClickListener = photoClickListener;
    }

    public void setItems(List<Photo> listPhoto) {
        this.listPhoto = listPhoto;

    }


    @NonNull
    @Override
    public PhotoGalleryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotoGalleryHolder(
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.photo_item_gallery_fragment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoGalleryAdapter.PhotoGalleryHolder holder, int position) {
        holder.bind(listPhoto.get(position),photoClickListener);
    }

    @Override
    public int getItemCount() {
        return listPhoto.size();
    }


    static class PhotoGalleryHolder extends RecyclerView.ViewHolder {

        PhotoItemGalleryFragmentBinding binding;

        public PhotoGalleryHolder(@NonNull PhotoItemGalleryFragmentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Photo photo, PhotoClickListener photoClickListener) {
            binding.setPhoto(photo);
            binding.setClickListener(photoClickListener);
        }
    }

}
