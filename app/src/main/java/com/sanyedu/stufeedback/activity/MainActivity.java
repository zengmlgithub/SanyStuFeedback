package com.sanyedu.stufeedback.activity;

import android.os.Bundle;

import com.sanyedu.sanylib.base.SanyBaseActivity;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.mvp.MainInfo.MainContacts;
import com.sanyedu.stufeedback.mvp.MainInfo.MainPresenter;

import butterknife.ButterKnife;

public class MainActivity extends SanyBaseActivity<MainPresenter> implements MainContacts.IMainUI {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    public MainPresenter onBindPresenter() {
        return new MainPresenter(this);
    }
}
