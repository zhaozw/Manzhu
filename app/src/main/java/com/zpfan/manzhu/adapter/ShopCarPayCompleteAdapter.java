package com.zpfan.manzhu.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.chat.EMMessage;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.OrderGenerationBean;
import com.zpfan.manzhu.myui.EaseActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class ShopCarPayCompleteAdapter extends BaseQuickAdapter<OrderGenerationBean,BaseViewHolder> {
    public ShopCarPayCompleteAdapter(@LayoutRes int layoutResId, @Nullable List<OrderGenerationBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final OrderGenerationBean item) {
        helper.setText(R.id.tv_ordernumber, item.getO_UID());
        String pattern = item.getO_TradingPattern();
        helper.setText(R.id.tv_buystyle, pattern);

        if (pattern.equals("线下交易")) {
            helper.setText(R.id.tv_style, "（买家付款后卖家即得到货款）");
        }
        helper.setText(R.id.tv_ordertime, item.getO_OrderTime().replace("-", " -  "))
                .setText(R.id.tv_shopname,item.getStore_CN())
                .setText(R.id.tv_userlv, "Lv." + item.getStore_Level());


        LinearLayout llgetorderdetil = helper.getView(R.id.ll_getorderdetil);
        LinearLayout ll_message = helper.getView(R.id.ll_message);

        llgetorderdetil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取订单详情


            }
        });


        ll_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, EaseActivity.class);
                intent.putExtra("userId", item.getStore_Phone());
                intent.putExtra("usercn", item.getStore_CN());
                intent.putExtra("chatType", EMMessage.ChatType.Chat);
                mContext.startActivity(intent);

            }
        });



    }



}
