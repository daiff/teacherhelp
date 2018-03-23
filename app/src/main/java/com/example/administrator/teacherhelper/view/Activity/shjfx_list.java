package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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


import com.example.administrator.teacherhelper.Bean.TCH_analysis;
import com.example.administrator.teacherhelper.Bean.jiaoxue;
import com.example.administrator.teacherhelper.Commen.commenDate;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.until.AccountUtils;
import com.example.administrator.teacherhelper.view.Activity.dialog.FlippingLoadingDialog;
import com.example.administrator.teacherhelper.view.Adapter.shjfxAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import java.lang.reflect.Field;

/**
 * Created by Administrator on 2018/3/16 0016.
 */

public class shjfx_list extends Activity {

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

    shjfxAdapter adapter;

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    List<jiaoxue> mycourse;
    List<TCH_analysis> allworksum;
    List<jiaoxue> nothave = new ArrayList<>();
    List<TCH_analysis> have = new ArrayList<>();

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
        init();
        data();
    }
    private void init() {
        title.setText("试卷分析列表");
        add.setVisibility(View.VISIBLE);
    }

    private  void data(){
        mycourse = new ArrayList<>();
        allworksum = new ArrayList<>();
        nothave = new ArrayList<>();
        have = new ArrayList<>();
        getLoadingDialog().show();
        BmobQuery<jiaoxue> jiaoxueBmobQuery =new BmobQuery<>();
        jiaoxueBmobQuery.addWhereEqualTo("teacher",BmobUser.getCurrentUser());
        jiaoxueBmobQuery.addWhereEqualTo("schoolyear", AccountUtils.getyear(shjfx_list.this));
        jiaoxueBmobQuery.include(commenDate.include_jiaoxue);
        jiaoxueBmobQuery.order("-createdAt");
        jiaoxueBmobQuery.findObjects(new FindListener<jiaoxue>() {
            @Override
            public void done(List<jiaoxue> list, BmobException e) {
                if (e==null){
                    if (list.size() == 0){
                        getLoadingDialog().dismiss();
                        Toast.makeText(shjfx_list.this, "本学期您没有课程", Toast.LENGTH_SHORT).show();
                    }else{
                        mycourse = list;
                        getWorkSum();
                    }
                }else {
                    getLoadingDialog().dismiss();
                    Toast.makeText(shjfx_list.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void getWorkSum(){
        allworksum = new ArrayList<>();
        BmobQuery<TCH_analysis> bmobQuery = new BmobQuery<>();
        bmobQuery.include("jiaoxue.ke,jiaoxue.classs,jiaoxue.grade,jiaoxue.major," +
                "jiaoxue.college,jiaoxue.schoolyear,jiaoxue.nature,jiaoxue.kaikeyuan,jiaoxue.book," +
                "jiaoxue.classs.classs,jiaoxue.classs.college,jiaoxue.classs.grade,jiaoxue.classs.major");
        bmobQuery.order("-createdAt");
        bmobQuery.findObjects(new FindListener<TCH_analysis>() {
            @Override
            public void done(final List<TCH_analysis> list, BmobException e) {
                if (e==null){
                    allworksum = list;
                    gethave();
                }else {
                    getLoadingDialog().dismiss();
                    Toast.makeText(shjfx_list.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void gethave() {
        for (int i = 0; i < mycourse.size(); i++) {
            for (int j = 0; j < allworksum.size(); j++) {
                if (mycourse.get(i).getObjectId().equals(allworksum.get(j).getJiaoxue().getObjectId())) {
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
                if (mycourse.get(i).getObjectId().equals(allworksum.get(j).getJiaoxue().getObjectId()) )
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
            adapter = new shjfxAdapter(have, shjfx_list.this);
            listt.setAdapter(adapter);
            listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(shjfx_list.this, shjfx_detail.class);
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
        Context context = shjfx_list.this;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.formcommonlist, null);
        ListView myListView = (ListView) layout.findViewById(R.id.formcustomspinner_list);
        jiaoxueAdapter adapter = new jiaoxueAdapter(nothave, shjfx_list.this);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int positon, long id) {
                Intent intent = new Intent(shjfx_list.this, shjfx_adddetail.class);
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
        private List<jiaoxue> stuList;
        private LayoutInflater inflater;
        public jiaoxueAdapter() {}
        public jiaoxueAdapter(List<jiaoxue> stuList, Context context) {
            this.stuList = stuList;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return stuList == null ? 0 : stuList.size();
        }

        @Override
        public jiaoxue getItem(int position) {
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
            jiaoxue student = getItem(position);
            //在view视图中查找id为image_photo的控件
            TextView course_code = (TextView) view.findViewById(R.id.tv_name);
            course_code.setText(student.getKe().getDespration()+ "  " + student.getClasss().getGrade().getDespration() + "级" +student.getClasss().getMajor().getDespration()+student.getClasss().getClasss().getDespration() + " 班");
            return view;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        data();
    }


}
