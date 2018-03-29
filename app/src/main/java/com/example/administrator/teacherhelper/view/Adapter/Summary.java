package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.TCH_worksum;
import com.example.administrator.teacherhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class Summary extends BaseAdapter {
    private List<TCH_worksum> stuList;
    private LayoutInflater inflater;

    public Summary() {
    }

    public Summary(List<TCH_worksum> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public TCH_worksum getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.summary_item, null);
        TCH_worksum worksum = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView course_code = (TextView) view.findViewById(R.id.gzzj_course);
        TextView classs = (TextView) view.findViewById(R.id.gzzj_class);

        course_code.setText(worksum.getTeach().getKe().getDespration());
        classs.setText(worksum.getClasss());
        return view;
    }
}