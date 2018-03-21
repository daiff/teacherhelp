package com.example.administrator.teacherhelper.view.select_Activity;

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

import com.example.administrator.teacherhelper.Bean.FIELD;
import com.example.administrator.teacherhelper.Commen.commenDate;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Activity.dialog.FlippingLoadingDialog;
import com.example.administrator.teacherhelper.view.Adapter.Adapter_value;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/3/20 0020.
 */

public class value_select extends Activity {
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

    String value;
    Adapter_value adapter;
    String valueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapteractivity);
        ButterKnife.bind(this);
        first();
        initView();
        initData();
    }

    private void initData() {
        BmobQuery<FIELD> value_bmob =new BmobQuery<>();
        value_bmob.addWhereEqualTo("value",value);
        value_bmob.findObjects(new FindListener<FIELD>() {
            @Override
            public void done(final List<FIELD> list, BmobException e) {
                getLoadingDialog().dismiss();
                if (e==null){
                    if (list.size()==0){
                        Toast.makeText(value_select.this, "没有数据", Toast.LENGTH_SHORT).show();
                    }else{
                        adapter = new Adapter_value(list, value_select.this);
                        listt.setAdapter(adapter);
                        listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent();
                                intent.putExtra("majorid",list.get(position).getObjectId());
                                intent.putExtra("majordesc",list.get(position).getDespration());
                                setResult(commenDate.select_major,intent);
                                finish();
                            }
                        });
                    }
                }else {
                    Toast.makeText(value_select.this, e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void first() {
        value = getIntent().getStringExtra("value");
    }

    private void initView() {
        title.setText("选择列表");
        getLoadingDialog().show();
    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }

}
