package com.zpfan.manzhu.myui;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zpfan.manzhu.R;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class SearchTopLin extends LinearLayout {


    private final Context context;
    private ImageView mIvtop1;
    private ImageView mIvtop2;
    private ImageView mIvtop3;
    private TextView mTvtop1;
    private TextView mTvtop2;
    private TextView mTvtop3;
    private LinearLayout mTopmenu;
    private View mInflate;
    private LinearLayout mLlsearch;
    private PopupWindow mWindow;

    public SearchTopLin(Context context) {
        super(context);
        this.context = context;
    }

    public SearchTopLin(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
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
        mLlsearch = (LinearLayout) findViewById(R.id.ll_search);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SearchTopLin);
        type.setText(array.getString( R.styleable.SearchTopLin_searchtype));
        title.setText(array.getString(R.styleable.SearchTopLin_searchcontext));
        int id = array.getResourceId(R.styleable.SearchTopLin_searchtop1iv, 1);
        int id1 = array.getResourceId(R.styleable.SearchTopLin_searchtop2iv, 2);
        if (id != 1 && id1 != 2) {
            mIvtop1.setImageResource(id);
            mIvtop2.setImageResource(id1);
        }
        boolean aBoolean = array.getBoolean(R.styleable.SearchTopLin_isshowmore, true);
        if (aBoolean) {
            mTopmenu.setVisibility(VISIBLE);
        } else {
            mTopmenu.setVisibility(GONE);
        }

        mTvtop1.setText(array.getString(R.styleable.SearchTopLin_searchtop1tv));
        mTvtop2.setText(array.getString(R.styleable.SearchTopLin_searchtop2tv));




        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).finish();
            }
        });

        mLlsearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindow = new PopupWindow(context);
                View inflate = View.inflate(context, R.layout.search_popwindow, null);
                EditText edtopsearch = (EditText) inflate.findViewById(R.id.ed_topsearch);

                edtopsearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                WindowManager.LayoutParams lp = ((Activity) context).getWindow()
                        .getAttributes();
                lp.alpha = 0.4f;
                ((Activity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                ((Activity) context).getWindow().setAttributes(lp);

                mWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.detail_toppop_bg));
                mWindow.setTouchable(true);
                mWindow.setContentView(inflate);
                mWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                mWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                mWindow.setOutsideTouchable(true);
                mWindow.update();
                mWindow.showAtLocation(inflate, Gravity.TOP, 0, 0);

                mWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = ((Activity) context).getWindow()
                                .getAttributes();
                        lp.alpha = 1f;
                        ((Activity) context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        ((Activity) context).getWindow().setAttributes(lp);

                    }
                });





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
