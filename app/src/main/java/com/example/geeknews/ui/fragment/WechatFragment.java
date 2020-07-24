package com.example.geeknews.ui.fragment;


import com.example.geeknews.R;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.presenter.WechatPresenter;
import com.example.geeknews.view.WechatView;

public class WechatFragment extends BaseFragment<WechatPresenter> implements WechatView {

    public static WechatFragment newInstance(){
        WechatFragment fragment = new WechatFragment();

        return fragment;
    }

    @Override
    protected WechatPresenter initPresenter() {
        return new WechatPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }
}
