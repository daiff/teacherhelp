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

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.STUDENT;
import com.example.administrator.teacherhelper.bean.classs;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.view.Activity.max.max_class;import com.example.administrator.teacherhelper.view.Activity.max.mx_upload;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class max_StudentAdd extends Activity {
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
    @Bind(R.id.student_num)
    EditText studentNum;
    @Bind(R.id.student_name)
    EditText studentName;
    @Bind(R.id.course_class1)
    TextView courseClass1;
    @Bind(R.id.select_course1_sc)
    ImageView selectCourse1Sc;
    String classid4;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_add);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        title.setText("录入学生");
        save.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                getLoadingDialog().show();
                STUDENT student = new STUDENT();
                classs cla = new classs();
                cla.setObjectId(classid4);
                student.setClasss(cla);
                student.setNumber(studentNum.getText().toString());
                student.setDespration(studentName.getText().toString());
                student.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        getLoadingDialog().dismiss();
                        if (e==null){
                            Toast.makeText(max_StudentAdd.this, "保存成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(max_StudentAdd.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }

    @OnClick(R.id.select_course1_sc)
    public void onViewClicked() {
        Intent intent14 = new Intent(max_StudentAdd.this, max_class.class);
        intent14.putExtra("select", CommenDate.maxcour_class);
        startActivityForResult(intent14, CommenDate.select_class4);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CommenDate.select_class4) {
            classid4 = data.getStringExtra("majorid");
            String majordesc = data.getStringExtra("majordesc");
            courseClass1.setText(majordesc);
        }
    }
}
