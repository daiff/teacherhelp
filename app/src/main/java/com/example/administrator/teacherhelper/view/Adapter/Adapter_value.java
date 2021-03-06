package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.FIELD;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20 0020.
 */

public class Adapter_value extends BaseAdapter {
    private List<FIELD> stuList;
    private LayoutInflater inflater;

    public Adapter_value() {
    }

    public Adapter_value(List<FIELD> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public FIELD getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.select_value, null);
        FIELD student = getItem(position);
        TextView course_code = (TextView) view.findViewById(R.id.value);
        if (student.getValue().equals(CommenDate.value_schele)){
            course_code.setText(student.getDespration() + "   编码：" + student.getCourse_code());
        }else {
            course_code.setText(student.getDespration());
        }

        return view;
    }
}