package com.example.administrator.teacherhelper.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class TEACH extends BmobObject {
    private FIELD College;
    private FIELD Course;
    private FIELD Nature;
    private FIELD Schoolyear;
    private classs Team;
    private person Teacher;
    private String Credit;

    public BmobRelation getBook() {
        return Book;
    }

    public void setBook(BmobRelation book) {
        Book = book;
    }

    private BmobRelation Book;

    public String getCredit() {
        return Credit;
    }

    public void setCredit(String credit) {
        Credit = credit;
    }

    public person getTeacher() {
        return Teacher;
    }

    public void setTeacher(person teacher) {
        Teacher = teacher;
    }

    public classs getTeam() {
        return Team;
    }

    public void setTeam(classs team) {
        Team = team;
    }

    public FIELD getSchoolyear() {
        return Schoolyear;
    }

    public void setSchoolyear(FIELD schoolyear) {
        Schoolyear = schoolyear;
    }

    public FIELD getNature() {
        return Nature;
    }

    public void setNature(FIELD nature) {
        Nature = nature;
    }

    public FIELD getCourse() {
        return Course;
    }

    public void setCourse(FIELD course) {
        Course = course;
    }

    public FIELD getCollege() {
        return College;
    }

    public void setCollege(FIELD college) {
        College = college;
    }


}
