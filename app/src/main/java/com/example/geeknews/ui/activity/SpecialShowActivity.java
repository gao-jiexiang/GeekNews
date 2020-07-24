package com.example.geeknews.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geeknews.R;
import com.example.geeknews.adapter.RlvSpecialShowAdapter;
import com.example.geeknews.base.Constants;
import com.example.geeknews.bean.TabBean;
import com.example.geeknews.widget.MyCallBack;

import java.util.ArrayList;

public class SpecialShowActivity extends AppCompatActivity {

    private ArrayList<TabBean> mTitles;
    private RecyclerView mRlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special_show);
        mTitles = (ArrayList<TabBean>) getIntent().getSerializableExtra(Constants.DATA);
        initView();



    }

    private void initView() {
        mRlv = (RecyclerView) findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(this));

        RlvSpecialShowAdapter adapter = new RlvSpecialShowAdapter(mTitles);
        mRlv.setAdapter(adapter);


        MyCallBack callBack = new MyCallBack(adapter);
        //callBack.setSwipeEnable(false);
        //帮助我们监听子条目状态改变的类
        ItemTouchHelper helper = new ItemTouchHelper(callBack);
        //关联一个recyclerview
        helper.attachToRecyclerView(mRlv);
    }

    //点返回键会触发的方法
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(Constants.DATA,mTitles);
        setResult(Activity.RESULT_OK,intent);
        super.onBackPressed();
    }
}
