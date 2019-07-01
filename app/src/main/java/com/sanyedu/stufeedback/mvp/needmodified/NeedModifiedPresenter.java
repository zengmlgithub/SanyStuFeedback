package com.sanyedu.stufeedback.mvp.needmodified;

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
import com.sanyedu.stufeedback.model.PageRecordBean;
import com.sanyedu.stufeedback.model.Records;
import com.sanyedu.stufeedback.utils.StuHttpUtil;

import java.util.List;

import okhttp3.Call;


public class NeedModifiedPresenter extends BasePresenter<NeedModifiedContacts.INeedModifiedUI> implements NeedModifiedContacts.INeedModifiedPresenter {
    public NeedModifiedPresenter(@NonNull NeedModifiedContacts.INeedModifiedUI view) {
        super(view);
    }

    @Override
    public void getRecords(@NonNull String startPage, @NonNull String everyPage,@NonNull String type) {

        if(!CheckUtils.isParasLegality(startPage,everyPage,type)){
            SanyLogs.e("params is null,return");
            return;
        }

        String url = HttpUtil.getPort(StuHttpUtil.TODYA_FEEDBACK_PORT);
//        SanyLogs.i("getLogin~~~tokenValue:" + tokenValue);
        OkHttpUtils
                .post()
                .url(url)
//                .addHeader(ConstantUtil.AUTHORIZATION, tokenValue)
                .addParams(StuHttpUtil.TodayFeedback.START_PAGE, startPage)
                .addParams(StuHttpUtil.TodayFeedback.EVERY_PAGE,everyPage)
                .addParams(StuHttpUtil.TodayFeedback.TYPE,type)
                .build()
                .execute(
                        new BaseModelCallback<PageRecordBean>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                                getView().showError(ErrorUtils.PARSE_ERROR);
                            }

                            @Override
                            public void onResponse(BaseModel<PageRecordBean> response, int id) {
                                if (response == null){
//                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    getView().showError(ErrorUtils.PARSE_ERROR);
                                    return;
                                }
//                                SanyLogs.i(response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
//                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    getView().showError(ErrorUtils.PARSE_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)){
//                                    ToastUtil.showLongToast(response.getInfo());
                                    getView().showError(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                PageRecordBean noticeBean = response.getObj();
                                if (noticeBean != null){
                                    List<Records> recordsList = noticeBean.getRecords();
//                                    SanyLogs.i("sanyLog~~~~~~111111");
                                    if (recordsList != null && recordsList.size() > 0){
//                                        SanyLogs.i("sanyLog~~~~~~222222");
                                        getView().setRecords(recordsList,Integer.valueOf(noticeBean.getTotal()));
                                    }else{
                                        //这个不是错误，是没有更多记录了
//                                        ToastUtil.showLongToast(ErrorUtils.PARSE_ERROR);
                                        getView().showNoMoreList();
                                    }
                                }else{
                                   getView().showError(ErrorUtils.SERVER_ERROR);
                                }
                            }
                        }
                );
    }
}
