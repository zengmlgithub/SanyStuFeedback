package com.sanyedu.stufeedback.model;

import java.io.Serializable;

public class FeedbackItem implements Serializable {
    private String feedbackTitle;
    private String feedbackAddress;
    private String feedbackContent;
    private String feedbackDept;
    private String feedbackPersonid;
    private String feedbackPersonname;
    private String feedbackA;
    private String feedbackB;
    private String feedbackC;
    private String toResponsiblename;
    private String toResponsibledept;
    private String toResponsibleid; //责任人id

    public FeedbackItem() {

    }

    public FeedbackItem(String feedbackTitle, String feedbackAddress, String feedbackContent, String feedbackDept, String feedbackPersonid, String feedbackPersonname, String feedbackA, String feedbackB, String feedbackC, String toResponsiblename, String toResponsibledept, String toResponsibleid) {
        this.feedbackTitle = feedbackTitle;
        this.feedbackAddress = feedbackAddress;
        this.feedbackContent = feedbackContent;
        this.feedbackDept = feedbackDept;
        this.feedbackPersonid = feedbackPersonid;
        this.feedbackPersonname = feedbackPersonname;
        this.feedbackA = feedbackA;
        this.feedbackB = feedbackB;
        this.feedbackC = feedbackC;
        this.toResponsiblename = toResponsiblename;
        this.toResponsibledept = toResponsibledept;
        this.toResponsibleid = toResponsibleid;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public String getFeedbackAddress() {
        return feedbackAddress;
    }

    public void setFeedbackAddress(String feedbackAddress) {
        this.feedbackAddress = feedbackAddress;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public String getFeedbackDept() {
        return feedbackDept;
    }

    public void setFeedbackDept(String feedbackDept) {
        this.feedbackDept = feedbackDept;
    }

    public String getFeedbackPersonid() {
        return feedbackPersonid;
    }

    public void setFeedbackPersonid(String feedbackPersonid) {
        this.feedbackPersonid = feedbackPersonid;
    }

    public String getFeedbackPersonname() {
        return feedbackPersonname;
    }

    public void setFeedbackPersonname(String feedbackPersonname) {
        this.feedbackPersonname = feedbackPersonname;
    }

    public String getFeedbackA() {
        return feedbackA;
    }

    public void setFeedbackA(String feedbackA) {
        this.feedbackA = feedbackA;
    }

    public String getFeedbackB() {
        return feedbackB;
    }

    public void setFeedbackB(String feedbackB) {
        this.feedbackB = feedbackB;
    }

    public String getFeedbackC() {
        return feedbackC;
    }

    public void setFeedbackC(String feedbackC) {
        this.feedbackC = feedbackC;
    }

    public String getToResponsiblename() {
        return toResponsiblename;
    }

    public void setToResponsiblename(String toResponsiblename) {
        this.toResponsiblename = toResponsiblename;
    }

    public String getToResponsibledept() {
        return toResponsibledept;
    }

    public void setToResponsibledept(String toResponsibledept) {
        this.toResponsibledept = toResponsibledept;
    }

    public String getToResponsibleid() {
        return toResponsibleid;
    }

    public void setToResponsibleid(String toResponsibleid) {
        this.toResponsibleid = toResponsibleid;
    }

    @Override
    public String toString() {
        return "FeedbackItem{" +
                "feedbackTitle='" + feedbackTitle + '\'' +
                ", feedbackAddress='" + feedbackAddress + '\'' +
                ", feedbackContent='" + feedbackContent + '\'' +
                ", feedbackDept='" + feedbackDept + '\'' +
                ", feedbackPersonid='" + feedbackPersonid + '\'' +
                ", feedbackPersonname='" + feedbackPersonname + '\'' +
                ", feedbackA='" + feedbackA + '\'' +
                ", feedbackB='" + feedbackB + '\'' +
                ", feedbackC='" + feedbackC + '\'' +
                ", toResponsiblename='" + toResponsiblename + '\'' +
                ", toResponsibledept='" + toResponsibledept + '\'' +
                ", toResponsibleid='" + toResponsibleid + '\'' +
                '}';
    }
}
