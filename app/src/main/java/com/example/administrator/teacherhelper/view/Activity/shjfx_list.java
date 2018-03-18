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


import com.example.administrator.teacherhelper.Bean.TCH_analysis;
import com.example.administrator.teacherhelper.Bean.jiaoxue;
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
    shjfxAdapter adapter;
    TCH_analysis tch_analysis;
    List<TCH_analysis> arrayList = new ArrayList<TCH_analysis>();
    List<jiaoxue> tan = new ArrayList<jiaoxue>();
    @Bind(R.id.right_button)
    RelativeLayout rightButton;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    List<jiaoxue> jiaoxue = new ArrayList<jiaoxue>();
    jiaoxue teach ;
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
        Bmob.initialize(this, "ab8ec6ed95c785a2a470225606acee3e");
        init();
        data();
    }

    private void data() {
        BmobQuery<TCH_analysis> bmobQuery = new BmobQuery<>();
        jiaoxue j = new jiaoxue();
        bmobQuery.include("jiaoxue.ke,jiaoxue.classs,jiaoxue.grade,jiaoxue.major");
        bmobQuery.findObjects(new FindListener<TCH_analysis>() {
            @Override
            public void done(final List<TCH_analysis> list, BmobException e) {
                arrayList = list;
                adapter = new shjfxAdapter(list, shjfx_list.this);
                listt.setAdapter(adapter);
                listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        tch_analysis = list.get(position);
                        Intent intent = new Intent(shjfx_list.this, shjfx_detail.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("tch_analysis", tch_analysis);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void init() {
        title.setText("试卷分析列表");
        add.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                getLoadingDialog().setMessage("").show();
                selectdata();
                break;
        }
    }

    private void selectdata() {
        BmobQuery<jiaoxue> jiaoxueBmobQuery = new BmobQuery<>();
        jiaoxueBmobQuery.addWhereEqualTo("teacher", BmobUser.getCurrentUser());
        jiaoxueBmobQuery.addWhereEqualTo("schoolyear", AccountUtils.getyear(shjfx_list.this));
        jiaoxueBmobQuery.include("ke,classs,grade,kaikeyuan,major,nature,schoolyear");
        jiaoxueBmobQuery.findObjects(new FindListener<jiaoxue>() {
            @Override
            public void done(List<jiaoxue> list, BmobException e) {
            if (e == null){
                {
                    jiaoxue = list;
//        jiaoxueBmobQuery.groupby(new String[]{"ke"});
//        jiaoxueBmobQuery.include("ke");
//        jiaoxueBmobQuery.findStatistics(jiaoxue.class, new QueryListener<JSONArray>() {
//            @Override
//            public void done(JSONArray jsonArray, BmobException e) {
//              if (e==null){
//                  FIELD jiao = null;
//                  for (int i=0;i<jsonArray.length();i++){
//                     jiao = new FIELD();
//                      try {
//                          JSONObject jsonobject = jsonArray.getJSONObject(i).getJSONObject("ke");
//                          jiao.setDespration(jsonobject.getString("despration"));
//                          jiao.setCourse_code(jsonobject.getString("course_code"));
//                          jiao.setCredit(jsonobject.getString("credit"));
//                          jiao.setValue(jsonobject.getString("value"));
//                          jiao.setObjectId(jsonobject.getString("objectId"));
//                      } catch (JSONException e1) {
//                          e1.printStackTrace();
//                      }
//                      liatjiao.add(jiao);
                  }
              } else{ }
                //比较有没有相同的课
                for (int i=0;i<jiaoxue.size();i++){
                    for (int j=0;j<arrayList.size();j++){
                        if (!(jiaoxue.get(i).getObjectId().equals(arrayList.get(j).getJiaoxue().getObjectId()))){
//                                没有填写的加进去
                            tan.add(jiaoxue.get(i));
                        }
                    }
                }
                if (tan.size() !=0){
                    getLoadingDialog().setMessage("").dismiss();
                    ShowDialog();
                }else {
                    getLoadingDialog().setMessage("").dismiss();
                    Toast.makeText(shjfx_list.this, "没有要填写的内容", Toast.LENGTH_SHORT).show();
                }

            }
        });


}



    public void ShowDialog() {
        Context context = shjfx_list.this;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.formcommonlist, null);
        ListView myListView = (ListView) layout.findViewById(R.id.formcustomspinner_list);
        jiaoxueAdapter adapter = new jiaoxueAdapter(tan, shjfx_list.this);
        myListView.setAdapter(adapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int positon, long id) {
                Intent intent = new Intent(shjfx_list.this, shjfx_adddetail.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ke",tan.get(positon) );
                intent.putExtras(bundle);
                startActivity(intent);
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

        public jiaoxueAdapter() {
        }

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

            course_code.setText(student.getKe().getDespration()+ "  " + student.getGrade().getDespration() + "级" +student.getMajor().getDespration()+student.getClasss().getDespration() + " 班");
            return view;
        }

    }

}
