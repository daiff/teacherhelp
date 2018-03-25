package com.example.administrator.teacherhelper.view.Activity.max;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.bean.book;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class Max_bookadd extends Activity {
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
    @Bind(R.id.book_name)
    EditText bookName;
    @Bind(R.id.bookauther)
    EditText bookauther;
    @Bind(R.id.booknum)
    EditText booknum;
    @Bind(R.id.bookpurchase)
    EditText bookpurchase;
    @Bind(R.id.bookdate)
    EditText bookdate;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.max_addbook);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("教材信息");
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
                book b = new book();
                b.setDespration(bookName.getText().toString());
                b.setEd(bookauther.getText().toString());
                b.setNumberOfWords(booknum.getText().toString());
                b.setPress(bookpurchase.getText().toString());
                b.setPublishing_time(bookdate.getText().toString());
                b.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        getLoadingDialog().dismiss();
                        if (e==null){
                            Toast.makeText(Max_bookadd.this, "保存成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Max_bookadd.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
