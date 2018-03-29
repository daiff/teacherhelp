package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.bean.TCA_detail;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Adapter.CalendarContent;

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

public class Calendar_Content extends Activity {
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
    CalendarContent adapter;
    String source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_contenttop);
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
                        Toast.makeText(Calendar_Content.this, "没有详情", Toast.LENGTH_SHORT).show();
                    }else{
                        adapter = new CalendarContent(list,Calendar_Content.this);
                        listt.setAdapter(adapter);

                    }

                }else {
                    Toast.makeText(Calendar_Content.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void first() {
        calenderid = getIntent().getStringExtra("calenderid");
        source = getIntent().getStringExtra("resource");
    }

    private void initView() {
        title.setText("教学日历详情");
        if (source.equals("add")){
            add.setVisibility(View.VISIBLE);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Calendar_Content.this,Calender_ContentAdd.class);
                    intent.putExtra("calenderid", calenderid);
                    startActivity(intent);
                }
            }
            );
        }
    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        iniitData();
    }
}
