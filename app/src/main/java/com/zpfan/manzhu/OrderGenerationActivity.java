package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMMessage;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.OrderGenerationBean;
import com.zpfan.manzhu.myui.EaseActivity;
import com.zpfan.manzhu.utils.PayResult;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderGenerationActivity extends AppCompatActivity {

    private static final int SDK_PAY_FLAG = 30;
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
    @BindView(R.id.tv_retime)
    TextView mTvRetime;
    @BindView(R.id.tv_redetime)
    TextView mTvRedetime;
    @BindView(R.id.ll_allpay)
    LinearLayout mLlAllpay;
    private DecimalFormat mDf;
    private OrderGenerationBean mBean1;
    private PopupWindow mPaywindow;
    private TextView mGoodprice;
    private TextView mTvgoodname;
    private TextView mTvallgoodprice;
    private TextView mTvyue;
    private TextView mTvalipay;
    private TextView mTvyueprice;
    private Button mBtimport;
    private ImageView mIvyue;
    private ImageView mIvalipay;
    private Double mYueprice;
    private boolean isalipay = false;
    private String payuid = "";
    private boolean issetpw = true;
    private String paystr = "";
    private String mfromType;
    private Handler mHandler =  new Handler() {
        public void handleMessage(Message msg) {
            PayResult result = new PayResult((Map<String, String>) msg.obj);

            if (result.getResultStatus().equals("9000")) {
                //说明支付成功
                Intent intent = new Intent(OrderGenerationActivity.this, PayCompleteActivity.class);
                intent.putExtra("orderd", mBean1);
                intent.putExtra("type", mfromType);

                startActivity(intent);
                mPaywindow.dismiss();
                finish();



            }


            Toast.makeText(OrderGenerationActivity.this, result.getMemo(),
                    Toast.LENGTH_LONG).show();
        };
    };



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
        mfromType = intent.getStringExtra("type");
        initPop();

        //发送请求去获取订单的详情
        if (avator.isRet()) {
         payuid = avator.getRetmsg();
            getorderDetil(avator.getRetmsg());
            //发送请求去获取用户的余额
            getuserYue();

        } else {
            //下单失败的界面
            mSvIsret.setVisibility(View.GONE);
            mLlRight.setVisibility(View.VISIBLE);
            mTvFailure.setVisibility(View.VISIBLE);
            mTvFailure.setText("由于" + avator.getRetmsg() + "导致你的订单提交失败");

        }

        if (mfromType.equals("idle")) {
            //闲置的界面
        } else if (mfromType.equals("new")) {
            //新商品的界面
            mTvDetil.setText("在完成支付前，您和卖家均可在【个人中心 - 我买到的（我卖出的）】中修改本订单信息。");
            //最后一个按钮 变成立即支付的按钮  点击以后 执行立即支付的方法
            mBtUsercenter.setText("立即支付");

        } else if (mfromType.equals("server")) {
            //服务的界面
            mTvDetil.setText("由于您当前拍下的是服务，服务商需要先确认是否接单后，您才可进行支付操作（如服务商一周内未确认的，订单会自动取消）。在完成支付前，您和服务商均可在【个人中心 - 我买到的（我卖出的）】中修改本订单信息");

        } else if (mfromType.equals("rent")) {
            //租赁的界面
            mTvDetil.setText("租赁订单的押金支付请在和出租人确认实物后再继续进行。“线上交易”订单租期在租赁人确认收货后开始计算；“线下交易”订单租期在押金支付后即开始计算。完成支付前，您和出租人均可在【个人中心 - 我租到的（我租出的）】中修改本订单信息。");
            mTvYajin.setVisibility(View.VISIBLE);
            ViewGroup.LayoutParams params = mLlGood.getLayoutParams();
            params.height = Utils.dp2px(80);
            mLlGood.setLayoutParams(params);
            mLlRentmoney.setVisibility(View.VISIBLE);
            mBtUsercenter.setText("支付押金");
        } else if (mfromType.equals("yuyue")) {
            mTvDetil.setText("由于您当前拍下的是服务，服务商需要先确认是否接单后，您才可进行支付操作（如服务商一周内未确认的，订单会自动取消）。在完成支付前，您和服务商均可在【个人中心 - 我买到的（我卖出的）】中修改本订单信息");
            mTvRedetime.setVisibility(View.VISIBLE);
            mTvGoodformat.setVisibility(View.GONE);
            mTvRetime.setVisibility(View.VISIBLE);
            if (mBean1 != null) {
                mTvRetime.setText("预约时间：" + mBean1.getO_ReturnDate().substring(0, 10).replace("-", " - "));
                mTvRedetime.setText(mBean1.getO_ExtendLeaseMessage().replace(",", "，"));
            }
        }

        isUserSetTrapw();
        getPayStr();





    }

    private void getPayStr() {

        Call<String> str = Aplication.mIinterface.getOrderalipayStr(payuid, "购物订单", "true", Utils.getloginuid());

        str.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("zc", "onResponse:   看看发送的请求" + call.request().toString());
                String body = response.body();

                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                    if (avatorBeen != null && avatorBeen.size() > 0) {
                        AvatorBean bean = avatorBeen.get(0);

                        Log.i("zc", "onResponse:   看看得到的支付串"+ bean.getRetmsg());
                        paystr = bean.getRetmsg();


                    }




                }




            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });




    }

    /**
     * 获取用户是否设置了交易密码
     */
    private void isUserSetTrapw() {
        Call<String> getmemberoaypassword = Aplication.mIinterface.getmemberoaypassword(Utils.getloginuid());
        getmemberoaypassword.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("zc", "onResponse:   看看数据" + call.request().toString());
                String body = response.body();

                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);

                    if (avatorBeen != null && avatorBeen.size() > 0) {
                        AvatorBean bean = avatorBeen.get(0);
                        if (bean.getRetmsg().equals("False")) {
                            //没有设置交易密码
                            issetpw = false;
                        } else {
                            //有设置交易密码
                            issetpw = true;

                        }


                    }




                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });



    }

    private void getuserYue() {

        Call<String> getmembermoneyintegral = Aplication.mIinterface.getmembermoneyintegral(Utils.getloginuid(), "余额");

        getmembermoneyintegral.enqueue(new Callback<String>() {
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

                        if (bean.isRet()) {
                            //显示余额  如果余额大于要支付的价钱，那么就默认用余额支付， 如果余额小
                            SpannableString yue = new SpannableString("（您当前的余额为：¥ " + retmsg + "）");

                            yue.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.pricetextcolor)), 9, yue.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                            mTvyueprice.setText(yue);
                            mYueprice = Double.valueOf(retmsg);





                        } else {
                            //设置余额为0



                        }


                    }






                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });



    }

    private void getorderDetil(final String retmsg) {
        Call<String> details = Aplication.mIinterface.getordersubmitpaysuccess(retmsg);

        details.enqueue(new Callback<String>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("zc", "onResponse:   看看订单详情的数据" + call.request().toString());
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
                            mTvRetime.setText("预约时间：" + mBean1.getO_ReturnDate().substring(0, 10).replace("-", " - "));
                            mTvRedetime.setText(mBean1.getO_ExtendLeaseMessage().replace(",", "，"));
                            mTvOrdernumber.setText(mBean1.getO_UID());
                            String pattern = mBean1.getO_TradingPattern();
                            mTvBuystyle.setText(pattern);
                            if (pattern.equals("线下交易")) {
                                mTvStyle.setText("（买家付款后卖家即得到货款）");
                            }


                            mTvOrdertime.setText(mBean1.getO_OrderTime().replace("-", " -  "));
                            mTvShopname.setText(mBean1.getStore_CN());
                            mTvgoodname.setText(mBean1.getGoodslist_arry().get(0).getGoods_title());
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
                            mGoodprice.setText(mDf.format(allmoney));
                            mTvallgoodprice.setText(mDf.format(allmoney));
                            mBtimport.setText("确认支付¥ " + mDf.format(allmoney));

                            if (mYueprice != null) {
                                if (mYueprice >= allmoney) {
                                    //余额大于要支付的金额， 就用余额支付

                                    mIvyue.setImageResource(R.mipmap.com_icon_baln);
                                    mTvyue.setTextColor(getResources().getColor(R.color.maintextcolor));
                                    mIvalipay.setImageResource(R.mipmap.com_icon_alipay_ept);
                                    mTvalipay.setTextColor(getResources().getColor(R.color.secondtextcolor));
                                } else {
                                    //余额不够的话  用支付宝 支付超过余额的部分
                                    mIvyue.setImageResource(R.mipmap.com_icon_baln_ept);
                                    mTvyue.setTextColor(getResources().getColor(R.color.secondtextcolor));
                                    mIvalipay.setImageResource(R.mipmap.com_icon_alipay);
                                    mTvalipay.setTextColor(getResources().getColor(R.color.maintextcolor));
                                    mBtimport.setText(mDf.format(allmoney - mYueprice));

                                }
                            }


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
                String s = mBtUsercenter.getText().toString();
                if (s.equals("个人中心")) {
                    //当上面显示的字是个人中心的时候 去个人中心

                    startActivity(new Intent(OrderGenerationActivity.this, UserCenterActivity.class));
                } else if (s.equals("立即支付") || s.equals("支付押金")) {
                    //当字是立即支付的时候  执行立即支付的操作

                    getuserYue();
                    payNow();
                }


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

    private void payNow() {
        WindowManager.LayoutParams lp = OrderGenerationActivity.this.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        OrderGenerationActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        OrderGenerationActivity.this.getWindow().setAttributes(lp);

        mPaywindow.showAtLocation(mBtUsercenter,Gravity.BOTTOM,0,0 );

    }

    private void initPop() {
        mPaywindow = new PopupWindow(OrderGenerationActivity.this);
        View inflate = View.inflate(OrderGenerationActivity.this, R.layout.checkout_counter_pop, null);
        mGoodprice = (TextView) inflate.findViewById(R.id.tv_goodprice);
        mTvgoodname = (TextView) inflate.findViewById(R.id.tv_goodname);
        mTvallgoodprice = (TextView) inflate.findViewById(R.id.tv_allgoodprice);
        mTvyue = (TextView) inflate.findViewById(R.id.tv_yue);
        mTvalipay = (TextView) inflate.findViewById(R.id.tv_alipay);
        mTvyueprice = (TextView) inflate.findViewById(R.id.tv_yueprice);

        mBtimport = (Button) inflate.findViewById(R.id.bt_import);
        final Button  btcancelpay = (Button) inflate.findViewById(R.id.bt_cancelpay);
        final Button btsurepay = (Button) inflate.findViewById(R.id.bt_surepay);


        mIvyue = (ImageView) inflate.findViewById(R.id.iv_yue);
        mIvalipay = (ImageView) inflate.findViewById(R.id.iv_alipay);
        ImageView  ivclean = (ImageView) inflate.findViewById(R.id.iv_clean);

        final LinearLayout llyuepayh = (LinearLayout) inflate.findViewById(R.id.ll_yuepay);
        final LinearLayout llalipay = (LinearLayout) inflate.findViewById(R.id.ll_alipay);
        final LinearLayout llpay = (LinearLayout) inflate.findViewById(R.id.ll_pay);

        final EditText etpassword = (EditText) inflate.findViewById(R.id.rt_password);
        final View strongline = inflate.findViewById(R.id.strongline);
        final TextView tvsetpaypw = (TextView) inflate.findViewById(R.id.tv_setpaypw);
        String source = "您还未设置交易密码，去设置>";
        SpannableString string = new SpannableString(source);
        string.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                //去密码设置的界面
                Log.i("zc", "onClick:  去设置交易密码");
                startActivity(new Intent(OrderGenerationActivity.this,EditPassWordActivity.class));

            }
        },10,source.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        string.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.secondtextcolor)), 10, source.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        string.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.transparent)), 10, source.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvsetpaypw.setText(string);
        tvsetpaypw.setMovementMethod(LinkMovementMethod.getInstance());

        llyuepayh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isalipay = false;
                mIvyue.setImageResource(R.mipmap.com_icon_baln);
                mTvyue.setTextColor(getResources().getColor(R.color.maintextcolor));
                mIvalipay.setImageResource(R.mipmap.com_icon_alipay_ept);
                mTvalipay.setTextColor(getResources().getColor(R.color.secondtextcolor));
            }
        });

        llalipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isalipay = true;
                mIvyue.setImageResource(R.mipmap.com_icon_baln_ept);
                mTvyue.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvalipay.setImageResource(R.mipmap.com_icon_alipay);
                mTvalipay.setTextColor(getResources().getColor(R.color.maintextcolor));

            }
        });


        mBtimport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isalipay) {
                    //走支付宝支付的逻辑  先获取支付的字符串
                    Runnable payRunnable = new Runnable() {

                        @Override
                        public void run() {
                            PayTask alipay = new PayTask(OrderGenerationActivity.this);
                            Log.i("zc", "run:  看看有没有值" + paystr);
                            Map<String,String> result = alipay.payV2(paystr,true);


                            Log.i("zc", "run:  看看发送的数据" +  alipay.toString());

                            Message msg = new Message();
                            msg.what = SDK_PAY_FLAG;
                            msg.obj = result;
                            mHandler.sendMessage(msg);

                        }
                    };
                    // 必须异步调用
                    Thread payThread = new Thread(payRunnable);
                    payThread.start();


                } else {
                    if (issetpw) {
                        //走余额的逻辑
                        llalipay.setVisibility(View.GONE);
                        llyuepayh.setVisibility(View.GONE);
                        //然后展示出密码
                        llpay.setVisibility(View.VISIBLE);
                        mBtimport.setVisibility(View.GONE);
                        btcancelpay.setVisibility(View.VISIBLE);
                        btsurepay.setVisibility(View.VISIBLE);
                    } else {
                        //让用户去设置交易密码
                        llalipay.setVisibility(View.GONE);
                        llyuepayh.setVisibility(View.GONE);

                        tvsetpaypw.setVisibility(View.VISIBLE);
                        strongline.setVisibility(View.GONE);
                    }

                }




            }
        });

        btcancelpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消以后 把之前的信息  展示回来
                llalipay.setVisibility(View.VISIBLE);
                llyuepayh.setVisibility(View.VISIBLE);
                //然后展示出密码
                llpay.setVisibility(View.GONE);
                mBtimport.setVisibility(View.VISIBLE);
                btcancelpay.setVisibility(View.GONE);
                btsurepay.setVisibility(View.GONE);
            }
        });

        btsurepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                String s = etpassword.getText().toString();
                //执行用余额支付的方法
                Call<String> operabalancepay = Aplication.mIinterface.operabalancepay(payuid, "购物订单", s, Utils.getloginuid());

                operabalancepay.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i("zc", "onResponse:   看看发送的访问" + call.request().toString());

                        String body = response.body();
                        if (body != null) {
                            Type type = new TypeToken<ArrayList<AvatorBean>>() {
                            }.getType();

                            ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);

                            if (avatorBeen != null && avatorBeen.size() > 0) {
                                AvatorBean bean = avatorBeen.get(0);
                                String retmsg = bean.getRetmsg();
                                if (retmsg.equals("True")) {
                                    // 跳转到付款成功的界面 ， 然后关掉
                                    Intent intent = new Intent(OrderGenerationActivity.this, PayCompleteActivity.class);
                                    intent.putExtra("orderd", mBean1);
                                    intent.putExtra("type", mfromType);

                                    startActivity(intent);
                                    mPaywindow.dismiss();
                                    finish();

                                    mPaywindow.dismiss();
                                } else if (retmsg.equals("还未设置支付密码")) {
                                    //界面变化为 去设置交易密码的界面
                                    strongline.setVisibility(View.GONE);
                                    tvsetpaypw.setVisibility(View.VISIBLE);

                                }
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });



            }
        });








        mPaywindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.detail_toppop_bg));
        mPaywindow.setTouchable(true);
        mPaywindow.setContentView(inflate);
        mPaywindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPaywindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPaywindow.setOutsideTouchable(true);
        mPaywindow.setFocusable(true);
        mPaywindow.update();

        mPaywindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = OrderGenerationActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                OrderGenerationActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                OrderGenerationActivity.this.getWindow().setAttributes(lp);

            }
        });
    }
}
