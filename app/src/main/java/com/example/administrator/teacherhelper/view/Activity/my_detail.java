package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.bean.person;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.until.AccountUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2018/3/13 0013.
 */

public class my_detail extends Activity {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.back1)
    RelativeLayout back1;
    @Bind(R.id.add)
    ImageButton add;
    @Bind(R.id.save)
    ImageButton save;
    @Bind(R.id.userid)
    TextView userid;
    @Bind(R.id.username)
    TextView username;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.email)
    EditText email;
    @Bind(R.id.phone)
    EditText phone;
    @Bind(R.id.title_my)
    TextView titleMy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        title.setText("我的资料");
        save.setVisibility(View.VISIBLE);
        userid.setText(AccountUtils.getUserid(my_detail.this));
        username.setText(AccountUtils.getUsername(my_detail.this));
        titleMy.setText(AccountUtils.getTitle(my_detail.this));
        phone.setText(AccountUtils.getPhone(my_detail.this));
        email.setText(AccountUtils.getEmail(my_detail.this));
    }

    @OnClick({R.id.back1, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.save:
                final String p = phone.getText().toString();
                final String em = email.getText().toString();
                if (p.equals(AccountUtils.getPhone(my_detail.this))&&
                        em.equals(AccountUtils.getEmail(my_detail.this))){
                    Toast.makeText(my_detail.this,"没有更改的内容",Toast.LENGTH_SHORT).show();
                }else {
                    person user = new person();
                    user.setMobilePhoneNumber(p);
                    user.setEmail(em);
                    user.update(AccountUtils.getid(my_detail.this), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(my_detail.this,"保存成功",Toast.LENGTH_SHORT).show();
                                AccountUtils.setPhone(my_detail.this,p);
                                AccountUtils.setEmail(my_detail.this,em);
                            } else {
                                Toast.makeText(my_detail.this,e.toString(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;
        }
    }

}
