package com.example.geeknews.ui.fragment;


import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.example.geeknews.R;
import com.example.geeknews.base.BaseFragment;
import com.example.geeknews.base.Constants;
import com.example.geeknews.presenter.SettingsPresenter;
import com.example.geeknews.ui.activity.MainActivity;
import com.example.geeknews.util.SpUtil;
import com.example.geeknews.view.SettingsView;
import com.example.geeknews.widget.UIModeUtil;

import butterknife.BindView;

public class SettingsFragment extends BaseFragment<SettingsPresenter> implements SettingsView {

    @BindView(R.id.sw)
    SwitchCompat mSw;
    @BindView(R.id.switchPitchure)
    SwitchCompat switchPitchure;

    //夜间模式，有两套资源的名称相同，一旦切换日夜建模式，系统会
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    protected SettingsPresenter initPresenter() {
        return new SettingsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void initView(View inflate) {
        //根据当前日夜间模式决定switchcompat是否选中
        int mode = (int) SpUtil.getParam(Constants.MODE, AppCompatDelegate.MODE_NIGHT_NO);
        if (mode == AppCompatDelegate.MODE_NIGHT_NO) {
            //日间模式
            mSw.setChecked(false);
        } else {
            mSw.setChecked(true);
        }
    }

    @Override
    protected void initListener() {
        mSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                /*if (日间模式){
                    //切换夜间模式
                }else {
                    //切换日间模式
                }*/
                //帮我们切换日夜间模式
                //切换日夜间模式的时候,activity会重建,
                //只有用户点击了swichCompat才需要切换模式
                if (buttonView.isPressed()) {
                    ((MainActivity) getActivity()).mFlag = "fdlksfjk";
                    //记录由于设置模式导致activity销毁,需要显示的fragment类型
                    SpUtil.setParam(Constants.CURRENT_FRAG_TYPE, MainActivity.TYPE_SETTINGS);

                    UIModeUtil.changeModeUI((AppCompatActivity) getActivity());
                }
            }
        });

        switchPitchure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {

            }
        });
    }
}
