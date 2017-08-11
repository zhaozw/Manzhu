package com.zpfan.manzhu.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.Aplication;
import com.zpfan.manzhu.LoginActivity;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.BussnessBean;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/8/11 0011.
 */

public class ShopTuiJianAdapter extends BaseMultiItemQuickAdapter<BussnessBean,BaseViewHolder> {

    private boolean isshowgood = true;
    private boolean isshownewgood = true;
    private boolean isshowphotogood = true;
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ShopTuiJianAdapter(List<BussnessBean> data) {
        super(data);
        addItemType(BussnessBean.NEW, R.layout.item_new);
        addItemType(BussnessBean.IDLE, R.layout.item_idel);
        addItemType(BussnessBean.SERVER, R.layout.item_photo);
    }

    @Override
    protected void convert(BaseViewHolder helper, final BussnessBean item) {
        switch (helper.getItemViewType()) {
            case BussnessBean.NEW:
                final ImageView  mAvator1 = helper.getView(R.id.iv_avator);
                final ImageView  mRenzheng1 = helper.getView(R.id.iv_renzheng);
                final ImageView  ivcollect1 = helper.getView(R.id.iv_collect);
                final ImageView  mShop1 = helper.getView(R.id.iv_shop);
                final ImageView mManor1 = helper.getView(R.id.iv_manor);
                final ImageView   mMore1 = helper.getView(R.id.iv_more);
                final TextView  mName1 = helper.getView(R.id.tv_name);
                final TextView mDizhi1 = helper.getView(R.id.tv_dizhi);
                TextView mBaobeikaopu1 = helper.getView(R.id.tv_baobeikaopu);
                TextView mMaikaopu1 = helper.getView(R.id.tv_maikaopu);
                final TextView  mUserlv1 = helper.getView(R.id.tv_userlv);
                TextView  mZukaopu1 = helper.getView(R.id.tv_zukaopu);
                final RelativeLayout mShopcar1 = helper.getView(R.id.rl_shopcar);
                final RelativeLayout mCollect1 = helper.getView(R.id.rl_collect);
                final LinearLayout    mKaopu1 = helper.getView(R.id.ll_kaopu);
                final LinearLayout  mLlmaikaopu1 = helper.getView(R.id.ll_maikaopu);
                final LinearLayout mLlzukaopu1 = helper.getView(R.id.ll_zukaopu);
                final ImageView  mBussnessphoto1 = helper.getView(R.id.iv_bussness_photo);
                final TextView  mBussnesstag1 = helper.getView(R.id.tv_bussnesstag);
                final TextView mBaoyou1 = helper.getView(R.id.tv_baoyou);
                final TextView  mTitle1 = helper.getView(R.id.tv_title);
                final LinearLayout mFanmai1 = helper.getView(R.id.ll_fanmai);
                final LinearLayout mbrand1 = helper.getView(R.id.ll_brand);
                TextView brandid1 = helper.getView(R.id.tv_brandname);

                helper.addOnClickListener(R.id.iv_bussness_photo)
                        .addOnClickListener(R.id.tv_bussnesstag)
                        .addOnClickListener(R.id.ll_baoyou)
                        .addOnClickListener(R.id.ll_fanmai)
                        .addOnClickListener(R.id.ll_brand);

                //设置商品的封面
                Glide.with(mContext).load(item.getG_Cover()).into(mBussnessphoto1);

                if (item.isG_IsFreeShip()) {
                    mBaoyou1.setVisibility(View.VISIBLE);
                    // mBaoyou.getLayoutParams()

                } else {
                    mBaoyou1.setVisibility(View.GONE);
                }
                helper.setText(R.id.tv_title, item.getG_Title());




                //是不是 要贩卖的商品
                if (item.isG_IsSale()) {
                    ViewGroup.LayoutParams params = mFanmai1.getLayoutParams();
                    params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    mFanmai1.setLayoutParams(params);
                    //设置贩卖的价格
                    String price = item.getG_FixedPrice();
                    String pr = price + "";
                    int index = pr.indexOf(".");
                    String substring = pr.substring(0, index);
                    String decimal = pr.substring(index + 1);


                    helper.setText(R.id.tv_price, substring)
                            .setText(R.id.tv_decimal,"." +decimal);

                } else {
                    ViewGroup.LayoutParams params = mFanmai1.getLayoutParams();
                    params.height = 0;
                    mFanmai1.setLayoutParams(params);
                }



                BussnessBean.GMemberOBJBean obj = item.getG_Member_OBJ();


                //设置头像 圆形
                RequestOptions options = new RequestOptions().centerCrop();

                Glide.with(mContext).asBitmap().load(Utils.imgUrl + obj.getM_Avatar()).apply(options).into(new BitmapImageViewTarget(mAvator1) {
                    @Override
                    protected void setResource(Bitmap resource) {

                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        mAvator1.setImageDrawable(circularBitmapDrawable);

                    }
                });

                //设置店铺的名字
                helper.setText(R.id.tv_name, obj.getM_Name()).setText(R.id.tv_dizhi,obj.getM_Province() + "-" + obj.getM_City());
                //设置性别
                if (obj.getM_Sex().equals("男")) {
                    mManor1.setImageResource(R.mipmap.com_icon_male);
                } else {
                    mManor1.setImageResource(R.mipmap.com_icon_female);
                }

                //设置是否是商家
                if (obj.isM_IsBusiness()) {
                    //是商家
                    mShop1.setImageResource(R.mipmap.type_icon_shop);
                    helper.setText(R.id.tv_name, obj.getM_UserName());
                } else {
                    //是个人用户
                    mShop1.setImageResource(R.mipmap.type_icon_user);
                    helper.setText(R.id.tv_name, obj.getM_UserName());
                }

                //评价星级
                helper.setText(R.id.tv_baobeikaopu, item.getBbkpd_member_value())
                        .setText(R.id.tv_maikaopu, item.getMjkpd_member_value())
                        .setText(R.id.tv_zukaopu, item.getCzrkpd_member_value());


                //设置品牌名
                String id = item.getBrandID();

                if (id.equals("")) {
                    brandid1.setVisibility(View.GONE);
                } else {
                    brandid1.setText(id + "|");
                }
                // helper.setText(R.id.tv_brandname,item.get)


                //设置销量
                helper.setText(R.id.tv_salenumber, item.getG_SaleNum() + "");

                //设置定金  先检查是否支持定金
                if (item.isG_IsDepositDeal()) {
                    //如果支持定金我就显示定金 如果不支持 就把定金那个内容设置位“”
                    helper.setText(R.id.tv_deposit, "（包含定金:¥ " + item.getG_DepositPrice() + "）");

                } else {
                    helper.setText(R.id.tv_deposit, "");
                }








                mMore1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击了以后隐藏信息 或者  展示信息
                        if (isshownewgood) {
                            // showBussnessinfo();
                            mAvator1.setVisibility(View.VISIBLE);
                            mShop1.setVisibility(View.VISIBLE);
                            mManor1.setVisibility(View.VISIBLE);
                            mName1.setVisibility(View.VISIBLE);
                            mDizhi1.setVisibility(View.VISIBLE);
                            mKaopu1.setVisibility(View.VISIBLE);
                            mLlmaikaopu1.setVisibility(View.VISIBLE);
                            mLlzukaopu1.setVisibility(View.VISIBLE);
                            mCollect1.setVisibility(View.VISIBLE);
                            mShopcar1.setVisibility(View.VISIBLE);
                            mUserlv1.setVisibility(View.VISIBLE);



                            mBussnessphoto1.setVisibility(View.GONE);
                            mBussnesstag1.setVisibility(View.GONE);
                            mBaoyou1.setVisibility(View.GONE);
                            mTitle1.setVisibility(View.GONE);
                            mFanmai1.setVisibility(View.GONE);
                            mbrand1.setVisibility(View.GONE);

                            mMore1.setImageResource(R.mipmap.com_icon_pro_list_corn_x);
                        } else {
                            //showGoodinfo();
                            mAvator1.setVisibility(View.GONE);
                            mShop1.setVisibility(View.GONE);
                            mManor1.setVisibility(View.GONE);
                            mName1.setVisibility(View.GONE);
                            mDizhi1.setVisibility(View.GONE);
                            mKaopu1.setVisibility(View.GONE);
                            mLlmaikaopu1.setVisibility(View.GONE);
                            mLlzukaopu1.setVisibility(View.GONE);
                            mCollect1.setVisibility(View.GONE);
                            mShopcar1.setVisibility(View.GONE);
                            mUserlv1.setVisibility(View.GONE);


                            mBussnessphoto1.setVisibility(View.VISIBLE);
                            mBussnesstag1.setVisibility(View.VISIBLE);
                            mBaoyou1.setVisibility(View.VISIBLE);
                            mTitle1.setVisibility(View.VISIBLE);
                            mFanmai1.setVisibility(View.VISIBLE);
                            mbrand1.setVisibility(View.VISIBLE);
                            mMore1.setImageResource(R.mipmap.com_icon_pro_list_corn);
                        }

                        isshownewgood = !isshownewgood;

                    }
                });
                //检查是否收藏过该商品
                Call<String> iscollection = Aplication.mIinterface.operaisCollection("商品", item.getId() + "", Utils.getloginuid());

                iscollection.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        String body = response.body();

                        if (body != null) {
                            Type type = new TypeToken<ArrayList<AvatorBean>>() {
                            }.getType();

                            ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                            if (avatorBeen != null && avatorBeen.size() > 0) {
                                AvatorBean bean = avatorBeen.get(0);
                                String retmsg = bean.getRetmsg();
                                if (retmsg.equals("true")) {
                                    ivcollect1.setImageResource(R.mipmap.com_icon_fav_w);
                                } else if (retmsg.equals("false")) {
                                    ivcollect1.setImageResource(R.mipmap.com_icon_share_ept_w);
                                }
                            }

                        }




                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        ivcollect1.setImageResource(R.mipmap.com_icon_share_ept_w);
                    }
                });
                mCollect1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Utils.isUserLogin()) {
                            Call<String> operacollectionfunction = Aplication.mIinterface.operacollectionfunction("商品", item.getId() + "", Utils.getloginuid());

                            operacollectionfunction.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {

                                    String body1 = response.body();
                                    if (body1 != null) {
                                        Type type2 = new TypeToken<ArrayList<AvatorBean>>() {
                                        }.getType();

                                        ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body1, type2);
                                        if (avatorBeen != null && avatorBeen.size() > 0) {
                                            AvatorBean bean1 = avatorBeen.get(0);
                                            String retmsg1 = bean1.getRetmsg();
                                            notifyDataSetChanged();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });


                        } else {
                            mContext.startActivity(new Intent(mContext,LoginActivity.class));
                        }


                    }
                });






                break;
            case BussnessBean.IDLE:
                final ImageView mAvator = helper.getView(R.id.iv_avator);
                final ImageView  mRenzheng = helper.getView(R.id.iv_renzheng);
                final ImageView  mShop = helper.getView(R.id.iv_shop);
                final ImageView mManor = helper.getView(R.id.iv_manor);
                final ImageView   mMore = helper.getView(R.id.iv_more);
                final TextView mName = helper.getView(R.id.tv_name);
                final TextView mDizhi = helper.getView(R.id.tv_dizhi);
                TextView mBaobeikaopu = helper.getView(R.id.tv_baobeikaopu);
                TextView mMaikaopu = helper.getView(R.id.tv_maikaopu);
                final TextView mUserlv = helper.getView(R.id.tv_userlv);
                TextView  mZukaopu = helper.getView(R.id.tv_zukaopu);
                final RelativeLayout mShopcar = helper.getView(R.id.rl_shopcar);
                final RelativeLayout mCollect = helper.getView(R.id.rl_collect);
                final LinearLayout mKaopu = helper.getView(R.id.ll_kaopu);
                final LinearLayout  mLlmaikaopu = helper.getView(R.id.ll_maikaopu);
                final LinearLayout mLlzukaopu = helper.getView(R.id.ll_zukaopu);
                final ImageView  mBussnessphoto = helper.getView(R.id.iv_bussness_photo);
                final TextView  mBussnesstag = helper.getView(R.id.tv_bussnesstag);
                final TextView mBaoyou = helper.getView(R.id.tv_baoyou);
                final TextView  mTitle = helper.getView(R.id.tv_title);
                final LinearLayout mFanmai = helper.getView(R.id.ll_fanmai);
                final LinearLayout mZu = helper.getView(R.id.ll_zu);
                final LinearLayout mHuan = helper.getView(R.id.ll_huan);
                TextView moreprice = helper.getView(R.id.tv_moreprice);
                TextView change2 = helper.getView(R.id.tv_change2);


                //设置商品的封面
                Glide.with(mContext).load(item.getG_Cover()).into(mBussnessphoto);

                if (item.isG_IsFreeShip()) {
                    mBaoyou.setVisibility(View.VISIBLE);
                    // mBaoyou.getLayoutParams()

                } else {
                    mBaoyou.setVisibility(View.GONE);
                }
                helper.setText(R.id.tv_title, item.getG_Title());

                //是不是要换的商品
                if (item.isG_IsChange()) {
                    ViewGroup.LayoutParams params = mHuan.getLayoutParams();
                    params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    mHuan.setLayoutParams(params);
                    //设置要换的东西
                    String fk = item.getDemand_FK();
                    String[] split = fk.split(",");

                    if (split.length == 1) {
                        //就显示一个
                        if (split[0].length() > 0) {
                            String[] split1 = split[0].split("\\|");
                            helper.setText(R.id.tv_change1, split1[2].substring(1) + " | " + split1[3] ).setText(R.id.tv_more,"");
                        }
                        change2.setVisibility(View.GONE);
                    } else if (split.length == 2){
                        //就显示两个
                        String[] split1 = split[0].split("\\|");
                        String[] split2 = split[1].split("\\|");
                        helper.setText(R.id.tv_change1, split1[2].substring(1) + " | " + split1[3]).setText(R.id.tv_change2,split2[2].substring(1) + " | " + split2[3]).setText(R.id.tv_more,"");

                    } else if (split.length > 2) {
                        //还是显示两个  和等
                        String[] split1 = split[0].split("\\|");
                        String[] split2 = split[1].split("\\|");
                        helper.setText(R.id.tv_change1, split1[2].substring(1) + " | " + split1[3]).setText(R.id.tv_change2,split2[2].substring(1) + " | " + split2[3]).setText(R.id.tv_more,"等");

                    }



                } else {
                    ViewGroup.LayoutParams params = mHuan.getLayoutParams();
                    params.height = 0;
                    mHuan.setLayoutParams(params);
                }



                //是不是 要租的商品
                if (item.isG_IsRent()) {
                    ViewGroup.LayoutParams params = mZu.getLayoutParams();
                    params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    mZu.setLayoutParams(params);
                    //设置租的价格和时间
                    String amount = item.getG_CorrespAmount();
                    String zuprice = null;
                    String zuxiaoshu = null;
                    if (amount.contains(".")) {
                        int i = amount.indexOf(".");
                        zuprice = amount.substring(0, i);
                        zuxiaoshu = amount.substring(i);
                    }

                    String price = item.getG_RenewalPrice();
                    String zudayprice = null;
                    String zudayxiaoshu = null;
                    if (price.contains(".")) {
                        int i = price.indexOf(".");
                        zudayprice = price.substring(0, i);
                        zudayxiaoshu = price.substring(i);
                    }
                    String[] zudayprices = price.split(".");

                    helper.setText(R.id.tv_zuprice, zuprice).setText(R.id.tv_zuxiaoshu, zuxiaoshu)
                            .setText(R.id.tv_zuday, "（" + item.getG_BasicLease() + "天）+ ").setText(R.id.tv_zudayprice, zudayprice)
                            .setText(R.id.tv_zudayxiaoshu, zudayxiaoshu);



                } else {
                    ViewGroup.LayoutParams params = mZu.getLayoutParams();
                    params.height = 0;
                    mZu.setLayoutParams(params);
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

                //是不是 多价格的商品
                if (item.isG_IsMorePrice()) {
                    moreprice.setVisibility(View.VISIBLE);
                } else {
                    moreprice.setVisibility(View.INVISIBLE);
                }

                final BussnessBean.GMemberOBJBean obj1 = item.getG_Member_OBJ();


                //设置头像 圆形
                RequestOptions options1 = new RequestOptions().centerCrop();

                Glide.with(mContext).asBitmap().load(Utils.imgUrl + obj1.getM_Avatar()).apply(options1).into(new BitmapImageViewTarget(mAvator) {
                    @Override
                    protected void setResource(Bitmap resource) {

                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        mAvator.setImageDrawable(circularBitmapDrawable);

                    }
                });

                //设置店铺的名字
                helper.setText(R.id.tv_name, obj1.getM_Name()).setText(R.id.tv_dizhi,obj1.getM_Province() + " - " + obj1.getM_City());
                //设置性别
                if (obj1.getM_Sex().equals("男")) {
                    mManor.setImageResource(R.mipmap.com_icon_male);
                } else {
                    mManor.setImageResource(R.mipmap.com_icon_female);
                }

                //设置是否是商家
                if (obj1.isM_IsBusiness()) {
                    //是商家
                    mShop.setImageResource(R.mipmap.type_icon_shop);
                    helper.setText(R.id.tv_name, obj1.getM_UserName());
                } else {
                    //是个人用户
                    mShop.setImageResource(R.mipmap.type_icon_user);
                    helper.setText(R.id.tv_name, obj1.getM_UserName());
                }

                //评价星级
                helper.setText(R.id.tv_baobeikaopu, item.getBbkpd_member_value())
                        .setText(R.id.tv_maikaopu, item.getMjkpd_member_value())
                        .setText(R.id.tv_zukaopu, item.getCzrkpd_member_value());
                final ImageView ivcollect = helper.getView(R.id.iv_collect);

                //检查是否收藏过该商品
                Call<String> iscollection1 = Aplication.mIinterface.operaisCollection("商品", item.getId() + "", Utils.getloginuid());

                iscollection1.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        String body = response.body();

                        if (body != null) {
                            Type type = new TypeToken<ArrayList<AvatorBean>>() {
                            }.getType();

                            ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                            if (avatorBeen != null && avatorBeen.size() > 0) {
                                AvatorBean bean = avatorBeen.get(0);
                                String retmsg = bean.getRetmsg();
                                if (retmsg.equals("true")) {
                                    ivcollect.setImageResource(R.mipmap.com_icon_fav_w);
                                } else if (retmsg.equals("false")) {
                                    ivcollect.setImageResource(R.mipmap.com_icon_share_ept_w);
                                }
                            }

                        }




                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        ivcollect.setImageResource(R.mipmap.com_icon_share_ept_w);
                    }
                });



                mMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //点击了以后隐藏信息 或者  展示信息
                        if (isshowgood) {
                            // showBussnessinfo();
                            mAvator.setVisibility(View.VISIBLE);
                            mRenzheng.setVisibility(View.VISIBLE);
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
                            mZu.setVisibility(View.GONE);
                            mHuan.setVisibility(View.GONE);
                            mMore.setImageResource(R.mipmap.com_icon_pro_list_corn_x);
                        } else {
                            //showGoodinfo();
                            mAvator.setVisibility(View.GONE);
                            mRenzheng.setVisibility(View.GONE);
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
                            mZu.setVisibility(View.VISIBLE);
                            mHuan.setVisibility(View.VISIBLE);
                            mMore.setImageResource(R.mipmap.com_icon_pro_list_corn);
                        }

                        isshowgood = !isshowgood;

                    }
                });

                //收藏的按钮
                mCollect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("zc", "onClick:  是不是点击了收藏");
                        if (Utils.isUserLogin()) {
                            Call<String> operacollectionfunction = Aplication.mIinterface.operacollectionfunction("商品", item.getId() + "", Utils.getloginuid());

                            operacollectionfunction.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {

                                    String body1 = response.body();
                                    if (body1 != null) {
                                        Type type2 = new TypeToken<ArrayList<AvatorBean>>() {
                                        }.getType();

                                        ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body1, type2);
                                        if (avatorBeen != null && avatorBeen.size() > 0) {
                                            AvatorBean bean1 = avatorBeen.get(0);
                                            String retmsg1 = bean1.getRetmsg();
                                            notifyDataSetChanged();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });


                        } else {
                            mContext.startActivity(new Intent(mContext,LoginActivity.class));
                        }


                    }
                });


                //购物车按钮
                mShopcar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //加入购物车的方法

                        if (Utils.isUserLogin()) {

                            Call<String> operaaddupdateshopcart = Aplication.mIinterface.operaaddupdateshopcart("", Utils.getloginuid(), item.getG_UID(),"", item.getMember_UID(), "1");

                            operaaddupdateshopcart.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {


                                    String body = response.body();
                                    if (body != null) {
                                        Type type = new TypeToken<ArrayList<AvatorBean>>() {
                                        }.getType();

                                        ArrayList<AvatorBean> been = Utils.gson.fromJson(body, type);
                                        if (been != null) {
                                            AvatorBean bean = been.get(0);

                                            if (bean.getRetmsg().equals("true")) {

                                                MyToast.show("添加到购物车成功", R.mipmap.com_icon_check_w);

                                            } else {
                                                MyToast.show("添加到购物车失败", R.mipmap.com_icon_cross_w);
                                            }


                                        }
                                    } else {

                                    }


                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });





                        } else {
                            mContext.startActivity(new Intent(mContext,LoginActivity.class));
                        }


                    }
                });








                break;
            case BussnessBean.SERVER:

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

                //设置点击事件  跳转到详情界面
                helper.addOnClickListener(R.id.iv_photo)
                        .addOnClickListener(R.id.tv_idle)
                        .addOnClickListener(R.id.tv_phototitle)
                        .addOnClickListener(R.id.ll_price)
                        .addOnClickListener(R.id.tv_provice);

                llcollect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //收藏的按钮



                    }
                });


                //设置单位
                helper.setText(R.id.tv_danwei, "/" +item.getServer_unit_string());

                //设置忙还是闲
                TextView idle = helper.getView(R.id.tv_idle);

                boolean deal = item.isG_IsOfflineDeal();
                if (deal) {
                    idle.setText("忙");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        idle.setBackground(mContext.getResources().getDrawable(R.drawable.item_photo_txt1));
                    }
                } else {
                    idle.setText("闲");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        idle.setBackground(mContext.getResources().getDrawable(R.drawable.item_photo_txt));
                    }
                }


                //设置商品的封面
                if (item.getG_Cover() != null && ivphoto!= null) {
                    Glide.with(mContext).load(item.getG_Cover()).into(ivphoto);
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


                BussnessBean.GMemberOBJBean obj2 = item.getG_Member_OBJ();


                //设置头像 圆形
                RequestOptions options2 = new RequestOptions().centerCrop();

                Glide.with(mContext).asBitmap().load(Utils.imgUrl + obj2.getM_Avatar()).apply(options2).into(new BitmapImageViewTarget(ivavator) {
                    @Override
                    protected void setResource(Bitmap resource) {

                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        ivavator.setImageDrawable(circularBitmapDrawable);

                    }
                });

                //设置店铺的名字
                helper.setText(R.id.tv_name, obj2.getM_Name()).setText(R.id.tv_dizhi,obj2.getM_Province() + "-" + obj2.getM_City());
                //设置性别
                if (obj2.getM_Sex().equals("男")) {
                    ivmanor.setImageResource(R.mipmap.com_icon_male);
                } else {
                    ivmanor.setImageResource(R.mipmap.com_icon_female);
                }

                //设置是否是商家
                if (obj2.isM_IsBusiness()) {
                    //是商家
                    ivshop.setImageResource(R.mipmap.type_icon_shop);
                    helper.setText(R.id.tv_name, obj2.getM_UserName());
                } else {
                    //是个人用户
                    ivshop.setImageResource(R.mipmap.type_icon_user);
                    helper.setText(R.id.tv_name, obj2.getM_UserName());
                }

                //设置地址
                helper.setText(R.id.tv_provice, item.getG_Province() + "-" + item.getG_City());

                //设置靠谱度
                helper.setText(R.id.tv_pro, item.getProfessionaldegree_value())
                        .setText(R.id.tv_server, item.getAttitude_number_value())
                        .setText(R.id.tv_finishspeed, item.getComplatespeed_value());

                //设置评级
                String value = item.getProfessionaldegree_value();

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
                        if (isshowphotogood) {
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

                        isshowphotogood = !isshowphotogood;

                    }
                });





                break;

        }


    }
}
