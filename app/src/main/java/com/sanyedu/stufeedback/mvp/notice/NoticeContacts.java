package com.sanyedu.stufeedback.mvp.notice;


import com.sanyedu.sanylib.mvp.IBasePresenter;
import com.sanyedu.sanylib.mvp.IBaseView;
import com.sanyedu.stufeedback.model.NoticeModel;

import java.util.ArrayList;

public final class NoticeContacts {
    public interface INoticeUI extends IBaseView {
        void setNotices(ArrayList<NoticeModel> notices, int maxPageCount);

        void setNoNotices();

        /**
         * 查看错误
         * @param serverError
         */
        void showError(String serverError);
    }

    public interface INoticePresenter extends IBasePresenter {
        /**
         *
         * @param curPage  当前查询的页面
         * @param pageCount 每个页面拥有的记录条数
         */
         void getNotices(String curPage, String pageCount);
    }
}
