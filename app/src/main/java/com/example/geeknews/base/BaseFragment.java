package com.example.geeknews.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



import com.example.geeknews.util.ToastUtil;
import com.example.geeknews.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;
//ak :app key

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView{

    public P mPresenter;
    private LoadingDialog mLoadingDialog;
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), null);
        mUnbinder = ButterKnife.bind(this, inflate);
        mPresenter = initPresenter();
        if (mPresenter != null) {
            //传递v层对象不再使用构造,使用方法传递,v和p层在父类中统一相互持有对方对象
            mPresenter.bindView(this);
        }
        initView(inflate);
        initData();
        initListener();
        return inflate;
    }


    //由子类提供对应的P层对象
    protected abstract P initPresenter();

    protected void initListener() {

    }

    protected void initData() {

    }

    protected void initView(View inflate) {
    }


    //专门提供布局id的,必须由子类复写
    protected abstract int getLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        //通知m层将网络请求取消掉
        mPresenter.onDestory();
        mUnbinder.unbind();
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(getContext());
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
