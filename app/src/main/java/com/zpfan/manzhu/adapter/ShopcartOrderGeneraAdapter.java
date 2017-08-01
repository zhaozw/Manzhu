package com.zpfan.manzhu.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.chat.EMMessage;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.OrderGenerationBean;
import com.zpfan.manzhu.myui.EaseActivity;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1 0001.
 */

public class ShopcartOrderGeneraAdapter extends BaseQuickAdapter<OrderGenerationBean,BaseViewHolder> {
    private DecimalFormat mDf;

    public ShopcartOrderGeneraAdapter(@LayoutRes int layoutResId, @Nullable List<OrderGenerationBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final OrderGenerationBean item) {
        String pattern = item.getO_TradingPattern();
        mDf = new DecimalFormat("0.00");

        if (pattern.equals("线下交易")) {
            helper.setText(R.id.tv_style,"（买家付款后卖家即得到货款）");
        }

            String s = item.getO_CouponMoney() + "";
            Double couponMoney = Double.valueOf(s);
        String yun = item.getO_GoodsFreight() + "";
        Double yunf = Double.valueOf(yun);


        helper.setText(R.id.tv_ordernumber, item.getO_UID())
                .setText(R.id.tv_ordertime,item.getO_OrderTime())
                .setText(R.id.tv_shopname,item.getStore_CN())
                .setText(R.id.tv_userlv,"Lv." + item.getStore_Level())
                .setText(R.id.tv_yunfei,mDf.format(yunf))
                .setText(R.id.tv_youhui, mDf.format(couponMoney));


        LinearLayout llmessage = helper.getView(R.id.ll_message);
        LinearLayout llgetorderdetil = helper.getView(R.id.ll_getorderdetil);
        RecyclerView rvordergoods = helper.getView(R.id.rv_order_goods);

        rvordergoods.setLayoutManager(new LinearLayoutManager(mContext));
        List<OrderGenerationBean.GoodslistArryBean> arry = item.getGoodslist_arry();
        OrderGeneraionGoodsAdapter adapter = new OrderGeneraionGoodsAdapter(R.layout.item_ordergengeraion_goods, arry);
        rvordergoods.setAdapter(adapter);


        llmessage.setOnClickListener(new View.OnClickListener() {
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
