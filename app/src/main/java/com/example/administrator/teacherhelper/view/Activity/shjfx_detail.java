package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.teacherhelper.Bean.TCH_analysis;
import com.example.administrator.teacherhelper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by daiff on 2018/1/29.
 * for:
 */

public class shjfx_detail extends Activity {
    TCH_analysis tch_analysis;
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
    @Bind(R.id.tishi)
    ImageButton tishi;
    @Bind(R.id.course)
    TextView course;
    @Bind(R.id.year_semester)
    TextView yearSemester;
    @Bind(R.id.kaikeyuan)
    TextView kaikeyuan;
    @Bind(R.id.shjfx_classs)
    TextView shjfxClasss;
    @Bind(R.id.fsh_9)
    TextView fsh9;
    @Bind(R.id.bl_9)
    TextView bl9;
    @Bind(R.id.fsh_8)
    TextView fsh8;
    @Bind(R.id.bl_8)
    TextView bl8;
    @Bind(R.id.fsh_7)
    TextView fsh7;
    @Bind(R.id.bl_7)
    TextView bl7;
    @Bind(R.id.fsh_6)
    TextView fsh6;
    @Bind(R.id.bl_6)
    TextView bl6;
    @Bind(R.id.fsh_0)
    TextView fsh0;
    @Bind(R.id.bl_0)
    TextView bl0;
    @Bind(R.id.total_people)
    TextView totalPeople;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.high_fen)
    TextView highFen;
    @Bind(R.id.low_fen)
    TextView lowFen;
    @Bind(R.id.prop_analysis)
    TextView propAnalysis;
    @Bind(R.id.right_button)
    RelativeLayout rightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shjfxi_detial);
        ButterKnife.bind(this);
        first();
        init();
    }

    private void first() {
        tch_analysis = (TCH_analysis) getIntent().getSerializableExtra("tch_analysis");
    }

    private void init() {
        title.setText("试卷分析表");
        tishi.setVisibility(View.VISIBLE);
        course.setText(tch_analysis.getJiaoxue().getKe().getCourse_code() + " "
                + tch_analysis.getJiaoxue().getKe().getDespration() + " (" +
                tch_analysis.getJiaoxue().getNature().getDespration() + ")");
        yearSemester.setText(tch_analysis.getJiaoxue().getSchoolyear().getDespration());
        kaikeyuan.setText("开课院：" + tch_analysis.getJiaoxue().getKaikeyuan().getDespration());
        shjfxClasss.setText(tch_analysis.getJiaoxue().getClasss().getGrade().getDespration() + "级" +tch_analysis.getJiaoxue().getClasss().getMajor().getDespration()+tch_analysis.getJiaoxue().getClasss().getClasss().getDespration() + " 班");
        fsh9.setText(tch_analysis.getNine_num() + "人");
        bl9.setText(tch_analysis.getNine_prop() + "%");
        fsh8.setText(tch_analysis.getEight_num() + "人");
        bl8.setText(tch_analysis.getEight_prop() + "%");
        fsh7.setText(tch_analysis.getSeven_num() + "人");
        bl7.setText(tch_analysis.getSeven_prop() + "%");
        fsh6.setText(tch_analysis.getSix_num() + "人");
        bl6.setText(tch_analysis.getSix_prop() + "%");
        fsh0.setText(tch_analysis.getZero_num() + "人");
        bl0.setText(tch_analysis.getZero_prop() + "%");
        totalPeople.setText(tch_analysis.getTatalpeoplenum());
        highFen.setText(tch_analysis.getHighest_score());
        lowFen.setText(tch_analysis.getMinimum_score());
        propAnalysis.setText(tch_analysis.getCom_analysis());
    }

    @OnClick({R.id.back1, R.id.tishi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.tishi:
                break;
        }
    }

    @OnClick(R.id.right_button)
    public void onViewClicked() {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("1.命题分析(就试卷的难易程度、覆盖面及试卷类型适宜情况等进行分析)\n" +
                        "2.学生考试结果(就教师的教学方法、手段、内容及学生对课程理解、掌握等方面进行分析)\n" +
                        "3.措施与方法(肯定有效措施与方法，寻找不足及其原因，提出改进意见)\n" +
                        "\n课程考核试卷分析须按班级分析，然后汇总写出该课程考核试卷的综合分析")
                .setPositiveButton("确定", null)
        .show();
    }
}
