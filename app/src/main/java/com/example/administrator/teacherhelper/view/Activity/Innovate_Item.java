package com.example.administrator.teacherhelper.view.Activity;

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
import com.example.administrator.teacherhelper.bean.Innovate;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.until.AccountUtils;
import com.example.administrator.teacherhelper.view.Adapter.InnovateItem;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/3/31 0031.
 */

public class Innovate_Item extends Activity {
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
    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }
    InnovateItem adapter;
    String resource;

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
        resource = getIntent().getStringExtra("resource");
    }

    private void initData() {
        getLoadingDialog().show();
        BmobQuery<Innovate> innovate = new BmobQuery<>();
        if (resource.equals(CommenDate.main)) {
            innovate.addWhereEqualTo("teacher", BmobUser.getCurrentUser());
        }
        innovate.findObjects(new FindListener<Innovate>() {
            @Override
            public void done(final List<Innovate> list, BmobException e) {
                getLoadingDialog().dismiss();
                if (e==null){
                    if (list.size()!=0){
                        adapter = new InnovateItem(list,Innovate_Item.this);
                        listt.setAdapter(adapter);
                        listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent in = new Intent(Innovate_Item.this,Innovatte_Detial.class);
                                in.putExtra("innovate",list.get(position));
                                startActivity(in);
                            }
                        });
                    }
                }else {
                    Toast.makeText(Innovate_Item.this, "获取失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        title.setText("创新项目");
        add.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                Intent intent = new Intent(Innovate_Item.this,Innovate_Add.class);
                startActivity(intent);
                break;
        }
    }
}
