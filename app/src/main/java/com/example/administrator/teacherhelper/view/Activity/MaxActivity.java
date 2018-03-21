package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Activity.max.max_teacher;
import com.example.administrator.teacherhelper.view.Activity.max.max_value;
import com.example.administrator.teacherhelper.view.Activity.max.mx_upload;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/18 0018.
 */

public class MaxActivity extends Activity {
    @Bind(R.id.max_teacher)
    LinearLayout maxTeacher;
    @Bind(R.id.max_teach)
    LinearLayout maxTeach;
    @Bind(R.id.max_current)
    LinearLayout maxCurrent;
    @Bind(R.id.max_value)
    LinearLayout maxValue;
    @Bind(R.id.max_field)
    LinearLayout maxField;
    @Bind(R.id.max_max)
    LinearLayout maxMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.max_activity);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.max_teacher, R.id.max_teach, R.id.max_current, R.id.max_value, R.id.max_field, R.id.max_max})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.max_teacher:
                Intent intent1 = new Intent(MaxActivity.this,max_teacher.class);
                startActivity(intent1);
                break;
            case R.id.max_teach:
                break;
            case R.id.max_current:
                break;
            case R.id.max_value:
                Intent intent2 = new Intent(MaxActivity.this,max_value.class);
                startActivity(intent2);
                break;
            case R.id.max_field:
                Intent intent = new Intent(MaxActivity.this,mx_upload.class);
                startActivity(intent);
                break;
            case R.id.max_max:
                break;
        }
    }
}
