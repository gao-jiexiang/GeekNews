package com.example.geeknews.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.geeknews.R;
import com.example.geeknews.adapter.RlvGankAdapter;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.base.Constants;
import com.example.geeknews.bean.GankBean;
import com.example.geeknews.presenter.GankItemPresenter;
import com.example.geeknews.util.LogUtil;
import com.example.geeknews.view.GankItemView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.BlurTransformation;


public class GankItemFragment extends BaseFragment<GankItemPresenter> implements GankItemView {


    @BindView(R.id.tv_blur)//高斯模糊
    ImageView mTvBlur;
    @BindView(R.id.tv_origin)//原图
    ImageView mTvOrigin;
    @BindView(R.id.ctl)
    CollapsingToolbarLayout mCtl;
    @BindView(R.id.appBar)
    AppBarLayout mAppBar;
    @BindView(R.id.cl)
    CoordinatorLayout mCl;
    @BindView(R.id.recy)
    RecyclerView recy;
    private ArrayList<GankBean.ResultsBean> list;
    private RlvGankAdapter adapter;
    private String tech;

    public static BaseFragment newInstance(String tech) {
        GankItemFragment fragment = new GankItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA, tech);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initListener() {
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //verticalOffset, 0到-600
                //两张图片,原图在上,高斯模糊图片在下,在滑动的过程中通过改变原图的透明度,实现图片渐渐模糊/清晰的效果
                //0 到 1,0完全透明,1是完全不透明
                int height = mAppBar.getHeight();
                LogUtil.print("height:" + height);
                //一上来 verticalOffset 0,图片显示原图,透明度为1,
                // verticalOffset 当滑动到-height(-600),透明度为0
                float alpha = 1 + (verticalOffset * 2.0f / height);
                if (alpha >= 0) {
                    mTvOrigin.setAlpha(alpha);
                }
            }
        });

    }

    @Override
    protected void initView(View inflate) {
        Bundle arguments = getArguments();
        //网络请求的参数
        tech = arguments.getString(Constants.DATA);


        //高斯模糊
        RequestOptions options = new RequestOptions()
                .transform(new BlurTransformation());
        Glide.with(getContext()).load(R.drawable.test03).apply(options).into(mTvBlur);
        Glide.with(getContext()).load(R.drawable.test03).into(mTvOrigin);
        initRecy();
    }

    private void initRecy() {
        list = new ArrayList<>();
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RlvGankAdapter(list, getActivity());
        recy.setAdapter(adapter);
    }

    @Override
    protected GankItemPresenter initPresenter() {
        return new GankItemPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank_item;
    }

    @Override
    public void setData(GankBean bean) {
        adapter.setData(bean);
    }

    @Override
    protected void initData() {
        mPresenter.getData(tech);
    }
}
