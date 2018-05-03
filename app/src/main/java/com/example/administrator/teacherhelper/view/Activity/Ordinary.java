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

import com.example.administrator.teacherhelper.bean.TEACH;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.until.AccountUtils;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;
import com.example.administrator.teacherhelper.view.Adapter.jcourseAdapter;

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
 * Created by daiff on 2018/1/29.
 * for:平时成绩
 */

public class Ordinary extends Activity {
//    private final static String TAG= "Summary_Item";
//    @Bind(R.id.back)
//    ImageButton back;
//    @Bind(R.id.back1)
//    RelativeLayout back1;
//    @Bind(R.id.title)
//    TextView title;
//    @Bind(R.id.re_title)
//    RelativeLayout reTitle;
//    @Bind(R.id.add)
//    ImageButton add;
//    @Bind(R.id.save)
//    ImageButton save;
//    @Bind(R.id.tishi)
//    ImageButton tishi;
//    @Bind(R.id.right_button)
//    RelativeLayout rightButton;
//    @Bind(R.id.listt)
//    ListView listt;
//    private AlertDialog.Builder builder;
//    private AlertDialog alertDialog;
//    List<TEACH> mycourse;//我所有的课程
//    List<TEACH> nothave;//我没有填写过平时成绩的课程
//    List<TEACH> have;//我填写过平时成绩的课程
//    List<TEACH> havejiaoxue;//有的
//    jcourseAdapter adapter;
//
//    protected FlippingLoadingDialog mLoadingDialog;
//    private FlippingLoadingDialog getLoadingDialog() {
//        if (mLoadingDialog == null)
//            mLoadingDialog = new FlippingLoadingDialog(this);
//        return mLoadingDialog;
//    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.adapteractivity);
//        ButterKnife.bind(this);
//        initView();
//        initDate();
//    }
//
//
//    private void initView() {
//        title.setText("平时成绩");
//        add.setVisibility(View.VISIBLE);
//    }
//
//    @OnClick({R.id.back1, R.id.right_button})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.back1:
//                finish();
//                break;
//            case R.id.right_button:
//                if (nothave.size()==0){
//                    Toast.makeText(this, "本学期的试卷分析表已全部填写", Toast.LENGTH_SHORT).show();
//                }else {
//                    ShowDialog();
//                }
//                break;
//   }
//}
//    private void initDate() {
//        getLoadingDialog().show();
//        mycourse = new ArrayList<>();
//        nothave = new ArrayList<>();
//        have = new ArrayList<>();
//        getmycourse();
//    }
//
//
//
//    //本教师本学期的所有课程
//    private void getmycourse(){
//        BmobQuery<TEACH> jiaoxueBmobQuery =new BmobQuery<>();
//        jiaoxueBmobQuery.addWhereEqualTo("Teacher", BmobUser.getCurrentUser());
//        jiaoxueBmobQuery.addWhereEqualTo("Schoolyear", AccountUtils.getyear(Ordinary.this));
//        jiaoxueBmobQuery.include(CommenDate.include_jiaoxue);
//        jiaoxueBmobQuery.order("-createdAt");
//        jiaoxueBmobQuery.findObjects(new FindListener<TEACH>() {
//            @Override
//            public void done(List<TEACH> list, BmobException e) {
//                if (e==null){
//                    if (list.size() == 0){
//                        getLoadingDialog().dismiss();
//                        Toast.makeText(Ordinary.this, "本学期您没有课程", Toast.LENGTH_SHORT).show();
//                    }else{
//                        mycourse = list;
//                        getWorkSum();
//                    }
//                }else {
//                    getLoadingDialog().dismiss();
//                    Toast.makeText(Ordinary.this, e.toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//
////本学期已经填写过的课程
//    private void getWorkSum() {
//        BmobQuery<MARK> tchachieve = new BmobQuery<>();
//        tchachieve.groupby(new String[]{"Course"});
//        tchachieve.include(CommenDate.achieve_jiaoxue);
//        tchachieve.order("-createdAt");//降序排列
//        tchachieve.findStatistics(MARK.class, new QueryListener<JSONArray>() {
//            @Override
//            public void done(JSONArray jsonArray, BmobException e) {
//                if (e==null){
//                    for (int i=0 ;i<jsonArray.length();i++){
//                        TEACH jiao = new TEACH();
//                        try {
//                            JSONObject jsonobject = jsonArray.getJSONObject(i).getJSONObject("Course");
//                            jiao.setObjectId(jsonobject.getString("objectId"));
//                        } catch (JSONException e1) {
//                            e1.printStackTrace();
//                        }
//                        havejiaoxue.add(jiao);
//                    }
//                    gethave();
//                }else{
//                    getLoadingDialog().dismiss();
//                    Toast.makeText(Ordinary.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void gethave() {
//        for (int i =0;i<mycourse.size();i++){
//            for (int j=0;j<havejiaoxue.size();j++){
//                if (mycourse.get(i).getObjectId() .equals(havejiaoxue.get(j).getObjectId()) ){
//                    have.add(mycourse.get(i));
//                }
//            }
//        }
//        getnohave();
//        showData();
//    }
//
//
//    //    显示已填写的工作总结
//    private void showData() {
//        getLoadingDialog().dismiss();
//        if (have.size()==0){
//            Toast.makeText(this, "没有任何已填写的平时成绩表", Toast.LENGTH_SHORT).show();
//        }else {
//            adapter = new jcourseAdapter(havejiaoxue, Ordinary.this);
//            listt.setAdapter(adapter);
//            listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(Ordinary.this, zcj_detial.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("jiaoxueid", have.get(position));
//                    bundle.putSerializable("source","pscj");
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//                }
//            });
//        }
//    }
//
//    private void getnohave() {
//        int i = 0, j = 0;
//        for ( i = 0; i < mycourse.size(); ++i) {
//            for ( j = 0; j < havejiaoxue.size(); ++j)
//                if (mycourse.get(i).getObjectId().equals(havejiaoxue.get(j).getObjectId()) )
//                    break;
//            if (j == havejiaoxue.size())
//                nothave.add(mycourse.get(i));
//        }
//    }
//
//
//
//
//    public void ShowDialog() {
//        Context context = Ordinary.this;
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
//        View layout = inflater.inflate(R.layout.formcommonlist, null);
//        ListView myListView = (ListView) layout.findViewById(R.id.formcustomspinner_list);
//        jiaoxueAdapter adapter = new jiaoxueAdapter(nothave, Ordinary.this);
//        myListView.setAdapter(adapter);
//        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1, int positon, long id) {
//                Intent intent = new Intent(Ordinary.this, zcj_add.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("ke",nothave.get(positon) );
//                intent.putExtras(bundle);
//                startActivity(intent);
//                alertDialog.dismiss();
//            }
//        });
//        builder = new AlertDialog.Builder(context);
//        builder.setView(layout);
//        alertDialog = builder.create();
//        alertDialog.show();
//    }
//    //    自定义适配器
//    class jiaoxueAdapter extends BaseAdapter {
//        private List<TEACH> stuList;
//        private LayoutInflater inflater;
//        public jiaoxueAdapter() {}
//        public jiaoxueAdapter(List<TEACH> stuList, Context context) {
//            this.stuList = stuList;
//            this.inflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public int getCount() {
//            return stuList == null ? 0 : stuList.size();
//        }
//
//        @Override
//        public TEACH getItem(int position) {
//            return stuList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            //加载布局为一个视图
//            View view = inflater.inflate(R.layout.rtu_item, null);
//            TEACH student = getItem(position);
//            //在view视图中查找id为image_photo的控件
//            TextView course_code = (TextView) view.findViewById(R.id.tv_name);
//            course_code.setText(student.getCourse().getDespration());
//            return view;
//        }
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        initDate();
//    }
}
