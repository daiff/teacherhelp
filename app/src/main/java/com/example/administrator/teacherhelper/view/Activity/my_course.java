package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.Bean.FIELD;
import com.example.administrator.teacherhelper.Bean.TEACH;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.widght.SwipeRefreshLayout;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by Administrator on 2018/3/14 0014.
 */

public class my_course extends Activity {
    private static final String TAG = "my_course";
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.back1)
    RelativeLayout back1;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.add)
    ImageButton add;
    @Bind(R.id.save)
    ImageButton save;
    @Bind(R.id.recyclerView_id)
    RecyclerView recyclerViewId;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @Bind(R.id.have_not_data_id)
    LinearLayout haveNotDataId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_course);
        ButterKnife.bind(this);
        Bmob.initialize(this, "ab8ec6ed95c785a2a470225606acee3e");
        init();
        getData();
    }
    private void init(){
        title.setText("本学期课程");
    }
    private void show(String msg) {
        Toast.makeText(my_course.this, msg, Toast.LENGTH_LONG).show();
    }

    private void getData(){
        BmobQuery<TEACH> bmobQuery = new BmobQuery<TEACH>();
//        bmobQuery.include("system,college,semester,schoolyear,schedule,nature,major,grade,coursecode,teacher");
        bmobQuery.getObject("hcKrOOOW", new QueryListener<TEACH>() {
          @Override
          public void done(TEACH teach, BmobException e) {
              if (e==null){
                  show("chg");
              }

          }
      });
//        BmobQuery<FIELD> bmobQuery = new BmobQuery<FIELD>();
//        bmobQuery.findObjects(new FindListener<FIELD>() {
//            @Override
//            public void done(List<FIELD> list, BmobException e) {
//                if(e==null){
////                    toast("查询成功");
//                }else{
////                    toast("查询失败：" + e.getMessage());
//                }
//            }
//        });
    };

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}
