package com.sanyedu.sanylib.share;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;


import com.sanyedu.sanylib.base.SanyEdu;
import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.okhttp.utils.Exceptions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SpHelper {

    private static SharedPreferences mSharedPreferences;

    private static synchronized SharedPreferences getPreferneces() {

        Context context = SanyEdu.getContext();
        if(context == null){
            Exceptions.illegalArgument("you can not initial context,please init context in application");
        }

        if (mSharedPreferences == null ) {
            // mSharedPreferences = App.context.getSharedPreferences(
            // PREFERENCE_NAME, Context.MODE_PRIVATE);
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(SanyEdu.getContext());
        }
        return mSharedPreferences;
    }

    /**
     * 打印所有
     */
    public static void print() {

        System.out.println(getPreferneces().getAll());
    }

    /**
     * 清空保存在默认SharePreference下的所有数据
     */
    public static void clear( ) {
        getPreferneces().edit().clear().commit();
    }

    /**
     * 保存字符串
     *
     * @return
     */
    public static void putString(String key, String value) {
        getPreferneces().edit().putString(key, value).commit();
    }

    /**
     * 读取字符串
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        return getPreferneces().getString(key, null);

    }

    public  static <T extends Object> void putObj(String key, T userInfo) throws Exception {
        if(userInfo instanceof Serializable) {
            SharedPreferences sharedPreferences = getPreferneces();
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


    public static <T extends Object> T getObj(String key){
        SharedPreferences sharedPreferences=getPreferneces();
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
    public static void putInt(String key, int value) {
        getPreferneces().edit().putInt(key, value).commit();
    }

    /**
     * 读取整型值
     *
     * @param key
     * @return
     */
    public static int getInt(String key) {
        return getPreferneces().getInt(key, 0);
    }

    /**
     * 保存布尔值
     *
     * @return
     */
    public static void putBoolean(String key, Boolean value) {
        getPreferneces().edit().putBoolean(key, value).commit();
    }

    public static void putLong(String key, long value) {
        getPreferneces().edit().putLong(key, value).commit();
    }

    public static long getLong(String key) {
        return getPreferneces().getLong(key, 0);
    }

    /**
     * t 读取布尔值
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(String key, boolean defValue) {
        return getPreferneces().getBoolean(key, defValue);

    }

    /**
     * 移除字段
     *
     * @return
     */
    public static void removeString(String key) {
        getPreferneces().edit().remove(key).commit();
    }


}
