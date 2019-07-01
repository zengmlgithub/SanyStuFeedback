package com.sanyedu.stufeedback.utils;

public class StuHttpUtil {
    public static final String CLOASE_FEEDBACK_PORT = "submitFeedback/updFeedback"; //关闭反馈

    public static final String UPLOAD_SUBRECTIFICATION_PORT = "submitFeedback/subRectification";  //更新状态

    public static final String UPLOAD_PHOTO_PORT = "submitFeedback/updateFile"; //图片上传

    public static final String POST_FEEDBACK_TO_SERVER_PORT = "submitFeedback/sub_deai";//上传反馈到服务器

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

    public static final String UPDATE_PERSONAL_PASSWORD_PORT = "schUser/updPass"; //修改个人密码
    public static final String UPDATE_PERSON_OBJ_PORT = "schUser/updData"; //修改个人资料

    public static final String GET_MY_FEEDBACK_COUNT_PORT = "academic/selMyInfoNum";//获取条数



    public static final String SUCCESS = "1";
    public static final String ERROR_ACCOUNT = "0"; //账号密码错误
    public static final String ERROR_SYSTEM_EXCEPTION = "400";//系统异常
    public static final String ERROR_SERVER = "服务器出错啦";


    public static class CloseFeedback{
        public static final String FEEDBACK_ID = "id";
        public static final String FEEDBACK_CONTENT = "feedbackContent";
        public static final String FEEDBACK_PERSON_ID = "feedbackPerid";
        public static final String FEEDBACK_PERSON_NAME = "feedbackPername";
        public static final String FEEDBACK_PERSON_DEPT = "feedbackPerdept";
    }

    public static class UpdateFeedbackState{
        public final static String FEEDBACK_ID = "feedbackId";
        public final static String FEEDBACK_STATUS = "feedbackStatus";
        public final static String FEEDBACK_CONTENT = "feedbackContent";
        public final static String FEEDBACK_PERID = "feedbackPerid"; //关闭人id
        public final static String FEEDBACK_PERNAME = "feedbackPername"; //关闭人名称
        public final static String FEEDBACK_PERDEPT = "feedbackPerdept"; //关闭人部门
        public final static String FEEDBACK_FILEA= "feedbackFilea";
        public final static String FEEDBACK_FILEB = "feedbackFileb";
        public final static String FEEDBACK_FILEC = "feedbackFilec";
    }

    public static class TodayFeedback{
        public final static String START_PAGE="startPage";
        public final static String EVERY_PAGE="everyPage";
        public final static String TYPE = "type";
    }

    public static class MyFeedbackCount{
        public static final String ID = "id";
        public static final String TYPE = "type";
    }

    public  class Notice{
        public final static String START_PAGE="startPage";
        public final static String EVERY_PAGE="everyPage";
    }

    public class NoticeDetail{
        public final static  String ID = "id";
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
