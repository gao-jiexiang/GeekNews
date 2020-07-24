package com.example.geeknews.widget;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geeknews.adapter.RlvSpecialShowAdapter;

public class MyCallBack extends ItemTouchHelper.Callback {
    private RlvSpecialShowAdapter mAdapter;
    public MyCallBack(RlvSpecialShowAdapter adapter){
        mAdapter = adapter;
    }

    //需要我们给定RecyclerView子条目可以滑动的方向
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //拖动上下可以拖拽
        int drag = ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        int swipe = ItemTouchHelper.LEFT;
        return makeMovementFlags(drag,swipe);
    }

    //上下拖拽会触发的回调方法
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getLayoutPosition(),
                target.getLayoutPosition());
        return true;
    }

    //左右侧滑的时候会触发的回调
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDelete(viewHolder.getLayoutPosition());
    }

    public  boolean isLongPressDragEnabled(){
        return super.isLongPressDragEnabled();
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }
}
