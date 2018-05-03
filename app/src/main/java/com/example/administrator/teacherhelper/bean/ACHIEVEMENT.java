package com.example.administrator.teacherhelper.bean;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class ACHIEVEMENT extends BmobObject {
    public TEACH getTeach() {
        return Teach;
    }

    public void setTeach(TEACH teach) {
        Teach = teach;
    }

    public BmobFile getFile() {
        return file;
    }

    public void setFile(BmobFile file) {
        this.file = file;
    }

    private TEACH Teach;
    private BmobFile file;
}
