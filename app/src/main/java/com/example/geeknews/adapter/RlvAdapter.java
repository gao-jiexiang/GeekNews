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
import com.example.geeknews.bean.Car;

import java.util.ArrayList;

public class RlvAdapter extends BaseRlvAdapter {
    private ArrayList<Car> mCars;

    public RlvAdapter( ArrayList<Car> mCars) {
        this.mCars = mCars;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_date, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh= (ViewHolder) holder;
        vh.tv.setText(mCars.get(position).name);
    }

    @Override
    public int getItemCount() {
        return mCars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv=  itemView.findViewById(R.id.tv);
        }
    }
}
