package com.sanyedu.sanylib.base;

import android.content.Context;

public class SanyEdu {


    private static Context appContext;

    public static void init(Context context) {
        if (null != context) {
            appContext = context;
        }
    }

    public static Context getContext(){
        return appContext;
    }
}
