package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.ACHIEVEMENT;

import java.util.List;

/**
 * Created by Administrator on 2018/5/2 0002.
 */

public class AchieveAdapter  extends BaseAdapter {
    private List<ACHIEVEMENT> stuList;
    private LayoutInflater inflater;

    public AchieveAdapter() {
    }

    public AchieveAdapter(List<ACHIEVEMENT> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public ACHIEVEMENT getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.innovate_item, null);
        ACHIEVEMENT student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView course_code = (TextView) view.findViewById(R.id.gzzj_course);

        course_code.setText(student.getTeach().getTeam().getGrade().getDespration()+"级"
                +student.getTeach().getTeam().getMajor().getDespration()
                +student.getTeach().getTeam().getClasss().getDespration()+"班"
                +student.getTeach().getCourse().getDespration()+"     "
                +student.getFile().getFilename());
        return view;
    }
}
