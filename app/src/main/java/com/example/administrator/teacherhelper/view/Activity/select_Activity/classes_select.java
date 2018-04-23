package com.example.administrator.teacherhelper.view.Activity.select_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.classs;
import com.example.administrator.teacherhelper.bean.jiaoxue;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.view.Adapter.classsAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/4/1 0001.
 */

public class classes_select extends Activity {
    @Bind(R.id.back1)
    RelativeLayout back1;
    @Bind(R.id.listt)
    ListView listt;
    private String courseid;
    classsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapteractivity);
        ButterKnife.bind(this);
        first();
        initData();
    }

    private void initData() {
        BmobQuery<classs> classes = new BmobQuery<>();
        jiaoxue teach = new jiaoxue();
        teach.setObjectId(courseid);
        classes.include(CommenDate.include_classs);
        classes.addWhereRelatedTo("Team", new BmobPointer(teach));
        classes.findObjects(new FindListener<classs>() {
            @Override
            public void done(final List<classs> list, BmobException e) {
                if (e == null) {
                    if (list.size() != 0) {
                        adapter = new classsAdapter(list, classes_select.this);
                        listt.setAdapter(adapter);
                        listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent();
                                intent.putExtra("classes",list.get(position).getGrade().getDespration() + "级" + list.get(position).getMajor().getDespration()+ list.get(position).getClasss().getDespration()+"班");
                                setResult(CommenDate.select_class1,intent);
                                finish();
                            }
                        });
                    }
                }
            }
        });

    }

    private void first() {
        courseid = getIntent().getStringExtra("courseid");
    }
}
