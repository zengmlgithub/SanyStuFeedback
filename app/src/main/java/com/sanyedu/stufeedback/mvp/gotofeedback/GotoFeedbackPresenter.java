package com.sanyedu.stufeedback.mvp.gotofeedback;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.model.BaseModel;
import com.sanyedu.sanylib.model.BaseModelCallback;

import com.sanyedu.sanylib.mvp.BasePresenter;
import com.sanyedu.stufeedback.model.DepartBean;
import com.sanyedu.stufeedback.model.DepartModel;
import com.sanyedu.stufeedback.model.FeedbackItem;
import com.sanyedu.stufeedback.model.PersonModel;
import com.sanyedu.stufeedback.mvp.UpdatePicture.UpdatePictureService;
import com.sanyedu.sanylib.okhttp.OkHttpUtils;
import com.sanyedu.sanylib.utils.CheckUtils;
import com.sanyedu.sanylib.utils.ErrorUtils;
import com.sanyedu.sanylib.utils.HttpUtil;
import com.sanyedu.sanylib.utils.ToastUtil;

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
                        new BaseModelCallback<List<DepartModel>>() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<List<DepartModel>> response, int id) {
                                if (response == null) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)) {
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }

                                List<DepartModel> departList = response.getObj();
                                if (departList != null && departList.size() > 0) {
                                    getView().setDepartList(departList);
                                } else {
                                    ToastUtil.showLongToast(ErrorUtils.PARSE_ERROR);
                                }
                            }
                        }
                );
    }

    @Override
    public void getPersonOfDepart(String departId) {

        if(CheckUtils.isParasLegality(departId) == false){
            SanyLogs.e("param is illegac,return!");
            return;
        }


        String url = HttpUtil.getPort(HttpUtil.GET_ONE_DEPART_TEACHER_PORT);
        OkHttpUtils
                .post()
                .addParams(HttpUtil.OneDepartTeacher.DEPART_ID, departId)
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<List<PersonModel>>() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<List<PersonModel>> response, int id) {
                                if (response == null) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)) {
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }

                                List<PersonModel> personBeans = response.getObj();
                                if (personBeans != null && personBeans.size() > 0) {
                                    getView().setPersonList(personBeans);
                                } else {
                                    ToastUtil.showLongToast(response.getInfo());
                                }
                            }
                        }
                );
    }

    @Override
    public void postFeedbackToServer(FeedbackItem item) {
        if (item == null) {
            SanyLogs.i("item is null,return!");
            return;
        }

        SanyLogs.i(item.toString());

        String url = HttpUtil.getPort(HttpUtil.POST_FEEDBACK_TO_SERVER_PORT);
        OkHttpUtils
                .post()
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_TITLE, item.getFeedbackTitle())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_ADDRESS, item.getFeedbackAddress())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_CONTENT, item.getFeedbackContent())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_DEPT, item.getFeedbackDept())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_PERSON_ID, item.getFeedbackPersonid())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_PERSON_NAME, item.getFeedbackPersonname())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_A, item.getFeedbackA())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_B, item.getFeedbackB())
                .addParams(HttpUtil.FeedbackToServer.FEEDBACK_C, item.getFeedbackC())
                .addParams(HttpUtil.FeedbackToServer.TO_RESPONSIBL_NAME, item.getToResponsiblename())
                .addParams(HttpUtil.FeedbackToServer.TO_RESPONSIBLE_DEPT, item.getToResponsibledept())
                .addParams(HttpUtil.FeedbackToServer.TO_RESPONSIBLE_ID, item.getToResponsibleid())
                .url(url)
                .build()
                .execute(
                        new BaseModelCallback<String>() {

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<String> response, int id) {
                                if (response == null) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)) {
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)) {
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }

                                String feedbackId = response.getObj();
                                if (!TextUtils.isEmpty(feedbackId)) {
//                                    ToastUtil.showLongToast("反馈上传成功！");
                                    getView().updateFeedbackSuccess();
                                }
                            }
                        }
                );
    }


    @Override
    public void postFeedbackToServer(List<String> files,final FeedbackItem item) {
        if (files == null || files.size() <= 0) {
            SanyLogs.e("file is null,return");
            return;
        }

        UpdatePictureService updatePictureService = new UpdatePictureService(files, new UpdatePictureService.UpdateFinishedListener() {
            @Override
            public void updateFinished(UpdatePictureService service,List<String> serverPathList) {
                if(item != null){
                    item.setFeedbackA(service.getServicePathA(serverPathList));
                    item.setFeedbackB(service.getServicePathB(serverPathList));
                    item.setFeedbackC(service.getServicePathC(serverPathList));
                    postFeedbackToServer(item);
                }
            }

            @Override
            public void updateFailure(UpdatePictureService service, String msg) {
                getView().updateFeedbackFailure(msg);
            }
        });

        updatePictureService.postFiles();
    }



}
