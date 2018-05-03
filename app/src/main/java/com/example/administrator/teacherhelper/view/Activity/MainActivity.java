package com.example.administrator.teacherhelper.view.Activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.administrator.teacherhelper.R;
import com.example.administrator.teacherhelper.view.fragment.InboxFragment;
import com.example.administrator.teacherhelper.view.fragment.ManageFragment;
import com.example.administrator.teacherhelper.view.fragment.MyFragment;


public class MainActivity extends Activity implements View.OnClickListener{
    private LinearLayout inbox,manage,my;
    private InboxFragment inboxFragment;
    private MyFragment myFragment;
    private ManageFragment manageFragment;
    private FragmentManager fManager;
    private FrameLayout content;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fManager = getFragmentManager();
        findViewById();

        inbox.performClick(); //模拟一次点击，既进去后选择第一项
    }


    protected void findViewById() {
        inbox = (LinearLayout)findViewById(R.id.inbox);
        manage = (LinearLayout)findViewById(R.id.manage);
        my = (LinearLayout)findViewById(R.id.my);
        content = (FrameLayout) findViewById(R.id.fragment_container);

        inbox.setOnClickListener(this);
        manage.setOnClickListener(this);
        my.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()) {
            case R.id.inbox:
                setSelected();
                inbox.setSelected(true);
                if (inboxFragment == null) {
                    inboxFragment = new InboxFragment();
                    fTransaction.add(R.id.fragment_container, inboxFragment);
                } else {
                    fTransaction.show(inboxFragment);
                }
                break;
            case R.id.manage:

                setSelected();
                manage.setSelected(true);
                if (manageFragment == null) {
                    manageFragment = new ManageFragment();
                    fTransaction.add(R.id.fragment_container, manageFragment);
                } else {
                    fTransaction.show(manageFragment);
                }
                break;
            case R.id.my:
                setSelected();
                my.setSelected(true);
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    fTransaction.add(R.id.fragment_container, myFragment);
                } else {
                    fTransaction.show(myFragment);
                }
                break;
        }
        fTransaction.commit();
    }

    //重置所有文本的选中状态
    private void setSelected() {
        inbox.setSelected(false);
        manage.setSelected(false);
        my.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (inboxFragment != null) fragmentTransaction.hide(inboxFragment);
        if (manageFragment != null) fragmentTransaction.hide(manageFragment);
        if (myFragment != null) fragmentTransaction.hide(myFragment);
    }
}
