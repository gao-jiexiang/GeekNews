package com.jy.geeknews.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jy.geeknews.base.BaseApp;
import com.jy.geeknews.base.BaseFragment;

import java.util.ArrayList;

public class VpFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Integer> mTabTitle;
    private ArrayList<BaseFragment> mFragments;

    public VpFragmentAdapter(@NonNull FragmentManager fm, ArrayList<Integer> tabTitle,
                             ArrayList<BaseFragment> fragments) {
        super(fm);
        mTabTitle = tabTitle;
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
        return BaseApp.getRes().getString(mTabTitle.get(position));//加载字符串资源，设置为tab页名字
    }
}
