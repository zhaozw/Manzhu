package com.zpfan.manzhu.utils;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.Aplication;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.UserBean;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
     * 获取到登陆用户的uid
     * @return
     */
    public  static String getloginuid() {
        String userid = SPUtils.getInstance().getString("useruid", "");

        return userid;


    }


    /**
     * 获取到登陆用户的id
     * @return
     */
    public  static String getloginid() {
        String userid = SPUtils.getInstance().getString("userid", "");

        return userid;

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

    /**
     * 将发布时间转换固定的格式
     * @param data
     * @return
     */
    public static String timeChange(String data) {
        String[] split = data.split(" ");
        String myYear = split[0];  //我的时间 年份什么的
        String mytime = split[1];  //我的具体的时间 小时-分钟-秒

        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            Date dt2 = sdf.parse(data);
            long lTime = dt2.getTime();
            long lSysTime2 = System.currentTimeMillis();
            long l = (lSysTime2 - lTime) / 1000;
            int mins = (int) (l / 60);  //分钟数
            //一小时以内的
            if (mins > 0 && mins <= 60) {
                return mins + "分钟前 发布";
            } else if (mins > 60 && mins <= 1440) {
                //一小时一上 一天以内
                int hourss = mins / 60;  //小时数
                return hourss + "小时前 发布";
            } else if (mins > 1440 && mins <= 10080) {
                //一天以上 一周以内
                int dayss = mins / 1440;
                return dayss + "天前 发布";
            } else {
                //一周以上的
                return myYear + " 发布";
            }



        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }


    }


    /**
     * 将浏览数量等 转换为固定的格式
     * @param numebr
     * @return
     */
    public static String numberChange(String numebr) {
        Integer integer = Integer.valueOf(numebr);
        if (integer < 9999) {
            return numebr;
        } else {
            String zhengshu = numebr.substring(0, 2);
            String xioashu = numebr.substring(2, 4);
            return zhengshu + "."+ xioashu + "万";
        }


    }


    public static AvatorBean getResult(String body) {
        if (body != null) {
            Type type = new TypeToken<ArrayList<AvatorBean>>() {
            }.getType();

            ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
            if (avatorBeen != null && avatorBeen.size() > 0) {
                AvatorBean bean = avatorBeen.get(0);
                if (bean != null) {
                    return bean;
                } else {
                    return null;
                }

            } else {
                return null;
            }


        } else {
            return null;
        }




    }





}
