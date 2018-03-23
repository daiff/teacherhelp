package com.example.administrator.teacherhelper.Bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/22 0022.
 */

public class classs extends BmobObject {

    public FIELD getClasss() {
        return classs;
    }

    public void setClasss(FIELD classs) {
        this.classs = classs;
    }

    public FIELD getCollege() {
        return college;
    }

    public void setCollege(FIELD college) {
        this.college = college;
    }

    public FIELD getGrade() {
        return grade;
    }

    public void setGrade(FIELD grade) {
        this.grade = grade;
    }

    public FIELD getMajor() {
        return major;
    }

    public void setMajor(FIELD major) {
        this.major = major;
    }

    private FIELD  classs;
    private FIELD  college;
    private FIELD grade ;
    private FIELD major ;

    public String getTotal_person() {
        return total_person;
    }

    public void setTotal_person(String total_person) {
        this.total_person = total_person;
    }

    private String total_person ;

}
