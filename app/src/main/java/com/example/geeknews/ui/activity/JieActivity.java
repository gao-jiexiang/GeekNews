package com.example.geeknews.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geeknews.R;
import com.example.geeknews.adapter.JieAdapter;
import com.example.geeknews.base.BaseActivity;
import com.example.geeknews.bean.V2_Base;
import com.example.geeknews.presenter.JiePresenter;
import com.example.geeknews.view.JieView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import qdx.stickyheaderdecoration.NormalDecoration;

public class JieActivity extends BaseActivity<JiePresenter> implements JieView {

    @BindView(R.id.back2)
    ImageView back2;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recy)
    RecyclerView recy;

    @Override
    protected JiePresenter initPresenter() {
        return new JiePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jie;
    }

    @Override
    protected void initView() {
        recy.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.getData(this);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
    }

    @Override
    public void setData(V2_Base bean) {
        ArrayList<String> title = bean.getTitle();
        NormalDecoration decoration=new NormalDecoration(){

            @Override
            public String getHeaderName(int i) {
                return title.get(i);
            }
        };
        recy.addItemDecoration(decoration);
        ArrayList<ArrayList<String>> item = bean.getItem();
        JieAdapter adapter = new JieAdapter(this, item);
        recy.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
