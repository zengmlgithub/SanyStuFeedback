package com.sanyedu.sanylib.utils;

import android.text.TextUtils;

import com.sanyedu.sanylib.log.SanyLogs;


/**
 * 网络相关的协议
 */
public class HttpUtil {

    public static final boolean IS_TEST = true;
    private static final boolean IS_HTTP = true;

    private static final String FINAL_SERVER = "";  //正式服务器地址
    private static final String TEST_SERVER = "172.16.11.73:8082";  //测试服务器地址

    public static final String HTTPS = "https://";
    public static final String HTTP = "http://";

    public static final String TEST_PORT = "testClass/testSel";

    public static final String AUTH_PORT = "auth";
    public static final String LOGIN_PORT = "loginUser/log";
    public static final String NOTICE_PORT = "info/sel_dicat";
    public static final String NOTICE_DETAIL_PORT = "info/selDis_info"; //单条通知详情
    public static final String TODYA_FEEDBACK_PORT = "academic/selInfo_day"; //今日反馈条数
    public static final String MODIFIED_DETAIL_PORT = "academic/selInfo_det"; //整改详情
    public static final String MY_FEEDBACK_PORT = "academic/myDectis";//我的反馈
    public static final String FEEDBACK_MY_PORT = "academic/selMyInfo";//反馈我的
    public static final String GET_ALL_DEPART_PORT = "schUser/selDept"; //查询所有部门
    public static final String GET_ONE_DEPART_TEACHER_PORT = "schUser/selTeacher"; //查找某个部门下的所有老师
    public static final String POST_FEEDBACK_TO_SERVER_PORT = "submitFeedback/updateFile";//上传反馈到服务器
    public static final String UPDATE_PERSONAL_PASSWORD_PORT = "schUser/updPass"; //修改个人密码
    public static final String UPDATE_PERSON_OBJ_PORT = "schUser/updData"; //修改个人资料


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


//    public static final String getTestPort(){
//        String server = getServerHost();
//        String url = server + TEST_PORT;
//        return url;
//    }
//
//    public static final String getTokenPort(){
//        String server = getServerHost();
//        String url = server + AUTO_PORT;
//        return url;
//    }

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
