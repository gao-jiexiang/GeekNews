package com.example.geeknews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseRlvAdapter;
import com.example.geeknews.bean.GankBean;

import java.util.List;

public class RlvGankAdapter extends BaseRlvAdapter {
    private List<GankBean.ResultsBean> list;
    private Context context;

    public RlvGankAdapter(List<GankBean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_cardview, null);
        return new GankItemVH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GankItemVH gankItemVH = (GankItemVH) holder;
        GankBean.ResultsBean bean = list.get(position);
 /*       sectionVH.mRecyTvHome.setText(bean.getTitle());
        Glide.with(context).load(bean.getImg()).into(sectionVH.mRecyImgHome);*/
        //gankItemVH.mContent.setText(bean.getDesc());
        gankItemVH.tv_title.setText(bean.getWho());
        gankItemVH.mTvTime.setText(bean.getPublishedAt());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(GankBean bean) {

        list.addAll(bean.getResults());
        notifyDataSetChanged();
    }



    class GankItemVH extends RecyclerView.ViewHolder {
        private ImageView iv_logo;
        private TextView tv_chapter_name;
        private TextView tv_title;
        private ImageView iv_time;
        private TextView mTvTime;

        public GankItemVH(@NonNull View itemView) {
            super(itemView);
            iv_logo = (ImageView) itemView.findViewById(R.id.iv_logo);
            tv_chapter_name = (TextView) itemView.findViewById(R.id.tv_chapter_name);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            iv_time = (ImageView) itemView.findViewById(R.id.iv_time);
            mTvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
