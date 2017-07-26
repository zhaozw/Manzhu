package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public class FormartAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public FormartAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_sheng, item);


    }

}
