package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.bean.book;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Adapter.book_adapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class Calendar_Book extends Activity {


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
    @Bind(R.id.book_title)
    EditText bookTitle;
    @Bind(R.id.book_auther)
    EditText bookAuther;
    @Bind(R.id.book_chuban)
    EditText bookChuban;
    @Bind(R.id.book_riqi)
    EditText bookRiqi;
    @Bind(R.id.book_zi)
    EditText bookZi;

    String calenderid;
    book boo;
    book_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_book);
        ButterKnife.bind(this);
        first();
        initView();
        initData();
    }

    private void initData() {
        BmobQuery<book>  bbook = new BmobQuery<>();
        bbook.addWhereEqualTo("tch_calender",calenderid);
        bbook.findObjects(new FindListener<book>() {
            @Override
            public void done(List<book> list, BmobException e) {
                if (e==null){
                    if (list.size()!=0){
                        adapter = new book_adapter(list,Calendar_Book.this);
                        listt.setAdapter(adapter);
                    }
                }else {
                    Toast.makeText(Calendar_Book.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void first() {
        calenderid = getIntent().getStringExtra("calenderid");
        boo = (book)getIntent().getSerializableExtra("book");
    }

    private void initView() {
        title.setText("教材信息");
        bookTitle.setText(boo.getDespration());
        bookAuther.setText(boo.getEd());
        bookChuban.setText(boo.getPress());
        bookRiqi.setText(boo.getPublishing_time());
        bookZi.setText(boo.getNumberOfWords());
    }


    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}
