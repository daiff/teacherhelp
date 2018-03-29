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
import com.example.administrator.teacherhelper.bean.TCA_detail;
import com.example.administrator.teacherhelper.bean.TCH_calender;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/3/27 0027.
 */

public class Calender_ContentAdd extends Activity {

    String calenderid;
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
    @Bind(R.id.April)
    EditText April;
    @Bind(R.id.Weekly)
    EditText Weekly;
    @Bind(R.id.hour)
    EditText hour;
    @Bind(R.id.Content)
    EditText Content;
    @Bind(R.id.self_hour)
    EditText selfHour;
    @Bind(R.id.homwork_hour)
    EditText homworkHour;
    @Bind(R.id.Remarks)
    EditText Remarks;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_contentadd);
        ButterKnife.bind(this);
        first();
        initView();
    }

    private void initView() {
        save.setVisibility(View.VISIBLE);
        title.setText("新增教学日历子项");
    }

    private void first() {
        calenderid = getIntent().getStringExtra("calenderid");
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                getLoadingDialog().show();
                TCA_detail detial = new TCA_detail();
                TCH_calender  ca = new TCH_calender();
                ca.setObjectId(calenderid);
                detial.setTch_calender(ca);
                detial.setApril(April.getText().toString());
                detial.setContent(Content.getText().toString());
                detial.setHomwork_hour(homworkHour.getText().toString());
                detial.setWeekly(Weekly.getText().toString());
                detial.setHour(hour.getText().toString());
                detial.setSelf_hour(selfHour.getText().toString());
                detial.setRemarks(Remarks.getText().toString());
                detial.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        getLoadingDialog().dismiss();
                        if (e==null){
                            Toast.makeText(Calender_ContentAdd.this, "保存成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Calender_ContentAdd.this, "保存失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
