package com.example.geeknews.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geeknews.R;
import com.example.geeknews.adapter.V2_Item_Adapter;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.bean.V2_ItemBean;
import com.example.geeknews.presenter.V2Item_Pre;
import com.example.geeknews.view.V2ItemView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class V2_item_fragment extends BaseFragment<V2Item_Pre> implements V2ItemView {

    @BindView(R.id.v2_rec)
    RecyclerView v2Rec;
    private V2_Item_Adapter adapter;
    private String type;
    private Unbinder unbinder;

    public static BaseFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        V2_item_fragment fragment = new V2_item_fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected V2Item_Pre initPresenter() {
        return new V2Item_Pre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.v2_item_fragment;
    }

    @Override
    public void setData(ArrayList<V2_ItemBean> s) {
        adapter.setData(s);
    }

    @Override
    protected void initView(View inflate) {
        Bundle arguments =
                getArguments();
        type = arguments.getString("type");
        v2Rec.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new V2_Item_Adapter(getContext());
        v2Rec.setAdapter(adapter);
        v2Rec.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        mPresenter.getData(getActivity(), type);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
