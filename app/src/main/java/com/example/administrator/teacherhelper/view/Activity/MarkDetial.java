package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.MARK;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class MarkDetial extends Activity {

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
    @Bind(R.id.classs)
    TextView classs;
    @Bind(R.id.xibie)
    TextView xibie;
    @Bind(R.id.failure_peoploname)
    TextView failurePeoploname;
    @Bind(R.id.zhuanye)
    TextView zhuanye;
    @Bind(R.id.major)
    TextView major;
    @Bind(R.id.banji)
    TextView banji;
    @Bind(R.id.grade_class)
    TextView gradeClass;
    @Bind(R.id.viewzcj)
    View viewzcj;
    @Bind(R.id.download)
    TextView download;
    File saveFile;
    private MARK teachid;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.markdetial);
        ButterKnife.bind(this);
        first();
        initView();
    }

    private void initView() {
        title.setText("平时成绩详情");
        course.setText(teachid.getTeach().getCourse().getDespration() + "(" + teachid.getTeach().getCourse().getCourse_code() + ")");
        yearSemester.setText(teachid.getTeach().getTeam().getGrade().getDespration() + "级" + teachid.getTeach().getTeam().getMajor().getDespration() + teachid.getTeach().getTeam().getClasss().getDespration() + "班");
        major.setText(teachid.getTeach().getCredit());
        failurePeoploname.setText(teachid.getTeach().getTeacher().getDesperation());
        gradeClass.setText(teachid.getTeach().getCollege().getDespration());
        classs.setText(teachid.getTeach().getNature().getDespration());
    }

    private void first() {
        teachid = (MARK) getIntent().getSerializableExtra("jiaoxueid");
    }

    @OnClick(R.id.download)
    public void onViewClicked() {
        getLoadingDialog().show();
        BmobQuery<MARK> mark1 = new BmobQuery<>();
        mark1.getObject(teachid.getObjectId(), new QueryListener<MARK>() {
            @Override
            public void done(MARK mark, BmobException e) {
                saveFile = new File("/sdcard/Teacher/", teachid.getFile().getFilename());
                mark.getFile().download(saveFile, new DownloadFileListener() {
                    @Override
                    public void done(String s, BmobException e) {
                        getLoadingDialog().dismiss();
                        if (e == null) {
                            if (saveFile.exists()) {
                                String end = saveFile.getName().substring(saveFile.getName().lastIndexOf("."), saveFile.getName().length()).toLowerCase();
                                Uri path = Uri.fromFile(saveFile);
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                if (end.equals(".xls")) {
                                    intent.setDataAndType(path, "application/vnd.ms-excel");
                                } else {
                                    intent.setDataAndType(path, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                                }
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                try {
                                    startActivity(intent);
                                } catch (ActivityNotFoundException e1) {
                                    Toast.makeText(MarkDetial.this,
                                            "No Application Available to View excel",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(MarkDetial.this, "加载失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onProgress(Integer integer, long l) {

                    }
                });
            }
        });
    }
}
