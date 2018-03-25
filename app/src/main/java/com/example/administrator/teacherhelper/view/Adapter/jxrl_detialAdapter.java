package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.TCA_detail;
import com.example.administrator.teacherhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class jxrl_detialAdapter extends BaseAdapter {

    private List<TCA_detail> stuList;
    private LayoutInflater inflater;

    public jxrl_detialAdapter() {
    }

    public jxrl_detialAdapter(List<TCA_detail> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public TCA_detail getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载布局为一个视图
        View view = inflater.inflate(R.layout.jxrl_item, null);
        TCA_detail student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView m_d = (TextView) view.findViewById(R.id.yueri);
        TextView time = (TextView) view.findViewById(R.id.zhc);
        TextView skss = (TextView) view.findViewById(R.id.skss);
        TextView jxnr = (TextView) view.findViewById(R.id.nr);
        TextView kw = (TextView) view.findViewById(R.id.kwzx);
        TextView qz = (TextView) view.findViewById(R.id.qz);
        TextView bz = (TextView) view.findViewById(R.id.bz);

        m_d.setText(student.getApril());
        time.setText(student.getWeekly());
        skss.setText(student.getHour());
        jxnr.setText(student.getContent());
        qz.setText(student.getHomwork_hour());
        kw.setText(student.getSelf_hour());
        bz.setText(student.getRemarks());

        return view;
    }
}