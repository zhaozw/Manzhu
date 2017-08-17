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
 * Created by Administrator on 2017/8/12 0012.
 */

public class ShareCommisssioinAdapter extends BaseQuickAdapter<BussnessBean,BaseViewHolder> {

    private final List<BussnessBean> data;

    public ShareCommisssioinAdapter(@LayoutRes int layoutResId, @Nullable List<BussnessBean> data) {
        super(layoutResId, data);
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, BussnessBean item) {
        ImageView ivshopcover = helper.getView(R.id.iv_shopcover);


        Glide.with(mContext).load(item.getG_Cover()).into(ivshopcover);
        String money = item.getG_CommissionMoney();
        String substring = money.substring(money.indexOf("."));
        String substring1 = money.substring(0, money.indexOf("."));


        helper.setText(R.id.tv_goodtitle, item.getG_Title())
                .setText(R.id.tv_goodprice,"售价 ¥ " + item.getG_FixedPrice())
                .setText(R.id.tv_sharenumber,"已有 " + item.getG_ShareNumber() + " 人分享")
                .setText(R.id.tv_shareprice," " +substring1)
                .setText(R.id.tv_sharepricexs,substring);

        helper.addOnClickListener(R.id.ll_share);



    }
}
