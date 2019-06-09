package com.sanyedu.stufeedback.model;

public class StudentModel {
    private String stuName;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public StudentModel(String stuName) {
        this.stuName = stuName;
    }

    public StudentModel() {
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "stuName='" + stuName + '\'' +
                '}';
    }
}
