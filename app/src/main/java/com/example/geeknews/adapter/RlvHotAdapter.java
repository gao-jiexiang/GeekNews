package com.example.geeknews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.geeknews.R;
import com.example.geeknews.base.BaseRlvAdapter;
import com.example.geeknews.bean.HotBean;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvHotAdapter extends RecyclerView.Adapter<RlvHotAdapter.ViewHolder> {
    private ArrayList<HotBean.RecentBean> hotList;
    private Context context;

    public RlvHotAdapter(ArrayList<HotBean.RecentBean> hotList, Context context) {
        this.hotList = hotList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hot, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HotBean.RecentBean recentBean = hotList.get(position);
        holder.tx_hot.setText(recentBean.getTitle());
        Glide.with(context).load(recentBean.getThumbnail()).into(holder.img_hot);

    }



    public void setData(HotBean bean) {
        List<HotBean.RecentBean> recent = bean.getRecent();
        hotList.clear();
        if (recent!=null && recent.size()>0){
            hotList.addAll(recent);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return hotList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_hot;
        private TextView tx_hot;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_hot=itemView.findViewById(R.id.img_hot);
            tx_hot=itemView.findViewById(R.id.tx_hot);
        }
    }
}
