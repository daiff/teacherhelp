package com.example.administrator.teacherhelper.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/4/1 0001.
 */

public class Schedule extends BmobObject {
    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getClassroom() {
        return Classroom;
    }

    public void setClassroom(String classroom) {
        Classroom = classroom;
    }

    public String getJiaoxue() {
        return jiaoxue;
    }

    public void setJiaoxue(String jiaoxue) {
        this.jiaoxue = jiaoxue;
    }


    public String getWeek() {
        return Week;
    }

    public void setWeek(String week) {
        Week = week;
    }

    public String getXingqi() {
        return Xingqi;
    }

    public void setXingqi(String xingqi) {
        Xingqi = xingqi;
    }

    public person getTeacher() {
        return teacher;
    }

    public void setTeacher(person teacher) {
        this.teacher = teacher;
    }

    private String classes;
    private String Classroom;
    private String jiaoxue;

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    private String section;
    private String Week;
    private String Xingqi;
    private person teacher;

    public FIELD getSchoolyear() {
        return schoolyear;
    }

    public void setSchoolyear(FIELD schoolyear) {
        this.schoolyear = schoolyear;
    }

    private FIELD schoolyear;
}
