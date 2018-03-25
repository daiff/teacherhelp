package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.bean.FIELD;
import com.example.administrator.teacherhelper.bean.currentyear;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Activity.max.max_class;
import com.example.administrator.teacherhelper.view.Activity.max.max_teacher;
import com.example.administrator.teacherhelper.view.Activity.max.max_value;
import com.example.administrator.teacherhelper.view.Activity.max.mx_upload;
import com.example.administrator.teacherhelper.view.Activity.select_Activity.book_select;
import com.example.administrator.teacherhelper.view.Activity.select_Activity.value_select;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

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
    @Bind(R.id.current_schoolyear)
    TextView currentSchoolyear;
    @Bind(R.id.max_selectyear)
    ImageView maxSelectyear;
    @Bind(R.id.max_student)
    LinearLayout maxStudent;
    @Bind(R.id.max_class)
    LinearLayout maxClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.max_activity);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        BmobQuery<currentyear> cu = new BmobQuery<>();
        cu.include("year");
        cu.findObjects(new FindListener<currentyear>() {
            @Override
            public void done(List<currentyear> list, BmobException e) {
                currentSchoolyear.setText(list.get(0).getYear().getDespration());
            }
        });
    }

    @OnClick({R.id.max_teacher, R.id.max_teach, R.id.max_current, R.id.max_value, R.id.max_field, R.id.max_max, R.id.max_student, R.id.max_class})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.max_teacher:
                Intent intent1 = new Intent(MaxActivity.this, max_teacher.class);
                intent1.putExtra("select", "");
                startActivity(intent1);
                break;
            case R.id.max_teach:
                Intent intent3 = new Intent(MaxActivity.this, my_course.class);
                intent3.putExtra("sourse", CommenDate.max_mycourse);
                startActivity(intent3);
                break;
            case R.id.max_current:
                Intent intent4 = new Intent(MaxActivity.this, book_select.class);
                intent4.putExtra("book", "");
                startActivity(intent4);
                break;
            case R.id.max_value:
                Intent intent2 = new Intent(MaxActivity.this, max_value.class);
                startActivity(intent2);
                break;
            case R.id.max_field:
                Intent intent = new Intent(MaxActivity.this, mx_upload.class);
                startActivity(intent);
                break;
            case R.id.max_max:
                break;
            case R.id.max_student:
                break;
            case R.id.max_class:
                Intent intent5 = new Intent(MaxActivity.this, max_class.class);
                intent5.putExtra("select", "");
                startActivity(intent5);
                break;
        }
    }

    @OnClick(R.id.max_selectyear)
    public void onViewClicked() {
        Intent intent = new Intent(MaxActivity.this, value_select.class);
        intent.putExtra("value", CommenDate.value_schoolyear);
        startActivityForResult(intent, CommenDate.max_year);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!(null == data || "".equals(data))) {
            String majorid = data.getStringExtra("majorid");
            String majordesc = data.getStringExtra("majordesc");

            currentSchoolyear.setText(majordesc);
            FIELD field = new FIELD();
            field.setObjectId(majorid);
            currentyear c = new currentyear();
            c.setYear(field);
            c.update("a6PwEEEJ", new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        Toast.makeText(MaxActivity.this, "学期更新成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MaxActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

}
