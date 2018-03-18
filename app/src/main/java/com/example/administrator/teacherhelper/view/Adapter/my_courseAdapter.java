package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.Bean.MATERIAL;
import com.example.administrator.teacherhelper.Bean.jiaoxue;
import com.example.administrator.teacherhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public class my_courseAdapter extends BaseAdapter {

    private List<jiaoxue> stuList;
    private LayoutInflater inflater;

    public my_courseAdapter() {
    }

    public my_courseAdapter(List<jiaoxue> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public jiaoxue getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载布局为一个视图
        View view = inflater.inflate(R.layout.my_coursedetail, null);
        jiaoxue student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView course_code = (TextView) view.findViewById(R.id.course_code);
        TextView schedule = (TextView) view.findViewById(R.id.schedule);
        TextView nature = (TextView) view.findViewById(R.id.nature);
        TextView ban = (TextView) view.findViewById(R.id.ban);
        TextView college = (TextView) view.findViewById(R.id.college);

        course_code.setText(student.getKe().getCourse_code());
        schedule.setText(student.getKe().getDespration());
        nature.setText(student.getNature().getDespration());
        ban.setText(student.getGrade().getDespration()+"级"+student.getMajor().getDespration()+student.getClasss().getDespration()+"班");
        college.setText(student.getCollege().getDespration());
        return view;
    }

}