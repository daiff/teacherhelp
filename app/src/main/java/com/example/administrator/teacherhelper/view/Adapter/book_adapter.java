package com.example.administrator.teacherhelper.view.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.book;
import com.example.administrator.teacherhelper.R;

import java.util.List;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class book_adapter extends BaseAdapter {
    private List<book> stuList;
    private LayoutInflater inflater;

    public book_adapter() {
    }

    public book_adapter(List<book> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public book getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.select_book, null);
        book worksum = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView course_code = (TextView) view.findViewById(R.id.bookname);
        TextView schedule = (TextView) view.findViewById(R.id.bookbianzhu);
        TextView classs = (TextView) view.findViewById(R.id.bookchuban);
        TextView shijian = (TextView) view.findViewById(R.id.shijian);

        course_code.setText(worksum.getDespration() + "  (" + worksum.getNumberOfWords() + ")字");
        schedule.setText(worksum.getEd());
        classs.setText(worksum.getPress());
        shijian.setText(worksum.getPublishing_time());
        return view;
    }
}
