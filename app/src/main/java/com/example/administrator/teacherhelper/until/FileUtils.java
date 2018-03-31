package com.example.administrator.teacherhelper.until;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.administrator.teacherhelper.view.Activity.max.max_StudentAdd;

import java.net.URISyntaxException;

/**
 * Created by Administrator on 2018/3/31 0031.
 */

public class FileUtils {

    public static String getpath(Context context, Uri uri) {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            }
            catch (Exception e){

            }
        }else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }
}
