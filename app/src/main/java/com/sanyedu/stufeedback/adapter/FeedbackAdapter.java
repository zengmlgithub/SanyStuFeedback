package com.sanyedu.stufeedback.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class FeedbackAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private TabLayout tabLayout;

    public FeedbackAdapter(FragmentManager fm, Context context, List<Fragment> fragmentList, List<String> titleList, TabLayout tabLayout){
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.titleList = titleList;
        this.tabLayout = tabLayout;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList == null? null : fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return titleList == null ? 0 : titleList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
       return titleList.get(position);
    }


}
