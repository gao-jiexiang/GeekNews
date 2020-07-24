package com.jy.geeknews.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.jy.geeknews.base.BaseFragment;

import java.util.ArrayList;

public class GankVpFragmentAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String> mTabs;
    private ArrayList<BaseFragment> mFragments;

    public GankVpFragmentAdapter(@NonNull FragmentManager fm, ArrayList<String> tabs, ArrayList<BaseFragment> fragments) {
        super(fm);
        mTabs = tabs;
        mFragments = fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position);
    }
}
