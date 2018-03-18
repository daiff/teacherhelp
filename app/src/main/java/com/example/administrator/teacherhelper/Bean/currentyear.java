package com.example.administrator.teacherhelper.Bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public class currentyear extends BmobObject {
    public FIELD getYear() {
        return year;
    }

    public void setYear(FIELD year) {
        this.year = year;
    }

    private FIELD year;
}
