package com.jy.geeknews.fragment;

import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.geeknews.R;
import com.jy.geeknews.base.BaseFragment;
import com.jy.geeknews.presenter.EmptyPresenter;
import com.jy.geeknews.view.EmptyView;

import butterknife.BindView;

public class WeChatMainFragment extends BaseFragment<EmptyPresenter> implements EmptyView {
    @BindView(R.id.txt_title)
    TextView mTitle;
    @BindView(R.id.rlv)
    RecyclerView mRlv;

    private String searchKey;

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;

        mTitle.setText(this.searchKey);
        //调用P的方法进行网络请求,调用P的微信精品搜索接口

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initPresenter() {
       mPresenter =  new EmptyPresenter();
    }

    @Override
    protected void initView() {
        //初始化列表
        mRlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //接着自己完成

    }

    @Override
    protected void initData() {
        //进入微信fragment，进行网络请求微信精选信息，列表展示。。。自己完成

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showToast(String str) {

    }
}
