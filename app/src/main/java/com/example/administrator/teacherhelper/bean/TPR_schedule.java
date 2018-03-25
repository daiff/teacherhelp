package com.example.administrator.teacherhelper.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class TPR_schedule extends BmobObject {
    public TCH_program getTch_program() {
        return tch_program;
    }

    public void setTch_program(TCH_program tch_program) {
        this.tch_program = tch_program;
    }

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getCourses_content() {
        return courses_content;
    }

    public void setCourses_content(String courses_content) {
        this.courses_content = courses_content;
    }

    public String getExperiment() {
        return Experiment;
    }

    public void setExperiment(String experiment) {
        Experiment = experiment;
    }

    public String getTeach_require() {
        return teach_require;
    }

    public void setTeach_require(String teach_require) {
        this.teach_require = teach_require;
    }

    public String getTheoretical_hours() {
        return Theoretical_hours;
    }

    public void setTheoretical_hours(String theoretical_hours) {
        Theoretical_hours = theoretical_hours;
    }

    private TCH_program tch_program;
    private String  basic;
    private String chapter ;
    private String courses_content ;
    private String Experiment ;
    private String teach_require ;
    private String Theoretical_hours ;
}
