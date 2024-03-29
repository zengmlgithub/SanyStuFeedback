package com.sanyedu.sanylib.mvp;

public interface IBaseView extends IBaseXView {

   /**
    * 显示正在加载 view
    */
    void showLoading();
    
    /**
     * 关闭正在加载 view
     */
    void hideLoading();

}