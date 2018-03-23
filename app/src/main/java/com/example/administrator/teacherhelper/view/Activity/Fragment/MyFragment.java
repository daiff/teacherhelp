package com.example.administrator.teacherhelper.view.Activity.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.until.AccountUtils;
import com.example.administrator.teacherhelper.view.Activity.LoginActivity;
import com.example.administrator.teacherhelper.view.Activity.dialog.FlippingLoadingDialog;
import com.example.administrator.teacherhelper.view.Activity.my_course;
import com.example.administrator.teacherhelper.view.Activity.my_detail;
import com.example.administrator.teacherhelper.view.my_download;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by daiff on 2017/12/26.
 * for:
 */

public class MyFragment extends Fragment {
    @Bind(R.id.back)
    ImageButton back;
    @Bind(R.id.back1)
    RelativeLayout back1;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.add)
    ImageButton add;
    @Bind(R.id.mydetail)
    LinearLayout mydetail;
    @Bind(R.id.downoaddata)
    TextView downoaddata;
    @Bind(R.id.mycourse)
    TextView mycourse;
    @Bind(R.id.resetpassword)
    TextView resetpassword;
    @Bind(R.id.exit)
    TextView exit;
    @Bind(R.id.usernamaedes)
    TextView usernamaedes;
    @Bind(R.id.username)
    TextView username;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        ButterKnife.bind(this, view);
        Bmob.initialize(getActivity(), "ab8ec6ed95c785a2a470225606acee3e");
        initview();
        return view;
    }

    private void initview() {
        back.setVisibility(View.GONE);
        title.setText("我的");
        username.setText(AccountUtils.getUserid(getActivity()));
        usernamaedes.setText(AccountUtils.getUsername(getActivity()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }

    @OnClick({R.id.mydetail, R.id.downoaddata, R.id.mycourse, R.id.resetpassword, R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mydetail:
                Intent intent = new Intent(getActivity(),my_detail.class);
                startActivity(intent);
                break;
            case R.id.downoaddata:
                Intent intent2 = new Intent(getActivity(),my_download.class);
                startActivity(intent2);
                break;
            case R.id.mycourse:
                Intent intent1 = new Intent(getActivity(),my_course.class);
                intent1.putExtra("sourse","");
                startActivity(intent1);
                break;
            case R.id.resetpassword:
                getLoadingDialog().setMessage("...").show();
                String email = BmobUser.getCurrentUser().getEmail();
                BmobUser.resetPasswordByEmail(email, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        getLoadingDialog().setMessage("").dismiss();
                        if (e==null){
                            Toast.makeText(getActivity(),"您会收到一封邮件，请到邮箱查看",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.exit:
                Intent logoutIntent = new Intent(getActivity(), LoginActivity.class);
                logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(logoutIntent);
                break;
        }
    }
    protected FlippingLoadingDialog mLoadingDialog;
    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(getActivity());
        return mLoadingDialog;
    }
}
