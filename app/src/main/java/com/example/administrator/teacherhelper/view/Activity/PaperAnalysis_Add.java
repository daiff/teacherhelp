package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.bean.TCH_analysis;
import com.example.administrator.teacherhelper.bean.classs;
import com.example.administrator.teacherhelper.bean.jiaoxue;
import com.example.administrator.teacherhelper.R;
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

/**
 * Created by Administrator on 2018/3/17 0017.
 */

public class PaperAnalysis_Add extends Activity {
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
    @Bind(R.id.kaikeyuan)
    TextView kaikeyuan;
    @Bind(R.id.shjfx_classs)
    TextView shjfxClasss;
    @Bind(R.id.fsh_9)
    EditText fsh9;
    @Bind(R.id.fsh_8)
    EditText fsh8;
    @Bind(R.id.fsh_7)
    EditText fsh7;
    @Bind(R.id.fsh_6)
    EditText fsh6;
    @Bind(R.id.fsh_0)
    EditText fsh0;
    @Bind(R.id.total_people)
    EditText totalPeople;
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.high_fen)
    EditText highFen;
    @Bind(R.id.low_fen)
    EditText lowFen;
    @Bind(R.id.prop_analysis)
    EditText propAnalysis;
    jiaoxue course1;
    @Bind(R.id.bl_9)
    EditText bl9;
    @Bind(R.id.bl_8)
    EditText bl8;
    @Bind(R.id.bl_7)
    EditText bl7;
    @Bind(R.id.bl_6)
    EditText bl6;
    @Bind(R.id.bl)
    EditText bl;
    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }
    StringBuilder str = new StringBuilder();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paperanalysis_add);
        ButterKnife.bind(this);
        first();
        init();
        getclass();
    }

    private void getclass() {
        BmobQuery<classs> bjiaoxue = new BmobQuery<>();
        bjiaoxue.include("classs,college,grade,major");

        jiaoxue analysis = new jiaoxue();
        analysis.setObjectId(course1.getObjectId());
        bjiaoxue.addWhereRelatedTo("Team",new BmobPointer(analysis));
        bjiaoxue.findObjects(new FindListener<classs>() {
            @Override
            public void done(List<classs> list, BmobException e) {
                if (e==null){
                    for (int i =0;i<list.size();i++){
                        str.append(list.get(i).getCollege().getDespration()+list.get(i).getGrade().getDespration()+list.get(i).getMajor().getDespration()+list.get(i).getClasss().getDespration()+ "班  ");
                    }
                    shjfxClasss.setText(str);
                }

            }
        });

    }

    private void first() {
        course1 = (jiaoxue) getIntent().getSerializableExtra("ke");
    }

    private void init() {
        title.setText("新增试卷分析表");
        save.setVisibility(View.VISIBLE);
        course.setText(course1.getKe().getCourse_code() + " " + course1.getKe().getDespration() + " " + course1.getNature().getDespration());
        yearSemester.setText(course1.getSchoolyear().getDespration());
        kaikeyuan.setText("开课院：" + course1.getKaikeyuan().getDespration());
//        shjfxClasss.setText(course1.getClasss().getGrade().getDespration() + "级 " + course1.getClasss().getMajor().getDespration() + " " + course1.getClasss().getClasss().getDespration() + "班");
    }

    @OnClick({R.id.back1, R.id.save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.save:
                getLoadingDialog().setMessage("").show();
                TCH_analysis tch_analysis = new TCH_analysis();
                tch_analysis.setCom_analysis(propAnalysis.getText().toString());
                tch_analysis.setZhurdata("");
                tch_analysis.setZero_prop(bl.getText().toString());
                tch_analysis.setZero_num(fsh0.getText().toString());
                tch_analysis.setTatalpeoplenum(totalPeople.getText().toString());
                tch_analysis.setSix_prop(bl6.getText().toString());
                tch_analysis.setSix_num(fsh6.getText().toString());
                tch_analysis.setSeven_prop(bl7.getText().toString());
                tch_analysis.setSeven_num(fsh7.getText().toString());
                tch_analysis.setNine_num(fsh9.getText().toString());
                tch_analysis.setNine_prop(bl9.getText().toString());
                tch_analysis.setMinimum_score(lowFen.getText().toString());
                tch_analysis.setClasss(shjfxClasss.getText().toString());
                tch_analysis.setJshqianmin("");
                tch_analysis.setJshdata("");
                tch_analysis.setJiaoxue(course1);
                tch_analysis.setHighest_score(highFen.getText().toString());
                tch_analysis.setEight_num(fsh8.getText().toString());
                tch_analysis.setEight_prop(bl8.getText().toString());
                tch_analysis.setZhurqianmin("");
                tch_analysis.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        getLoadingDialog().setMessage("").dismiss();
                        if (e==null){
                            Toast.makeText(PaperAnalysis_Add.this, "保存成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(PaperAnalysis_Add.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                break;
        }
    }
}
