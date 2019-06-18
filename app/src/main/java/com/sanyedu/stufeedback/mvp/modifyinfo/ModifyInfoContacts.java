package com.sanyedu.stufeedback.mvp.modifyinfo;

import com.sanyedu.sanylib.mvp.IBasePresenter;
import com.sanyedu.sanylib.mvp.IBaseView;

public final class ModifyInfoContacts {
    public interface IModifyInfoUI extends IBaseView{
       void showModifySuccess(); //修改成功后需要重新获取个人数据
    }

    public interface IModifyInfoPresenter extends IBasePresenter{
        void ModifyObj(String type, String obj);
    }
}
