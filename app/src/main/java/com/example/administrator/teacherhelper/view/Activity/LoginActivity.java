package com.example.administrator.teacherhelper.view.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.administrator.teacherhelper.AppManager;
import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.until.AccountUtils;
import com.example.administrator.teacherhelper.view.Activity.dialog.FlippingLoadingDialog;
import com.example.administrator.teacherhelper.view.Activity.switchButtopn.MySwitchButton;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by Administrator on 2017/12/18.
 */

public class LoginActivity extends BaseActivity {
    private static final String TAG = "LoginActivity";
    private MySwitchButton mySwitchButton;
    private EditText mUser;
    private EditText mPassword;
    private LinearLayout login;
    private long exitTime = 0;
    protected FlippingLoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginn);
        Bmob.initialize(this, "ab8ec6ed95c785a2a470225606acee3e");

        //蒲公英版本更新
        updata();
        findViewById();
        initView();
    }


    @Override
    protected void findViewById() {

        mUser = (EditText)findViewById(R.id.user);
        mPassword = (EditText)findViewById(R.id.password);
        login = (LinearLayout) findViewById(R.id.login);

        //登录按钮
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mUser.getText().length() == 0) {
                    mUser.setError(getString(R.string.login_error_empty_user));
                    mUser.requestFocus();
                } else if (mPassword.getText().length() == 0) {
                    mPassword.setError(getString(R.string.login_error_empty_passwd));
                    mPassword.requestFocus();
                }else {
                    login();
                }
            }
        });

    }

    @Override
    protected void initView() {
        if (AccountUtils.getUserid(LoginActivity.this)!=null){
            mUser.setText(AccountUtils.getUserid(LoginActivity.this));
            mPassword.setText(AccountUtils.getUserPassword(LoginActivity.this));
        }
    }

    /**
     * 登陆*
     */
    private void login() {

        getLoadingDialog().setMessage("正在登录...").show();
        final String user_num = mUser.getText().toString();
        final String user_password = mPassword.getText().toString().trim();
        BmobUser bmobUser = new BmobUser();
        bmobUser.setUsername(user_num);
        bmobUser.setPassword(user_password);
        bmobUser.login(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                getLoadingDialog().setMessage("正在登录...").dismiss();
                if(e==null){
                    AccountUtils.setUserid(LoginActivity.this,user_num);
                    AccountUtils.setUserPassword(LoginActivity.this,user_password);
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent_main = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent_main);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();

                }
            }
        });
        }




    private FlippingLoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new FlippingLoadingDialog(this);
        return mLoadingDialog;
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_LONG).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.AppExit(LoginActivity.this);
        }
    }


    private  void updata(){
        PgyUpdateManager.register(LoginActivity.this,null,
                new UpdateManagerListener() {

                    @Override
                    public void onUpdateAvailable(final String result) {

                        // 将新版本信息封装到AppBean中
                        final AppBean appBean = getAppBeanFromString(result);
                        new AlertDialog.Builder(LoginActivity.this)
                                .setTitle("更新")
                                .setMessage("新版本提示")
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
                                            public void onClick(
                                                    DialogInterface dialog,
                                                    int which) {
                                                startDownloadTask(
                                                        LoginActivity.this,
                                                        appBean.getDownloadURL());
                                            }
                                        })
                                .show();
                    }

                    @Override
                    public void onNoUpdateAvailable() {
                    }
                });
    }
}