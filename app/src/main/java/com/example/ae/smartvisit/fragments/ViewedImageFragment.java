package com.example.ae.smartvisit.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ae.smartvisit.R;
import com.example.ae.smartvisit.activities.PictureContainerActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ViewedImageFragment extends BaseFragment {

    private ImageView imageView;
    private ProgressBar progressBar;
    private String viewedImageUrl;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewedImageUrl = getActivity().getIntent().getStringExtra("url");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture_view, container, false);

        imageView = (ImageView) view.findViewById(R.id.fragment_picture_image_view);
        progressBar = (ProgressBar) view.findViewById(R.id.fragment_picture_progress_bar);

        Picasso.with(getActivity()).load(viewedImageUrl).resize(300,300).centerCrop().placeholder(R.mipmap.ic_launcher).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                Toast.makeText(application, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
