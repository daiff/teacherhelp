package com.example.administrator.teacherhelper.Bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/16 0016.
 */

public class city extends BmobObject {


    public com.example.administrator.teacherhelper.Bean.person getPerson() {
        return person;
    }

    public void setPerson(com.example.administrator.teacherhelper.Bean.person person) {
        this.person = person;
    }

    public com.example.administrator.teacherhelper.Bean.person getUser() {
        return user;
    }

    public void setUser(com.example.administrator.teacherhelper.Bean.person user) {
        this.user = user;
    }

    public com.example.administrator.teacherhelper.Bean.FIELD getFIELD() {
        return FIELD;
    }

    public void setFIELD(com.example.administrator.teacherhelper.Bean.FIELD FIELD) {
        this.FIELD = FIELD;
    }

    private person person;
    private person user;
    private FIELD FIELD;

    public FIELD getR() {
        return R;
    }

    public void setR(FIELD r) {
        R = r;
    }

    private FIELD R;
}
