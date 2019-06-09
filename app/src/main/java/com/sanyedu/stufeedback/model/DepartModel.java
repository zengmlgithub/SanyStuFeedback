/**
 * Copyright 2019 bejson.com
 */
package com.sanyedu.stufeedback.model;

/**
 * Auto-generated: 2019-06-02 6:21:40
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DepartModel extends BaseModel {

    private String fullname;
    private String id;
    private String num;
    private String pid;
    private String pids;
    private String simplename;
    private String tips;
    private String version;

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public String getPids() {
        return pids;
    }

    public void setSimplename(String simplename) {
        this.simplename = simplename;
    }

    public String getSimplename() {
        return simplename;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getTips() {
        return tips;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "DepartBean{" +
                "fullname='" + fullname + '\'' +
                ", id=" + id +
                ", num=" + num +
                ", pid=" + pid +
                ", pids='" + pids + '\'' +
                ", simplename='" + simplename + '\'' +
                ", tips='" + tips + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}