package com.example.geeknews.adapter;

import android.content.Context;

import android.util.Log;
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
import com.example.geeknews.bean.V2_ItemBean;


import java.util.ArrayList;

public class V2_Item_Adapter extends BaseRlvAdapter {
    private Context context;
    private ArrayList<V2_ItemBean> data=new ArrayList<>();

    public V2_Item_Adapter(Context context) {
        this.context = context;
    }
public void setData(ArrayList<V2_ItemBean> data){
        if(data!=null&&data.size()>0){
            this.data.addAll(data);
            notifyDataSetChanged();
        }
}
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.v2_rec, viewGroup, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1= (ViewHolder) viewHolder;
        V2_ItemBean bean = data.get(i);
        Glide.with(context).load("https:"+bean.getImage()).into(viewHolder1.imageView);
        viewHolder1.title.setText(bean.getTitle());
        viewHolder1.second.setText(bean.getSecondTab());
        viewHolder1.count.setText(bean.getNumber());
        Log.d("图片资源", "onBindViewHolder: "+bean.getImage());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        TextView second;
        TextView count;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.v2_image);
            second=itemView.findViewById(R.id.second);
            title=itemView.findViewById(R.id.v2_title);
            count=itemView.findViewById(R.id.count);
        }
    }
}
