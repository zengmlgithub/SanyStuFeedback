package com.sanyedu.stufeedback.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.sanyedu.sanylib.base.SanyBaseActivity;
import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.utils.StartUtils;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.mvp.MainInfo.MainContacts;
import com.sanyedu.stufeedback.mvp.MainInfo.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends SanyBaseActivity<MainPresenter> implements MainContacts.IMainUI {
    @BindView(R.id.operator_rl)
    RelativeLayout operatorLayout;

    @OnClick({R.id.all_feedback_iv,R.id.notice_iv,R.id.my_feedback_iv,R.id.feedback_my_iv,R.id.add_civ})
    public void myOnclick(View view){
        if (view.getId() == R.id.all_feedback_iv){
//            StartUtils.startActivity();
            SanyLogs.i("goto all feedback");
        }else if(view.getId() == R.id.notice_iv){
//            StartUtils.startActivity();
//            SanyLogs.i("goto all notice");
            StartUtils.startActivity(MainActivity.this,NoticeActivity.class);
        }else if(view.getId() == R.id.my_feedback_iv){
//            StartUtils.startActivity();
            SanyLogs.i("goto all myfeedback");
        }else if(view.getId() == R.id.feedback_my_iv){
//            StartUtils.startActivity();
            SanyLogs.i("goto all feedbackmy");
        }else if (view.getId() == R.id.add_civ){
            StartUtils.startActivity(MainActivity.this,GotoFeedbackActivity.class);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        ButterKnife.bind(R.layout.layout_main_operator,operatorLayout);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }


    @Override
    public MainPresenter onBindPresenter() {
        return new MainPresenter(this);
    }
}
