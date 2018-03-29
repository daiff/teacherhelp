package com.example.administrator.teacherhelper.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public class TCH_pro extends BmobObject {
    public com.example.administrator.teacherhelper.bean.jiaoxue getJiaoxue() {
        return jiaoxue;
    }

    public void setJiaoxue(com.example.administrator.teacherhelper.bean.jiaoxue jiaoxue) {
        this.jiaoxue = jiaoxue;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    private jiaoxue jiaoxue;
    private String classs;
}
