package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.adapter.ShopcarCheckoutAdapter;
import com.zpfan.manzhu.adapter.ShopcartOrderGeneraAdapter;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.OrderGenerationBean;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.PayResult;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopCartOrderGeneraActivity extends AppCompatActivity {

    private static final int SDK_PAY_FLAG = 50;
    @BindView(R.id.ll_right)
    LinearLayout mLlRight;
    @BindView(R.id.tv_failure)
    TextView mTvFailure;
    @BindView(R.id.rv_shopcartgenera)
    RecyclerView mRvShopcartgenera;
    private ArrayList<OrderGenerationBean> mGenerationBeen;
    private boolean isfinish = false;
    private DecimalFormat mDf;
    private String mType;
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
    private boolean isalipay;
    private String paystr;
    private Handler mHandler =  new Handler() {
        public void handleMessage(Message msg) {
            PayResult result = new PayResult((Map<String, String>) msg.obj);

            if (result.getResultStatus().equals("9000")) {
                //说明支付成功
                Intent intent = new Intent(ShopCartOrderGeneraActivity.this, PayCompleteActivity.class);
                intent.putExtra("orderd", mGenerationBeen);
                intent.putExtra("type", mType);

                startActivity(intent);
                mPaywindow.dismiss();
                finish();



            }


            Toast.makeText(ShopCartOrderGeneraActivity.this, result.getMemo(),
                    Toast.LENGTH_LONG).show();
        };
    };
    private boolean issetpw;
    private String payuid;
    private Double mYueprice;
    private TextView mBtusercenter;
    private ShopcarCheckoutAdapter mAdapter;
    private Double mAllPay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart_order_genera);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        AvatorBean avator = intent.getParcelableExtra("avator");
        mType = intent.getStringExtra("type");






        mDf = new DecimalFormat("0.00");
        if (avator.isRet()) {
            //下单成功
            mRvShopcartgenera.setLayoutManager(new LinearLayoutManager(this));
            //发送请求去获取数据
            payuid = avator.getRetmsg();
            getorderDetil(payuid);


        } else {
            //下单失败
            mLlRight.setVisibility(View.VISIBLE);
            mTvFailure.setVisibility(View.VISIBLE);
            mTvFailure.setText("由于" + avator.getRetmsg() + "原因导致下单失败");

        }
        initPop();
        getuserYue();
        getPayStr();
        isUserSetTrapw();


    }

    private void getPayStr() {

        Call<String> str = Aplication.mIinterface.getOrderalipayStr(payuid, "购物订单", "true", Utils.getloginuid());

        str.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String body = response.body();

                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                    if (avatorBeen != null && avatorBeen.size() > 0) {
                        AvatorBean bean = avatorBeen.get(0);


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

    private void getorderDetil(final String retmsg) {
        Call<String> details = Aplication.mIinterface.getordersubmitpaysuccess(retmsg);

        details.enqueue(new Callback<String>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

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
                            mGenerationBeen = Utils.gson.fromJson(substring, type1);
                            mAdapter.setNewData(mGenerationBeen);
                            ShopcartOrderGeneraAdapter adapter = new ShopcartOrderGeneraAdapter(R.layout.item_shopcartorder, mGenerationBeen);
                            View headview = View.inflate(ShopCartOrderGeneraActivity.this, R.layout.shopcart_ordergenera_head, null);
                            initHeadView(headview);
                            View footView = View.inflate(ShopCartOrderGeneraActivity.this, R.layout.shopcart_ordergenera_foot, null);
                            initFootView(footView,mGenerationBeen);
                            adapter.addHeaderView(headview);
                            adapter.addFooterView(footView);
                            mRvShopcartgenera.setAdapter(adapter);
                        }


                    }

                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void initHeadView(View headview) {
        TextView btbackhome = (TextView) headview.findViewById(R.id.bt_backhome);
        TextView btbackgood = (TextView) headview.findViewById(R.id.bt_backgood);
        mBtusercenter = (TextView) headview.findViewById(R.id.bt_usercenter);
        TextView tvdetile = (TextView) headview.findViewById(R.id.tv_detile);

        if (mType.equals("idle")) {
            //说明是闲置物品
            tvdetile.setText("由于您当前拍下的是闲置物品，卖家需要先确认当前是否有货后，您才可进行支付操作（如卖家24小时内未确认的，订单会自动取消）。在完成支付前，您和卖家均可在【个人中心 - 我买到的（我卖出的）】中修改本订单信息。");

        } else if (mType.equals("new")) {
            //说明是新商品
            mBtusercenter.setText("立即支付");
            tvdetile.setText("在完成支付前，您和卖家均可在【个人中心 - 我买到的（我卖出的）】中修改本订单信息。");


        } else if (mType.equals("server")) {
            //说明是服务
            tvdetile.setText("由于您当前拍下的是服务，服务商需要先确认是否接单后，您才可进行支付操作（如服务商一周内未确认的，订单会自动取消）。在完成支付前，您和服务商均可在【个人中心 - 我买到的（我卖出的）】中修改本订单信息");
        }


        btbackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopCartOrderGeneraActivity.this,MainActivity.class));
            }
        });

        btbackgood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopCartOrderGeneraActivity.this,IdleActivity.class));
                finish();
            }
        });

        final String bt = mBtusercenter.getText().toString();

        mBtusercenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bt.equals("个人中心")) {

                    startActivity(new Intent(ShopCartOrderGeneraActivity.this, UserCenterActivity.class));
                } else if (bt.equals("立即支付")) {
                    getuserYue();
                    payNow();



                }
            }
        });




    }

    private void payNow() {
        WindowManager.LayoutParams lp = ShopCartOrderGeneraActivity.this.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        ShopCartOrderGeneraActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ShopCartOrderGeneraActivity.this.getWindow().setAttributes(lp);

        mPaywindow.showAtLocation(mBtusercenter, Gravity.BOTTOM,0,0 );

    }

    private void initPop() {
        mPaywindow = new PopupWindow(ShopCartOrderGeneraActivity.this);
        final View inflate = View.inflate(ShopCartOrderGeneraActivity.this, R.layout.shopcar_checkout_counter_pop, null);
        RecyclerView rvshopcar = (RecyclerView) inflate.findViewById(R.id.rv_shopcartcheckout);
        rvshopcar.setLayoutManager(new LinearLayoutManager(ShopCartOrderGeneraActivity.this));

        mAdapter = new ShopcarCheckoutAdapter(R.layout.item_shopcart_checkout, mGenerationBeen);
        rvshopcar.setAdapter(mAdapter);
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
                startActivity(new Intent(ShopCartOrderGeneraActivity.this,EditPassWordActivity.class));

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
                            PayTask alipay = new PayTask(ShopCartOrderGeneraActivity.this);
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
                //获取到用户的余额  和 总价对比
                if (mYueprice > mAllPay) {
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
                                        MyToast.show("恭喜你，付款成功", R.mipmap.com_icon_check_w);
                                        // 跳转到付款成功的界面 ， 然后关掉
                                        Intent intent = new Intent(ShopCartOrderGeneraActivity.this, PayCompleteActivity.class);
                                        intent.putParcelableArrayListExtra("carorderd", mGenerationBeen);
                                        intent.putExtra("type", mType);
                                        intent.putExtra("iscar", true);

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
                } else {
                    MyToast.show("您的余额不足支付订单金额，请选择支付宝付款",R.mipmap.com_icon_cross_w);

                }


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
                WindowManager.LayoutParams lp = ShopCartOrderGeneraActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                ShopCartOrderGeneraActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                ShopCartOrderGeneraActivity.this.getWindow().setAttributes(lp);

            }
        });
    }

    private void getuserYue() {

        Call<String> getmembermoneyintegral = Aplication.mIinterface.getmembermoneyintegral(Utils.getloginuid(), "余额");

        getmembermoneyintegral.enqueue(new Callback<String>() {
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

                        if (bean.isRet()) {
                            //显示余额  如果余额大于要支付的价钱，那么就默认用余额支付， 如果余额小
                            if (retmsg == null) {
                                retmsg = "";
                            }
                            SpannableString yue = new SpannableString("（您当前的余额为：¥ " + retmsg + "）");

                            if (retmsg != null) {
                                yue.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.pricetextcolor)), 9, yue.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            }

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


    private void initFootView(View footView, ArrayList<OrderGenerationBean> generationBeen) {
        TextView tvallpay = (TextView) footView.findViewById(R.id.tv_allpay);
        TextView tvjifen = (TextView) footView.findViewById(R.id.tv_jifen);
        Double allpay = 0.00;
        double alljifen = 0;

        for (OrderGenerationBean bean : generationBeen) {
            allpay = allpay + Double.valueOf(bean.getO_PayMoney());
            alljifen =  alljifen + Double.valueOf(bean.getO_DeductionIntegralMoney()) ;
        }

        String s1 = alljifen + "";

        String s = allpay + "";
        mAllPay = Double.valueOf(s);
        Double aDouble1 = Double.valueOf(s1);


        tvallpay.setText(mDf.format(mAllPay));
         mTvallgoodprice.setText(" ¥ " +mDf.format(mAllPay));
        mBtimport.setText("确认支付 ¥ " + mDf.format(mAllPay));
        tvjifen.setText(mDf.format(aDouble1));
    }
}
