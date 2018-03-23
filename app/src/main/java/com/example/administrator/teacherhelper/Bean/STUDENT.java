package com.example.administrator.teacherhelper.Bean;


import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/22 0022.
 */

public class STUDENT extends BmobObject {
    public com.example.administrator.teacherhelper.Bean.classs getClasss() {
        return classs;
    }

    public void setClasss(com.example.administrator.teacherhelper.Bean.classs classs) {
        this.classs = classs;
    }

    public String getDespration() {
        return despration;
    }

    public void setDespration(String despration) {
        this.despration = despration;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private classs classs;
    private String despration;
    private String number;
}
