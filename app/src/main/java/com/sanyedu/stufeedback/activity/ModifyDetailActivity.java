package com.sanyedu.stufeedback.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.sanyedu.sanylib.base.SanyBaseActivity;
import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.utils.StartUtils;
import com.sanyedu.sanylib.utils.ToastUtil;
import com.sanyedu.sanylib.widget.CloseFeedbackDialog;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.adapter.ModifyDetailAdapter;
import com.sanyedu.stufeedback.model.DetailModel;
import com.sanyedu.stufeedback.mvp.modifieddetail.ModifiedDetailContacts;
import com.sanyedu.stufeedback.mvp.modifieddetail.ModifiedDetailPresenter;
import com.sanyedu.stufeedback.utils.StuContantsUtil;
import com.sanyedu.stufeedback.utils.UserInfoHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 整改详情
 */
public class ModifyDetailActivity extends SanyBaseActivity<ModifiedDetailPresenter> implements ModifiedDetailContacts.IModifiedDetailUI/*, View.OnClickListener*/ {
    @BindView(R.id.fk_single_title)
    TextView titleTv;  //标题

    @BindView(R.id.fk_single_detail)
    TextView contentTv;

    @BindView(R.id.fk_single_pos)
    TextView addressTv;

    @BindView(R.id.date_tv)
    TextView dateTv;
    @BindView(R.id.detail_1)
    ImageView photo1Iv;

    @BindView(R.id.detail_2)
    ImageView photo2Iv;

    @BindView(R.id.detail_3)
    ImageView photo3Iv;

    @BindView(R.id.fk_info_rv)
    RecyclerView recyclerView;

    @BindView(R.id.operator_rl)
    RelativeLayout opeartorRl;

    TextView modifyTv;
    TextView closeTv;

    //feedbackId号
    private String feedbackId = "";

    @BindView(R.id.modify_fk_ib)
    ImageButton modifyFkIb;

    @OnClick(R.id.modify_fk_ib)
    public void setVsibleOfOperator(){
        if(opeartorRl.getVisibility() == View.VISIBLE){
            opeartorRl.setVisibility(View.GONE);
        }else{
            opeartorRl.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.goback_ib)
    public void goback(){
        finish();
    }

    private ModifyDetailAdapter adapter;


    @Override
    protected void initData() {
        ButterKnife.bind(this);

        initFeedbackId();
//        initOperator();

        modifyTv = opeartorRl.findViewById(R.id.modify_pwd_tv);
        closeTv = opeartorRl.findViewById(R.id.logout_tv);
        modifyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartUtils.startActivity(ModifyDetailActivity.this,ModifyChangeActivity.class,feedbackId);
            }
        });

        closeTv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                finish();
                showCloaseDialog();
            }
        });

        adapter = new ModifyDetailAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        showLoading();
        getPresenter().getDetail(feedbackId);
    }

    private void initFeedbackId() {
        Intent intent = getIntent();
        if(intent != null){
            feedbackId = intent.getStringExtra(StuContantsUtil.ID);
            SanyLogs.i("modifyDetail----feedbackId:" + feedbackId);
        }
    }

    private void initOperator(String rectiStatus) {
        if("3".equals(rectiStatus) || "4".equals(rectiStatus)){
            modifyFkIb.setVisibility(View.VISIBLE);
        }else{
            modifyFkIb.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_fk_detail;
    }

    @Override
    public ModifiedDetailPresenter onBindPresenter() {
        return new ModifiedDetailPresenter(this);
    }


    @Override
    public void setDetail(DetailModel bean) {
        SanyLogs.i(bean.toString());
        if (bean != null) {
            titleTv.setText(bean.getFeedbackTitle());
            contentTv.setText(bean.getFeedbackContent());
            addressTv.setText(bean.getFeedbackAdress());
            dateTv.setText(bean.getFeedbackPubtime());

            if (adapter != null) {
                adapter.setList(bean.getDetailedList());
            }
            String rectiStatus = bean.getRectiStatus();
            SanyLogs.i("ModifyDetailActivity~~~~" + rectiStatus);
            initOperator(rectiStatus);
        }
        hideLoading();
    }

    @Override
    public void getDetailFailure(String msg) {
        hideLoading();
        ToastUtil.showLongToast(msg);
    }

    @Override
    public void getDetailSuccess() {
        closeDialog();
        hideLoading();
    }

    @Override
    public void modifySuccess(){
        closeDialog();
        hideLoading();
        getPresenter().getDetail(feedbackId);
    }

    private CloseFeedbackDialog closeFeedbackDialog;
    private void showCloaseDialog(){
        if(closeFeedbackDialog == null){
            closeFeedbackDialog = new CloseFeedbackDialog(ModifyDetailActivity.this, R.style.sany_dialog, new CloseFeedbackDialog.OnClickListener() {
                @Override
                public void onPositive(Dialog dialog, boolean cancel) {
                    closeFeedback(closeFeedbackDialog.getContent());
                    dialog.dismiss();
                }

                @Override
                public void onNevige(Dialog dialog, boolean confirm) {
                    //关闭
                    dialog.dismiss();
                }
            });
        }

        if(!closeFeedbackDialog.isShowing()){
            closeFeedbackDialog.show();
        }
    }

    private void closeDialog(){
        if (closeFeedbackDialog != null && closeFeedbackDialog.isShowing()){
            closeFeedbackDialog.dismiss();
        }
    }

    //提交内容
    private void closeFeedback(String feedbackContent){
        if(TextUtils.isEmpty(feedbackContent)){
            ToastUtil.showLongToast("请输入内容");
            return;
        }

        String feedbakcPerid = UserInfoHelper.getPersonId();
        String feedbackPername = UserInfoHelper.getPersonName();
        String feedbackPerdept = UserInfoHelper.getPersonDept();

        showLoading();
        getPresenter().closeFeedback(feedbackId,feedbackContent,feedbakcPerid,feedbackPername,feedbackPerdept);
    }
}
