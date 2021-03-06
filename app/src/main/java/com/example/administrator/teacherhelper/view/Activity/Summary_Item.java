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

import com.example.administrator.teacherhelper.bean.TCH_worksum;
import com.example.administrator.teacherhelper.bean.TEACH;
import com.example.administrator.teacherhelper.bean.jiaoxue;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.until.AccountUtils;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;
import com.example.administrator.teacherhelper.view.Adapter.Summary;

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

/**
 * Created by daiff on 2018/1/29.
 * for:
 */

public class Summary_Item extends Activity {
    private final static String TAG= "Summary_Item";

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
    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    List<TEACH> mycourse;
    List<TCH_worksum> allworksum ;
    List<TEACH> nothave;
    List<TCH_worksum> have;
    Summary adapter;
    String resource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapteractivity);
        ButterKnife.bind(this);
        Bmob.initialize(this, "ab8ec6ed95c785a2a470225606acee3e");
        first();
        initView();
        initData();
    }

    private void first() {
        resource = getIntent().getStringExtra("resource");
    }

    protected void initView() {
        title.setText("教学工作总结");
        add.setVisibility(View.VISIBLE);
    }


    protected void initData() {
        getLoadingDialog().show();
       mycourse = new ArrayList<>();
        allworksum = new ArrayList<>();
       nothave = new ArrayList<>();
        have = new ArrayList<>();
        if (resource.equals(CommenDate.main)) {
            getmycourse();
        }else if (resource.equals(CommenDate.max)){
            BmobQuery<TCH_worksum> worksumbmob = new BmobQuery<>();
            worksumbmob.include(CommenDate.include_summ);
            worksumbmob.order("-createdAt");
            worksumbmob.findObjects(new FindListener<TCH_worksum>() {
                @Override
                public void done(final List<TCH_worksum> list, BmobException e) {
                    getLoadingDialog().dismiss();
                    if (e==null){
                        adapter = new Summary(list, Summary_Item.this);
                        listt.setAdapter(adapter);
                        listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(Summary_Item.this, Summary_Detial.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("tch_worksum", list.get(position));
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }else {
                        Toast.makeText(Summary_Item.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
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
                    Toast.makeText(this, "本学期的工作总结已全部填写", Toast.LENGTH_SHORT).show();
                }else {
                    ShowDialog();
                }
                break;
        }
    }
//本教师本学期的所有课程
    private void getmycourse(){
        BmobQuery<TEACH> jiaoxueBmobQuery =new BmobQuery<>();
        jiaoxueBmobQuery.addWhereEqualTo("Teacher",BmobUser.getCurrentUser());
        jiaoxueBmobQuery.addWhereEqualTo("Schoolyear", AccountUtils.getyear(Summary_Item.this));
        jiaoxueBmobQuery.include(CommenDate.include_jiaoxue);
        jiaoxueBmobQuery.order("-createdAt");
        jiaoxueBmobQuery.findObjects(new FindListener<TEACH>() {
            @Override
            public void done(List<TEACH> list, BmobException e) {
                if (e==null){
                    if (list.size() == 0){
                        getLoadingDialog().dismiss();
                        Toast.makeText(Summary_Item.this, "本学期您没有课程", Toast.LENGTH_SHORT).show();
                    }else{
                        mycourse = list;
                        getWorkSum();
                        Log.i(TAG, "done: ");
                    }
                }else {
                    getLoadingDialog().dismiss();
                    Toast.makeText(Summary_Item.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//工作总结表的全部内容
    private void getWorkSum() {
        BmobQuery<TCH_worksum> worksumbmob = new BmobQuery<>();
        worksumbmob.include(CommenDate.include_summ);
        worksumbmob.order("-createdAt");
        worksumbmob.findObjects(new FindListener<TCH_worksum>() {
            @Override
            public void done(List<TCH_worksum> list, BmobException e) {
                if (e==null){
                    allworksum = list;
                    Log.i(TAG, "done: ");
                    gethave();
                }else {
                    getLoadingDialog().dismiss();
                    Toast.makeText(Summary_Item.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//    得到已填写的工作总结

    private void gethave() {
        for (int i = 0; i < mycourse.size(); i++) {
            for (int j = 0; j < allworksum.size(); j++) {
                if (mycourse.get(i).getObjectId().equals(allworksum.get(j).getTeach().getObjectId())) {
                    have.add(allworksum.get(j));
                    Log.i(TAG, "classification: ");
                }
            }
        }
        getnohave();
        showData();
    }
//    得到未填写的工作总结
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
            Toast.makeText(this, "没有任何已填写的工作总结表", Toast.LENGTH_SHORT).show();
        }else {
            adapter = new Summary(have, Summary_Item.this);
            listt.setAdapter(adapter);
            listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(Summary_Item.this, Summary_Detial.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tch_worksum", have.get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
    }

//    显示须填写的工作总结列表
    public void ShowDialog() {
        Context context = Summary_Item.this;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.formcommonlist, null);
        ListView myListView = (ListView) layout.findViewById(R.id.formcustomspinner_list);
        jiaoxueAdapter adapter = new jiaoxueAdapter(nothave, Summary_Item.this);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int positon, long id) {
                Intent intent = new Intent(Summary_Item.this, Summary_Add.class);
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
