package com.example.administrator.teacherhelper.view.Activity;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.ButterKnife;


/**
 * @Description:所有Activity的基类，是个抽象类，把整个项目中都需要用到的东西封装起来
 * @author http://blog.csdn.net/finddreams
 */

public abstract class BaseActivity extends AppCompatActivity {


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentLayout());
		initView();
		initData();
	}

	protected abstract int getContentLayout();
	protected abstract void initView();
	protected abstract void initData();


}
