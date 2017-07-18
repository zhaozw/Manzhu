package com.zpfan.manzhu.myui;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zpfan.manzhu.R;

/**
 * Created by Administrator on 2017/6/14 0014.
 */

public class TopLin extends LinearLayout {
    public TopLin(Context context) {
        super(context);
    }

    public TopLin(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.general_top, this);

        ImageView top_back = (ImageView) findViewById(R.id.iv_top_back);
        TextView top_text = (TextView) findViewById(R.id.tv_top_text);


        //获取到自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Topmenu);

       /* int count = array.getIndexCount();
        int index = array.getIndex(0);*/

        top_text.setText(array.getString(0));
        top_text.getPaint().setFakeBoldText(true);

        top_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)context).finish();
            }
        });
    }






}
