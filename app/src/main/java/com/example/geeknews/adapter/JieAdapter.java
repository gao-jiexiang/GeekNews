package com.example.geeknews.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geeknews.R;
import com.example.geeknews.widget.FlowLayout;

import java.util.ArrayList;

public class JieAdapter extends RecyclerView.Adapter<JieAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ArrayList<String>> data;
    private TextView mText;
    private String data2;

    public JieAdapter(Context context, ArrayList<ArrayList<String>> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_jie, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewHolder vh= holder;
        ArrayList<String> list = data.get(position);
        for (int i = 0; i < list.size(); i++) {
            mText = new TextView(context);
            mText.setTextSize(15);
            mText.setTextColor(Color.BLUE);
            data2 = list.get(i);
        }
        mText.setText(data2);
        vh.flowLayout.addView(mText);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        FlowLayout flowLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flowLayout=itemView.findViewById(R.id.flow);
        }
    }
}
