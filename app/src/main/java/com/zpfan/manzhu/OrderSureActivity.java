package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
    private Map<String, String> mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_sure);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        mMap = new HashMap<>();
        mMap.put("member_uid", Utils.getloginuid());
        mMap.put("Count", " ");
        mMap.put("goods_uid", " ");
        mMap.put("goods_ps_uid", " ");
        mMap.put("goods_id_btn", " ");
        mMap.put("Finally_Address_ID", " ");
        mMap.put("liuyan_m", " ");
        mMap.put("CarOrBuy", " ");
        mMap.put("OrderCate", " ");
        mMap.put("liuyan_m", " ");
        mMap.put("shopcoupon_bymemberlist", " ");
        mMap.put("deduction_buy_score_number", " ");
        mMap.put("see_sell_my_data_mm", " ");
        mMap.put("share_uid", " ");
        mMap.put("appointment_date", " ");
        mMap.put("appointment_time", " ");
        mMap.put("yunfei_value_model", " ");
        mMap.put("trading_value_model", " ");
        mMap.put("edit_goods_single_price_model", " ");

        View headview = View.inflate(this, R.layout.rv_order_sure_head, null);
        mRvOrdersure.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();

        String type = intent.getStringExtra("type");

        if (type.equals("sopcart")) {
            mMorderlist = intent.getParcelableArrayListExtra("shopcat");
            for (ShopCartbean.CarshoplistBean bean : mMorderlist) {
                Log.i("zc", "initView:   看看数据对不对" + bean.getCheckgoodslist().size());
            }
            mAdapter = new OrderSureAdapter(R.layout.item_ordersure, mMorderlist, new EditListener() {
                @Override
                public void edit(ArrayList<ShopCartbean.CarshoplistBean.CargoodslistBean> checeGood) {
                    Log.i("zc", "edit:   更新底部数据");
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
                    //发送请求去提交订单
                    Call<String> orderSubmit = Aplication.mIinterface.orderSubmit(mMap);

                    orderSubmit.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String body = response.body();
                            Log.i("zc", "onResponse:   请求成功" + call.request().toString());
                            if (body != null) {

                                Log.i("zc", "onResponse:   看看body" + body + call.request().toString());

                            }



                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.i("zc", "onFailure:   请求失败" + call.request().toString());
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
        TextView tvallprice = (TextView) mFootView.findViewById(R.id.tv_allprice);
        TextView tvyunfei = (TextView) mFootView.findViewById(R.id.tv_yunfei);
        TextView tvyouhuijuan = (TextView) mFootView.findViewById(R.id.tv_youhuijuan);
        final TextView tvjifen = (TextView) mFootView.findViewById(R.id.tv_jifen);
        final TextView tvpayprice = (TextView) mFootView.findViewById(R.id.tv_payprice);
        final EditText etjifen = (EditText) mFootView.findViewById(R.id.et_jifen);
        View dashinle = mFootView.findViewById(R.id.dashline1);
        dashinle.setLayerType(View.LAYER_TYPE_SOFTWARE, null);


        for (ShopCartbean.CarshoplistBean bean : mMorderlist) {

            yunfeni = yunfeni + bean.getYunfei();
            youhui = youhui + bean.getYouhui();
                String m = mMap.get("liuyan_m");
            String liuyan = m + "," + bean.getLiuyan();
            for (ShopCartbean.CarshoplistBean.CargoodslistBean cargoodslistBean : bean.getCheckgoodslist()) {
                String btn = mMap.get("goods_id_btn");
                String id = btn + ","+ cargoodslistBean.getSC_UID();



                mMap.put("goods_id_btn", id);
                mMap.put("OrderCate", "购物订单");
                mMap.put("CarOrBuy", "car");

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

        final DecimalFormat df = new DecimalFormat("0.00");
        tvallprice.setText(df.format(allprice));
        //运费的计算
        tvyunfei.setText(df.format(yunfeni));
        //优惠劵的金额
        tvyouhuijuan.setText(df.format(youhui));
        String s = tvjifen.getText().toString();
        Double jifen = Double.valueOf(s);

        //积分折扣的钱
        double endmoney = allprice + yunfeni - youhui - jifen;

        tvpayprice.setText(df.format(endmoney));
        mTvPay.setText(df.format(endmoney));

        etjifen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String w = s.toString();
                double v = 0;
                if (!w.isEmpty()) {
                    Double aDouble = Double.valueOf(w);
                    v = aDouble / 100;
                    tvjifen.setText(df.format(v));
                    //积分折扣的钱
                    double endmoney = allprice + yunfeni - youhui - v;
                    tvpayprice.setText(df.format(endmoney));
                    mTvPay.setText(df.format(endmoney));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {

                    tvjifen.setText(df.format(0));
                    //积分折扣的钱
                    double endmoney = allprice + yunfeni - youhui - 0;
                    tvpayprice.setText(df.format(endmoney));
                    mTvPay.setText(df.format(endmoney));
                }


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
                        Log.i("zc", "onResponse:    看看收获地址" + retmsg.length());
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
                                        mTvaddrlocation.setText(bean.getMD_Province() + bean.getMD_City() + bean.getMD_Area() + bean.getMD_Address());
                                        mMap.put("Finally_Address_ID", bean.getId() + "");
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
        Log.i("shuaxin", "onResume: 刷新了数据了吗");
        mAdapter.notifyDataSetChanged();
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
            }

            mTvaddrname.setText("收货人：" + location.getMD_Name());
            mTvphone.setText(location.getMD_Phone());
            mTvaddrlocation.setText(location.getMD_Province() + location.getMD_City() + location.getMD_Area() + location.getMD_Address());

        }
    }
}
