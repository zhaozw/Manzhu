package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;

import java.util.List;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class CsoTagAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public CsoTagAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {


        helper.setText(R.id.tv_costag, item);


    }
}
