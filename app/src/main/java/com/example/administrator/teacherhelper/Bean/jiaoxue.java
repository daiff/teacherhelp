package com.example.administrator.teacherhelper.Bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by Administrator on 2018/3/16 0016.
 */

public class jiaoxue extends BmobObject {
    public FIELD getKe() {
        return ke;
    }

    public void setKe(FIELD ke) {
        this.ke = ke;
    }

    private FIELD ke;

    public FIELD getNature() {
        return nature;
    }

    public void setNature(FIELD nature) {
        this.nature = nature;
    }
    private FIELD nature;




    public FIELD getSchoolyear() {
        return schoolyear;
    }

    public void setSchoolyear(FIELD schoolyear) {
        this.schoolyear = schoolyear;
    }

    private FIELD schoolyear;

    public person getTeacher() {
        return teacher;
    }

    public void setTeacher(person teacher) {
        this.teacher = teacher;
    }

    private person teacher;

    public FIELD getKaikeyuan() {
        return kaikeyuan;
    }

    public void setKaikeyuan(FIELD kaikeyuan) {
        this.kaikeyuan = kaikeyuan;
    }

    private FIELD kaikeyuan;
    private FIELD classs;
    private FIELD college;
    private FIELD grade;

    public FIELD getMajor() {
        return major;
    }

    public void setMajor(FIELD major) {
        this.major = major;
    }

    public FIELD getGrade() {
        return grade;
    }

    public void setGrade(FIELD grade) {
        this.grade = grade;
    }

    public FIELD getCollege() {
        return college;
    }

    public void setCollege(FIELD college) {
        this.college = college;
    }

    public FIELD getClasss() {
        return classs;
    }

    public void setClasss(FIELD classs) {
        this.classs = classs;
    }

    private FIELD major;
}
