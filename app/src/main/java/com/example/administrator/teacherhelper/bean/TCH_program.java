package com.example.administrator.teacherhelper.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class TCH_program extends BmobObject {
    public TEACH getCourse() {
        return Course;
    }

    public void setCourse(TEACH course) {
        Course = course;
    }

    private TEACH Course;
    private String  ability;
    private String check ;
    private String contact ;
    private String englishname ;
    private String Hours ;
    private String object ;

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    private String major ;

    public String getObject_oriented() {
        return Object_oriented;
    }

    public void setObject_oriented(String object_oriented) {
        Object_oriented = object_oriented;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getHours() {
        return Hours;
    }

    public void setHours(String hours) {
        Hours = hours;
    }

    public String getEnglishname() {
        return englishname;
    }

    public void setEnglishname(String englishname) {
        this.englishname = englishname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    private String Object_oriented ;
}
