package com.sanyedu.sanylib.base;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;

import com.sanyedu.sanylib.broadcastreceiver.NetBroadcastReceiver;
import com.sanyedu.sanylib.mvp.BaseActivity;
import com.sanyedu.sanylib.mvp.IBasePresenter;
import com.sanyedu.sanylib.utils.ActivityUtil;
import com.sanyedu.sanylib.utils.ConstantUtil;


/**
 * BaseActivity是所有Activity的基类，把一些公共的方法放到里面，如基础样式设置，权限封装，网络状态监听等
 * <p>
 * Created by 邹峰立 on 2018/3/5.
 */
public abstract class SanyBaseActivity<P extends IBasePresenter> extends BaseActivity<P> implements NetBroadcastReceiver.NetChangeListener  {
    public static NetBroadcastReceiver.NetChangeListener netEvent;// 网络状态改变监听事件

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
//        if (getSupportActionBar() != null)
//            getSupportActionBar().hide();

//        // 沉浸效果
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            // 透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

        // 添加到Activity工具类
        ActivityUtil.getInstance().addActivity(this);

        // 初始化netEvent
        netEvent = this;

        setContentView(getLayout());
        // 执行初始化方法
        initData();
    }

    // 抽象 - 初始化方法，可以对数据进行初始化
    protected abstract void initData();

    protected abstract int getLayout();

    @Override
    protected void onResume() {
        super.onResume();
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();

        //字体缩放
        configuration.fontScale = ConstantUtil.TEXTVIEWSIZE;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    @Override
    protected void onDestroy() {
        // Activity销毁时，提示系统回收
        // System.gc();
        netEvent = null;
        // 移除Activity
        ActivityUtil.getInstance().removeActivity(this);
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 点击手机上的返回键，返回上一层
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 移除Activity
            ActivityUtil.getInstance().removeActivity(this);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 权限检查方法，false代表没有该权限，ture代表有该权限
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    /**
     * 权限请求方法
     */
    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param permissions  权限组
     * @param grantResults 结果集
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doRequestPermissionsResult(requestCode, grantResults);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param grantResults 结果集
     */
    public void doRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
    }

    /**
     * 网络状态改变时间监听
     *
     * @param netWorkState true有网络，false无网络
     */
    @Override
    public void onNetChange(boolean netWorkState) {
    }
}