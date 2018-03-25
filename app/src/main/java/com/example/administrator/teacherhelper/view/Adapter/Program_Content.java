package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.TPR_schedule;
import com.example.administrator.teacherhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class Program_Content extends BaseAdapter {
    private List<TPR_schedule> stuList;
    private LayoutInflater inflater;

    public Program_Content() {
    }

    public Program_Content(List<TPR_schedule> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public TPR_schedule getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.program_content, null);
        TPR_schedule student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView weekly = (TextView) view.findViewById(R.id.chapter);
        TextView hour = (TextView) view.findViewById(R.id.content);
        TextView Teaching_methods = (TextView) view.findViewById(R.id.Remarks);


        weekly.setText(student.getChapter());
        hour.setText(student.getCourses_content());
        Teaching_methods.setText(student.getTeach_require());
        return view;
    }
}