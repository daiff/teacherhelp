package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.TEACH;
import com.example.administrator.teacherhelper.bean.jiaoxue;
import com.example.administrator.teacherhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public class my_courseAdapter extends BaseAdapter {

    private List<TEACH> stuList;
    private LayoutInflater inflater;

    public my_courseAdapter() {
    }

    public my_courseAdapter(List<TEACH> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public TEACH getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载布局为一个视图
        View view = inflater.inflate(R.layout.max_course, null);
        TEACH student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView course = (TextView) view.findViewById(R.id.co_co);
        TextView teacher = (TextView) view.findViewById(R.id.co_tea);
        TextView kaikeyuan = (TextView) view.findViewById(R.id.co_kaike);
        TextView nature = (TextView) view.findViewById(R.id.co_nature);
        TextView schoolyear = (TextView) view.findViewById(R.id.co_sch);
        TextView classs = (TextView) view.findViewById(R.id.co_class);
        TextView credit = (TextView) view.findViewById(R.id.co_credit);

        course.setText(student.getCourse().getDespration());
        teacher.setText(student.getTeacher().getDesperation());
        kaikeyuan.setText(student.getCollege().getDespration());
        nature.setText(student.getNature().getDespration());
        credit.setText(student.getCredit()+"分");
        schoolyear.setText(student.getSchoolyear().getDespration());
        classs.setText(student.getTeam().getCollege().getDespration()+student.getTeam().getGrade().getDespration()+"级"+student.getTeam().getMajor().getDespration()+student.getTeam().getClasss().getDespration()+"班");
        return view;
    }



}
