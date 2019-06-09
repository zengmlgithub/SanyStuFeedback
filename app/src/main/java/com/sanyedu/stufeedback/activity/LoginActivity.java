package com.sanyedu.stufeedback.activity;

import android.widget.EditText;


import com.sanyedu.sanylib.base.SanyBaseActivity;
import com.sanyedu.sanylib.utils.StartUtils;
import com.sanyedu.sanylib.utils.ToastUtil;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.mvp.login.LoginContacts;
import com.sanyedu.stufeedback.mvp.login.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends SanyBaseActivity<LoginPresenter> implements LoginContacts.ILoginUI/*, View.OnClickListener*/{
    @BindView(R.id.username_et)
     EditText usernameEt;
    @BindView(R.id.password_et)
     EditText passwordEt;

    @OnClick(R.id.commit_btn)
    public void commit(){
        String username = usernameEt.getText().toString();
        String password = passwordEt.getText().toString();
        getPresenter().getToken(username,password, "2");
        showLoading();
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter onBindPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void startMain() {
        StartUtils.startActivity(LoginActivity.this, MainActivity.class);
    }

    @Override
    public void loginFailure(String msg) {
        hideLoading();
        ToastUtil.showLongToast(msg);
    }
}
