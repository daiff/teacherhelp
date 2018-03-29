package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.bean.TCH_calender;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class Calendar_Check extends Activity {
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
    @Bind(R.id.zxqk)
    EditText zxqk;
    @Bind(R.id.zldj)
    EditText zldj;
    @Bind(R.id.jys)
    EditText jys;
    @Bind(R.id.jysqz)
    EditText jysqz;
    @Bind(R.id.jysqzrq)
    EditText jysqzrq;
    @Bind(R.id.xzr)
    EditText xzr;
    @Bind(R.id.xzrqz)
    EditText xzrqz;
    @Bind(R.id.xzrrq)
    EditText xzrrq;
    String source;

    TCH_calender calender;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_check);
        ButterKnife.bind(this);
        first();
        initView();
    }

    private void first() {
        source= getIntent().getStringExtra("source");
        if (source.equals("")) {
            calender = (TCH_calender) getIntent().getSerializableExtra("check");
        }

    }

    private void initView() {
        title.setText("教学进度执行情况检查");
        if (source.equals("")) {
            zxqk.setText(calender.getHavedone());
            zldj.setText(calender.getRank());
        }else {
            save.setVisibility(View.VISIBLE);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getLoadingDialog().show();
                    saveall();
                }
            });
        }
        BmobQuery<TCH_calender> calender = new BmobQuery<>();
        calender.getObject(source, new QueryListener<TCH_calender>() {
            @Override
            public void done(TCH_calender tch_calender, BmobException e) {
                if (e==null){
                    if (!(tch_calender.equals(""))){
                        zxqk.setText(tch_calender.getHavedone());
                        zldj.setText(tch_calender.getRank());
                    }
                }else {
                    Toast.makeText(Calendar_Check.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void saveall(){
        TCH_calender ca = new TCH_calender();
        ca.setHavedone(zxqk.getText().toString());
        ca.setRank(zldj.getText().toString());
        ca.update(source, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                getLoadingDialog().dismiss();
                if (e==null){
                    Toast.makeText(Calendar_Check.this, "保存成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Calendar_Check.this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}
