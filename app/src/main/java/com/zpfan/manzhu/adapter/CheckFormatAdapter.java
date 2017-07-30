package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.BussnessBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/30 0030.
 */

public class CheckFormatAdapter extends BaseQuickAdapter<BussnessBean.GoodsSpecificationsBean,BaseViewHolder> {
    public CheckFormatAdapter(@LayoutRes int layoutResId, @Nullable List<BussnessBean.GoodsSpecificationsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BussnessBean.GoodsSpecificationsBean item) {
        helper.setText(R.id.tv_sheng, item.getPS_AttributeValues());


    }
}
