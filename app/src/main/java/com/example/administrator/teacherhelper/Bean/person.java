package com.example.administrator.teacherhelper.Bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2018/3/13 0013.
 */

public class person extends BmobUser {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesperation() {
        return desperation;
    }

    public void setDesperation(String desperation) {
        this.desperation = desperation;
    }





    private String desperation;
    private String title;

    public FIELD getXi() {
        return xi;
    }

    public void setXi(FIELD xi) {
        this.xi = xi;
    }

    private FIELD xi;
}
