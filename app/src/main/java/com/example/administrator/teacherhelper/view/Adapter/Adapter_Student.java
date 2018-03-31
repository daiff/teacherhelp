package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.STUDENT;

import java.util.List;

/**
 * Created by Administrator on 2018/3/31 0031.
 */

public class Adapter_Student extends BaseAdapter {
    private List<STUDENT> stuList;
    private LayoutInflater inflater;

    public Adapter_Student() {
    }

    public Adapter_Student(List<STUDENT> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public STUDENT getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.rtu_item, null);
        STUDENT student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView course_code = (TextView) view.findViewById(R.id.tv_name);

        course_code.setText(student.getNumber() + "    " + student.getDespration());
        return view;
    }
}
