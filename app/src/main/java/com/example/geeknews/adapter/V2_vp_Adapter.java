package com.example.geeknews.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.geeknews.bean.V2_Tab;

import java.util.ArrayList;

public class V2_vp_Adapter extends FragmentStatePagerAdapter {
    public ArrayList<V2_Tab> tabBeans;
    public ArrayList<Fragment> fragments;
    private ArrayList<String> data=new ArrayList<>();
    public V2_vp_Adapter(FragmentManager fm, ArrayList<V2_Tab> tabBeans, ArrayList<Fragment> fragments) {
        super(fm);
        this.tabBeans = tabBeans;
        this.fragments = fragments;
        for (int i = 0; i < tabBeans.size(); i++) {
            if(tabBeans.get(i).isSelect){
                data.add(tabBeans.get(i).getName());
            }
        }
    }


    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return data.get(position);
    }
}
