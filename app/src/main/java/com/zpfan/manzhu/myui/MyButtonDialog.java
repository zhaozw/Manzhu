package com.zpfan.manzhu.myui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zpfan.manzhu.R;

/**
 * Created by Administrator on 2017/8/2 0002.
 */

public class MyButtonDialog extends AlertDialog {
    private final Context context;
    private String mytitle;
    private String mycontent;

    private TextView mBtcancel;
    private TextView mBtsure;

    public MyButtonDialog(@NonNull Context context, @StyleRes int themeResId, String mytitle, String mycontent) {
        super(context, themeResId);
        this.mytitle = mytitle;
        this.mycontent = mycontent;
        this.context = context;
    }

    public String getMytitle() {
        return mytitle;
    }

    public void setMytitle(String mytitle) {
        this.mytitle = mytitle;
    }

    public String getMycontent() {
        return mycontent;
    }

    public void setMycontent(String mycontent) {
        this.mycontent = mycontent;
    }

    public MyButtonDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public MyButtonDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void setonsureClickListener(View.OnClickListener listener) {

        mBtsure.setOnClickListener(listener);
    }

    public void setoncancleClickListener(View.OnClickListener listener) {

        mBtcancel.setOnClickListener(listener);
    }




    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.mydialog, null);
        setContentView(view);
        //radiobutton的初始化
        TextView tvtitle = (TextView) view.findViewById(R.id.tv_dialogtitle);
        TextView tvcontent = (TextView) view.findViewById(R.id.tv_dialogcontent);
        mBtcancel = (TextView) view.findViewById(R.id.bt_cancel);
        mBtsure = (TextView) view.findViewById(R.id.bt_sure);

        tvtitle.setText(mytitle);
        tvcontent.setText(mycontent);

        //设置dialog大小，这里是一个小赠送，模块好的控件大小设置
        Window dialogWindow = getWindow();
        WindowManager manager = ((Activity) context).getWindowManager();
        WindowManager.LayoutParams params = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.CENTER);//设置对话框位置
        Display d = manager.getDefaultDisplay(); // 获取屏幕宽、高度
        params.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65，根据实际情况调整
        dialogWindow.setAttributes(params);

    }


}
