package com.example.ae.smartvisit.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.example.ae.smartvisit.fragments.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ahmed.E on 6/2/2017.
 */

public class CustomPageAdapter extends FragmentStatePagerAdapter {

    public final List<BaseFragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public CustomPageAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public void addFragment(BaseFragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

}
