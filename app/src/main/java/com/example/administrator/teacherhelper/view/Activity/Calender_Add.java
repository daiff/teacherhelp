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
import com.example.administrator.teacherhelper.bean.TCH_calender;
import com.example.administrator.teacherhelper.bean.classs;
import com.example.administrator.teacherhelper.bean.jiaoxue;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public class Calender_Add extends Activity {
    jiaoxue jiao = new jiaoxue();
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
    @Bind(R.id.failure_peoploname)
    TextView failurePeoploname;
    @Bind(R.id.jxrl_teacher)
    TextView jxrlTeacher;
    @Bind(R.id.jxrl_title)
    TextView jxrlTitle;
    @Bind(R.id.jxrl_fdteacher)
    TextView jxrlFdteacher;
    @Bind(R.id.jxrl_fatitle)
    TextView jxrlFatitle;
    @Bind(R.id.mudi)
    LinearLayout mudi;
    @Bind(R.id.book)
    LinearLayout book;
    @Bind(R.id.zzs)
    EditText zzs;
    @Bind(R.id.qzks)
    EditText qzks;
    @Bind(R.id.qz_zxss)
    EditText qzZxss;
    @Bind(R.id.qmks)
    EditText qmks;
    @Bind(R.id.qmks_xs)
    EditText qmksXs;
    @Bind(R.id.anpai_detial)
    LinearLayout anpaiDetial;
    @Bind(R.id.jxrl_xiang)
    LinearLayout jxrlXiang;
    @Bind(R.id.jxrl_check)
    LinearLayout jxrlCheck;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    StringBuilder str = new StringBuilder();
    String calenderid= "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_detial);
        ButterKnife.bind(this);
        first();
        initView();

        getclass();
    }

    private void getclass() {
        BmobQuery<classs> bjiaoxue = new BmobQuery<>();
        bjiaoxue.include(CommenDate.include_classs);
        jiaoxue analysis = new jiaoxue();
        analysis.setObjectId(jiao.getObjectId());
        bjiaoxue.addWhereRelatedTo("Team",new BmobPointer(analysis));
        bjiaoxue.findObjects(new FindListener<classs>() {
            @Override
            public void done(List<classs> list, BmobException e) {
                if (e==null){
                    for (int i =0;i<list.size();i++){
                        str.append(list.get(i).getCollege().getDespration()+list.get(i).getGrade().getDespration()+list.get(i).getMajor().getDespration()+list.get(i).getClasss().getDespration()+ "班  ");
                    }
                    failurePeoploname.setText(str);
                }

            }
        });

    }

    private void initView() {
        title.setText("新增教学日历");
        save.setVisibility(View.VISIBLE);
    }

    private void first() {
        jiao = (jiaoxue) getIntent().getSerializableExtra("ke");
    }

    @OnClick({R.id.back1, R.id.right_button,R.id.mudi, R.id.book, R.id.anpai_detial, R.id.jxrl_xiang, R.id.jxrl_check})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                if ((calenderid.equals(""))
                        &&!(failurePeoploname.getText().toString().equals(""))
                        &&!(zzs.getText().toString().equals(""))
                        &&!(qzks.getText().toString().equals(""))
                        &&!(qzZxss.getText().toString().equals(""))
                        &&!(qmksXs.getText().toString().equals(""))
                        &&!(qmks.getText().toString().equals(""))) {
                    getLoadingDialog().show();
                    TCH_calender calender = new TCH_calender();
                    calender.setClasss(failurePeoploname.getText().toString());
                    calender.setTotal_weeks(zzs.getText().toString());
                    calender.setMid_number(qzks.getText().toString());
                    calender.setMid_hour(qzZxss.getText().toString());
                    calender.setEnd_hour(qmksXs.getText().toString());
                    calender.setEnd_number(qmks.getText().toString());
                    calender.setJiaoxue(jiao);
                    calender.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            getLoadingDialog().dismiss();
                            if (e == null) {
                                Toast.makeText(Calender_Add.this,"保存成功", Toast.LENGTH_SHORT).show();
                                calenderid = s;
                            } else {
                                Toast.makeText(Calender_Add.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(this, "请将此页面填写完整并保存", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mudi:
                if (calenderid.equals(null)){
                    Toast.makeText(this, "请将此页面填写完整并保存", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Calender_Add.this, Calender_Object.class);
                    intent.putExtra("mudi", "");
                    intent.putExtra("calenderid", calenderid);
                    startActivity(intent);
                }
                break;
            case R.id.book:
                if (calenderid.equals(null)){
                    Toast.makeText(this, "请将此页面填写完整并保存", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent1 = new Intent(Calender_Add.this, Calendar_Book.class);
                    intent1.putExtra("calenderid", calenderid);
                    intent1.putExtra("book", jiao.getBook());
                    startActivity(intent1);
                }
                break;
            case R.id.anpai_detial:
                if (calenderid.equals(null)){
                    Toast.makeText(this, "请将此页面填写完整并保存", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent3 = new Intent(Calender_Add.this, Calendar_Time.class);
                    intent3.putExtra("timedetial", calenderid);
                    intent3.putExtra("source", "add");
                    startActivity(intent3);
                }
                break;
            case R.id.jxrl_xiang:
                if (calenderid.equals(null)){
                    Toast.makeText(this, "请将此页面填写完整并保存", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent4 = new Intent(Calender_Add.this, Calendar_Content.class);
                    intent4.putExtra("calenderid", calenderid);
                    intent4.putExtra("resource", "add");
                    startActivity(intent4);
                }
                break;
            case R.id.jxrl_check:
                if (calenderid.equals(null)){
                    Toast.makeText(this, "请将此页面填写完整并保存", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent2 = new Intent(Calender_Add.this, Calendar_Check.class);
                    intent2.putExtra("source", calenderid);
                    startActivity(intent2);
                }
                break;
        }
    }
}
