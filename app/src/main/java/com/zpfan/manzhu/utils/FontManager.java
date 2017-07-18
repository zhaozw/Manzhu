package com.zpfan.manzhu.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 使用 fontawesome 字体做图标的工具类
 * Created by Administrator on 2017/6/13 0013.
 */

public class FontManager {

    public static final String ROOT = "fonts/",
            FONTAWESOME = ROOT + "fontawesome-webfont.ttf";


    public static Typeface getTypeface(Context context, String font) {


        return Typeface.createFromAsset(context.getAssets(), font);

    }

    /**
     *  根据text不同字体 来设置图标
     * @param v
     * @param typeface
     */
    public static void markAsIconContainer(View v, Typeface typeface) {
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                markAsIconContainer(child,typeface);
            }
        } else if (v instanceof TextView) {
            ((TextView) v).setTypeface(typeface);
        }
    }


}
