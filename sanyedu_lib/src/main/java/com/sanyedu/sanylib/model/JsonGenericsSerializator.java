package com.sanyedu.sanylib.model;

import com.google.gson.Gson;
import com.sanyedu.sanylib.okhttp.callback.IGenericsSerializator;


/**
 * Created by JimGong on 2016/6/23.
 */
public class JsonGenericsSerializator<T> implements IGenericsSerializator {

    Gson mGson = new Gson();

    @Override
    public <T> T transform(String response, Class<T> classOfT) {
        //转成普通的
        return mGson.fromJson(response, classOfT);
    }


}
