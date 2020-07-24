package com.example.geeknews.ui.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.base.Constants;
import com.example.geeknews.presenter.AboutPresenter;
import com.example.geeknews.view.AboutView;

import butterknife.BindView;

public class AboutFragment extends BaseFragment<AboutPresenter> implements AboutView {

    private static final String TAG = "AboutFragment";
    @BindView(R.id.tv)
    TextView mTv;
    private String mTitle;

    public static AboutFragment newInstance(String title) {
        AboutFragment fragment = new AboutFragment();

        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA, title);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected AboutPresenter initPresenter() {
        return new AboutPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initView(View inflate) {
        Bundle arguments = getArguments();
        mTitle = arguments.getString(Constants.DATA);
        mTv.setText(mTitle);

        Log.d(TAG, "initView: "+ mTitle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: "+mTitle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: "+mTitle);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: "+mTitle);
    }
}
