package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.TCH_calender;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Administrator on 2018/3/24 0024.
 */

public class Calender_Object extends Activity {

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

    String mudi;
    @Bind(R.id.jxmd_detial)
    EditText jxmdDetial;
    String calenderid;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_object);
        ButterKnife.bind(this);
        first();
        initView();
    }
    private void first() {
        mudi = getIntent().getStringExtra("mudi");
        calenderid = getIntent().getStringExtra("calenderid");
    }

    private void initView() {
        title.setText("教学目的及主要要求");
        if (mudi.equals("")){
            save.setVisibility(View.VISIBLE);
            rightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getLoadingDialog().show();
                    TCH_calender calender = new TCH_calender();
                    calender.setObjective(jxmdDetial.getText().toString());
                    calender.update(calenderid, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            getLoadingDialog().dismiss();
                            if (e==null){
                                Toast.makeText(Calender_Object.this, "保存成功", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(Calender_Object.this, "保存失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
            BmobQuery<TCH_calender> ca = new BmobQuery<>();
            ca.addWhereEqualTo("objectId",calenderid);
            ca.findObjects(new FindListener<TCH_calender>() {
                @Override
                public void done(List<TCH_calender> list, BmobException e) {
                    if (e==null) {
                        if (list.size()!=0) {
                            jxmdDetial.setText(list.get(0).getObjective());
                        }
                    }else {
                        Toast.makeText(Calender_Object.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            jxmdDetial.setText(mudi);
        }
    }



    @OnClick(R.id.back1)
    public void onViewClicked() {
        finish();
    }
}




