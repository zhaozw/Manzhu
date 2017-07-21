package com.zpfan.manzhu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.reflect.TypeToken;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.BussnessBean;
import com.zpfan.manzhu.bean.ShopBean;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zpfan.manzhu.Aplication.mContext;

public class IdleDetailActivity extends AppCompatActivity {

    @BindView(R.id.iv_detail)
    ImageView mIvDetail;
    @BindView(R.id.iv_top_back)
    ImageView mIvTopBack;
    @BindView(R.id.iv_topmenu)
    ImageView mIvTopmenu;
    @BindView(R.id.tv_baoyou)
    TextView mTvBaoyou;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.ll_share)
    LinearLayout mLlShare;
    @BindView(R.id.ll_freepostage)
    LinearLayout mLlFreepostage;
    @BindView(R.id.tv_fan)
    TextView mTvFan;
    @BindView(R.id.tv_qian)
    TextView mTvQian;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_decimal)
    TextView mTvDecimal;
    @BindView(R.id.tv_moreprice)
    TextView mTvMoreprice;
    @BindView(R.id.ll_fanmai)
    LinearLayout mLlFanmai;
    @BindView(R.id.tv_zu)
    TextView mTvZu;
    @BindView(R.id.textView2)
    TextView mTextView2;
    @BindView(R.id.tv_zuprice)
    TextView mTvZuprice;
    @BindView(R.id.tv_zuxiaoshu)
    TextView mTvZuxiaoshu;
    @BindView(R.id.tv_zuday)
    TextView mTvZuday;
    @BindView(R.id.tv_zudayprice)
    TextView mTvZudayprice;
    @BindView(R.id.tv_zudayxiaoshu)
    TextView mTvZudayxiaoshu;
    @BindView(R.id.ll_zu)
    LinearLayout mLlZu;
    @BindView(R.id.tv_huan)
    TextView mTvHuan;
    @BindView(R.id.ll_change)
    LinearLayout mLlChange;
    @BindView(R.id.ll_fineness)
    LinearLayout mLlFineness;
    @BindView(R.id.ll_express)
    LinearLayout mLlExpress;
    @BindView(R.id.ll_babayparame)
    LinearLayout mLlBabayparame;
    @BindView(R.id.ll_babayparame_out)
    LinearLayout mLlBabayparameOut;
    @BindView(R.id.line_coupon)
    View mLineCoupon;
    @BindView(R.id.ll_coupon)
    LinearLayout mLlCoupon;
    @BindView(R.id.line_format)
    View mLineFormat;
    @BindView(R.id.ll_format)
    LinearLayout mLlFormat;
    @BindView(R.id.line_detail)
    LinearLayout mLineDetail;
    @BindView(R.id.ll_detail)
    LinearLayout mLlDetail;
    @BindView(R.id.line_comment)
    LinearLayout mLineComment;
    @BindView(R.id.rv_comment)
    RecyclerView mRvComment;
    @BindView(R.id.ll_comment)
    LinearLayout mLlComment;
    @BindView(R.id.line_cos)
    LinearLayout mLineCos;
    @BindView(R.id.rv_cos)
    RecyclerView mRvCos;
    @BindView(R.id.ll_bottommenu)
    LinearLayout mLlBottommenu;
    @BindView(R.id.tv_newold)
    TextView mTvNewold;
    @BindView(R.id.tv_chengse)
    TextView mTvChengse;
    @BindView(R.id.tv_express)
    TextView mTvExpress;
    @BindView(R.id.tv_uptime)
    TextView mTvUptime;
    @BindView(R.id.tv_viewnumber)
    TextView mTvViewnumber;
    @BindView(R.id.iv_babayparame)
    ImageView mIvBabayparame;
    @BindView(R.id.dashline)
    View mDashline;
    @BindView(R.id.tv_zutao)
    TextView mTvZutao;
    @BindView(R.id.tv_jk)
    TextView mTvJk;
    @BindView(R.id.tv_xw)
    TextView mTvXw;
    @BindView(R.id.tv_xc)
    TextView mTvXc;
    @BindView(R.id.tv_yc)
    TextView mTvYc;
    @BindView(R.id.tv_yw)
    TextView mTvYw;
    @BindView(R.id.tv_kc)
    TextView mTvKc;
    @BindView(R.id.tv_sg)
    TextView mTvSg;
    @BindView(R.id.tv_tz)
    TextView mTvTz;
    @BindView(R.id.tv_tw)
    TextView mTvTw;
    @BindView(R.id.tv_rr)
    TextView mTvRr;
    @BindView(R.id.tv_hd)
    TextView mTvHd;
    @BindView(R.id.tv_bx)
    TextView mTvBx;
    @BindView(R.id.tv_yczs)
    TextView mTvYczs;
    @BindView(R.id.tv_coupon)
    TextView mTvCoupon;
    @BindView(R.id.rv_topline)
    RelativeLayout mRvTopline;
    @BindView(R.id.tv_guige)
    TextView mTvGuige;
    @BindView(R.id.tv_prddetail)
    TextView mTvPrddetail;


    private boolean isshowbabyparame = false;
    private BussnessBean mbussness;
    private boolean isbussness = false;
    private ArrayList<ShopBean> mShopBeen;
    private BussnessBean.GMemberOBJBean mObj;
    public ArrayList<String> formatname = new ArrayList<>();
    public ArrayList<String> formatvale1 = new ArrayList<>();
    public ArrayList<String> formatvale2 = new ArrayList<>();
    private int selectid1 = 0;
    private int selectid2 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        mDashline.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mbussness = intent.getParcelableExtra("id");


        if (mbussness != null) {
            //设置封面
            Glide.with(this).load(mbussness.getG_Cover()).into(mIvDetail);

            mTvTitle.setText(mbussness.getG_Title());
            if (mbussness.isG_IsFreeShip()) {
                //是包邮的操作
                mTvBaoyou.setVisibility(View.VISIBLE);

            } else {
                mTvBaoyou.setVisibility(View.GONE);
            }
            //设置价格
            String price = mbussness.getG_FixedPrice();
            int index = price.indexOf(".");
            String fixedPrice = price.substring(0, index);
            String fixedPriceDecimal = price.substring(index);
            mTvPrice.setText(fixedPrice);
            mTvDecimal.setText(fixedPriceDecimal);
            //设置多价格
            if (mbussness.isG_IsMorePrice()) {

                mTvMoreprice.setVisibility(View.VISIBLE);
            } else {

                mTvMoreprice.setVisibility(View.GONE);
            }

            //设置是否是租的商品
            if (mbussness.isG_IsRent()) {
                ViewGroup.LayoutParams params = mLlZu.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                mLlZu.setLayoutParams(params);
                String amount = mbussness.getG_CorrespAmount();
                int aount = amount.indexOf(".");
                String zuprice = amount.substring(0, aount);
                String zuxiaoshu = amount.substring(aount);

                mTvZuprice.setText(zuprice);
                mTvZuxiaoshu.setText(zuxiaoshu);
                String renewal = mbussness.getG_RenewalPrice();

                int renewa = renewal.indexOf(".");
                String zudayprice = renewal.substring(0, renewa);
                String zudayxiaoshu = renewal.substring(renewa);


                mTvZuday.setText("(" + mbussness.getG_BasicLease() + "天)+");
                mTvZudayprice.setText(zudayprice);
                mTvZudayxiaoshu.setText(zudayxiaoshu);


            } else {
                ViewGroup.LayoutParams params = mLlZu.getLayoutParams();
                params.height = 0;
                mLlZu.setLayoutParams(params);
            }

            //设置是否是换的商品
            if (mbussness.isG_IsChange()) {
                ViewGroup.LayoutParams params = mLlChange.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                mLlChange.setLayoutParams(params);
                //设置换的商品   所有的 都要显示出来
                String fk = mbussness.getDemand_FK();
                String[] changes = fk.split(",");
                for (String change : changes) {
                    final TextView inflate = (TextView) IdleDetailActivity.this.getLayoutInflater().inflate(R.layout.change_tv, null);
                    inflate.setText(change.substring(1));
                    final int i = Utils.dp2px(20);
                    // TODO: 2017/7/20 0020   动态修改宽高
                    ViewTreeObserver observer = mTvHuan.getViewTreeObserver();
                    observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @SuppressLint("NewApi")
                        @Override
                        public void onGlobalLayout() {
                            mTvHuan.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            int height = mTvHuan.getHeight();
                            inflate.setHeight(height);
                        }
                    });

                    mLlChange.addView(inflate);
                }


            } else {
                ViewGroup.LayoutParams params = mLlChange.getLayoutParams();
                params.height = 0;
                mLlChange.setLayoutParams(params);
            }

            //设置成色的信息
            String degree = mbussness.getG_NewOldDegree();
            Log.i("zc", "initView:   看看出问题的商品的id" + mbussness.getId());
            String neworold = null;
            if (degree.contains("成")) {
                neworold = degree.substring(0, degree.indexOf("成"));
                mTvNewold.setText(neworold);
                mTvChengse.setText("成新");
            } else {
                mTvNewold.setText(degree);
                mTvChengse.setText("");
            }

            //设置快递的价格
            mTvExpress.setText(mbussness.getG_CourierMoney() + "(成都 到 北京）");

            //设置发布时间
            mTvUptime.setText(mbussness.getG_UpTime().substring(0, 11));

            //设置看过的人数
            mTvViewnumber.setText(mbussness.getG_Hits() + "");

            //设置宝贝参数
            String parameter = mbussness.getGoods_parameter();
            String[] split = parameter.split(",");
            for (String s : split) {
                if (s.contains("组套情况")) {
                    mTvZutao.setText(s);
                } else if (s.contains("肩宽")) {
                    mTvJk.setText(s);
                } else if (s.contains("胸围")) {
                    mTvXw.setText(s);
                } else if (s.contains("袖长")) {
                    mTvXc.setText(s);
                } else if (s.contains("衣长(")) {
                    mTvYc.setText(s);
                } else if (s.contains("腰围")) {
                    mTvYw.setText(s);
                } else if (s.contains("裤长")) {
                    mTvKc.setText(s);
                } else if (s.contains("身高")) {
                    mTvSg.setText(s);
                } else if (s.contains("体重")) {
                    mTvTz.setText(s);
                } else if (s.contains("臀围")) {
                    mTvTw.setText(s);
                } else if (s.contains("面料柔软")) {
                    mTvRr.setText(s);
                } else if (s.contains("面料厚度")) {
                    mTvHd.setText(s);
                } else if (s.contains("版型指数")) {
                    mTvBx.setText(s);
                } else if (s.contains("衣长指数")) {
                    mTvYczs.setText(s);
                }
            }

            //设置优惠劵的数量
            mTvCoupon.setText(mbussness.getShop_coupon_count() + "");

            //查看是否有规格
            List<BussnessBean.GoodsSpecificationsBean> specifications = mbussness.getGoods_specifications();
            if (specifications.size() == 0) {
                mLlFormat.setVisibility(View.GONE);
            } else {
                mTvGuige.setText(specifications.get(0).getPS_AttributeNames());
            }


            //是否是商家 如果是商家  弹出的东西就要有变化 如果不是商家 直接就把bussness 传进去

            mObj = mbussness.getG_Member_OBJ();

            boolean business = mObj.isM_IsBusiness();

            if (business) {
                //发送网络请求 去请求到商家的信息
                Call<String> getshopdetail = Aplication.mIinterface.getshopdetail(mObj.getM_UID());
                getshopdetail.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();

                        if (body != null) {
                            Type type = new TypeToken<ArrayList<AvatorBean>>() {
                            }.getType();

                            ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                            AvatorBean bean = avatorBeen.get(0);
                            if (bean != null) {
                                String retmsg = bean.getRetmsg();
                                if (retmsg.contains("[") && retmsg != null) {

                                    Type type1 = new TypeToken<ArrayList<ShopBean>>() {
                                    }.getType();

                                    mShopBeen = Utils.gson.fromJson(retmsg, type1);

                                }


                            }


                        }


                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });


                isbussness = true;
            } else {


                isbussness = false;
            }


        }

        //设置商品详情的情况
        String remarks = mbussness.getG_DetailRemarks();
        if (remarks.contains("<span>")) {
            String replace = remarks.replace("<span>", "");
            String replace1 = replace.replace("</span>", "");
            mTvPrddetail.setText(replace1);
        } else {
            mTvPrddetail.setText(remarks);
        }
        //设置商品评价







    }

    @OnClick({R.id.iv_top_back, R.id.ll_share, R.id.ll_babayparame, R.id.ll_coupon, R.id.iv_topmenu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_top_back:
                //返回按钮
                finish();

                break;
            case R.id.ll_share:
                //分享按钮


                break;
            case R.id.ll_babayparame:
                //宝贝参数的按钮
                if (isshowbabyparame) {
                    mLlBabayparameOut.setVisibility(View.GONE);
                    mIvBabayparame.setImageResource(R.mipmap.com_icon_arr_right_gra);
                } else {
                    mLlBabayparameOut.setVisibility(View.VISIBLE);
                    mIvBabayparame.setImageResource(R.mipmap.com_icon_arr_down_gra);
                }

                isshowbabyparame = !isshowbabyparame;


                break;

            case R.id.ll_coupon:
                //优惠劵的点击事件


                break;

            case R.id.iv_topmenu:
                //顶部点击事件 和 规格点击事件相同

                showTopWindow();

                break;


        }
    }

    private void showTopWindow() {

        PopupWindow window = new PopupWindow(IdleDetailActivity.this);
        View inflate = View.inflate(IdleDetailActivity.this, R.layout.detail_popwindow, null);
        initPopData(inflate, window);
        WindowManager.LayoutParams lp = IdleDetailActivity.this.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        IdleDetailActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        IdleDetailActivity.this.getWindow().setAttributes(lp);

        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.home_toppop_bg));
        window.setTouchable(true);
        window.setContentView(inflate);
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setOutsideTouchable(true);
        window.update();
        window.showAtLocation(mRvTopline, Gravity.NO_GRAVITY, 0, 0);

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = IdleDetailActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                IdleDetailActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                IdleDetailActivity.this.getWindow().setAttributes(lp);

            }
        });


    }

    private void initPopData(View inflate, PopupWindow window) {
        ImageView ivbotoom = (ImageView) inflate.findViewById(R.id.iv_botoom);
        ImageView ivhome = (ImageView) inflate.findViewById(R.id.iv_home);
        ImageView ivshopcart = (ImageView) inflate.findViewById(R.id.iv_shopcart);
        ImageView ivmessage = (ImageView) inflate.findViewById(R.id.iv_message);
        ImageView ivusercenter = (ImageView) inflate.findViewById(R.id.iv_usercenter);
        final ImageView ivavator = (ImageView) inflate.findViewById(R.id.iv_avator);
        ImageView ivshop = (ImageView) inflate.findViewById(R.id.iv_shop);
        ImageView ivmanor = (ImageView) inflate.findViewById(R.id.iv_manor);


        TextView tvname = (TextView) inflate.findViewById(R.id.tv_name);
        TextView tvdizhi = (TextView) inflate.findViewById(R.id.tv_dizhi);
        TextView tvnumber = (TextView) inflate.findViewById(R.id.tv_number);
        TextView tvjiufen = (TextView) inflate.findViewById(R.id.tv_jiufen);
        TextView tvfirstscore = (TextView) inflate.findViewById(R.id.tv_firstscore);
        TextView tvpro = (TextView) inflate.findViewById(R.id.tv_pro);
        TextView tvsecandscore = (TextView) inflate.findViewById(R.id.tv_secandscore);
        TextView tvserver = (TextView) inflate.findViewById(R.id.tv_server);
        TextView tvthreescore = (TextView) inflate.findViewById(R.id.tv_threescore);
        TextView tvfinishspeed = (TextView) inflate.findViewById(R.id.tv_finishspeed);
        TextView tvformat1 = (TextView) inflate.findViewById(R.id.tv_format1);
        TextView tvformat2 = (TextView) inflate.findViewById(R.id.tv_format2);
        final TextView tvprice = (TextView) inflate.findViewById(R.id.tv_price);
        final TextView tvdecimal = (TextView) inflate.findViewById(R.id.tv_decimal);
        TextView tvmoreprice = (TextView) inflate.findViewById(R.id.tv_moreprice);
        final TextView tvzuprice = (TextView) inflate.findViewById(R.id.tv_zuprice);
        final TextView tvzuxiaoshu = (TextView) inflate.findViewById(R.id.tv_zuxiaoshu);
        final TextView tvzudayprice = (TextView) inflate.findViewById(R.id.tv_zudayprice);
        final TextView tvzudayxiaoshu = (TextView) inflate.findViewById(R.id.tv_zudayxiaoshu);
        final TextView tvzuday = (TextView) inflate.findViewById(R.id.tv_zuday);
        TextView tvprdnumber = (TextView) inflate.findViewById(R.id.tv_prdnumber);
        TextView tvleav = (TextView) inflate.findViewById(R.id.tv_leav);


        Button btup = (Button) inflate.findViewById(R.id.bt_up);
        Button btdown = (Button) inflate.findViewById(R.id.bt_down);
        View tagline = inflate.findViewById(R.id.tagline);

        LinearLayout llsearch = (LinearLayout) inflate.findViewById(R.id.ll_search);
        LinearLayout llzu = (LinearLayout) inflate.findViewById(R.id.ll_zu);

        final TagFlowLayout tag1 = (TagFlowLayout) inflate.findViewById(R.id.tag_1);
        final TagFlowLayout tag2 = (TagFlowLayout) inflate.findViewById(R.id.tag_2);

        //设置参数进去
        if (mObj.isM_IsBusiness()) {
            //是商家 设置商家的参数
            ViewGroup.LayoutParams params = ivavator.getLayoutParams();
            params.width = Utils.dp2px(104);
            params.height = Utils.dp2px(50);
            ShopBean bean = mShopBeen.get(0);
            Glide.with(this).load(bean.getS_Logo()).into(ivavator);
            tvname.setText(bean.getS_Name());
            ivmanor.setVisibility(View.GONE);
            String province = bean.getS_Com_Province();
            String city = bean.getS_Com_City();
            String sheng = province.substring(0, province.lastIndexOf("省"));
            String shi = city.substring(0, city.lastIndexOf("市"));
            String text = sheng + "- " + shi;
            tvdizhi.setText(text);

            tvfirstscore.setText("宝贝描述相符度");
            tvpro.setText(mbussness.getBbmsxfd_member_value());

            tvsecandscore.setText("卖家服务态度");
            tvserver.setText(mbussness.getMjfutd_member_value());

            tvthreescore.setText("卖家发货速度");
            tvfinishspeed.setText(mbussness.getMjfhsd_member_value());


        } else {
            //不是商家  设置个人的参数

            RequestOptions options = new RequestOptions().centerCrop();

            Glide.with(mContext).asBitmap().load(Utils.imgUrl + mObj.getM_Avatar()).apply(options).into(new BitmapImageViewTarget(ivavator) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivavator.setImageDrawable(circularBitmapDrawable);
                }
            });

            tvleav.setText("Lv." + mObj.getN_AllLevel());
            ivshop.setImageResource(R.mipmap.type_icon_user);
            tvname.setText(mObj.getM_UserName());
            tvdizhi.setText(mObj.getM_Province() + "-" + mObj.getM_City());

            if (mObj.getM_Sex().equals("男")) {
                ivmanor.setImageResource(R.mipmap.com_icon_male);
            } else {
                ivmanor.setImageResource(R.mipmap.com_icon_female);
            }

            tvfirstscore.setText("宝贝靠谱度");
            tvpro.setText(mbussness.getBbkpd_member_value());

            tvsecandscore.setText("卖家靠谱度");
            tvserver.setText(mbussness.getMjkpd_member_value());

            tvthreescore.setText("出租人靠谱度");
            tvfinishspeed.setText(mbussness.getCzrkpd_member_value());

        }

        //设置共通的参数

        //设置分数mObj.getOrder_deal_count()

        tvnumber.setText(mObj.getOrder_deal_count() + "");
        tvjiufen.setText("(纠纷比例:" + mObj.getS_DisputeProportion() + "%)");


        final List<BussnessBean.GoodsSpecificationsBean> specifications = mbussness.getGoods_specifications();
        Log.i("zc", "initPopData:    看看数据是什么样子的" + specifications.size());
        if (specifications.size() > 0) {
            formatname.clear();
            BussnessBean.GoodsSpecificationsBean bean = specifications.get(0);

            String names1 = bean.getPS_AttributeNames();
            String[] split = names1.split(",");
            for (String s : split) {
                formatname.add(s);
            }

            if (formatname.size() == 1) {
                formatvale1.clear();
                //就是有一个规格
                for (BussnessBean.GoodsSpecificationsBean specification : specifications) {
                    String values = specification.getPS_AttributeValues();
                    formatvale1.add(values);
                }
                tvformat1.setText(formatname.get(0));
                TagAdapter adapter = new TagAdapter(formatvale1) {
                    @Override
                    public View getView(FlowLayout parent, int position, Object o) {
                        TextView tv = (TextView) IdleDetailActivity.this.getLayoutInflater().inflate(R.layout.tv, tag1, false);
                        tv.setText(formatvale1.get(position));
                        return tv;
                    }
                };
                tag1.setAdapter(adapter);

                adapter.setSelectedList(selectid1);

                tag1.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet) {
                        for (Integer integer : selectPosSet) {
                            selectid1 = integer;
                            mTvGuige.setText(formatvale1.get(integer));
                            //点击以后 要设置价格
                            for (BussnessBean.GoodsSpecificationsBean specification : specifications) {
                                if (formatvale1.get(integer).equals(specification.getPS_AttributeValues())) {
                                    //重新去设置价格
                                    Log.i("zc", "getView:   看看进来了没有");
                                    String price = specification.getPS_FixedPrice();
                                    setFixPrice(tvprice, tvdecimal, price);
                                    String amount = specification.getPS_CorrespAmount();
                                    String renewal = specification.getPS_RenewalPrice();
                                    setRentPrice(tvzuprice, tvzuxiaoshu, tvzudayprice, tvzudayxiaoshu, tvzuday, amount, renewal);
                                }

                            }
                        }
                    }
                });
                tvformat2.setVisibility(View.GONE);
                tag2.setVisibility(View.GONE);

            } else if (formatname.size() == 2) {
                tvformat2.setVisibility(View.VISIBLE);
                tag2.setVisibility(View.VISIBLE);
                for (BussnessBean.GoodsSpecificationsBean specification : specifications) {
                    String values = specification.getPS_AttributeValues();
                    String[] split1 = values.split(",");

                    if (!formatvale1.contains(split1[0])) {
                        formatvale1.add(split1[0]);

                    }
                    if (!formatvale2.contains(split1[1])) {

                        formatvale2.add(split1[1]);

                    }

                }

                tvformat1.setText(formatname.get(0));
                TagAdapter adapter = new TagAdapter(formatvale1) {
                    @Override
                    public View getView(FlowLayout parent, int position, Object o) {
                        TextView tv = (TextView) IdleDetailActivity.this.getLayoutInflater().inflate(R.layout.tv, tag1, false);
                        tv.setText(formatvale1.get(position));
                        return tv;
                    }
                };
                tag1.setAdapter(adapter);

                TagAdapter adapter1 = new TagAdapter(formatvale2) {
                    @Override
                    public View getView(FlowLayout parent, int position, Object o) {
                        TextView tv = (TextView) IdleDetailActivity.this.getLayoutInflater().inflate(R.layout.tv, tag2, false);
                        tv.setText(formatvale2.get(position));
                        return tv;
                    }
                };
                tag2.setAdapter(adapter1);
                adapter.setSelectedList(selectid1);
                adapter1.setSelectedList(selectid2);
                tag1.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet) {
                        for (Integer integer : selectPosSet) {
                            selectid1 = integer;
                            mTvGuige.setText(formatvale1.get(integer));
                            //点击以后 去更改价格
                            for (BussnessBean.GoodsSpecificationsBean specification : specifications) {
                                String s = formatvale1.get(selectid1) + "," + formatvale2.get(selectid2);
                                if (s.equals(specification.getPS_AttributeValues())) {
                                    //去更改价格
                                    String price = specification.getPS_FixedPrice();
                                    setFixPrice(tvprice, tvdecimal, price);
                                    String amount = specification.getPS_CorrespAmount();
                                    String renewal = specification.getPS_RenewalPrice();
                                    setRentPrice(tvzuprice, tvzuxiaoshu, tvzudayprice, tvzudayxiaoshu, tvzuday, amount, renewal);
                                }
                            }

                        }
                    }
                });

                tvformat2.setText(formatname.get(1));

                tag2.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet) {
                        for (Integer integer : selectPosSet) {
                            selectid2 = integer;

                            mTvGuige.setText(formatvale1.get(integer) + ", " + formatvale2.get(integer));
                            //点击以后 去更改价格
                            for (BussnessBean.GoodsSpecificationsBean specification : specifications) {
                                String s = formatvale1.get(selectid1) + "," + formatvale2.get(selectid2);
                                if (s.equals(specification.getPS_AttributeValues())) {
                                    //去更改价格
                                    String price = specification.getPS_FixedPrice();
                                    setFixPrice(tvprice, tvdecimal, price);
                                    String amount = specification.getPS_CorrespAmount();
                                    String renewal = specification.getPS_RenewalPrice();
                                    setRentPrice(tvzuprice, tvzuxiaoshu, tvzudayprice, tvzudayxiaoshu, tvzuday, amount, renewal);
                                }
                            }


                        }
                    }
                });


            }
        } else if (specifications.size() == 0) {
            tvformat1.setVisibility(View.GONE);
            tag1.setVisibility(View.GONE);
            tvformat2.setVisibility(View.GONE);
            tag2.setVisibility(View.GONE);
            tagline.setVisibility(View.GONE);
        }


        //设置价格
        String price = mbussness.getG_FixedPrice();
        setFixPrice(tvprice, tvdecimal, price);


        //设置是否是租的商品
        if (mbussness.isG_IsRent()) {
           /* ViewGroup.LayoutParams params = llzu.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            llzu.setLayoutParams(params);*/
            llzu.setVisibility(View.VISIBLE);
            String amount = mbussness.getG_CorrespAmount();
            String renewal = mbussness.getG_RenewalPrice();
            setRentPrice(tvzuprice, tvzuxiaoshu, tvzudayprice, tvzudayxiaoshu, tvzuday, amount, renewal);


        } else {
        /*    ViewGroup.LayoutParams params = llzu.getLayoutParams();
            params.height = 0;
            llzu.setLayoutParams(params);*/

            llzu.setVisibility(View.GONE);
        }


    }

    private void setRentPrice(TextView tvzuprice, TextView tvzuxiaoshu, TextView tvzudayprice, TextView tvzudayxiaoshu, TextView tvzuday, String amount, String renewal) {
        int aount = amount.indexOf(".");
        String zuprice = amount.substring(0, aount);
        String zuxiaoshu = amount.substring(aount);

        tvzuprice.setText(zuprice);
        tvzuxiaoshu.setText(zuxiaoshu);

        int renewa = renewal.indexOf(".");
        String zudayprice = renewal.substring(0, renewa);
        String zudayxiaoshu = renewal.substring(renewa);


        tvzuday.setText("(" + mbussness.getG_BasicLease() + "天)+");
        tvzudayprice.setText(zudayprice);
        tvzudayxiaoshu.setText(zudayxiaoshu);
    }

    private void setFixPrice(TextView tvprice, TextView tvdecimal, String price) {
        int index = price.indexOf(".");
        String fixedPrice = price.substring(0, index);
        String fixedPriceDecimal = price.substring(index);
        tvprice.setText(fixedPrice);
        tvdecimal.setText(fixedPriceDecimal);
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }


}
