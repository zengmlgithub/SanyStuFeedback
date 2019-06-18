package com.sanyedu.stufeedback.mvp.modifieddetail;


import com.sanyedu.sanylib.mvp.IBasePresenter;
import com.sanyedu.sanylib.mvp.IBaseView;
import com.sanyedu.stufeedback.model.DetailModel;

public final class ModifiedDetailContacts {
    public interface IModifiedDetailUI extends IBaseView {

        void setDetail(DetailModel bean);
        void getDetailFailure(String msg);
        void getDetailSuccess();
        void modifySuccess();
    }

    public interface IModifiedDetailPresenter extends IBasePresenter {
        void getDetail(String id);
        void closeFeedback(String id, String feedbackContent, String feedbackPerid, String feedbackPername, String feedbackPerdept);
    }
}
