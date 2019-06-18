package com.sanyedu.stufeedback.model;

public class StudentModel extends BaseModel{
   private String createtime;
   private String id;
   private String password;
   private String stuClass;
   private String stuEmai;
   private String stuFlag;
   private String stuName;
   private String stuNum;
   private String stuPhone;
   private String stuPosi;
   private String stuSex;
   private String username;

    public StudentModel(String createtime, String id, String password, String stuClass, String stuEmai, String stuFlag, String stuName, String stuNum, String stuPhone, String stuPosi, String stuSex, String username) {
        this.createtime = createtime;
        this.id = id;
        this.password = password;
        this.stuClass = stuClass;
        this.stuEmai = stuEmai;
        this.stuFlag = stuFlag;
        this.stuName = stuName;
        this.stuNum = stuNum;
        this.stuPhone = stuPhone;
        this.stuPosi = stuPosi;
        this.stuSex = stuSex;
        this.username = username;
    }

    public StudentModel() {
    }



    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getStuEmai() {
        return stuEmai;
    }

    public void setStuEmai(String stuEmai) {
        this.stuEmai = stuEmai;
    }

    public String getStuFlag() {
        return stuFlag;
    }

    public void setStuFlag(String stuFlag) {
        this.stuFlag = stuFlag;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getStuPosi() {
        return stuPosi;
    }

    public void setStuPosi(String stuPosi) {
        this.stuPosi = stuPosi;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "createtime='" + createtime + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", stuClass='" + stuClass + '\'' +
                ", stuEmai='" + stuEmai + '\'' +
                ", stuFlag='" + stuFlag + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuNum='" + stuNum + '\'' +
                ", stuPhone='" + stuPhone + '\'' +
                ", stuPosi='" + stuPosi + '\'' +
                ", stuSex='" + stuSex + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
