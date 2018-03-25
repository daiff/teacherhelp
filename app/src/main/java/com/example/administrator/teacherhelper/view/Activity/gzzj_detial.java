package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.TCH_worksum;
import com.example.administrator.teacherhelper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class gzzj_detial extends Activity {


    TCH_worksum worksum = new TCH_worksum();
    @Bind(R.id.gzzj_semester)
    TextView gzzjSemester;
    @Bind(R.id.gzzj_class)
    TextView gzzjClass;
    @Bind(R.id.gzzj_teacher)
    TextView gzzjTeacher;
    @Bind(R.id.gzzj_title)
    TextView gzzjTitle;
    @Bind(R.id.gzzj_major)
    TextView gzzjMajor;
    @Bind(R.id.gzzj_personnum)
    TextView gzzjPersonnum;
    @Bind(R.id.gzzj_hour)
    TextView gzzjHour;
    @Bind(R.id.gzzj_book)
    TextView gzzjBook;
    @Bind(R.id.gzzj_kaizhan)
    TextView gzzjKaizhan;
    @Bind(R.id.gzzj_wanch)
    TextView gzzjWanch;
    @Bind(R.id.gzzj_pigai)
    TextView gzzjPigai;
    @Bind(R.id.gzzj_xueshxx)
    TextView gzzjXueshxx;
    @Bind(R.id.gzzj_pschj)
    TextView gzzjPschj;
    @Bind(R.id.gzzj_czjn)
    TextView gzzjCzjn;
    @Bind(R.id.gzzj_shj)
    TextView gzzjShj;
    @Bind(R.id.gzzj_pjfen)
    TextView gzzjPjfen;
    @Bind(R.id.gzzj_high)
    TextView gzzjHigh;
    @Bind(R.id.gzzj_low)
    TextView gzzjLow;
    @Bind(R.id.gzzj_bjg)
    TextView gzzjBjg;
    @Bind(R.id.gzzj_blbjg)
    TextView gzzjBlbjg;
    @Bind(R.id.gzzj_shifen)
    TextView gzzjShifen;
    @Bind(R.id.gzzj_problem)
    TextView gzzjProblem;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gzzj_detial);
        ButterKnife.bind(this);
        Bmob.initialize(this, "ab8ec6ed95c785a2a470225606acee3e");
        first();
        initView();
    }

    private void initView() {
        title.setText("工作总结详情");
        gzzjSemester.setText(worksum.getTeach().getSchoolyear().getDespration());
//        gzzjClass.setText(worksum.getTeach().getClasss().getGrade().getDespration() + "级" + worksum.
//                getTeach().getClasss().getMajor().getDespration() + worksum.getTeach().getClasss().getClasss().getDespration() + "班");
        gzzjTeacher.setText(worksum.getTeach().getTeacher().getDesperation());
        gzzjTitle.setText(worksum.getTeach().getTeacher().getTitle());
        gzzjMajor.setText(worksum.getTeach().getTeacher().getXi().getDespration());
//        gzzjPersonnum.setText(worksum.getTeach().getClasss().getTotal_person());
        gzzjHour.setText(worksum.getLilun_hour());
        gzzjBook.setText(worksum.getTeach().getBook().getDespration());
        gzzjKaizhan.setText(worksum.getWork_kaizhan());
        gzzjWanch.setText(worksum.getWork_wanch());
        gzzjPigai.setText(worksum.getPigai());
        gzzjXueshxx.setText(worksum.getLearn());
        gzzjPschj.setText(worksum.getPshi());
        gzzjCzjn.setText(worksum.getJineng());
        gzzjShj.setText(worksum.getSjpeople());
        gzzjPjfen.setText(worksum.getPinjun());
        gzzjHigh.setText(worksum.getHigh_fen());
        gzzjBjg.setText(worksum.getBjgpeople());
        gzzjLow.setText(worksum.getLow_fen());
        gzzjBlbjg.setText(worksum.getBjglv());
        gzzjShifen.setText(worksum.getShifen());
        gzzjProblem.setText(worksum.getProblem());

    }

    private void first() {
        worksum = (TCH_worksum) getIntent().getSerializableExtra("tch_worksum");
    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}
