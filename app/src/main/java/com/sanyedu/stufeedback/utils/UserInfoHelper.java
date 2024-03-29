package com.sanyedu.stufeedback.utils;


import android.text.TextUtils;

import com.sanyedu.sanylib.share.SpHelper;
import com.sanyedu.sanylib.utils.ConstantUtil;
import com.sanyedu.stufeedback.model.StudentModel;

public class UserInfoHelper {
    public static String getPersonId(){
        StudentModel bean = SpHelper.getObj(StuContantsUtil.STUINFO);
        String tempId = "";
        if(bean != null){
            tempId = bean.getId();
        }

        return tempId;
    }

    public static String getPersonName() {
        StudentModel bean = SpHelper.getObj(StuContantsUtil.STUINFO);
        String tempName = "";
        if(bean != null){
            tempName = bean.getStuName();
        }
        return tempName;
    }

    public static String getPersonDept(){
        StudentModel bean = SpHelper.getObj(StuContantsUtil.STUINFO);
        String tempDept = "";
        if(bean != null){
//            tempDept = bean.getStuDept();
            tempDept = "工程机械学院";
            //TODO：学生没有系

        }
        return tempDept;
    }

    public static boolean isLogined(String info){
        if(TextUtils.isEmpty(info)){
            return false;
        }
        StudentModel studentModel = SpHelper.getObj(info);
        return studentModel  != null && studentModel.getStuName() != null;
    }
}
