package com.sanyedu.stufeedback.mvp.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;


import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.model.BaseModel;
import com.sanyedu.sanylib.model.BaseModelCallback;
import com.sanyedu.sanylib.model.JsonGenericsSerializator;
import com.sanyedu.sanylib.mvp.BasePresenter;
import com.sanyedu.sanylib.okhttp.OkHttpUtils;
import com.sanyedu.sanylib.okhttp.callback.GenericsCallback;
import com.sanyedu.sanylib.share.SpHelper;

import com.sanyedu.sanylib.utils.HttpUtil;
import com.sanyedu.sanylib.utils.MD5Utils;
import com.sanyedu.stufeedback.model.StudentModel;
import com.sanyedu.stufeedback.model.TokenModel;
import com.sanyedu.stufeedback.utils.StuContantsUtil;

import java.util.List;

import okhttp3.Call;

public class LoginPresenter extends BasePresenter<LoginContacts.ILoginUI> implements LoginContacts.ILoginPresenter {

    public LoginPresenter(@NonNull LoginContacts.ILoginUI view) {
        super(view);
    }


    @Override
    public void getToken(final String userName, final String password, final String regFlag) {
        String url = HttpUtil.getPort(HttpUtil.AUTH_PORT);
//        String tokenValue = "Bearer " + SpHelper.getString(ConstantUtil.TOKEN);
        OkHttpUtils
                .post()
                .url(url)
//                .addHeader(ConstantUtil.AUTHORIZATION,tokenValue)
                .addParams("userName", userName)
                .addParams("password", MD5Utils.getMD5(password))
                .addParams("loginFlag", regFlag)
                .build()
                .execute(new GenericsCallback<TokenModel>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SanyLogs.e("testUrl:" + e.getMessage());
                        getView().loginFailure("登录失败");
                    }

                    @Override
                    public void onResponse(TokenModel tokenModel, int id) {
                        SanyLogs.i("getToken:" + tokenModel.toString());
                        if (tokenModel != null) {
                            String code = tokenModel.getCode();
                            if (!TextUtils.isEmpty(code)) {
                                if (HttpUtil.SUCCESS.equals(code)) {
                                    SpHelper.putString(StuContantsUtil.TOKEN, tokenModel.getToken());
                                    getLogin(userName, password, regFlag);
                                } else if (HttpUtil.ERROR_ACCOUNT.equals(code)) {
                                    //TODO:error
                                    SanyLogs.i("getToken:error");
                                    getView().loginFailure(HttpUtil.ERROR_SERVER);
                                }
                            }
                        }

                    }
                });
    }

    @Override
    public void getLogin(String userName, String password, String regFlag) {
        String url = HttpUtil.getPort(HttpUtil.LOGIN_PORT);
        String tokenValue = "Bearer " + SpHelper.getString(StuContantsUtil.TOKEN);
        SanyLogs.i("getLogin~~~tokenValue:" + tokenValue);
        OkHttpUtils
                .post()
                .url(url)
                .addHeader(StuContantsUtil.AUTHORIZATION, tokenValue)
                .addParams("userName", userName)
                .addParams("password", MD5Utils.getMD5(password))
                .addParams("loginFlag", regFlag)
                .build()
                .execute(
                        new BaseModelCallback<List<StudentModel>>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                                getView().loginFailure(HttpUtil.ERROR_SERVER);
                            }

                            @Override
                            public void onResponse(BaseModel<List<StudentModel>> response, int id) {
                                SanyLogs.i("userInfo:" + response.getObj().get(0));
                                try {
                                    StudentModel userInfo = response.getObj().get(0);
                                    SpHelper.putObj(StuContantsUtil.STUINFO,userInfo);
                                    getView().startMain();
                                }catch (Exception e){
                                    SanyLogs.i(e.toString());
                                    getView().loginFailure(HttpUtil.ERROR_SERVER);
                                }

                            }
                        }
                );
    }

    private void saveUser(StudentModel userInfo) {
        //TODO:saveUser
    }


}
