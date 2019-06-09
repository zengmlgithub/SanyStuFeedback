package com.sanyedu.stufeedback.mvp.gotofeedback;

import android.support.annotation.NonNull;
import android.text.TextUtils;


import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.model.BaseModel;
import com.sanyedu.sanylib.model.BaseModelCallback;
import com.sanyedu.sanylib.mvp.BasePresenter;
import com.sanyedu.sanylib.okhttp.OkHttpUtils;
import com.sanyedu.sanylib.utils.ErrorUtils;
import com.sanyedu.sanylib.utils.HttpUtil;
import com.sanyedu.sanylib.utils.ToastUtil;
import com.sanyedu.stufeedback.model.DepartModel;
import com.sanyedu.stufeedback.model.PersonModel;

import java.io.File;
import java.util.List;

import okhttp3.Call;

public class GotoFeedbackPresenter extends BasePresenter<GotoFeedbackContacts.IGotoFeedbackUI> implements GotoFeedbackContacts.IGoToFeedbackPresenter {
    public GotoFeedbackPresenter(@NonNull GotoFeedbackContacts.IGotoFeedbackUI view) {
        super(view);
    }

    @Override
    public void getDepart() {
        String url = HttpUtil.getPort(HttpUtil.GET_ALL_DEPART_PORT);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<List<DepartModel>>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<List<DepartModel>> response, int id) {
                                if (response == null){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)){
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }

                                List<DepartModel> departList = response.getObj();
                                if(departList != null && departList.size() > 0){
                                    getView().setDepartList(departList);
                                }else{
                                    ToastUtil.showLongToast(ErrorUtils.PARSE_ERROR);
                                }
                            }
                        }
                );
    }

    @Override
    public void getPersonOfDepart(String departId) {
        String url = HttpUtil.getPort(HttpUtil.GET_ONE_DEPART_TEACHER_PORT);
        OkHttpUtils
                .post()
                .addParams(HttpUtil.OneDepartTeacher.DEPART_ID,departId)
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<List<PersonModel>>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<List<PersonModel>> response, int id) {
                                if (response == null){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)){
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }

                                List<PersonModel> personBeans = response.getObj();
                                if(personBeans != null && personBeans.size() > 0){
                                    getView().setPersonList(personBeans);
                                }else{
                                    ToastUtil.showLongToast(ErrorUtils.PARSE_ERROR);
                                }
                            }
                        }
                );
    }

    @Override
    public void postFeedbackToServer(String feedbackTitle, String feedbackAddress, String feedbackContent, String feedbackDept, String feedbackPersonid, String feedbackPersonname, String feedbackA, String feedbackB, String feedbackC, String toResponsiblename, String toResponsibledept) {
        String url = HttpUtil.getPort(HttpUtil.POST_FEEDBACK_TO_SERVER_PORT);
        OkHttpUtils
                .post()
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_TITLE,feedbackTitle)
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_ADDRESS,feedbackAddress)
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_CONTENT,feedbackContent)
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_DEPT,feedbackDept)
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_PERSON_ID,feedbackPersonid)
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_PERSON_NAME,feedbackPersonname)
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_A,feedbackA)
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_B,feedbackB)
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_C,feedbackC)
                .addParams(HttpUtil.FeedbackToServer.TO_RESPONSIBL_NAME,toResponsiblename)
                .addParams(HttpUtil.FeedbackToServer.TO_RESPONSIBLE_DEPT,toResponsibledept)
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<String>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<String> response, int id) {
                                if (response == null){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)){
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }

                               String feedbackId = response.getObj();
                                if(!TextUtils.isEmpty(feedbackId)){
                                    ToastUtil.showLongToast("反馈上传成功！");
                                }
                            }
                        }
                );
    }


    private void postFile(final String fileName,final File file,final int i) {
        String url = HttpUtil.getPort(HttpUtil.GET_ONE_DEPART_TEACHER_PORT);
        OkHttpUtils
                .post()
                .addFile("img",fileName,file)
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<String>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<String> response, int id) {
                                if (response == null){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)){
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }

//                                List<PersonBean> personBeans = response.getObj();
//                                if(personBeans != null && personBeans.size() > 0){
//                                    getView().setPersonList(personBeans);
//                                }else{
//                                    ToastUtil.showLongToast(ErrorUtils.PARSE_ERROR);
//                                }
                                String path = response.getObj();
                                if(!TextUtils.isEmpty(path)){
                                    SanyLogs.i("第" + i + "张图片上传成功");
                                }
                                //传一下张图片

                            }
                        }
                );
    }

    @Override
    public void postFiles(List<String> files) {
        if(files == null || files.size() <= 0){
            SanyLogs.e("file is null,return");
            return;
        }
        for(int i = 0; i < files.size(); i ++){
            File file = new File(files.get(i));
            if(file.isFile() && file.exists()){
                postFile(files.get(i),file,i+1);
            }
        }
    }
}
