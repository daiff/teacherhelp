package com.example.administrator.teacherhelper.view.Activity.max;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.NOTICE;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class Max_Messege extends Activity {
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
    @Bind(R.id.messege_save)
    Button messegeSave;
    @Bind(R.id.messege_title)
    EditText messegeTitle;
    @Bind(R.id.messege_content)
    EditText messegeContent;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.max_messegeadd);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("新增通知");
        tishi.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                Intent intent = new Intent(Max_Messege.this, Max_MessegeList.class);
                startActivity(intent);
                break;
        }
    }

    @OnClick(R.id.messege_save)
    public void onViewClicked() {
        getLoadingDialog().show();
        NOTICE notice = new NOTICE();
        notice.setTitle(messegeTitle.getText().toString());
        notice.setContent(messegeContent.getText().toString());
        notice.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                getLoadingDialog().dismiss();
                if (e==null){
                    Toast.makeText(Max_Messege.this, "保存成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Max_Messege.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
