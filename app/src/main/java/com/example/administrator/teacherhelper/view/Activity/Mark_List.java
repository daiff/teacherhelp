package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.MARK;
import com.example.administrator.teacherhelper.bean.TCH_calender;
import com.example.administrator.teacherhelper.bean.TEACH;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.until.AccountUtils;
import com.example.administrator.teacherhelper.view.Adapter.Calendar;
import com.example.administrator.teacherhelper.view.Adapter.jcourseAdapter;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public class Mark_List extends Activity {
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

    jcourseAdapter adapter;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    String resource;


    List<TEACH> mycourse;
    List<MARK> allworksum;
    List<TEACH> nothave;
    List<MARK> have;


    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapteractivity);
        ButterKnife.bind(this);
        first();
        iniitView();
        initData();
    }
    private void first() {
        resource = getIntent().getStringExtra("resource");
    }

    private void initData() {
        getLoadingDialog().show();
        mycourse = new ArrayList<>();
        allworksum = new ArrayList<>();
        nothave = new ArrayList<>();
        have = new ArrayList<>();
        if (resource.equals(CommenDate.main)){
            data();
        }else if (resource.equals(CommenDate.max)){
            allworksum = new ArrayList<>();
            BmobQuery<MARK> bmobQuery = new BmobQuery<>();
            bmobQuery.include(CommenDate.include_MARK);
            bmobQuery.order("-createdAt");
            bmobQuery.findObjects(new FindListener<MARK>() {
                @Override
                public void done(final List<MARK> list, BmobException e) {
                    getLoadingDialog().dismiss();
                    if (e==null){
                        if (list.size()!=0){
                            adapter = new jcourseAdapter(list, Mark_List.this);
                            listt.setAdapter(adapter);
                            listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(Mark_List.this, MarkDetial.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("jiaoxueid", list.get(position));
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                        }
                    }else {
                        getLoadingDialog().dismiss();
                        Toast.makeText(Mark_List.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private  void data(){
        BmobQuery<TEACH> jiaoxueBmobQuery =new BmobQuery<>();
        jiaoxueBmobQuery.addWhereEqualTo("Teacher", BmobUser.getCurrentUser());
        jiaoxueBmobQuery.addWhereEqualTo("Schoolyear", AccountUtils.getyear(Mark_List.this));
        jiaoxueBmobQuery.include(CommenDate.include_jiaoxue);
        jiaoxueBmobQuery.order("-createdAt");
        jiaoxueBmobQuery.findObjects(new FindListener<TEACH>() {
            @Override
            public void done(List<TEACH> list, BmobException e) {
                if (e==null){
                    if (list.size() == 0){
                        getLoadingDialog().dismiss();
                        Toast.makeText(Mark_List.this, "本学期您没有课程", Toast.LENGTH_SHORT).show();
                    }else{
                        mycourse = list;
                        getWorkSum();
                    }
                }else {
                    getLoadingDialog().dismiss();
                    Toast.makeText(Mark_List.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getWorkSum(){
        allworksum = new ArrayList<>();
        BmobQuery<MARK> bmobQuery = new BmobQuery<>();
        bmobQuery.include(CommenDate.include_MARK);
        bmobQuery.order("-createdAt");
        bmobQuery.findObjects(new FindListener<MARK>() {
            @Override
            public void done(final List<MARK> list, BmobException e) {
                if (e==null){
                    allworksum = list;
                    gethave();
                }else {
                    getLoadingDialog().dismiss();
                    Toast.makeText(Mark_List.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void gethave() {
        for (int i = 0; i < mycourse.size(); i++) {
            for (int j = 0; j < allworksum.size(); j++) {
                if (mycourse.get(i).getObjectId().equals(allworksum.get(j).getTeach().getObjectId())) {
                    have.add(allworksum.get(j));
                }
            }
        }
        getnohave();
        showData();
    }

    private void getnohave(){
        int i = 0, j = 0;
        for ( i = 0; i < mycourse.size(); ++i) {
            for ( j = 0; j < allworksum.size(); ++j)
                if (mycourse.get(i).getObjectId().equals(allworksum.get(j).getTeach().getObjectId()) )
                    break;
            if (j == allworksum.size())
                nothave.add(mycourse.get(i));
        }
    }

    //    显示已填写的工作总结
    private void showData() {
        getLoadingDialog().dismiss();
        if (have.size()==0){
            Toast.makeText(this, "没有任何已填写的平时成绩表", Toast.LENGTH_SHORT).show();
        }else {
            adapter = new jcourseAdapter(have, Mark_List.this);
            listt.setAdapter(adapter);
            listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(Mark_List.this, MarkDetial.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("jiaoxueid", have.get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }



    private void iniitView() {
        title.setText("平时成绩");
        add.setVisibility(View.VISIBLE);
        if (resource.equals(CommenDate.main)){
            add.setVisibility(View.VISIBLE);
            rightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (nothave.size()==0){
                        Toast.makeText(Mark_List.this, "没有要填写的课程", Toast.LENGTH_SHORT).show();
                    }else {
                        ShowDialog();
                    }
                }
            });
        }
    }

    @OnClick({R.id.back1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
        }
    }


    public void ShowDialog() {
        Context context = Mark_List.this;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.formcommonlist, null);
        ListView myListView = (ListView) layout.findViewById(R.id.formcustomspinner_list);
        jiaoxueAdapter adapter = new jiaoxueAdapter(nothave, Mark_List.this);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int positon, long id) {
                Intent intent = new Intent(Mark_List.this, Mark_add.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ke",nothave.get(positon) );
                intent.putExtras(bundle);
                startActivity(intent);
                alertDialog.dismiss();
            }
        });
        builder = new AlertDialog.Builder(context);
        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.show();
    }
    //    自定义适配器
    class jiaoxueAdapter extends BaseAdapter {
        private List<TEACH> stuList;
        private LayoutInflater inflater;
        public jiaoxueAdapter() {}
        public jiaoxueAdapter(List<TEACH> stuList, Context context) {
            this.stuList = stuList;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return stuList == null ? 0 : stuList.size();
        }

        @Override
        public TEACH getItem(int position) {
            return stuList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //加载布局为一个视图
            View view = inflater.inflate(R.layout.rtu_item, null);
            TEACH student = getItem(position);
            //在view视图中查找id为image_photo的控件
            TextView course_code = (TextView) view.findViewById(R.id.tv_name);
            course_code.setText(student.getCourse().getDespration());
            return view;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }
}
