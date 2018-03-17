package com.example.administrator.teacherhelper.Bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/16 0016.
 */

public class Teach extends BmobObject {


    public FIELD getZhuanye() {
        return zhuanye;
    }

    public void setZhuanye(FIELD zhuanye) {
        this.zhuanye = zhuanye;
    }

    private FIELD zhuanye;
}
