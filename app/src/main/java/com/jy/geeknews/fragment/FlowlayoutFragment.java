package com.jy.geeknews.fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.geeknews.MainActivity;
import com.jy.geeknews.R;
import com.jy.geeknews.base.BaseFragment;
import com.jy.geeknews.presenter.EmptyPresenter;
import com.jy.geeknews.util.ToastUtil;
import com.jy.geeknews.view.EmptyView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class FlowlayoutFragment extends BaseFragment<EmptyPresenter> implements EmptyView {
    @BindView(R.id.et_key)
    EditText mKey;
    @BindView(R.id.btn_search)
    Button mSearch;
    @BindView(R.id.img_clear)
    ImageView mClear;
    @BindView(R.id.tagFlow)
    TagFlowLayout mTagFlow;
    private ArrayList<String> mKeys;
    private TagAdapter<String> mTagAdapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_flowlayout;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new EmptyPresenter();
    }

    @OnClick(R.id.btn_search)
    public void onSearchClick(){
        String key = mKey.getText().toString();
        if(!TextUtils.isEmpty(key)){
            if(!mKeys.contains(key)) {//搜索历史中不包含此key，则添加进去，包含则不需重复添加
                mKeys.add(0, key);//把新的搜索关键字 添加在第一位
                mTagAdapter.notifyDataChanged();
            }

            mKey.setText("");//情况输入信息
            //进行网络请求的操作，比如调用p进行网络请求
//            mPresenter.searchData(key);

        }else {
            ToastUtil.showShort("搜索不能为空");
        }
    }

    @OnClick(R.id.img_clear)
    public void onClearClick(){
        mKeys.clear();//清空所有搜索记录
        mTagAdapter.notifyDataChanged();
    }

    @Override
    protected void initView() {
        mKeys = new ArrayList<>();//搜索历史记录
        mKeys.add("JAVA");
        mKeys.add("Android");
        mKeys.add("Phthon");
        mKeys.add("长征火箭升空");
        mKeys.add("王牌对王牌第5季");
        mKeys.add("创造营2020");
        mKeys.add("斗罗大陆");
        mKeys.add("时间管理大师");

        mTagAdapter = new TagAdapter<String>(mKeys) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.tag_flowlayout_view,parent,false);
                tv.setText(mKeys.get(position));

                return tv;
            }
        };
        //添加布局中的搜索关键字的监听
        mTagFlow.setAdapter(mTagAdapter);
        //添加点击监听
        mTagFlow.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String key = mKeys.get(position);
                mKey.setText(key);
                return false;
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void showToast(String str) {

    }
}
