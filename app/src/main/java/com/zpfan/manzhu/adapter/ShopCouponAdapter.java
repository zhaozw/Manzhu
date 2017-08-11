package com.zpfan.manzhu.adapter;

import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.ShopBean;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class ShopCouponAdapter extends BaseQuickAdapter<ShopBean.ShopcouponlistBean,BaseViewHolder> {
    private final List<ShopBean.ShopcouponlistBean> data;

    public ShopCouponAdapter(@LayoutRes int layoutResId, @Nullable List<ShopBean.ShopcouponlistBean> data) {
        super(layoutResId, data);
        this.data = data;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void convert(BaseViewHolder helper, ShopBean.ShopcouponlistBean item) {


        View dashline = helper.getView(R.id.dashline1);

        dashline.setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        int position = helper.getPosition();
        //最后一个不显示虚线
        if (position == data.size() -1 ) {
            dashline.setVisibility(View.GONE);
        }

       // String status = item.getMember_use_get_status();
        TextView tvused = helper.getView(R.id.tv_used);
       /* if (status.equals("已领用")) {
            tvused.setText("已领用");
            tvused.setBackground(mContext.getResources().getDrawable(R.drawable.coupon_used_shape));
        } else if (status.equals("未领用")) {
            tvused.setText("未领用");
            tvused.setBackground(mContext.getResources().getDrawable(R.drawable.coupon_unused_shape));
        }*/



        String money = item.getMC_PreferentialMoney();
        Log.i("zc", "convert:   money"  + money);
        if (money.contains(".")) {
            Double aDouble = Double.valueOf(money);
            DecimalFormat df = new DecimalFormat("0.00");
            String format = df.format(aDouble);

            int i = format.indexOf(".");

            String price = format.substring(0, i);
            String substring = format.substring(i);
            Log.i("zc", "convert:   看看数量对不对" + price+ "-----------" + substring);

            helper.setText(R.id.tv_price, price);
            helper.setText(R.id.tv_xiaoshu, substring);



        } else {
            helper.setText(R.id.tv_price, money);
        }
        helper.setText(R.id.tv_meetmoney, item.getMC_MeetMoney() + " 元");
        LinearLayout llcoupon = helper.getView(R.id.ll_coupon);

        String cate = item.getMC_Cate();
        if (cate.contains("租")) {
            llcoupon.setBackground(mContext.getResources().getDrawable(R.mipmap.coupon_bg_rent));
            helper.setText(R.id.tv_meetmoney, item.getMC_MeetMoney() + "元 / " + item.getMC_ShortestRentDay() + "天");
        } else {
            llcoupon.setBackground(mContext.getResources().getDrawable(R.mipmap.coupon_bg_sell));
        }

    }
}
