package com.jy.geeknews.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jy.geeknews.R;
import com.jy.geeknews.bean.Car;

import java.util.ArrayList;

public class RlvCarAdapter extends RecyclerView.Adapter<RlvCarAdapter.ViewHolder> {
    private ArrayList<Car> mCars;

    public RlvCarAdapter(ArrayList<Car> cars) {
        mCars = cars;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rlv_car_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mName.setText(mCars.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mCars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.txt_name);
        }
    }
}
