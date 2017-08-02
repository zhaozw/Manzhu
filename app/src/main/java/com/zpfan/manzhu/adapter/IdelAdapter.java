package com.zpfan.manzhu.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
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
import com.chad.library.adapter.base.BaseQuickAdapter;
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
 * Created by Administrator on 2017/7/12 0012.
 */

public class IdelAdapter extends BaseQuickAdapter<BussnessBean,BaseViewHolder> {


    private boolean isshowgood = true;

    public IdelAdapter(@LayoutRes int layoutResId, @Nullable List<BussnessBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final BussnessBean item) {
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
        final LinearLayout mZu = helper.getView(R.id.ll_zu);
        final LinearLayout mHuan = helper.getView(R.id.ll_huan);
        TextView moreprice = helper.getView(R.id.tv_moreprice);
        TextView change2 = helper.getView(R.id.tv_change2);


        helper.addOnClickListener(R.id.iv_bussness_photo)
                .addOnClickListener(R.id.iv_renzheng)
                .addOnClickListener(R.id.iv_shop)
                .addOnClickListener(R.id.iv_manor)
                .addOnClickListener(R.id.ll_baoyou)
                .addOnClickListener(R.id.ll_fanmai)
                .addOnClickListener(R.id.ll_zu)
                .addOnClickListener(R.id.ll_huan);





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
                    helper.setText(R.id.tv_change1, split[0].substring(1)).setText(R.id.tv_more,"");
                }
                change2.setVisibility(View.GONE);
            } else if (split.length == 2){
                //就显示两个
                helper.setText(R.id.tv_change1, split[0]).setText(R.id.tv_change2,split[1].substring(1)).setText(R.id.tv_more,"");

            } else if (split.length > 2) {
                //还是显示两个  和等
                helper.setText(R.id.tv_change1, split[0]).setText(R.id.tv_change2,split[1].substring(1)).setText(R.id.tv_more,"等");

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

        final BussnessBean.GMemberOBJBean obj = item.getG_Member_OBJ();


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
        helper.setText(R.id.tv_name, obj.getM_Name()).setText(R.id.tv_dizhi,obj.getM_Province() + " - " + obj.getM_City());
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
        final ImageView ivcollect = helper.getView(R.id.iv_collect);

        //检查是否收藏过该商品
        Call<String> iscollection = Aplication.mIinterface.operaisCollection("商品", item.getId() + "", Utils.getloginuid());

        iscollection.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("zc", "onResponse:   看看请求" + call.request().toString());
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
                Log.i("zc", "onItemChildClick:   收藏");
                if (Utils.isUserLogin()) {
                    Call<String> operacollectionfunction = Aplication.mIinterface.operacollectionfunction("商品", item.getId() + "", Utils.getloginuid());

                    operacollectionfunction.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.i("zc", "onResponse:    看看请求的数据"  + call.request().toString());
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
                Log.i("zc", "onItemChildClick:   购物车");
                if (Utils.isUserLogin()) {

                    Call<String> operaaddupdateshopcart = Aplication.mIinterface.operaaddupdateshopcart("", Utils.getloginuid(), item.getG_UID(),"", item.getMember_UID(), "1");

                    operaaddupdateshopcart.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.i("zc", "onResponse:   看看是什么情况i" + call.request().toString());

                            String body = response.body();
                            if (body != null) {
                                Type type = new TypeToken<ArrayList<AvatorBean>>() {
                                }.getType();

                                ArrayList<AvatorBean> been = Utils.gson.fromJson(body, type);
                                if (been != null) {
                                    AvatorBean bean = been.get(0);
                                    Log.i("zc", "onResponse:   看看请求返回的结果" + bean.getRetmsg());
                                    if (bean.getRetmsg().equals("true")) {

                                        MyToast.show("添加到购物车成功", R.mipmap.com_icon_check_w);

                                    } else {
                                        MyToast.show("添加到购物车失败", R.mipmap.com_icon_cross_w);
                                    }


                                }
                            } else {
                                Log.i("zc", "onFailure:   看看错误的地方" + call.request().toString() + "-----"+ response.code());
                            }


                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.i("zc", "onFailure:   看看错误的地方" + call.request().toString());
                        }
                    });





                } else {
                    mContext.startActivity(new Intent(mContext,LoginActivity.class));
                }


            }
        });







    }








}
