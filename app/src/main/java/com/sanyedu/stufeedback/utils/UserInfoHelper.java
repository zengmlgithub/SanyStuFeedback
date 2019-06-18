package com.sanyedu.stufeedback.utils;


import com.sanyedu.sanylib.share.SpHelper;
import com.sanyedu.sanylib.utils.ConstantUtil;
import com.sanyedu.stufeedback.model.StudentModel;

public class UserInfoHelper {
    public static String getPersonId(){
        StudentModel bean = SpHelper.getObj(ConstantUtil.USERINFO);
        String tempId = "";
        if(bean != null){
            tempId = bean.getId();
        }

        return tempId;
    }

    public static String getPersonName() {
        StudentModel bean = SpHelper.getObj(ConstantUtil.USERINFO);
        String tempName = "";
        if(bean != null){
            tempName = bean.getStuName();
        }
        return tempName;
    }

    public static String getPersonDept(){
        StudentModel bean = SpHelper.getObj(ConstantUtil.USERINFO);
        String tempDept = "";
        if(bean != null){
//            tempDept = bean.getStuDept();
            //TODO：学生没有系
        }
        return tempDept;
    }

}
