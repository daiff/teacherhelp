package com.example.administrator.teacherhelper.Bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class book extends BmobObject {
    private String despration;
    private String Ed;
    private Boolean mainBook;
    private String NumberOfWords;
    private String Press;
    private String Publishing_time;

    public TCH_calender getTch_calender() {
        return tch_calender;
    }

    public void setTch_calender(TCH_calender tch_calender) {
        this.tch_calender = tch_calender;
    }

    public String getDespration() {
        return despration;
    }

    public void setDespration(String despration) {
        this.despration = despration;
    }

    public String getEd() {
        return Ed;
    }

    public void setEd(String ed) {
        Ed = ed;
    }

    public Boolean getMainBook() {
        return mainBook;
    }

    public void setMainBook(Boolean mainBook) {
        this.mainBook = mainBook;
    }

    public String getNumberOfWords() {
        return NumberOfWords;
    }

    public void setNumberOfWords(String numberOfWords) {
        NumberOfWords = numberOfWords;
    }

    public String getPress() {
        return Press;
    }

    public void setPress(String press) {
        Press = press;
    }

    public String getPublishing_time() {
        return Publishing_time;
    }

    public void setPublishing_time(String publishing_time) {
        Publishing_time = publishing_time;
    }

    private TCH_calender tch_calender;

}
