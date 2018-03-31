package com.example.administrator.teacherhelper.bean;

import android.provider.ContactsContract;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by Administrator on 2018/3/31 0031.
 */

public class Innovate extends BmobObject {
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public com.example.administrator.teacherhelper.bean.person getTeacher() {
        return teacher;
    }

    public void setTeacher(com.example.administrator.teacherhelper.bean.person teacher) {
        this.teacher = teacher;
    }

    public BmobRelation getPerson() {
        return person;
    }

    public void setPerson(BmobRelation person) {
        this.person = person;
    }

    private String content;
    private String title;
    private person teacher;
    private BmobRelation person;
}
