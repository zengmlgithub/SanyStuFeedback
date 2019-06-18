package com.sanyedu.stufeedback.mvp.MainInfo;

import android.support.annotation.NonNull;

import com.sanyedu.sanylib.mvp.BasePresenter;

public class MainPresenter extends BasePresenter<MainContacts.IMainUI> implements MainContacts.IMainPresenter {
    public MainPresenter(@NonNull MainContacts.IMainUI view) {
        super(view);
    }
}
