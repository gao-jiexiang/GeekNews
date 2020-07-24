package com.example.geeknews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.bean.SectionBean;

import java.util.ArrayList;
import java.util.List;

public class RlvSectionAdapter extends RecyclerView.Adapter<RlvSectionAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<SectionBean.DataBean> sectionList;

    public RlvSectionAdapter(Context mContext, ArrayList<SectionBean.DataBean> sectionList) {
        this.mContext = mContext;
        this.sectionList = sectionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_section, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SectionBean.DataBean dataBean = sectionList.get(position);
        holder.se_text.setText(dataBean.getName());
        Glide.with(mContext).load(dataBean.getThumbnail()).into(holder.se_img);
    }

    public void setData(SectionBean bean) {
        List<SectionBean.DataBean> data = bean.getData();
        sectionList.clear();
        if (data!=null && data.size()>0){
            sectionList.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView se_img;
        private TextView se_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            se_img= itemView.findViewById(R.id.se_img);
            se_text= itemView.findViewById(R.id.se_text);
        }
    }
}
