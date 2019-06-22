package com.sanyedu.stufeedback.activity;

import android.content.Intent;
import android.provider.ContactsContract;
import android.text.format.DateUtils;
import android.widget.TextView;

import com.sanyedu.sanylib.base.SanyBaseActivity;
import com.sanyedu.sanylib.utils.ConstantUtil;
import com.sanyedu.sanylib.utils.SanyDataUtils;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.model.NoticeDetailBean;
import com.sanyedu.stufeedback.mvp.noticedetail.NoticeDetailContacts;
import com.sanyedu.stufeedback.mvp.noticedetail.NoticeDetailPresenter;
import com.sanyedu.stufeedback.utils.StuContantsUtil;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticeDetailActivity extends SanyBaseActivity<NoticeDetailPresenter> implements NoticeDetailContacts.INoticeDetailUI/*, View.OnClickListener */{
    @BindView(R.id.feedback_single_tv)
     TextView titleTv;

    @BindView(R.id.pub_person_tv)
    TextView pubPersonTv;

    @BindView(R.id.notice_date_time_tv)
    TextView dateTv;

    @BindView(R.id.content_tv)
    TextView contentTv;
//    private ImageButton gobackIb;

//    titleTv = findViewById(R.id.feedback_single_tv);
//    contentTv = findViewById(R.id.content_tv);
//    pubPersonTv = findViewById(R.id.pub_person_tv);
//    dateTv = findViewById(R.id.notice_date_time_tv);
//    gobackIb = findViewById(R.id.goback_iv);

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.goback_iv){
//            finish();
//        }
//    }


    @OnClick(R.id.goback_iv)
    public void closePage() {
        finish();
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if(intent != null){
            String id = intent.getStringExtra(StuContantsUtil.ID);
            getPresenter().getNoticeDetail(id);
        }
    }

//    @Override
//    protected void findViews() {
//
//    }

//    @Override
//    protected void setListeners() {
//        gobackIb.setOnClickListener(this);
//    }

    @Override
    protected int getLayout() {
        return R.layout.activity_notice_detail;
    }

    @Override
    public NoticeDetailPresenter onBindPresenter() {
        return new NoticeDetailPresenter(this);
    }

    @Override
    public void setNoticeDetail(NoticeDetailBean detailBean) {
        if(detailBean != null){
            titleTv.setText(detailBean.getTitle());
            contentTv.setText(detailBean.getContent());
            pubPersonTv.setText(detailBean.getPubName());
            String formattedStr = SanyDataUtils.getFormatStr(detailBean.getCreatetime());

            dateTv.setText(formattedStr);
        }
    }
}
