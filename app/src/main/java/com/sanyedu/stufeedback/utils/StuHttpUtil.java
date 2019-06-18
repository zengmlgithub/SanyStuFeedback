package com.sanyedu.stufeedback.utils;

public class StuHttpUtil {
    public static final String CLOASE_FEEDBACK_PORT = "submitFeedback/updFeedback"; //关闭反馈

    public static final String UPLOAD_SUBRECTIFICATION_PORT = "submitFeedback/subRectification";  //更新状态

    public static final String UPLOAD_PHOTO_PORT = "submitFeedback/updateFile"; //图片上传

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
}
