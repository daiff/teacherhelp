package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.Bean.TCH_worksum;
import com.example.administrator.teacherhelper.Bean.book;
import com.example.administrator.teacherhelper.Bean.jiaoxue;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.Activity.dialog.FlippingLoadingDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/3/19 0019.
 */

public class gzzj_add extends Activity {

    protected FlippingLoadingDialog mLoadingDialog;

    jiaoxue teach = new jiaoxue();
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
    @Bind(R.id.gzzj_semester)
    TextView gzzjSemester;
    @Bind(R.id.gzzj_class)
    TextView gzzjClass;
    @Bind(R.id.gzzj_teacher)
    TextView gzzjTeacher;
    @Bind(R.id.gzzj_title)
    TextView gzzjTitle;
    @Bind(R.id.gzzj_major)
    TextView gzzjMajor;
    @Bind(R.id.gzzj_personnum)
    EditText gzzjPersonnum;
    @Bind(R.id.gzzj_hour)
    EditText gzzjHour;
    @Bind(R.id.gzzj_book)
    EditText gzzjBook;
    @Bind(R.id.gzzj_kaizhan)
    EditText gzzjKaizhan;
    @Bind(R.id.gzzj_wanch)
    EditText gzzjWanch;
    @Bind(R.id.gzzj_pigai)
    EditText gzzjPigai;
    @Bind(R.id.gzzj_xueshxx)
    EditText gzzjXueshxx;
    @Bind(R.id.gzzj_pschj)
    EditText gzzjPschj;
    @Bind(R.id.gzzj_czjn)
    EditText gzzjCzjn;
    @Bind(R.id.gzzj_shj)
    EditText gzzjShj;
    @Bind(R.id.gzzj_pjfen)
    EditText gzzjPjfen;
    @Bind(R.id.gzzj_high)
    EditText gzzjHigh;
    @Bind(R.id.gzzj_low)
    EditText gzzjLow;
    @Bind(R.id.gzzj_bjg)
    EditText gzzjBjg;
    @Bind(R.id.gzzj_blbjg)
    EditText gzzjBlbjg;
    @Bind(R.id.gzzj_shifen)
    EditText gzzjShifen;
    @Bind(R.id.gzzj_problem)
    EditText gzzjProblem;

    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gzzj_add);
        ButterKnife.bind(this);
        Bmob.initialize(this, "ab8ec6ed95c785a2a470225606acee3e");
        first();
        initView();
    }

    private void first() {
        teach = (jiaoxue) getIntent().getSerializableExtra("ke");
    }

    private void initView() {
        title.setText("新增工作总结表");
        add.setVisibility(View.VISIBLE);
        gzzjSemester.setText(teach.getSchoolyear().getDespration());
        gzzjClass.setText(teach.getGrade().getDespration() + "级" + teach.getMajor().getDespration() + teach.getClasss().getDespration() + "班");
        gzzjTeacher.setText(teach.getTeacher().getDesperation());
        gzzjTitle.setText(teach.getTeacher().getTitle());
        gzzjMajor.setText(teach.getTeacher().getXi().getDespration());
        gzzjPersonnum.setText(teach.getPersonnum());
        gzzjBook.setText(teach.getBook().getDespration());
    }

    @OnClick({R.id.back1, R.id.right_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                getLoadingDialog().show();
                TCH_worksum tchworksum = new TCH_worksum();
                tchworksum.setLilun_hour(gzzjHour.getText().toString());
                tchworksum.setTeach(teach);
                tchworksum.setWork_kaizhan(gzzjKaizhan.getText().toString());
                tchworksum.setWork_wanch(gzzjWanch.getText().toString());
                tchworksum.setPigai(gzzjPigai.getText().toString());
                tchworksum.setLearn(gzzjXueshxx.getText().toString());
                tchworksum.setPshi(gzzjPschj.getText().toString());
                tchworksum.setJineng(gzzjCzjn.getText().toString());
                tchworksum.setSjpeople(gzzjShj.getText().toString());
                tchworksum.setPinjun(gzzjPjfen.getText().toString());
                tchworksum.setHigh_fen(gzzjHigh.getText().toString());
                tchworksum.setBjgpeople(gzzjBjg.getText().toString());
                tchworksum.setLow_fen(gzzjLow.getText().toString());
                tchworksum.setBjglv(gzzjBlbjg.getText().toString());
                tchworksum.setShifen(gzzjShifen.getText().toString());
                tchworksum.setProblem(gzzjProblem.getText().toString());
                tchworksum.setLilun_hour(gzzjHour.getText().toString());
                tchworksum.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        getLoadingDialog().dismiss();
                        if (e == null) {
                            Toast.makeText(gzzj_add.this, "保存成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(gzzj_add.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;


        }
    }


}
