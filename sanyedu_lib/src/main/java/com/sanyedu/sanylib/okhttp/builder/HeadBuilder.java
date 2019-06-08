package com.sanyedu.sanylib.okhttp.builder;


import com.sanyedu.sanylib.okhttp.OkHttpUtils;
import com.sanyedu.sanylib.okhttp.request.OtherRequest;
import com.sanyedu.sanylib.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
