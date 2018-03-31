package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.bean.Innovate;
import com.example.administrator.teacherhelper.bean.STUDENT;
import com.example.administrator.teacherhelper.bean.person;
import com.example.administrator.teacherhelper.commen.CommenDate;
import com.example.administrator.teacherhelper.view.Activity.max.Max_StudentDetial;
import com.example.administrator.teacherhelper.view.enclosure.FlippingLoadingDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2018/3/31 0031.
 */

public class Innovate_Add extends Activity {
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
    @Bind(R.id.innovate_title)
    EditText innovateTitle;
    @Bind(R.id.innovate_content)
    EditText innovateContent;
    @Bind(R.id.course_class1)
    TextView courseClass1;
    @Bind(R.id.select_innovate1)
    ImageView selectInnovate1;
    @Bind(R.id.course_class2)
    TextView courseClass2;
    @Bind(R.id.select_innovate2)
    ImageView selectInnovate2;
    @Bind(R.id.course_class3)
    TextView courseClass3;
    @Bind(R.id.select_innovate3)
    ImageView selectInnovate3;
    @Bind(R.id.course_class4)
    TextView courseClass4;
    @Bind(R.id.select_innovate4)
    ImageView selectInnovate4;
    @Bind(R.id.course_class5)
    TextView courseClass5;
    @Bind(R.id.select_innovate5)
    ImageView selectInnovate5;

    String student1;
    String student2;
    String student3;
    String student4;
    String student5;

    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.innovate_add);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        title.setText("新增大创项目");
        save.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.back1, R.id.right_button, R.id.select_innovate1, R.id.select_innovate2, R.id.select_innovate3, R.id.select_innovate4, R.id.select_innovate5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back1:
                finish();
                break;
            case R.id.right_button:
                getLoadingDialog().show();
                save();
                break;
            case R.id.select_innovate1:
                Intent intent1 = new Intent(Innovate_Add.this,Max_StudentDetial.class);
                intent1.putExtra("classid","");
                startActivityForResult(intent1, CommenDate.select_student1);
                break;
            case R.id.select_innovate2:
                Intent intent2 = new Intent(Innovate_Add.this,Max_StudentDetial.class);
                intent2.putExtra("classid","");
                startActivityForResult(intent2, CommenDate.select_student2);
                break;
            case R.id.select_innovate3:
                Intent intent3 = new Intent(Innovate_Add.this,Max_StudentDetial.class);
                intent3.putExtra("classid","");
                startActivityForResult(intent3, CommenDate.select_student3);
                break;
            case R.id.select_innovate4:
                Intent intent4 = new Intent(Innovate_Add.this,Max_StudentDetial.class);
                intent4.putExtra("classid","");
                startActivityForResult(intent4, CommenDate.select_student4);
                break;
            case R.id.select_innovate5:
                Intent intent5 = new Intent(Innovate_Add.this,Max_StudentDetial.class);
                intent5.putExtra("classid","");
                startActivityForResult(intent5, CommenDate.select_student5);
                break;
        }
    }

    private void save() {
        STUDENT st1= new STUDENT();
        st1.setObjectId(student1);

        STUDENT st2= new STUDENT();
        st2.setObjectId(student2);

        STUDENT st3= new STUDENT();
        st3.setObjectId(student3);

        STUDENT st4= new STUDENT();
        st4.setObjectId(student4);

        STUDENT st5= new STUDENT();
        st5.setObjectId(student5);

        Innovate innovate = new Innovate();
        innovate.setContent(innovateContent.getText().toString());
        innovate.setTitle(innovateTitle.getText().toString());

        person user = new person();
        user.setObjectId(BmobUser.getCurrentUser().getObjectId());
        innovate.setTeacher(user);

        BmobRelation relation = new BmobRelation();
        relation.add(st1);
        relation.add(st2);
        relation.add(st3);
        relation.add(st4);
        relation.add(st5);
        innovate.setPerson(relation);
        innovate.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                getLoadingDialog().dismiss();
                if (e==null){
                    Toast.makeText(Innovate_Add.this, "保存成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Innovate_Add.this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CommenDate.select_student1){
            String desc = data.getStringExtra("desc");
            student1 = data.getStringExtra("id");
            courseClass1.setText(desc);
        }else if (requestCode==CommenDate.select_student2){
            String desc = data.getStringExtra("desc");
            student2 = data.getStringExtra("id");
            courseClass2.setText(desc);
        }else if (requestCode==CommenDate.select_student3){
            String desc = data.getStringExtra("desc");
            student3 = data.getStringExtra("id");
            courseClass3.setText(desc);
        }else if (requestCode==CommenDate.select_student4){
            String desc = data.getStringExtra("desc");
            student4 = data.getStringExtra("id");
            courseClass4.setText(desc);
        }else if (requestCode==CommenDate.select_student5){
            String desc = data.getStringExtra("desc");
            student5 = data.getStringExtra("id");
            courseClass5.setText(desc);
        }
    }
}
