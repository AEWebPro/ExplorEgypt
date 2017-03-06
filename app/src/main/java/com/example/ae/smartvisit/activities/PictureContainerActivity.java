package com.example.ae.smartvisit.activities;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.fragments.ViewedImageFragment;

public class PictureContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_fragment_container);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_picture_fragment_container);
        if(fragment == null){
            fragment = new ViewedImageFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.activity_picture_fragment_container, fragment).commit();
        }
    }

    public static Intent newIntent(Context context, String imageUrl){
        Intent intent = new Intent(context,PictureContainerActivity.class);
        intent.putExtra("url",imageUrl);
        return intent;
    }
}
