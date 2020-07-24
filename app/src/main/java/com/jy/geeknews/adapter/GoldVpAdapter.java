package com.jy.geeknews.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.jy.geeknews.bean.GoldChannelBean;
import com.jy.geeknews.fragment.GoldVpPageFragment;

import java.util.ArrayList;

public class GoldVpAdapter extends FragmentStatePagerAdapter {
    private ArrayList<GoldChannelBean> mGoldChannelChoosed;
    private ArrayList<GoldVpPageFragment> mFragments;

    public GoldVpAdapter(@NonNull FragmentManager fm, ArrayList<GoldChannelBean> goldChannelChoosed,
                         ArrayList<GoldVpPageFragment> fragments) {
        super(fm);
        mGoldChannelChoosed = goldChannelChoosed;
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
        return mGoldChannelChoosed.get(position).getName();
    }
}
