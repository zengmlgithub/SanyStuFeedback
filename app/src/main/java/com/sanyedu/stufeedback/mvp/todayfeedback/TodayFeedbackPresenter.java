package com.sanyedu.stufeedback.mvp.todayfeedback;

import android.support.annotation.NonNull;

import com.sanyedu.sanylib.mvp.BasePresenter;


public class TodayFeedbackPresenter extends BasePresenter<TodayFeedbackContacts.ITodayFeedbackUI> implements TodayFeedbackContacts.ITodayFeedbackPresenter {
    public TodayFeedbackPresenter(@NonNull TodayFeedbackContacts.ITodayFeedbackUI view) {
        super(view);
    }
}
