package com.sanyedu.stufeedback.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;


import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.sanyedu.sanylib.base.SanyBaseActivity;
import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.adapter.DepartAdapter;
import com.sanyedu.stufeedback.adapter.GridImageAdapter;
import com.sanyedu.stufeedback.adapter.PersonAdapter;
import com.sanyedu.stufeedback.layoutmanager.FullyGridLayoutManager;
import com.sanyedu.stufeedback.model.DepartModel;
import com.sanyedu.stufeedback.model.PersonModel;
import com.sanyedu.stufeedback.mvp.gotofeedback.GotoFeedbackContacts;
import com.sanyedu.stufeedback.mvp.gotofeedback.GotoFeedbackPresenter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 提交反馈的页面
 */
public class GotoFeedbackActivity extends SanyBaseActivity<GotoFeedbackPresenter> implements GotoFeedbackContacts.IGotoFeedbackUI, AdapterView.OnItemSelectedListener {

    @BindView(R.id.goback_iv)
    TextView gobackTv;

    @BindView(R.id.submit_tv)
    TextView commitTv;

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;

    @BindView(R.id.policy_department_et)
    Spinner departSpinner;

    @BindView(R.id.policy_person_et)
    Spinner personSpinner;



    private int maxSelectNum = 3;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;

    private PopupWindow pop;

    private List<DepartModel> departBeans = null;

    DepartAdapter departAdapter;
    private PersonAdapter personAdapter;

    @OnClick(R.id.goback_iv)
    public void close(){
        finish();
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initPhoto();
        initDepart();
        initPerson();
    }

    private void initPhoto() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(GotoFeedbackActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(GotoFeedbackActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(GotoFeedbackActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });

    }

    private void initPerson() {
        personAdapter = new PersonAdapter(getLayoutInflater());
        personSpinner.setAdapter(personAdapter);
    }

    private void initDepart() {
        departAdapter = new DepartAdapter(getLayoutInflater());
        departSpinner.setAdapter(departAdapter);

        getPresenter().getDepart();

        departSpinner.setOnItemSelectedListener(this);
    }

//    @Override
//    protected void setListeners() {
//        gobackTv.setOnClickListener(this);
//        commitTv.setOnClickListener(this);
//    }

    @Override
    protected int getLayout() {
        return R.layout.activity_goto_feedback;
    }

    @Override
    public GotoFeedbackPresenter onBindPresenter() {
        return new GotoFeedbackPresenter(this);
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {

        @Override
        public void onAddPicClick() {
            //第一种方式，弹出选择和拍照的dialog
            showPop();
        }
    };

    private void showPop() {
        View bottomView = View.inflate(GotoFeedbackActivity.this, R.layout.layout_bottom_dialog, null);
        TextView mAlbum = bottomView.findViewById(R.id.tv_album);
        TextView mCamera = bottomView.findViewById(R.id.tv_camera);
        TextView mCancel = bottomView.findViewById(R.id.tv_cancel);

        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.tv_album:
                        //相册
                        PictureSelector.create(GotoFeedbackActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(maxSelectNum)
                                .minSelectNum(1)
                                .imageSpanCount(3)
                                .previewImage(true)
                                .selectionMode(PictureConfig.MULTIPLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_camera:
                        //拍照
                        PictureSelector.create(GotoFeedbackActivity.this)
                                .openCamera(PictureMimeType.ofImage())
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.tv_cancel:
                        //取消
                        //closePopupWindow();
                        break;
                }
                closePopupWindow();
            }
        };

        mAlbum.setOnClickListener(clickListener);
        mCamera.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
    }


    public void closePopupWindow() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SanyLogs.i( "onActivityResult~~~~~~requestCode:" + requestCode + ",resultCode:" + resultCode);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    images = PictureSelector.obtainMultipleResult(data);
                    selectList.addAll(images);
//                    selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    adapter.setList(selectList);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }


    @Override
    public void setDepartList(List<DepartModel> departList) {
        this.departBeans = departList;
        departAdapter.setData(departList);
    }

    @Override
    public void setPersonList(List<PersonModel> personList) {
        personAdapter.setData(personList);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(departBeans != null ){
            DepartModel departBean = departBeans.get(position);
            if(departBean != null){
                SanyLogs.i("you hava click:" + position + "," + departBean.toString());
                String departId = departBean.getId();
                getPresenter().getPersonOfDepart(departId);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick(R.id.submit_tv)
    public void submitPhotos(){
        List<String> photoList = new ArrayList<>();
        if(selectList != null && selectList.size() > 0 ){
            for(int i = 0; i < selectList.size() ; i ++){
                LocalMedia localMedia = selectList.get(i);
                if(localMedia != null){
                    String photoPath = localMedia.getPath();
                    SanyLogs.i("photoPath:" + photoPath);
                    photoList.add(photoPath);
                }
            }
        }

        getPresenter().postFiles(photoList);
    }
}
