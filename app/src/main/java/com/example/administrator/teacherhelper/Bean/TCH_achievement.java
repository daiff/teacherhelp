package com.example.administrator.teacherhelper.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2018/3/22 0022.
 */

public class TCH_achievement extends BmobObject {
    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public String getZp_pingsh() {
        return zp_pingsh;
    }

    public void setZp_pingsh(String zp_pingsh) {
        this.zp_pingsh = zp_pingsh;
    }

    public String getZongpin() {
        return zongpin;
    }

    public void setZongpin(String zongpin) {
        this.zongpin = zongpin;
    }

    public String getShiyan() {
        return shiyan;
    }

    public void setShiyan(String shiyan) {
        this.shiyan = shiyan;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getQimo() {
        return qimo;
    }

    public void setQimo(String qimo) {
        this.qimo = qimo;
    }

    public String getPs_achieve() {
        return ps_achieve;
    }

    public void setPs_achieve(String ps_achieve) {
        this.ps_achieve = ps_achieve;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getKaoqin() {
        return kaoqin;
    }

    public void setKaoqin(String kaoqin) {
        this.kaoqin = kaoqin;
    }

    public String getZp_shiyan() {
        return zp_shiyan;
    }

    public void setZp_shiyan(String zp_shiyan) {
        this.zp_shiyan = zp_shiyan;
    }

    public com.example.administrator.teacherhelper.bean.jiaoxue getJiaoxue() {
        return jiaoxue;
    }

    public void setJiaoxue(com.example.administrator.teacherhelper.bean.jiaoxue jiaoxue) {
        this.jiaoxue = jiaoxue;
    }

    public STUDENT getStudentid() {
        return studentid;
    }

    public void setStudentid(STUDENT studentid) {
        this.studentid = studentid;
    }

    private String  homework ;
    private String   zp_pingsh;
    private String  zongpin ;
    private String shiyan  ;
    private String  remark ;
    private String  qimo ;
    private String ps_achieve  ;
    private String  other ;
    private String  kaoqin ;
    private String  zp_shiyan ;
    private jiaoxue  jiaoxue ;
    private STUDENT   studentid;
}
