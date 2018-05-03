package com.example.administrator.teacherhelper.view.Activity.max;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.bean.FIELD;
import com.example.administrator.teacherhelper.bean.classs;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;
import com.example.administrator.teacherhelper.view.Activity.select_Activity.value_select;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class max_addclasss extends Activity {
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
    @Bind(R.id.course_coll)
    TextView courseColl;
    @Bind(R.id.select_course_coll)
    ImageView selectCourseColl;
    @Bind(R.id.course_grade)
    TextView courseGrade;
    @Bind(R.id.select_course_grade)
    ImageView selectCourseGrade;
    @Bind(R.id.course_ma)
    TextView courseMa;
    @Bind(R.id.select_course_ma)
    ImageView selectCourseMa;
    @Bind(R.id.curse_class)
    TextView curseClass;
    @Bind(R.id.select_curse_class)
    ImageView selectCurseClass;
    @Bind(R.id.course_num)
    EditText courseNum;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    String collegeid;
    String gradeid;
    String majorid;
    String classid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.max_addclasss);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("录入班级信息");
        save.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.back1, R.id.right_button,R.id.select_course_coll, R.id.select_course_grade, R.id.select_course_ma, R.id.select_curse_class})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                getLoadingDialog().show();
                saveall();
                break;
            case R.id.select_course_coll:
                Intent intent4 = new Intent(max_addclasss.this, value_select.class);
                intent4.putExtra("value", CommenDate.value_college);
                startActivityForResult(intent4, CommenDate.select_collegestu);
                break;
            case R.id.select_course_grade:
                Intent intent5 = new Intent(max_addclasss.this, value_select.class);
                intent5.putExtra("value", CommenDate.value_grade);
                startActivityForResult(intent5, CommenDate.select_grade);
                break;
            case R.id.select_course_ma:
                Intent intent6 = new Intent(max_addclasss.this, value_select.class);
                intent6.putExtra("value", CommenDate.value_major);
                startActivityForResult(intent6, CommenDate.select_major);
                break;
            case R.id.select_curse_class:
                Intent intent7 = new Intent(max_addclasss.this, value_select.class);
                intent7.putExtra("value", CommenDate.value_class);
                startActivityForResult(intent7, CommenDate.select_class);
                break;
        }
    }

    private void saveall() {
        classs jiaoxue =new classs();
        FIELD college = new FIELD();
        college.setObjectId(collegeid);
        jiaoxue.setCollege(college);

        FIELD grade = new FIELD();
        grade.setObjectId(gradeid);
        jiaoxue.setGrade(grade);

        FIELD major = new FIELD();
        major.setObjectId(majorid);
        jiaoxue.setMajor(major);

        FIELD classs = new FIELD();
        classs.setObjectId(classid);
        jiaoxue.setClasss(classs);

        jiaoxue.setTotal_person(courseNum.getText().toString());

        jiaoxue.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                getLoadingDialog().dismiss();
                if (e==null){
                    Toast.makeText(max_addclasss.this, "保存成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(max_addclasss.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!(null == data || "".equals(data))) {
            if (requestCode == CommenDate.select_collegestu) {
                collegeid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseColl.setText(majordesc);
            } else if (requestCode == CommenDate.select_grade) {
                gradeid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseGrade.setText(majordesc);
            } else if (requestCode == CommenDate.select_major) {
                majorid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseMa.setText(majordesc);
            } else if (requestCode == CommenDate.select_class) {
                classid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                curseClass.setText(majordesc);
            }
        }
    }
}
