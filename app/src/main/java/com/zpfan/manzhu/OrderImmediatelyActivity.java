package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Build;
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

import com.bumptech.glide.Glide;
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
import com.zpfan.manzhu.bean.ReservationTimeBean;
import com.zpfan.manzhu.bean.ShopBean;
import com.zpfan.manzhu.myui.EaseActivity;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.myui.TopLin;
import com.zpfan.manzhu.utils.SPUtils;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderImmediatelyActivity extends AppCompatActivity {

    private static final int REQUESY_LOCATINO = 5;
    private static final int REQUEST_LEAVEMESSAGE = 10;
    private static final int REQUEST_DATETIME = 15;
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
    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.tv_numbertype)
    TextView mTvNumbertype;
    @BindView(R.id.tv_rentprice)
    TextView mTvRentprice;
    @BindView(R.id.tv_rentbaseday)
    TextView mTvRentbaseday;
    @BindView(R.id.tv_rentrewal)
    TextView mTvRentrewal;
    @BindView(R.id.tv_weiyuejin)
    TextView mTvWeiyuejin;
    @BindView(R.id.tv_zudate)
    TextView mTvZudate;
    @BindView(R.id.tv_weiyue)
    TextView mTvWeiyue;
    @BindView(R.id.ll_startrent)
    LinearLayout mLlStartrent;
    @BindView(R.id.ll_rentdate)
    LinearLayout mLlRentdate;
    @BindView(R.id.et_startrent)
    EditText mEtStartrent;
    @BindView(R.id.et_rentdate)
    EditText mEtRentdate;
    @BindView(R.id.ll_editrent)
    LinearLayout mLlEditrent;
    @BindView(R.id.ll_count)
    LinearLayout mLlCount;
    @BindView(R.id.tv_reyear)
    TextView mTvReyear;
    @BindView(R.id.ll_reyear)
    LinearLayout mLlReyear;
    @BindView(R.id.tv_remonth)
    TextView mTvRemonth;
    @BindView(R.id.ll_remonth)
    LinearLayout mLlRemonth;
    @BindView(R.id.tv_reday)
    TextView mTvReday;
    @BindView(R.id.ll_reday)
    LinearLayout mLlReday;
    @BindView(R.id.ll_reservationday)
    LinearLayout mLlReservationday;
    @BindView(R.id.tv_retime)
    TextView mTvRetime;
    @BindView(R.id.iv_retime)
    ImageView mIvRetime;
    @BindView(R.id.ll_reservationtime)
    LinearLayout mLlReservationtime;
    private ArrayList<AddressBean> mAddressBeen;
    private BussnessBean mDetail;
    private ShopBean mShopBean = new ShopBean();
    private int max = 1;
    private List<BussnessBean.GoodsSpecificationsBean> mSpecifications;
    private boolean isRent = false;
    private Integer mJifen;
    private ArrayList<OrderCouponBean> mCouponBeanArrayList = new ArrayList<>();
    private DecimalFormat mDf;
    private String goospuid = "";
    private String addruid = "";
    private String couponid = "";
    private String mBuystyle;
    private double allprice = 0;
    private double singleprice = 0;
    private int baseday = 2;
    private int maxday = 0;
    private String mFrome;
    private String rentDay = "1";
    private Calendar mNow;
    private int today;
    private ArrayList<ReservationTimeBean> mTimeData;
    private ArrayList<String> checkid = new ArrayList<>();
    private ArrayList<ReservationTimeBean> mChecktimedata;

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
        mNow = Calendar.getInstance();

        Intent intent = getIntent();

        mDetail = intent.getParcelableExtra("detail");
        mSpecifications = mDetail.getGoods_specifications();
        String type = intent.getStringExtra("type");
        mFrome = intent.getStringExtra("frome");

        getUserIntegral();


        int num = mDetail.getG_StockNum();

        if (type.equals("rent")) {
            //说明是要租的商品
            isRent = true;
            mTvYajin.setVisibility(View.VISIBLE);
            mLlRenttime.setVisibility(View.VISIBLE);
            mLlRentday.setVisibility(View.VISIBLE);
            mLlRentde.setVisibility(View.VISIBLE);
            mDashline4.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mLlEditfinish.setBackground(getResources().getDrawable(R.color.maintextcolor));
            }

            //设置为租赁数量
            mTvNumbertype.setText("租赁数量");
            //查看商品的规格 获取租赁的价格
            if (mSpecifications.size() > 0) {
                //商品有规格 但是不知道有没有 具体的规格
                if (mDetail.getMsp() == null) {
                    BussnessBean.GoodsSpecificationsBean bean = mSpecifications.get(0);
                    String values = bean.getPS_AttributeValues();
                    mTvFormat.setText(values);
                    mTvCheckformate.setText(values);
                    String price = bean.getPS_FixedPrice();
                    mTvGoodprice.setText(price); //押金
                    mTvRentprice.setText(bean.getPS_CorrespAmount()); //起步租金
                    String rennewal = bean.getPS_RenewalPrice();
                    mTvRentrewal.setText(rennewal); //续租租金
                    mEtStartrent.setText(bean.getPS_CorrespAmount()); //设置编辑几步租金的初始值
                    mEtRentdate.setText(rennewal);  //设置编辑续租租金的初始值
                    mTvRentbaseday.setText("（" + bean.getPS_BasicLease() + "天）"); //起租日期
                    mTvZudate.setText("租期\n" + bean.getPS_BasicLease() + "天"); //默认租期是起租的日期
                    Double yajin = Double.valueOf(price);
                    Double zujin = Double.valueOf(rennewal);
                    //违约租期是押金/续租租金

                    baseday = bean.getPS_BasicLease();
                    maxday = (int) (yajin / zujin);
                    mTvWeiyuejin.setText("违约金为续租租金的" + mDetail.getPP_Lease_period_proportion() + "倍");

                    mEtGoodmoney.setText(price);
                    int inventory = bean.getPS_Inventory();
                    mTvKucun.setText("(库存：" + inventory + ")");
                    if (inventory == 0) {
                        max = 1;
                    } else {
                        max = inventory;
                    }
                    calculationMoney(false);
                } else {
                    //商品有具体的规格
                    BussnessBean.GoodsSpecificationsBean msp = mDetail.getMsp();
                    String values = msp.getPS_AttributeValues();
                    mTvFormat.setText(values);
                    mTvCheckformate.setText(values);
                    String price = msp.getPS_FixedPrice();
                    mTvGoodprice.setText(price);
                    mTvRentprice.setText(msp.getPS_CorrespAmount()); //起步租金
                    String rennewal = msp.getPS_RenewalPrice();
                    mTvRentrewal.setText(rennewal); //续租租金
                    mEtStartrent.setText(msp.getPS_CorrespAmount()); //设置编辑几步租金的初始值
                    mEtRentdate.setText(rennewal);  //设置编辑续租租金的初始值
                    mTvRentbaseday.setText("（" + msp.getPS_BasicLease() + "天）"); //最短的租期
                    mTvWeiyuejin.setText("违约金为续租租金的" + mDetail.getPP_Lease_period_proportion() + "倍"); // 违约金
                    mTvZudate.setText("租期\n" + msp.getPS_BasicLease() + "天"); //默认租期是起租的日期
                    Double yajin = Double.valueOf(price);
                    Double zujin = Double.valueOf(rennewal);


                    baseday = msp.getPS_BasicLease();
                    maxday = (int) (yajin / zujin);

                    mEtGoodmoney.setText(price);
                    int inventory = msp.getPS_Inventory();
                    mTvKucun.setText("(库存：" + inventory + ")");
                    if (inventory == 0) {
                        max = 1;

                    } else {
                        max = inventory;

                    }
                    calculationMoney(false);

                }
            } else {
                //商品没有规格
                mTvFormat.setVisibility(View.INVISIBLE);
                mTvCheckformate.setVisibility(View.INVISIBLE);
                String price = mDetail.getG_FixedPrice();
                mTvGoodprice.setText(price);
                String rennewal = mDetail.getG_RenewalPrice();
                mTvRentprice.setText(mDetail.getG_CorrespAmount()); //起步租金
                mTvRentrewal.setText(rennewal); //续租租金
                mEtStartrent.setText(mDetail.getG_CorrespAmount()); //设置编辑几步租金的初始值
                mEtRentdate.setText(rennewal);  //设置编辑续租租金的初始值
                mTvRentbaseday.setText("（" + mDetail.getG_BasicLease() + "天）");
                mTvWeiyuejin.setText("违约金为续租租金的" + mDetail.getPP_Lease_period_proportion() + "倍"); // 违约金
                mTvZudate.setText("租期\n" + mDetail.getG_BasicLease() + "天"); //默认租期是起租的日期

                Double yajin = Double.valueOf(price);
                Double zujin = Double.valueOf(rennewal);


                baseday = mDetail.getG_BasicLease();
                maxday = (int) (yajin / zujin);


                mEtGoodmoney.setText(price);
                mTvKucun.setText("(库存：" + num + ")");
                if (num != 0) {
                    max = num;
                }
                calculationMoney(false);
            }


        } else if (type.equals("server")) {
            //预约的商品
            mLlRenttime.setVisibility(View.GONE);
            mLlRentday.setVisibility(View.GONE);
            mLlRentde.setVisibility(View.GONE);
            mTvYajin.setVisibility(View.VISIBLE);
            mLlLocation.setVisibility(View.GONE);
            mIvEdit.setVisibility(View.GONE);
            mTvFormat.setVisibility(View.GONE);
            mLine1.setVisibility(View.GONE);
            mLlCount.setVisibility(View.GONE);
            mLlReservationday.setVisibility(View.VISIBLE);
            mLlReservationtime.setVisibility(View.VISIBLE);
            mDashline4.setVisibility(View.VISIBLE);

            //对价格的设置
            String fixedPrice = mDetail.getG_FixedPrice();
            mTvGoodprice.setText(fixedPrice);
            //对单位的设置
            mTvYajin.setText("/" +mDetail.getServer_unit_string());

            //对数量的设置
            int count = mDetail.getBuyCount();
            mTvCarcount.setText(count + "");
            mTvCount.setText(count + "");
            mTvRemonth.setText((mNow.get(Calendar.MONTH) + 1) + "月");
            mTvReyear.setText(mNow.get(Calendar.YEAR) + "年");
            mTvReday.setText(mNow.get(Calendar.DATE) + "日");
            today = mNow.get(Calendar.DATE);
            //把提交订单改为提交预约
            mTvSubmit.setText("提交预约");




        calculationMoney(false);
        } else {
            isRent = false;
            mTvYajin.setVisibility(View.INVISIBLE);
            mLlRenttime.setVisibility(View.GONE);
            mLlRentday.setVisibility(View.GONE);
            mLlRentde.setVisibility(View.GONE);
            mDashline4.setVisibility(View.GONE);

            //设置为购买数量
            mTvNumbertype.setText("购买数量");

            if (mSpecifications.size() > 0) {
                //商品有规格 但是不知道有没有 具体的规格
                if (mDetail.getMsp() == null) {
                    BussnessBean.GoodsSpecificationsBean bean = mSpecifications.get(0);
                    String values = bean.getPS_AttributeValues();
                    mTvFormat.setText(values);
                    mTvCheckformate.setText(values);
                    mTvGoodprice.setText(bean.getPS_FixedPrice());
                    mEtGoodmoney.setText(bean.getPS_FixedPrice());
                    int inventory = bean.getPS_Inventory();
                    mTvKucun.setText("(库存：" + inventory + ")");
                    if (inventory != 0) {
                        max = inventory;
                    }
                    calculationMoney(false);
                } else {
                    //商品有具体的规格
                    BussnessBean.GoodsSpecificationsBean msp = mDetail.getMsp();
                    String values = msp.getPS_AttributeValues();
                    mTvFormat.setText(values);
                    mTvCheckformate.setText(values);
                    mTvGoodprice.setText(msp.getPS_FixedPrice());
                    mEtGoodmoney.setText(msp.getPS_FixedPrice());
                    int inventory = msp.getPS_Inventory();
                    mTvKucun.setText("(库存：" + inventory + ")");
                    if (inventory != 0) {
                        max = inventory;
                    }
                    calculationMoney(false);

                }
            } else {
                //商品没有规格
                mTvFormat.setVisibility(View.INVISIBLE);
                mTvCheckformate.setVisibility(View.INVISIBLE);
                mTvGoodprice.setText(mDetail.getG_FixedPrice());
                mEtGoodmoney.setText(mDetail.getG_FixedPrice());
                mTvKucun.setText("(库存：" + num + ")");
                if (num != 0) {
                    max = num;
                }
                calculationMoney(false);
            }
        }

        //初始化积分
        mEtJifen.setText("0");

        //设置运费
        String money = mDetail.getG_CourierMoney();
        Double aDouble = Double.valueOf(money);
        mTvYunfei.setText(mDf.format(aDouble));
        mTvZongyunfei.setText(mDf.format(aDouble));


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

        //设置数量
        mTvCount.setText(mDetail.getBuyCount() + "");
        mTvCarcount.setText("x" + mDetail.getBuyCount());

        //设置优惠劵
        mTvYouhuijuan.setText("0.00");
        mTvCouponmoney.setText("0.00");

        //设置商品的图片
        Glide.with(this).load(mDetail.getG_Cover()).into(mIvShopcover);


        getCouponList();

        //设置积分抵扣
        mEtJifen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String s1 = s.toString(); // 获取到用户输入的值
                String s2 = mEtJifen.getText().toString();
                mEtJifen.setSelection(s2.length());
                if (s1.length() > 0) {
                    Integer integer = Integer.valueOf(s1);  //用户输入的值
                    if (integer > mJifen) {
                        integer = mJifen;
                        mEtJifen.setText(mJifen + "");
                    }
                    if (integer > allprice * 100) {
                        integer = (int) allprice;
                        mEtJifen.setText(integer + "");
                    }
                    String s3 = integer + "";
                    Double aDouble = Double.valueOf(s3);
                    mTvJifen.setText(mDf.format(aDouble / 100));


                } else if (s1.length() == 0) {
                    mTvJifen.setText("0.00");
                }


                calculationMoney(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        String usesheng = SPUtils.getInstance().getString("usesheng", "");
        String cout = mTvCount.getText().toString();
        Integer count = Integer.valueOf(cout);
        String weight = mDetail.getG_Weight();
        Double wei = Double.valueOf(weight);

        double allweight = wei * count;
        getbuyStyle(usesheng, mDf.format(allweight), mDetail.getG_UID());
        calculationMoney(false);

    }

    /**
     * 获取用户的积分或者余额
     */
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
                            mTvUserjifen.setText("（可用积分 " + retmsg + "）");
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
                                        mTvAddrlocation.setText("收货地址：" + bean.getMD_Province() + bean.getMD_City() + bean.getMD_Area() + bean.getMD_Address());
                                        addruid = bean.getId() + "";
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


    @OnClick({R.id.ll_location, R.id.ll_message, R.id.iv_edit, R.id.ll_editfinish, R.id.bt_up, R.id.bt_down, R.id.ll_checkformate, R.id.tv_online, R.id.other, R.id.tv_leavemessage
            , R.id.iv_leavemessage, R.id.iv_online, R.id.tv_coupon, R.id.iv_coupon, R.id.tv_submit, R.id.tv_rentday,R.id.ll_reyear,R.id.ll_remonth,R.id.ll_reday
            ,R.id.ll_reservationtime})
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
                mLlStartrent.setVisibility(View.GONE);
                mLlRentdate.setVisibility(View.GONE);
                mLlEditrent.setVisibility(View.VISIBLE);

                break;
            case R.id.ll_editfinish:
                mLlNormal.setVisibility(View.VISIBLE);
                mIvEdit.setVisibility(View.VISIBLE);
                mLlEditrent.setVisibility(View.GONE);
                mBtUp.setClickable(true);
                mBtDown.setClickable(true);

                //数量也改为1
                mTvCount.setText("1");
                mTvCarcount.setText("x" + 1);

                //在完成编辑后  把价格修改了  如果有规格 要修改 规格的价格
                String s3 = mEtGoodmoney.getText().toString();
                if (s3.isEmpty()) {
                    s3 = 0 + "";
                    mEtGoodmoney.setText(0 + "");
                }
                if (mDetail.getGoods_specifications().size() > 0) {
                    BussnessBean.GoodsSpecificationsBean msp = mDetail.getMsp();
                    for (BussnessBean.GoodsSpecificationsBean specification : mSpecifications) {
                        if (specification.getId() == msp.getId()) {
                            specification.setPS_FixedPrice(s3);
                            singleprice = Double.valueOf(s3);
                            msp.setPS_FixedPrice(s3);
                            if (isRent) {
                                //如果是租的商品
                                String eStartrent = mEtStartrent.getText().toString(); //修改后的起始租金
                                String eRentData = mEtRentdate.getText().toString();  //修改后的续租租金
                                msp.setPS_CorrespAmount(eStartrent);
                                msp.setPS_RenewalPrice(eRentData);
                                Double aDouble = Double.valueOf(eStartrent);
                                Double bDouble = Double.valueOf(eRentData);
                                mTvRentprice.setText(mDf.format(aDouble));
                                mTvRentrewal.setText(mDf.format(bDouble));
                                //重新计算能够租的时间
                                double v = singleprice / bDouble;
                                maxday = (int) v;


                            }
                        }
                    }
                    String price = msp.getPS_FixedPrice();
                    Double aDouble = Double.valueOf(price);
                    mTvGoodprice.setText(mDf.format(aDouble));
                    calculationMoney(false);
                } else {
                    //没有规格
                    String s = mEtGoodmoney.getText().toString();
                    Double aDouble = Double.valueOf(s);
                    //把商品的价格修改了
                    mDetail.setG_FixedPrice(mDf.format(aDouble));
                    mTvGoodprice.setText(mDf.format(aDouble));

                    if (isRent) {
                        //是租的商品修改租金
                        String eStartrent = mEtStartrent.getText().toString(); //修改后的起始租金
                        String eRentData = mEtRentdate.getText().toString();  //修改后的续租租金
                        mDetail.setG_CorrespAmount(eStartrent);
                        mDetail.setG_RenewalPrice(eRentData);
                        mTvRentprice.setText(eStartrent);
                        mTvRentrewal.setText(eRentData);

                    }


                    calculationMoney(false);

                }


                mLlFinish.setVisibility(View.GONE);
                mLlEdit.setVisibility(View.GONE);
                mLlStartrent.setVisibility(View.VISIBLE);
                mLlRentdate.setVisibility(View.VISIBLE);

                break;


            case R.id.bt_up:
                //增加
                mBtDown.setClickable(true);
                String s = mTvCount.getText().toString();
                Integer integer = Integer.valueOf(s) + 1;

                if (integer > max) {
                    integer = max;
                    if (max == 0) {
                        integer = 1;
                        mBtUp.setClickable(true);
                    }
                    MyToast.show("购买商品的数量不能超过库存的数量", R.mipmap.com_icon_cross_w);
                    mBtUp.setClickable(false);
                } else {
                    mBtUp.setClickable(true);
                }
                mTvCount.setText(integer + "");
                mTvCarcount.setText("x" + integer);
                calculationMoney(false);

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
                calculationMoney(false);

                break;
            case R.id.ll_checkformate:
                //选择规格
                final PopupWindow popupWindow = new PopupWindow(this);
                final View pop = View.inflate(this, R.layout.format_popwindow, null);
                RecyclerView rvformat = (RecyclerView) pop.findViewById(R.id.rv_format);
                rvformat.setLayoutManager(new LinearLayoutManager(OrderImmediatelyActivity.this));
                CheckFormatAdapter adapter = new CheckFormatAdapter(R.layout.item_location_popr, mSpecifications);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        BussnessBean.GoodsSpecificationsBean bean = mSpecifications.get(position);
                        String values = bean.getPS_AttributeValues();
                        mTvCheckformate.setText(values);
                        mTvFormat.setText(values);
                        String price1 = bean.getPS_FixedPrice();
                        mTvGoodprice.setText(price1);
                        mEtGoodmoney.setText(price1);
                        int inventory = bean.getPS_Inventory();
                        mTvKucun.setText("(库存：" + inventory + ")");
                        goospuid = bean.getProduct_UniqueID();
                        String amount = bean.getPS_CorrespAmount();
                        mTvRentprice.setText(amount); //起步租金
                        String price = bean.getPS_RenewalPrice();
                        mTvRentrewal.setText(price); //续租租金
                        mEtStartrent.setText(amount);
                        mEtRentdate.setText(price);
                        Double yajin = Double.valueOf(price1);
                        Double zujin = Double.valueOf(price);


                        baseday = bean.getPS_BasicLease();
                        maxday = (int) (yajin / zujin);
                        mDetail.setMsp(bean);

                        max = inventory;

                        calculationMoney(false);
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

                popupWindow.setBackgroundDrawable(OrderImmediatelyActivity.this.getResources().getDrawable(R.drawable.home_toppop_bg));
                popupWindow.showAsDropDown(mLlCheckformate);

                break;
            case R.id.iv_online:

            case R.id.tv_online:
                //获取交易方式

                if (mBuystyle.length() > 5) {
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
                            calculationMoney(false);
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

                    onlineWindow.setBackgroundDrawable(OrderImmediatelyActivity.this.getResources().getDrawable(R.drawable.home_toppop_bg));
                    onlineWindow.showAsDropDown(mTvOnline);
                } else {
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
                //获取优惠劵
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
                        calculationMoney(false);
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
                String s5 = mTvSubmit.getText().toString();
                if (s5.equals("提交订单")) {
                    if (!isRent) {
                        mTvSubmit.setEnabled(false);
                        mTvSubmit.setClickable(false);
                        mTvSubmit.setOnClickListener(null);
                        Map<String, String> map = new LinkedHashMap<>();

                        map.put("member_uid", Utils.getloginuid());
                        String cunt = mTvCount.getText().toString();
                        map.put("Count", cunt);
                        map.put("goods_uid", mDetail.getG_UID());
                        String id = "";
                        if (mDetail.getMsp() != null) {
                            id = mDetail.getMsp().getPS_UniqueID();
                        }
                        map.put("goods_ps_uid", id);
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
                        map.put("appointment_date", "2017-07-31");
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


                                String body = response.body();

                                if (body != null) {

                                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                                    }.getType();

                                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);

                                    if (avatorBeen != null && avatorBeen.size() > 0) {
                                        AvatorBean bean = avatorBeen.get(0);
                                        Intent orderIntent = new Intent(OrderImmediatelyActivity.this, OrderGenerationActivity.class);
                                        orderIntent.putExtra("avator", bean);
                                        orderIntent.putExtra("type", mFrome);
                                        startActivity(orderIntent);

                                        finish();
                                    }

                                }


                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    } else {
                        //租赁订单的发起
                        Map<String, String> map = new LinkedHashMap<>();

                        map.put("member_uid", Utils.getloginuid());
                        String count = mTvCount.getText().toString();
                        map.put("rent_count", count); //租赁的数量
                        map.put("goods_uid", mDetail.getG_UID()); //商品的唯一编号
                        String psuid = "";
                        BussnessBean.GoodsSpecificationsBean msp = mDetail.getMsp();
                        if (msp != null) {
                            psuid = msp.getPS_UniqueID();
                        }

                        map.put("goods_ps_uid", psuid);
                        String liuyan = mTvLeavemessage.getText().toString();
                        map.put("liuyan_m", liuyan);
                        map.put("CarOrBuy", "Buy");
                        map.put("OrderCate", "租赁订单");
                        map.put("shopcoupon_bymemberlist", couponid);
                        String jifen = mEtJifen.getText().toString();
                        if (jifen.length() == 0) {
                            jifen = "0";
                        }
                        map.put("deduction_buy_score_number", jifen);
                        map.put("change_rent_day_model_v", rentDay);

                        map.put("Finally_Address_ID", addruid);
                        String trading = mTvOnline.getText().toString();
                        map.put("trading_pattern", trading);
                        String goodprice = mTvGoodprice.getText().toString();
                        map.put("edit_goods_single_price", goodprice);
                        String rentprice = mTvRentprice.getText().toString();
                        map.put("edit_rentfirst_price", rentprice);
                        String rentrewal = mTvRentrewal.getText().toString();
                        map.put("edit_rentxuzu_price", rentrewal);


                        Call<String> call = Aplication.mIinterface.submitRentOrder(map);

                        call.enqueue(new Callback<String>() {
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

                                        Intent orderIntent = new Intent(OrderImmediatelyActivity.this, OrderGenerationActivity.class);
                                        orderIntent.putExtra("avator", bean);
                                        orderIntent.putExtra("type", "rent");
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
                } else if (s5.equals("提交预约")) {
                    //提交预约的方法
                    String retime = mTvRetime.getText().toString();
                    if (!retime.equals("请选择预约时段")) {

                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("member_uid", Utils.getloginuid());
                        String cunt = mTvCount.getText().toString();
                        map.put("Count", cunt);
                        map.put("goods_uid", mDetail.getG_UID());
                        String id = "";
                        if (mDetail.getMsp() != null) {
                            id = mDetail.getMsp().getPS_UniqueID();
                        }
                        map.put("goods_ps_uid", id);
                        map.put("goods_id_btn", "");
                        map.put("Finally_Address_ID", addruid);
                        String liuyan = mTvLeavemessage.getText().toString();
                        if (liuyan.equals("给卖家的补充说明都可以写在这里")) {
                            liuyan = "  ";
                        }
                        map.put("liuyan_m", liuyan);
                        map.put("CarOrBuy", "Buy");
                        map.put("OrderCate", "购物订单");
                        map.put("shopcoupon_bymemberlist", couponid);
                        String jifen = mEtJifen.getText().toString();
                        map.put("deduction_buy_score_number", jifen);
                        map.put("see_sell_my_data_mm", "");
                        map.put("share_uid", "");
                        final String toreyear = mTvReyear.getText().toString().replace("年", "");
                        String toremonth = mTvRemonth.getText().toString().replace("月", "");
                        String toreday = mTvReday.getText().toString().replace("日", "");

                        final String date = toreyear + "-" + toremonth + "-" + toreday;
                        map.put("appointment_date", date);

                        Map<String, String> time = new LinkedHashMap<>();
                        time.put("time", "");
                        for (ReservationTimeBean bean : mChecktimedata) {
                            String time1 = time.get("time");

                            if (time1.isEmpty()) {
                                time.put("time", bean.getTime());
                            } else {
                                time.put("time", time1 + "," + bean.getTime());

                            }

                        }

                        map.put("appointment_time", time.get("time"));
                        map.put("yunfei_value_model", "");
                        String trading = mTvOnline.getText().toString();
                        map.put("trading_value_model", trading);
                        String editgoodmoney = mEtGoodmoney.getText().toString();
                        String s4 = mTvAllprice.getText().toString();
                        map.put("edit_goods_single_price_model", s4);

                        Log.i("zc", "onViewClicked:  看看数据" + map.toString());


                        Call<String> orderSubmit = Aplication.mIinterface.orderSubmit(map);

                        orderSubmit.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.i("zc", "onResponse:  看看发送的请求是什么返回" + call.request().toString());
                                String body = response.body();
                                if (body != null) {
                                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                                    }.getType();

                                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);

                                    if (avatorBeen != null && avatorBeen.size() > 0) {
                                        AvatorBean bean = avatorBeen.get(0);
                                        Log.i("zc", "onResponse:  看看数据" + bean.getRetmsg());

                                        Intent orderIntent = new Intent(OrderImmediatelyActivity.this, OrderGenerationActivity.class);
                                        orderIntent.putExtra("avator", bean);
                                        orderIntent.putExtra("type", "yuyue");
                                        startActivity(orderIntent);

                                        finish();

                                    }



                                }


                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });










                    } else {
                        MyToast.show("请先选择了预约时段再下单",R.mipmap.com_icon_cross_w);
                        mTvSubmit.setClickable(false);
                        mTvSubmit.setEnabled(false);
                    }


                }


                break;

            case R.id.tv_rentday:
                //对租期的选择
                final ArrayList<String> rentday = new ArrayList<>();
                for (int i = baseday; i < maxday; i++) {
                    rentday.add(i + "天");
                }

                final PopupWindow onlineWindow = new PopupWindow(OrderImmediatelyActivity.this);
                final View onlinepop = View.inflate(OrderImmediatelyActivity.this, R.layout.format_popwindow, null);
                RecyclerView rvonline = (RecyclerView) onlinepop.findViewById(R.id.rv_format);
                rvonline.setLayoutManager(new LinearLayoutManager(OrderImmediatelyActivity.this));
                FormartAdapter onlineadapter = new FormartAdapter(R.layout.item_location_popr, rentday);
                onlineadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        String s4 = rentday.get(position);
                        rentDay = s4.substring(0, s4.length() - 1);
                        Integer integer2 = Integer.valueOf(rentDay);
                        mTvZudate.setText("租期\n" + rentDay + "天"); //默认租期是起租的日期

                        int i = maxday - integer2;

                        mTvRentday.setText(s4);
                        onlineWindow.dismiss();
                    }
                });
                rvonline.setAdapter(onlineadapter);
                onlineWindow.setContentView(onlinepop);

                onlineWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                int i = Utils.dp2px(100);
                onlineWindow.setWidth(i);
                onlineWindow.setTouchable(true);
                onlineWindow.setOutsideTouchable(true);

                onlineWindow.setBackgroundDrawable(OrderImmediatelyActivity.this.getResources().getDrawable(R.drawable.home_toppop_bg));
                onlineWindow.showAsDropDown(mTvRentday);


                break;
            case R.id.ll_reyear:
                //预约的年份


                int year = mNow.get(Calendar.YEAR);

                final ArrayList<String> reyear = new ArrayList<>();
                for (int i1 = year; i1 < year + 3; i1++) {
                    reyear.add(i1 + "年");
                }

                final PopupWindow reYearWindow = new PopupWindow(OrderImmediatelyActivity.this);
                final View reYearpop = View.inflate(OrderImmediatelyActivity.this, R.layout.format_popwindow, null);
                RecyclerView rvReYear = (RecyclerView) reYearpop.findViewById(R.id.rv_format);
                rvReYear.setLayoutManager(new LinearLayoutManager(OrderImmediatelyActivity.this));
                FormartAdapter rvReYeardapter = new FormartAdapter(R.layout.item_location_popr, reyear);
                rvReYeardapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        String s4 = reyear.get(position);
                        mTvReyear.setText(s4);
                        reYearWindow.dismiss();
                    }
                });
                rvReYear.setAdapter(rvReYeardapter);
                reYearWindow.setContentView(reYearpop);

                reYearWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                int i2 = Utils.dp2px(100);
                reYearWindow.setWidth(i2);
                reYearWindow.setTouchable(true);
                reYearWindow.setOutsideTouchable(true);

                reYearWindow.setBackgroundDrawable(OrderImmediatelyActivity.this.getResources().getDrawable(R.drawable.home_toppop_bg));
                reYearWindow.showAsDropDown(mLlReyear);


                break;
            case R.id.ll_remonth:
                //预约的月份


                int start = 0;
                int month = mNow.get(Calendar.MONTH);
                int year1 = mNow.get(Calendar.YEAR);
                String s4 = mTvReyear.getText().toString().replace("年","");
                int myyear = Integer.valueOf(s4);
                if (myyear == year1) {
                    start =  month;
                } else {
                    start = 1;
                }
                mTvRemonth.setText(start + "月");

                final ArrayList<String> remonth = new ArrayList<>();
                for (int i1 = start; i1 < 13; i1++) {
                    remonth.add(i1 + "月");
                }

                final PopupWindow reMonthWindow = new PopupWindow(OrderImmediatelyActivity.this);
                final View reMonthpop = View.inflate(OrderImmediatelyActivity.this, R.layout.format_popwindow, null);
                RecyclerView rvReMoonth = (RecyclerView) reMonthpop.findViewById(R.id.rv_format);
                rvReMoonth.setLayoutManager(new LinearLayoutManager(OrderImmediatelyActivity.this));
                FormartAdapter rvReMonthdapter = new FormartAdapter(R.layout.item_location_popr, remonth);
                rvReMonthdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        String s4 = remonth.get(position);
                        mTvRemonth.setText(s4);
                        reMonthWindow.dismiss();
                    }
                });
                rvReMoonth.setAdapter(rvReMonthdapter);
                reMonthWindow.setContentView(reMonthpop);

                reMonthWindow.setHeight(Utils.dp2px(300));

                reMonthWindow.setWidth(Utils.dp2px(100));
                reMonthWindow.setTouchable(true);
                reMonthWindow.setOutsideTouchable(true);

                reMonthWindow.setBackgroundDrawable(OrderImmediatelyActivity.this.getResources().getDrawable(R.drawable.home_toppop_bg));
                reMonthWindow.showAsDropDown(mLlRemonth);




                break;
            case R.id.ll_reday:
                //预约的天数

                // 先根据年月 获取当月的天数是最大的天数
                String year2 = mTvReyear.getText().toString().replace("年", "");
                String month2 = mTvRemonth.getText().toString().replace("月", "");
                Integer yer = Integer.valueOf(year2);
                Integer mon = Integer.valueOf(month2);
                int toyer = mNow.get(Calendar.YEAR);
                int tomon = mNow.get(Calendar.MONTH) + 1;
                int day = getMonthDay(yer, mon);
                int startday = 1;
                final ArrayList<String> reday = new ArrayList<>();

                //获取到当月的最大天数 然后判断是不是当月  如果是 就从当前的日往后选择 如果不是 就显示整个月的日期
                if (toyer == yer &&  tomon == mon) {
                    //说明是今年的这个月
                    startday = today;

                }

                for (int iday = startday; iday <= day; iday++) {
                    reday.add(iday + "日");

                }

                final PopupWindow reDayWindow = new PopupWindow(OrderImmediatelyActivity.this);
                final View reDaythpop = View.inflate(OrderImmediatelyActivity.this, R.layout.format_popwindow, null);
                RecyclerView rvReDay = (RecyclerView) reDaythpop.findViewById(R.id.rv_format);
                rvReDay.setLayoutManager(new LinearLayoutManager(OrderImmediatelyActivity.this));
                FormartAdapter rvReDayapter = new FormartAdapter(R.layout.item_location_popr, reday);
                rvReDayapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        String s4 = reday.get(position);
                        mTvReday.setText(s4);
                        reDayWindow.dismiss();
                    }
                });
                rvReDay.setAdapter(rvReDayapter);
                reDayWindow.setContentView(reDaythpop);

                reDayWindow.setHeight(Utils.dp2px(300));

                reDayWindow.setWidth(Utils.dp2px(100));
                reDayWindow.setTouchable(true);
                reDayWindow.setOutsideTouchable(true);

                reDayWindow.setBackgroundDrawable(OrderImmediatelyActivity.this.getResources().getDrawable(R.drawable.home_toppop_bg));
                reDayWindow.showAsDropDown(mLlReday);

                break;
            case R.id.ll_reservationtime:
                mTvSubmit.setClickable(true);
                mTvSubmit.setEnabled(true);
                //获取当前选中的时间
                final String toreyear = mTvReyear.getText().toString().replace("年", "");
                String toremonth = mTvRemonth.getText().toString().replace("月", "");
                String toreday = mTvReday.getText().toString().replace("日", "");

                final String date = toreyear + "-" + toremonth + "-" + toreday;
                mTimeData = new ArrayList<>();
                Call<String> serverTimeLis = Aplication.mIinterface.getSercerTimeList(mDetail.getG_UID(), date);

                serverTimeLis.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i("zc", "onResponse:   看看发送的书u" + call.request().toString());

                        String body = response.body();
                        if (body != null) {
                            Type type = new TypeToken<ArrayList<AvatorBean>>() {
                            }.getType();

                            ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                            if (avatorBeen != null && avatorBeen.size() > 0) {
                                AvatorBean bean = avatorBeen.get(0);
                                String retmsg = bean.getRetmsg();

                                String[] split = retmsg.split("\\|");
                                for (String s : split) {
                                    //得到显示的时间 价格 是否已预约 时间点值
                                    String[] split1 = s.split(",");
                                    mTimeData.add(new ReservationTimeBean(split1[0], split1[1], split1[2], split1[3]));
                                }
                                if (mTimeData.size() > 0) {
                                    Intent reservationintent = new Intent(OrderImmediatelyActivity.this,ReservationTimeActivity.class);
                                    reservationintent.putExtra("buss", mDetail);
                                    reservationintent.putParcelableArrayListExtra("data", mTimeData);
                                    reservationintent.putExtra("date", date);
                                    reservationintent.putStringArrayListExtra("checkid", checkid);
                                    startActivityForResult(reservationintent,REQUEST_DATETIME);
                                }


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

    private int getMonthDay(int integer, int p0) {
        mNow.set(Calendar.YEAR, integer);
        mNow.set(Calendar.MONTH, p0 - 1);
        mNow.set(Calendar.DATE, 1);
        mNow.roll(Calendar.DATE, -1);
        int maxDate = mNow.get(Calendar.DATE);
        mNow.clear();
        return maxDate;
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

                    }
                } else {

                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }

    private void calculationMoney(boolean isDate) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (!isDate) {
            String s = mTvGoodprice.getText().toString();
            Double price = Double.valueOf(s);
            String s1 = mTvCount.getText().toString();
            Integer count = Integer.valueOf(s1);
            singleprice = price;
            allprice = price * count;
        }

        mTvAllprice.setText(df.format(allprice));
        String zongyunfei = mTvZongyunfei.getText().toString();
        Double yunfei = Double.valueOf(zongyunfei);

        String zongjifen = mTvJifen.getText().toString();
        Double jifen = Double.valueOf(zongjifen);

        String youhuijuan = mTvYouhuijuan.getText().toString();
        Double youhui = Double.valueOf(youhuijuan);


        double pay = allprice + yunfei - jifen - youhui;

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


        if (requestCode == REQUEST_DATETIME && resultCode == 2) {
            checkid.clear();

            //获取到了预约的信息
            Double price = 0.00;
            Map<String, String> map = new LinkedHashMap<>();
             map.put("time", "");
            mChecktimedata = data.getParcelableArrayListExtra("checkdata");
            mTvCount.setText(mChecktimedata.size() + "");
            mTvCarcount.setText("x" + mChecktimedata.size());

            for (ReservationTimeBean bean : mChecktimedata) {
                for (ReservationTimeBean timeBean : mTimeData) {
                    timeBean.setIscheck(false);
                    if (timeBean.getTimeid().equals(bean.getTimeid()) ) {
                        timeBean.setIscheck(true);
                        checkid.add(bean.getTimeid());
                    }

                }


                Double aDouble = Double.valueOf(bean.getPrice());
                price = price + aDouble;
                String time = map.get("time");
                if (time.isEmpty()) {
                    map.put("time", bean.getTime());
                } else {
                    map.put("time", time + "，" + bean.getTime());
                }

            }

            mTvRetime.setText(map.get("time"));

            allprice = price;
            calculationMoney(true);

        }




    }


}
