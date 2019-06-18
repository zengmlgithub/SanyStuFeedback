package com.sanyedu.stufeedback.mvp.myfeedback;


import com.sanyedu.sanylib.model.RecordsBean;
import com.sanyedu.sanylib.mvp.IBasePresenter;
import com.sanyedu.sanylib.mvp.IBaseView;

import java.util.List;

public final class MyFeedbackContacts {
    public interface IMyFeedbackUI extends IBaseView {
        public void setMyFeedbacks(List<RecordsBean> recordsList);
    }

    public interface IMyFeedbackPresenter extends IBasePresenter {
        public void getMyFeedbacks(String startPage, String everyPage, String id, String type);
    }
}
