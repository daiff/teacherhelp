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

import com.example.administrator.teacherhelper.Bean.classs;
import com.example.administrator.teacherhelper.Commen.commenDate;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Activity.dialog.FlippingLoadingDialog;
import com.example.administrator.teacherhelper.view.Adapter.classsAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class max_class extends Activity {
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
    classsAdapter Adapter;
    String select;


    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapteractivity);
        ButterKnife.bind(this);
        first();
        initView();
        initData();
    }

    private void first() {
        select = getIntent().getStringExtra("select");
    }

    private void initData() {
        getLoadingDialog().show();
        BmobQuery<classs> bclass = new BmobQuery<>();
        bclass.include("classs,college,major,grade");
        bclass.findObjects(new FindListener<classs>() {
            @Override
            public void done(final List<classs> list, BmobException e) {
                getLoadingDialog().dismiss();
                if (e==null){
                    if (list.size()==0){
                        Toast.makeText(max_class.this, "暂时没有数据", Toast.LENGTH_SHORT).show();
                    }else {
                        Adapter = new classsAdapter(list,max_class.this);
                        listt.setAdapter(Adapter);
                        if (select.equals( commenDate.maxcour_class)){
                            listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    intent.putExtra("majorid",list.get(position).getObjectId());
                                    intent.putExtra("majordesc",list.get(position).getCollege().getDespration()+
                                            list.get(position).getGrade().getDespration()+"级"
                                            +list.get(position).getMajor().getDespration() +
                                            list.get(position).getClasss().getDespration());
                                    setResult(commenDate.select_class,intent);
                                    finish();
                                }
                            });
                        }
                    }
                }else {
                    Toast.makeText(max_class.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        title.setText("班级信息");
        add.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                Intent in = new Intent(max_class.this,max_addclasss.class);
                startActivity(in);
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }
}
