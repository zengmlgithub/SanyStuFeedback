package com.sanyedu.stufeedback.model;

import java.io.Serializable;

public class UpdateInfoData implements Serializable {
    private String lastestVersionName;
    // 1 for force, 0 for not.
    private int force;
    private String lastestVersionCode;
    private String url;
    private String content;

    public UpdateInfoData(String lastestVersionName, int force, String lastestVersionCode, String url, String content) {
        this.lastestVersionName = lastestVersionName;
        this.force = force;
        this.lastestVersionCode = lastestVersionCode;
        this.url = url;
        this.content = content;
    }

    public UpdateInfoData() {
    }

    public String getLastestVersionName() {
        return lastestVersionName;
    }

    public void setLastestVersionName(String lastestVersionName) {
        this.lastestVersionName = lastestVersionName;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public String getLastestVersionCode() {
        return lastestVersionCode;
    }

    public void setLastestVersionCode(String lastestVersionCode) {
        this.lastestVersionCode = lastestVersionCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "UpdateInfoData{" +
                "lastestVersionName='" + lastestVersionName + '\'' +
                ", force=" + force +
                ", lastestVersionCode='" + lastestVersionCode + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}