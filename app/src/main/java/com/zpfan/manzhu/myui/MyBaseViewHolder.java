package com.zpfan.manzhu.myui;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class MyBaseViewHolder extends BaseViewHolder {

    public MyBaseViewHolder(View view) {
        super(view);
        int width = view.getContext().getResources().getDisplayMetrics().widthPixels / 2;
        view.getLayoutParams().width = width;



    }
}
