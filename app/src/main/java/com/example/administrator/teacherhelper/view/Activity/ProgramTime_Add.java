package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.TCH_program;
import com.example.administrator.teacherhelper.bean.TPR_schedule;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public class ProgramTime_Add extends Activity {
    String programid;
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
    @Bind(R.id.chapter)
    EditText chapter;
    @Bind(R.id.basic)
    EditText basic;
    @Bind(R.id.Theoretical_hours)
    EditText TheoreticalHours;
    @Bind(R.id.Experiment)
    EditText Experiment;
    @Bind(R.id.courses_content)
    EditText coursesContent;
    @Bind(R.id.teach_require)
    EditText teachRequire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_timeadd);
        ButterKnife.bind(this);
        first();
        initView();
    }

    private void initView() {
        title.setText("新增学时安排");
        save.setVisibility(View.VISIBLE);
    }

    private void first() {
        programid = getIntent().getStringExtra("programid");
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                save();
                break;
        }
    }

    private void save() {
        TPR_schedule schedule = new TPR_schedule();
        schedule.setBasic(basic.getText().toString());
        schedule.setChapter(chapter.getText().toString());
        schedule.setTheoretical_hours(TheoreticalHours.getText().toString());
        schedule.setExperiment(Experiment.getText().toString());
        schedule.setCourses_content(coursesContent.getText().toString());
        schedule.setTeach_require(teachRequire.getText().toString());

        TCH_program  pro = new TCH_program();
        pro.setObjectId(programid);

        schedule.setTch_program(pro);
        schedule.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    Toast.makeText(ProgramTime_Add.this, "保存成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ProgramTime_Add.this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
