package com.sanyedu.stufeedback.mvp.modifyinfo;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.model.BaseModel;
import com.sanyedu.sanylib.model.BaseModelCallback;
import com.sanyedu.sanylib.mvp.BasePresenter;
import com.sanyedu.sanylib.okhttp.OkHttpUtils;
import com.sanyedu.sanylib.utils.CheckUtils;
import com.sanyedu.sanylib.utils.ErrorUtils;
import com.sanyedu.sanylib.utils.HttpUtil;
import com.sanyedu.sanylib.utils.ToastUtil;
import com.sanyedu.stufeedback.utils.StuHttpUtil;

import okhttp3.Call;

public class ModifyInfoPresenter extends BasePresenter<ModifyInfoContacts.IModifyInfoUI> implements ModifyInfoContacts.IModifyInfoPresenter {
    public ModifyInfoPresenter(@NonNull ModifyInfoContacts.IModifyInfoUI view) {
        super(view);
    }


    @Override
    public void ModifyObj(String type, String obj) {

        if(!CheckUtils.isParasLegality(type,obj)){
            SanyLogs.e("params is null ,return");
            return;
        }

        String url = HttpUtil.getPort(StuHttpUtil.UPDATE_PERSON_OBJ_PORT);
        OkHttpUtils
                .post()
                .url(url)
                .addParams(StuHttpUtil.UpdateObj.TYPE, type)
                .addParams(StuHttpUtil.UpdateObj.TE_USER,obj)
                .build()
                .execute(
                        new BaseModelCallback<String>(){

                            @Override
                            public void onError(Call call, Exception e, int id) {
                                SanyLogs.e("string:" + e.toString());
                            }

                            @Override
                            public void onResponse(BaseModel<String> response, int id) {
                                if (response == null){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                SanyLogs.i(response.toString());

                                String code = response.getCode();
                                if (TextUtils.isEmpty(code)){
                                    ToastUtil.showLongToast(ErrorUtils.SERVER_ERROR);
                                    return;
                                }

                                if (!"1".equals(code)){
                                    ToastUtil.showLongToast(response.getInfo());
                                    return;
                                }else{
                                    getView().showModifySuccess();
                                }
                            }
                        }
                );
    }
}
