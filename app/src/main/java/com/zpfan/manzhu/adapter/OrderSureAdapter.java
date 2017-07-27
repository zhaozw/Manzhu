package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.ShopCartbean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class OrderSureAdapter extends BaseQuickAdapter<ShopCartbean.CarshoplistBean,BaseViewHolder> {
    public OrderSureAdapter(@LayoutRes int layoutResId, @Nullable List<ShopCartbean.CarshoplistBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCartbean.CarshoplistBean item) {

            helper.setText(R.id.tv_shopname, item.getMember_Name());



    }



}
