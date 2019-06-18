package com.sanyedu.stufeedback.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;


import com.sanyedu.sanylib.base.SanyBaseActivity;
import com.sanyedu.sanylib.model.RecordsBean;
import com.sanyedu.sanylib.utils.StartUtils;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.adapter.FeedbackAdapter;
import com.sanyedu.stufeedback.fragment.BaseMyFeedbackFragment;
import com.sanyedu.stufeedback.mvp.myfeedback.MyFeedbackContacts;
import com.sanyedu.stufeedback.mvp.myfeedback.MyFeedbackPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的反馈
 */
public class MyFeedbackActivity extends SanyBaseActivity<MyFeedbackPresenter> implements MyFeedbackContacts.IMyFeedbackUI/*, View.OnClickListener*/ {

    private List<Fragment> fragments;
    private ArrayList<String> titles;
    private FeedbackAdapter feedbackAdapter;

    @BindView(R.id.type_tl)
    TabLayout tabLayout;

    @BindView(R.id.vp_content)
    ViewPager viewPager;

    @OnClick(R.id.goback_iv)
    public void closePage() {
        finish();
    }


    @OnClick(R.id.go_feedback_tv)
    public void gotoFeedback() {
        StartUtils.startActivity(this, GotoFeedbackActivity.class);
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        BaseMyFeedbackFragment submitedFragment = BaseMyFeedbackFragment.newInstance("1");
        BaseMyFeedbackFragment checkedFragment = BaseMyFeedbackFragment.newInstance("2");
        BaseMyFeedbackFragment waittingFragment = BaseMyFeedbackFragment.newInstance("3");
        BaseMyFeedbackFragment finishFragment = BaseMyFeedbackFragment.newInstance("4");

        fragments.add(submitedFragment);
        fragments.add(checkedFragment);
        fragments.add(waittingFragment);
        fragments.add(finishFragment);

        titles = new ArrayList<>();

        titles.add("已提交");
        titles.add("已审核");
        titles.add("整改中");
        titles.add("已完成");

        feedbackAdapter = new FeedbackAdapter(getSupportFragmentManager(), this, fragments, titles, tabLayout);
        viewPager.setAdapter(feedbackAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_feedback;
    }

    @Override
    public MyFeedbackPresenter onBindPresenter() {
        return new MyFeedbackPresenter(this);
    }

    @Override
    public void setMyFeedbacks(List<RecordsBean> recordsList) {

    }
}
