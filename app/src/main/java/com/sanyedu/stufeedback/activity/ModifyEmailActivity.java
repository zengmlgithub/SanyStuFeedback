package com.sanyedu.stufeedback.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sanyedu.stufeedback.R;
import com.sanyedu.sanylib.base.SanyBaseActivity;

import com.sanyedu.stufeedback.model.StudentModel;
import com.sanyedu.stufeedback.mvp.modifyinfo.ModifyInfoContacts;
import com.sanyedu.stufeedback.mvp.modifyinfo.ModifyInfoPresenter;
import com.sanyedu.sanylib.share.SpHelper;
import com.sanyedu.sanylib.utils.CheckUtils;
import com.sanyedu.sanylib.utils.ConstantUtil;
import com.sanyedu.sanylib.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyEmailActivity extends SanyBaseActivity<ModifyInfoPresenter> implements ModifyInfoContacts.IModifyInfoUI {

//    @BindView(R.id.goback_ib)
//    ImageButton gobackIB;

//    @BindView(R.id.title_tv)
//    TextView titleTv;

    @BindView(R.id.modify_content_etw)
    EditText modifyEt;

    @BindView(R.id.check_tv)
    TextView checkTv;

    @Override
    protected void initData() {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_modify_email;
    }

    @Override
    public ModifyInfoPresenter onBindPresenter() {
        return new ModifyInfoPresenter(this);
    }

    @Override
    public void showModifySuccess() {
        ToastUtil.showLongToast(R.string.modify_email_success);
        //TODO：反回主页时对主页进行刷新
    }

    @OnClick(R.id.confirm_btn)
    public void confirm(){
        String emailStr = modifyEt.getText().toString().trim();
        if(TextUtils.isEmpty(emailStr)){
//            ToastUtil.showLongToast(R.string.please_input_email);
            checkTv.setVisibility(View.VISIBLE);
            checkTv.setText(getResources().getString(R.string.please_input_email));
            return;
        }

        if(CheckUtils.isEmail(emailStr)){
            checkTv.setVisibility(View.VISIBLE);
            checkTv.setText(getResources().getString(R.string.email_illegacity));
            return;
        }

        StudentModel newBean = createNewTeacher();
        if(newBean != null) {
            newBean.setStuEmai(emailStr);
            List<StudentModel> beanList = new ArrayList<>();
            beanList.add(newBean);
            String str = new Gson().toJson(beanList);
            getPresenter().ModifyObj("1",str);
        }else{
            //TODO:当email没有输入的时候，这个时候是不需要改东西的时候，可以做一个用户提示
        }
    }

    private StudentModel createNewTeacher() {
        StudentModel bean = SpHelper.getObj(ConstantUtil.USERINFO);
        StudentModel tempBean = new StudentModel();
        tempBean.setId(bean.getId());
        return tempBean;
    }

    @OnClick(R.id.goback_tv)
    public void goback(){
        finish();
    }
}
