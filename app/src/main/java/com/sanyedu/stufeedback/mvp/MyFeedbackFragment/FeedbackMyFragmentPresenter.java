package com.sanyedu.stufeedback.mvp.MyFeedbackFragment;

import android.support.annotation.NonNull;
import android.text.TextUtils;


import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.model.BaseModel;
import com.sanyedu.sanylib.model.BaseModelCallback;
import com.sanyedu.sanylib.mvp.BasePresenter;
import com.sanyedu.sanylib.okhttp.OkHttpUtils;
import com.sanyedu.sanylib.utils.ErrorUtils;
import com.sanyedu.sanylib.utils.HttpUtil;
import com.sanyedu.stufeedback.model.PageRecordBean;
import com.sanyedu.stufeedback.model.Records;
import com.sanyedu.stufeedback.utils.StuHttpUtil;

import java.util.List;

import okhttp3.Call;

public class FeedbackMyFragmentPresenter extends BasePresenter<CommonFeedbackFragmentContacts.ICommonFeedbackFragmentUI> implements CommonFeedbackFragmentContacts.ICommonFeedbacFragmentPresenter  {
    public FeedbackMyFragmentPresenter(@NonNull CommonFeedbackFragmentContacts.ICommonFeedbackFragmentUI view) {
        super(view);
    }

    @Override
    public void getFeedbacks(@NonNull String startPage,@NonNull String everyPage, @NonNull String id, @NonNull String type) {
        String url = HttpUtil.getPort(StuHttpUtil.FEEDBACK_MY_PORT);
        SanyLogs.i("request:" + "id:" + id + "====type:" + type);
//        SanyLogs.i("getLogin~~~tokenValue:" + tokenValue);
        OkHttpUtils
                .post()
                .url(url)
//                .addHeader(ConstantUtil.AUTHORIZATION, tokenValue)
                .addParams(StuHttpUtil.TodayFeedback.START_PAGE, startPage)
                .addParams(StuHttpUtil.TodayFeedback.EVERY_PAGE,everyPage)
                .addParams(StuHttpUtil.TodayFeedback.TYPE,type)
                .addParams(StuHttpUtil.MyFeedback.ID,id)
                .build()
                .execute(
                        new BaseModelCallback<PageRecordBean>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
//                                SanyLogs.e("string:" + e.toString());
                                getView().showError(ErrorUtils.SERVER_ERROR);
                            }

                            @Override
                            public void onResponse(BaseModel<PageRecordBean> response, int id) {
                                if (response == null){
//                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    getView().showError(ErrorUtils.SERVER_ERROR);
                                    return;
                                }
                                SanyLogs.i("getfeedback:" + response.toString());
                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
//                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    getView().showError(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)){
//                                    ToastUtil.showLongToast(response.getInfo());
                                    getView().showError(response.getInfo());
                                    return;
                                }

                                PageRecordBean noticeBean = response.getObj();
                                if (noticeBean != null){
                                    List<Records> recordsList = noticeBean.getRecords();
//                                    SanyLogs.i("sanyLog~~~~~~111111");
                                    if (recordsList != null && recordsList.size() > 0){
//                                        SanyLogs.i("sanyLog~~~~~~222222");
                                        int maxCount = Integer.parseInt(noticeBean.getTotal());
                                        getView().setFeebacks(recordsList,maxCount);
                                    }else{
                                        getView().showNoMoreList();
                                    }
                                }else{
                                    getView().showNoMoreList();
                                }
                            }
                        }
                );
    }
}
