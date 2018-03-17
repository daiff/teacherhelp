package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.Bean.jiaoxue;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Adapter.my_courseAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/3/14 0014.
 */

public class my_course extends Activity {
    private static final String TAG = "my_course";
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.back1)
    RelativeLayout back1;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.add)
    ImageButton add;
    @Bind(R.id.save)
    ImageButton save;
    @Bind(R.id.listt)
    ListView listt;
    my_courseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapteractivity);
        ButterKnife.bind(this);
        Bmob.initialize(this, "ab8ec6ed95c785a2a470225606acee3e");
        init();
        getData();
    }

    private void init() {
        title.setText("本学期课程");
    }

    private void show(String msg) {
        Toast.makeText(my_course.this, msg, Toast.LENGTH_LONG).show();
    }

    private void getData() {
        BmobQuery<jiaoxue> b = new BmobQuery<>();
        b.include("teacher,ke,college,system,schoolyear,semester,major,grade,classs,nature");
        b.findObjects(new FindListener<jiaoxue>() {
            @Override
            public void done(List<jiaoxue> list, BmobException e) {
                adapter=new my_courseAdapter(list,my_course.this);
                listt.setAdapter(adapter);
            }
        });
    }


    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}
