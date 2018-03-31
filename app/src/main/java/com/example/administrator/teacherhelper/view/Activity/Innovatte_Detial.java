package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.Innovate;
import com.example.administrator.teacherhelper.bean.STUDENT;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/31 0031.
 */

public class Innovatte_Detial extends Activity {
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
    @Bind(R.id.object)
    LinearLayout object;
    @Bind(R.id.content)
    TextView content;
    Innovate innovate;
    @Bind(R.id.innovate_title)
    TextView innovateTitle;

    STUDENT student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.innovate_detiall);
        ButterKnife.bind(this);
        first();
        initView();
//        initData();
    }


    private void initView() {
        title.setText("创新详情");
        innovateTitle.setText(innovate.getTitle());
        content.setText(innovate.getContent());

    }

    private void first() {
        innovate = (Innovate) getIntent().getSerializableExtra("innovate");
    }


    @OnClick({R.id.back1, R.id.object})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.object:
                Intent intent = new Intent(Innovatte_Detial.this,Innovate_Student.class);
                intent.putExtra("innovateid",innovate.getObjectId());
                startActivity(intent);
                break;
        }
    }
}
