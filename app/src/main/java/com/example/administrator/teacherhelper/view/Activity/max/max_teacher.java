package com.example.administrator.teacherhelper.view.Activity.max;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.bean.person;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Adapter.Adapter_teacher;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class max_teacher extends Activity {
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
    Adapter_teacher adapter;

    String select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapteractivity);
        ButterKnife.bind(this);
        Bmob.initialize(this, "ab8ec6ed95c785a2a470225606acee3e");
        first();
        initView();
        initData();
    }
    private void first(){
        select = getIntent().getStringExtra("select");
    }

    private void initData() {
        BmobQuery<person> per = new BmobQuery<>();
        per.include("xi.despration");
        per.order("-createdAt");
        per.findObjects(new FindListener<person>() {
            @Override
            public void done(final List<person> list, BmobException e) {
                if (e==null){
                    if (list.size() == 0){
                        Toast.makeText(max_teacher.this, "没有人员信息", Toast.LENGTH_SHORT).show();
                    }else {
                        adapter = new Adapter_teacher(list, max_teacher.this);
                        listt.setAdapter(adapter);
                        if (select.equals(CommenDate.maxcour_teacher)){
                            listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    intent.putExtra("majorid",list.get(position).getObjectId());
                                    intent.putExtra("majordesc",list.get(position).getDesperation());
                                    setResult(CommenDate.select_teacher,intent);
                                    finish();
                                }
                            });
                        }
                    }

                }else{
                    Toast.makeText(max_teacher.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initView() {
        title.setText("人员表");
        if (!(select.equals(CommenDate.maxcour_teacher))){
            add.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                Intent intent = new Intent(max_teacher.this,max_teacheradd.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }
}
