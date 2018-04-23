package com.example.administrator.teacherhelper.view.Activity.max;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.NOTICE;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class Max_MessegeDetial extends Activity {
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
    @Bind(R.id.messege_title)
    TextView messegeTitle;
    @Bind(R.id.messege_content)
    TextView messegeContent;
    @Bind(R.id.messege_data)
    TextView messegeData;

    NOTICE notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.max_messegedetial);
        ButterKnife.bind(this);
        first();
        initView();
    }

    private void initView() {
        title.setText("通知详情");
        messegeTitle.setText(notice.getTitle());
        messegeContent.setText(notice.getContent());
        messegeData.setText(notice.getCreatedAt());
    }

    private void first() {
        notice = (NOTICE) getIntent().getSerializableExtra("messege");
    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}
