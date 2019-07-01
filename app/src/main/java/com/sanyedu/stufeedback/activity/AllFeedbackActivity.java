package com.sanyedu.stufeedback.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.sanyedu.sanylib.base.SanyBaseActivity;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.adapter.FeedbackAdapter;
import com.sanyedu.stufeedback.fragment.MainHasModifiedFragment;
import com.sanyedu.stufeedback.fragment.MainNeedModifyFragment;
import com.sanyedu.stufeedback.mvp.todayfeedback.TodayFeedbackContacts;
import com.sanyedu.stufeedback.mvp.todayfeedback.TodayFeedbackPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AllFeedbackActivity extends SanyBaseActivity<TodayFeedbackPresenter> implements TodayFeedbackContacts.ITodayFeedbackUI {
    @BindView(R.id.title_tl)
    TabLayout tabLayout;

    @BindView(R.id.vp_content)
    ViewPager viewPager;

    private List<Fragment> fragments;
    private ArrayList<String> titles;
    private FeedbackAdapter feedbackAdapter;


    @OnClick(R.id.goback_ib)
    public void goBack() {
        finish();
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_main_fk;
    }

    @Override
    protected void initData() {

        ButterKnife.bind(this);


        fragments = new ArrayList<>();
        fragments.add(new MainNeedModifyFragment());
        fragments.add(new MainHasModifiedFragment());

        titles = new ArrayList<>();
        titles.add("待整改");
        titles.add("已整改");

        feedbackAdapter = new FeedbackAdapter(getSupportFragmentManager(), this, fragments, titles, tabLayout);
        viewPager.setAdapter(feedbackAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public TodayFeedbackPresenter onBindPresenter() {
        return new TodayFeedbackPresenter(this);
    }


}
