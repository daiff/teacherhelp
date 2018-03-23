package com.example.administrator.teacherhelper.view.Activity.max;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.Bean.FIELD;
import com.example.administrator.teacherhelper.Commen.commenDate;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Activity.dialog.FlippingLoadingDialog;
import com.example.administrator.teacherhelper.view.Adapter.Adapter_value;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class max_value extends Activity {
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
    @Bind(R.id.value_schoolyear)
    TextView valueSchoolyear;
    @Bind(R.id.value_grade)
    TextView valueGrade;
    @Bind(R.id.value_major)
    TextView valueMajor;
    @Bind(R.id.value_course)
    TextView valueCourse;
    @Bind(R.id.value_college)
    TextView valueCollege;
    @Bind(R.id.listt)
    ListView listt;

    List<FIELD> schoolyear;
    List<FIELD> schedule;
    List<FIELD> college;
    List<FIELD> grade;
    List<FIELD> major;

    Adapter_value adapter;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.max_value);
        ButterKnife.bind(this);
        initView();
        initData();
    }


    private void initView() {
        add.setVisibility(View.VISIBLE);
        title.setText("域值");
        getLoadingDialog().show();

    }

    private void initData() {
        schoolyear = new ArrayList<>();
        schedule = new ArrayList<>();
        college = new ArrayList<>();
        grade = new ArrayList<>();
        major = new ArrayList<>();
        BmobQuery<FIELD> fieldBomb = new BmobQuery<>();
        fieldBomb.order("-createdAt");
        fieldBomb.findObjects(new FindListener<FIELD>() {
            @Override
            public void done(List<FIELD> list, BmobException e) {
                getLoadingDialog().dismiss();
                if (e==null){
                    if (list.size() ==0){
                        Toast.makeText(max_value.this, "无数据", Toast.LENGTH_SHORT).show();
                    }else {
                        for (int i = 0;i<list.size();i++){
                            if (list.get(i).getValue().equals(commenDate.value_college)){
                                college.add(list.get(i));
                            }else if (list.get(i).getValue().equals(commenDate.value_schoolyear)){
                                schoolyear.add(list.get(i));
                            }else if (list.get(i).getValue().equals(commenDate.value_schele)){
                                schedule.add(list.get(i));
                            }else if (list.get(i).getValue().equals(commenDate.value_grade)){
                                grade.add(list.get(i));
                            }else if (list.get(i).getValue().equals(commenDate.value_major)){
                                major.add(list.get(i));
                            }
                        }
                        setSelected();
                        valueSchoolyear.setSelected(true);
                        valueAdapter(schoolyear);
                    }
                }else{
                    Toast.makeText(max_value.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @OnClick({R.id.back1, R.id.right_button, R.id.value_schoolyear, R.id.value_grade, R.id.value_major, R.id.value_course, R.id.value_college})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                Intent intent = new Intent(max_value.this,max_valueadd.class);
                startActivity(intent);
                break;
            case R.id.value_schoolyear:
                setSelected();
                valueSchoolyear.setSelected(true);
                valueAdapter(schoolyear);
                break;
            case R.id.value_grade:
                setSelected();
                valueGrade.setSelected(true);
                valueAdapter(grade);
                break;
            case R.id.value_major:
                setSelected();
                valueMajor.setSelected(true);
                valueAdapter(major);
                break;
            case R.id.value_course:
                setSelected();
                valueCourse.setSelected(true);
                valueAdapter(schedule);
                break;
            case R.id.value_college:
                setSelected();
                valueCollege.setSelected(true);
                valueAdapter(college);
                break;
        }
    }

    private void valueAdapter(List<FIELD> list){
        adapter = new Adapter_value(list, max_value.this);
        listt.setAdapter(adapter);
    }

    //重置所有文本的选中状态
    private void setSelected() {
        valueSchoolyear.setSelected(false);
        valueCollege.setSelected(false);
        valueCourse.setSelected(false);
        valueGrade.setSelected(false);
        valueMajor.setSelected(false);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }
}
