package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.TCH_Progress;
import com.example.administrator.teacherhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class SpeedContent extends BaseAdapter {
    private List<TCH_Progress> stuList;
    private LayoutInflater inflater;

    public SpeedContent() {
    }

    public SpeedContent(List<TCH_Progress> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public TCH_Progress getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.speed_itemcontent, null);
        TCH_Progress student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView weekly = (TextView) view.findViewById(R.id.weekly);
        TextView hour = (TextView) view.findViewById(R.id.hour);
        TextView Teaching_methods = (TextView) view.findViewById(R.id.Teaching_methods);
        TextView task_hour = (TextView) view.findViewById(R.id.task_hour);
        TextView chapter = (TextView) view.findViewById(R.id.chapter);
        TextView content = (TextView) view.findViewById(R.id.content);
        TextView objective = (TextView) view.findViewById(R.id.objective);
        TextView Remarks = (TextView) view.findViewById(R.id.Remarks);


        weekly.setText(student.getWeekly());
        hour.setText(student.getHour());
        Teaching_methods.setText(student.getTeaching_methods());
        task_hour.setText(student.getTask_hour());
        chapter.setText(student.getChapter());
        content.setText(student.getContent());
        objective.setText(student.getObjective());
        Remarks.setText(student.getRemarks());
        return view;
    }
}
