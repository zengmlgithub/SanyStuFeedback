/**
 * Copyright 2019 bejson.com
 */
package com.sanyedu.stufeedback.model;

import java.util.List;

/**
 * Auto-generated: 2019-05-22 11:28:41
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class PageRecordBean {

    private String current;
    private String pages;
    private List<Records> records;
    private int size;
    private String total;

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getPages() {
        return pages;
    }

    public void setRecords(List<Records> records) {
        this.records = records;
    }

    public List<Records> getRecords() {
        return records;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "PageRecordBean{" +
                "current='" + current + '\'' +
                ", pages='" + pages + '\'' +
                ", records=" + records +
                ", size=" + size +
                ", total='" + total + '\'' +
                '}';
    }
}