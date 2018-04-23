package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.NOTICE;

import java.util.List;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class Messege  extends BaseAdapter {
    private List<NOTICE> stuList;
    private LayoutInflater inflater;

    public Messege() {
    }

    public Messege(List<NOTICE> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public NOTICE getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.max_messegelist, null);
        NOTICE student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView course_code = (TextView) view.findViewById(R.id.messege_list);

        course_code.setText(student.getTitle());

        return view;
    }
}