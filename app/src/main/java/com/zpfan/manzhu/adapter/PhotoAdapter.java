package com.zpfan.manzhu.adapter;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class PhotoAdapter extends BaseQuickAdapter<BussnessBean,BaseViewHolder> {


    private boolean isshowgood = true;

    public PhotoAdapter(@LayoutRes int layoutResId, @Nullable List<BussnessBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BussnessBean item) {

        Log.i("zc", "convert:   是不是 item 为null" + item.isG_IsFreeShip());
            //点击按钮以后要显示的界面
        final ImageView ivavator = helper.getView(R.id.iv_avator);
        ImageView ivshop= helper.getView(R.id.iv_shop);
        ImageView ivmanor = helper.getView(R.id.iv_manor);
        ImageView ivstar1 = helper.getView(R.id.iv_start1);
        ImageView ivstar2 = helper.getView(R.id.iv_start2);
        ImageView ivstar3 = helper.getView(R.id.iv_start3);
        ImageView ivstar4 = helper.getView(R.id.iv_start4);
        ImageView ivstar5 = helper.getView(R.id.iv_start5);
        final ImageView ivmore = helper.getView(R.id.iv_more);
        final ImageView ivphoto = helper.getView(R.id.iv_photo);


        final TextView tvuserlv = helper.getView(R.id.tv_userlv);
        final TextView tvdizhi = helper.getView(R.id.tv_dizhi);
        final TextView tvtag = helper.getView(R.id.tv_bussnesstag);
        final TextView tvidle = helper.getView(R.id.tv_idle);
        final TextView tvprovice = helper.getView(R.id.tv_provice);
        final TextView tvphototitle = helper.getView(R.id.tv_phototitle);
        final TextView tvmoreprice = helper.getView(R.id.tv_moreprice);
        final TextView tvsalenumber = helper.getView(R.id.tv_salenumber);


        final LinearLayout llleve = helper.getView(R.id.ll_leve);
        final LinearLayout llshop = helper.getView(R.id.ll_shop);
        final LinearLayout llscore = helper.getView(R.id.ll_score);
        final LinearLayout llinfo = helper.getView(R.id.ll_info);
        final LinearLayout llprice = helper.getView(R.id.ll_price);
        final LinearLayout llcollect = helper.getView(R.id.ll_photocoll);


        //设置商品的封面
        if (item.getG_Cover() != null && ivphoto!= null) {
            Glide.with(mContext).load(item.getG_Cover()).into(ivphoto);
        }

        if (item.isG_IsFreeShip()) {
            tvidle.setVisibility(View.VISIBLE);
        } else {
            tvidle.setWidth(0);
            //tvidle.setVisibility(View.GONE);

        }
        helper.setText(R.id.tv_phototitle, item.getG_Title());



        //是不是 多价格的商品
        if (item.isG_IsMorePrice()) {
            tvmoreprice.setVisibility(View.VISIBLE);
        } else {
            tvmoreprice.setVisibility(View.INVISIBLE);
        }

        String price = item.getG_FixedPrice();
        String pr = price + "";
        int index = pr.indexOf(".");
        String substring = pr.substring(0, index);
        String decimal = pr.substring(index + 1);
        helper.setText(R.id.tv_price, substring)
                .setText(R.id.tv_decimal,"." +decimal);


        BussnessBean.GMemberOBJBean obj = item.getG_Member_OBJ();


        //设置头像 圆形
        RequestOptions options = new RequestOptions().centerCrop();

        Glide.with(mContext).asBitmap().load(Utils.imgUrl + obj.getM_Avatar()).apply(options).into(new BitmapImageViewTarget(ivavator) {
            @Override
            protected void setResource(Bitmap resource) {

                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                ivavator.setImageDrawable(circularBitmapDrawable);

            }
        });

        //设置店铺的名字
        helper.setText(R.id.tv_name, obj.getM_Name()).setText(R.id.tv_dizhi,obj.getM_Province() + "-" + obj.getM_City());
        //设置性别
        if (obj.getM_Sex().equals("男")) {
            ivmanor.setImageResource(R.mipmap.com_icon_male);
        } else {
            ivmanor.setImageResource(R.mipmap.com_icon_female);
        }

        //设置是否是商家
        if (obj.isM_IsBusiness()) {
            //是商家
            ivshop.setImageResource(R.mipmap.type_icon_shop);
            helper.setText(R.id.tv_name, obj.getM_UserName());
        } else {
            //是个人用户
            ivshop.setImageResource(R.mipmap.type_icon_user);
            helper.setText(R.id.tv_name, obj.getM_UserName());
        }

        //设置地址
        helper.setText(R.id.tv_provice, item.getG_Province() + "-" + item.getG_City());

        //设置靠谱度
        helper.setText(R.id.tv_pro, item.getProfessionaldegree_value())
                .setText(R.id.tv_server, item.getAttitude_number_value())
                .setText(R.id.tv_finishspeed, item.getComplatespeed_value());

        //设置评级
        String value = item.getProfessionaldegree_value();
        Log.i("zc", "convert:   看看数据  为什么没有设置上去" + value);
        if (value.equals("0.5")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_half);
            ivstar2.setImageResource(R.mipmap.com_icon_star_off);
            ivstar3.setImageResource(R.mipmap.com_icon_star_off);
            ivstar4.setImageResource(R.mipmap.com_icon_star_off);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);
        } else if (value.equals("1.0")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_off);
            ivstar3.setImageResource(R.mipmap.com_icon_star_off);
            ivstar4.setImageResource(R.mipmap.com_icon_star_off);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);
        } else if (value.equals("0.0")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_off);
            ivstar2.setImageResource(R.mipmap.com_icon_star_off);
            ivstar3.setImageResource(R.mipmap.com_icon_star_off);
            ivstar4.setImageResource(R.mipmap.com_icon_star_off);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);
        } else if (value.equals("1.5")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_half);
            ivstar3.setImageResource(R.mipmap.com_icon_star_off);
            ivstar4.setImageResource(R.mipmap.com_icon_star_off);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);
        } else if (value.equals("2")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_on);
            ivstar3.setImageResource(R.mipmap.com_icon_star_off);
            ivstar4.setImageResource(R.mipmap.com_icon_star_off);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);
        } else if (value.equals("2.5")) {

            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_on);
            ivstar3.setImageResource(R.mipmap.com_icon_star_half);
            ivstar4.setImageResource(R.mipmap.com_icon_star_off);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);

        } else if (value.equals("3.0")) {

            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_on);
            ivstar3.setImageResource(R.mipmap.com_icon_star_on);
            ivstar4.setImageResource(R.mipmap.com_icon_star_off);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);

        } else if (value.equals("3.5")) {

            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_on);
            ivstar3.setImageResource(R.mipmap.com_icon_star_on);
            ivstar4.setImageResource(R.mipmap.com_icon_star_half);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);


        } else if (value.equals("4.0")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_on);
            ivstar3.setImageResource(R.mipmap.com_icon_star_on);
            ivstar4.setImageResource(R.mipmap.com_icon_star_on);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);
        } else if (value.equals("4.5")) {

            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_on);
            ivstar3.setImageResource(R.mipmap.com_icon_star_on);
            ivstar4.setImageResource(R.mipmap.com_icon_star_on);
            ivstar5.setImageResource(R.mipmap.com_icon_star_half);

        } else if (value.equals("5.0")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_on);
            ivstar3.setImageResource(R.mipmap.com_icon_star_on);
            ivstar4.setImageResource(R.mipmap.com_icon_star_on);
            ivstar5.setImageResource(R.mipmap.com_icon_star_on);
        }




        helper.setText(R.id.tv_salenumber, item.getG_SaleNum() + "").setText(R.id.tv_startnumber,item.getProfessionaldegree_value());











        ivmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击了以后隐藏信息 或者  展示信息
                if (isshowgood) {
                   // showBussnessinfo();
                    llleve.setVisibility(View.VISIBLE);
                    llshop.setVisibility(View.VISIBLE);
                    llscore.setVisibility(View.VISIBLE);
                    llinfo.setVisibility(View.VISIBLE);
                    tvuserlv.setVisibility(View.VISIBLE);
                    tvdizhi.setVisibility(View.VISIBLE);
                    ivavator.setVisibility(View.VISIBLE);




                    ivphoto.setVisibility(View.GONE);
                    tvtag.setVisibility(View.GONE);
                    llcollect.setVisibility(View.GONE);
                    tvidle.setVisibility(View.GONE);
                    tvphototitle.setVisibility(View.GONE);
                    llprice.setVisibility(View.GONE);
                    tvprovice.setVisibility(View.GONE);
                    ivmore.setImageResource(R.mipmap.com_icon_pro_list_corn_x);
                } else {
                    //showGoodinfo();
                    llleve.setVisibility(View.GONE);
                    llshop.setVisibility(View.GONE);
                    llscore.setVisibility(View.GONE);
                    llinfo.setVisibility(View.GONE);
                    tvuserlv.setVisibility(View.GONE);
                    tvdizhi.setVisibility(View.GONE);
                    ivavator.setVisibility(View.GONE);


                    ivphoto.setVisibility(View.VISIBLE);
                    tvtag.setVisibility(View.VISIBLE);
                    llcollect.setVisibility(View.VISIBLE);
                    tvidle.setVisibility(View.VISIBLE);
                    tvphototitle.setVisibility(View.VISIBLE);
                    llprice.setVisibility(View.VISIBLE);
                    tvprovice.setVisibility(View.VISIBLE);
                    ivmore.setImageResource(R.mipmap.com_icon_pro_list_corn);
                }

                isshowgood = !isshowgood;

            }
        });






    }








}
