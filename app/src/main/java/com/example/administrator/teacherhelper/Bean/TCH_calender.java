package com.example.administrator.teacherhelper.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class TCH_calender extends BmobObject {
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(String end_hour) {
        this.end_hour = end_hour;
    }

    public String getEnd_number() {
        return end_number;
    }

    public void setEnd_number(String end_number) {
        this.end_number = end_number;
    }

    public String getMid_number() {
        return mid_number;
    }

    public void setMid_number(String mid_number) {
        this.mid_number = mid_number;
    }

    public String getMid_hour() {
        return mid_hour;
    }

    public void setMid_hour(String mid_hour) {
        this.mid_hour = mid_hour;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getTotal_weeks() {
        return total_weeks;
    }

    public void setTotal_weeks(String total_weeks) {
        this.total_weeks = total_weeks;
    }

    private String data  ;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getHavedone() {
        return havedone;
    }

    public void setHavedone(String havedone) {
        this.havedone = havedone;
    }

    public com.example.administrator.teacherhelper.bean.jiaoxue getJiaoxue() {
        return jiaoxue;
    }

    public void setJiaoxue(com.example.administrator.teacherhelper.bean.jiaoxue jiaoxue) {
        this.jiaoxue = jiaoxue;
    }



    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    private String rank  ;
    private String  objective ;
    private String  end_hour ;
    private String  end_number ;
    private String  mid_hour ;
    private String  mid_number ;
    private String  havedone ;
    private String  total_weeks ;
    private String  classs ;
    private jiaoxue  jiaoxue ;

}
