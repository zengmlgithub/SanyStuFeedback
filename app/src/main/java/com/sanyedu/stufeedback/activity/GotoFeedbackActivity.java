package com.sanyedu.stufeedback.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

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
import com.sanyedu.stufeedback.adapter.DepartAdapter;
import com.sanyedu.stufeedback.adapter.ImagePickerAdapter;
import com.sanyedu.stufeedback.adapter.PersonAdapter;
import com.sanyedu.stufeedback.model.DepartModel;
import com.sanyedu.stufeedback.model.FeedbackItem;
import com.sanyedu.stufeedback.model.PersonModel;
import com.sanyedu.stufeedback.mvp.gotofeedback.GotoFeedbackContacts;
import com.sanyedu.stufeedback.mvp.gotofeedback.GotoFeedbackPresenter;
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
 * 提交反馈的页面
 */
public class GotoFeedbackActivity extends SanyBaseActivity<GotoFeedbackPresenter> implements GotoFeedbackContacts.IGotoFeedbackUI, ImagePickerAdapter.OnRecyclerViewItemClickListener, AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;

    //当前选择的所有图片
    private ArrayList<ImageItem> selImageList;
    private int maxImgCount = 3;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private ImagePickerAdapter adapter;

    private DepartAdapter departAdapter;
    private PersonAdapter personAdapter;

    @BindView(R.id.policy_department_et)
    Spinner departSpinner;

    @BindView(R.id.policy_person_et)
    Spinner personSpinner;


    @OnClick(R.id.submit_tv)
    public void submit(){
        SanyLogs.i("submit~~~~");
        List<String> pathList = getImagePath();
        showLoading();
        getPresenter().postFeedbackToServer(pathList,getCurrentItem());
    }

    @BindView(R.id.title_et)
    EditText titleEt;

    @BindView(R.id.address_et)
    EditText addressEt;

    @BindView(R.id.feedback_detail_et)
    EditText contentEt;


    private List<String> getImagePath() {
        if(selImageList != null && selImageList.size() > 0){
            List<String> tempList = new ArrayList<>();
            for(int i = 0 ; i < selImageList.size() ; i ++){
                tempList.add(selImageList.get(i).path);
            }

            return tempList;
        }else{
            return null;
        }
    }

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        initImagePickerMulti();
        initRecyclerView();

        initDepart();
        initPerson();
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

    @Override
    protected int getLayout() {
        return R.layout.activity_goto_feedback;
    }

    @Override
    public GotoFeedbackPresenter onBindPresenter() {
        return new GotoFeedbackPresenter(this);
    }

    @Override
    public void setDepartList(List<DepartModel> departList) {
        departBeans = departList;
        departAdapter.setData(departList);
    }

    @Override
    public void setPersonList(List<PersonModel> personList) {
        personAdapter.setData(personList);
    }

    @Override
    public FeedbackItem getCurrentItem() {
        FeedbackItem tempItem = new FeedbackItem();
        try {
            String address = addressEt.getText().toString().trim();
            SanyLogs.i("address:" + address);
            tempItem.setFeedbackAddress(address);

            String content = contentEt.getText().toString().trim();
            SanyLogs.i("content:" + content);
            tempItem.setFeedbackContent(content);

            String feedbackDept = getFeedbackDept();
            SanyLogs.i("feedbackDept:" + feedbackDept);
            tempItem.setFeedbackDept(feedbackDept);

            String title = titleEt.getText().toString().trim();
            SanyLogs.i("title:" + title);
            tempItem.setFeedbackTitle(title);

            String responseDepartName = getResponseDepartName();
            tempItem.setToResponsibledept(responseDepartName);

            String feedbackId = getFeedbackId();
            tempItem.setFeedbackPersonid(feedbackId);

            String feedbackPeopleName = getFeedbackPeopleName();
            tempItem.setFeedbackPersonname(feedbackPeopleName);

            String responsePeopleName = getResponseName();
            tempItem.setToResponsiblename(responsePeopleName);

            String responsePeopleId = getResponseId();
            tempItem.setToResponsibleid(responsePeopleId);

        }catch (Exception e){
            SanyLogs.i(e.toString());
        }
        return tempItem;
    }

    @Override
    public void updateFeedbackSuccess() {
        hideLoading();
        //上传完后
        finish();
    }

    @Override
    public void updateFeedbackFailure(String failureReson) {
        hideLoading();
        ToastUtil.showLongToast(failureReson);
    }

    private String getFeedbackDept() {
        String depart = UserInfoHelper.getPersonDept();
        return depart;
    }

    private String getFeedbackPeopleName() {
        String name = UserInfoHelper.getPersonName();
        return name;
    }

    private String getFeedbackId() {
        String id = UserInfoHelper.getPersonId();
        return id;
    }

    //
    private String getResponseId() {
        Object tempBean = personSpinner.getSelectedItem();
        PersonModel personBean = null;
        if(tempBean instanceof PersonModel){
            personBean = (PersonModel)tempBean;
            if(personBean != null){
                return personBean.getId();
            }
        }
        return null;
    }

    //获取当前选择的部门
    private String getResponseDepartName() {
        Object tempBean = departSpinner.getSelectedItem();
        DepartModel departBean = null;
        if(tempBean instanceof DepartModel){
            departBean = (DepartModel)tempBean;
            if(departBean != null){
                return departBean.getFullname();
            }
        }
        return null;
    }

    //获取当前选择的部门
    private String getResponseName() {
        Object tempBean = personSpinner.getSelectedItem();
        PersonModel personBean = null;
        if(tempBean instanceof PersonModel){
            personBean = (PersonModel)tempBean;
            if(personBean != null){
                return personBean.getTeName();
            }
        }
        return null;
    }



    @Override
    public void onItemClick(View view, int position){
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
                        if (AndPermission.hasAlwaysDeniedPermission(GotoFeedbackActivity.this, data)) {
                            //true，弹窗再次向用户索取权限
//                            showSettingDialog(MainActivity.this, data);
                        }
                    }
                }).start();
    }

    private PictureChooseDialog showDialog(PictureChooseDialog.SelectDialogListener listener, List<String> names) {
        PictureChooseDialog dialog = new PictureChooseDialog(this, R.style.transparentFrameWindowStyle, listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
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
                        Intent intent = new Intent(GotoFeedbackActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS,true); // 是否是直接打开相机
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                        break;
                    case 1:
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent1 = new Intent(GotoFeedbackActivity.this, ImageGridActivity.class);
                        startActivityForResult(intent1, REQUEST_CODE_SELECT);
                        break;
                    default:
                        break;
                }
            }
        }, names);
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

    private List<DepartModel> departBeans = null;
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

//    @Override
//    public void showRationale(Context context, List<String> data, RequestExecutor executor) {
//        List<String> permissionNames = Permission.transformText(context, data);
//        String message = "请授权该下的权限" + "\n" + permissionNames;
//
//        new android.app.AlertDialog.Builder(context)
//                .setCancelable(false)
//                .setTitle("提示")
//                .setMessage(message)
//                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        executor.execute();
//                    }
//                })
//                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        executor.cancel();
//                    }
//                })
//                .show();
//    }
}
