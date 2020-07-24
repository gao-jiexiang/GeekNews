package com.example.geeknews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.base.Constants;
import com.example.geeknews.bean.DailyNewsBean;
import com.example.geeknews.bean.NewsDetailBean;
import com.example.geeknews.presenter.NewsDetailPresenter;
import com.example.geeknews.ui.fragment.CollectFragment;
import com.example.geeknews.util.LogUtil;
import com.example.geeknews.view.NewsDetailView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends BaseActivity<NewsDetailPresenter> implements NewsDetailView {

    private static final String TAG = "NewsDetail";
    @BindView(R.id.html_text)
    HtmlTextView htmlText;
    @BindView(R.id.appBar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.ctl)
    CollapsingToolbarLayout ctl;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private DailyNewsBean.StoriesBean mBean;

    @Override
    protected NewsDetailPresenter initPresenter() {
        return new NewsDetailPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initView() {
        toolBar.setTitle("");
        setSupportActionBar(toolBar);

        mBean = (DailyNewsBean.StoriesBean) getIntent().getSerializableExtra(Constants.DATA);
        LogUtil.print(mBean.getTitle() + "," + mBean.getId());

        Glide.with(this).load(mBean.getImages().get(0)).into(iv);

        //设置标题
        ctl.setTitle(mBean.getTitle());
        //扩张时候的title颜色
        ctl.setExpandedTitleColor(getResources().getColor(R.color.white));
        //收缩后显示title的颜色
        ctl.setCollapsedTitleTextColor(getResources().getColor(R.color.white));

        //点击收藏
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getDetail(mBean.getId());
    }

    @Override
    public void setData(NewsDetailBean bean) {
        htmlText.setHtml(bean.getBody(),
                new HtmlHttpImageGetter(htmlText));
    }

    @Override
    protected void initListener() {
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //verticalOffset 垂直方向的偏移量 1dp = 3px 200dp = 600px
                //0 到 -600px
                Log.d(TAG, "onOffsetChanged: " + verticalOffset);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
