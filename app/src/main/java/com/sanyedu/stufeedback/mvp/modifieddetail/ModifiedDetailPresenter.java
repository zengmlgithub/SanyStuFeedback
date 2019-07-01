package com.sanyedu.stufeedback.mvp.modifieddetail;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.model.*;
import com.sanyedu.sanylib.mvp.BasePresenter;
import com.sanyedu.sanylib.okhttp.OkHttpUtils;
import com.sanyedu.sanylib.utils.CheckUtils;
import com.sanyedu.sanylib.utils.ErrorUtils;
import com.sanyedu.sanylib.utils.HttpUtil;
import com.sanyedu.stufeedback.model.DetailModel;
import com.sanyedu.stufeedback.utils.StuHttpUtil;

import okhttp3.Call;

public class ModifiedDetailPresenter extends BasePresenter<ModifiedDetailContacts.IModifiedDetailUI> implements ModifiedDetailContacts.IModifiedDetailPresenter {
    public ModifiedDetailPresenter(@NonNull ModifiedDetailContacts.IModifiedDetailUI view) {
        super(view);
    }

    @Override
    public void getDetail(@NonNull String id) {

        if(CheckUtils.isParasLegality(id) == false){
            SanyLogs.e("param is illegac,return!");
            return;
        }

        String url = HttpUtil.getPort(StuHttpUtil.MODIFIED_DETAIL_PORT);
        OkHttpUtils
                .post()
                .url(url)
                .addParams(StuHttpUtil.MoDifiedDetail.ID, id)
                .build()
                .execute(
                        new BaseModelCallback<DetailModel>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                                getView().getDetailFailure(ErrorUtils.SERVER_ERROR);
                            }

                            @Override
                            public void onResponse(BaseModel<DetailModel> response, int id) {
                                if (response == null){
//                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    getView().getDetailFailure(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                SanyLogs.i(response.toString());

                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
//                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    getView().getDetailFailure(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)){
//                                    ToastUtil.showLongToast(response.getInfo());
                                    getView().getDetailFailure(response.getInfo());
                                    return;
                                }

                                DetailModel detailModel = response.getObj();
                                if (detailModel != null){
                                    getView().setDetail(detailModel);
                                    getView().getDetailSuccess();
                                }else{
//                                    ToastUtil.showLongToast(ErrorUtils.PARSE_ERROR);
                                    getView().getDetailFailure(ErrorUtils.PARSE_ERROR);
                                }
                            }
                        }
                );
    }

    @Override
    public void closeFeedback(String id, String feedbackContent, String feedbackPerid, String feedbackPername, String feedbackPerdept){
        String url = HttpUtil.getPort(StuHttpUtil.CLOASE_FEEDBACK_PORT);
        OkHttpUtils
                .post()
                .url(url)
                .addParams(StuHttpUtil.CloseFeedback.FEEDBACK_ID, id)
                .addParams(StuHttpUtil.CloseFeedback.FEEDBACK_CONTENT, feedbackContent)
                .addParams(StuHttpUtil.CloseFeedback.FEEDBACK_PERSON_ID, feedbackPerid)
                .addParams(StuHttpUtil.CloseFeedback.FEEDBACK_PERSON_NAME,feedbackPername)
                .addParams(StuHttpUtil.CloseFeedback.FEEDBACK_PERSON_DEPT,feedbackPerdept)
                .build()
                .execute(
                        new BaseModelCallback<String>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                                getView().getDetailFailure(ErrorUtils.SERVER_ERROR);
                            }

                            @Override
                            public void onResponse(BaseModel<String> response, int id) {
                                if (response == null){
//                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    getView().getDetailFailure(ErrorUtils.PARSE_ERROR);
                                    return;
                                }
//                                SanyLogs.i(response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
                                    getView().getDetailFailure(ErrorUtils.PARSE_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)){
                                    getView().getDetailFailure(response.getInfo());
                                    return;
                                }
                                getView().modifySuccess();
                            }
                        }
                );
    }
}
