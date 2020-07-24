package com.jy.geeknews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.geeknews.R;
import com.jy.geeknews.TouchCallBack;
import com.jy.geeknews.bean.GoldChannelBean;
import com.jy.geeknews.util.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;

public class RlvGoldChannelAdapter extends RecyclerView.Adapter<RlvGoldChannelAdapter.ViewHolder>
        implements TouchCallBack {
    private ArrayList<GoldChannelBean> mBeans;
    public RlvGoldChannelAdapter(ArrayList<GoldChannelBean> beans) {
        mBeans = beans;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rlv_gold_channel_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GoldChannelBean bean = mBeans.get(position);
        holder.name.setText(bean.getName());
        //添加sc的选择改变监听：选中和不选中，，一定要先设置监听，再赋值，赋值时监听正好起作用
        holder.sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                ToastUtil.showShort(""+isChecked);
                bean.setChoosed(isChecked);//把选择的结果设置到本数据对象的身上记录下载
            }
        });
        holder.sc.setChecked(bean.isChoosed());//设置sc的选中状态
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mOnItemClickLis.onItemClick(position);
//            }
//        });
//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                mOnItemClickLis.onItemLongClick(position);
//                return false;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换位置
        Collections.swap(mBeans,fromPosition,toPosition);
        //局部刷新(移动)
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDelete(int position) {
//删除数据
        mBeans.remove(position);
        //局部刷新(移除)
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        SwitchCompat sc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
            sc = itemView.findViewById(R.id.sc);
        }
    }

    private OnItemClickLis mOnItemClickLis;

    public void setOnItemClickLis(OnItemClickLis onItemClickLis) {
        mOnItemClickLis = onItemClickLis;
    }

    public interface OnItemClickLis{
        void onItemClick(int position);
        void onItemLongClick(int position);
    }


}
