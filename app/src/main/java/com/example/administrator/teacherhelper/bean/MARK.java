package com.example.administrator.teacherhelper.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2018/5/1 0001.
 */

public class MARK extends BmobObject {
    public TEACH getTeach() {
        return Teach;
    }

    public void setTeach(TEACH teach) {
        Teach = teach;
    }

    public BmobFile getFile() {
        return File;
    }

    public void setFile(BmobFile file) {
        File = file;
    }

    private TEACH Teach;
    private BmobFile File;
}
