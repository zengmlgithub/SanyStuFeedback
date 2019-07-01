package com.sanyedu.stufeedback.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sanyedu.sanylib.base.SanyBaseActivity;
import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.share.SpHelper;
import com.sanyedu.sanylib.utils.StartUtils;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.model.StudentModel;
import com.sanyedu.stufeedback.mvp.MainInfo.MainContacts;
import com.sanyedu.stufeedback.mvp.MainInfo.MainPresenter;
import com.sanyedu.stufeedback.utils.StuContantsUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends SanyBaseActivity<MainPresenter> implements MainContacts.IMainUI {

    @BindView(R.id.operator_rl)
    RelativeLayout operatorLayout;


    @OnClick({R.id.all_feedback_iv,R.id.notice_iv,R.id.my_feedback_iv,R.id.feedback_my_iv,R.id.add_civ,R.id.head_rl})
    public void myOnclick(View view){
        if (view.getId() == R.id.all_feedback_iv){
            SanyLogs.i("goto all feedback");
            StartUtils.startActivity(MainActivity.this,AllFeedbackActivity.class);
        }else if(view.getId() == R.id.notice_iv){
            StartUtils.startActivity(MainActivity.this,NoticeActivity.class);
        }else if(view.getId() == R.id.my_feedback_iv){
            StartUtils.startActivity(MainActivity.this,MyFeedbackActivity.class);
        }else if(view.getId() == R.id.feedback_my_iv){
            StartUtils.startActivity(MainActivity.this,FeedbackMyActivity.class);
            SanyLogs.i("goto all feedbackmy");
        }else if (view.getId() == R.id.add_civ){
            StartUtils.startActivity(MainActivity.this,GotoFeedbackActivity.class);
        }else if(view.getId() == R.id.head_rl){
            StartUtils.startActivity(MainActivity.this,PersonalActivity.class);
            SanyLogs.i("goto all personal page");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        ButterKnife.bind(R.layout.layout_main_operator,operatorLayout);
        initHead();
    }

    private void initHead() {
        TextView headTv = findViewById(R.id.head_tv);
        StudentModel studentModel = SpHelper.getObj(StuContantsUtil.STUINFO);
        if(studentModel != null) {
            String name = studentModel.getStuName();
            headTv.setText(name);
        }
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
