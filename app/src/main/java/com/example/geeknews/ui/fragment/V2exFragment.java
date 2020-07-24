package com.example.geeknews.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.geeknews.R;
import com.example.geeknews.adapter.V2_vp_Adapter;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.bean.V2_Tab;
import com.example.geeknews.bean.V2exTabBean;
import com.example.geeknews.presenter.V2exPresenter;
import com.example.geeknews.ui.activity.JieActivity;
import com.example.geeknews.util.LogUtil;
import com.example.geeknews.view.V2exView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class V2exFragment extends BaseFragment<V2exPresenter> implements V2exView {
    Unbinder unbinder;
    @BindView(R.id.tabLayout)
    TabLayout mTab;
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.linear)
    LinearLayout linear;
    private String mUrl = "https://www.v2ex.com/";
    private ArrayList<V2_Tab> tabs;

    public static BaseFragment newInstance() {
        Bundle bundle = new Bundle();
        V2exFragment fragment = new V2exFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void initListener() {
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tabs.size() > 0) {
                    Intent intent = new Intent(getActivity(), JieActivity.class);
                    startActivity(intent);
                } else {
                    Snackbar.make(linear, "数据还在路上", Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    @Override
    protected void initView(View inflate) {
        initTab();
    }

    private void initTab() {
        tabs = new ArrayList<>();
        mPresenter.getData(getActivity());
    }

    @Override
    protected V2exPresenter initPresenter() {
        return new V2exPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2ex;
    }

    private void initFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabs.size(); i++) {
            if (tabs.get(i).isSelect) {
                String url = tabs.get(i).getUrl();
                Log.i("Tab栏数据", "initFragment: " + tabs.get(i).getName());
                Log.i("123", "initFragment: 在这里");
                fragments.add(V2_item_fragment.newInstance(url));
            }
        }
        V2_vp_Adapter adapter = new V2_vp_Adapter(getChildFragmentManager(), tabs, fragments);
        mVp.setAdapter(adapter);
        mTab.setupWithViewPager(mVp);
    }

    @Override
    protected void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setTab(ArrayList<V2_Tab> bean) {
        if (bean!=null && bean.size()>0){
            tabs.addAll(bean);
            initFragment();
        }
    }
}
