package com.sanyedu.sanylib.utils;

import android.text.TextUtils;

import com.sanyedu.sanylib.log.SanyLogs;


/**
 * 网络相关的协议
 */
public class HttpUtil {

    public static final boolean IS_TEST = false;
    private static final boolean IS_HTTP = true;

    private static final String FINAL_SERVER = "42.48.115.230:8082";  //正式服务器地址
    private static final String TEST_SERVER = "274717v9b3.wicp.vip:22849";  //测试服务器地址

    public static final String HTTPS = "https://";
    public static final String HTTP = "http://";

    private static final String getServerHost() {
        String prot = IS_HTTP ? HTTP : HTTPS;
        String server = IS_TEST ? TEST_SERVER : FINAL_SERVER;
        String protServer = prot + server;
        SanyLogs.i("protServer:" + protServer);
        if(protServer == null ){
            return null;
        }else{
            if(protServer.endsWith("/")){
                return protServer;
            }else{
                return protServer + "/";
            }
        }
    }

    public static final String getPort(String serverAddress){
        String url = null;
        if(!TextUtils.isEmpty(serverAddress)){
            String server = getServerHost();
            url = server + serverAddress;
        }
        return url;
    }
}
