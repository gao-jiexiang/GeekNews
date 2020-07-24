package com.example.geeknews.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.bean.TabBean;

import java.util.ArrayList;

/**
 * 使用FragmentPagerAdapter ,碎片会不匹配,因为使用它FragmentPagerAdapter,FragmentManager里面会有fragment的缓存,下次会直接使用,但是又使用的不对
 * FragmentPagerAdapter:onPause->onStop->onDestroyView
 * FragmentStatePagerAdapter:销毁的生命周期:onPause->onStop->onDestroyView->onDestroy->onDetach
 */
public class VpGoldAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<TabBean> mTitles;
    private final ArrayList<BaseFragment> mFragments;
    private final ArrayList<String> mSelectTitles;

    public VpGoldAdapter(@NonNull FragmentManager fm, ArrayList<TabBean> titles,
                         ArrayList<BaseFragment> fragments) {
        super(fm);
        //因为mTitles中不管选中与否,都是有8个title,但是fragment是有选中的
        mTitles = titles;
        mSelectTitles = new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++) {
            TabBean tabBean = mTitles.get(i);
            if (tabBean.isSelect){
                mSelectTitles.add(tabBean.title);
            }
        }
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
        return mSelectTitles.get(position);
    }
}
