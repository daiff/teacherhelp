package com.example.administrator.teacherhelper.view.Activity.max;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.STUDENT;
import com.example.administrator.teacherhelper.bean.classs;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.until.FileUtils;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import java.io.FileInputStream;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class max_StudentAdd extends Activity {

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
//    @Bind(R.id.student_num)
//    EditText studentNum;
//    @Bind(R.id.student_name)
//    EditText studentName;
    @Bind(R.id.course_class1)
    TextView courseClass1;
    @Bind(R.id.select_course1_sc)
    ImageView selectCourse1Sc;
    String classid4;
    private static final int FILE_SELECT_CODE = 0;
    String path;


    @Bind(R.id.upload_path)
    TextView uploadPath;
    @Bind(R.id.upload_file)
    ImageView uploadFile;
    @Bind(R.id.daoru)
    Button daoru;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_add);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        title.setText("录入学生");
        save.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.back1, R.id.upload_file, R.id.select_course1_sc,R.id.daoru})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.upload_file:
                showFileChooser();
                break;
            case R.id.select_course1_sc:
                Intent intent14 = new Intent(max_StudentAdd.this, max_class.class);
                intent14.putExtra("select", CommenDate.maxcour_class);
                startActivityForResult(intent14, CommenDate.select_class4);
                break;
            case R.id.daoru:
                readExcelToDB();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CommenDate.select_class4) {
            classid4 = data.getStringExtra("majorid");
            String majordesc = data.getStringExtra("majordesc");
            courseClass1.setText(majordesc);
        }
        if (requestCode == FILE_SELECT_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                path = FileUtils.getpath(max_StudentAdd.this, uri);
                uploadPath.setText(path);
            }
        }
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

    private void readExcelToDB() {
        classs cla = new classs();
        cla.setObjectId(classid4);
        try {
            InputStream is = new FileInputStream(path);
            Workbook book = Workbook.getWorkbook(is);
            book.getNumberOfSheets();
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int Rows = sheet.getRows();
            STUDENT info = new STUDENT();
            for (int i = 1; i < Rows; ++i) {
                String content = (sheet.getCell(1, i)).getContents();
                String phonetic = (sheet.getCell(2, i)).getContents();
                info.setDespration(phonetic);
                info.setNumber(content);
                info.setClasss(cla);
                info.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        getLoadingDialog().dismiss();
                        if (e==null){
                            Toast.makeText(max_StudentAdd.this, "成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(max_StudentAdd.this, "失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
