package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.STUDENT;
import com.example.administrator.teacherhelper.R;

import java.util.List;



/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class zcj_studentAdapter  extends BaseAdapter {


    private List<STUDENT> stuList;
    private LayoutInflater inflater;

    public zcj_studentAdapter() {

    }

    public zcj_studentAdapter(List<STUDENT> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public STUDENT getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.zcj_additem, null);
        STUDENT student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView xuehao = (TextView) view.findViewById(R.id.zcj_xuehao);
        TextView xingmin = (TextView) view.findViewById(R.id.zcj_mingzi);

        xuehao.setText(student.getNumber());
        xingmin.setText(student.getDespration());

        return view;
    }
}