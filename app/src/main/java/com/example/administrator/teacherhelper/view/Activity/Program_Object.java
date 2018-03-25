package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.TCH_program;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class Program_Object extends Activity {
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
    @Bind(R.id.jxmd_detial)
    EditText jxmdDetial;
    String object;
    String programid;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jxrl_mudi);
        ButterKnife.bind(this);
        first();
        initView();
    }
    private void first() {
        object = getIntent().getStringExtra("object");
        programid = getIntent().getStringExtra("programid");
        key = getIntent().getStringExtra("key");
    }

    private void initView() {

        if (!(programid.equals(""))){
            title.setText(object);
            save.setVisibility(View.VISIBLE);
            rightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TCH_program  program = new TCH_program();
                    if (key.equals("object")){
                        program.setObject(jxmdDetial.getText().toString());
                    }else if (key.equals("ability")){
                        program.setAbility(jxmdDetial.getText().toString());
                    }else if (key.equals("contact")){
                        program.setContact(jxmdDetial.getText().toString());
                    }else if (key.equals("check")){
                        program.setCheck(jxmdDetial.getText().toString());
                    }
                    program.update(programid, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null){
                                Toast.makeText(Program_Object.this, "保存成功", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(Program_Object.this, "保存失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }else {
            jxmdDetial.setText(object);
            title.setText(key);
        }
    }



    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}
