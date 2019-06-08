package com.sanyedu.sanylib.mvp;

import android.support.annotation.NonNull;

public abstract class BasePresenter<V extends IBaseView> extends BaseXPresenter<V> implements IBasePresenter{

    public BasePresenter(@NonNull V view) {
        super(view);
    }
    
      @Override
    public void cancel(@NonNull Object tag) {
        //TODO:取消网络请求
    }

    @Override
    public void cancelAll() {
       //TODO:取消所有网络请求
    }
}