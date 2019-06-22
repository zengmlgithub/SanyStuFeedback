package com.sanyedu.stufeedback.model;

import java.util.ArrayList;

public class PageNoticeBean {
    private String current;
    private ArrayList<NoticeModel> pNotice;
    private String pages;
    private String size;
    private String total;

    public PageNoticeBean(String current, ArrayList<NoticeModel> pNotice, String pages, String size, String total) {
        this.current = current;
        this.pNotice = pNotice;
        this.pages = pages;
        this.size = size;
        this.total = total;
    }

    public PageNoticeBean() {
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public ArrayList<NoticeModel> getpNotice() {
        return pNotice;
    }

    public void setpNotice(ArrayList<NoticeModel> pNotice) {
        this.pNotice = pNotice;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageNoticeBean{" +
                "current='" + current + '\'' +
                ", pNotice=" + pNotice +
                ", pages='" + pages + '\'' +
                ", size='" + size + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
