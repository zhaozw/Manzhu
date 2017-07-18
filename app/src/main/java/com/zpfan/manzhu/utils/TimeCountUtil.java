package com.zpfan.manzhu.utils;

import android.content.Context;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.widget.Button;

/**
 * 发验证码的按钮倒计时
 * Created by kqw on 2016/5/11.
 * TimeCountUtil
 */
public class TimeCountUtil extends CountDownTimer {

    private Button mButton;
    private final Context mContext;


    public TimeCountUtil(Button button, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mButton = button;
        mContext = button.getContext();




    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onTick(long millisUntilFinished) {
        // 按钮不可用
        mButton.setEnabled(false);
        String showText = millisUntilFinished / 1000 + " s";
        mButton.setText(showText);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onFinish() {
        // 按钮设置可用
        mButton.setEnabled(true);
        mButton.setText("重新获取验证码");

    }
}
