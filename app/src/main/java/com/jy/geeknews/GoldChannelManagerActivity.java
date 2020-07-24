package com.jy.geeknews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.geeknews.adapter.RlvGoldChannelAdapter;
import com.jy.geeknews.base.Constants;
import com.jy.geeknews.bean.GoldChannelBean;
import com.jy.geeknews.util.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoldChannelManagerActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private ArrayList<GoldChannelBean> mBeans;
    private RlvGoldChannelAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gold_channel_manager);
        ButterKnife.bind(this);

        initToolbar();
        initRlv();
    }

    private void initRlv() {
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        mRlv.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        //得到数据
        mBeans = (ArrayList<GoldChannelBean>) getIntent().getSerializableExtra(Constants.DATA);
        mAdapter = new RlvGoldChannelAdapter(mBeans);
        mRlv.setAdapter(mAdapter);
//        mAdapter.setOnItemClickLis(new RlvGoldChannelAdapter.OnItemClickLis() {
//            @Override
//            public void onItemClick(int position) {
////                update(position);//修改
//                //删除
////                delete(position);
//                //添加一条
//                add(position);//点击添加
//            }
//
//            @Override
//            public void onItemLongClick(int position) {
//                delete(position);//长安删除
//            }
//        });

        //
        SimpleItemTouchHelperCallBack callBack = new SimpleItemTouchHelperCallBack(mAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callBack);
        helper.attachToRecyclerView(mRlv);
    }

    private void add(int position) {
        GoldChannelBean bean = new GoldChannelBean("1907B", true);
        mBeans.add(position,bean);//在positio的位置插入一条，插队，后面的数据都会后移一位
        mAdapter.notifyItemInserted(position);//局部刷新
        mAdapter.notifyItemRangeChanged(position,mBeans.size()-position);//告诉适配器,
        // 某个范围内的数据发送了改变是从我们删除位置开始,以下全部数据都要通知,系统会更新新的索引,
        // 这样可以避免索引混乱
    }

    private void delete(int position) {
        mBeans.remove(position);//删除集合中的数据
        mAdapter.notifyItemRemoved(position);//局部刷新
        mAdapter.notifyItemRangeChanged(position,mBeans.size()-position);//告诉适配器,
        // 某个范围内的数据发送了改变是从我们删除位置开始,以下全部数据都要通知,系统会更新新的索引,
        // 这样可以避免索引混乱
    }

    public void update(int position){
        //修改操作
        GoldChannelBean bean = mBeans.get(position);
        bean.setName(bean.getName()+"--新的");
        mAdapter.notifyItemChanged(position);
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_action_navigation_arrow_back);//改变toolbar的后退图标
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击返回
                goBack();
            }
        });

    }

    private void goBack() {
        Intent it = new Intent();
        it.putExtra(Constants.DATA,mBeans);
        setResult(RESULT_OK,it);
        finish();
    }
}
