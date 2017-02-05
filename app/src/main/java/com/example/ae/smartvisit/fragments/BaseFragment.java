package com.example.ae.smartvisit.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.ae.smartvisit.infrastructure.MyApplication;

public class BaseFragment extends Fragment {
    protected MyApplication application;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MyApplication) getActivity().getApplication();

    }
}
