package com.sanyedu.stufeedback.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;


import com.sanyedu.sanylib.base.SanyBaseActivity;
import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.mvp.IBaseXPresenter;
import com.sanyedu.sanylib.share.SpHelper;
import com.sanyedu.sanylib.utils.ConstantUtil;
import com.sanyedu.sanylib.utils.StartUtils;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.model.StudentModel;
import com.sanyedu.stufeedback.utils.StuContantsUtil;
import com.sanyedu.stufeedback.utils.UserInfoHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstActivity extends SanyBaseActivity {
    @BindView(R.id.next_btn)
    TextView skipButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);

        //设置全屏

        mHandler.post(myRunnale);


    }

    @Override
    protected int getLayout() {
        return R.layout.activity_first;
    }

    private void goToNext(){
        Class<?> clazz = UserInfoHelper.isLogined(StuContantsUtil.STUINFO)? MainActivity.class:LoginActivity.class;
        StartUtils.startActivity(FirstActivity.this,clazz);
        finish();
    }


    private int limitTime = 4;
    private Handler mHandler = new Handler();
    private Runnable myRunnale = new Runnable() {
        @Override
        public void run() {
            limitTime--;
            SanyLogs.i("current limitTime:" + limitTime);
            if(limitTime>0){
                mHandler.postDelayed(myRunnale,1000);
                String skipTxt = getResources().getString(R.string.skip_txt);
                String secondTxt = getResources().getString(R.string.second_txt);
                skipButton.setText(skipTxt + limitTime + "s");
            }else{
                mHandler.removeCallbacks(myRunnale);
                goToNext();

            }
        }
    };

    @Override
    public IBaseXPresenter onBindPresenter() {
        return null;
    }

    @OnClick(R.id.next_btn)
    public void goNext(){
        if (mHandler != null){
            mHandler.removeCallbacks(myRunnale);
        }
        goToNext();
    }
}
