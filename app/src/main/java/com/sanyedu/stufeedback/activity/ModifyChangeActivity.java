package com.sanyedu.stufeedback.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;

import com.sanyedu.sanylib.base.SanyBaseActivity;
import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.utils.ToastUtil;
import com.sanyedu.sanylib.widget.GlideImageLoader;
import com.sanyedu.sanylib.widget.PictureChooseDialog;
import com.sanyedu.stufeedback.R;
import com.sanyedu.stufeedback.adapter.ImagePickerAdapter;
import com.sanyedu.stufeedback.model.ChangeFeedbackModel;
import com.sanyedu.stufeedback.mvp.modifychange.ModifyChangeContacts;
import com.sanyedu.stufeedback.mvp.modifychange.ModifyChangePresenter;
import com.sanyedu.stufeedback.utils.StuContantsUtil;
import com.sanyedu.stufeedback.utils.UserInfoHelper;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改整改的状态
 */
public class ModifyChangeActivity extends SanyBaseActivity<ModifyChangePresenter> implements ModifyChangeContacts.IModifyChangeUI, ImagePickerAdapter.OnRecyclerViewItemClickListener {

    @OnClick(R.id.goback_tv)
    public void goBack(){
        finish();
    }



    @BindView(R.id.content_et)
    EditText contentEt;

    @BindView(R.id.modifying_btn)
    Button modifyingBtn;

    @BindView(R.id.modified_btn)
    Button modifiedBtn;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    //当前选择的所有图片
    private ArrayList<ImageItem> selImageList;
    private int maxImgCount = 3;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ImagePickerAdapter adapter;

    private String feedbackId; //当前反馈的id

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initImagePickerMulti();
        initRecyclerView();
        getFeedbackId();
    }

    private void getFeedbackId() {
        Intent intent = new Intent();
        if (intent != null){
            feedbackId = intent.getStringExtra(StuContantsUtil.ID);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_modifiy_change;
    }

    @Override
    public ModifyChangePresenter onBindPresenter() {
        return new ModifyChangePresenter(this);
    }

    private void initImagePickerMulti() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                            //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setMultiMode(true);                      //多选
//        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
//        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
//        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemRemoveClick(new ImagePickerAdapter.OnItemRemoveClick() {
            @Override
            public void onItemRemoveClick() {
                adapter.setImages(adapter.getImages());
                adapter.notifyDataSetChanged();
                selImageList.clear();
                selImageList.addAll(adapter.getImages());
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        SanyLogs.i("OnItemClick~~~position:" + position);
        switch (position){
            case IMAGE_ITEM_ADD:
                SanyLogs.i("OnItemClick~~~position  switch:" + IMAGE_ITEM_ADD);
                //先请求权限，再进行操作
//                AndPermission.with(this)
//                        .requestCode(300)
//                        .permission(
//                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                                Manifest.permission.READ_EXTERNAL_STORAGE)
//                        .callback(this)
//                        .start();

                requestPermission();

                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS,true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }


    private void requestPermission() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.CAMERA)
//                .rationale(this)//添加拒绝权限回调
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        // data.get(0);
                        SanyLogs.d("permission", data.get(0));
                        chooseImage();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        /**
                         * 当用户没有允许该权限时，回调该方法
                         */
                        ToastUtil.showLongToast("没有获取照相机权限，该功能无法使用");
                        /**
                         * 判断用户是否点击了禁止后不再询问，AndPermission.hasAlwaysDeniedPermission(MainActivity.this, data)
                         */
                        if (AndPermission.hasAlwaysDeniedPermission(ModifyChangeActivity.this, data)) {
                            //true，弹窗再次向用户索取权限
//                            showSettingDialog(MainActivity.this, data);
                        }
                    }
                }).start();
    }

    private void chooseImage(){
        List<String> names = new ArrayList<>();
        names.add("拍照");
        names.add("从相册选择");
        showDialog(new PictureChooseDialog.SelectDialogListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: // 直接调起相机
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent = new Intent(ModifyChangeActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS,true); // 是否是直接打开相机
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                        break;
                    case 1:
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent1 = new Intent(ModifyChangeActivity.this, ImageGridActivity.class);
                        startActivityForResult(intent1, REQUEST_CODE_SELECT);
                        break;
                    default:
                        break;
                }
            }
        }, names);
    }
    private PictureChooseDialog showDialog(PictureChooseDialog.SelectDialogListener listener, List<String> names) {
        PictureChooseDialog dialog = new PictureChooseDialog(this, R.style.transparentFrameWindowStyle, listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null){
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null){
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }


    @OnClick(R.id.save_tv)
    public void save(){
        ChangeFeedbackModel changeFeedbackModel = new ChangeFeedbackModel();
        changeFeedbackModel.setFeedbackId(feedbackId);

        String feedbackStatus = getFeedbackStatus();
        changeFeedbackModel.setFeedbackStatus(feedbackStatus);

        String feedbackContent = getFeedbackContent();
        changeFeedbackModel.setFeedbackContent(feedbackContent);

        String feedbackPerid = UserInfoHelper.getPersonId();
        changeFeedbackModel.setFeedbackPerid(feedbackPerid);

        String feedbackPername = UserInfoHelper.getPersonName();
        changeFeedbackModel.setFeedbackPername(feedbackPername);

        String feedbackPerdept = UserInfoHelper.getPersonDept();
        changeFeedbackModel.setFeedbackPerdept(feedbackPerdept);

        List<String> pathList = getPathList();
        getPresenter().updateFeedback(pathList, changeFeedbackModel);
    }

    private List<String> getPathList() {
        List<String> tempList = new ArrayList<>();
        for(int i = 0 ;i < selImageList.size() ; i ++ ){
            tempList.add(selImageList.get(i).path);
        }
        return tempList;
    }


    private String getFeedbackContent() {
        String content = contentEt.getText().toString().trim() ;
        String contentStr = TextUtils.isEmpty(content)? "":content;
        return contentStr;
    }

    private String getFeedbackStatus() {
        return "1";
    }

    @Override
    public void updateFeedbackFailure(String msg) {
        ToastUtil.showLongToast(msg);
    }

    @Override
    public void updateFeedbackSuccess() {
        ToastUtil.showLongToast("更新成功");
    }
}
