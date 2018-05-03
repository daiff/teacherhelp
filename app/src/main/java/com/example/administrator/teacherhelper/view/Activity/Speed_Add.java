package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.TCH_Progress;
import com.example.administrator.teacherhelper.bean.TCH_pro;
import com.example.administrator.teacherhelper.bean.TEACH;
import com.example.administrator.teacherhelper.bean.classs;
import com.example.administrator.teacherhelper.bean.jiaoxue;
import com.example.administrator.teacherhelper.view.Adapter.SpeedContent;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2018/3/25 0025.
 */

public class Speed_Add extends Activity {


    TEACH jiao;
    StringBuilder str = new StringBuilder();
    String newproid = "";
    SpeedContent adapter;
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
    @Bind(R.id.course)
    TextView course;
    @Bind(R.id.year_semester)
    TextView yearSemester;
    @Bind(R.id.jxjd_fzr)
    TextView jxjdFzr;
    @Bind(R.id.major)
    TextView major;
    @Bind(R.id.listt)
    ListView listt;
    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speed_detial);
        ButterKnife.bind(this);
        first();
        initView();
        find();
    }
//根据传过来的pro找到对应的详情
    private void find() {
        BmobQuery<TCH_Progress> bjiaoxue = new BmobQuery<>();
        bjiaoxue.addWhereEqualTo("TCH_pro", newproid);
        bjiaoxue.order("+weekly");
        bjiaoxue.findObjects(new FindListener<TCH_Progress>() {
            @Override
            public void done(final List<TCH_Progress> list, BmobException e) {
                if (e == null) {
                    if (list.size() != 0) {
                        adapter = new SpeedContent(list, Speed_Add.this);
                        listt.setAdapter(adapter);
                        listt.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                                new AlertDialog.Builder(Speed_Add.this)
                                        .setTitle("提示")
                                        .setMessage("确认删除？")
                                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        })
                                        .setNegativeButton(
                                                "确认",
                                                new DialogInterface.OnClickListener() {

                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        TCH_Progress pro=new TCH_Progress();
                                                        pro.setObjectId(list.get(position).getObjectId());
                                                        pro.delete(new UpdateListener() {
                                                            @Override
                                                            public void done(BmobException e) {
                                                                if (e==null){
                                                                    Toast.makeText(Speed_Add.this, "删除成功", Toast.LENGTH_SHORT).show();
                                                                    onRestart();
                                                                }else {
                                                                    Toast.makeText(Speed_Add.this, "删除失败", Toast.LENGTH_SHORT).show();
                                                                }

                                                            }
                                                        });
                                                    }
                                                })
                                        .show();
                                return true;
                            }
                        });
                    }

                } else {
                    Toast.makeText(Speed_Add.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void initView() {
        title.setText("新增教学进度");
        add.setVisibility(View.VISIBLE);
        course.setText(jiao.getCourse().getDespration());
        yearSemester.setText(jiao.getSchoolyear().getDespration());
        jxjdFzr.setText(jiao.getTeacher().getDesperation());
        major.setText(jiao.getTeam().getGrade().getDespration()+jiao.getTeam().getMajor().getDespration()+jiao.getTeam().getClasss().getDespration()+"班");
    }

    private void first() {
        jiao = (TEACH) getIntent().getSerializableExtra("ke");
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                getLoadingDialog().show();
                if (newproid == "") {
                    save();
                } else {
                    Intent intent = new Intent(Speed_Add.this, Speed_AddItem.class);
                    intent.putExtra("proid", newproid);
                    startActivity(intent);
                }
                break;
        }
    }

    private void save() {
        TCH_pro pro = new TCH_pro();
        pro.setCourse(jiao);
        pro.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                getLoadingDialog().dismiss();
                if (e == null) {
                    newproid = s;
                    Intent intent = new Intent(Speed_Add.this, Speed_AddItem.class);
                    intent.putExtra("proid", s);
                    startActivity(intent);
                } else {
                    Toast.makeText(Speed_Add.this, "失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        find();
    }
}
