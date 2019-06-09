package com.sanyedu.stufeedback.mvp.login;


import com.sanyedu.sanylib.mvp.IBasePresenter;
import com.sanyedu.sanylib.mvp.IBaseView;

public final class LoginContacts {
    public interface ILoginUI extends IBaseView {
        public void startMain(); //跳转到登录
        public void loginFailure(String msg); //登录失败
    }

    public interface ILoginPresenter extends IBasePresenter {
        public void getToken(String userName, String password, String regFlag);//获取token
        public void getLogin(String userName, String password, String regFlag); //获取登录信息
    }
}
