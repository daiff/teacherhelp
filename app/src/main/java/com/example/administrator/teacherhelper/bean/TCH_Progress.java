package com.example.administrator.teacherhelper.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class TCH_Progress extends BmobObject {
    private String chapter  ;
    private String  content ;
    private String  hour ;
    private TCH_pro  tch_pro ;
    private String  objective ;
    private String  Remarks ;
    private String  task_hour ;
    private String  Teaching_methods ;
    private String  weekly ;

    public String getWeekly() {
        return weekly;
    }

    public void setWeekly(String weekly) {
        this.weekly = weekly;
    }

    public String getTask_hour() {
        return task_hour;
    }

    public void setTask_hour(String task_hour) {
        this.task_hour = task_hour;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }


    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getTeaching_methods() {
        return Teaching_methods;
    }

    public void setTeaching_methods(String teaching_methods) {
        Teaching_methods = teaching_methods;
    }



    public TCH_pro getTch_pro() {
        return tch_pro;
    }

    public void setTch_pro(TCH_pro tch_pro) {
        this.tch_pro = tch_pro;
    }
}
