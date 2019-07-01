package com.sanyedu.sanylib.utils;

/**
 * 常量管理类
 * <p>
 * Created by 邹峰立 on 2018/3/5.
 */
public class ConstantUtil {
    /**
     * 读取手机状态权限
     */
    public final static int PERMISSIONS_REQUEST_READ_PHONE_STATE = 211;

    /**
     * 字体缩放比例
     */
    public final static int TEXTVIEWSIZE = 1;

    public final static int TIMEMILL = 5000 ;

    public final static String TOKEN = "token";

    public static final String FK_STATE_SUBMITED = "1";  //已提交
    public static final String FK_STATE_CHECKED = "2"; //已审核
    public static final String FK_STATE_WATIIING_MODIFED = "3";
    public static final String FK_STATE_FINISHED = "4";
    public static final String FK_STATE_NULL = "0";
    //图片获取状态码
    public static final int REQUEST_IMAGE_GET = 0;
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int REQUEST_SMALL_IMAGE_CUTTING = 2;
    public static final int REQUEST_CHANGE_USER_NICK_NAME = 10;
    public static final String IMAGE_FILE_NAME = "user_head_icon.jpg";
    //provider constant
    public final static String PROVIER_PATH = "com.sany.myfeedback.fileprovider";
    //图片路径
    public static final String HEAD_IMAGE = "user_head_uri";
}
