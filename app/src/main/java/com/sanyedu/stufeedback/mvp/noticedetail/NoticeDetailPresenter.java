package com.sanyedu.stufeedback.mvp.noticedetail;

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
import com.sanyedu.stufeedback.model.NoticeDetailBean;

import okhttp3.Call;

public class NoticeDetailPresenter extends BasePresenter<NoticeDetailContacts.INoticeDetailUI> implements NoticeDetailContacts.INoticeDetaiPresenter {
    public NoticeDetailPresenter(@NonNull NoticeDetailContacts.INoticeDetailUI view) {
        super(view);
    }

    @Override
    public void getNoticeDetail(@NonNull String id) {
        String url = HttpUtil.getPort(HttpUtil.NOTICE_DETAIL_PORT);

        OkHttpUtils
                .get()
                .url(url)
                .addParams(HttpUtil.NoticeDetail.ID, id)
                .build()
                .execute(new BaseModelCallback<NoticeDetailBean>(){

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SanyLogs.e(e.toString());
                    }

                    @Override
                    public void onResponse(BaseModel<NoticeDetailBean> response, int id) {
                        SanyLogs.i(response.toString());

                        if(response == null){
                            ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                            return ;
                        }

                        String code = response.getCode();
                        if (TextUtils.isEmpty(code ) || !"1".equals(code)){
                            ToastUtil.showLongToast(response.getInfo());
                        }else{
                            NoticeDetailBean detailBean= response.getObj();
                            getView().setNoticeDetail(detailBean);
                        }
                    }
                });
    }
}
