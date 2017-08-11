package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.BussnessBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public class ShopGoodListAdapter extends BaseQuickAdapter<BussnessBean,BaseViewHolder> {
    public ShopGoodListAdapter(@LayoutRes int layoutResId, @Nullable List<BussnessBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BussnessBean item) {
        ImageView goodcover = helper.getView(R.id.iv_goodcover);

        Glide.with(mContext).load(item.getG_Cover()).into(goodcover);

        helper.setText(R.id.tv_goodname, item.getG_Title())
                .setText(R.id.tv_price, "¥ " +item.getG_FixedPrice());

        if (item.getG_Type().equals("服务")) {
            helper.setText(R.id.tv_price, "¥ " + item.getG_FixedPrice() + "/" + item.getServer_unit_string());
            helper.setText(R.id.tv_bussnesstag, "约单");
        } else if (item.getG_Type().equals("新商品")) {
            helper.setText(R.id.tv_bussnesstag, "新品");

        } else if (item.getG_Type().equals("二手商品")) {

            helper.setText(R.id.tv_bussnesstag, "闲置");

        }

    }
}
