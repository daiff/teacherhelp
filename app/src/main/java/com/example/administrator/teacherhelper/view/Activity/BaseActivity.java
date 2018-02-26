package com.example.administrator.teacherhelper.view.Activity;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.administrator.teacherhelper.AppManager;
import com.example.administrator.teacherhelper.BaseApplication;

import butterknife.ButterKnife;


/**
 * @Description:所有Activity的基类，是个抽象类，把整个项目中都需要用到的东西封装起来
 * @author http://blog.csdn.net/finddreams
 */ 
public abstract class BaseActivity extends ActionBarActivity {
	private ProgressDialog mProgressDialog;

	protected BaseApplication baseApplication;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
		baseApplication = (BaseApplication) getApplication();
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		ButterKnife.unbind(this);
		//结束Activity&从堆栈中移除
		AppManager.getAppManager().finishActivity(this);
	}

	public void showProgressBar(boolean show) {
		showProgressBar(show, "");
	}

	public void showProgressBar(boolean show, String message) {
		initProgressBar();
		if (show) {
			mProgressDialog.setMessage(message);
			mProgressDialog.show();
		} else {
			mProgressDialog.hide();
		}
	}

	private void initProgressBar() {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(this);
			mProgressDialog.setIndeterminate(true);
			mProgressDialog.setCancelable(false);
		}
	}

	public void showProgressBar(int messageId) {
		String message = getString(messageId);
		showProgressBar(true, message);
	}

	public void colseProgressBar() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}
	}

	/**
	 *
	 */
	protected BaseApplication getBaseApplication() {
		return baseApplication;
	}

	/**
	 * 绑定控件id
	 */
	protected abstract void findViewById();

	/**
	 * 初始化控件
	 */
	protected abstract void initView();

}
