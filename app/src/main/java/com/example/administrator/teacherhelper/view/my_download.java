package com.example.administrator.teacherhelper.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.Bean.MATERIAL;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Activity.LoginActivity;
import com.example.administrator.teacherhelper.view.Activity.dialog.FlippingLoadingDialog;
import com.example.administrator.teacherhelper.view.Adapter.materialAdapter;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/3/14 0014.
 */

public class my_download extends Activity {


    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.back1)
    RelativeLayout back1;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.add)
    ImageButton add;
    @Bind(R.id.save)
    ImageButton save;
    @Bind(R.id.listt)
    ListView listt;
    private materialAdapter adapter;
    private SimpleAdapter simpleAdapter;
    protected FlippingLoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapteractivity);
        ButterKnife.bind(this);
        Bmob.initialize(this, "ab8ec6ed95c785a2a470225606acee3e");
        init();
        getData();
    }

    private void getData() {

        BmobQuery<MATERIAL> bmobQuery= new BmobQuery<>();
        bmobQuery.order("-createdAt");
        bmobQuery.findObjects(new FindListener<MATERIAL>() {
            @Override
            public void done(final List<MATERIAL> list, BmobException e) {
                if (e == null) {
                    if (list.size()!=0){
                    adapter=new materialAdapter(list,my_download.this);
                    listt.setAdapter(adapter);

                    listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                            new AlertDialog.Builder(my_download.this)
                                    .setTitle("下载")
                                    .setMessage("确定下载此文件？")
                                    .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    })
                                    .setNegativeButton(
                                            "确定",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    getLoadingDialog().setMessage("正在下载...").show();
//                                                    下载
                                                    File saveFile = new File("/sdcard/Teacher/", list.get(position).getMaterial().getFilename());
                                                    list.get(position).getMaterial().download(saveFile, new DownloadFileListener() {
                                                        @Override
                                                        public void done(String s, BmobException e) {
                                                            getLoadingDialog().setMessage("正在下载...").dismiss();
                                                            Toast.makeText(my_download.this, "下载完成，请到文件夹“Teacher”中查看", Toast.LENGTH_SHORT).show();
                                                        }

                                                        @Override
                                                        public void onProgress(Integer integer, long l) {
//                                                            Toast.makeText(my_download.this, "正在下载", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            })
                                    .show();
                        }
                    });
                    }else {
                        Toast.makeText(my_download.this, "没有下载的文件列表", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(my_download.this, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void init() {
        title.setText("文件下载");
    }

    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }
}
