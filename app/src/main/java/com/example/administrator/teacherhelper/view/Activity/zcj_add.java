package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.ACHIEVEMENT;
import com.example.administrator.teacherhelper.bean.STUDENT;
import com.example.administrator.teacherhelper.bean.TEACH;
import com.example.administrator.teacherhelper.bean.jiaoxue;
import com.example.administrator.teacherhelper.view.Adapter.zcj_studentAdapter;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by Administrator on 2018/3/22 0022.
 */

public class zcj_add extends Activity {

    private TEACH teach;
    zcj_studentAdapter adapter;
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
    @Bind(R.id.upload_path)
    TextView uploadPath;
    @Bind(R.id.upload_file)
    ImageView uploadFile;
    @Bind(R.id.upload)
    Button upload;

    private static final int FILE_SELECT_CODE = 0;
    String path;
    protected FlippingLoadingDialog mLoadingDialog;

    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zcj_detail);
        ButterKnife.bind(this);
        first();
        initView();
    }

    private void initView() {
        title.setText("新增总成绩表");
        save.setVisibility(View.VISIBLE);
        course.setText(teach.getCourse().getDespration() + teach.getCourse().getCourse_code());
        yearSemester.setText(teach.getSchoolyear().getDespration());
        failurePeoploname.setText(teach.getTeacher().getDesperation());
        major.setText(teach.getCredit());
        gradeClass.setText(teach.getCollege().getDespration());
        classs.setText(teach.getTeam().getGrade().getDespration()+"级"+teach.getTeam().getMajor().getDespration()+teach.getTeam().getClasss().getDespration()+"班");
    }


    private void first() {
        teach = (TEACH) getIntent().getSerializableExtra("ke");
    }

    @OnClick({R.id.back1, R.id.upload_file, R.id.upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.upload_file:
                showFileChooser();
                break;
            case R.id.upload:
                getLoadingDialog().show();
                wenjianShchuan();
                break;
        }
    }

    private void wenjianShchuan() {
        File file1 = new File(path);
        final BmobFile icon = new BmobFile(file1);
        icon.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                ACHIEVEMENT material = new ACHIEVEMENT();
                material.setFile(icon);
                material.setTeach(teach);
                material.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        getLoadingDialog().dismiss();
                        if (e == null) {
                            Toast.makeText(zcj_add.this, "上传成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(zcj_add.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");//过滤文件类型（所有）
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "请选择文件！"), FILE_SELECT_CODE);
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(this, "未安装文件管理器！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    path = FileUtils.getPath(this, uri);//得到文件路径
                    uploadPath.setText(path);
                }
                break;
        }
    }

    /**
     * 文件工具类
     *
     * @author ql
     */
    static class FileUtils {
        public static String getPath(Context context, Uri uri) {

            if ("content".equalsIgnoreCase(uri.getScheme())) {
                String[] projection = {"_data"};
                Cursor cursor = null;

                try {
                    cursor = context.getContentResolver().query(uri, projection, null, null, null);
                    int column_index = cursor.getColumnIndexOrThrow("_data");
                    if (cursor.moveToFirst()) {
                        return cursor.getString(column_index);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
            return null;
        }
    }
}
