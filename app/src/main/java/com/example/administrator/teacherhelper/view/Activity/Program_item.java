package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.TCH_program;
import com.example.administrator.teacherhelper.bean.TEACH;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.until.AccountUtils;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

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
 * for:教学大纲列表
 */

public class Program_item extends Activity {

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

    List<TEACH> mycourse;
    List<TCH_program> allworksum;
    List<TEACH> nothave;
    List<TCH_program> have;
    String resource;

    protected FlippingLoadingDialog mLoadingDialog;

    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    com.example.administrator.teacherhelper.view.Adapter.Program_item adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapteractivity);
        ButterKnife.bind(this);
        first();
        init();
        initData();

    }

    private void initData() {
        mycourse = new ArrayList<>();
        allworksum = new ArrayList<>();
        nothave = new ArrayList<>();
        have = new ArrayList<>();
        getLoadingDialog().show();
        if (resource.equals(CommenDate.main)) {
            data();
        } else if (resource.equals(CommenDate.max)) {
            allworksum = new ArrayList<>();
            BmobQuery<TCH_program> bmobQuery = new BmobQuery<>();
            bmobQuery.include(CommenDate.include_commen);
            bmobQuery.order("-createdAt");
            bmobQuery.findObjects(new FindListener<TCH_program>() {
                @Override
                public void done(final List<TCH_program> list, BmobException e) {
                    getLoadingDialog().dismiss();
                    if (e == null) {
                        if (list.size() != 0) {
                            adapter = new com.example.administrator.teacherhelper.view.Adapter.Program_item(list, Program_item.this);
                            listt.setAdapter(adapter);
                            listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(Program_item.this, Program_Detial.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("tch_program", list.get(position));
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                        }
                    } else {
                        Toast.makeText(Program_item.this, "获取失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void first() {
        resource = getIntent().getStringExtra("resource");
    }

    private void init() {
        title.setText("教学大纲");
        add.setVisibility(View.VISIBLE);
    }

    private void data() {
        BmobQuery<TEACH> jiaoxueBmobQuery = new BmobQuery<>();
        jiaoxueBmobQuery.addWhereEqualTo("Teacher", BmobUser.getCurrentUser());
        jiaoxueBmobQuery.addWhereEqualTo("Schoolyear", AccountUtils.getyear(Program_item.this));
        jiaoxueBmobQuery.include(CommenDate.include_jiaoxue);
        jiaoxueBmobQuery.order("-createdAt");
        jiaoxueBmobQuery.findObjects(new FindListener<TEACH>() {
            @Override
            public void done(List<TEACH> list, BmobException e) {
                if (e == null) {
                    if (list.size() == 0) {
                        getLoadingDialog().dismiss();
                        Toast.makeText(Program_item.this, "本学期您没有课程", Toast.LENGTH_SHORT).show();
                    } else {
                        mycourse = list;
                        getWorkSum();
                    }
                } else {
                    getLoadingDialog().dismiss();
                    Toast.makeText(Program_item.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getWorkSum() {
        BmobQuery<TCH_program> bmobQuery = new BmobQuery<>();
        bmobQuery.include(CommenDate.include_commen);
        bmobQuery.order("-createdAt");
        bmobQuery.findObjects(new FindListener<TCH_program>() {
            @Override
            public void done(final List<TCH_program> list, BmobException e) {
                if (e == null) {
                    allworksum = list;
                    gethave();
                } else {
                    getLoadingDialog().dismiss();
                    Toast.makeText(Program_item.this, e.toString(), Toast.LENGTH_SHORT).show();
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

    private void getnohave() {
        int i = 0, j = 0;
        for (i = 0; i < mycourse.size(); ++i) {
            for (j = 0; j < allworksum.size(); ++j)
                if (mycourse.get(i).getObjectId().equals(allworksum.get(j).getCourse().getObjectId()))
                    break;
            if (j == allworksum.size())
                nothave.add(mycourse.get(i));
        }
    }

    //    显示已填写的工作总结
    private void showData() {
        getLoadingDialog().dismiss();
        if (have.size() == 0) {
            Toast.makeText(this, "没有任何已填写的教学大纲", Toast.LENGTH_SHORT).show();
        } else {
            adapter = new com.example.administrator.teacherhelper.view.Adapter.Program_item(have, Program_item.this);
            listt.setAdapter(adapter);
            listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(Program_item.this, Program_Detial.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tch_program", have.get(position));
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
                if (nothave.size() == 0) {
                    Toast.makeText(this, "本学期的教学大纲已全部填写", Toast.LENGTH_SHORT).show();
                } else {
                    ShowDialog();
                }
                break;
        }
    }


    public void ShowDialog() {
        Context context = Program_item.this;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.formcommonlist, null);
        ListView myListView = (ListView) layout.findViewById(R.id.formcustomspinner_list);
        jiaoxueAdapter adapter = new jiaoxueAdapter(nothave, Program_item.this);
        myListView.setAdapter(adapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int positon, long id) {
                Intent intent = new Intent(Program_item.this, Program_add.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ke", nothave.get(positon));
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

        public jiaoxueAdapter() {
        }

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
