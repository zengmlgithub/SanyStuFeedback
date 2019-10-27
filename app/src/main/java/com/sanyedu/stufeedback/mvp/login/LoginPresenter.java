package com.sanyedu.stufeedback.mvp.login;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;


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
import com.sanyedu.stufeedback.utils.StuErrorUtil;
import com.sanyedu.stufeedback.utils.StuHttpUtil;

import java.util.List;

import okhttp3.Call;

public class LoginPresenter extends BasePresenter<LoginContacts.ILoginUI> implements LoginContacts.ILoginPresenter {

    public LoginPresenter(@NonNull LoginContacts.ILoginUI view) {
        super(view);
    }


    @Override
    public void getToken(final String userName, final String password, final String loginFlag) {
        String url = HttpUtil.getPort(StuHttpUtil.AUTH_PORT);
//        String tokenValue = "Bearer " + SpHelper.getString(ConstantUtil.TOKEN);


        String passwordMD5 = MD5Utils.getMD5(password);
        SanyLogs.i("userName:" + userName + ",passwordMD5:" + passwordMD5 + ",loginFlag:" + loginFlag);
        OkHttpUtils
                .post()
                .url(url)
                .addParams("userName", userName)
                .addParams("password", passwordMD5)
                .addParams("loginFlag", loginFlag)
                .build()
                .execute(new GenericsCallback<TokenModel>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        SanyLogs.e("testUrl:" + e.getMessage());
                        getView().loginFailure(StuErrorUtil.SERVER_ERROR);
                    }

                    @Override
                    public void onResponse(TokenModel tokenModel, int id) {
                        if (tokenModel != null) {
                            SanyLogs.i(tokenModel.toString());
                            String code = tokenModel.getCode();
                            if (!TextUtils.isEmpty(code)) {
                                if (StuHttpUtil.SUCCESS.equals(code)) {
                                    SpHelper.putString(StuContantsUtil.TOKEN, tokenModel.getToken());
                                    getLogin(userName, password, loginFlag);
                                } else if (StuHttpUtil.ERROR_ACCOUNT.equals(code)) {
                                    //TODO:error
                                    SanyLogs.i("getToken:error-----");
                                    getView().loginFailure(StuErrorUtil.ACCOUNT_ERROR);
                                }
                            }else{
                                SanyLogs.e("tokenModel is null" );
                                getView().loginFailure(StuErrorUtil.SERVER_ERROR);
                            }
                        }

                    }
                });
    }

    @Override
    public void getLogin(String userName, String password, String loginFlag) {
        String url = HttpUtil.getPort(StuHttpUtil.LOGIN_PORT);
//        String tokenValue = "Bearer " + SpHelper.getString(StuContantsUtil.TOKEN);
//        SanyLogs.i("getLogin~~~tokenValue:" + tokenValue);
        OkHttpUtils
                .post()
                .url(url)
                .addParams("userName", userName)
                .addParams("password", MD5Utils.getMD5(password))
                .addParams("loginFlag", loginFlag)
                .build()
                .execute(
                        new BaseModelCallback<List<StudentModel>>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                                getView().loginFailure(StuHttpUtil.ERROR_SERVER);
                            }

                            @Override
                            public void onResponse(BaseModel<List<StudentModel>> response, int id) {
                                if(response != null){
                                    List<StudentModel> studentModels = response.getObj();
                                    if (studentModels != null && studentModels.size() > 0){
                                        try {
                                            StudentModel userInfo =studentModels.get(0);
                                            SanyLogs.i(userInfo.toString());
                                            SpHelper.putObj(StuContantsUtil.STUINFO,userInfo);
                                            getView().startMain();
                                        }catch (Exception e){
                                            SanyLogs.i(e.toString());
                                            getView().loginFailure(StuHttpUtil.ERROR_SERVER);
                                        }
                                    }else{
                                        getView().loginFailure(StuHttpUtil.ERROR_SERVER);
                                        SanyLogs.i("studentModels is null");
                                    }
                                }else{
                                    getView().loginFailure(StuHttpUtil.ERROR_SERVER);
                                    SanyLogs.i("response is null");
                                }
                            }
                        }
                );
    }

    private void saveUser(StudentModel userInfo) {
        //TODO:saveUser
    }
}
