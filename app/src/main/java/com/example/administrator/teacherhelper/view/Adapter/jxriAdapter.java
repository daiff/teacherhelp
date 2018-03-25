package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.TCH_calender;
import com.example.administrator.teacherhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class jxriAdapter  extends BaseAdapter {
    private List<TCH_calender> stuList;
    private LayoutInflater inflater;

    public jxriAdapter() {
    }

    public jxriAdapter(List<TCH_calender> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public TCH_calender getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.my_coursedetail, null);
        TCH_calender student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView course_code = (TextView) view.findViewById(R.id.course_code);
        TextView schedule = (TextView) view.findViewById(R.id.ban);

        course_code.setText(student.getJiaoxue().getKe().getDespration());
//        schedule.setText(student.getJiaoxue().getClasss().getGrade().getDespration()+"级"+student.getJiaoxue().getClasss().getMajor().getDespration()+student.getJiaoxue().getClasss().getClasss().getDespration()+"班");
        return view;
    }
}
