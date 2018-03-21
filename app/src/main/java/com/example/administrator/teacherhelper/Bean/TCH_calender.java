package com.example.administrator.teacherhelper.Bean;

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

    public jiaoxue getTeach() {
        return teach;
    }

    public void setTeach(jiaoxue teach) {
        this.teach = teach;
    }

    private String data  ;
    private String  end_hour ;
    private String  end_number ;
    private String  mid_hour ;
    private String  mid_number ;
    private String  objective ;
    private String  total_weeks ;
    private jiaoxue  teach ;

}
