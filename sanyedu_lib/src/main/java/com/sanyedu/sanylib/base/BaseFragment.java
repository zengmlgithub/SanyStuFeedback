package com.sanyedu.sanylib.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanyedu.sanylib.broadcastreceiver.NetBroadcastReceiver;
import com.sanyedu.sanylib.mvp.IBasePresenter;
import com.sanyedu.sanylib.mvp.IBaseXView;


/**
 * BaseFragment是所有Fragment的基类，把一些公共的方法放到里面，如基础样式设置，权限封装，网络状态监听等
 * <p>
 * Created by 邹峰立 on 2017/10/19.
 */
public abstract class BaseFragment<P extends IBasePresenter>  extends Fragment implements NetBroadcastReceiver.NetChangeListener, IBaseXView {
    public static NetBroadcastReceiver.NetChangeListener netEvent;// 网络状态改变监听事件

    private P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(),container,false);
        init(view);
        return view;
    }

    protected abstract int getLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        netEvent = null;
    }

    // 抽象 - 初始化方法，可以对控件进行初始化，也可以对数据进行初始化
    protected abstract void init(View view);

    /**
     * 网络状态改变时间监听
     *
     * @param netWorkState true有网络，false无网络
     */
    @Override
    public void onNetChange(boolean netWorkState) {
    }

    public P getPresenter() {
        if (mPresenter == null) {
            mPresenter = onBindPresenter();
        }
        return mPresenter;
    }

    public abstract P onBindPresenter();

    @Override
    public Activity getSelfActivity() {
        return getActivity();
    }
}
