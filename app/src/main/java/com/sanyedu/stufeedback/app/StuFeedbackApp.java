package com.sanyedu.stufeedback.app;

import android.app.Application;

import com.sanyedu.sanylib.imageloader.ImageLoader;
import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.stufeedback.imageloader.PicassoLoader;

import me.jessyan.autosize.AutoSizeConfig;


public class StuFeedbackApp extends Application {

    public static StuFeedbackApp gApp;

    @Override
    public void onCreate() {
        SanyLogs.d("start app..");
        super.onCreate();
        gApp = this;

        //增加自动适配方案
        AutoSizeConfig.getInstance().setCustomFragment(true);

        //初始化图片库
        ImageLoader.getInstance().setGlobalImageLoader(new PicassoLoader());

    }

    public static final StuFeedbackApp getApp(){
        return gApp;
    }
}
