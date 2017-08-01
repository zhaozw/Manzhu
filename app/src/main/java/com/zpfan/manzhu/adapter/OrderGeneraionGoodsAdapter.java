package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.OrderGenerationBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

public class OrderGeneraionGoodsAdapter extends BaseQuickAdapter<OrderGenerationBean.GoodslistArryBean,BaseViewHolder> {

    public OrderGeneraionGoodsAdapter(@LayoutRes int layoutResId, @Nullable List<OrderGenerationBean.GoodslistArryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderGenerationBean.GoodslistArryBean item) {
        helper.setText(R.id.tv_goodtitle, item.getGoods_title())
                .setText(R.id.tv_goodformat, item.getGoods_specification())
                .setText(R.id.tv_goodcount, "x" + item.getGoods_count())
                .setText(R.id.tv_goodprice, item.getGoods_money());

        RoundedImageView ivcover = helper.getView(R.id.iv_shopcover);

        Glide.with(mContext).load(item.getGoods_images()).into(ivcover);


    }
}
