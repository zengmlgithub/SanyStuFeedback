package com.sanyedu.stufeedback.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.previewlibrary.ZoomMediaLoader;
import com.previewlibrary.loader.IZoomMediaLoader;
import com.previewlibrary.loader.MySimpleTarget;
import com.sanyedu.sanylib.base.SanyEdu;
import com.sanyedu.sanylib.imageloader.ImageLoader;
import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.stufeedback.imageloader.PicassoLoader;
import com.tencent.bugly.crashreport.CrashReport;

import me.jessyan.autosize.AutoSizeConfig;


public class StuFeedbackApp extends Application {

    public static StuFeedbackApp gApp;

    @Override
    public void onCreate() {
        SanyLogs.d("start app..");
        super.onCreate();
        gApp = this;

        //bugly需要的初始化
        CrashReport.initCrashReport(getApplicationContext(), "8286a2d262", false);

        //增加自动适配方案
        AutoSizeConfig.getInstance().setCustomFragment(true);

        //初始化图片库
        ImageLoader.getInstance().setGlobalImageLoader(new PicassoLoader());

        //初始化sanyedu_lib
        SanyEdu.init(this);

    }

    public static final StuFeedbackApp getApp(){
        return gApp;
    }
}
