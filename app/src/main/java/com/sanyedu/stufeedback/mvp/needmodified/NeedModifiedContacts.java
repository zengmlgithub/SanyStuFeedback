package com.sanyedu.stufeedback.mvp.needmodified;


import com.sanyedu.sanylib.mvp.IBasePresenter;
import com.sanyedu.sanylib.mvp.IBaseView;
import com.sanyedu.stufeedback.model.Records;

import java.util.List;

public final class NeedModifiedContacts {
    public interface INeedModifiedUI extends IBaseView {
         void setRecords(List<Records> recordsList, int maxCount);
         void showNoMoreList();
         void showError(String msg);
    }

    public interface INeedModifiedPresenter extends IBasePresenter {
         void getRecords(String startPage, String everyPage, String type);
    }
}
