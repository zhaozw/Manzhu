package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.OrderCouponBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class CouponpopAdapter extends BaseQuickAdapter<OrderCouponBean,BaseViewHolder> {
    public CouponpopAdapter(@LayoutRes int layoutResId, @Nullable List<OrderCouponBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderCouponBean item) {
        helper.setText(R.id.tv_sheng, "满 ¥ " + item.getMC_MeetMoney() + "减 ¥ " + item.getMC_PreferentialMoney());

    }
}
