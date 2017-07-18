package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class LocationpopshiAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    private final List<String> data;

    public LocationpopshiAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
        this.data = data;

    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {


        helper.setText(R.id.tv_sheng,item);



    }
}
