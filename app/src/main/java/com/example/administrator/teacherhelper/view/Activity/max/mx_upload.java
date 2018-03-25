package com.example.administrator.teacherhelper.view.Activity.max;

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

import com.example.administrator.teacherhelper.bean.MATERIAL;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by Administrator on 2018/3/18 0018.
 */

public class mx_upload extends Activity {

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
    @Bind(R.id.upload_file)
    ImageView uploadFile;
    @Bind(R.id.upload)
    Button upload;
    private static final int FILE_SELECT_CODE = 0;
    String path;
    @Bind(R.id.upload_path)
    TextView uploadPath;
    protected FlippingLoadingDialog mLoadingDialog;
    private String fileurl;

    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mx_upload);
        ButterKnife.bind(this);
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
                MATERIAL material = new MATERIAL();
                material.setMaterial(icon);
                material.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        getLoadingDialog().dismiss();
                        if (e==null){
                            Toast.makeText(mx_upload.this, "上传成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(mx_upload.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
    };
//    private void savetomaterial(){
//        MATERIAL material = new MATERIAL();
//        material.setMaterial(fileurl);
//
//    }

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
