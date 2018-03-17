package com.example.administrator.teacherhelper.view.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.teacherhelper.Bean.MATERIAL;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Activity.LoginActivity;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class materialAdapter extends BaseAdapter {

    private List<MATERIAL> stuList;
    private LayoutInflater inflater;

    public materialAdapter() {
    }

    public materialAdapter(List<MATERIAL> stuList, Context context) {
        this.stuList = stuList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return stuList == null ? 0 : stuList.size();
    }

    @Override
    public MATERIAL getItem(int position) {
        return stuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载布局为一个视图
        View view = inflater.inflate(R.layout.my_download, null);
        MATERIAL student = getItem(position);
        //在view视图中查找id为image_photo的控件
        TextView tv_name = (TextView) view.findViewById(R.id.dowload);
        ImageView down = (ImageView) view.findViewById(R.id.downn);
        tv_name.setText(student.getMaterial().getFilename());
        File file = new File("/sdcard/Teacher/"+student.getMaterial().getFilename());
        if (file.exists()){
            down.setVisibility(View.VISIBLE);
        }else{
        }
        return view;
    }
}
