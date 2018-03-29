package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.TCH_pro;

import java.util.List;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public class Speed extends BaseAdapter {
    private List<TCH_pro> stuList;
    private LayoutInflater inflater;

    public Speed() {
    }

    public Speed(List<TCH_pro> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public TCH_pro getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.summary_item, null);
        TCH_pro student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView course_code = (TextView) view.findViewById(R.id.gzzj_course);
        TextView title = (TextView) view.findViewById(R.id.gzzj_class);

        course_code.setText(student.getJiaoxue().getKe().getDespration());
        title.setText(student.getClasss());
        return view;
    }
}