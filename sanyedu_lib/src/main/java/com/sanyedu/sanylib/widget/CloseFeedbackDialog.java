package com.sanyedu.sanylib.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sanyedu.sanylib.R;


public class CloseFeedbackDialog extends Dialog implements View.OnClickListener{
    private TextView titleTv;
    private EditText contentEt;
    private TextView submitTxt;
    private TextView cancelTxt;

    private OnClickListener listener;


    public CloseFeedbackDialog(Context context) {
        this(context,0,null);
    }

    public CloseFeedbackDialog(Context context, int themeResId) {
        this(context,themeResId,null);
    }

    public CloseFeedbackDialog(Context context, int themeResId, OnClickListener listener) {
        super(context, themeResId);

        this.listener = listener;
    }

    protected CloseFeedbackDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_close_feedback_dialog);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView(){
        contentEt = findViewById(R.id.content);
        titleTv = findViewById(R.id.title);
        submitTxt = findViewById(R.id.submit);
        submitTxt.setOnClickListener(this);
        cancelTxt = findViewById(R.id.cancel);
        cancelTxt.setOnClickListener(this);

    }

    public String getContent(){
        String content = contentEt.getText().toString();
        return content;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == cancelTxt.getId()){
            if(listener != null){
                listener.onNevige(this, false);
            }
            this.dismiss();
        }else if(v.getId() == submitTxt.getId()){
            if(listener != null){
                listener.onPositive(this, true);
            }
        }
    }

    public interface OnClickListener{
        void onPositive(Dialog dialog, boolean cancel);
        void onNevige(Dialog dialog, boolean confirm);
    }


}