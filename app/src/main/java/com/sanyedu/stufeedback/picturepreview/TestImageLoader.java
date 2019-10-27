package com.sanyedu.stufeedback.picturepreview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.previewlibrary.loader.IZoomMediaLoader;
import com.previewlibrary.loader.MySimpleTarget;

public class TestImageLoader implements IZoomMediaLoader {
    @Override
    public void displayImage(@NonNull Fragment context, @NonNull String path, ImageView imageView, @NonNull MySimpleTarget simpleTarget) {

    }

    @Override
    public void displayGifImage(@NonNull Fragment context, @NonNull String path, ImageView imageView, @NonNull MySimpleTarget simpleTarget) {

    }

    @Override
    public void onStop(@NonNull Fragment context) {

    }

    @Override
    public void clearMemory(@NonNull Context c) {

    }
}
