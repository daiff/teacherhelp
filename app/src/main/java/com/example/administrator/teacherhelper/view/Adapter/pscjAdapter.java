package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.TCH_achievement;
import com.example.administrator.teacherhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class pscjAdapter extends BaseAdapter {
    private List<TCH_achievement> stuList;
    private LayoutInflater inflater;

    public pscjAdapter() {
    }

    public pscjAdapter(List<TCH_achievement> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public TCH_achievement getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.pscj_item, null);
        TCH_achievement student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView xuehao = (TextView) view.findViewById(R.id.pscj_num);
        TextView xingmin = (TextView) view.findViewById(R.id.pscj_name);
        TextView shyan = (TextView) view.findViewById(R.id.kq);
        TextView psh = (TextView) view.findViewById(R.id.zy);
        TextView qmo = (TextView) view.findViewById(R.id.sy);
        TextView zp = (TextView) view.findViewById(R.id.qt);
        TextView cj = (TextView) view.findViewById(R.id.cj);
        TextView bz = (TextView) view.findViewById(R.id.bz);


        xuehao.setText(student.getStudentid().getNumber());
        xingmin.setText(student.getStudentid().getDespration());
        shyan.setText(student.getKaoqin());
        psh.setText(student.getHomework());

        qmo.setText(student.getShiyan());
        zp.setText(student.getOther());
        cj.setText(student.getPs_achieve());
        bz.setText(student.getRemark());

        return view;
    }
}
