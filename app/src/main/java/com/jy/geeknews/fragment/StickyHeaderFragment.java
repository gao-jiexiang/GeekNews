package com.jy.geeknews.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.geeknews.R;
import com.jy.geeknews.adapter.RlvCarAdapter;
import com.jy.geeknews.base.BaseFragment;
import com.jy.geeknews.bean.Car;
import com.jy.geeknews.presenter.EmptyPresenter;
import com.jy.geeknews.util.ToastUtil;
import com.jy.geeknews.view.EmptyView;

import java.util.ArrayList;

import butterknife.BindView;
import qdx.stickyheaderdecoration.NormalDecoration;

public class StickyHeaderFragment extends BaseFragment<EmptyPresenter> implements EmptyView {
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private ArrayList<Car> mCars;
    private RlvCarAdapter mAdapter;
    private NormalDecoration mNormalDecoration;

    @Override
    protected int getLayout() {
        return R.layout.fragment_stickyheader;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new EmptyPresenter();
    }

    @Override
    protected void initView() {
        mRlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRlv.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        initCars();
        mAdapter = new RlvCarAdapter(mCars);
        //创建粘性头部分割线
        mNormalDecoration = new NormalDecoration() {

            @Override
            public String getHeaderName(int i) {
                return mCars.get(i).getHeaderName();
            }
        };
        //自定义粘性头部分割线
        mNormalDecoration.setOnDecorationHeadDraw(new NormalDecoration.OnDecorationHeadDraw() {
            @Override
            public View getHeaderView(int i) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.sticky_header_layout, null);
                TextView headerName= view.findViewById(R.id.txt_headername);
                headerName.setText(mCars.get(i).getHeaderName());

                return view;
            }
        });
        //头布局的点击事件
        mNormalDecoration.setOnHeaderClickListener(new NormalDecoration.OnHeaderClickListener() {
            @Override
            public void headerClick(int i) {
                ToastUtil.showShort(mCars.get(i).getHeaderName());
                //实现跳转等业务

            }
        });
        mRlv.addItemDecoration(mNormalDecoration);//设置分割线
        mRlv.setAdapter(mAdapter);

    }

    private void initCars() {
        mCars = new ArrayList<>();
        mCars.add(new Car("奥迪",  "A"));
        mCars.add(new Car("阿尔法罗密欧",  "A"));
        mCars.add(new Car("阿斯顿马丁",  "A"));
        mCars.add(new Car("ALPINA",  "A"));
        mCars.add(new Car("安凯客车",  "A"));


        mCars.add(new Car("本田", "B"));
        mCars.add(new Car("别克", "B"));
        mCars.add(new Car("奔驰",  "B"));
        mCars.add(new Car("宝马", "B"));
        mCars.add(new Car("保时捷",  "B"));
        mCars.add(new Car("比亚迪", "B"));
        mCars.add(new Car("北京", "B"));
        mCars.add(new Car("宾利",  "B"));
        mCars.add(new Car("巴博斯",  "B"));
        mCars.add(new Car("布加迪威龙", "B"));

        mCars.add(new Car("长安", "C"));
        mCars.add(new Car("长城",  "C"));

        mCars.add(new Car("大众", "D"));
        mCars.add(new Car("东南",  "D"));
        mCars.add(new Car("东风", "D"));
        mCars.add(new Car("DS", "D"));
        mCars.add(new Car("道奇", "D"));
        mCars.add(new Car("东风小康", "D"));
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
}
