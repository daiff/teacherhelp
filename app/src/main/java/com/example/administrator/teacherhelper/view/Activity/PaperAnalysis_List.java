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


import com.example.administrator.teacherhelper.bean.TCH_analysis;
import com.example.administrator.teacherhelper.bean.TEACH;
import com.example.administrator.teacherhelper.bean.jiaoxue;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.until.AccountUtils;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;
import com.example.administrator.teacherhelper.view.Adapter.PaperAnalysis;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by Administrator on 2018/3/16 0016.
 */

public class PaperAnalysis_List extends Activity {

    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.back1)
    RelativeLayout back1;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.add)
    ImageButton add;
    @Bind(R.id.listt)
    ListView listt;
    @Bind(R.id.right_button)
    RelativeLayout rightButton;

    PaperAnalysis adapter;

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    List<TEACH> mycourse;
    List<TCH_analysis> allworksum;
    List<TEACH> nothave;
    List<TCH_analysis> have;
    String resource;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapteractivity);
        ButterKnife.bind(this);
        Bmob.initialize(this, "ab8ec6ed95c785a2a470225606acee3e");
        first();
        init();
        InitData();
    }

    private void InitData() {
        mycourse = new ArrayList<>();
        allworksum = new ArrayList<>();
        nothave = new ArrayList<>();
        have = new ArrayList<>();
        getLoadingDialog().show();
        if (resource.equals(CommenDate.main)){
            data();
        }else if (resource.equals(CommenDate.max)){
            BmobQuery<TCH_analysis> bmobQuery = new BmobQuery<>();
            bmobQuery.include(CommenDate.include_commen);
            bmobQuery.order("-createdAt");
            bmobQuery.findObjects(new FindListener<TCH_analysis>() {
                @Override
                public void done(final List<TCH_analysis> list, BmobException e) {
                    getLoadingDialog().dismiss();
                    if (e==null){if (list.size()!=0){
                        adapter = new PaperAnalysis(list, PaperAnalysis_List.this);
                        listt.setAdapter(adapter);
                        listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(PaperAnalysis_List.this, PaperAnalysis_Detial.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("tch_analysis", list.get(position));
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }
                    }else {
                        Toast.makeText(PaperAnalysis_List.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void first() {
        resource = getIntent().getStringExtra("resource");
    }

    private void init() {
        title.setText("试卷分析列表");
        add.setVisibility(View.VISIBLE);
    }

    private  void data(){
        BmobQuery<TEACH> jiaoxueBmobQuery =new BmobQuery<>();
        jiaoxueBmobQuery.addWhereEqualTo("Teacher",BmobUser.getCurrentUser());
        jiaoxueBmobQuery.addWhereEqualTo("Schoolyear", AccountUtils.getyear(PaperAnalysis_List.this));
        jiaoxueBmobQuery.include(CommenDate.include_jiaoxue);
        jiaoxueBmobQuery.order("-createdAt");
        jiaoxueBmobQuery.findObjects(new FindListener<TEACH>() {
            @Override
            public void done(List<TEACH> list, BmobException e) {
                if (e==null){
                    if (list.size() == 0){
                        getLoadingDialog().dismiss();
                        Toast.makeText(PaperAnalysis_List.this, "本学期您没有课程", Toast.LENGTH_SHORT).show();
                    }else{
                        mycourse = list;
                        getWorkSum();
                    }
                }else {
                    getLoadingDialog().dismiss();
                    Toast.makeText(PaperAnalysis_List.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void getWorkSum(){
        BmobQuery<TCH_analysis> bmobQuery = new BmobQuery<>();
        bmobQuery.include(CommenDate.include_commen);
        bmobQuery.order("-createdAt");
        bmobQuery.findObjects(new FindListener<TCH_analysis>() {
            @Override
            public void done(final List<TCH_analysis> list, BmobException e) {
                if (e==null){
                    allworksum = list;
                    gethave();
                }else {
                    getLoadingDialog().dismiss();
                    Toast.makeText(PaperAnalysis_List.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void gethave() {
        for (int i = 0; i < mycourse.size(); i++) {
            for (int j = 0; j < allworksum.size(); j++) {
                if (mycourse.get(i).getObjectId().equals(allworksum.get(j).getCourse().getObjectId())) {
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
                if (mycourse.get(i).getObjectId().equals(allworksum.get(j).getCourse().getObjectId()) )
                    break;
            if (j == allworksum.size())
                nothave.add(mycourse.get(i));
        }
    }

    //    显示已填写的工作总结
    private void showData() {
        getLoadingDialog().dismiss();
        if (have.size()==0){
            Toast.makeText(this, "没有任何已填写的试卷分析表", Toast.LENGTH_SHORT).show();
        }else {
            adapter = new PaperAnalysis(have, PaperAnalysis_List.this);
            listt.setAdapter(adapter);
            listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(PaperAnalysis_List.this, PaperAnalysis_Detial.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tch_analysis", have.get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                if (nothave.size()==0){
                    Toast.makeText(this, "本学期的试卷分析表已全部填写", Toast.LENGTH_SHORT).show();
                }else {
                    ShowDialog();
                }
                break;
        }
    }
    public void ShowDialog() {
        Context context = PaperAnalysis_List.this;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.formcommonlist, null);
        ListView myListView = (ListView) layout.findViewById(R.id.formcustomspinner_list);
        jiaoxueAdapter adapter = new jiaoxueAdapter(nothave, PaperAnalysis_List.this);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int positon, long id) {
                Intent intent = new Intent(PaperAnalysis_List.this, PaperAnalysis_Add.class);
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
        InitData();
    }


}
