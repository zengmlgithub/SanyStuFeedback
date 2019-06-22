package com.sanyedu.stufeedback.model;

public class NoticeModel {
    private String content;
    private String createtime;
    private String dept;
    private String id;
    private String pubName; //发布者名称
    private String title;

    public NoticeModel(String content, String createtime, String dept, String id, String pubName, String title) {
        this.content = content;
        this.createtime = createtime;
        this.dept = dept;
        this.id = id;
        this.pubName = pubName;
        this.title = title;
    }

    public NoticeModel() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "NoticeBean{" +
                "content='" + content + '\'' +
                ", createtime='" + createtime + '\'' +
                ", dept='" + dept + '\'' +
                ", id='" + id + '\'' +
                ", pubName='" + pubName + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
