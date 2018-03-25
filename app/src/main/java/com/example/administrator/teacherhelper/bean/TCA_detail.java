package com.example.administrator.teacherhelper.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class TCA_detail extends BmobObject {
    public String getApril() {
        return April;
    }

    public void setApril(String april) {
        April = april;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getHomwork_hour() {
        return homwork_hour;
    }

    public void setHomwork_hour(String homwork_hour) {
        this.homwork_hour = homwork_hour;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getSelf_hour() {
        return self_hour;
    }

    public void setSelf_hour(String self_hour) {
        this.self_hour = self_hour;
    }

    public String getWeekly() {
        return Weekly;
    }

    public void setWeekly(String weekly) {
        Weekly = weekly;
    }

    public TCH_calender getTch_calender() {
        return tch_calender;
    }

    public void setTch_calender(TCH_calender tch_calender) {
        this.tch_calender = tch_calender;
    }

    private String April  ;
    private String  Content ;
    private String  homwork_hour ;
    private String  hour ;
    private String  Remarks ;
    private String  self_hour ;
    private String  Weekly ;
    private TCH_calender  tch_calender ;
}
