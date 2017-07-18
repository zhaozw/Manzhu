package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.utils.SPUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class IdelpopAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    private int checkposition;

    public IdelpopAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        checkposition = SPUtils.getInstance().getInt("check", 0);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        helper.setText(R.id.tv_sheng, item);
        ImageView check = helper.getView(R.id.iv_sheng_checked);
        int position = helper.getPosition();
        if (position == checkposition) {
            check.setVisibility(View.VISIBLE);
        } else {
            check.setVisibility(View.INVISIBLE);
        }


    }


    public  void cleanCheck(){

        checkposition = -1;
        notifyDataSetChanged();

    }


    public  int getpostion(){

        return checkposition;
    }

    public void setCheck(int pos){
        checkposition = pos;
        notifyDataSetChanged();
    }



}
