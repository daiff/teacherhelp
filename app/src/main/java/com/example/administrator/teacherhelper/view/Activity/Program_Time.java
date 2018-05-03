package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.bean.TPR_schedule;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Adapter.Program_Content;
import com.example.administrator.teacherhelper.view.Adapter.Program_TimeAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/3/24 0024.
 *
 */

public class Program_Time extends Activity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.back1)
    RelativeLayout back1;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.re_title)
    RelativeLayout reTitle;
    @Bind(R.id.add)
    ImageButton add;
    @Bind(R.id.save)
    ImageButton save;
    @Bind(R.id.tishi)
    ImageButton tishi;
    @Bind(R.id.right_button)
    RelativeLayout rightButton;
    @Bind(R.id.listt)
    ListView listt;
    String programid;
    Program_TimeAdapter adapter;
    Program_Content adapter1;
    String source;
    String resource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapteractivity);
        ButterKnife.bind(this);
        first();
        initView();
        initData();
    }

    private void initData() {
        BmobQuery<TPR_schedule> bschedule = new BmobQuery<>();
        bschedule.addWhereEqualTo("tch_program",programid);
        bschedule.order("-chapter");
        bschedule.findObjects(new FindListener<TPR_schedule>() {
            @Override
            public void done(List<TPR_schedule> list, BmobException e) {
                if (e==null){
                    if (list.size()!=0){
                        if (source.equals("time")) {
                            adapter = new Program_TimeAdapter(list, Program_Time.this);
                            listt.setAdapter(adapter);
                        }else {
                            adapter1 = new Program_Content(list, Program_Time.this);
                            listt.setAdapter(adapter1);
                        }
                    }else
                    {
                        Toast.makeText(Program_Time.this, "没有要显示的数据", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Program_Time.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initView() {
        if (source.equals("time")) {
            title.setText("学时安排");
        }else{
            title.setText("课程教学内容与要求");
        }
        if (resource.equals("add")){
            add.setVisibility(View.VISIBLE);
            rightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Program_Time.this,ProgramTime_Add.class);
                    intent.putExtra("programid",programid);
                    startActivity(intent);
                }
            });
        }
    }

    private void first() {
        programid = getIntent().getStringExtra("programid");
        source = getIntent().getStringExtra("souce");
        resource = getIntent().getStringExtra("resource");
    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }
}
