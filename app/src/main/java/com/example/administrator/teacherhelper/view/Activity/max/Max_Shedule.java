package com.example.administrator.teacherhelper.view.Activity.max;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.FIELD;
import com.example.administrator.teacherhelper.bean.Schedule;
import com.example.administrator.teacherhelper.bean.person;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.until.AccountUtils;
import com.example.administrator.teacherhelper.view.Activity.my_course;
import com.example.administrator.teacherhelper.view.Activity.select_Activity.classes_select;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;

/**
 * Created by Administrator on 2018/4/1 0001.
 */

public class Max_Shedule extends Activity {
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
    @Bind(R.id.sc_teacher)
    TextView scTeacher;
    @Bind(R.id.select_scteacher)
    ImageView selectScteacher;
    @Bind(R.id.sc_cource)
    TextView scCource;
    @Bind(R.id.select_sccource)
    ImageView selectSccource;
    @Bind(R.id.sc_class)
    TextView scClass;
    @Bind(R.id.select_scclass)
    ImageView selectScclass;
    @Bind(R.id.select_douyou)
    RadioButton selectDouyou;
    @Bind(R.id.select_dan)
    RadioButton selectDan;
    @Bind(R.id.select_sh)
    RadioButton selectSh;
    @Bind(R.id.select_yi)
    RadioButton selectYi;
    @Bind(R.id.select_er)
    RadioButton selectEr;
    @Bind(R.id.select_san)
    RadioButton selectSan;
    @Bind(R.id.select_si)
    RadioButton selectSi;
    @Bind(R.id.select_wu)
    RadioButton selectWu;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.select_one)
    RadioButton selectOne;
    @Bind(R.id.select_two)
    RadioButton selectTwo;
    @Bind(R.id.select_three)
    RadioButton selectThree;
    @Bind(R.id.select_four)
    RadioButton selectFour;
    @Bind(R.id.bookdate)
    EditText bookdate;
    @Bind(R.id.select_douyou2)
    RadioButton selectDouyou2;
    @Bind(R.id.select_dan2)
    RadioButton selectDan2;
    @Bind(R.id.select_sh2)
    RadioButton selectSh2;
    @Bind(R.id.select_yi2)
    RadioButton selectYi2;
    @Bind(R.id.select_er2)
    RadioButton selectEr2;
    @Bind(R.id.select_san2)
    RadioButton selectSan2;
    @Bind(R.id.select_si2)
    RadioButton selectSi2;
    @Bind(R.id.select_wu2)
    RadioButton selectWu2;
    @Bind(R.id.select_one2)
    RadioButton selectOne2;
    @Bind(R.id.select_two2)
    RadioButton selectTwo2;
    @Bind(R.id.select_three2)
    RadioButton selectThree2;
    @Bind(R.id.select_four2)
    RadioButton selectFour2;
    @Bind(R.id.bookdate2)
    EditText bookdate2;
    @Bind(R.id.select_douyou3)
    RadioButton selectDouyou3;
    @Bind(R.id.select_dan3)
    RadioButton selectDan3;
    @Bind(R.id.select_sh3)
    RadioButton selectSh3;
    @Bind(R.id.select_yi3)
    RadioButton selectYi3;
    @Bind(R.id.select_er3)
    RadioButton selectEr3;
    @Bind(R.id.select_san3)
    RadioButton selectSan3;
    @Bind(R.id.select_si3)
    RadioButton selectSi3;
    @Bind(R.id.select_wu3)
    RadioButton selectWu3;
    @Bind(R.id.select_one3)
    RadioButton selectOne3;
    @Bind(R.id.select_two3)
    RadioButton selectTwo3;
    @Bind(R.id.select_three3)
    RadioButton selectThree3;
    @Bind(R.id.select_four3)
    RadioButton selectFour3;
    @Bind(R.id.bookdate3)
    EditText bookdate3;
    @Bind(R.id.select_dsz1)
    RadioGroup selectDsz1;
    @Bind(R.id.select_xq1)
    RadioGroup selectXq1;
    @Bind(R.id.select_djj1)
    RadioGroup selectDjj1;
    @Bind(R.id.dsz2)
    RadioGroup dsz2;
    @Bind(R.id.xq2)
    RadioGroup xq2;
    @Bind(R.id.djj2)
    RadioGroup djj2;
    @Bind(R.id.dsz3)
    RadioGroup dsz3;
    @Bind(R.id.xq3)
    RadioGroup xq3;
    @Bind(R.id.djj3)
    RadioGroup djj3;
    String teacherid;
    String classid;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.max_schedule);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("新增课程表");
        save.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.back1, R.id.right_button,R.id.select_scteacher, R.id.select_sccource,R.id.select_scclass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                getLoadingDialog().show();
                saveall();
                break;
            case R.id.select_scteacher:
                Intent intent8 = new Intent(Max_Shedule.this, max_teacher.class);
                intent8.putExtra("select", CommenDate.maxcour_teacher);
                startActivityForResult(intent8, CommenDate.select_teacher);
                break;
            case R.id.select_sccource:
                Intent intent = new Intent(Max_Shedule.this, my_course.class);
                intent.putExtra("sourse", CommenDate.maxschedule_jiaoxue);
                intent.putExtra("teacherid", teacherid);
                startActivityForResult(intent, CommenDate.select_course);
                break;
            case R.id.select_scclass:
                Intent intent1 = new Intent(Max_Shedule.this, classes_select.class);
                intent1.putExtra("courseid", classid);
                startActivityForResult(intent1, CommenDate.select_class1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CommenDate.select_teacher) {
            teacherid = data.getStringExtra("majorid");
            String majordesc = data.getStringExtra("majordesc");
            scTeacher.setText(majordesc);
        }else if (requestCode == CommenDate.select_course){
            String majordesc = data.getStringExtra("course");
            classid = data.getStringExtra("courseid");
            scCource.setText(majordesc);
        }else if (requestCode == CommenDate.select_class1){
            String majordesc = data.getStringExtra("classes");
            scClass.setText(majordesc);
        }
    }

    private void saveall() {
        List<BmobObject> schedule = new ArrayList<BmobObject>();
        Schedule s1 = new Schedule();
        person pe = new person();
        pe.setObjectId(teacherid);
        s1.setTeacher(pe);
        s1.setClasses(scClass.getText().toString());
        s1.setJiaoxue(scCource.getText().toString());
        s1.setClassroom(bookdate.getText().toString());
        FIELD f = new FIELD();
        f.setObjectId(AccountUtils.getyear(Max_Shedule.this));
        s1.setSchoolyear(f);
        int week1 = selectDsz1.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(week1);
        String dsz = radioButton.getText().toString();
        s1.setWeek(dsz);

        int zhou = selectXq1.getCheckedRadioButtonId();
        RadioButton radioButton1 = (RadioButton) findViewById(zhou);
        String XQ1 = radioButton1.getText().toString();
        s1.setXingqi(XQ1);

        int ke1 = selectDjj1.getCheckedRadioButtonId();
        RadioButton radioButton2 = (RadioButton) findViewById(ke1);
        String djj1 = radioButton2.getText().toString();
        s1.setSection(djj1);
        schedule.add(s1);

    if (!(bookdate2.getText().toString().equals(""))){
        Schedule s2 = new Schedule();
        s2.setTeacher(pe);
        s2.setClasses(scClass.getText().toString());
        s2.setJiaoxue(scCource.getText().toString());
        s2.setClassroom(bookdate2.getText().toString());
        s2.setSchoolyear(f);

        int week2 = dsz2.getCheckedRadioButtonId();
        RadioButton radioButton3 = (RadioButton) findViewById(week2);
        String DSZ2 = radioButton3.getText().toString();
        s2.setWeek(DSZ2);

        int zhou2 = xq2.getCheckedRadioButtonId();
        RadioButton radioButton4 = (RadioButton) findViewById(zhou2);
        String ZS2 = radioButton4.getText().toString();
        s2.setXingqi(ZS2);

        int ke2 = djj2.getCheckedRadioButtonId();
        RadioButton radioButton5 = (RadioButton) findViewById(ke2);
        String DJJ2 = radioButton5.getText().toString();
        s2.setSection(DJJ2);
        schedule.add(s2);
    }
        if (!(bookdate2.getText().toString().equals(""))) {
            Schedule s3 = new Schedule();
            s3.setTeacher(pe);
            s3.setClasses(scClass.getText().toString());
            s3.setJiaoxue(scCource.getText().toString());
            s3.setClassroom(bookdate3.getText().toString());
            s3.setSchoolyear(f);

            int week3 = dsz3.getCheckedRadioButtonId();
            RadioButton radioButton7 = (RadioButton) findViewById(week3);
            String DSZ3 = radioButton7.getText().toString();
            s3.setWeek(DSZ3);

            int zhou3 = xq3.getCheckedRadioButtonId();
            RadioButton radioButton8 = (RadioButton) findViewById(zhou3);
            String ZS3 = radioButton8.getText().toString();
            s3.setXingqi(ZS3);

            int ke3 = djj3.getCheckedRadioButtonId();
            RadioButton radioButton9 = (RadioButton) findViewById(ke3);
            String DJJ3 = radioButton9.getText().toString();
            s3.setSection(DJJ3);
            schedule.add(s3);
        }

        new BmobBatch().insertBatch(schedule).doBatch(new QueryListListener<BatchResult>() {
            @Override
            public void done(List<BatchResult> list, BmobException e) {
                getLoadingDialog().dismiss();
                if (e==null){
                    Toast.makeText(Max_Shedule.this, "保存成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Max_Shedule.this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
