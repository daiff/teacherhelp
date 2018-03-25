package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.TCH_analysis;
import com.example.administrator.teacherhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public class PaperAnalysis extends BaseAdapter {
    private List<TCH_analysis> stuList;
    private LayoutInflater inflater;

    public PaperAnalysis() {
    }

    public PaperAnalysis(List<TCH_analysis> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public TCH_analysis getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.my_coursedetail, null);
        TCH_analysis student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView course_code = (TextView) view.findViewById(R.id.course_code);
        TextView schedule = (TextView) view.findViewById(R.id.schedule);
        TextView nature = (TextView) view.findViewById(R.id.nature);
        TextView kaikeyuan = (TextView) view.findViewById(R.id.ban);

        course_code.setText(student.getJiaoxue().getKe().getDespration());
        schedule.setText(student.getJiaoxue().getKe().getCourse_code());
        nature.setText(student.getJiaoxue().getNature().getDespration());
        kaikeyuan.setText(student.getJiaoxue().getKaikeyuan().getDespration());

        return view;
    }
}
