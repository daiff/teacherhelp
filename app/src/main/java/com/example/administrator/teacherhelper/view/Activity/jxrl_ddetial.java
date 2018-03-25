package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.bean.TCA_detail;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Adapter.jxrl_detialAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class jxrl_ddetial extends Activity {
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
    @Bind(R.id.listt)
    ListView listt;

    String calenderid;
    jxrl_detialAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jxrl_itemtou);
        ButterKnife.bind(this);
        first();
        initView();
        iniitData();
    }

    private void iniitData() {
        BmobQuery<TCA_detail> btcadetial = new BmobQuery<>();
        btcadetial.addWhereEqualTo("tch_calender",calenderid);
        btcadetial.findObjects(new FindListener<TCA_detail>() {
            @Override
            public void done(List<TCA_detail> list, BmobException e) {
                if (e==null){
                    if (list.size()==0){
                        Toast.makeText(jxrl_ddetial.this, "没有详情", Toast.LENGTH_SHORT).show();
                    }else{
                        adapter = new jxrl_detialAdapter(list,jxrl_ddetial.this);
                        listt.setAdapter(adapter);

                    }

                }else {
                    Toast.makeText(jxrl_ddetial.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void first() {
        calenderid = getIntent().getStringExtra("calenderid");
    }

    private void initView() {
        title.setText("教学日历详情");
    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}
