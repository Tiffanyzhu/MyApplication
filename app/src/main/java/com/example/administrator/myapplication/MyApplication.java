package com.example.administrator.myapplication;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2017/3/28.
 */

public class MyApplication extends Application{
    public static int width, height;
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        // 获得屏幕的高宽（用来适配分辨率）
        width = dm.widthPixels;
        height = dm.heightPixels;
    }
}
