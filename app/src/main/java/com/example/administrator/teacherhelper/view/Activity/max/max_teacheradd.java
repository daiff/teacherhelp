package com.example.administrator.teacherhelper.view.Activity.max;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.Bean.FIELD;
import com.example.administrator.teacherhelper.Bean.person;
import com.example.administrator.teacherhelper.Commen.commenDate;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Activity.dialog.FlippingLoadingDialog;
import com.example.administrator.teacherhelper.view.select_Activity.value_select;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/3/20 0020.
 */

public class max_teacheradd extends Activity {

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
    @Bind(R.id.myadd_id)
    EditText myaddId;
    @Bind(R.id.myadd_password)
    EditText myaddPassword;
    @Bind(R.id.myadd_username)
    EditText myaddUsername;
    @Bind(R.id.myadd_title)
    EditText myaddTitle;
    @Bind(R.id.myadd_major)
    TextView myaddMajor;
    @Bind(R.id.myadd_selectmajor)
    ImageView myaddSelectmajor;
    @Bind(R.id.myadd_phone)
    EditText myaddPhone;
    @Bind(R.id.myadd_email)
    EditText myaddEmail;

    String majorid = null;
    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.max_teacheradd);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("录入教师");
        save.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                getLoadingDialog().show();
                person  person =new person();
                person.setDesperation(myaddUsername.getText().toString());
                person.setUsername(myaddId.getText().toString());
                person.setPassword(myaddPassword.getText().toString());
                person.setMobilePhoneNumber(myaddPhone.getText().toString());
                FIELD field = new FIELD();
                field.setObjectId(majorid);
                person.setXi(field);
                person.setTitle(myaddTitle.getText().toString());
                person.setEmail(myaddEmail.getText().toString());
                person.signUp(new SaveListener<person>() {
                    @Override
                    public void done(person person, BmobException e) {
                        getLoadingDialog().dismiss();
                        if (e==null){
                            Toast.makeText(max_teacheradd.this, "保存成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(max_teacheradd.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }

    @OnClick(R.id.myadd_selectmajor)
    public void onViewClicked() {
        Intent intent = new Intent(max_teacheradd.this,value_select.class);
        intent.putExtra("value",commenDate.value_major);
        startActivityForResult(intent, commenDate.select_major);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!(null == data || "".equals(data))) {
            majorid = data.getStringExtra("majorid");
            String majordesc = data.getStringExtra("majordesc");
            myaddMajor.setText(majordesc);
        }

    }
}
