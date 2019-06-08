package com.sanyedu.sanylib.model;

public class BaseModel<T>{
    private String code;
    private String info;
    private T obj;

    public BaseModel(String code, String info, T obj) {
        this.code = code;
        this.info = info;
        this.obj = obj;
    }

    public BaseModel() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

//    public String getObj() {
//        return obj;
//    }
//
//    public void setObj(String obj) {
//        this.obj = obj;
//    }


    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "BaseModel{" +
                "code='" + code + '\'' +
                ", info='" + info + '\'' +
                ", obj='" + obj + '\'' +
                '}';
    }
}
