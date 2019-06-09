package com.sanyedu.stufeedback.activity;

import android.net.Uri;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.sanyedu.sanylib.base.SanyBaseActivity;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.mvp.personal.MainMyContacts;
import com.sanyedu.stufeedback.mvp.personal.MainMyPresenter;

import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 个人中心
 */
public class PersonalActivity extends SanyBaseActivity<MainMyPresenter> implements MainMyContacts.IMainMyUI {

    @BindView(R.id.name_tv)
    TextView nameTv;

    @BindView(R.id.depart_tv)
    TextView departTv;

    @BindView(R.id.feedback_main_number_tv)
    TextView feedbackMyTv;

    @BindView(R.id.main_fk_number_tv)
    TextView myFeedbackTv;


    @BindView(R.id.tel_tv)
    TextView telTv;

    @BindView(R.id.email_tv)
    TextView emailTv;

    @BindView(R.id.card_tv)
    TextView cardTv;

    @BindView(R.id.pos_tv)
    TextView posTv;


    @BindView(R.id.fk_main_rl)
    RelativeLayout feedbackMyRl;

    @BindView(R.id.main_fk_rl)
    RelativeLayout myFeedbackRl;

    @BindView(R.id.head_iv)
    ImageView headIv;

    @BindView(R.id.settings_ib)
    ImageButton settingIb;

    @BindView(R.id.setting_rl)
    ImageView settingRl;

    @BindView(R.id.modify_pwd_ib)
    ImageButton modifywIb;

    @BindView(R.id.logout_ib)
    ImageButton logoutIb;

    @BindView(R.id.email_rl)
    RelativeLayout emailRl;

    @BindView(R.id.tel_rl)
    RelativeLayout telRl;


    @Override
    protected void initData() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return  R.layout.activity_personal;
    }

    @Override
    public MainMyPresenter onBindPresenter() {
        return new MainMyPresenter(this);
    }

    @Override
    public InputStream openInputStream(Uri uri) {
        return null;
    }

    @Override
    public void showHeadImage() {

    }

    @Override
    public void startActivityWithResult(Uri uri, Uri cropUri) {

    }
}
