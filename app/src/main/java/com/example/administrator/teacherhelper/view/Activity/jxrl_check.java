package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.TCH_calender;
import com.example.administrator.teacherhelper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class jxrl_check extends Activity {
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
    TCH_calender calender;
    @Bind(R.id.zxqk)
    EditText zxqk;
    @Bind(R.id.zldj)
    EditText zldj;
    @Bind(R.id.jys)
    EditText jys;
    @Bind(R.id.jysqz)
    EditText jysqz;
    @Bind(R.id.jysqzrq)
    EditText jysqzrq;
    @Bind(R.id.xzr)
    EditText xzr;
    @Bind(R.id.xzrqz)
    EditText xzrqz;
    @Bind(R.id.xzrrq)
    EditText xzrrq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jxrl_check);
        ButterKnife.bind(this);
        first();
        initView();
    }

    private void first() {
        calender = (TCH_calender) getIntent().getSerializableExtra("check");
    }

    private void initView() {
        title.setText("教学进度执行情况检查");
        zxqk.setText(calender.getHavedone());
        zldj.setText(calender.getRank());


    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}
