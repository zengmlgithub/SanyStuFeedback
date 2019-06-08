package com.sanyedu.sanylib.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sanyedu.sanylib.R;

public class CommonDialog extends Dialog implements View.OnClickListener{
    private TextView contentTxt;
    private TextView titleTxt;
    private TextView submitTxt;
    private TextView cancelTxt;

//    private Context mContext;
    private String content;
    private OnCloseListener listener;
    private String positiveName;
    private String negativeName;
    private String title;

    public CommonDialog(Context context) {
        this(context,0,null,null);
    }

    public CommonDialog(Context context, int themeResId, String content) {
        this(context,themeResId,content,null);
    }

    public CommonDialog(Context context, int themeResId, String content, OnCloseListener listener) {
        super(context, themeResId);
        this.content = content;
        this.listener = listener;
    }

    protected CommonDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
//        this.mContext = context;
    }

    public CommonDialog setTitle(String title){
        this.title = title;
        return this;
    }

    public CommonDialog setPositiveButton(String name){
        this.positiveName = name;
        return this;
    }

    public CommonDialog setNegativeButton(String name){
        this.negativeName = name;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common);
        setCanceledOnTouchOutside(false);
        initView();
    }

    private void initView(){
        contentTxt = (TextView)findViewById(R.id.content);
        titleTxt = (TextView)findViewById(R.id.title);
        submitTxt = (TextView)findViewById(R.id.submit);
        submitTxt.setOnClickListener(this);
        cancelTxt = (TextView)findViewById(R.id.cancel);
        cancelTxt.setOnClickListener(this);

        contentTxt.setText(content);
        if(!TextUtils.isEmpty(positiveName)){
            submitTxt.setText(positiveName);
        }

        if(!TextUtils.isEmpty(negativeName)){
            cancelTxt.setText(negativeName);
        }

        if(!TextUtils.isEmpty(title)){
            titleTxt.setText(title);
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == cancelTxt.getId()){
            if(listener != null){
                listener.onNevige(this, false);
            }
            this.dismiss();
        }else if(v.getId() == submitTxt.getId()){
            if(listener != null){
                listener.onPositive(this, true);
            }
            this.dismiss();
        }
    }

    public interface OnCloseListener{
        void onPositive(Dialog dialog, boolean cancel);
        void onNevige(Dialog dialog, boolean confirm);
    }


}