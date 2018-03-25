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

import com.example.administrator.teacherhelper.bean.TCH_calender;
import com.example.administrator.teacherhelper.bean.jiaoxue;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.until.AccountUtils;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;
import com.example.administrator.teacherhelper.view.Adapter.jxriAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by daiff on 2018/1/29.
 * for:
 */

public class jxrl_Activity extends Activity {
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

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    List<jiaoxue> mycourse;
    List<TCH_calender> allworksum;
    List<jiaoxue> nothave = new ArrayList<>();
    List<TCH_calender> have = new ArrayList<>();

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;}

    jxriAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapteractivity);
        ButterKnife.bind(this);
        init();
        data();
    }

    private void init() {
        title.setText("教学日历");
        add.setVisibility(View.VISIBLE);
    }

    private  void data(){
        mycourse = new ArrayList<>();
        allworksum = new ArrayList<>();
        nothave = new ArrayList<>();
        have = new ArrayList<>();
        getLoadingDialog().show();
        BmobQuery<jiaoxue> jiaoxueBmobQuery =new BmobQuery<>();
        jiaoxueBmobQuery.addWhereEqualTo("teacher", BmobUser.getCurrentUser());
        jiaoxueBmobQuery.addWhereEqualTo("schoolyear", AccountUtils.getyear(jxrl_Activity.this));
        jiaoxueBmobQuery.include(CommenDate.include_jiaoxue);
        jiaoxueBmobQuery.order("-createdAt");
        jiaoxueBmobQuery.findObjects(new FindListener<jiaoxue>() {
            @Override
            public void done(List<jiaoxue> list, BmobException e) {
                if (e==null){
                    if (list.size() == 0){
                        getLoadingDialog().dismiss();
                        Toast.makeText(jxrl_Activity.this, "本学期您没有课程", Toast.LENGTH_SHORT).show();
                    }else{
                        mycourse = list;
                        getWorkSum();
                    }
                }else {
                    getLoadingDialog().dismiss();
                    Toast.makeText(jxrl_Activity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getWorkSum(){
        allworksum = new ArrayList<>();
        BmobQuery<TCH_calender> bmobQuery = new BmobQuery<>();
        bmobQuery.include("jiaoxue.book,jiaoxue.kaikeyuan,jiaoxue.ke,jiaoxue.nature," +
                "jiaoxue.schoolyear,jiaoxue.teacher,jiaoxue.classs.college," +
                "jiaoxue.classs.classs,jiaoxue.classs.grade,jiaoxue.classs.major,tca_time");
        bmobQuery.order("-createdAt");
        bmobQuery.findObjects(new FindListener<TCH_calender>() {
            @Override
            public void done(final List<TCH_calender> list, BmobException e) {
                if (e==null){
                    allworksum = list;
                    gethave();
                }else {
                    getLoadingDialog().dismiss();
                    Toast.makeText(jxrl_Activity.this, e.toString(), Toast.LENGTH_SHORT).show();
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
            adapter = new jxriAdapter(have, jxrl_Activity.this);
            listt.setAdapter(adapter);
            listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(jxrl_Activity.this, jxrl_detial.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tch_calender", have.get(position));
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
                    Toast.makeText(this, "本学期的教学日历已全部填写", Toast.LENGTH_SHORT).show();
                }else {
                    ShowDialog();
                }
                break;
        }
    }

    public void ShowDialog() {
        Context context = jxrl_Activity.this;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.formcommonlist, null);
        ListView myListView = (ListView) layout.findViewById(R.id.formcustomspinner_list);
        jiaoxueAdapter adapter = new jiaoxueAdapter(nothave, jxrl_Activity.this);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int positon, long id) {
                Intent intent = new Intent(jxrl_Activity.this, PaperAnalysis_Add.class);
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
//            course_code.setText(student.getKe().getDespration()+ "  " + student.getClasss().getGrade().getDespration() + "级" +student.getClasss().getMajor().getDespration()+student.getClasss().getClasss().getDespration() + " 班");
            return view;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        data();
    }
}
