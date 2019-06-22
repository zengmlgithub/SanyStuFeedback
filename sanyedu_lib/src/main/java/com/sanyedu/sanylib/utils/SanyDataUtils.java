package com.sanyedu.sanylib.utils;

import android.text.TextUtils;


public class SanyDataUtils {

    public static  String getFormatStr(String dataStr){
        String formatedStr = dataStr;
       if (!TextUtils.isEmpty(dataStr) && dataStr.length() > 6){
           formatedStr =  dataStr.substring(5,10);
       }
       return formatedStr;
    }
}
