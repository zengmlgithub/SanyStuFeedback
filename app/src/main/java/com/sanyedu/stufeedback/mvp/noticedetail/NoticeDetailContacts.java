package com.sanyedu.stufeedback.mvp.noticedetail;


import com.sanyedu.sanylib.mvp.IBasePresenter;
import com.sanyedu.sanylib.mvp.IBaseView;
import com.sanyedu.stufeedback.model.NoticeDetailBean;

public final class NoticeDetailContacts {

    public interface INoticeDetailUI extends IBaseView {
        public void setNoticeDetail(NoticeDetailBean detailBean);
    }

    public interface INoticeDetaiPresenter extends IBasePresenter {
        public void getNoticeDetail(String id);
    }


}
