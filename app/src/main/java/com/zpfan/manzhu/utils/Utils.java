package com.zpfan.manzhu.utils;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zpfan.manzhu.Aplication;
import com.zpfan.manzhu.bean.UserBean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public class Utils {

    public static String baseUrl = "http://www.anipiggy.com/AppWebService.asmx/";
    public static String imgUrl = "http://www.anipiggy.com";
    public static UserBean user = null;


    public static Gson gson = new Gson();


    private Toast mToast;


    /**
     * 验证手机号码
     * @param phoneNumber 手机号码
     * @return boolean
     */
    public static boolean checkPhoneNumber(String phoneNumber){
        Pattern pattern= Pattern.compile("^(1)\\d{10}$");
        Matcher matcher=pattern.matcher(phoneNumber);
        return matcher.matches();
    }


    /**
     * 全局设置状态栏背景状态
     */
    public static  void setTranslucentStatus(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = activity.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
        SystemStatusManager tintManager = new SystemStatusManager(activity);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(0);
    }


    /**
     * 判断用户是否登陆
     * @return
     */
    public static  boolean isUserLogin() {
        boolean userlogin = SPUtils.getInstance().getBoolean("userlogin", false);
        return userlogin;
    }

    /**
     * 是不是第一次 去加载聊天界面
     * @param phone
     * @return
     */
    public static  boolean isFirstLogin(String phone) {
        boolean isFirest = SPUtils.getInstance().getBoolean(phone, true);

        return isFirest;
    }


    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(final float dpValue) {
        final float scale = Aplication.mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(final float pxValue) {
        final float scale = Aplication.mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转px
     *
     * @param spValue sp值
     * @return px值
     */
    public static int sp2px(final float spValue) {
        final float fontScale = Aplication.mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px转sp
     *
     * @param pxValue px值
     * @return sp值
     */
    public static int px2sp(final float pxValue) {
        final float fontScale = Aplication.mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    public static String[] getchangedata(String data) {
        //首先 将字符串根据，号 切割出来
        String[] split = data.split(",");




        return split;

    }






}
