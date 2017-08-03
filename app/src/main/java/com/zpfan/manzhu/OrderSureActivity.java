package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.adapter.OrderSureAdapter;
import com.zpfan.manzhu.bean.AddressBean;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.FormatBean;
import com.zpfan.manzhu.bean.ShopCartbean;
import com.zpfan.manzhu.utils.EditListener;
import com.zpfan.manzhu.utils.SPUtils;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderSureActivity extends AppCompatActivity {

    private static final int REQUESY_LOCATINO = 5;
    @BindView(R.id.rv_ordersure)
    RecyclerView mRvOrdersure;
    @BindView(R.id.ll_importorder)
    LinearLayout mLlImportorder;
    @BindView(R.id.tv_pay)
    TextView mTvPay;
    private TextView mTvaddrname;
    private TextView mTvaddrlocation;
    private TextView mTvphone;
    private View mFootView;
    private ArrayList<ShopCartbean.CarshoplistBean> mMorderlist;
    private double allprice = 0;
    private double yunfeni = 0;
    private double youhui = 0;
    private int allnumber = 0;
    private OrderSureAdapter mAdapter;
    private ArrayList<AddressBean> mAddressBeen;

    private TextView mTvuserjifen;
    private Integer mJifen;
    private DecimalFormat mDf;
    private TextView mTvjifen;
    private TextView mTvallprice;
    private TextView mTvyunfei;
    private TextView mTvyouhuijuan;
    private TextView mTvpayprice;
    private String locationid = "";
    private EditText mEtjifen;
    private String mBuystyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_sure);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        mDf = new DecimalFormat("0.00");


        String usesheng = SPUtils.getInstance().getString("usesheng", "");
        View headview = View.inflate(this, R.layout.rv_order_sure_head, null);
        mRvOrdersure.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");





        if (type.equals("sopcart")) {
            mMorderlist = intent.getParcelableArrayListExtra("shopcat");

            for (ShopCartbean.CarshoplistBean bean : mMorderlist) {
                double allweight = 0;
                String uid = "";
                List<ShopCartbean.CarshoplistBean.CargoodslistBean> cargoodslist = bean.getCheckgoodslist();
                if (cargoodslist.size() > 0) {
                    ShopCartbean.CarshoplistBean.CargoodslistBean bean1 = cargoodslist.get(0);
                    for (ShopCartbean.CarshoplistBean.CargoodslistBean cargoodslistBean : cargoodslist) {
                        allweight = allweight + cargoodslistBean.getGoods_model().getG_Weight() * cargoodslistBean.getCarCount();
                        uid = uid +"," + bean1.getGoods_UID();
                    }
                    getbuyStyle(usesheng,mDf.format(allweight),uid,bean1.getGoods_model().getG_Province(),bean);
                }


            }


            mAdapter = new OrderSureAdapter(R.layout.item_ordersure, mMorderlist, new EditListener() {
                @Override
                public void edit(ArrayList<ShopCartbean.CarshoplistBean.CargoodslistBean> checeGood) {
                    initFootView();
                }
            });
            mFootView = View.inflate(this, R.layout.order_sure_foot, null);
            initFootView();
            mTvaddrname = (TextView) headview.findViewById(R.id.tv_addrname);
            mTvaddrlocation = (TextView) headview.findViewById(R.id.tv_addrlocation);
            mTvphone = (TextView) headview.findViewById(R.id.tv_phone);

            getLocation(Utils.getloginuid());

            headview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mAddressBeen != null) {
                        Intent locationintent = new Intent(OrderSureActivity.this, OrderLocationActivity.class);
                        locationintent.putParcelableArrayListExtra("location", mAddressBeen);
                        startActivityForResult(locationintent, REQUESY_LOCATINO);

                    }
                }
            });


            mAdapter.addHeaderView(headview);
            mAdapter.addFooterView(mFootView);
            mRvOrdersure.setAdapter(mAdapter);




            mLlImportorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Map<String, String>  mMap = new LinkedHashMap<>();
                    mMap.put("member_uid", Utils.getloginuid());
                    mMap.put("Count", "0");
                    mMap.put("goods_uid", "");
                    mMap.put("goods_ps_uid", "");
                    mMap.put("goods_id_btn", "");
                    mMap.put("Finally_Address_ID", locationid);
                    mMap.put("liuyan_m", " ");
                    mMap.put("CarOrBuy", "Car");
                    mMap.put("OrderCate", "购物订单");
                    mMap.put("liuyan_m", "");
                    mMap.put("shopcoupon_bymemberlist", "");
                    String jifen = mEtjifen.getText().toString();
                    mMap.put("deduction_buy_score_number", jifen);
                    mMap.put("see_sell_my_data_mm", "");
                    mMap.put("share_uid", "");
                    mMap.put("appointment_date", "2017-07-31");
                    mMap.put("appointment_time", "");
                    mMap.put("yunfei_value_model", "");
                    mMap.put("trading_value_model", "");
                    mMap.put("edit_goods_single_price_model", "");

                    for (ShopCartbean.CarshoplistBean bean : mMorderlist) {
                        List<ShopCartbean.CarshoplistBean.CargoodslistBean> checkgoodslist = bean.getCheckgoodslist();
                        for (ShopCartbean.CarshoplistBean.CargoodslistBean cargoodslistBean : checkgoodslist) {
                            String btn = mMap.get("goods_id_btn");
                            if (!btn.isEmpty()) {
                                mMap.put("goods_id_btn", btn + "," + cargoodslistBean.getSC_UID());
                            } else {
                                mMap.put("goods_id_btn",  cargoodslistBean.getSC_UID());
                            }

                            //添加编辑的价格
                            String edit = mMap.get("edit_goods_single_price_model");
                            if (edit.isEmpty()) {
                                mMap.put("edit_goods_single_price_model", cargoodslistBean.getEditMoney());
                            } else {
                                mMap.put("edit_goods_single_price_model",edit+ "," + cargoodslistBean.getEditMoney());
                            }



                        }
                        //添加留言
                        String liu = mMap.get("liuyan_m");
                        if (!liu.isEmpty()) {
                            mMap.put("liuyan_m", liu + "," + bean.getLiuyan());
                        } else {
                            mMap.put("liuyan_m", bean.getLiuyan());
                        }
                        //添加优惠劵
                        String bymemberlist = mMap.get("shopcoupon_bymemberlist");
                        if (!bean.getCouponid().equals("")) {
                            if (!bymemberlist.isEmpty()) {
                                mMap.put("shopcoupon_bymemberlist", bymemberlist + "," + bean.getCouponid());
                            } else {
                                mMap.put("shopcoupon_bymemberlist",  bean.getCouponid());
                            }
                        }
                        //添加交易方式
                        String model = mMap.get("trading_value_model");
                        if (model.isEmpty()) {
                            mMap.put("trading_value_model", bean.getJiaoyi());
                        } else {
                            mMap.put("trading_value_model", model+ "," + bean.getJiaoyi());
                        }

                    }

                    String number = mMap.get("deduction_buy_score_number");
                    if (number.isEmpty()) {
                        mMap.put("deduction_buy_score_number", "0");
                    }

                    //发送请求去提交订单
                    Call<String> orderSubmit = Aplication.mIinterface.orderSubmit(mMap);

                    orderSubmit.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            String body = response.body();
                            if (body != null) {
                                Type type1 = new TypeToken<ArrayList<AvatorBean>>() {
                                }.getType();

                                ArrayList<AvatorBean> list = Utils.gson.fromJson(body, type1);

                                if (list != null && list.size() > 0){
                                    AvatorBean bean = list.get(0);

                                    Intent orderIntent = new Intent(OrderSureActivity.this,ShopCartOrderGeneraActivity.class);
                                    orderIntent.putExtra("avator", bean);
                                    orderIntent.putExtra("type", "idle");
                                    startActivity(orderIntent);
                                    finish();
                                }



                            }

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });



                }
            });
        }

    }

    private void initFootView() {
        allprice = 0;
        yunfeni = 0;
        allnumber = 0;
        youhui = 0;
        TextView tvallcount = (TextView) mFootView.findViewById(R.id.tv_allcount);
        mTvallprice = (TextView) mFootView.findViewById(R.id.tv_allprice);
        mTvyunfei = (TextView) mFootView.findViewById(R.id.tv_yunfei);
        mTvyouhuijuan = (TextView) mFootView.findViewById(R.id.tv_youhuijuan);
        mTvjifen = (TextView) mFootView.findViewById(R.id.tv_jifen);
        mTvpayprice = (TextView) mFootView.findViewById(R.id.tv_payprice);
        mTvuserjifen = (TextView) mFootView.findViewById(R.id.tv_userjifen);
        mEtjifen = (EditText) mFootView.findViewById(R.id.et_jifen);
        View dashinle = mFootView.findViewById(R.id.dashline1);
        dashinle.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        getUserIntegral();


        for (ShopCartbean.CarshoplistBean bean : mMorderlist) {

            yunfeni = yunfeni + bean.getYunfei();
            youhui = youhui + Double.valueOf(bean.getCoupon());


            for (ShopCartbean.CarshoplistBean.CargoodslistBean cargoodslistBean : bean.getCheckgoodslist()) {

                allnumber = allnumber + 1 * cargoodslistBean.getCarCount();
                String uid = cargoodslistBean.getGoods_Spcification_UID();

                if (uid.equals("")) {
                    allprice = allprice + Double.valueOf(cargoodslistBean.getGoods_model().getG_FixedPrice()) * cargoodslistBean.getCarCount();
                } else {
                    List<FormatBean> specifications = cargoodslistBean.getGoods_model().getGoods_specifications();

                    for (FormatBean specification : specifications) {
                        if (specification.getPS_UniqueID().equals(uid)) {
                            allprice = allprice + Double.valueOf(specification.getPS_FixedPrice()) * cargoodslistBean.getCarCount();
                        }
                    }


                }


            }
        }

        tvallcount.setText(allnumber + "");


        mTvallprice.setText(mDf.format(allprice));
        //运费的计算
        mTvyunfei.setText(mDf.format(yunfeni));
        //优惠劵的金额
        mTvyouhuijuan.setText(mDf.format(youhui));
        String s = mTvjifen.getText().toString();
        final Double jifen = Double.valueOf(s);

        //积分折扣的钱
        double endmoney = allprice + yunfeni - youhui - jifen;

        mTvpayprice.setText(mDf.format(endmoney));
        mTvPay.setText(mDf.format(endmoney));

        mEtjifen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = s.toString(); // 获取到用户输入的值
                String s2 = mEtjifen.getText().toString();
                mEtjifen.setSelection(s2.length());
                if (s1.length() > 0) {
                    Integer integer = Integer.valueOf(s1);  //用户输入的值
                    if (integer > mJifen) {
                        integer = mJifen;
                        mEtjifen.setText(mJifen + "");
                    }
                    if (integer > allprice * 100) {
                        integer = (int)allprice;
                        mEtjifen.setText(integer + "");
                    }
                    String s3 = integer + "";
                    Double aDouble = Double.valueOf(s3);
                    mTvjifen.setText(mDf.format(aDouble / 100));


                } else if (s1.length() == 0){
                    mEtjifen.setText(0+ "");
                    mTvjifen.setText("0.00");
                }


                jisuanMoney();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void getUserIntegral() {

        Call<String> getmemberintegral = Aplication.mIinterface.getmembermoneyintegral(Utils.getloginuid(), "积分");

        getmemberintegral.enqueue(new Callback<String>() {
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
                        if (retmsg != null) {
                            mTvuserjifen.setText("（可用积分 " +retmsg + "）");
                            Integer aDouble = Integer.valueOf(retmsg);
                            mJifen = aDouble;
                        }



                    }




                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void getLocation(String getloginuid) {
        //发送网络请求去获取地址 如果没有地址 跳转到新增地址的界面
        Call<String> getshippingaddr = Aplication.mIinterface.getshippingaddr(getloginuid);

        getshippingaddr.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();

                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> been = Utils.gson.fromJson(body, type);

                    if (been != null) {
                        String retmsg = been.get(0).getRetmsg();

                        if (retmsg.length() > 4 && retmsg.contains("[")) {
                            String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));

                            Type type1 = new TypeToken<ArrayList<AddressBean>>() {
                            }.getType();

                            mAddressBeen = Utils.gson.fromJson(substring, type1);

                            if (mAddressBeen.size() > 0) {
                                for (AddressBean bean : mAddressBeen) {
                                    if (bean.isMD_IsDefault()) {
                                        mTvaddrname.setText("收货人：" + bean.getMD_Name());
                                        mTvphone.setText(bean.getMD_Phone());
                                        mTvaddrlocation.setText("收货地址：" +bean.getMD_Province() + bean.getMD_City() + bean.getMD_Area() + bean.getMD_Address());
                                        locationid = bean.getId() + "";
                                    }

                                }
                            }


                        } else if (retmsg.length() == 0) {
                            // TODO: 2017/7/28 0028  没有地址 去添加地址


                        }


                    }


                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        mAdapter.notifyDataSetChanged();
    }


    protected  void jisuanMoney(){
        String s = mTvallprice.getText().toString();
        Double allprice = Double.valueOf(s);

        String yun = mTvyunfei.getText().toString();
        Double yunfei = Double.valueOf(yun);

        String you = mTvyouhuijuan.getText().toString();
        Double youhui = Double.valueOf(you);

        String jife = mTvjifen.getText().toString();
        Double jifen = Double.valueOf(jife);

        double pay = allprice + yunfei - youhui - jifen;

        mTvpayprice.setText(mDf.format(pay));
        mTvPay.setText(mDf.format(pay));
    }

    private void getbuyStyle(String usesheng, String format, String uid, String bussnesssheng, final ShopCartbean.CarshoplistBean item) {

        Call<String> getorderbydealstyle = Aplication.mIinterface.getorderbydealstyle("Car", "二手商品", Utils.getloginuid(),bussnesssheng, usesheng, format, uid);
        getorderbydealstyle.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String body = response.body();

                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);

                    if (avatorBeen != null && avatorBeen.size() > 0) {

                        AvatorBean bean = avatorBeen.get(0);

                        mBuystyle = bean.getRetmsg();

                        item.setHuoqujiaoyi(mBuystyle);
                        //初始化交易方式 默认为线上
                        if (mBuystyle.contains("线上")) {
                            //设置为线上交易
                            item.setJiaoyi("线上交易");
                            //同时初始化运费
                            List<ShopCartbean.CarshoplistBean.CargoodslistBean> checkgoodslist = item.getCheckgoodslist();
                            double yunfei = 0;
                            for (ShopCartbean.CarshoplistBean.CargoodslistBean cargoodslistBean : checkgoodslist) {
                                String money = cargoodslistBean.getGoods_model().getG_CourierMoney();
                                Double aDouble = Double.valueOf(money);
                                yunfei = yunfei + aDouble;
                            }
                            item.setYunfei(yunfei);


                        } else {
                            item.setJiaoyi("线下交易");
                        }



                    }
                } else {

                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESY_LOCATINO && resultCode == 1) {
            AddressBean location = data.getParcelableExtra("location");
            if (location != null) {
                for (AddressBean bean : mAddressBeen) {
                    if (bean.getId() == location.getId()) {
                        bean.setIscheck(true);
                    } else {
                        bean.setIscheck(false);
                    }
                }
                locationid = location.getId() + "";
            }


            mTvaddrname.setText("收货人：" + location.getMD_Name());
            mTvphone.setText(location.getMD_Phone());
            mTvaddrlocation.setText(location.getMD_Province() + location.getMD_City() + location.getMD_Area() + location.getMD_Address());

        }
    }




}
