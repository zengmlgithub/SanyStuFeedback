package com.sanyedu.sanylib.utils;

import android.content.Context;
import android.content.Intent;

import com.sanyedu.sanylib.log.SanyLogs;


/**
 * 启动activity
 */
public class StartUtils {
    public static void startActivity(Context packageContext, Class<?> cls){
       try{
          packageContext.startActivity(new Intent(packageContext,cls));
       }catch (Exception e ){
           SanyLogs.e(e.toString());
       }
    }

    private static final String ID = "id";
    public static void startActivity(Context packageContext, Class<?> cls,String id){
        try {
            Intent intent = new Intent();
            intent.putExtra(ID, id);
            intent.setClass(packageContext, cls);
            packageContext.startActivity(intent);
        }catch (Exception e){
            SanyLogs.i(e.toString());
        }
    }
}
