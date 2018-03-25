package com.example.administrator.teacherhelper.view.enclosure;


//登录时旋转


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

import com.example.administrator.teacherhelper.R;


public class FlippingLoadingDialog extends Dialog {
    private TextView tv_text;

    public FlippingLoadingDialog(Context context) {
        super(context);
        /**设置对话框背景透明*/
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.common_flipping_loading_diloag);
        tv_text = (TextView) findViewById(R.id.loadingdialog_htv_text);
        setCanceledOnTouchOutside(false);
    }

    /**
     * 为加载进度个对话框设置不同的提示消息
     *
     * @param message 给用户展示的提示信息
     * @return build模式设计，可以链式调用
     */
    public FlippingLoadingDialog setMessage(String message) {
        tv_text.setText(message);
        return this;
    }

}
