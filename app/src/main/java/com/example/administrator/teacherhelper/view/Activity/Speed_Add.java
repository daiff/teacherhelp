package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.TCH_Progress;
import com.example.administrator.teacherhelper.bean.TCH_pro;
import com.example.administrator.teacherhelper.bean.classs;
import com.example.administrator.teacherhelper.bean.jiaoxue;
import com.example.administrator.teacherhelper.view.Adapter.SpeedContent;

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

public class Speed_Add extends Activity {


    jiaoxue jiao;
    StringBuilder str = new StringBuilder();
    String newproid = "";
    SpeedContent adapter;
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
    @Bind(R.id.jxjd_fzr)
    TextView jxjdFzr;
    @Bind(R.id.major)
    TextView major;
    @Bind(R.id.listt)
    ListView listt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speed_detial);
        ButterKnife.bind(this);
        first();
        initView();
        initData();
        find();
    }

    private void find() {
        BmobQuery<TCH_Progress> bjiaoxue = new BmobQuery<>();
        bjiaoxue.addWhereEqualTo("tch_pro", newproid);
        bjiaoxue.order("+weekly");
        bjiaoxue.findObjects(new FindListener<TCH_Progress>() {
            @Override
            public void done(List<TCH_Progress> list, BmobException e) {
                if (e == null) {
                    if (list.size() != 0) {
                        adapter = new SpeedContent(list, Speed_Add.this);
                        listt.setAdapter(adapter);
                    }

                } else {
                    Toast.makeText(Speed_Add.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initData() {
        BmobQuery<classs> bjiaoxue = new BmobQuery<>();
        bjiaoxue.include("classs,college,grade,major");
        jiaoxue analysis = new jiaoxue();
        analysis.setObjectId(jiao.getObjectId());
        bjiaoxue.addWhereRelatedTo("Team", new BmobPointer(analysis));
        bjiaoxue.findObjects(new FindListener<classs>() {
            @Override
            public void done(List<classs> list, BmobException e) {
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        str.append(list.get(i).getCollege().getDespration() + list.get(i).getGrade().getDespration() + list.get(i).getMajor().getDespration() + list.get(i).getClasss().getDespration() + "班  ");
                    }
                    major.setText(str);
                }

            }
        });
    }

    private void initView() {
        title.setText("新增教学进度");
        add.setVisibility(View.VISIBLE);
        course.setText(jiao.getKe().getDespration());
        yearSemester.setText(jiao.getSchoolyear().getDespration());
        jxjdFzr.setText(jiao.getTeacher().getDesperation());
    }

    private void first() {
        jiao = (jiaoxue) getIntent().getSerializableExtra("ke");
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                if (newproid == "") {
                    save();
                } else {
                    Intent intent = new Intent(Speed_Add.this, Speed_AddItem.class);
                    intent.putExtra("proid", newproid);
                    startActivity(intent);
                }
                break;
        }
    }

    private void save() {
        TCH_pro pro = new TCH_pro();
        pro.setClasss(major.getText().toString());
        pro.setJiaoxue(jiao);
        pro.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    newproid = s;
                    Intent intent = new Intent(Speed_Add.this, Speed_AddItem.class);
                    intent.putExtra("proid", s);
                    startActivity(intent);
                } else {
                    Toast.makeText(Speed_Add.this, "失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        find();
    }
}
