package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.TCH_program;
import com.example.administrator.teacherhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class Program_item extends BaseAdapter {
    private List<TCH_program> stuList;
    private LayoutInflater inflater;

    public Program_item() {
    }

    public Program_item(List<TCH_program> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public TCH_program getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.program_item, null);
        TCH_program student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView course_code = (TextView) view.findViewById(R.id.course_code);
        course_code.setText(student.getJiaoxue().getKe().getDespration());
        return view;
    }
}
