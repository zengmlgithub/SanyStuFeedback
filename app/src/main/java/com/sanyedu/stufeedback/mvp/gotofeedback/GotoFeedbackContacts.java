package com.sanyedu.stufeedback.mvp.gotofeedback;



import com.sanyedu.sanylib.mvp.IBasePresenter;
import com.sanyedu.sanylib.mvp.IBaseView;
import com.sanyedu.stufeedback.model.DepartModel;
import com.sanyedu.stufeedback.model.FeedbackItem;
import com.sanyedu.stufeedback.model.PersonModel;

import java.util.List;

public final class GotoFeedbackContacts {
    public interface IGotoFeedbackUI extends IBaseView {
        void setDepartList(List<DepartModel> departList);
        void setPersonList(List<PersonModel> personList);

        FeedbackItem getCurrentItem();

        void updateFeedbackSuccess();
        void updateFeedbackFailure(String failureReson);
    }

    public interface IGoToFeedbackPresenter extends IBasePresenter {
        void getDepart();
        void getPersonOfDepart(String departId);
        void postFeedbackToServer(FeedbackItem feedbackBean);
        void postFeedbackToServer(List<String> pathList, final FeedbackItem item);
     }
}
