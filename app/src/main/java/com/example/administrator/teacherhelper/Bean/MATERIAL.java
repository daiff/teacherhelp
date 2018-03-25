package com.example.administrator.teacherhelper.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class MATERIAL extends BmobObject {


    public BmobFile getMaterial() {
        return material;
    }

    public void setMaterial(BmobFile material) {
        this.material = material;
    }

    private BmobFile material;


}
