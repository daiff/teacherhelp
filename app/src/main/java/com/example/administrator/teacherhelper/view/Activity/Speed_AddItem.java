package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.TCH_Progress;
import com.example.administrator.teacherhelper.bean.TCH_pro;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public class Speed_AddItem extends Activity {
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
    @Bind(R.id.weekly)
    EditText weekly;
    @Bind(R.id.hour)
    EditText hour;
    @Bind(R.id.chapter)
    EditText chapter;
    @Bind(R.id.content)
    EditText content;
    @Bind(R.id.objective)
    EditText objective;
    @Bind(R.id.Teaching_methods)
    EditText TeachingMethods;
    @Bind(R.id.task_hour)
    EditText taskHour;
    @Bind(R.id.Remarks)
    EditText Remarks;
    String proid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speed_additem);
        ButterKnife.bind(this);
        first();
        initView();
    }

    private void initView() {
        title.setText("新增一条字数据");
        save.setVisibility(View.VISIBLE);
    }

    private void first() {
        proid = getIntent().getStringExtra("proid");
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                TCH_Progress progress = new TCH_Progress();
                TCH_pro bpro = new TCH_pro();
                bpro.setObjectId(proid);
                progress.setTch_pro(bpro);
                progress.setChapter(chapter.getText().toString());
                progress.setContent(content.getText().toString());
                progress.setHour(hour.getText().toString());
                progress.setObjective(objective.getText().toString());
                progress.setRemarks(Remarks.getText().toString());
                progress.setTask_hour(taskHour.getText().toString());
                progress.setTeaching_methods(TeachingMethods.getText().toString());
                progress.setWeekly(weekly.getText().toString());
                progress.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e==null){
                            Toast.makeText(Speed_AddItem.this, "保存成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Speed_AddItem.this, "保存失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
