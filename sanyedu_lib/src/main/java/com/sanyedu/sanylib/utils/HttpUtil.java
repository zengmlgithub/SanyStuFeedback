package com.sanyedu.sanylib.utils;

import android.text.TextUtils;

import com.sanyedu.sanylib.log.SanyLogs;


/**
 * 网络相关的协议
 */
public class HttpUtil {

    public static final boolean IS_TEST = false;
    private static final boolean IS_HTTP = true;

    private static final String FINAL_SERVER = "42.48.115.230:8082";  //正式服务器地址
    private static final String TEST_SERVER = "42.48.115.105:8082";  //测试服务器地址

    public static final String HTTPS = "https://";
    public static final String HTTP = "http://";

    public static final String TEST_PORT = "testClass/testSel";

    public static final String SUCCESS = "1";
    public static final String ERROR_ACCOUNT = "0"; //账号密码错误
    public static final String ERROR_SYSTEM_EXCEPTION = "400";//系统异常
    public static final String ERROR_SERVER = "服务器出错啦";

    private static final String getServerHost() {
        String prot = IS_HTTP ? HTTP : HTTPS;
        String server = IS_TEST ? TEST_SERVER : FINAL_SERVER;
        String protServer = prot + server;
        SanyLogs.i("protServer:" + protServer);
        if(protServer == null ){
            return null;
        }else{
            if(protServer.endsWith("/")){
                return protServer;
            }else{
                return protServer + "/";
            }
        }
    }

    public static final String getPort(String serverAddress){
        String url = null;
        if(!TextUtils.isEmpty(serverAddress)){
            String server = getServerHost();
            url = server + serverAddress;
        }
        return url;
    }


    public  class Notice{
        public final static String START_PAGE="startPage";
        public final static String EVERY_PAGE="everyPage";
    }

    public class NoticeDetail{
        public final static  String ID = "id";
    }

    public static class TodayFeedback{
        public final static String START_PAGE="startPage";
        public final static String EVERY_PAGE="everyPage";
        public final static String TYPE = "type";
    }

    public static class MoDifiedDetail{
        public final static String ID = "id";
    }

    public static class MyFeedback{
        public final static String START_PAGE = "startPage";
        public final static String EVERY_PAGE = "everyPage";
        public final static String ID = "id";
        public final static String TYPE = "type";
    }

    public static class OneDepartTeacher{
        public final static String DEPART_ID = "id";
        public final static String TeName = "teName";
    }

    public static class FeedbackToServer{
        public final static String FEEDBACK_TITLE = "feedbackTitle";
        public final static String FEEDBACK_ADDRESS = "feedbackAdress";
        public final static String FEEDBACK_CONTENT = "feedbackContent";
        public final static String FEEDBACK_DEPT = "feedbackDept";
        public final static String FEEDBACK_PERSON_ID = "feedbackPersonid";
        public final static String FEEDBACK_PERSON_NAME = "feedbackPersonname";
        public final static String FEEDBACK_A = "feedbackA";
        public final static String FEEDBACK_B = "feedbackB";
        public final static String FEEDBACK_C = "feedbackC";
        public final static String TO_RESPONSIBL_NAME = "toResponsiblename";
        public final static String  TO_RESPONSIBLE_DEPT= "toResponsibledept";
        public final static String TO_RESPONSIBLE_ID = "toResponsibleid";
    }

    public static class UploadFile{
//        public final static String KEY = "key";
    }

    public static class UpdatePwd{
        public final static String TYPE = "type";
        public final static String ID = "id";
        public final static String USERNAME = "userName";
        public final static String PASSWORD = "password";
        public final static String NewPassword = "newPassword";
    }

    public static class UpdateObj{
        public final static String TYPE = "type";
        public final static String TE_USER = "teUser";
    }
}
