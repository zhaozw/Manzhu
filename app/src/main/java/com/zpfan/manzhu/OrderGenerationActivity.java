package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMMessage;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.OrderGenerationBean;
import com.zpfan.manzhu.myui.EaseActivity;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderGenerationActivity extends AppCompatActivity {

    @BindView(R.id.bt_backhome)
    TextView mBtBackhome;
    @BindView(R.id.bt_backgood)
    TextView mBtBackgood;
    @BindView(R.id.bt_usercenter)
    TextView mBtUsercenter;
    @BindView(R.id.tv_ordernumber)
    TextView mTvOrdernumber;
    @BindView(R.id.ll_getorderdetil)
    LinearLayout mLlGetorderdetil;
    @BindView(R.id.tv_buystyle)
    TextView mTvBuystyle;
    @BindView(R.id.tv_style)
    TextView mTvStyle;
    @BindView(R.id.tv_ordertime)
    TextView mTvOrdertime;
    @BindView(R.id.tv_shopname)
    TextView mTvShopname;
    @BindView(R.id.tv_userlv)
    TextView mTvUserlv;
    @BindView(R.id.iv_message)
    ImageView mIvMessage;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;
    @BindView(R.id.iv_shopcover)
    RoundedImageView mIvShopcover;
    @BindView(R.id.tv_goodtitle)
    TextView mTvGoodtitle;
    @BindView(R.id.tv_goodformat)
    TextView mTvGoodformat;
    @BindView(R.id.tv_goodcount)
    TextView mTvGoodcount;
    @BindView(R.id.tv_goodprice)
    TextView mTvGoodprice;
    @BindView(R.id.tv_yunfei)
    TextView mTvYunfei;
    @BindView(R.id.tv_youhui)
    TextView mTvYouhui;
    @BindView(R.id.tv_jifen)
    TextView mTvJifen;
    @BindView(R.id.tv_allpay)
    TextView mTvAllpay;
    @BindView(R.id.tv_failure)
    TextView mTvFailure;
    @BindView(R.id.sv_isret)
    ScrollView mSvIsret;
    @BindView(R.id.ll_right)
    LinearLayout mLlRight;
    @BindView(R.id.tv_detil)
    TextView mTvDetil;
    @BindView(R.id.tv_yajin)
    TextView mTvYajin;
    @BindView(R.id.ll_good)
    LinearLayout mLlGood;
    @BindView(R.id.ll_rentmoney)
    LinearLayout mLlRentmoney;
    private DecimalFormat mDf;
    private OrderGenerationBean mBean1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_generation);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        mDf = new DecimalFormat("0.00");

        AvatorBean avator = intent.getParcelableExtra("avator");
        String type = intent.getStringExtra("type");
        if (type.equals("idle")) {
            //闲置的界面
        } else if (type.equals("new")) {
            //新商品的界面
            mTvDetil.setText("在完成支付前，您和卖家均可在【个人中心 - 我买到的（我卖出的）】中修改本订单信息。");

        } else if (type.equals("server")) {
            //服务的界面

        } else if (type.equals("rent")) {
            //租赁的界面
            mTvDetil.setText("租赁订单的押金支付请在和出租人确认实物后再继续进行。“线上交易”订单租期在租赁人确认收货后开始计算；“线下交易”订单租期在押金支付后即开始计算。完成支付前，您和出租人均可在【个人中心 - 我租到的（我租出的）】中修改本订单信息。");
            mTvYajin.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams params = mLlGood.getLayoutParams();
            params.height = Utils.dp2px(80);
            mLlGood.setLayoutParams(params);
            mLlRentmoney.setVisibility(View.VISIBLE);
        }


        //发送请求去获取订单的详情
        if (avator.isRet()) {
            getorderDetil(avator.getRetmsg());
        } else {
            //下单失败的界面
            mSvIsret.setVisibility(View.GONE);
            mLlRight.setVisibility(View.VISIBLE);
            mTvFailure.setVisibility(View.VISIBLE);
            mTvFailure.setText("由于" + avator.getRetmsg() + "导致你的订单提交失败");

        }


    }

    private void getorderDetil(final String retmsg) {
        Call<String> details = Aplication.mIinterface.getordersubmitpaysuccess(retmsg);

        details.enqueue(new Callback<String>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("zc", "onResponse:   看看订单详情的数据"  + call.request().toString());
                String body = response.body();

                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                    if (avatorBeen != null && avatorBeen.size() > 0) {
                        AvatorBean bean = avatorBeen.get(0);
                        String retmsg1 = bean.getRetmsg();

                        if (retmsg1.contains("[")) {

                            String substring = retmsg1.substring(1, retmsg1.lastIndexOf("]"));

                            Type type1 = new TypeToken<ArrayList<OrderGenerationBean>>() {
                            }.getType();

                            ArrayList<OrderGenerationBean> generationBeen = Utils.gson.fromJson(substring, type1);
                            mBean1 = generationBeen.get(0);

                            mTvOrdernumber.setText(mBean1.getO_UID());
                            String pattern = mBean1.getO_TradingPattern();
                            mTvBuystyle.setText(pattern);
                            if (pattern.equals("线下交易")) {
                                mTvStyle.setText("（买家付款后卖家即得到货款）");
                            }

                            mTvOrdertime.setText(mBean1.getO_OrderTime().replace("-", " -  "));
                            mTvShopname.setText(mBean1.getStore_CN());
                            mTvUserlv.setText("Lv." + mBean1.getStore_Level());
                            OrderGenerationBean.GoodslistArryBean goodslistArryBean = mBean1.getGoodslist_arry().get(0);
                            mTvGoodtitle.setText(goodslistArryBean.getGoods_title());
                            mTvGoodformat.setText(goodslistArryBean.getGoods_specification());
                            mTvGoodcount.setText(goodslistArryBean.getGoods_count() + "");
                            mTvGoodprice.setText(goodslistArryBean.getGoods_money());
                            Glide.with(OrderGenerationActivity.this).load(goodslistArryBean.getGoods_images()).into(mIvShopcover);
                            String s = mBean1.getO_CouponMoney() + "";
                            Double aDouble = Double.valueOf(s);

                            String format = mDf.format(aDouble);
                            mTvYouhui.setText(format);

                            String yun = mBean1.getO_GoodsFreight() + "";
                            Double yunf = Double.valueOf(yun);

                            mTvYunfei.setText(mDf.format(yunf));

                            String jifen = mBean1.getO_DeductionIntegralMoney() + "";
                            Double jif = Double.valueOf(jifen);
                            mTvJifen.setText(mDf.format(jif));

                            String all = mBean1.getO_PayMoney() + "";
                            Double allmoney = Double.valueOf(all);

                            mTvAllpay.setText(mDf.format(allmoney));

                        }


                    }

                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    @OnClick({R.id.bt_backhome, R.id.bt_backgood, R.id.bt_usercenter, R.id.ll_message, R.id.ll_getorderdetil})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_backhome:

                startActivity(new Intent(OrderGenerationActivity.this, MainActivity.class));
                break;
            case R.id.bt_backgood:
                startActivity(new Intent(OrderGenerationActivity.this, IdleActivity.class));
                break;
            case R.id.bt_usercenter:
                startActivity(new Intent(OrderGenerationActivity.this, UserCenterActivity.class));

                break;
            case R.id.ll_message:
                Intent intent = new Intent(OrderGenerationActivity.this, EaseActivity.class);
                intent.putExtra("userId", mBean1.getStore_Phone());
                intent.putExtra("usercn", mBean1.getStore_CN());
                intent.putExtra("chatType", EMMessage.ChatType.Chat);
                startActivity(intent);


                break;
            case R.id.ll_getorderdetil:


                break;
        }
    }
}
