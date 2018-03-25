package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.teacherhelper.bean.TCA_Timetatistic;
import com.example.administrator.teacherhelper.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class jxrl_time extends Activity {
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
    @Bind(R.id.jk_zs)
    EditText jkZs;
    @Bind(R.id.jk_pj)
    EditText jkPj;
    @Bind(R.id.jk_xs)
    EditText jkXs;
    @Bind(R.id.xz_cs)
    EditText xzCs;
    @Bind(R.id.xz_pj)
    EditText xzPj;
    @Bind(R.id.xz_xs)
    EditText xzXs;
    @Bind(R.id.tl_cs)
    EditText tlCs;
    @Bind(R.id.tl_pj)
    EditText tlPj;
    @Bind(R.id.tl_xs)
    EditText tlXs;
    @Bind(R.id.sj_pj)
    EditText sjPj;
    @Bind(R.id.sj_xs)
    EditText sjXs;
    @Bind(R.id.sj_cs)
    EditText sjCs;
    @Bind(R.id.sj_zs)
    EditText sjZs;
    @Bind(R.id.sj_ts)
    EditText sjTs;
    @Bind(R.id.skzzs)
    EditText skzzs;
    @Bind(R.id.skzxs)
    EditText skzxs;
    @Bind(R.id.zx)
    EditText zx;
    @Bind(R.id.qzxz)
    EditText qzxz;
    @Bind(R.id.sjjx_cs)
    EditText sjjxCs;

    TCA_Timetatistic timedetial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jxrl_time);
        ButterKnife.bind(this);
        first();
        initView();
    }

    private void initView() {
        title.setText("课时详情");

        jkZs.setText(timedetial.getLecture_total());
        jkPj.setText(timedetial.getLecture_hour());
        jkXs.setText(timedetial.getLecture_titalHours());

        xzCs.setText(timedetial.getWriting_number());
        xzPj.setText(timedetial.getWriting_hour());
        xzXs.setText(timedetial.getWriting_total());

        tlCs.setText(timedetial.getDiscuss_number());
        tlPj.setText(timedetial.getDiscuss_hour());
        tlXs.setText(timedetial.getDiacuss_total());

        sjCs.setText(timedetial.getOther_number());
        sjPj.setText(timedetial.getOther_hour());
        sjXs.setText(timedetial.getOther_total());

        sjjxCs.setText(timedetial.getPractice_number());
        sjZs.setText(timedetial.getPractice_hours());
        sjTs.setText(timedetial.getPractice_total());

        skzzs.setText(timedetial.getTotal_week());
        skzxs.setText(timedetial.getTotal_hours());

        zx.setText(timedetial.getExtra_total());
        qzxz.setText(timedetial.getExtra_include());
    }

    private void first() {
        timedetial = (TCA_Timetatistic)getIntent().getSerializableExtra("timedetial");
    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}
