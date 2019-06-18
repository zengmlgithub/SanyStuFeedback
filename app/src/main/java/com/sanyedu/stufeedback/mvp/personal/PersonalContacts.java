package com.sanyedu.stufeedback.mvp.personal;

import android.net.Uri;


import com.sanyedu.sanylib.mvp.IBasePresenter;
import com.sanyedu.sanylib.mvp.IBaseView;

import java.io.InputStream;

public final class PersonalContacts {
    public interface IMainMyUI extends IBaseView {
        InputStream openInputStream(Uri uri);
        void showHeadImage();
        void startActivityWithResult(Uri uri, Uri cropUri);
    }

    public interface IMainMyPresenter extends IBasePresenter {
        void mkdir();

        void setPicToView(Uri uri);

        void startPhotoZoom(Uri uri);
    }
}
