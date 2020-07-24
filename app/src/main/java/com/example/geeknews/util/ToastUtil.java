package com.example.geeknews.util;

import android.widget.Toast;

import com.example.geeknews.base.BaseApp;


public class ToastUtil {

    public static void showToastShort(String msg){
        Toast.makeText(BaseApp.sContext,msg, Toast.LENGTH_SHORT).show();
    }

    public static void showToastLong(String msg){
        Toast.makeText(BaseApp.sContext,msg, Toast.LENGTH_LONG).show();
    }
}
