package com.jy.geeknews.fragment;

import android.content.Intent;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jy.geeknews.GoldChannelManagerActivity;
import com.jy.geeknews.R;
import com.jy.geeknews.adapter.GoldVpAdapter;
import com.jy.geeknews.base.BaseFragment;
import com.jy.geeknews.base.Constants;
import com.jy.geeknews.bean.GoldChannelBean;
import com.jy.geeknews.presenter.EmptyPresenter;
import com.jy.geeknews.view.EmptyView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class GoldMainFragment extends BaseFragment<EmptyPresenter> implements EmptyView {
    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.img)
    ImageView mImg;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<GoldChannelBean> mBeans;
    private ArrayList<GoldChannelBean> mGoldChannelChoosed;
    private ArrayList<GoldVpPageFragment> mFragments;
    private GoldVpAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_gold_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new EmptyPresenter();
    }

    @Override
    protected void initView() {
        //创建频道数据源，替代网络请求
        mBeans = new ArrayList<>();
        mBeans.add(new GoldChannelBean("Android",true));
        mBeans.add(new GoldChannelBean("IOS",true));
        mBeans.add(new GoldChannelBean("Python",true));
        mBeans.add(new GoldChannelBean("大数据",true));
        mBeans.add(new GoldChannelBean("前端",true));
        mBeans.add(new GoldChannelBean("后端",true));
        mBeans.add(new GoldChannelBean("人工智能",true));
        mBeans.add(new GoldChannelBean("JAVA",true));
        mBeans.add(new GoldChannelBean("电影",true));

        //创建选择的频道的集合
        mGoldChannelChoosed = new ArrayList<>();
        mFragments = new ArrayList<>();
        initTabAndVp();
    }

    private void initTabAndVp() {
        //清空旧数据
        mGoldChannelChoosed.clear();
        mFragments.clear();
        //创建新数据
        for (int i = 0; i <mBeans.size() ; i++) {
            GoldChannelBean bean = mBeans.get(i);
            if(bean.isChoosed() == true){
                mGoldChannelChoosed.add(bean);
                //创建vp的page页fragment
                mFragments.add(GoldVpPageFragment.getInstance(bean.getName()));
            }
        }
        //为Vp绑定适配器
        mAdapter = new GoldVpAdapter(getChildFragmentManager(), mGoldChannelChoosed, mFragments);
        mVp.setAdapter(mAdapter);
        //结合
        mTab.setupWithViewPager(mVp);
    }

    @OnClick(R.id.img)//点击图片，跳转到栏目设置页面
    public void jumpToManager(){
        Intent it = new Intent(getActivity(), GoldChannelManagerActivity.class);
        it.putExtra(Constants.DATA,mBeans);
        startActivityForResult(it,100);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showToast(String str) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == getActivity().RESULT_OK){
            //得到设置后的频道数据
            mBeans = (ArrayList<GoldChannelBean>) data.getSerializableExtra(Constants.DATA);
            initTabAndVp();
        }
    }
}
