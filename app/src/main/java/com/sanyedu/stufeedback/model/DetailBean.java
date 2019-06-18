/**
 * Copyright 2019 bejson.com
 */
package com.sanyedu.stufeedback.model;

import java.util.List;

/**
 * Auto-generated: 2019-05-22 17:26:30
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DetailBean {

    private String feedbackAdress;
    private String feedbackContent;
    private String feedbackCreatetime;
    private String feedbackDept;
    private String feedbackPersonid;
    private String feedbackPersonname;
    private String feedbackPubtime;
    private String feedbackTitle;
    private String id;
    private List<DetailedList> detailedList;
    private String rectiStatus;
    private String toResponsibledept;
    private String toResponsibleid;
    private String toResponsiblename;

    public void setFeedbackAdress(String feedbackAdress) {
        this.feedbackAdress = feedbackAdress;
    }

    public String getFeedbackAdress() {
        return feedbackAdress;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public String getFeedbackCreatetime() {
        return feedbackCreatetime;
    }

    public void setFeedbackCreatetime(String feedbackCreatetime) {
        this.feedbackCreatetime = feedbackCreatetime;
    }

    public void setFeedbackDept(String feedbackDept) {
        this.feedbackDept = feedbackDept;
    }

    public String getFeedbackDept() {
        return feedbackDept;
    }

    public void setFeedbackPersonid(String feedbackPersonid) {
        this.feedbackPersonid = feedbackPersonid;
    }

    public String getFeedbackPersonid() {
        return feedbackPersonid;
    }

    public void setFeedbackPersonname(String feedbackPersonname) {
        this.feedbackPersonname = feedbackPersonname;
    }

    public String getFeedbackPersonname() {
        return feedbackPersonname;
    }

    public String getFeedbackPubtime() {
        return feedbackPubtime;
    }

    public void setFeedbackPubtime(String feedbackPubtime) {
        this.feedbackPubtime = feedbackPubtime;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDetailedList(List<DetailedList> detailedList) {
        this.detailedList = detailedList;
    }

    public List<DetailedList> getDetailedList() {
        return detailedList;
    }

    public void setRectiStatus(String rectiStatus) {
        this.rectiStatus = rectiStatus;
    }

    public String getRectiStatus() {
        return rectiStatus;
    }

    public void setToResponsibledept(String toResponsibledept) {
        this.toResponsibledept = toResponsibledept;
    }

    public String getToResponsibledept() {
        return toResponsibledept;
    }

    public void setToResponsibleid(String toResponsibleid) {
        this.toResponsibleid = toResponsibleid;
    }

    public String getToResponsibleid() {
        return toResponsibleid;
    }

    public void setToResponsiblename(String toResponsiblename) {
        this.toResponsiblename = toResponsiblename;
    }

    public String getToResponsiblename() {
        return toResponsiblename;
    }

    @Override
    public String toString() {
        return "DetailBean{" +
                "feedbackAdress='" + feedbackAdress + '\'' +
                ", feedbackContent='" + feedbackContent + '\'' +
                ", feedbackCreatetime='" + feedbackCreatetime + '\'' +
                ", feedbackDept='" + feedbackDept + '\'' +
                ", feedbackPersonid='" + feedbackPersonid + '\'' +
                ", feedbackPersonname='" + feedbackPersonname + '\'' +
                ", feedbackPubtime='" + feedbackPubtime + '\'' +
                ", feedbackTitle='" + feedbackTitle + '\'' +
                ", id='" + id + '\'' +
                ", detailedList=" + detailedList +
                ", rectiStatus='" + rectiStatus + '\'' +
                ", toResponsibledept='" + toResponsibledept + '\'' +
                ", toResponsibleid='" + toResponsibleid + '\'' +
                ", toResponsiblename='" + toResponsiblename + '\'' +
                '}';
    }
}