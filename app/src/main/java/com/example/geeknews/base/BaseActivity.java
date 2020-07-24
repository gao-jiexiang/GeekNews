package com.example.geeknews.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.geeknews.util.ToastUtil;
import com.example.geeknews.widget.LoadingDialog;

import butterknife.ButterKnife;

////父类规定泛型,子类继承的时候提供,父类可以帮子类保存对应类型的p层对象
public abstract class BaseActivity<P extends BasePresenter>
        extends AppCompatActivity implements BaseView {

    public P mPresenter;
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局 R.layout.activity_main;
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        //new MainPresenter();
        mPresenter = initPresenter();
        if (mPresenter != null) {
            //传递v层对象不再使用构造,使用方法传递,v和p层在父类中统一相互持有对方对象
            mPresenter.bindView(this);
        }
        initView();
        initData();
        initListener();
    }

    //由子类提供对应的P层对象
    protected abstract P initPresenter();

    protected void initListener() {

    }

    protected void initData() {

    }

    protected void initView() {
    }

    ;

    //专门提供布局id的,必须由子类复写
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //通知m层将网络请求取消掉
        mPresenter.onDestory();
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
        }
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()){
            mLoadingDialog.hide();
        }
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToastShort(msg);
    }
}
