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

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.NOTICE;
import com.example.administrator.teacherhelper.view.Adapter.Messege;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class Max_MessegeList extends Activity {
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
    Messege adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapteractivity);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        BmobQuery<NOTICE> notice = new BmobQuery<>();
        notice.findObjects(new FindListener<NOTICE>() {
            @Override
            public void done(final List<NOTICE> list, BmobException e) {
                if (e==null){
                    if (list.size()!=0){
                        adapter = new Messege(list,Max_MessegeList.this);
                        listt.setAdapter(adapter);
                        listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(Max_MessegeList.this, Max_MessegeDetial.class);
                                intent.putExtra("messege",list.get(position));
                                startActivity(intent);
                            }
                        });
                    }else {
                        Toast.makeText(Max_MessegeList.this, "没有数据", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Max_MessegeList.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        title.setText("通知列表");
    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}
