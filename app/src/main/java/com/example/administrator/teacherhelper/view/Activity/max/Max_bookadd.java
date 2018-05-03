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

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.TEACH;
import com.example.administrator.teacherhelper.bean.book;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.view.Activity.my_course;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

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

    String courseid;

    protected FlippingLoadingDialog mLoadingDialog;
    @Bind(R.id.book_course)
    TextView bookCourse;
    @Bind(R.id.course_select)
    ImageView courseSelect;

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
                //先将书籍信息增加到book表中
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
                        if (e == null) {
                            //成功之后将书籍关联到教课表
                            book b = new book();
                            b.setObjectId(s);
                            TEACH post = new TEACH();
                            post.setObjectId(courseid);
//将当前用户添加到Post表中的likes字段值中，表明当前用户喜欢该帖子
                            BmobRelation relation = new BmobRelation();
//将当前用户添加到多对多关联中
                            relation.add(b);
//多对多关联指向`post`的`likes`字段
                            post.setBook(relation);
                            post.update(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if(e==null){
                                        getLoadingDialog().dismiss();
                                        Toast.makeText(Max_bookadd.this, "保存成功", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(Max_bookadd.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(Max_bookadd.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }

    @OnClick(R.id.course_select)
    public void onViewClicked() {
        Intent intent = new Intent(Max_bookadd.this,my_course.class);
        intent.putExtra("sourse",CommenDate.maxschedule_jiaoxue);
        intent.putExtra("teacherid", BmobUser.getCurrentUser().getObjectId());
        startActivityForResult(intent, CommenDate.select_course);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!(null == data || "".equals(data))) {
            courseid = data.getStringExtra("courseid");
            String majordesc = data.getStringExtra("course");
            bookCourse.setText(majordesc);
        }
    }
}
