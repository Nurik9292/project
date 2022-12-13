package com.example.profileusers;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import com.example.profileusers.profile.UserProfileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Fragment userProfileFragment = fragmentManager.findFragmentById(R.id.main_fragment_container);
//        if (userProfileFragment == null) {
//            userProfileFragment = new UserProfileFragment();
//            fragmentManager.beginTransaction()
//                    .add(R.id.main_fragment_container, userProfileFragment)
//                    .commit();
//        }

    }



    // UI NAVIGATOR
    public static void userProfileToPhotoGallery(Activity activity) {
        Navigation.findNavController(activity, R.id.main_fragment_container)
                .navigate(R.id.action_userProfileFragment_to_photoGalleryFragment, null, null);
    }

}
