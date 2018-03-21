package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.Bean.person;
import com.example.administrator.teacherhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class Adapter_teacher extends BaseAdapter {
    private List<person> stuList;
    private LayoutInflater inflater;

    public Adapter_teacher() {
    }

    public Adapter_teacher(List<person> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public person getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.max_teacher, null);
        person student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView course_code = (TextView) view.findViewById(R.id.gzzj_course);
        TextView schedule = (TextView) view.findViewById(R.id.my_class);
        TextView title = (TextView) view.findViewById(R.id.gzzj_nature);
        TextView email = (TextView) view.findViewById(R.id.email);
        TextView phone = (TextView) view.findViewById(R.id.phone);

        course_code.setText(student.getUsername() + "  " + student.getDesperation());
        schedule.setText(student.getXi().getDespration());
        title.setText(student.getTitle());
        email.setText(student.getEmail());
        phone.setText(student.getMobilePhoneNumber());
        return view;
    }
}
