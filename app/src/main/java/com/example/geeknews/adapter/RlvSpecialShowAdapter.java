package com.example.geeknews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.example.geeknews.R;
import com.example.geeknews.base.BaseRlvAdapter;
import com.example.geeknews.bean.TabBean;

import java.util.ArrayList;
import java.util.Collections;

public class RlvSpecialShowAdapter extends BaseRlvAdapter {
    private ArrayList<TabBean> mTitles;

    public RlvSpecialShowAdapter(ArrayList<TabBean> titles) {
        mTitles = titles;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_special, parent,false);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        TabBean bean = mTitles.get(position);
        vh.mTvTitle.setText(bean.title);
        vh.mSc.setChecked(bean.isSelect);

        //swichcompat的状态切换监听
        vh.mSc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //用户修改的状态需要保存下来
                bean.isSelect = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    class VH extends RecyclerView.ViewHolder{

        private final TextView mTvTitle;
        private final SwitchCompat mSc;

        public VH(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mSc = itemView.findViewById(R.id.sc);
        }
    }

    public void onItemDelete(int position){
        mTitles.remove(position);
        notifyDataSetChanged();
    }

    //条目交换位置的回调方法
    public void onItemMove(int startPosition,int toPosition){
        //工具类交换集合的两个位置的数据
        Collections.swap(mTitles,startPosition,toPosition);
        //局部刷新
        notifyItemMoved(startPosition,toPosition);
    }

}
