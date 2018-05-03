package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.bean.TCA_Timetatistic;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.TCH_calender;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class Calendar_Time extends Activity {
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
    String calenderid;
    String source;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_time);
        ButterKnife.bind(this);
        first();
        initView();
    }

    private void first() {
        calenderid = getIntent().getStringExtra("timedetial");
        source = getIntent().getStringExtra("source");

    }

    private void initView() {
        title.setText("课时详情");
        if (source.equals("detial")){
            initData();
        }else if (source.equals("add")){
            savNewData();
            initData();
        }
    }

    private void savNewData() {
        save.setVisibility(View.VISIBLE);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoadingDialog().show();
                final TCA_Timetatistic time = new TCA_Timetatistic();

                time.setLecture_total(jkZs.getText().toString());
                time.setLecture_hour(jkPj.getText().toString());
                time.setLecture_titalHours(jkXs.getText().toString());

                time.setWriting_number(xzCs.getText().toString());
                time.setWriting_hour(xzPj.getText().toString());
                time.setWriting_total(xzXs.getText().toString());

                time.setDiscuss_number(tlCs.getText().toString());
                time.setDiscuss_hour(tlPj.getText().toString());
                time.setDiacuss_total(tlXs.getText().toString());

                time.setOther_number(sjCs.getText().toString());
                time.setOther_hour(sjPj.getText().toString());
                time.setOther_total(sjXs.getText().toString());

                time.setPractice_number(sjjxCs.getText().toString());
                time.setPractice_hours(sjZs.getText().toString());
                time.setPractice_total(sjTs.getText().toString());

                time.setTotal_week(skzzs.getText().toString());
                time.setTotal_hours(skzxs.getText().toString());

                time.setExtra_total(zx.getText().toString());
                time.setExtra_include(qzxz.getText().toString());
                TCH_calender calender = new TCH_calender();
                calender.setObjectId(calenderid);
                time.setTch_calender(calender);

                time.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        getLoadingDialog().dismiss();
                        if (e==null){
                            Toast.makeText(Calendar_Time.this, "保存成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Calendar_Time.this, "保存失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

    //根据canlender的id去找时间的详情
    private void initData() {
        BmobQuery<TCA_Timetatistic>  time = new BmobQuery<>();
        time.addWhereEqualTo("tch_calender",calenderid);
        time.findObjects(new FindListener<TCA_Timetatistic>() {
            @Override
            public void done(List<TCA_Timetatistic> list, BmobException e) {
                if (e==null) {
                    if (list.size()!=0){
                        timedetial = list.get(0);
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
                    }else {
                        Toast.makeText(Calendar_Time.this, "没有数据", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Calendar_Time.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}
