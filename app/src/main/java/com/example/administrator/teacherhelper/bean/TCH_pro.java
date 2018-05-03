package com.example.administrator.teacherhelper.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/4/26 0026.
 */

public class TCH_pro extends BmobObject {
    public TEACH getCourse() {
        return Course;
    }

    public void setCourse(TEACH course) {
        Course = course;
    }

    private TEACH Course;
}
