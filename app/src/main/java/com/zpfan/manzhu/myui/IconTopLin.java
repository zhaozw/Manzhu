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

public class IconTopLin extends LinearLayout {


    private ImageView mIvtop1;
    private ImageView mIvtop2;
    private ImageView mIvtop3;
    private TextView mTvtop1;
    private TextView mTvtop2;
    private TextView mTvtop3;
    private LinearLayout mTopmenu;
    private View mInflate;
    private TextView mTitle;
    private ImageView mIcon;

    public IconTopLin(Context context) {
        super(context);

    }

    public IconTopLin(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflate = inflater.inflate(R.layout.general_icontop, this);

        ImageView back = (ImageView) findViewById(R.id.iv_icontop_back);
        mIcon = (ImageView) findViewById(R.id.iv_menu);
        mTitle = (TextView) findViewById(R.id.tv_icontop_text);
        mIvtop1 = (ImageView) findViewById(R.id.iv_top1);
        mIvtop2 = (ImageView) findViewById(R.id.iv_top2);
        mIvtop3 = (ImageView) findViewById(R.id.iv_top3);
        mTvtop1 = (TextView) findViewById(R.id.tv_top1);
        mTvtop2 = (TextView) findViewById(R.id.tv_top2);
        mTvtop3 = (TextView) findViewById(R.id.tv_top3);
        mTopmenu = (LinearLayout) findViewById(R.id.ll_topmenu);


        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Topiconmenu);

        mTitle.setText(array.getString(0));
        mTitle.getPaint().setFakeBoldText(true);
        mIcon.setImageResource(array.getResourceId(R.styleable.Topiconmenu_topicon, 1));
        mIvtop1.setImageResource(array.getResourceId(R.styleable.Topiconmenu_top1iv, 1));
        mIvtop2.setImageResource(array.getResourceId(R.styleable.Topiconmenu_top2iv, 2));
        mIvtop3.setImageResource(array.getResourceId(R.styleable.Topiconmenu_top3iv, 3));

        mTvtop1.setText(array.getString(R.styleable.Topiconmenu_top1tv));
        mTvtop2.setText(array.getString(R.styleable.Topiconmenu_top2tv));
        mTvtop3.setText(array.getString(R.styleable.Topiconmenu_top3tv));



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

    public void  settopContext(String s){

        mTitle.setText(s);

    }


    public void setIcon(int i) {

        mIcon.setImageResource(i);

    }








}
