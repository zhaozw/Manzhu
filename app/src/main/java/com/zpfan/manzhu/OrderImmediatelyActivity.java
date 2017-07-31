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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMMessage;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zpfan.manzhu.adapter.CheckFormatAdapter;
import com.zpfan.manzhu.adapter.CouponpopAdapter;
import com.zpfan.manzhu.adapter.FormartAdapter;
import com.zpfan.manzhu.bean.AddressBean;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.BussnessBean;
import com.zpfan.manzhu.bean.OrderCouponBean;
import com.zpfan.manzhu.bean.ShopBean;
import com.zpfan.manzhu.myui.EaseActivity;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.myui.TopLin;
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
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zpfan.manzhu.Aplication.mContext;

public class OrderImmediatelyActivity extends AppCompatActivity {

    private static final int REQUESY_LOCATINO = 5;
    private static final int REQUEST_LEAVEMESSAGE = 10;
    @BindView(R.id.tl_ordersure)
    TopLin mTlOrdersure;
    @BindView(R.id.line1)
    View mLine1;
    @BindView(R.id.tv_addrname)
    TextView mTvAddrname;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_addrlocation)
    TextView mTvAddrlocation;
    @BindView(R.id.ll_location)
    LinearLayout mLlLocation;
    @BindView(R.id.line2)
    View mLine2;
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
    @BindView(R.id.ll_finishedit)
    LinearLayout mLlFinishedit;
    @BindView(R.id.iv_shopcover)
    RoundedImageView mIvShopcover;
    @BindView(R.id.tv_goodname)
    TextView mTvGoodname;
    @BindView(R.id.tv_format)
    TextView mTvFormat;
    @BindView(R.id.tv_goodprice)
    TextView mTvGoodprice;
    @BindView(R.id.tv_carcount)
    TextView mTvCarcount;
    @BindView(R.id.ll_price)
    LinearLayout mLlPrice;
    @BindView(R.id.ll_normal)
    LinearLayout mLlNormal;
    @BindView(R.id.iv_edit)
    ImageView mIvEdit;
    @BindView(R.id.et_goodmoney)
    EditText mEtGoodmoney;
    @BindView(R.id.ll_editmoney)
    LinearLayout mLlEditmoney;
    @BindView(R.id.dashline1)
    View mDashline1;
    @BindView(R.id.tv_checkformate)
    TextView mTvCheckformate;
    @BindView(R.id.ll_checkformate)
    LinearLayout mLlCheckformate;
    @BindView(R.id.ll_edit)
    LinearLayout mLlEdit;
    @BindView(R.id.ll_editfinish)
    LinearLayout mLlEditfinish;
    @BindView(R.id.ll_delete)
    LinearLayout mLlDelete;
    @BindView(R.id.ll_finish)
    LinearLayout mLlFinish;
    @BindView(R.id.dashline)
    View mDashline;
    @BindView(R.id.tv_kucun)
    TextView mTvKucun;
    @BindView(R.id.bt_up)
    RelativeLayout mBtUp;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.bt_down)
    RelativeLayout mBtDown;
    @BindView(R.id.tv_online)
    TextView mTvOnline;
    @BindView(R.id.iv_online)
    ImageView mIvOnline;
    @BindView(R.id.tv_yunfei)
    TextView mTvYunfei;
    @BindView(R.id.tv_coupon)
    TextView mTvCoupon;
    @BindView(R.id.iv_coupon)
    ImageView mIvCoupon;
    @BindView(R.id.tv_couponmoney)
    TextView mTvCouponmoney;
    @BindView(R.id.other)
    View mOther;
    @BindView(R.id.iv_other)
    ImageView mIvOther;
    @BindView(R.id.tv_leavemessage)
    TextView mTvLeavemessage;
    @BindView(R.id.iv_leavemessage)
    ImageView mIvLeavemessage;
    @BindView(R.id.et_jifen)
    EditText mEtJifen;
    @BindView(R.id.line_detail)
    LinearLayout mLineDetail;
    @BindView(R.id.tv_allcount)
    TextView mTvAllcount;
    @BindView(R.id.dashline2)
    View mDashline2;
    @BindView(R.id.tv_allprice)
    TextView mTvAllprice;
    @BindView(R.id.tv_zongyunfei)
    TextView mTvZongyunfei;
    @BindView(R.id.tv_youhuijuan)
    TextView mTvYouhuijuan;
    @BindView(R.id.tv_jifen)
    TextView mTvJifen;
    @BindView(R.id.dashline3)
    View mDashline3;
    @BindView(R.id.tv_payprice)
    TextView mTvPayprice;
    @BindView(R.id.tv_pay)
    TextView mTvPay;
    @BindView(R.id.ll_importorder)
    LinearLayout mLlImportorder;
    @BindView(R.id.dashline4)
    View mDashline4;
    @BindView(R.id.tv_yajin)
    TextView mTvYajin;
    @BindView(R.id.ll_renttime)
    LinearLayout mLlRenttime;
    @BindView(R.id.tv_rentday)
    TextView mTvRentday;
    @BindView(R.id.iv_rentday)
    ImageView mIvRentday;
    @BindView(R.id.tv_rentmoney)
    TextView mTvRentmoney;
    @BindView(R.id.ll_rentday)
    LinearLayout mLlRentday;
    @BindView(R.id.ll_rentde)
    LinearLayout mLlRentde;
    @BindView(R.id.tv_userjifen)
    TextView mTvUserjifen;
    private ArrayList<AddressBean> mAddressBeen;
    private BussnessBean mDetail;
    private ShopBean mShopBean = new ShopBean();
    private int max = 0;
    private List<BussnessBean.GoodsSpecificationsBean> mSpecifications;
    private boolean isRent = false;
    private Double mJifen;
    private ArrayList<OrderCouponBean> mCouponBeanArrayList = new ArrayList<>();
    private DecimalFormat mDf;
    private String goospuid = "";
    private String addruid = "";
    private String couponid = "";
    private String mBuystyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_immediately);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mDf = new DecimalFormat("0.00");

        Intent intent = getIntent();

        mDetail = intent.getParcelableExtra("detail");
        mSpecifications = mDetail.getGoods_specifications();
        String type = intent.getStringExtra("type");

        if (type.equals("rent")) {
            //说明是要租的商品
            isRent = true;
            mTvYajin.setVisibility(View.VISIBLE);
            mLlRenttime.setVisibility(View.VISIBLE);
            mLlRentday.setVisibility(View.VISIBLE);
            mLlRentde.setVisibility(View.VISIBLE);
            mDashline4.setVisibility(View.VISIBLE);





        } else {
            isRent = false;
            mTvYajin.setVisibility(View.INVISIBLE);
            mLlRenttime.setVisibility(View.GONE);
            mLlRentday.setVisibility(View.GONE);
            mLlRentde.setVisibility(View.GONE);
            mDashline4.setVisibility(View.GONE);

            //设置运费
            String money = mDetail.getG_CourierMoney();
            Double aDouble = Double.valueOf(money);
            mTvYunfei.setText(mDf.format(aDouble));
            mTvZongyunfei.setText(mDf.format(aDouble));

            mEtJifen.setText("0");


            if (mSpecifications.size() > 0) {
                BussnessBean.GoodsSpecificationsBean bean = mSpecifications.get(0);
                String values = bean.getPS_AttributeValues();
                mTvFormat.setText(values);
                mTvCheckformate.setText(values);
                mTvGoodprice.setText(bean.getPS_FixedPrice());
                mEtGoodmoney.setText(bean.getPS_FixedPrice());
                mTvKucun.setText("(库存：" + bean.getPS_Inventory() + ")");
                max = bean.getPS_Inventory();
                calculationMoney();
            } else {
                mTvFormat.setVisibility(View.INVISIBLE);
                mTvCheckformate.setVisibility(View.INVISIBLE);
                mTvGoodprice.setText(mDetail.getG_FixedPrice());
                mEtGoodmoney.setText(mDetail.getG_FixedPrice());
                mTvKucun.setText("(库存：" + mDetail.getG_StockNum() + ")");
                max = mDetail.getG_StockNum();
                calculationMoney();
            }
        }


        //获取地址信息
        getLocation(Utils.getloginuid());
        //获取店铺的信息
        getShopDetail(mDetail.getMember_UID());
        //设置基本的信息
        mTvGoodname.setText(mDetail.getG_Title());
        //设置虚线
        mDashline.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mDashline1.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mDashline2.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mDashline3.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mDashline4.setLayerType(View.LAYER_TYPE_SOFTWARE, null);


        //设置用户积分
        final String userjifen = SPUtils.getInstance().getString("userjifen", "0");
        mJifen = Double.valueOf(userjifen);
        mTvUserjifen.setText(userjifen);




        //设置优惠劵
        mTvYouhuijuan.setText("0.00");
        mTvCouponmoney.setText("0.00");

        getCouponList();

        //设置积分抵扣
        mEtJifen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = s.toString();
                if (s1.length() > 0) {
                    Double aDouble1 = Double.valueOf(s1);

                    if (aDouble1 > mJifen) {
                        aDouble1 = mJifen;
                        mEtJifen.setText(userjifen);

                    }
                    double jifen = aDouble1 / 100;
                    mTvJifen.setText(mDf.format(jifen));
                }
                calculationMoney();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    mTvJifen.setText("0.00");
                }
            }
        });

        String usesheng = SPUtils.getInstance().getString("usesheng", "");
        String cout = mTvCount.getText().toString();
        Integer count = Integer.valueOf(cout);
        String weight = mDetail.getG_Weight();
        Double aDouble = Double.valueOf(weight);

        double allweight = aDouble * count;
        getbuyStyle(usesheng,mDf.format(allweight),mDetail.getG_UID());
    }

    private void getCouponList() {
        //发送请求去获取店铺的优惠劵
        String s = mTvAllprice.getText().toString();
        String type = null;
        if (isRent) {
            type = "仅租赁";
        } else {
            type = "仅购物";
        }
        Call<String> getordercouponlist = Aplication.mIinterface.getordercouponlist(Utils.getloginuid(), mDetail.getMember_UID(), s, type, "");

        getordercouponlist.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();

                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> been = Utils.gson.fromJson(body, type);
                    if (been != null && been.size() > 0) {
                        AvatorBean bean = been.get(0);
                        String retmsg = bean.getRetmsg();

                        if (retmsg.contains("[")) {
                            String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));

                            if (substring != null) {
                                Type type1 = new TypeToken<ArrayList<OrderCouponBean>>() {
                                }.getType();

                                ArrayList<OrderCouponBean> been1 = Utils.gson.fromJson(substring, type1);

                                mCouponBeanArrayList.clear();
                                mCouponBeanArrayList.addAll(been1);

                            }



                        }





                    }




                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void getShopDetail(String uid) {
        Call<String> getshopdetail = Aplication.mIinterface.getshopdetail(uid);

        getshopdetail.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();

                if (body != null) {

                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                    AvatorBean bean = avatorBeen.get(0);
                    String retmsg = bean.getRetmsg();
                    if (retmsg != null && retmsg.contains("[")) {
                        Type type1 = new TypeToken<ArrayList<ShopBean>>() {
                        }.getType();

                        ArrayList<ShopBean> shopBeens = Utils.gson.fromJson(retmsg, type1);

                        if (shopBeens != null) {
                            mShopBean = shopBeens.get(0);

                            mTvShopname.setText(mShopBean.getS_Name());
                            mTvUserlv.setText("Lv." + mShopBean.getS_LevelNumber());

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
                        Log.i("zc", "onResponse:    看看收获地址" + retmsg.length());
                        if (retmsg.length() > 4 && retmsg.contains("[")) {
                            String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));

                            Type type1 = new TypeToken<ArrayList<AddressBean>>() {
                            }.getType();

                            mAddressBeen = Utils.gson.fromJson(substring, type1);

                            if (mAddressBeen.size() > 0) {
                                for (AddressBean bean : mAddressBeen) {
                                    if (bean.isMD_IsDefault()) {
                                        mTvAddrname.setText("收货人：" + bean.getMD_Name());
                                        mTvPhone.setText(bean.getMD_Phone());
                                        mTvAddrlocation.setText(bean.getMD_Province() + bean.getMD_City() + bean.getMD_Area() + bean.getMD_Address());
                                        addruid = bean.getId()+ "";
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


    @OnClick({R.id.ll_location, R.id.ll_message, R.id.iv_edit, R.id.ll_editfinish, R.id.ll_delete, R.id.bt_up, R.id.bt_down, R.id.ll_checkformate, R.id.tv_online, R.id.other, R.id.tv_leavemessage
            , R.id.iv_leavemessage, R.id.iv_online,R.id.tv_coupon,R.id.iv_coupon,R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_location:
                //跳转到地址界面去选择地址
                if (mAddressBeen != null) {
                    Intent locationintent = new Intent(OrderImmediatelyActivity.this, OrderLocationActivity.class);
                    locationintent.putParcelableArrayListExtra("location", mAddressBeen);
                    startActivityForResult(locationintent, REQUESY_LOCATINO);
                }
                break;
            case R.id.ll_message:
                //跳转到和商家会话的界面
                Intent intent = new Intent(OrderImmediatelyActivity.this, EaseActivity.class);
                intent.putExtra("userId", mDetail.getG_ContactPhone());
                intent.putExtra("usercn", mShopBean.getS_Name());
                intent.putExtra("chatType", EMMessage.ChatType.Chat);
                OrderImmediatelyActivity.this.startActivity(intent);

                break;
            case R.id.iv_edit:
                //编辑的界面
                mLlNormal.setVisibility(View.GONE);
                mIvEdit.setVisibility(View.GONE);

                mLlFinish.setVisibility(View.VISIBLE);
                mLlEdit.setVisibility(View.VISIBLE);

                break;
            case R.id.ll_editfinish:
                mLlNormal.setVisibility(View.VISIBLE);
                mIvEdit.setVisibility(View.VISIBLE);

                mLlFinish.setVisibility(View.GONE);
                mLlEdit.setVisibility(View.GONE);

                break;

            case R.id.ll_delete:
                //删除的操作

                MyToast.show("最后一件商品不能删除", R.mipmap.com_icon_cross_w);
                mLlDelete.setClickable(false);
                break;

            case R.id.bt_up:
                //增加
                mBtDown.setClickable(true);
                String s = mTvCount.getText().toString();
                Integer integer = Integer.valueOf(s) + 1;
                if (integer > max) {
                    integer = max;
                    MyToast.show("购买商品的数量不能超过库存的数量", R.mipmap.com_icon_cross_w);
                    mBtUp.setClickable(false);
                } else {
                    mBtUp.setClickable(true);
                }
                mTvCount.setText(integer + "");
                mTvCarcount.setText("x" + integer);
                calculationMoney();

                break;

            case R.id.bt_down:
                //减少
                mBtUp.setClickable(true);
                String s1 = mTvCount.getText().toString();
                Integer integer1 = Integer.valueOf(s1) - 1;
                if (integer1 < 1) {
                    integer1 = 1;
                    MyToast.show("购买数量不能小于1", R.mipmap.com_icon_cross_w);
                    mBtDown.setClickable(false);
                } else {
                    mBtDown.setClickable(true);
                }

                mTvCount.setText(integer1 + "");
                mTvCarcount.setText("x" + integer1);
                calculationMoney();

                break;
            case R.id.ll_checkformate:
                final PopupWindow popupWindow = new PopupWindow(this);
                final View pop = View.inflate(this, R.layout.format_popwindow, null);
                RecyclerView rvformat = (RecyclerView) pop.findViewById(R.id.rv_format);
                rvformat.setLayoutManager(new LinearLayoutManager(mContext));
                CheckFormatAdapter adapter = new CheckFormatAdapter(R.layout.item_location_popr, mSpecifications);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        String values = mSpecifications.get(position).getPS_AttributeValues();
                        mTvCheckformate.setText(values);
                        mTvFormat.setText(values);
                        mTvGoodprice.setText(mSpecifications.get(position).getPS_FixedPrice());
                        mEtGoodmoney.setText(mSpecifications.get(position).getPS_FixedPrice());
                        mTvKucun.setText("(库存：" + mSpecifications.get(position).getPS_Inventory() + ")");
                        goospuid = mSpecifications.get(position).getProduct_UniqueID();

                        calculationMoney();
                        popupWindow.dismiss();
                    }
                });
                rvformat.setAdapter(adapter);

                popupWindow.setContentView(pop);
                //int width = dp2px(LinearLayout.LayoutParams.WRAP_CONTENT);
                // int height = dp2px(LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(mLlCheckformate.getWidth());
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);

                popupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.home_toppop_bg));
                popupWindow.showAsDropDown(mLlCheckformate);

                break;
            case R.id.iv_online:

            case R.id.tv_online:
                //获取交易方式
                Log.i("zc", "onViewClicked:   看看长度" + mBuystyle.length());
               if (mBuystyle.length() > 5){
                   //线上交易和线下交易
                   final PopupWindow onlineWindow = new PopupWindow(OrderImmediatelyActivity.this);
                   final View onlinepop = View.inflate(OrderImmediatelyActivity.this, R.layout.format_popwindow, null);
                   RecyclerView rvonline = (RecyclerView) onlinepop.findViewById(R.id.rv_format);
                   rvonline.setLayoutManager(new LinearLayoutManager(OrderImmediatelyActivity.this));
                   ArrayList<String> location = new ArrayList<String>();
                   location.add("线上交易");
                   location.add("线下交易");
                   FormartAdapter onlineadapter = new FormartAdapter(R.layout.item_location_popr, location);
                   onlineadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                       @Override
                       public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                           DecimalFormat df = new DecimalFormat("0.00");
                           double zongyunfei = 0.00;
                           if (position == 0) {
                               //线上交易
                               String money = mDetail.getG_CourierMoney();
                               Double aDouble = Double.valueOf(money);
                               zongyunfei = zongyunfei + aDouble;
                               mTvYunfei.setText(df.format(zongyunfei));
                               mTvZongyunfei.setText(df.format(zongyunfei));
                               mTvOnline.setText("线上交易");

                           } else {
                               //线下交易
                               mTvOnline.setText("线下交易");
                               zongyunfei = 0.00;
                               mTvYunfei.setText(df.format(zongyunfei));
                               mTvZongyunfei.setText(df.format(zongyunfei));
                           }
                           calculationMoney();
                           onlineWindow.dismiss();
                       }
                   });
                   rvonline.setAdapter(onlineadapter);
                   onlineWindow.setContentView(onlinepop);
                   // int height = dp2px(LinearLayout.LayoutParams.WRAP_CONTENT);
                   onlineWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                   int i = Utils.dp2px(100);
                   onlineWindow.setWidth(i);
                   onlineWindow.setTouchable(true);
                   onlineWindow.setOutsideTouchable(true);

                   onlineWindow.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.home_toppop_bg));
                   onlineWindow.showAsDropDown(mTvOnline);
               }else {
                       double zongyunfei = 0.00;
                   if (mBuystyle.equals("线上交易|")) {
                       String money = mDetail.getG_CourierMoney();
                       Double aDouble = Double.valueOf(money);
                       zongyunfei = zongyunfei + aDouble;
                       mTvYunfei.setText(mDf.format(zongyunfei));
                       mTvZongyunfei.setText(mDf.format(zongyunfei));
                       mTvOnline.setText("线上交易");

                   } else if (mBuystyle.equals("线下交易|")) {
                       mTvOnline.setText("线下交易");
                       zongyunfei = 0.00;
                       mTvYunfei.setText(mDf.format(zongyunfei));
                       mTvZongyunfei.setText(mDf.format(zongyunfei));

                   }

               }




                break;

            case R.id.other:
                //其它的按钮
                startActivity(new Intent(OrderImmediatelyActivity.this, OtherActivity.class));
                break;

            case R.id.iv_leavemessage:

            case R.id.tv_leavemessage:
                Intent leavemessage = new Intent(OrderImmediatelyActivity.this, LeaveMessageActivity.class);
                String s2 = mTvLeavemessage.getText().toString();
                leavemessage.putExtra("b", "b");
                leavemessage.putExtra("a", s2);
                startActivityForResult(leavemessage, REQUEST_LEAVEMESSAGE);
                break;

            case R.id.iv_coupon:

            case R.id.tv_coupon:
                getCouponList();
                final PopupWindow couponWindow = new PopupWindow(OrderImmediatelyActivity.this);
                final View couponpop = View.inflate(OrderImmediatelyActivity.this, R.layout.format_popwindow, null);
                RecyclerView rvcoupon = (RecyclerView) couponpop.findViewById(R.id.rv_format);
                rvcoupon.setLayoutManager(new LinearLayoutManager(OrderImmediatelyActivity.this));

                CouponpopAdapter couponadapter = new CouponpopAdapter(R.layout.item_location_popr, mCouponBeanArrayList);
                couponadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        OrderCouponBean bean = mCouponBeanArrayList.get(position);

                        couponid = bean.getId() + "";
                        String money = bean.getMC_PreferentialMoney();
                        Double aDouble = Double.valueOf(money);
                        mTvYouhuijuan.setText(mDf.format(aDouble));
                        mTvCouponmoney.setText(mDf.format(aDouble));
                        couponWindow.dismiss();
                        calculationMoney();
                    }
                });

                rvcoupon.setAdapter(couponadapter);
                couponWindow.setContentView(couponpop);
                // int height = dp2px(LinearLayout.LayoutParams.WRAP_CONTENT);
                couponWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

                couponWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                couponWindow.setTouchable(true);
                couponWindow.setOutsideTouchable(true);

                couponWindow.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.home_toppop_bg));
                couponWindow.showAsDropDown(mTvCoupon);

                break;

            case R.id.tv_submit:
                //提交订单
                Map<String, String> map = new LinkedHashMap<>();

                map.put("member_uid", Utils.getloginuid());
                String cunt = mTvCount.getText().toString();
                map.put("Count", cunt);
                map.put("goods_uid", mDetail.getG_UID());
                map.put("goods_ps_uid", goospuid);
                map.put("goods_id_btn", "");
                map.put("Finally_Address_ID", addruid);
                String liuyan = mTvLeavemessage.getText().toString();
                map.put("liuyan_m", liuyan);
                map.put("CarOrBuy", "Buy");
                map.put("OrderCate", "购物订单");
                map.put("shopcoupon_bymemberlist", couponid);
                String jifen = mEtJifen.getText().toString();
                map.put("deduction_buy_score_number", jifen);
                map.put("see_sell_my_data_mm", "");
                map.put("share_uid", "");
                map.put("appointment_date", "");
                map.put("appointment_time", "");
                map.put("yunfei_value_model", "");
                String trading = mTvOnline.getText().toString();
                map.put("trading_value_model", trading);
                String editgoodmoney = mEtGoodmoney.getText().toString();
                map.put("edit_goods_single_price_model", editgoodmoney);

                Call<String> orderSubmit = Aplication.mIinterface.orderSubmit(map);

                orderSubmit.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i("zc", "onResponse:   看看请求" + call.request().toString());

                        String body = response.body();

                        if (body != null) {

                            Type type = new TypeToken<ArrayList<AvatorBean>>() {
                            }.getType();

                            ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);

                            if (avatorBeen != null && avatorBeen.size() > 0) {
                                Log.i("zc", "onResponse:  看看返回的数据" + avatorBeen.get(0).getRetmsg() );

                            }

                        }



                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });



                break;

        }
    }

    private void getbuyStyle(String usesheng, String format, String uid) {

        Call<String> getorderbydealstyle = Aplication.mIinterface.getorderbydealstyle("Buy", "二手商品", Utils.getloginuid(), mDetail.getG_Province(), usesheng, format, uid);

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
                        Log.i("zc", "onResponse:   看看获取的交易方式" + mBuystyle);
                    }
                } else {
                    Log.i("zc", "getbuyStyle:   来获取地址了吗" + call.request().toString());
                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }

    private void calculationMoney() {
        DecimalFormat df = new DecimalFormat("0.00");
        String s = mTvGoodprice.getText().toString();
        Double price = Double.valueOf(s);
        String s1 = mTvCount.getText().toString();
        Integer count = Integer.valueOf(s1);
        double v = price * count;
        mTvAllprice.setText(df.format(v));

        String zongyunfei = mTvZongyunfei.getText().toString();
        Double yunfei = Double.valueOf(zongyunfei);

        String zongjifen = mTvJifen.getText().toString();
        Double jifen = Double.valueOf(zongjifen);

        String youhuijuan = mTvYouhuijuan.getText().toString();
        Double youhui = Double.valueOf(youhuijuan);


        double pay = v + yunfei - jifen - youhui;

        mTvPayprice.setText(df.format(pay));

        mTvPay.setText(df.format(pay));


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //地址选择的操作
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
            mTvAddrname.setText("收货人：" + location.getMD_Name());
            mTvPhone.setText(location.getMD_Phone());
            mTvAddrlocation.setText(location.getMD_Province() + location.getMD_City() + location.getMD_Area() + location.getMD_Address());
            addruid = location.getId() + "";
        }

        if (requestCode == REQUEST_LEAVEMESSAGE && resultCode == 1) {
            //卖家l留言

            String message = data.getStringExtra("message");

            mTvLeavemessage.setText(message);


        }


    }


}
