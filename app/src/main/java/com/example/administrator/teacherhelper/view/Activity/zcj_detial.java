package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.bean.TCH_achievement;
import com.example.administrator.teacherhelper.bean.jiaoxue;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Adapter.pscjAdapter;
import com.example.administrator.teacherhelper.view.Adapter.zcjAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/3/22 0022.
 */

public class zcj_detial extends Activity {
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
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.listt)
    ListView listt;

    jiaoxue teach;
    @Bind(R.id.classs)
    TextView classs;
    zcjAdapter adapter;
    String source;
    pscjAdapter psadapter;
    @Bind(R.id.liner)
    LinearLayout liner;
    @Bind(R.id.viewzcj)
    View viewzcj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zcj_detail);
        ButterKnife.bind(this);
        first();
        initView();
        initData();
    }

    private void initData() {
        BmobQuery<TCH_achievement> achieve = new BmobQuery<>();
        achieve.include("studentid");
        achieve.addWhereEqualTo("jiaoxue", teach.getObjectId());
        achieve.findObjects(new FindListener<TCH_achievement>() {
            @Override
            public void done(List<TCH_achievement> list, BmobException e) {
                if (e == null) {
                    if (source.equals("zcj")) {
                        adapter = new zcjAdapter(list, zcj_detial.this);
                        listt.setAdapter(adapter);
                    } else {
                        psadapter = new pscjAdapter(list, zcj_detial.this);
                        listt.setAdapter(psadapter);
                    }


                } else {
                    Toast.makeText(zcj_detial.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void first() {
        teach = (jiaoxue) getIntent().getSerializableExtra("jiaoxueid");
        source = getIntent().getStringExtra("source");
    }

    private void initView() {
        if (source.equals("zcj")) {
            title.setText("总成绩表");
        } else {
            title.setText("平时成绩表");
            liner.setVisibility(View.GONE);
            viewzcj.setVisibility(View.GONE);
        }
        course.setText(teach.getKe().getDespration() + teach.getKe().getCourse_code() + "  " + teach.getNature().getDespration());
        yearSemester.setText(teach.getSchoolyear().getDespration());
        failurePeoploname.setText(teach.getTeacher().getDesperation());
        major.setText(teach.getKe().getCredit());
        gradeClass.setText(teach.getKaikeyuan().getDespration());
//        classs.setText(teach.getClasss().getGrade().getDespration() + "级" + teach.getClasss().getMajor().getDespration() + teach.getClasss().getClasss().getDespration() + "班");
    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}
