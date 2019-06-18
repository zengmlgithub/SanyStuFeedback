package com.sanyedu.stufeedback.mvp.modifychange;



import com.sanyedu.sanylib.mvp.IBasePresenter;
import com.sanyedu.sanylib.mvp.IBaseView;
import com.sanyedu.stufeedback.model.ChangeFeedbackBean;

import java.util.List;

public final class ModifyChangeContacts {
    public interface IModifyChangeUI extends IBaseView {
        int UPDATE＿SUCCESS = 0;
        int UPDATE＿FAILURE = 1;
        void updateFeedbackFailure(String msg);
        void updateFeedbackSuccess();
    }

    public interface IModifyChangePresenter extends IBasePresenter {
         void updateFeedback(List<String> files, ChangeFeedbackBean changeFeedbackBean);
    }


}
