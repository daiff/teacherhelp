package com.example.administrator.teacherhelper.Bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/14 0014.
 */

public class TEACH extends BmobObject {
    public FIELD getClasss() {
        return classs;
    }

    public void setClasss(FIELD classs) {
        this.classs = classs;
    }

    public FIELD getSystem() {
        return system;
    }

    public void setSystem(FIELD system) {
        this.system = system;
    }

    public FIELD getSchoolyear() {
        return schoolyear;
    }

    public void setSchoolyear(FIELD schoolyear) {
        this.schoolyear = schoolyear;
    }

    public FIELD getSemester() {
        return semester;
    }

    public void setSemester(FIELD semester) {
        this.semester = semester;
    }

    public FIELD getSchedule() {
        return schedule;
    }

    public void setSchedule(FIELD schedule) {
        this.schedule = schedule;
    }

    public FIELD getNature() {
        return nature;
    }

    public void setNature(FIELD nature) {
        this.nature = nature;
    }

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

    public FIELD getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(FIELD coursecode) {
        this.coursecode = coursecode;
    }

    public FIELD getCollege() {
        return college;
    }

    public void setCollege(FIELD college) {
        this.college = college;
    }

    public _User getTeacher() {
        return teacher;
    }

    public void setTeacher(_User teacher) {
        this.teacher = teacher;
    }

    private FIELD classs;
    private FIELD system;
    private FIELD semester;
    private FIELD schoolyear;
    private FIELD schedule;
    private FIELD nature;
    private FIELD major;
    private FIELD grade;
    private FIELD coursecode;
    private FIELD college;
    private _User teacher;



}
