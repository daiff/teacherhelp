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

    public FIELD getNature() {
        return nature;
    }

    public void setNature(FIELD nature) {
        this.nature = nature;
    }

    public FIELD getKaikeyuan() {
        return kaikeyuan;
    }

    public void setKaikeyuan(FIELD kaikeyuan) {
        this.kaikeyuan = kaikeyuan;
    }

    public com.example.administrator.teacherhelper.Bean.book getBook() {
        return book;
    }

    public void setBook(com.example.administrator.teacherhelper.Bean.book book) {
        this.book = book;
    }

    public com.example.administrator.teacherhelper.Bean.classs getClasss() {
        return classs;
    }

    public void setClasss(com.example.administrator.teacherhelper.Bean.classs classs) {
        this.classs = classs;
    }

    public FIELD getSchoolyear() {
        return schoolyear;
    }

    public void setSchoolyear(FIELD schoolyear) {
        this.schoolyear = schoolyear;
    }

    public person getTeacher() {
        return teacher;
    }

    public void setTeacher(person teacher) {
        this.teacher = teacher;
    }

    private FIELD ke;//
    private FIELD nature;//
    private FIELD kaikeyuan;//
    private book book;//
    private classs classs;//
    private FIELD schoolyear;//
    private person teacher;//


}
