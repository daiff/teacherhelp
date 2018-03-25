package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.jiaoxue;
import com.example.administrator.teacherhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class jcourseAdapter extends BaseAdapter {
    private List<jiaoxue> stuList;
    private LayoutInflater inflater;

    public jcourseAdapter() {
    }

    public jcourseAdapter(List<jiaoxue> stuList, Context context) {
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
        View view = inflater.inflate(R.layout.gzzj, null);
        jiaoxue student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView course_code = (TextView) view.findViewById(R.id.gzzj_course);
        TextView schedule = (TextView) view.findViewById(R.id.gzzj_nature);
        TextView title = (TextView) view.findViewById(R.id.gzzj_class);

        course_code.setText(student.getKe().getDespration() + "  " + student.getKe().getCourse_code());
        schedule.setText("学分:" + student.getKe().getCredit());
//        title.setText(student.getClasss().getGrade().getDespration()+"级"+student.getClasss().getMajor().getDespration()+student.getClasss().getClasss().getDespration()+"班");
        return view;
    }
}