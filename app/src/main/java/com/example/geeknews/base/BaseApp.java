package com.example.geeknews.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.geeknews.util.LogUtil;
import com.example.geeknews.util.SpUtil;
import com.example.geeknews.widget.UIModeUtil;

import org.json.JSONObject;
//app一上来会先走application是有条件的,要求app原来所在的进程被杀死才会走,
//如果仅仅是activity销毁了,不一定走
//Android 系统为了提高app启动的速度,在界面销毁之后,进程不会被杀死, 而是变成一个空进程
public class BaseApp extends Application {
    public static Context sContext;
    public static int mMode;

    public static Resources getRes() {
        return sContext.getResources();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

        setDayNightMode();
    }

    private void setDayNightMode() {
        //根据sp里面的模式设置对应的日夜间模式 y:2;no:1
        mMode = (int) SpUtil.getParam(Constants.MODE, AppCompatDelegate.MODE_NIGHT_NO);
        LogUtil.print( "setDayNightMode: "+mMode);
        UIModeUtil.setAppMode(mMode);
    }
}
