package com.example.administrator.teacherhelper.Bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/14 0014.
 */

public class FIELD extends BmobObject {
    private String course_code;
    private String credit;
    private String despration;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDespration() {
        return despration;
    }

    public void setDespration(String despration) {
        this.despration = despration;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    private String value;



}