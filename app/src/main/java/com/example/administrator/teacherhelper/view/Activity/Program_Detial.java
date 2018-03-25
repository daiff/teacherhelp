package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.TCH_program;
import com.example.administrator.teacherhelper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class Program_Detial extends Activity {
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
    @Bind(R.id.jxjd_course)
    TextView jxjdCourse;
    @Bind(R.id.jxjd_year)
    TextView jxjdYear;
    @Bind(R.id.jxdg_credit)
    TextView jxdgCredit;
    @Bind(R.id.Hours)
    EditText Hours;
    @Bind(R.id.Object_oriented)
    EditText ObjectOriented;
    @Bind(R.id.major)
    EditText major;
    @Bind(R.id.englishname)
    EditText englishname;
    @Bind(R.id.object)
    LinearLayout object;
    @Bind(R.id.xueshianpai)
    LinearLayout xueshianpai;
    @Bind(R.id.jxnryyq)
    LinearLayout jxnryyq;
    @Bind(R.id.ability)
    LinearLayout ability;
    @Bind(R.id.contact)
    LinearLayout contact;
    @Bind(R.id.check)
    LinearLayout check;
    @Bind(R.id.book)
    LinearLayout book;
    TCH_program program = new TCH_program();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_detial);
        ButterKnife.bind(this);
        first();
        initView();
    }

    private void initView() {
        title.setText("教学大纲");
        jxjdCourse.setText(program.getJiaoxue().getKe().getDespration() + "(" + program.getJiaoxue().getNature().getDespration() + ")");
        jxjdYear.setText(program.getJiaoxue().getSchoolyear().getDespration());
        jxdgCredit.setText(program.getJiaoxue().getKe().getCredit());
        Hours.setText(program.getHours());
        ObjectOriented.setText(program.getObject_oriented());
        major.setText(program.getMajor());
        englishname.setText(program.getEnglishname());
    }

    private void first() {
        program = (TCH_program) getIntent().getSerializableExtra("tch_program");
    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.xueshianpai, R.id.jxnryyq, R.id.ability, R.id.contact, R.id.check, R.id.book,R.id.object})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xueshianpai:
                Intent intent1 = new Intent(Program_Detial.this,Program_Time.class);
                intent1.putExtra("programid",program.getObjectId());
                intent1.putExtra("souce","time");
                intent1.putExtra("resource","detial");
                startActivity(intent1);
                break;
            case R.id.jxnryyq:
                Intent intent2 = new Intent(Program_Detial.this,Program_Time.class);
                intent2.putExtra("programid",program.getObjectId());
                intent2.putExtra("souce","");
                intent2.putExtra("resource","detial");
                startActivity(intent2);
                break;
            case R.id.ability:
                Intent intent3 = new Intent(Program_Detial.this,Program_Object.class);
                intent3.putExtra("object",program.getAbility());
                intent3.putExtra("key","对学生能力培养的要求");
                intent3.putExtra("programid","");
                startActivity(intent3);
                break;
            case R.id.contact:
                Intent intent4 = new Intent(Program_Detial.this,Program_Object.class);
                intent4.putExtra("object",program.getContact());
                intent4.putExtra("key","各课程的联系");
                intent4.putExtra("programid","");
                startActivity(intent4);
                break;
            case R.id.check:
                Intent intent5 = new Intent(Program_Detial.this,Program_Object.class);
                intent5.putExtra("object",program.getCheck());
                intent5.putExtra("key","考核方式");
                intent5.putExtra("programid","");
                startActivity(intent5);
                break;
            case R.id.book:
                break;
            case R.id.object:
                Intent intent = new Intent(Program_Detial.this,Program_Object.class);
                intent.putExtra("object",program.getObject());
                intent.putExtra("key","课程的任务和目的");
                intent.putExtra("programid","");
                startActivity(intent);
                break;
        }
    }
}
