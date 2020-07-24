package com.jy.geeknews;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class AppbarActivity extends AppCompatActivity {

    private static final String TAG = "GankDetailFragment";
    @BindView(R.id.iv_blur)
    ImageView mIvBlur;
    @BindView(R.id.iv_origin)
    ImageView mIvOrigin;
    @BindView(R.id.ctl)
    CollapsingToolbarLayout mCtl;
    @BindView(R.id.appBar)
    AppBarLayout mAppBar;
    @BindView(R.id.cl)
    CoordinatorLayout mCl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_appbar);
        setContentView(R.layout.activity_appbar1);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //        Glide.with(getActivity()).load(R.mipmap.ph11).into(mIvOrigin);
        //mIvOrigin.setVisibility(View.GONE);
        //高斯模糊 BlurTransformation 高斯模糊图形变换的类
        RequestOptions options = new RequestOptions().transforms(new BlurTransformation());
        Glide.with(this)
                .load(R.mipmap.ph11)
                .apply(options)
                .into(mIvBlur);

        //实现页面朝上滚动的时候 图片变模糊,朝下滑动变清晰
        //实际是使用两张图片,模糊的在清晰的下方,滑动时随着滑动的距离改变
        //清晰图片的透明度,使模糊的图片可见
        //AppBarLayout的偏移监听
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(TAG, "onOffsetChanged: "+verticalOffset+",appHeight:"+mAppBar.getHeight());
                //appbar 200dp verticalOffset 范围是0到-600

                //int 类型的那个api过时了,里面的值是0到255
                //float 值的范围0-1 1.0完全不透明,0完全透明
                //页面朝上 1.0 ---> 0.0   0 ---> -600
                //页面朝下 0.0 ---> 1.0   -600 ---> 0
                //一上来 verticalOffset是0 ,alpha是 1,我们需要 alpha加速由1.0-->0.0
                //float alpha = 1+ (verticalOffset * 1.0f/mAppBar.getHeight());//范围 0-1
                float alpha = 1+ (verticalOffset * 2.0f/mAppBar.getHeight());// 范围 1到-1
                if (alpha>0)
                    mIvOrigin.setAlpha(alpha);


            }
        });
    }
}
