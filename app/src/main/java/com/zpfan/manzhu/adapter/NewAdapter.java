package com.zpfan.manzhu.adapter;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.BussnessBean;
import com.zpfan.manzhu.utils.Utils;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class NewAdapter extends BaseQuickAdapter<BussnessBean,BaseViewHolder> {


    private boolean isshowgood = true;

    public NewAdapter(@LayoutRes int layoutResId, @Nullable List<BussnessBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BussnessBean item) {
            //点击按钮以后要显示的界面
        final ImageView  mAvator = helper.getView(R.id.iv_avator);
        final ImageView  mRenzheng = helper.getView(R.id.iv_renzheng);
        final ImageView  mShop = helper.getView(R.id.iv_shop);
        final ImageView mManor = helper.getView(R.id.iv_manor);
        final ImageView   mMore = helper.getView(R.id.iv_more);
        final TextView  mName = helper.getView(R.id.tv_name);
        final TextView mDizhi = helper.getView(R.id.tv_dizhi);
        TextView mBaobeikaopu = helper.getView(R.id.tv_baobeikaopu);
        TextView mMaikaopu = helper.getView(R.id.tv_maikaopu);
        final TextView  mUserlv = helper.getView(R.id.tv_userlv);
        TextView  mZukaopu = helper.getView(R.id.tv_zukaopu);
        final RelativeLayout mShopcar = helper.getView(R.id.rl_shopcar);
        final RelativeLayout mCollect = helper.getView(R.id.rl_collect);
        final LinearLayout    mKaopu = helper.getView(R.id.ll_kaopu);
        final LinearLayout  mLlmaikaopu = helper.getView(R.id.ll_maikaopu);
        final LinearLayout mLlzukaopu = helper.getView(R.id.ll_zukaopu);
        final ImageView  mBussnessphoto = helper.getView(R.id.iv_bussness_photo);
        final TextView  mBussnesstag = helper.getView(R.id.tv_bussnesstag);
        final TextView mBaoyou = helper.getView(R.id.tv_baoyou);
        final TextView  mTitle = helper.getView(R.id.tv_title);
        final LinearLayout mFanmai = helper.getView(R.id.ll_fanmai);
        final LinearLayout mbrand = helper.getView(R.id.ll_brand);
        TextView brandid = helper.getView(R.id.tv_brandname);

        helper.addOnClickListener(R.id.iv_bussness_photo)
                .addOnClickListener(R.id.tv_bussnesstag)
                .addOnClickListener(R.id.ll_baoyou)
                .addOnClickListener(R.id.ll_fanmai)
                .addOnClickListener(R.id.ll_brand);




        //设置商品的封面
        Glide.with(mContext).load(item.getG_Cover()).into(mBussnessphoto);

        //设置是否包邮
        if (item.isG_IsFreeShip()) {
            mBaoyou.setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_title, "         " + item.getG_Title());
        } else {
            mBaoyou.setVisibility(View.GONE);
            helper.setText(R.id.tv_title, item.getG_Title());
        }




        //是不是 要贩卖的商品
        if (item.isG_IsSale()) {
            ViewGroup.LayoutParams params = mFanmai.getLayoutParams();
            params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            mFanmai.setLayoutParams(params);
            //设置贩卖的价格
            String price = item.getG_FixedPrice();
            String pr = price + "";
            int index = pr.indexOf(".");
            String substring = pr.substring(0, index);
            String decimal = pr.substring(index + 1);


            helper.setText(R.id.tv_price, substring)
                    .setText(R.id.tv_decimal,"." +decimal);

        } else {
            ViewGroup.LayoutParams params = mFanmai.getLayoutParams();
            params.height = 0;
            mFanmai.setLayoutParams(params);
        }



        BussnessBean.GMemberOBJBean obj = item.getG_Member_OBJ();


        //设置头像 圆形
        RequestOptions options = new RequestOptions().centerCrop();

        Glide.with(mContext).asBitmap().load(Utils.imgUrl + obj.getM_Avatar()).apply(options).into(new BitmapImageViewTarget(mAvator) {
            @Override
            protected void setResource(Bitmap resource) {

                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    mAvator.setImageDrawable(circularBitmapDrawable);

            }
        });

        //设置店铺的名字
        helper.setText(R.id.tv_name, obj.getM_Name()).setText(R.id.tv_dizhi,obj.getM_Province() + "-" + obj.getM_City());
        //设置性别
        if (obj.getM_Sex().equals("男")) {
            mManor.setImageResource(R.mipmap.com_icon_male);
        } else {
            mManor.setImageResource(R.mipmap.com_icon_female);
        }

        //设置是否是商家
        if (obj.isM_IsBusiness()) {
            //是商家
            mShop.setImageResource(R.mipmap.type_icon_shop);
            helper.setText(R.id.tv_name, obj.getM_UserName());
        } else {
            //是个人用户
            mShop.setImageResource(R.mipmap.type_icon_user);
            helper.setText(R.id.tv_name, obj.getM_UserName());
        }

        //评价星级
        helper.setText(R.id.tv_baobeikaopu, item.getBbkpd_member_value())
                .setText(R.id.tv_maikaopu, item.getMjkpd_member_value())
                .setText(R.id.tv_zukaopu, item.getCzrkpd_member_value());


        //设置品牌名
        String id = item.getBrandID();

        if (id.equals("")) {
            brandid.setVisibility(View.GONE);
        } else {
            brandid.setText(id + "|");
        }
        // helper.setText(R.id.tv_brandname,item.get)


        //设置销量
        helper.setText(R.id.tv_salenumber, item.getG_SaleNum() + "");

        //设置定金  先检查是否支持定金
        if (item.isG_IsDepositDeal()) {
            //如果支持定金我就显示定金 如果不支持 就把定金那个内容设置位“”
            helper.setText(R.id.tv_deposit, "（包含定金：¥ " + item.getG_DepositPrice() + "）");

        } else {
            helper.setText(R.id.tv_deposit, "");
        }








        mMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击了以后隐藏信息 或者  展示信息
                if (isshowgood) {
                   // showBussnessinfo();
                    mAvator.setVisibility(View.VISIBLE);
                    mShop.setVisibility(View.VISIBLE);
                    mManor.setVisibility(View.VISIBLE);
                    mName.setVisibility(View.VISIBLE);
                    mDizhi.setVisibility(View.VISIBLE);
                    mKaopu.setVisibility(View.VISIBLE);
                    mLlmaikaopu.setVisibility(View.VISIBLE);
                    mLlzukaopu.setVisibility(View.VISIBLE);
                    mCollect.setVisibility(View.VISIBLE);
                    mShopcar.setVisibility(View.VISIBLE);
                    mUserlv.setVisibility(View.VISIBLE);



                    mBussnessphoto.setVisibility(View.GONE);
                    mBussnesstag.setVisibility(View.GONE);
                    mBaoyou.setVisibility(View.GONE);
                    mTitle.setVisibility(View.GONE);
                    mFanmai.setVisibility(View.GONE);
                    mbrand.setVisibility(View.GONE);

                    mMore.setImageResource(R.mipmap.com_icon_pro_list_corn_x);
                } else {
                    //showGoodinfo();
                    mAvator.setVisibility(View.GONE);
                    mShop.setVisibility(View.GONE);
                    mManor.setVisibility(View.GONE);
                    mName.setVisibility(View.GONE);
                    mDizhi.setVisibility(View.GONE);
                    mKaopu.setVisibility(View.GONE);
                    mLlmaikaopu.setVisibility(View.GONE);
                    mLlzukaopu.setVisibility(View.GONE);
                    mCollect.setVisibility(View.GONE);
                    mShopcar.setVisibility(View.GONE);
                    mUserlv.setVisibility(View.GONE);


                    mBussnessphoto.setVisibility(View.VISIBLE);
                    mBussnesstag.setVisibility(View.VISIBLE);
                    mBaoyou.setVisibility(View.VISIBLE);
                    mTitle.setVisibility(View.VISIBLE);
                    mFanmai.setVisibility(View.VISIBLE);
                    mbrand.setVisibility(View.VISIBLE);
                    mMore.setImageResource(R.mipmap.com_icon_pro_list_corn);
                }

                isshowgood = !isshowgood;

            }
        });






    }








}
