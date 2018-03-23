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

import com.example.administrator.teacherhelper.Bean.FIELD;
import com.example.administrator.teacherhelper.Bean.book;
import com.example.administrator.teacherhelper.Bean.classs;
import com.example.administrator.teacherhelper.Bean.jiaoxue;
import com.example.administrator.teacherhelper.Bean.person;
import com.example.administrator.teacherhelper.Commen.commenDate;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Activity.dialog.FlippingLoadingDialog;
import com.example.administrator.teacherhelper.view.select_Activity.book_select;
import com.example.administrator.teacherhelper.view.select_Activity.value_select;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

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
    @Bind(R.id.course_class)
    TextView courseClass;
    @Bind(R.id.select_course_class)
    ImageView selectCourseClass;
    @Bind(R.id.course_book)
    TextView courseBook;
    @Bind(R.id.select_course_book)
    ImageView selectCourseBook;

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
        jiaoxue jiaoxue = new jiaoxue();

        FIELD schoolyear = new FIELD();
        schoolyear.setObjectId(schoolyearid);
        jiaoxue.setSchoolyear(schoolyear);

        person teacher = new person();
        teacher.setObjectId(teacherid);
        jiaoxue.setTeacher(teacher);

        FIELD course = new FIELD();
        course.setObjectId(courseid);
        jiaoxue.setKe(course);

        FIELD nature = new FIELD();
        nature.setObjectId(natureid);
        jiaoxue.setNature(nature);

        FIELD kaike = new FIELD();
        kaike.setObjectId(kaikeid);
        jiaoxue.setKaikeyuan(kaike);


        book b = new book();
        b.setObjectId(bookid);
        jiaoxue.setBook(b);

        classs cl = new classs();
        cl.setObjectId(classid);
        jiaoxue.setClasss(cl);


        jiaoxue.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                getLoadingDialog().dismiss();
                if (e == null) {
                    Toast.makeText(max_courseadd.this, "保存成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(max_courseadd.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!(null == data || "".equals(data))) {
            if (requestCode == commenDate.select_schoolyear) {
                schoolyearid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseSc.setText(majordesc);
            } else if (requestCode == commenDate.select_course) {
                courseid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseCour.setText(majordesc);
            } else if (requestCode == commenDate.select_nature) {
                natureid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseNa.setText(majordesc);
            } else if (requestCode == commenDate.select_college) {
                kaikeid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseKai.setText(majordesc);
            } else if (requestCode == commenDate.select_teacher) {
                teacherid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseTea.setText(majordesc);
            } else if (requestCode == commenDate.select_book) {
                bookid = data.getStringExtra("bookid");
                String majordesc = data.getStringExtra("bookname");
                courseBook.setText(majordesc);
            }else if (requestCode == commenDate.select_class) {
                classid = data.getStringExtra("majorid");
                String majordesc = data.getStringExtra("majordesc");
                courseClass.setText(majordesc);
            }
        }

    }

    @OnClick({R.id.back1, R.id.right_button, R.id.select_course_sc, R.id.select_course_tea, R.id.select_course_cour, R.id.select_course_na, R.id.select_course_kai, R.id.select_course_class, R.id.select_course_book})
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
                intent.putExtra("value", commenDate.value_schoolyear);
                startActivityForResult(intent, commenDate.select_schoolyear);
                break;
            case R.id.select_course_tea:
                Intent intent8 = new Intent(max_courseadd.this, max_teacher.class);
                intent8.putExtra("select", commenDate.maxcour_teacher);
                startActivityForResult(intent8, commenDate.select_teacher);
                break;
            case R.id.select_course_cour:
                Intent intent1 = new Intent(max_courseadd.this, value_select.class);
                intent1.putExtra("value", commenDate.value_schele);
                startActivityForResult(intent1, commenDate.select_course);
                break;
            case R.id.select_course_na:
                Intent intent2 = new Intent(max_courseadd.this, value_select.class);
                intent2.putExtra("value", commenDate.value_nature);
                startActivityForResult(intent2, commenDate.select_nature);
                break;
            case R.id.select_course_kai:
                Intent intent3 = new Intent(max_courseadd.this, value_select.class);
                intent3.putExtra("value", commenDate.value_college);
                startActivityForResult(intent3, commenDate.select_college);
                break;
            case R.id.select_course_book:
                Intent intent9 = new Intent(max_courseadd.this, book_select.class);
                intent9.putExtra("book", commenDate.maxcour_book);
                startActivityForResult(intent9, commenDate.select_book);
                break;
            case R.id.select_course_class:
                Intent intent10 = new Intent(max_courseadd.this, max_class.class);
                intent10.putExtra("select", commenDate.maxcour_class);
                startActivityForResult(intent10, commenDate.select_class);
                break;
        }
    }
}
