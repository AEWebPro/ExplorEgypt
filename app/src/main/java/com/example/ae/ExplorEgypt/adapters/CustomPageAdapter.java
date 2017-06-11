package com.example.ae.ExplorEgypt.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.ae.ExplorEgypt.fragments.BaseFragment;

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
