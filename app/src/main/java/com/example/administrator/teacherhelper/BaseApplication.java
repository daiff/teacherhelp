package com.example.administrator.teacherhelper;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;


/**
 * Created by think on 2015/12/11.
 */
public class BaseApplication extends MultiDexApplication {
    private String username = "";
    private static BaseApplication mContext;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public static BaseApplication getInstance(){
        return mContext;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
    }


}
