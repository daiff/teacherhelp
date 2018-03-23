package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.Bean.STUDENT;
import com.example.administrator.teacherhelper.Bean.jiaoxue;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Adapter.zcj_studentAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/3/22 0022.
 */

public class zcj_add extends Activity {

    jiaoxue teach;
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
    @Bind(R.id.classs)
    TextView classs;
    @Bind(R.id.xibie)
    TextView xibie;
    @Bind(R.id.failure_peoploname)
    TextView failurePeoploname;
    @Bind(R.id.zhuanye)
    TextView zhuanye;
    @Bind(R.id.major)
    TextView major;
    @Bind(R.id.banji)
    TextView banji;
    @Bind(R.id.grade_class)
    TextView gradeClass;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.listt)
    ListView listt;

    zcj_studentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zcj_detail);
        ButterKnife.bind(this);
        first();
        initView();
        initDate();
    }

    private void initDate() {
        BmobQuery<STUDENT>  bstudent = new BmobQuery<>();
        bstudent.addWhereEqualTo("classs",teach.getClasss().getObjectId());
        bstudent.findObjects(new FindListener<STUDENT>() {
            @Override
            public void done(List<STUDENT> list, BmobException e) {
                if (e==null){
                    adapter = new zcj_studentAdapter(list,zcj_add.this);
                    listt.setAdapter(adapter);

                }else {
                    Toast.makeText(zcj_add.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        title.setText("新增总成绩表");
        save.setVisibility(View.VISIBLE);
        course.setText(teach.getKe().getDespration() + teach.getKe().getCourse_code());
        yearSemester.setText(teach.getSchoolyear().getDespration());
        failurePeoploname.setText(teach.getTeacher().getDesperation());
        major.setText(teach.getKe().getCredit());
        gradeClass.setText(teach.getKaikeyuan().getDespration());
        classs.setText(teach.getClasss().getGrade().getDespration()+"级"+teach.getClasss().getMajor().getDespration()+teach.getClasss().getClasss().getDespration()+"班");
    }


    private void first() {
        teach = (jiaoxue) getIntent().getSerializableExtra("ke");
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                saveall();
                break;
        }
    }

    private void saveall() {



    }
}
