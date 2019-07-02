package com.sanyedu.stufeedback.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;


import com.sanyedu.sanylib.base.SanyBaseActivity;
import com.sanyedu.sanylib.utils.StartUtils;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.adapter.FeedbackAdapter;
import com.sanyedu.stufeedback.fragment.BaseFeedbackMyFragment;
import com.sanyedu.stufeedback.model.Records;
import com.sanyedu.stufeedback.mvp.myfeedback.MyFeedbackContacts;
import com.sanyedu.stufeedback.mvp.myfeedback.MyFeedbackPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 反馈我的
 */
public class FeedbackMyActivity extends SanyBaseActivity implements MyFeedbackContacts.IMyFeedbackUI {
    @BindView(R.id.type_tl)
    TabLayout tabLayout;

    @BindView(R.id.vp_content)
    ViewPager viewPager;

    private List<Fragment> fragments;
    private ArrayList<String> titles;
    private FeedbackAdapter feedbackAdapter;


    @Override
    protected void initData() {
        ButterKnife.bind(this);
        fragments = new ArrayList<>();

        BaseFeedbackMyFragment submitedFragment = BaseFeedbackMyFragment.newInstance("1");
        BaseFeedbackMyFragment checkedFragment = BaseFeedbackMyFragment.newInstance("2");
        BaseFeedbackMyFragment waittingFragment = BaseFeedbackMyFragment.newInstance("3");
        BaseFeedbackMyFragment finishFragment =  BaseFeedbackMyFragment.newInstance("4");

        fragments.add(submitedFragment);
        fragments.add(checkedFragment);
        fragments.add(waittingFragment);
        fragments.add(finishFragment);

        titles = new ArrayList<>();
        titles.add("待整改");
        titles.add("整改中");
        titles.add("已整改");
        titles.add("已关闭");

        feedbackAdapter = new FeedbackAdapter(getSupportFragmentManager(), this, fragments, titles,tabLayout);
        viewPager.setAdapter(feedbackAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }


    @OnClick(R.id.goback_iv)
    public void close(){
        finish();
    }

    @OnClick(R.id.go_feedback_tv)
    public void goFeebackOnClick() {
        StartUtils.startActivity(this,GotoFeedbackActivity.class);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_feedback_my;
    }

    @Override
    public MyFeedbackPresenter onBindPresenter() {
        return new MyFeedbackPresenter(this);
    }

    @Override
    public void setMyFeedbacks(List<Records> recordsList) {

    }
}
