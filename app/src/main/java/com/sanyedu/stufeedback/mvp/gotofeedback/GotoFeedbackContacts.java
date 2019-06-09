package com.sanyedu.stufeedback.mvp.gotofeedback;

import com.sanyedu.sanylib.mvp.IBasePresenter;
import com.sanyedu.sanylib.mvp.IBaseView;
import com.sanyedu.stufeedback.model.DepartModel;
import com.sanyedu.stufeedback.model.PersonModel;

import java.util.List;

public final class GotoFeedbackContacts {
    public interface IGotoFeedbackUI extends IBaseView {
        void setDepartList(List<DepartModel> departList);
        void setPersonList(List<PersonModel> personList);
    }

    public interface IGoToFeedbackPresenter extends IBasePresenter {
        void getDepart();
        void getPersonOfDepart(String departId);
        void postFeedbackToServer(String feedbackTitle, String feedbackAdress, String feedbackContent, String feedbackDept, String feedbackPersonid, String feedbackPersonname, String feedbackA, String feedbackB, String feedbackC, String toResponsiblename, String toResponsibledept);
        void postFiles(List<String> files);
     }
}
