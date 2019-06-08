package com.sanyedu.sanylib.model;


import com.google.gson.Gson;
import com.sanyedu.sanylib.okhttp.callback.Callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class BaseModelCallback<T> extends Callback<BaseModel<T>>
{

//    @Override
//    public  parseNetworkResponse(Response response,int id) throws IOException
//    {
//        String string = response.body().string();
//        List<User> user = new Gson().fromJson(string, List.class);
//        return user;
//    }


    @Override
    public BaseModel<T> parseNetworkResponse(Response response, int id) throws Exception {
        Gson gson = new Gson();
        String string = response.body().string();

//        SanyLogs.i("str:" + string);

        Type type = getClass().getGenericSuperclass();
        if(type instanceof ParameterizedType){
            Type[] types = ((ParameterizedType)type).getActualTypeArguments();
            Type ty = new ParameterizedTypeImpl(BaseModel.class, new Type[]{types[0]});
            BaseModel<T> data = gson.fromJson(string, ty);
            return data;
        }else{
            return null;
        }

    }


}
