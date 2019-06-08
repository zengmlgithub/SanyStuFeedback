package com.sanyedu.sanylib.share;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;


import com.sanyedu.sanylib.log.SanyLogs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SpHelper {

    private static SharedPreferences mSharedPreferences;

    private static synchronized SharedPreferences getPreferneces(Context context) {
        if (mSharedPreferences == null) {
            // mSharedPreferences = App.context.getSharedPreferences(
            // PREFERENCE_NAME, Context.MODE_PRIVATE);
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return mSharedPreferences;
    }

    /**
     * 打印所有
     */
    public static void print(Context context) {
        System.out.println(getPreferneces(context).getAll());
    }

    /**
     * 清空保存在默认SharePreference下的所有数据
     */
    public static void clear(Context context) {
        getPreferneces(context).edit().clear().commit();
    }

    /**
     * 保存字符串
     *
     * @return
     */
    public static void putString(Context context,String key, String value) {
        getPreferneces(context).edit().putString(key, value).commit();
    }

    /**
     * 读取字符串
     *
     * @param key
     * @return
     */
    public static String getString(Context context,String key) {
        return getPreferneces(context).getString(key, null);

    }

    public  static <T extends Object> void putObj(Context context,String key, T userInfo) throws Exception {
        if(userInfo instanceof Serializable) {
            SharedPreferences sharedPreferences = getPreferneces(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(userInfo);//把对象写到流里
                String temp = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
                editor.putString(key, temp);
                editor.commit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            throw new Exception("User must implements Serializable");
        }
    }


    public static <T extends Object> T getObj(Context context,String key){
        SharedPreferences sharedPreferences=getPreferneces(context);
        String temp = sharedPreferences.getString(key, "");
        ByteArrayInputStream bais =  new ByteArrayInputStream(Base64.decode(temp.getBytes(), Base64.DEFAULT));
        T userInfo = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            userInfo = (T) ois.readObject();
        } catch (IOException e) {
            SanyLogs.e(e.toString());
        }catch(ClassNotFoundException e) {
            SanyLogs.e(e.toString());
        }
        return userInfo;

    }

    /**
     * 保存整型值
     *
     * @return
     */
    public static void putInt(Context context,String key, int value) {
        getPreferneces(context).edit().putInt(key, value).commit();
    }

    /**
     * 读取整型值
     *
     * @param key
     * @return
     */
    public static int getInt(Context context,String key) {
        return getPreferneces(context).getInt(key, 0);
    }

    /**
     * 保存布尔值
     *
     * @return
     */
    public static void putBoolean(Context context,String key, Boolean value) {
        getPreferneces(context).edit().putBoolean(key, value).commit();
    }

    public static void putLong(Context context,String key, long value) {
        getPreferneces(context).edit().putLong(key, value).commit();
    }

    public static long getLong(Context context,String key) {
        return getPreferneces(context).getLong(key, 0);
    }

    /**
     * t 读取布尔值
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context,String key, boolean defValue) {
        return getPreferneces(context).getBoolean(key, defValue);

    }

    /**
     * 移除字段
     *
     * @return
     */
    public static void removeString(Context context,String key) {
        getPreferneces(context).edit().remove(key).commit();
    }


}
