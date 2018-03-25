package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.bean.TCH_Progress;
import com.example.administrator.teacherhelper.bean.jiaoxue;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Adapter.jxjditemAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class jxjd_detial extends Activity {
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
    @Bind(R.id.course)
    TextView course;
    @Bind(R.id.year_semester)
    TextView yearSemester;
    @Bind(R.id.jxjd_fzr)
    TextView jxjdFzr;
    @Bind(R.id.major)
    TextView major;
    @Bind(R.id.grade_class)
    TextView gradeClass;
    @Bind(R.id.listt)
    ListView listt;

    jxjditemAdapter adapter;

    jiaoxue jiao = new jiaoxue();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jxjd_detial);
        ButterKnife.bind(this);
        first();
        initView();
        initData();
    }

    private void initData() {
        BmobQuery<TCH_Progress>  bjiaoxue = new BmobQuery<>();
        bjiaoxue.addWhereEqualTo("jiaoxue",jiao.getObjectId());
        bjiaoxue.order("-weekly");
        bjiaoxue.findObjects(new FindListener<TCH_Progress>() {
            @Override
            public void done(List<TCH_Progress> list, BmobException e) {
                if (e==null){
                    if (list.size()!=0){
                        adapter = new jxjditemAdapter(list,jxjd_detial.this);
                        listt.setAdapter(adapter);
                    }

                }else {
                    Toast.makeText(jxjd_detial.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initView() {
        title.setText("教学进度表");
        course.setText(jiao.getKe().getDespration()  + "   " + jiao.getKaikeyuan().getDespration());
        yearSemester.setText(jiao.getSchoolyear().getDespration());
        jxjdFzr.setText(jiao.getTeacher().getDesperation());
//        major.setText(jiao.getClasss().getMajor().getDespration());
//        gradeClass.setText(jiao.getClasss().getGrade().getDespration() + " 级"+ jiao.getClasss().getClasss().getDespration()+"班");
    }

    private void first() {
        jiao = (jiaoxue)getIntent().getSerializableExtra("jiaoxueid");
    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}
