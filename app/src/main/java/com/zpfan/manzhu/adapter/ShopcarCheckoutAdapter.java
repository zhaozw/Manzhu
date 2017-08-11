package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.OrderGenerationBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class ShopcarCheckoutAdapter extends BaseQuickAdapter<OrderGenerationBean,BaseViewHolder> {
    public ShopcarCheckoutAdapter(@LayoutRes int layoutResId, @Nullable List<OrderGenerationBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderGenerationBean item) {

        OrderGenerationBean.GoodslistArryBean bean = item.getGoodslist_arry().get(0);
        helper.setText(R.id.tv_goodname, bean.getGoods_title());
        helper.setText(R.id.tv_goodprice, " ¥ " +bean.getGoods_money());

    }
}
