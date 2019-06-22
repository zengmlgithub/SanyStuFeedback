package com.sanyedu.stufeedback.mvp.notice;

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
import com.sanyedu.stufeedback.model.NoticeModel;
import com.sanyedu.stufeedback.model.PageNoticeBean;


import java.util.ArrayList;

import okhttp3.Call;

public class NoticePresenter extends BasePresenter<NoticeContacts.INoticeUI> implements NoticeContacts.INoticePresenter {
    public NoticePresenter(@NonNull NoticeContacts.INoticeUI view) {
        super(view);
    }

    @Override
    public void getNotices(@NonNull String startPage,@NonNull String pageCount) {

        if(!CheckUtils.isParasLegality(startPage,pageCount)){
            SanyLogs.e("para is null,return!");
            return;
        }

        String url = HttpUtil.getPort(HttpUtil.NOTICE_PORT);

        OkHttpUtils
                .post()
                .url(url)
                .addParams(HttpUtil.Notice.START_PAGE, startPage)
                .addParams(HttpUtil.Notice.EVERY_PAGE, pageCount)
                .build()
                .execute(new BaseModelCallback<PageNoticeBean>(){

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SanyLogs.e(e.toString());
                        getView().showError(ErrorUtils.PARSE_ERROR);
                    }

                    @Override
                    public void onResponse(BaseModel<PageNoticeBean> response, int id) {
                        //TODO:这个通知有点简单，需要进一步加强
                        if(response == null){
//                            ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                            getView().showError(ErrorUtils.SERVER_ERROR);
                            return ;
                        }

                        String code = response.getCode();
                        if (TextUtils.isEmpty(code ) || !"1".equals(code)){
                            ToastUtil.showLongToast(response.getInfo());

                        }else{
                            PageNoticeBean pageNoticeBean= response.getObj();
                            if(pageNoticeBean != null){
                                ArrayList<NoticeModel> noticeList = pageNoticeBean.getpNotice();
                                if(noticeList != null && noticeList.size() > 0){
                                    int totalPageCount = Integer.valueOf(pageNoticeBean.getTotal());
                                    getView().setNotices(noticeList,totalPageCount);
                                }else{
//                                    getView().setNotices(null);
                                    getView().setNoNotices();
                                }
                            }
                        }


                    }
                });
    }
}
