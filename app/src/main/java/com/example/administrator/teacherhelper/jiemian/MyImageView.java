package com.example.administrator.teacherhelper.jiemian;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by daiff on 2018/1/23.
 * for:
 */

public class MyImageView extends ImageView {
    public MyImageView(Context context) {
        super(context);
    }
    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, heightMeasureSpec);
    }
}
