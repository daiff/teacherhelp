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
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.TCH_program;
import com.example.administrator.teacherhelper.bean.TEACH;
import com.example.administrator.teacherhelper.view.Adapter.Program_TimeAdapter;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public class Program_add extends Activity {
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
    @Bind(R.id.jxjd_course)
    TextView jxjdCourse;
    @Bind(R.id.jxjd_year)
    TextView jxjdYear;
    @Bind(R.id.jxdg_credit)
    TextView jxdgCredit;
    @Bind(R.id.Hours)
    EditText Hours;
    @Bind(R.id.Object_oriented)
    EditText ObjectOriented;
    @Bind(R.id.major)
    EditText major;
    @Bind(R.id.englishname)
    EditText englishname;
    @Bind(R.id.object)
    LinearLayout object;
    @Bind(R.id.xueshianpai)
    LinearLayout xueshianpai;
    @Bind(R.id.jxnryyq)
    LinearLayout jxnryyq;
    @Bind(R.id.ability)
    LinearLayout ability;
    @Bind(R.id.contact)
    LinearLayout contact;
    @Bind(R.id.check)
    LinearLayout check;
    @Bind(R.id.book)
    LinearLayout book;

    TEACH teach = new TEACH();
    String programid;
    int control=0;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_detial);
        ButterKnife.bind(this);
        first();
        initView();

    }

    private void initView() {
        title.setText("新增教学大纲");
        save.setVisibility(View.VISIBLE);
        jxjdCourse.setText(teach.getCourse().getDespration() + "    " +  teach.getNature().getDespration());
        jxjdYear.setText(teach.getSchoolyear().getDespration());
        jxdgCredit.setText(teach.getCredit());
    }

    private void first() {
        teach = (TEACH) getIntent().getSerializableExtra("ke");
    }

    @OnClick({R.id.back1, R.id.right_button, R.id.object, R.id.xueshianpai, R.id.jxnryyq, R.id.ability, R.id.contact, R.id.check, R.id.book})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                if (!(ObjectOriented.getText().toString().equals(""))&&!(major.getText().toString().equals(""))&&!(englishname.getText().toString().equals(""))&&!(Hours.getText().toString().equals(""))){
                    getLoadingDialog().show();
                save();
                }else {
                    Toast.makeText(Program_add.this, "请填写完整之后再保存", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.object:
                if (control==1){
                    Intent intent = new Intent(Program_add.this,Program_Object.class);
                    intent.putExtra("programid",programid);
                    intent.putExtra("key","object");
                    intent.putExtra("object","课程的任务和目的");
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "请先保存当前内容", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.xueshianpai:
                if (control==1){
                    Intent intent1 = new Intent(Program_add.this,Program_Time.class);
                    intent1.putExtra("programid",programid);
                    intent1.putExtra("souce","time");
                    intent1.putExtra("resource","add");
                    startActivity(intent1);
                }else {
                    Toast.makeText(this, "请先保存当前内容", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.jxnryyq:
                if (control==1){
                    Intent intent2 = new Intent(Program_add.this,Program_Time.class);
                    intent2.putExtra("programid",programid);
                    intent2.putExtra("souce","");
                    intent2.putExtra("resource","add");
                    startActivity(intent2);
                }else {
                    Toast.makeText(this, "请先保存当前内容", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ability:
                if (control==1){
                    Intent intent = new Intent(Program_add.this,Program_Object.class);
                    intent.putExtra("programid",programid);
                    intent.putExtra("key","ability");
                    intent.putExtra("object","对学生能力培养的要求");
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "请先保存当前内容", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.contact:
                if (control==1){
                    Intent intent = new Intent(Program_add.this,Program_Object.class);
                    intent.putExtra("programid",programid);
                    intent.putExtra("key","contact");
                    intent.putExtra("object","各课程的联系");
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "请先保存当前内容", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.check:
                if (control==1){
                    Intent intent = new Intent(Program_add.this,Program_Object.class);
                    intent.putExtra("programid",programid);
                    intent.putExtra("key","check");
                    intent.putExtra("object","考核方式");
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "请先保存当前内容", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.book:
                if (control==1){}else {
                    Toast.makeText(this, "请先保存当前内容", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void save() {
            TCH_program program = new TCH_program();
            program.setEnglishname(englishname.getText().toString());
            program.setMajor(major.getText().toString());
            program.setObject_oriented(ObjectOriented.getText().toString());
            program.setHours(Hours.getText().toString());
            program.setCourse(teach);
            program.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    getLoadingDialog().dismiss();
                    if (e==null){
                        Toast.makeText(Program_add.this, "保存成功", Toast.LENGTH_SHORT).show();
                        programid = s;
                        control=1;
                    }else {
                        Toast.makeText(Program_add.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }
}
