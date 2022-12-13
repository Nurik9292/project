package com.example.profileusers.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import com.example.profileusers.MainActivity;
import com.example.profileusers.R;
import com.example.profileusers.databinding.UserProfileFragmentBinding;

public class UserProfileFragment extends Fragment {

    public static final String PHOTO_FILE_PATH_REQUEST = "photo_file_path";
    private String photoPath;
    private ActivityResultLauncher<Intent> someActivityResultLauncher;
    private ActivityResultLauncher<String> mPermissionResult;

    private UserProfileFragmentBinding binding;
    //передать ссылку на ViewModel
    private UserProfileViewModel viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(requireActivity()).get(UserProfileViewModel.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getResultFragmentPhotoGallery();

        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null)
                            viewModel.setPhotoUri(data.getData());
                    }
                });

        mPermissionResult = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                result -> {

                    if (result) {
                        Log.d("TEST", "Permission granted...!");
                    } else {
                        Log.d("TEST", "Permission denied...!");
                    }
                });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.user_profile_fragment, container, false);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(viewModel);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getShowStandardPhotoGallery().observe(getViewLifecycleOwner(), event -> {
            if (event.isHandled()) showStandardGallery();
        });

        viewModel.getShowPermission().observe(getViewLifecycleOwner(), event -> {
            if (event.isHandled()) showPermission();
        });

        viewModel.getShowPhotoGallery().observe(getViewLifecycleOwner(), event -> {
            if (event.isHandled()) MainActivity.userProfileToPhotoGallery(requireActivity());
        });

    }

    private void showStandardGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        someActivityResultLauncher.launch(intent);

    }

    private void getResultFragmentPhotoGallery() {
        requireActivity().getSupportFragmentManager().setFragmentResultListener(PHOTO_FILE_PATH_REQUEST, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                photoPath = result.getString(PHOTO_FILE_PATH_REQUEST);
                Log.d("TEST", "PHOTO_FILE_PATH_REQUEST:" + photoPath);
                viewModel.setPhotoPathString(photoPath);
            }
        });
    }

    private void showPermission() {
        //String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
        //ActivityCompat.requestPermissions(requireActivity(), permissions, PERMISSION_CODE);
        //альтернативный вариант
        mPermissionResult.launch(Manifest.permission.READ_EXTERNAL_STORAGE, null);
    }

}