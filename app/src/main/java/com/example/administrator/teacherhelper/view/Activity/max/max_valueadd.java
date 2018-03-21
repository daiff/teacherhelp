package com.example.administrator.teacherhelper.view.Activity.max;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.Bean.FIELD;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.jiemian.PickerView;
import com.example.administrator.teacherhelper.view.Activity.dialog.FlippingLoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class max_valueadd extends Activity {
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
    @Bind(R.id.value_pv)
    PickerView valuePv;
    @Bind(R.id.value_despration)
    EditText valueDespration;
    @Bind(R.id.value_code)
    EditText valueCode;
    @Bind(R.id.value_num)
    EditText valueNum;
    @Bind(R.id.value_one)
    LinearLayout valueOne;
    @Bind(R.id.value_two)
    LinearLayout valueTwo;
    private String valuee;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picker);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("录入域值");
        save.setVisibility(View.VISIBLE);
        List<String> data = new ArrayList<String>();
        data.add(0, "学年");
        data.add(1, "年级");
        data.add(2, "专业");
        data.add(3, "课程");
        data.add(4, "学院");
        valuePv.setData(data);
        valuePv.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                if (text.equals("课程")) {
                    valueOne.setVisibility(View.VISIBLE);
                    valueTwo.setVisibility(View.VISIBLE);
                    valuee = "schedule";
                } else if (text.equals("专业")) {
                    valueOne.setVisibility(View.GONE);
                    valueTwo.setVisibility(View.GONE);
                    valuee = "major";
                } else if (text.equals("学院")) {
                    valueOne.setVisibility(View.GONE);
                    valueTwo.setVisibility(View.GONE);
                    valuee = "college";
                } else if (text.equals("年级")) {
                    valueOne.setVisibility(View.GONE);
                    valueTwo.setVisibility(View.GONE);
                    valuee = "grade";
                } else if (text.equals("学年")) {
                    valueOne.setVisibility(View.GONE);
                    valueTwo.setVisibility(View.GONE);
                    valuee = "schoolyear";
                }
            }
        });
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                getLoadingDialog().show();
                FIELD field = new FIELD();
                field.setValue(valuee);
                field.setDespration(valueDespration.getText().toString());
                field.setCourse_code(valueCode.getText().toString());
                field.setCredit(valueNum.getText().toString());
                field.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        getLoadingDialog().dismiss();
                        if (e == null) {
                            Toast.makeText(max_valueadd.this, "保存成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(max_valueadd.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }

}
