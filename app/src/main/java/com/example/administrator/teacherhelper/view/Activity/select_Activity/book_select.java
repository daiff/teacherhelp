package com.example.administrator.teacherhelper.view.Activity.select_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.bean.book;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Activity.book_course;
import com.example.administrator.teacherhelper.view.Activity.max.Max_bookadd;
import com.example.administrator.teacherhelper.view.Adapter.book_adapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class book_select extends Activity {
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
    String select;

    book_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapteractivity);
        ButterKnife.bind(this);
        first();
        initView();
        getData();
    }
    private void first(){
        select = getIntent().getStringExtra("book");
    }

    private void getData() {
        BmobQuery<book> bookbmob = new BmobQuery<>();
        bookbmob.order("-createdAt");
        bookbmob.findObjects(new FindListener<book>() {
            @Override
            public void done(final List<book> list, BmobException e) {
                if (e==null){
                    if (list.size() ==0){
                        Toast.makeText(book_select.this, "没有教材信息", Toast.LENGTH_SHORT).show();
                    }else {
                        adapter = new book_adapter(list, book_select.this);
                        listt.setAdapter(adapter);
                        if (select.equals(CommenDate.maxcour_book)){
                            listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent();
                                    intent.putExtra("bookid",list.get(position).getObjectId());
                                    intent.putExtra("bookname",list.get(position).getDespration());
                                    setResult(CommenDate.select_book,intent);
                                    finish();
                                }
                            });
                        }else if (select.equals("wode")){
                            listt.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                @Override
                                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(book_select.this,book_course.class);
                                    intent.putExtra("book",list.get(position));
                                    startActivity(intent);
                                    return true;
                                }
                            });

                        }
                    }
                }else {
                    Toast.makeText(book_select.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        title.setText("教材");
        if (!(select.equals(CommenDate.maxcour_book))){
            add.setVisibility(View.VISIBLE);
        }

    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                Intent intent = new Intent(book_select.this,Max_bookadd.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getData();
    }
}
