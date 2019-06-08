package com.sanyedu.sanylib.mvp;

import android.app.ProgressDialog;

import com.sanyedu.sanylib.R;
import com.sanyedu.sanylib.widget.SanyProgressDialog;


public abstract class BaseActivity<P extends IBasePresenter> extends BaseXActivity<P> implements IBaseView {
    // 加载进度框
    private ProgressDialog mProgressDialog;
    
    @Override
    public void showLoading(){
        //TODO:显示loading
        //显示ProgressDialog
        mProgressDialog = new SanyProgressDialog(this, R.style.CustomProgressDialog);
        mProgressDialog.show();
    }
    
    @Override
    public void hideLoading(){
       //TODO:隐藏loading
        if(mProgressDialog != null){
            mProgressDialog.dismiss();
        }
    }
    
    @Override
    public void showToast(String msg){
       //TODO:显示Toast
    }
    
    @Override
    protected void onDestroy() {
        hideLoading();
        super.onDestroy();
    }
}