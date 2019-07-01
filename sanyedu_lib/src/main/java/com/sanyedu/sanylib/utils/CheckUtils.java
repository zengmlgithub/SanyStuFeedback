package com.sanyedu.sanylib.utils;

import android.text.TextUtils;


import com.sanyedu.sanylib.log.SanyLogs;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具类
 *
 * @author zengmaolin 2019/07/02
 *
 */
public class CheckUtils {
    /**
     * 判断参数是否为空，如果为空，则返回非法
     *
     * @param params
     * @return
     */
    public static boolean isParasLegality(String... params) {
        boolean isLegality = true;
        for (String para : params) {
            if (TextUtils.isEmpty(para)) {
                isLegality = false;
                SanyLogs.e("param is legacy ,return.");
            }
        }

        return isLegality;
    }

    /**
     * 判断对象中的值是否有空存在
     * @param obj
     * @return
     * @throws
     */
    public static boolean isAllObjFieldLegacity(Object obj) throws Exception {
        Class stuCla = (Class) obj.getClass();// 得到类对象
        Field[] fs = stuCla.getDeclaredFields();//得到属性集合
        boolean flag = true;
        for (Field f : fs) {//遍历属性
            f.setAccessible(true); // 设置属性是可以访问的(私有的也可以)
            Object val = f.get(obj);// 得到此属性的值
            if (val == null) {//只要有1个属性不为空,那么就不是所有的属性值都为空
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        String regexStr = "^[1][3,4,5,7,8,9][0-9]{9}$";
        return check(str,regexStr);
    }

    /**
     * 邮箱验证
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        String regexStr = "[a-zA-Z_]{1,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}" ;
        return check(email,regexStr);
    }

    private static boolean check(String str,String regexStr){
        if(TextUtils.isEmpty(str) || TextUtils.isEmpty(regexStr)){
            return false;
        }
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile(regexStr); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
}
