package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.TCH_calender;
import com.example.administrator.teacherhelper.bean.book;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.view.Activity.select_Activity.book_select;
import com.example.administrator.teacherhelper.view.Adapter.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class Calendar_AddBook extends Activity {
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
    @Bind(R.id.course_sc)
    TextView courseSc;
    @Bind(R.id.select_course_sc)
    ImageView selectCourseSc;
    @Bind(R.id.course_tea)
    TextView courseTea;
    @Bind(R.id.select_course_tea)
    ImageView selectCourseTea;
    @Bind(R.id.course_cour)
    TextView courseCour;
    @Bind(R.id.select_course_cour)
    ImageView selectCourseCour;
    @Bind(R.id.course_kai)
    TextView courseKai;
    @Bind(R.id.select_course_kai)
    ImageView selectCourseKai;
    private String calenderid;

    private  String book1="";
    private  String book2="";
    private  String book3="";
    private  String book4="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_addbook);
        ButterKnife.bind(this);
        first();
        initView();
    }

    private void initView() {
        title.setText("选择教材");
        save.setVisibility(View.VISIBLE);
    }

    private void first() {
        calenderid = getIntent().getStringExtra("calenderid");
    }

    @OnClick({R.id.back1, R.id.right_button, R.id.select_course_sc, R.id.select_course_tea, R.id.select_course_cour, R.id.select_course_kai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                saveall();
                break;
            case R.id.select_course_sc:
                Intent intent = new Intent(Calendar_AddBook.this, book_select.class);
                intent.putExtra("book", CommenDate.maxcour_book);
                startActivityForResult(intent,CommenDate.select_book);
                break;
            case R.id.select_course_tea:
                Intent intent1 = new Intent(Calendar_AddBook.this, book_select.class);
                intent1.putExtra("book", CommenDate.maxcour_book);
                startActivityForResult(intent1,CommenDate.select_book1);
                break;
            case R.id.select_course_cour:
                Intent intent2 = new Intent(Calendar_AddBook.this, book_select.class);
                intent2.putExtra("book", CommenDate.maxcour_book);
                startActivityForResult(intent2,CommenDate.select_book2);
                break;
            case R.id.select_course_kai:
                Intent intent3 = new Intent(Calendar_AddBook.this, book_select.class);
                intent3.putExtra("book", CommenDate.maxcour_book);
                startActivityForResult(intent3,CommenDate.select_book3);
                break;
        }
    }

    private void saveall(){
        book b1 = new book();
        b1.setObjectId(book1);
        BmobRelation relation = new BmobRelation();
//将当前用户添加到多对多关联中
        relation.add(b1);
        if (!(book2.equals(""))){
            book b2 = new book();
            b2.setObjectId(book2);
            relation.add(b2);
        }

        if (!(book3.equals(""))){
            book b3 = new book();
            b3.setObjectId(book3);
            relation.add(b3);
        }
        if (!(book4.equals(""))){
            book b4 = new book();
            b4.setObjectId(book4);
            relation.add(b4);
        }

        TCH_calender post = new TCH_calender();
        post.setObjectId(calenderid);
//将当前用户添加到Post表中的likes字段值中，表明当前用户喜欢该帖子

//多对多关联指向`post`的`likes`字段
        post.setBook(relation);
        post.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Toast.makeText(Calendar_AddBook.this, "保存成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Calendar_AddBook.this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!(null == data || "".equals(data))) {
            if (requestCode == CommenDate.select_book) {
                book1 = data.getStringExtra("bookid");
                String majordesc = data.getStringExtra("bookname");
                courseSc.setText(majordesc);
            }else if (requestCode == CommenDate.select_book1) {
                book2 = data.getStringExtra("bookid");
                String majordesc = data.getStringExtra("bookname");
                courseTea.setText(majordesc);
            }else if (requestCode == CommenDate.select_book2) {
                book3 = data.getStringExtra("bookid");
                String majordesc = data.getStringExtra("bookname");
                courseCour.setText(majordesc);
            }else if (requestCode == CommenDate.select_book3) {
                book4 = data.getStringExtra("bookid");
                String majordesc = data.getStringExtra("bookname");
                courseKai.setText(majordesc);
            }
        }
    }
}
