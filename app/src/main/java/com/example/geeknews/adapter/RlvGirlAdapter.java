package com.example.geeknews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.bean.FuLiBean;

import java.util.ArrayList;
import java.util.List;

public class RlvGirlAdapter extends RecyclerView.Adapter<RlvGirlAdapter.ViewHolder> {
    private Context mContext;
    private List<FuLiBean.ResultsBean> grilList;

    public RlvGirlAdapter(Context mContext, List<FuLiBean.ResultsBean> grilList) {
        this.mContext = mContext;
        this.grilList = grilList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_gril, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FuLiBean.ResultsBean resultsBean = grilList.get(position);
        Glide.with(mContext).load(resultsBean.getUrl()).into(holder.tv_img);

    }

    @Override
    public int getItemCount() {
        return grilList.size();
    }

    public void setData(FuLiBean bean) {
        grilList.addAll(bean.getResults());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView tv_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_img=  itemView.findViewById(R.id.tv_img);
        }
    }
}
