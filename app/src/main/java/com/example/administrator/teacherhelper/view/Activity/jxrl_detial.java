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

import com.example.administrator.teacherhelper.bean.TCH_calender;
import com.example.administrator.teacherhelper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class jxrl_detial extends Activity {
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
    @Bind(R.id.xibie)
    TextView xibie;
    @Bind(R.id.failure_peoploname)
    TextView failurePeoploname;
    @Bind(R.id.zhuanye)
    TextView zhuanye;
    @Bind(R.id.major)
    TextView major;
    @Bind(R.id.banji)
    TextView banji;
    @Bind(R.id.grade_class)
    TextView gradeClass;
    @Bind(R.id.jxrl_teacher)
    TextView jxrlTeacher;
    @Bind(R.id.jxrl_title)
    TextView jxrlTitle;
    @Bind(R.id.jxrl_fdteacher)
    TextView jxrlFdteacher;
    @Bind(R.id.jxrl_fatitle)
    TextView jxrlFatitle;
    @Bind(R.id.mudi)
    LinearLayout mudi;
    @Bind(R.id.book)
    LinearLayout book;
    @Bind(R.id.anpai_detial)
    LinearLayout anpaiDetial;
    @Bind(R.id.jxrl_xiang)
    LinearLayout jxrlXiang;
    @Bind(R.id.jxrl_check)
    LinearLayout jxrlCheck;

    TCH_calender calender;
    @Bind(R.id.zzs)
    EditText zzs;
    @Bind(R.id.qzks)
    EditText qzks;
    @Bind(R.id.qz_zxss)
    EditText qzZxss;
    @Bind(R.id.qmks)
    EditText qmks;
    @Bind(R.id.qmks_xs)
    EditText qmksXs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jxrl_detial);
        ButterKnife.bind(this);
        first();
        initView();
        iinitData();

    }

    private void iinitData() {

    }

    private void initView() {
        title.setText("教学日历");
        course.setText(calender.getJiaoxue().getKe().getDespration() + " (" +
                calender.getJiaoxue().getNature().getDespration() + ")");
        yearSemester.setText(calender.getJiaoxue().getSchoolyear().getDespration());
//        failurePeoploname.setText(calender.getJiaoxue().getClasss().getMajor().getDespration());
//        major.setText(calender.getJiaoxue().getClasss().getMajor().getDespration());
//        gradeClass.setText(calender.getJiaoxue().getClasss().getGrade().getDespration() + "级" +calender.getJiaoxue().getClasss().getClasss().getDespration() + " 班");
        jxrlTeacher.setText(calender.getJiaoxue().getTeacher().getDesperation());
        jxrlTitle.setText(calender.getJiaoxue().getTeacher().getTitle());
        zzs.setText(calender.getTotal_weeks());
        qzks.setText(calender.getMid_number());
        qzZxss.setText(calender.getMid_hour());
        qmks.setText(calender.getEnd_number());
        qmksXs.setText(calender.getEnd_hour());
    }

    private void first() {
        calender = (TCH_calender) getIntent().getSerializableExtra("tch_calender");
    }

    @OnClick({R.id.back1, R.id.right_button, R.id.mudi, R.id.book, R.id.anpai_detial, R.id.jxrl_xiang, R.id.jxrl_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                break;
            case R.id.mudi:
                Intent intent = new Intent(jxrl_detial.this,jxrl_mudi.class);
                intent.putExtra("mudi",calender.getObjective());
                startActivity(intent);
                break;
            case R.id.book:
                Intent intent1 = new Intent(jxrl_detial.this,jxrl_book.class);
                intent1.putExtra("calenderid",calender.getObjectId());
                intent1.putExtra("book",calender.getJiaoxue().getBook());
                startActivity(intent1);
                break;
            case R.id.anpai_detial:
                Intent intent3 = new Intent(jxrl_detial.this,jxrl_time.class);
                intent3.putExtra("timedetial",calender.getTca_time());
                startActivity(intent3);
                break;
            case R.id.jxrl_xiang:
                Intent intent4 = new Intent(jxrl_detial.this,jxrl_ddetial.class);
                intent4.putExtra("calenderid",calender.getObjectId());
                startActivity(intent4);
                break;
            case R.id.jxrl_check:
                Intent intent2 = new Intent(jxrl_detial.this,jxrl_check.class);
                intent2.putExtra("check",calender);
                startActivity(intent2);
                break;
        }
    }
}
