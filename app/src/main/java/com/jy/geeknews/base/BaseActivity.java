package com.jy.geeknews.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{
    public P mPresenter;//所有的V都要有一个P，P必须是BasePresenter的后代

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("111", "onCreate-------------: ");
        setContentView(getLayout());//设置布局 通过getLayout()得到对应的布局，具体由真正的Activity提供
        ButterKnife.bind(this);//每个页面都要有绑定view
        initPresenter();//获得Presenter，具体页面创建具体的P对象
        if(mPresenter != null){
            mPresenter.bindView(this);//绑定View，P和V建立关系，P中的mView赋值
        }
        initView();
        initData();
        initListener();//给组件添加监听
    }

    protected abstract int getLayout();

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
        mPresenter = null;
    }
}
