package com.example.administrator.teacherhelper.until;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.administrator.teacherhelper.R;


/**
 * 登录帐号管理Created by yw on 2015/5/5.
 */
public class AccountUtils {

    public static final int REQUEST_LOGIN = 0;

    private static final String key_login_member = "logined@member";
    private static final String key_fav_nodes = "logined@fav_nodes";

    /**
     * 记住的密码
     */
    public static String getUserPassword(Context cxt) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.logined_member_password), "");
    }
    public static void setUserPassword(Context cxt, String password) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.logined_member_password), password).commit();
    }
    /**
     * 记录用户名
     */

    public static void setUserid(Context cxt, String userName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.logined_member_usernid), userName).commit();
    }
    public static String getUserid(Context cxt) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.logined_member_usernid), "");
    }


    /**
     * 记录用户中文名
     */

    public static void setUsername(Context cxt, String userName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.logined_member_username), userName).commit();
    }
    public static String getUsername(Context cxt) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.logined_member_username), "");
    }

    /**
     * 记录用户邮箱
     */

    public static void setEmail(Context cxt, String userName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.logined_member_email), userName).commit();
    }
    public static String getEmail(Context cxt) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.logined_member_email), "");
    }

    /**
     * 记录用户邮箱
     */

    public static void setPhone(Context cxt, String userName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.logined_member_phone), userName).commit();
    }
    public static String getPhone(Context cxt) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.logined_member_phone), "");
    }

    /**
     * 记录教师的头衔
     */

    public static void setTitle(Context cxt, String userName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.logined_member_title), userName).commit();
    }
    public static String getTitle(Context cxt) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.logined_member_title), "");
    }
    /**
     * 记录教师的头衔
     */

    public static void setid(Context cxt, String userName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.logined_member_id), userName).commit();
    }
    public static String getid(Context cxt) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.logined_member_id), "");
    }

    /**
     * 记录当前学期
     */

    public static void setyear(Context cxt, String userName) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        sharedPreferences.edit().putString(cxt.getString(R.string.logined_member_year), userName).commit();
    }
    public static String getyear(Context cxt) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cxt);
        return sharedPreferences.getString(cxt.getString(R.string.logined_member_year), "");
    }



}