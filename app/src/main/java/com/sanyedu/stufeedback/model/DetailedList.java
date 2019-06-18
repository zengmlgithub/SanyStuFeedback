/**
  * Copyright 2019 bejson.com 
  */
package com.sanyedu.stufeedback.model;

/**
 * Auto-generated: 2019-05-22 17:26:30
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DetailedList extends BaseModel {

    private String feedbackContent;
    private String feedbackFilea;
    private String feedbackFileb;
    private String feedbackFilec;
    private String feedbackId;
    private String feedbackPerdept;
    private String feedbackPerid;
    private String feedbackPername;
    private String feedbackStatus;
    private String feedbackTime;
    private String id;
    public void setFeedbackContent(String feedbackContent) {
         this.feedbackContent = feedbackContent;
     }
     public String getFeedbackContent() {
         return feedbackContent;
     }

    public void setFeedbackFilea(String feedbackFilea) {
         this.feedbackFilea = feedbackFilea;
     }
     public String getFeedbackFilea() {
         return feedbackFilea;
     }

    public void setFeedbackFileb(String feedbackFileb) {
         this.feedbackFileb = feedbackFileb;
     }
     public String getFeedbackFileb() {
         return feedbackFileb;
     }

    public void setFeedbackFilec(String feedbackFilec) {
         this.feedbackFilec = feedbackFilec;
     }
     public String getFeedbackFilec() {
         return feedbackFilec;
     }

    public void setFeedbackId(String feedbackId) {
         this.feedbackId = feedbackId;
     }
     public String getFeedbackId() {
         return feedbackId;
     }

    public void setFeedbackPerdept(String feedbackPerdept) {
         this.feedbackPerdept = feedbackPerdept;
     }
     public String getFeedbackPerdept() {
         return feedbackPerdept;
     }

    public void setFeedbackPerid(String feedbackPerid) {
         this.feedbackPerid = feedbackPerid;
     }
     public String getFeedbackPerid() {
         return feedbackPerid;
     }

    public void setFeedbackPername(String feedbackPername) {
         this.feedbackPername = feedbackPername;
     }
     public String getFeedbackPername() {
         return feedbackPername;
     }

    public void setFeedbackStatus(String feedbackStatus) {
         this.feedbackStatus = feedbackStatus;
     }
     public String getFeedbackStatus() {
         return feedbackStatus;
     }

    public String getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(String feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    @Override
    public String toString() {
        return "DetailedList{" +
                "feedbackContent='" + feedbackContent + '\'' +
                ", feedbackFilea='" + feedbackFilea + '\'' +
                ", feedbackFileb='" + feedbackFileb + '\'' +
                ", feedbackFilec='" + feedbackFilec + '\'' +
                ", feedbackId='" + feedbackId + '\'' +
                ", feedbackPerdept='" + feedbackPerdept + '\'' +
                ", feedbackPerid='" + feedbackPerid + '\'' +
                ", feedbackPername='" + feedbackPername + '\'' +
                ", feedbackStatus='" + feedbackStatus + '\'' +
                ", feedbackTime=" + feedbackTime +
                ", id='" + id + '\'' +
                '}';
    }
}