package com.example.administrator.teacherhelper.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public class TCH_analysis extends BmobObject {
    private String com_analysis ;
    private String  zhurdata;
    private String zero_prop ;
    private String  zero_num;

    public TEACH getCourse() {
        return Course;
    }

    public void setCourse(TEACH course) {
        Course = course;
    }

    private TEACH Course ;
    private String  tatalpeoplenum;
    private String six_prop ;
    private String six_num ;
    private String seven_prop ;
    private String seven_num ;
    private String nine_prop ;
    private String  nine_num;
    private String minimum_score ;
    private String jshqianmin ;
    private String jshdata ;
    private String highest_score ;
    private String eight_prop ;
    private String eight_num ;

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    private String classs ;

    public String getZhurqianmin() {
        return zhurqianmin;
    }

    public void setZhurqianmin(String zhurqianmin) {
        this.zhurqianmin = zhurqianmin;
    }

    public String getEight_num() {
        return eight_num;
    }

    public void setEight_num(String eight_num) {
        this.eight_num = eight_num;
    }

    public String getEight_prop() {
        return eight_prop;
    }

    public void setEight_prop(String eight_prop) {
        this.eight_prop = eight_prop;
    }

    public String getHighest_score() {
        return highest_score;
    }

    public void setHighest_score(String highest_score) {
        this.highest_score = highest_score;
    }

    public String getJshdata() {
        return jshdata;
    }

    public void setJshdata(String jshdata) {
        this.jshdata = jshdata;
    }

    public String getJshqianmin() {
        return jshqianmin;
    }

    public void setJshqianmin(String jshqianmin) {
        this.jshqianmin = jshqianmin;
    }

    public String getMinimum_score() {
        return minimum_score;
    }

    public void setMinimum_score(String minimum_score) {
        this.minimum_score = minimum_score;
    }

    public String getNine_num() {
        return nine_num;
    }

    public void setNine_num(String nine_num) {
        this.nine_num = nine_num;
    }

    public String getNine_prop() {
        return nine_prop;
    }

    public void setNine_prop(String nine_prop) {
        this.nine_prop = nine_prop;
    }

    public String getSeven_num() {
        return seven_num;
    }

    public void setSeven_num(String seven_num) {
        this.seven_num = seven_num;
    }

    public String getSeven_prop() {
        return seven_prop;
    }

    public void setSeven_prop(String seven_prop) {
        this.seven_prop = seven_prop;
    }

    public String getSix_num() {
        return six_num;
    }

    public void setSix_num(String six_num) {
        this.six_num = six_num;
    }

    public String getSix_prop() {
        return six_prop;
    }

    public void setSix_prop(String six_prop) {
        this.six_prop = six_prop;
    }

    public String getTatalpeoplenum() {
        return tatalpeoplenum;
    }

    public void setTatalpeoplenum(String tatalpeoplenum) {
        this.tatalpeoplenum = tatalpeoplenum;
    }


    public String getZero_num() {
        return zero_num;
    }

    public void setZero_num(String zero_num) {
        this.zero_num = zero_num;
    }

    public String getZero_prop() {
        return zero_prop;
    }

    public void setZero_prop(String zero_prop) {
        this.zero_prop = zero_prop;
    }

    public String getZhurdata() {
        return zhurdata;
    }

    public void setZhurdata(String zhurdata) {
        this.zhurdata = zhurdata;
    }

    public String getCom_analysis() {
        return com_analysis;
    }

    public void setCom_analysis(String com_analysis) {
        this.com_analysis = com_analysis;
    }

    private String zhurqianmin ;
}
