package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.Bean.classs;
import com.example.administrator.teacherhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class classsAdapter extends BaseAdapter {

    private List<classs> stuList;
    private LayoutInflater inflater;

    public classsAdapter() {
    }

    public classsAdapter(List<classs> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public classs getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载布局为一个视图
        View view = inflater.inflate(R.layout.max_class, null);
        classs student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView course = (TextView) view.findViewById(R.id.class_detial);
        TextView teacher = (TextView) view.findViewById(R.id.class_total);
        course.setText(student.getCollege().getDespration() + "  " + student.getGrade().getDespration() + "级" + student.getMajor().getDespration()+ student.getClasss().getDespration()+"班");
        teacher.setText(student.getTotal_person()+"人");
        return view;
    }



}