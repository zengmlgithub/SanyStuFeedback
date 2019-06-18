package com.sanyedu.stufeedback.mvp.modifychange;

import android.support.annotation.NonNull;
import android.text.TextUtils;


import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.model.BaseModel;
import com.sanyedu.sanylib.model.BaseModelCallback;
import com.sanyedu.sanylib.mvp.BasePresenter;
import com.sanyedu.sanylib.okhttp.OkHttpUtils;
import com.sanyedu.sanylib.utils.CheckUtils;
import com.sanyedu.sanylib.utils.ErrorUtils;
import com.sanyedu.sanylib.utils.HttpUtil;
import com.sanyedu.sanylib.utils.ToastUtil;
import com.sanyedu.stufeedback.model.ChangeFeedbackModel;
import com.sanyedu.stufeedback.mvp.UpdatePicture.UpdatePictureService;
import com.sanyedu.stufeedback.utils.StuHttpUtil;

import java.util.List;

import okhttp3.Call;

public class ModifyChangePresenter extends BasePresenter<ModifyChangeContacts.IModifyChangeUI> implements ModifyChangeContacts.IModifyChangePresenter {
    public ModifyChangePresenter(@NonNull ModifyChangeContacts.IModifyChangeUI view) {
        super(view);
    }


    private void updateFeedback(@NonNull ChangeFeedbackModel changeFeedbackModel, String pathA, String pathB, String pathC){

        String url = HttpUtil.getPort(StuHttpUtil.UPLOAD_SUBRECTIFICATION_PORT);

        SanyLogs.i(changeFeedbackModel.toString());

        try {
            if(!CheckUtils.isAllObjFieldLegacity(changeFeedbackModel)){
                SanyLogs.e("ChangeFeedbackBean is null,return!");
                return;
            }
        }catch (Exception e){
            SanyLogs.e(e.toString());
            return;
        }

        OkHttpUtils
                .post()
                .url(url)
               .addParams(StuHttpUtil.UpdateFeedbackState.FEEDBACK_ID, changeFeedbackModel.getFeedbackId())
                .addParams(StuHttpUtil.UpdateFeedbackState.FEEDBACK_STATUS, changeFeedbackModel.getFeedbackStatus())
                .addParams(StuHttpUtil.UpdateFeedbackState.FEEDBACK_CONTENT, changeFeedbackModel.getFeedbackContent())
                .addParams(StuHttpUtil.UpdateFeedbackState.FEEDBACK_PERID, changeFeedbackModel.getFeedbackPerid())
                .addParams(StuHttpUtil.UpdateFeedbackState.FEEDBACK_PERNAME, changeFeedbackModel.getFeedbackPername())
                .addParams(StuHttpUtil.UpdateFeedbackState.FEEDBACK_PERDEPT, changeFeedbackModel.getFeedbackPerdept())
                .addParams(StuHttpUtil.UpdateFeedbackState.FEEDBACK_FILEA,pathA)
                .addParams(StuHttpUtil.UpdateFeedbackState.FEEDBACK_FILEB,pathB)
                .addParams(StuHttpUtil.UpdateFeedbackState.FEEDBACK_FILEC,pathC)
                .build()
                .execute(
                        new BaseModelCallback<String>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
//                                getView().updateFeedbackResult(ModifyChangeContacts.IModifyChangeUI.UPDATE＿FAILURE);
                                getView().updateFeedbackFailure("上传失败");
                            }

                            @Override
                            public void onResponse(BaseModel<String> response, int id) {
                                if (response == null){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    getView().updateFeedbackFailure("上传失败");
                                    return;
                                }
//                                SanyLogs.i(response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    getView().updateFeedbackFailure("上传失败");
                                    return;
                                }

                                if (!"1".equals(code)){
                                    ToastUtil.showLongToast(response.getInfo());
                                    getView().updateFeedbackFailure("上传失败");
                                    return;
                                }

//                                String noticeBean = response.getObj();
//                                ToastUtil.showLongToast("反馈成功");
                                getView().updateFeedbackSuccess();
                            }
                        }
                );
    }



    @Override
    public void updateFeedback(final List<String> files, final ChangeFeedbackModel changeFeedbackModel) {

//        try {
//            if(!CheckUtils.isAllObjFieldLegacity(changeFeedbackBean)){
//                SanyLogs.e("ChangeFeedbackBean is null,return!");
//                return;
//            }
//        }catch (Exception e){
//            SanyLogs.e(e.toString());
//            return;
//        }

        if (files == null || files.size() <= 0) {
            SanyLogs.e("file is null,return");
            return;
        }

        UpdatePictureService updatePictureService = new UpdatePictureService(files, new UpdatePictureService.UpdateFinishedListener() {
            @Override
            public void updateFinished(UpdatePictureService service,List<String> serverPathList) {
                if(service.hasPhoto()){
                    if(changeFeedbackModel != null){
                        updateFeedback(changeFeedbackModel,files.get(0),files.get(1),files.get(2));
                    }
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
