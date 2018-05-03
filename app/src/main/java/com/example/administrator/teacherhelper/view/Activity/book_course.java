package com.example.administrator.teacherhelper.view.Activity;

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
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2018/5/3 0003.
 */

public class book_course extends Activity {

    @Bind(R.id.book_name)
    EditText bookName;
    @Bind(R.id.book_course)
    TextView bookCourse;
    @Bind(R.id.course_select)
    ImageView courseSelect;
    String courseid;
    book book1;
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
    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_course);
        ButterKnife.bind(this);
        first();
        initView();
    }

    private void initView() {
        title.setText("教材");
        save.setVisibility(View.VISIBLE);
        bookName.setText(book1.getDespration());
    }

    private void first() {
        book1 = (book) getIntent().getSerializableExtra("book");
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


    @OnClick({R.id.right_button, R.id.course_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.right_button:
                getLoadingDialog().show();
                TEACH post = new TEACH();
                post.setObjectId(courseid);
//将当前用户添加到Post表中的likes字段值中，表明当前用户喜欢该帖子
                BmobRelation relation = new BmobRelation();
//将当前用户添加到多对多关联中
                relation.add(book1);
//多对多关联指向`post`的`likes`字段
                post.setBook(relation);
                post.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if(e==null){
                            getLoadingDialog().dismiss();
                            Toast.makeText(book_course.this, "保存成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(book_course.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.course_select:
                Intent intent = new Intent(book_course.this, my_course.class);
                intent.putExtra("sourse", CommenDate.maxschedule_jiaoxue);
                intent.putExtra("teacherid", BmobUser.getCurrentUser().getObjectId());
                startActivityForResult(intent, CommenDate.select_course);
                break;
        }
    }
}
