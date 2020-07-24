package com.example.geeknews.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRlvAdapter extends RecyclerView.Adapter {
    public OmItemClickListener mOmItemClickListener;


    public interface OmItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOmItemClickListener(OmItemClickListener listener){
        this.mOmItemClickListener=listener;
    }
}
