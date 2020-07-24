package com.example.geeknews.ui.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geeknews.R;
import com.example.geeknews.adapter.RlvAdapter;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.bean.Car;
import com.example.geeknews.presenter.CollectPresenter;
import com.example.geeknews.ui.activity.MainActivity;
import com.example.geeknews.view.CollectView;

import java.util.ArrayList;

import butterknife.BindView;
import qdx.stickyheaderdecoration.NormalDecoration;


public class CollectFragment extends BaseFragment<CollectPresenter> implements CollectView {

    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private ArrayList<Car> mCars;

    public static BaseFragment newInstance() {
        CollectFragment fragment = new CollectFragment();

        return fragment;
    }

    @Override
    protected CollectPresenter initPresenter() {
        return new CollectPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public void initData() {

    }

    @Override
    protected void initView(View inflate) {

        mCars = new ArrayList<>();
        mCars.add(new Car("奥迪", "A"));
        mCars.add(new Car("阿尔法罗密欧", "A"));
        mCars.add(new Car("阿斯顿马丁", "A"));
        mCars.add(new Car("ALPINA", "A"));
        mCars.add(new Car("安凯客车", "A"));
        mCars.add(new Car("本田", "B"));
        mCars.add(new Car("别克", "B"));
        mCars.add(new Car("奔驰", "B"));
        mCars.add(new Car("宝马", "B"));
        mCars.add(new Car("保时捷", "B"));
        mCars.add(new Car("比亚迪", "B"));
        mCars.add(new Car("北京", "B"));
        mCars.add(new Car("宾利", "B"));
        mCars.add(new Car("巴博斯", "B"));
        mCars.add(new Car("布加迪威龙", "B"));
        mCars.add(new Car("长安", "C"));
        mCars.add(new Car("长城", "C"));
        mCars.add(new Car("大众", "D"));
        mCars.add(new Car("东南", "D"));
        mCars.add(new Car("东风", "D"));
        mCars.add(new Car("DS", "D"));
        mCars.add(new Car("道奇", "D"));
        mCars.add(new Car("东风小康", "D"));

        mRlv = (RecyclerView)inflate.findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        RlvAdapter rlvAdapter = new RlvAdapter(mCars);
        //返回头布局的内容
        final NormalDecoration decoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int i) {
                return mCars.get(i).header;
            }
        };
        //自定义头布局,可不设置
       /* decoration.setOnDecorationHeadDraw(new NormalDecoration.OnDecorationHeadDraw() {
            @Override
            public View getHeaderView(final int i) {
                View inflate = inflater.inflate(R.layout.item_header, null);
                TextView tv = inflate.findViewById(R.id.tv);
                tv.setText(mCars.get(i).headerName);
                return inflate;
            }
        });*/
        mRlv.addItemDecoration(decoration);
        //头布局的点击事件
        decoration.setOnHeaderClickListener(new NormalDecoration.OnHeaderClickListener() {
            @Override
            public void headerClick(int i) {
                showToast(mCars.get(i).header);
            }
        });
        mRlv.setAdapter(rlvAdapter);
    }
}
