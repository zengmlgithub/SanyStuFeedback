package com.sanyedu.stufeedback.mvp.MyFeedbackFragment;

import android.support.annotation.NonNull;

import com.sanyedu.sanylib.mvp.IBasePresenter;
import com.sanyedu.sanylib.mvp.IBaseView;
import com.sanyedu.stufeedback.model.Records;

import java.util.List;

public final class CommonFeedbackFragmentContacts {
    public interface ICommonFeedbackFragmentUI extends IBaseView {
        void setFeebacks(List<Records> recordsList, int maxCount);
        void showNoMoreList();
        void showError(@NonNull String serverErrorMsg);
    }

    public interface ICommonFeedbacFragmentPresenter extends IBasePresenter {
         void getFeedbacks(@NonNull String startPage, @NonNull String everyPage, @NonNull String id, @NonNull String type);
    }
}
