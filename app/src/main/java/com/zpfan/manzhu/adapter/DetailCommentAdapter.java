package com.zpfan.manzhu.adapter;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.BussnessBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22 0022.
 */

public class DetailCommentAdapter extends BaseQuickAdapter<BussnessBean.OrderReviewListBean,BaseViewHolder> {


    private final int i;
    private BussnessBean bean;


    public DetailCommentAdapter(@LayoutRes int layoutResId, @Nullable List<BussnessBean.OrderReviewListBean> data, BussnessBean bean,int i) {
        super(layoutResId, data);
        this.bean = bean;
        this.i = i;
    }



    @Override
    protected void convert(BaseViewHolder helper, BussnessBean.OrderReviewListBean item) {
        helper.setText(R.id.tv_title, bean.getG_Title());
        if (i == 0) {
            helper.setText(R.id.tv_comment, item.getOR_GoodsComment());
        } else {
            helper.setText(R.id.tv_comment, item.getOR_Sellerimpression());
        }
        helper.setText(R.id.tv_format, item.getGoods_spcification_name());

        helper.setText(R.id.tv_userlv, "Lv." + item.getReivewmember_level() );
        ImageView view = helper.getView(R.id.iv_avator);


        RequestOptions options = new RequestOptions().centerCrop();

        Glide.with(mContext).asBitmap().load( item.getReviewmember_avator()).apply(options).into(new BitmapImageViewTarget(view) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                view.setImageDrawable(circularBitmapDrawable);
            }
        });



    }
}
