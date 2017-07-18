package com.zpfan.manzhu.myui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zpfan.manzhu.Aplication;
import com.zpfan.manzhu.R;

/**
 * Created by Administrator on 2017/6/14 0014.
 */

public class MyToast extends  Toast{


    private static Toast mToast;
    private int duration;
    private int imgs;
    private String text;

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public MyToast(Context context) {
        super(context);
    }


   /* public MyToast(Context context, String text, int imgs, int duration) {

        View toastView = LayoutInflater.from(context).inflate(R.layout.toast_layout, null);

        ImageView toastImg= (ImageView) toastView.findViewById(R.id.toast_img);
        TextView toastTxt = (TextView) toastView.findViewById(R.id.toast_txt);

        toastTxt.setText(text);
        toastImg.setImageResource(imgs);
        if (mToast == null) {
            mToast = new Toast(context);
            mToast.setDuration(duration);
            mToast.setView(toastView);
            mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        } else {
            mToast.setDuration(duration);
            mToast.setView(toastView);
            mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        }

    }*/



  /*  public void show(){

        if (mToast != null) {
            mToast.show();
        }

    }*/


    // TODO: 2017/6/29 0029   将弹吐司的方法 放到工具类中去 保证吐司唯一
    public  static  void show(String text,int imgs){

        View toastView = LayoutInflater.from(Aplication.mContext).inflate(R.layout.toast_layout, null);

        ImageView toastImg= (ImageView) toastView.findViewById(R.id.toast_img);
        TextView toastTxt = (TextView) toastView.findViewById(R.id.toast_txt);

        toastTxt.setText(text);
        toastImg.setImageResource(imgs);

        if (Looper.myLooper() != Looper.getMainLooper() ) {
            Looper.prepare();
            Toast toast = new Toast(Aplication.mContext);
            toast.setView(toastView);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);

            toast.show();
           Looper.loop();
        } else {
            Toast toast = new Toast(Aplication.mContext);
            toast.setView(toastView);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();
        }

    }




}
