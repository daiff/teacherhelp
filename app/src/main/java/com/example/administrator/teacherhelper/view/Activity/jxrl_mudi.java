package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.teacherhelper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class jxrl_mudi extends Activity {

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

    String mudi;
    @Bind(R.id.jxmd_detial)
    EditText jxmdDetial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jxrl_mudi);
        ButterKnife.bind(this);
        first();
        initView();
    }

    private void initView() {
        title.setText("教学目的及主要要求");

    }

    private void first() {
        mudi = getIntent().getStringExtra("mudi");
        jxmdDetial.setText(mudi);
    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}




