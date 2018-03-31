package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.Innovate;
import com.example.administrator.teacherhelper.bean.STUDENT;
import com.example.administrator.teacherhelper.view.Adapter.InnovateStudent;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/3/31 0031.
 */

public class Innovate_Student extends Activity {
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
    String innovateid;
    InnovateStudent adapter;

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
        BmobQuery<STUDENT> student= new BmobQuery<>();
        student.include("classs");
        Innovate inno = new Innovate();
        inno.setObjectId(innovateid);
        student.addWhereRelatedTo("perrson",new BmobPointer(inno));
        student.findObjects(new FindListener<STUDENT>() {
            @Override
            public void done(List<STUDENT> list, BmobException e) {
                if (e==null){
                    if (list.size()!=0){
                        adapter = new InnovateStudent(list,Innovate_Student.this);
                        listt.setAdapter(adapter);
                    }

                }else {
                    Toast.makeText(Innovate_Student.this, "获取失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void first() {
        innovateid = getIntent().getStringExtra("innovateid");
    }

    private void initView() {
        title.setText("学生");
    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}
