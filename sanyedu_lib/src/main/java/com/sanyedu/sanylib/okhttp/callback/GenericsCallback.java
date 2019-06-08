package com.sanyedu.sanylib.okhttp.callback;


import com.sanyedu.sanylib.log.SanyLogs;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import okhttp3.Response;

/**
 * Created by JimGong on 2016/6/23.
 */

public abstract class GenericsCallback<T> extends Callback<T> {
    IGenericsSerializator mGenericsSerializator;

    public GenericsCallback(IGenericsSerializator serializator) {
        mGenericsSerializator = serializator;
    }

    @Override
    public T parseNetworkResponse(Response response, int id) throws IOException {
        String string = response.body().string();
//        SanyLogs.i("parseNetworkResponse:" + string);
        //TODO:如果包含了List，这时是无法解释的

        Class entityClass = (Class ) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        SanyLogs.i("entityClass name:" + entityClass.getCanonicalName());


        if(entityClass == String.class){
            return (T)string;
        }else{
            T bean = (T)mGenericsSerializator.transform(string,entityClass);
            return bean;
        }

    }

}
