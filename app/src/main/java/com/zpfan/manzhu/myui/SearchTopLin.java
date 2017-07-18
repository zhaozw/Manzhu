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
 * Created by Administrator on 2017/7/12 0012.
 */

public class SearchTopLin extends LinearLayout {


    private ImageView mIvtop1;
    private ImageView mIvtop2;
    private ImageView mIvtop3;
    private TextView mTvtop1;
    private TextView mTvtop2;
    private TextView mTvtop3;
    private LinearLayout mTopmenu;
    private View mInflate;

    public SearchTopLin(Context context) {
        super(context);

    }

    public SearchTopLin(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.search_top_line, this);

        ImageView back = (ImageView) findViewById(R.id.iv_top_back);
        TextView title = (TextView) findViewById(R.id.tv_searchkeyword);
        TextView type = (TextView) findViewById(R.id.tv_searchtype);

        mIvtop1 = (ImageView) findViewById(R.id.iv_searchtop1);
        mIvtop2 = (ImageView) findViewById(R.id.iv_searchtop2);

        mTvtop1 = (TextView) findViewById(R.id.tv_searchtop1);
        mTvtop2 = (TextView) findViewById(R.id.tv_searchtop2);

        mTopmenu = (LinearLayout) findViewById(R.id.ll_searchtopmenu);


        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SearchTopLin);

        title.setText(array.getString(0));
        mIvtop1.setImageResource(array.getResourceId(R.styleable.SearchTopLin_searchtop1iv, 1));
        mIvtop2.setImageResource(array.getResourceId(R.styleable.SearchTopLin_searchtop2iv, 2));


        mTvtop1.setText(array.getString(R.styleable.SearchTopLin_searchtop1tv));
        mTvtop2.setText(array.getString(R.styleable.SearchTopLin_searchtop2tv));




        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });


    }


    public void showmenu(){
        //展示菜单的方法
        mTopmenu.setVisibility(VISIBLE);

    }

    public  void  hidemenu() {
        //隐藏菜单的方法
        mTopmenu.setVisibility(GONE);
    }






}
