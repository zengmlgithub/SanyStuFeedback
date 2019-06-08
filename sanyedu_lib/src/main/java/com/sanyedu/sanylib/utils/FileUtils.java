package com.sanyedu.sanylib.utils;

import android.os.Environment;

import com.sanyedu.sanylib.log.SanyLogs;

import java.io.File;

public class FileUtils {
    private static final String MyPetRootDirectory = Environment.getExternalStorageDirectory() + File.separator+"MyPet";
    public static String getMyPetRootDirectory(){
        return MyPetRootDirectory;
    }

    public static void mkdirRootDirectory(){
        boolean isSdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);// 判断sdcard是否存在
        if (isSdCardExist) {
            File MyPetRoot = new File(getMyPetRootDirectory());
            if (!MyPetRoot.exists()) {
                try {
                    MyPetRoot.mkdir();
//                    Log.d(TAG, "mkdir success");
                    SanyLogs.d("mkdir success");
                } catch (Exception e) {
//                    Log.e(TAG, "exception->" + e.toString());
                    SanyLogs.e("exception->" + e.toString());
                }
            }
        }
    }
}
