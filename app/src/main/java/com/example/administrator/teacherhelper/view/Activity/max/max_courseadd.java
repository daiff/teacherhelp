package com.example.administrator.teacherhelper.view.Activity.max;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.FIELD;
import com.example.administrator.teacherhelper.bean.TEACH;
import com.example.administrator.teacherhelper.bean.classs;
import com.example.administrator.teacherhelper.bean.jiaoxue;
import com.example.administrator.teacherhelper.bean.person;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.view.Activity.select_Activity.value_select;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class max_courseadd extends Activity {


    String schoolyearid;
    String teacherid;
    String courseid;
    String natureid;
    String kaikeid;
    String bookid;
    String classid;
    String classid1;
    String classid2;
    String classid3;
    String classid4;

    protected FlippingLoadingDialog mLoadingDialog;
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
    @Bind(R.id.course_sc)
    TextView courseSc;
    @Bind(R.id.select_course_sc)
    ImageView selectCourseSc;
    @Bind(R.id.course_tea)
    TextView courseTea;
    @Bind(R.id.select_course_tea)
    ImageView selectCourseTea;
    @Bind(R.id.course_cour)
    TextView courseCour;
    @Bind(R.id.select_course_cour)
    ImageView selectCourseCour;
    @Bind(R.id.course_na)
    TextView courseNa;
    @Bind(R.id.select_course_na)
    ImageView selectCourseNa;
    @Bind(R.id.course_kai)
    TextView courseKai;
    @Bind(R.id.select_course_kai)
    ImageView selectCourseKai;
    @Bind(R.id.course_class1)
    TextView courseClass1;
    @Bind(R.id.select_course1_sc)
    ImageView selectCourse1Sc;
    @Bind(R.id.course_class2)
    TextView courseClass2;
    @Bind(R.id.select_course2_sc)
    ImageView selectCourse2Sc;
    @Bind(R.id.course_class3)
    TextView courseClass3;
    @Bind(R.id.select_course3_sc)
    ImageView selectCourse3Sc;
    @Bind(R.id.course_class4)
    TextView courseClass4;
    @Bind(R.id.select_course4_sc)
    ImageView selectCourse4Sc;
    @Bind(R.id.course_class5)
    TextView courseClass5;
    @Bind(R.id.select_course5_sc)
    ImageView selectCourse5Sc;
    @Bind(R.id.course_fen)
    TextView courseFen;

    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.max_courseadd);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("课程信息");
        save.setVisibility(View.VISIBLE);
    }

    private void saveall() {
        List<BmobObject> teach = new ArrayList<>();

        TEACH jiaoxue = new TEACH();

        FIELD schoolyear = new FIELD();
        schoolyear.setObjectId(schoolyearid);
        jiaoxue.setSchoolyear(schoolyear);

        person teacher = new person();
        teacher.setObjectId(teacherid);
        jiaoxue.setTeacher(teacher);

        FIELD course = new FIELD();
        course.setObjectId(courseid);
        jiaoxue.setCourse(course);

        FIELD nature = new FIELD();
        nature.setObjectId(natureid);
        jiaoxue.setNature(nature);

        FIELD kaike = new FIELD();
        kaike.setObjectId(kaikeid);
        jiaoxue.setCollege(kaike);

        jiaoxue.setCredit(courseFen.getText().toString());

        classs c = new classs();
        c.setObjectId(classid);
        jiaoxue.setTeam(c);
        teach.add(jiaoxue);
        if (classid1!=null){
            TEACH jiaoxue1 = new TEACH();

            jiaoxue1.setSchoolyear(schoolyear);
            jiaoxue1.setTeacher(teacher);
            jiaoxue1.setCourse(course);
            jiaoxue1.setNature(nature);
            jiaoxue1.setCollege(kaike);
            jiaoxue1.setCredit(courseFen.getText().toString());
            classs c1 = new classs();
            c1.setObjectId(classid1);
            jiaoxue1.setTeam(c1);
            teach.add(jiaoxue1);
        }
        if (classid2!=null){
            TEACH jiaoxue2 = new TEACH();

            jiaoxue2.setSchoolyear(schoolyear);
            jiaoxue2.setTeacher(teacher);
            jiaoxue2.setCourse(course);
            jiaoxue2.setNature(nature);
            jiaoxue2.setCollege(kaike);
            jiaoxue2.setCredit(courseFen.getText().toString());
            classs c2= new classs();
            c2.setObjectId(classid2);
            jiaoxue2.setTeam(c2);
            teach.add(jiaoxue2);
        }

        if (classid3!=null){
            TEACH jiaoxue3 = new TEACH();

            jiaoxue3.setSchoolyear(schoolyear);
            jiaoxue3.setTeacher(teacher);
            jiaoxue3.setCourse(course);
            jiaoxue3.setNature(nature);
            jiaoxue3.setCollege(kaike);
            jiaoxue3.setCredit(courseFen.getText().toString());
            classs c3= new classs();
            c3.setObjectId(classid3);
            jiaoxue3.setTeam(c3);
            teach.add(jiaoxue3);
        }

        if (classid4!=null){
            TEACH jiaoxue4 = new TEACH();

            jiaoxue4.setSchoolyear(schoolyear);
            jiaoxue4.setTeacher(teacher);
            jiaoxue4.setCourse(course);
            jiaoxue4.setNature(nature);
            jiaoxue4.setCollege(kaike);
            jiaoxue4.setCredit(courseFen.getText().toString());
            classs c4= new classs();
            c4.setObjectId(classid4);
            jiaoxue4.setTeam(c4);
            teach.add(jiaoxue4);
        }
        new BmobBatch().insertBatch(teach).doBatch(new QueryListListener<BatchResult>() {
            @Override
            public void done(List<BatchResult> list, BmobException e) {
                getLoadingDialog().dismiss();
                if (e == null) {
                    Toast.makeText(max_courseadd.this, "保存成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(max_courseadd.this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!(null == data || "".equals(data))) {
            if (requestCode == CommenDate.select_schoolyear) {
                schoolyearid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseSc.setText(majordesc);
            } else if (requestCode == CommenDate.select_course) {
                courseid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseCour.setText(majordesc);
            } else if (requestCode == CommenDate.select_nature) {
                natureid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseNa.setText(majordesc);
            } else if (requestCode == CommenDate.select_college) {
                kaikeid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseKai.setText(majordesc);
            } else if (requestCode == CommenDate.select_teacher) {
                teacherid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseTea.setText(majordesc);
            } else if (requestCode == CommenDate.select_class) {
                classid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseClass1.setText(majordesc);
            } else if (requestCode == CommenDate.select_class1) {
                classid1 = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseClass2.setText(majordesc);
            } else if (requestCode == CommenDate.select_class2) {
                classid2 = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseClass3.setText(majordesc);
            } else if (requestCode == CommenDate.select_class3) {
                classid3 = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseClass4.setText(majordesc);
            } else if (requestCode == CommenDate.select_class4) {
                classid4 = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseClass5.setText(majordesc);
            }
        }

    }

    @OnClick({R.id.back1, R.id.right_button, R.id.select_course_sc, R.id.select_course_tea, R.id.select_course_cour, R.id.select_course_na, R.id.select_course_kai, R.id.select_course1_sc, R.id.select_course2_sc, R.id.select_course3_sc, R.id.select_course4_sc, R.id.select_course5_sc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                getLoadingDialog().show();
                saveall();
                break;
            case R.id.select_course_sc:
                Intent intent = new Intent(max_courseadd.this, value_select.class);
                intent.putExtra("value", CommenDate.value_schoolyear);
                startActivityForResult(intent, CommenDate.select_schoolyear);
                break;
            case R.id.select_course_tea:
                Intent intent8 = new Intent(max_courseadd.this, max_teacher.class);
                intent8.putExtra("select", CommenDate.maxcour_teacher);
                startActivityForResult(intent8, CommenDate.select_teacher);
                break;
            case R.id.select_course_cour:
                Intent intent1 = new Intent(max_courseadd.this, value_select.class);
                intent1.putExtra("value", CommenDate.value_schele);
                startActivityForResult(intent1, CommenDate.select_course);
                break;
            case R.id.select_course_na:
                Intent intent2 = new Intent(max_courseadd.this, value_select.class);
                intent2.putExtra("value", CommenDate.value_nature);
                startActivityForResult(intent2, CommenDate.select_nature);
                break;
            case R.id.select_course_kai:
                Intent intent3 = new Intent(max_courseadd.this, value_select.class);
                intent3.putExtra("value", CommenDate.value_college);
                startActivityForResult(intent3, CommenDate.select_college);
                break;
            case R.id.select_course1_sc:
                Intent intent10 = new Intent(max_courseadd.this, max_class.class);
                intent10.putExtra("select", CommenDate.maxcour_class);
                startActivityForResult(intent10, CommenDate.select_class);
                break;
            case R.id.select_course2_sc:
                Intent intent11 = new Intent(max_courseadd.this, max_class.class);
                intent11.putExtra("select", CommenDate.maxcour_class);
                startActivityForResult(intent11, CommenDate.select_class1);
                break;
            case R.id.select_course3_sc:
                Intent intent12 = new Intent(max_courseadd.this, max_class.class);
                intent12.putExtra("select", CommenDate.maxcour_class);
                startActivityForResult(intent12, CommenDate.select_class2);
                break;
            case R.id.select_course4_sc:
                Intent intent13 = new Intent(max_courseadd.this, max_class.class);
                intent13.putExtra("select", CommenDate.maxcour_class);
                startActivityForResult(intent13, CommenDate.select_class3);
                break;
            case R.id.select_course5_sc:
                Intent intent14 = new Intent(max_courseadd.this, max_class.class);
                intent14.putExtra("select", CommenDate.maxcour_class);
                startActivityForResult(intent14, CommenDate.select_class4);
                break;
        }
    }
}
