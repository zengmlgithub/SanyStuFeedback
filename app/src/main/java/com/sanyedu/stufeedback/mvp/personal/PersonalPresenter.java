package com.sanyedu.stufeedback.mvp.personal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;


import com.sanyedu.sanylib.log.SanyLogs;
import com.sanyedu.sanylib.mvp.BasePresenter;
import com.sanyedu.sanylib.share.SpHelper;
import com.sanyedu.sanylib.utils.ConstantUtil;
import com.sanyedu.sanylib.utils.FileUtils;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import static com.sanyedu.sanylib.utils.ConstantUtil.IMAGE_FILE_NAME;

public class PersonalPresenter extends BasePresenter<PersonalContacts.IMainMyUI> implements PersonalContacts.IMainMyPresenter {
    public PersonalPresenter(@NonNull PersonalContacts.IMainMyUI view) {
        super(view);
    }

    @Override
    public void mkdir() {
        FileUtils.mkdirRootDirectory();
    }

    @Override
    public void setPicToView(Uri uri) {
        if (uri != null) {
            Bitmap photo = null;
            try {
                InputStream inputStream = getView().openInputStream(uri);
                photo = BitmapFactory.decodeStream(inputStream);
                // 创建 smallIcon 文件夹
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    //String storage = Environment.getExternalStorageDirectory().getPath();
                    File dirFile = new File(FileUtils.getMyPetRootDirectory(),  "Icon");
                    if (!dirFile.exists()) {
                        if (!dirFile.mkdirs()) {
                            SanyLogs.i("in setPicToView->文件夹创建失败");
                        } else {
                            SanyLogs.i("in setPicToView->文件夹创建成功");
                        }
                    }
                    File file = new File(dirFile, IMAGE_FILE_NAME);
                    //保存图片路径
                    SpHelper.putString(ConstantUtil.HEAD_IMAGE,file.getPath());
                    //Log.d("result",file.getPath());
                    // Log.d("result",file.getAbsolutePath());
                    // 保存图片
                    FileOutputStream outputStream = null;
                    try {
                        outputStream = new FileOutputStream(file);
                        photo.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                        outputStream.flush();
                        outputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 在视图中显示图片
                getView().showHeadImage();
                //circleImageView_user_head.setImageBitmap(InfoPrefs.getData(Constants.UserInfo.GEAD_IMAGE));
            }catch (Exception e){
                SanyLogs.e(e.toString());
                return;
            }

        }
    }

    @Override
    public void startPhotoZoom(Uri uri) {
//        Log.d(TAG,"Uri = "+uri.toString());
        SanyLogs.i("Uri = "+uri.toString());
        //保存裁剪后的图片
        File cropFile=new File(FileUtils.getMyPetRootDirectory(),"crop.jpg");
        try{
            if(cropFile.exists()){
                cropFile.delete();
                SanyLogs.i("delete");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        Uri cropUri;
        cropUri = Uri.fromFile(cropFile);
        getView().startActivityWithResult(uri,cropUri);
    }

    private  void saveToken(String tokenString) {
        SanyLogs.i("save token:" + tokenString);
        if (tokenString == null || "".equals(tokenString)) {
            return;
        } else {
            SpHelper.putString(ConstantUtil.TOKEN,tokenString);
        }
    }
}
