package com.zpfan.manzhu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMMessage;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zpfan.manzhu.adapter.CosAdapter;
import com.zpfan.manzhu.adapter.DetailCommentAdapter;
import com.zpfan.manzhu.adapter.ShareAdapter;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.BussnessBean;
import com.zpfan.manzhu.bean.CosBean;
import com.zpfan.manzhu.bean.CouponBean;
import com.zpfan.manzhu.bean.ShareBean;
import com.zpfan.manzhu.bean.ShopBean;
import com.zpfan.manzhu.myui.EaseActivity;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.myui.ShareActivity;
import com.zpfan.manzhu.utils.MyScrollView;
import com.zpfan.manzhu.utils.SPUtils;
import com.zpfan.manzhu.utils.ScrollViewListener;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.zpfan.manzhu.Aplication.mContext;

public class IdleDetailActivity extends AppCompatActivity {


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
    @BindView(R.id.tv_nocomment)
    TextView mTvNocomment;
    @BindView(R.id.bt_morecomment)
    Button mBtMorecomment;
    @BindView(R.id.tv_commentnumber)
    TextView mTvCommentnumber;
    @BindView(R.id.ll_babaycomment)
    LinearLayout mLlBabaycomment;
    @BindView(R.id.tv_impressionnumber)
    TextView mTvImpressionnumber;
    @BindView(R.id.ll_impression)
    LinearLayout mLlImpression;
    @BindView(R.id.iv_comment)
    ImageView mIvComment;
    @BindView(R.id.tv_commentt)
    TextView mTvCommentt;
    @BindView(R.id.iv_impression)
    ImageView mIvImpression;
    @BindView(R.id.tv_impressiont)
    TextView mTvImpressiont;
    @BindView(R.id.bt_morecos)
    Button mBtMorecos;
    @BindView(R.id.ll_callbussness)
    LinearLayout mLlCallbussness;
    @BindView(R.id.iv_shop)
    ImageView mIvShop;
    @BindView(R.id.tv_shoporuser)
    TextView mTvShoporuser;
    @BindView(R.id.ll_shop)
    LinearLayout mLlShop;
    @BindView(R.id.iv_collect)
    ImageView mIvCollect;
    @BindView(R.id.tv_collect)
    TextView mTvCollect;
    @BindView(R.id.ll_collect)
    LinearLayout mLlCollect;
    @BindView(R.id.ll_shopcart)
    LinearLayout mLlShopcart;
    @BindView(R.id.tv_makeorder)
    TextView mTvMakeorder;
    @BindView(R.id.myscrollview)
    MyScrollView mMyscrollview;
    @BindView(R.id.iv_icontop_back)
    ImageView mIvIcontopBack;
    @BindView(R.id.tv_icontop_text)
    TextView mTvIcontopText;
    @BindView(R.id.iv_top1)
    ImageView mIvTop1;
    @BindView(R.id.tv_top1)
    TextView mTvTop1;
    @BindView(R.id.ll_top1)
    LinearLayout mLlTop1;
    @BindView(R.id.iv_top2)
    ImageView mIvTop2;
    @BindView(R.id.tv_top2)
    TextView mTvTop2;
    @BindView(R.id.ll_top2)
    LinearLayout mLlTop2;
    @BindView(R.id.iv_top3)
    ImageView mIvTop3;
    @BindView(R.id.tv_top3)
    TextView mTvTop3;
    @BindView(R.id.ll_top3)
    LinearLayout mLlTop3;
    @BindView(R.id.iv_shaixuan)
    ImageView mIvShaixuan;
    @BindView(R.id.tv_shaixuan)
    TextView mTvShaixuan;
    @BindView(R.id.ll_top4)
    LinearLayout mLlTop4;
    @BindView(R.id.ll_topmenu)
    LinearLayout mLlTopmenu;
    @BindView(R.id.ll_topmen)
    LinearLayout mLlTopmen;
    @BindView(R.id.iv_menu)
    ImageView mIvMenu;
    @BindView(R.id.tv_pagetitle)
    TextView mTvPagetitle;
    @BindView(R.id.ll_newxiaoliang)
    LinearLayout mLlNewxiaoliang;
    @BindView(R.id.tv_brand)
    TextView mTvBrand;
    @BindView(R.id.ll_brand)
    LinearLayout mLlBrand;
    @BindView(R.id.tv_xiaoliang)
    TextView mTvXiaoliang;
    @BindView(R.id.tv_dingjin)
    TextView mTvDingjin;
    @BindView(R.id.tv_serverunit)
    TextView mTvServerunit;
    @BindView(R.id.iv_start1)
    ImageView mIvStart1;
    @BindView(R.id.iv_start2)
    ImageView mIvStart2;
    @BindView(R.id.iv_start3)
    ImageView mIvStart3;
    @BindView(R.id.iv_start4)
    ImageView mIvStart4;
    @BindView(R.id.iv_start5)
    ImageView mIvStart5;
    @BindView(R.id.tv_startnumber)
    TextView mTvStartnumber;
    @BindView(R.id.tv_salenumber)
    TextView mTvSalenumber;
    @BindView(R.id.ll_leve)
    LinearLayout mLlLeve;
    @BindView(R.id.ll_serverstart)
    LinearLayout mLlServerstart;
    @BindView(R.id.tv_kuaidi)
    TextView mTvKuaidi;
    @BindView(R.id.tv_idle)
    TextView mTvIdle;
    @BindView(R.id.line5)
    View mLine5;
    @BindView(R.id.iv_detail)
    BGABanner mIvDetail;


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
    private DetailCommentAdapter mAdapter;
    private int commenttype = 0;
    private ArrayList<CosBean> coslist = new ArrayList<>();
    private PopupWindow mWindow;
    private int mLineDetailTop;
    private int mIvDetailTop;
    private int mMLineCommentTop;
    private int mCostop;
    private String mSpecificationId = "";
    private ShopBean mShopBean;
    private int prdnumber = 1;
    private boolean isCollect = false;
    private String mType;
    private PopupWindow mPaywindow;
    private TextView mTvcancle;
    private TextView mTvshare;
    private RecyclerView mRvpopshare;
    private ShareAdapter mpopAdapter;
    private List<ShareBean> mShare;
    private ArrayList<CosBean> mCosBeen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idle_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ScrollViewListener listener = new ScrollViewListener() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                Rect rect = new Rect();
                boolean rect1 = mIvDetail.getGlobalVisibleRect(rect);
                if (rect1) {
                    //showTopBottom();
                    mLlTopmen.setVisibility(View.GONE);
                } else {
                    //hideTopBottom();
                    mLlTopmen.setVisibility(View.VISIBLE);
                }


            }
        };
        mMyscrollview.setListener(listener);


        Intent intent = getIntent();
        mDashline.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mbussness = intent.getParcelableExtra("id");
        mType = intent.getStringExtra("type");
        mRvCos.setLayoutManager(new LinearLayoutManager(IdleDetailActivity.this));

        if (mbussness != null) {
            //设置封面的轮播图

            ArrayList<String> imgs = new ArrayList<>();
            String images = mbussness.getG_Images();
            String[] split1 = images.split(",");
            for (String s : split1) {
                imgs.add(s);
            }

            mIvDetail.setAdapter(new BGABanner.Adapter<ImageView, String>() {
                @Override
                public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                    Glide.with(IdleDetailActivity.this).load(model).into(itemView);
                }
            });
            mIvDetail.setData(imgs,null);


            if (mbussness.isG_IsFreeShip()) {
                //是包邮的操作
                mTvBaoyou.setVisibility(View.VISIBLE);
                mTvTitle.setText(mbussness.getG_Title());
            } else {
                mTvBaoyou.setVisibility(View.GONE);
                mTvTitle.setText(mbussness.getG_Title());
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


                mTvZuday.setText("（" + mbussness.getG_BasicLease() + "天）+ ");
                mTvZudayprice.setText(zudayprice);
                mTvZudayxiaoshu.setText(zudayxiaoshu);


            } else {
                /*ViewGroup.LayoutParams params = mLlZu.getLayoutParams();
                params.height = 0;
                mLlZu.setLayoutParams(params);*/
                mLlZu.setVisibility(View.GONE);
            }

            //设置是否是换的商品
            if (mbussness.isG_IsChange()) {

                ViewGroup.LayoutParams params = mLlChange.getLayoutParams();
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                mLlChange.setLayoutParams(params);
                //设置换的商品   所有的 都要显示出来
                String fk = mbussness.getDemand_FK();
                String[] changes = fk.split(",");

                for (int i = 0; i < changes.length; i++) {
                    if (changes[i].length() > 1) {

                        String[] split = changes[i].split("\\|");

                        String s = split[2];
                        String type = "";
                        if (s.contains("服装")) {
                            type = "服";
                        } else if (s.contains("道具")) {
                            type = "具";
                        } else if (s.contains("假毛")) {
                            type = "毛";
                        } else if (s.contains("鞋靴")) {
                            type = "鞋";
                        } else if (s.contains("周边")) {
                            type = "其";
                        } else {
                            type = s.substring(1);
                        }

                        String chang = type + " | " + split[3];
                        final TextView inflate = (TextView) IdleDetailActivity.this.getLayoutInflater().inflate(R.layout.change_tv, null);
                        inflate.setText(chang);

                        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        if (i == 0) {
                            params1.setMargins(0, 0, 0, 0);
                        } else {
                            params1.setMargins(Utils.dp2px(10), 0, 0, 0);
                        }
                        inflate.setLayoutParams(params1);

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
                }


            } else {

                mLlChange.setVisibility(View.GONE);
            }

            if (mType.equals("idle")) {
                //设置成色的信息
                String degree = mbussness.getG_NewOldDegree();
                String neworold = null;
                if (degree.contains("成")) {
                    neworold = degree.substring(0, degree.indexOf("成"));
                    mTvNewold.setText(neworold);
                    mTvChengse.setText("成新");
                } else {
                    mTvNewold.setText(degree);
                    mTvChengse.setText("");
                }
                mLlNewxiaoliang.setVisibility(View.GONE);
                mLlFineness.setVisibility(View.VISIBLE);
            } else if (mType.equals("new")) {

                //把标题换了  是新商品
                mTvPagetitle.setText("新商品");
                mTvIcontopText.setText("新商品");
                //设置新商品的品牌名 和总销量 别的都一样
                mLlFineness.setVisibility(View.GONE);
                mLlNewxiaoliang.setVisibility(View.VISIBLE);
                String id = mbussness.getBrandID();
                if (id.isEmpty()) {
                    id = "";
                }
                mTvBrand.setText(id);

                mTvXiaoliang.setText(mbussness.getG_SaleNum() + "");
                //是否支持定金
                if (mbussness.isG_IsDepositDeal()) {
                    mTvDingjin.setVisibility(View.VISIBLE);
                    mTvDingjin.setText("（包含定金：¥ " + mbussness.getG_DepositPrice() + "）");
                }


            } else if (mType.equals("server")) {
                //进来展示服务的内容
                mTvFan.setVisibility(View.GONE);
                mTvServerunit.setVisibility(View.VISIBLE);
                mTvServerunit.setText(" / " + mbussness.getServer_unit_string());
                mLlNewxiaoliang.setVisibility(View.GONE);
                mTvExpress.setVisibility(View.GONE);
                //把标题换了  是新商品
                mTvPagetitle.setText("约单/服务");
                mTvIcontopText.setText("约单/服务");
                mTvBaoyou.setVisibility(View.GONE);
                mTvIdle.setVisibility(View.VISIBLE);

                boolean deal = mbussness.isG_IsOfflineDeal();
                if (deal) {
                    mTvIdle.setText("忙");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        mTvIdle.setBackground(mContext.getResources().getDrawable(R.drawable.item_photo_txt1));
                    }
                } else {
                    mTvIdle.setText("闲");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        mTvIdle.setBackground(mContext.getResources().getDrawable(R.drawable.item_photo_txt));
                    }
                }

                String fixedPrice1 = mbussness.getG_FixedPrice();

                if (fixedPrice1.equals("0.00")) {
                    mTvPrice.setText("无偿");
                    mTvDecimal.setText("");
                    mTvServerunit.setVisibility(View.GONE);
                    mTvQian.setVisibility(View.GONE);
                }


                mTvKuaidi.setText(mbussness.getG_Province() + "-" + mbussness.getG_City());

                if (mbussness.isG_IsServiceBook()) {
                    mTvMakeorder.setText("立即预约");
                }

                //展示评级的条目
                mLlServerstart.setVisibility(View.VISIBLE);
                String value = mbussness.getProfessionaldegree_value();
                mTvStartnumber.setText(value);
                mTvSalenumber.setText(mbussness.getG_SaleNum() + "");


                if (value.equals("0.5")) {
                    mIvStart1.setImageResource(R.mipmap.com_icon_star_half);
                    mIvStart2.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart3.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart4.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart5.setImageResource(R.mipmap.com_icon_star_off);
                } else if (value.equals("1.0")) {
                    mIvStart1.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart2.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart3.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart4.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart5.setImageResource(R.mipmap.com_icon_star_off);
                } else if (value.equals("0.0")) {
                    mIvStart1.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart2.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart3.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart4.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart5.setImageResource(R.mipmap.com_icon_star_off);
                } else if (value.equals("1.5")) {
                    mIvStart1.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart2.setImageResource(R.mipmap.com_icon_star_half);
                    mIvStart3.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart4.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart5.setImageResource(R.mipmap.com_icon_star_off);
                } else if (value.equals("2")) {
                    mIvStart1.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart2.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart3.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart4.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart5.setImageResource(R.mipmap.com_icon_star_off);
                } else if (value.equals("2.5")) {

                    mIvStart1.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart2.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart3.setImageResource(R.mipmap.com_icon_star_half);
                    mIvStart4.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart5.setImageResource(R.mipmap.com_icon_star_off);

                } else if (value.equals("3.0")) {

                    mIvStart1.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart2.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart3.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart4.setImageResource(R.mipmap.com_icon_star_off);
                    mIvStart5.setImageResource(R.mipmap.com_icon_star_off);

                } else if (value.equals("3.5")) {

                    mIvStart1.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart2.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart3.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart4.setImageResource(R.mipmap.com_icon_star_half);
                    mIvStart5.setImageResource(R.mipmap.com_icon_star_off);


                } else if (value.equals("4.0")) {
                    mIvStart1.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart2.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart3.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart4.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart5.setImageResource(R.mipmap.com_icon_star_off);
                } else if (value.equals("4.5")) {

                    mIvStart1.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart2.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart3.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart4.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart5.setImageResource(R.mipmap.com_icon_star_half);

                } else if (value.equals("5.0")) {
                    mIvStart1.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart2.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart3.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart4.setImageResource(R.mipmap.com_icon_star_on);
                    mIvStart5.setImageResource(R.mipmap.com_icon_star_on);
                }


            }


            //设置快递的价格
            String usercity = SPUtils.getInstance().getString("Usercity", "");
            mTvExpress.setText(mbussness.getG_CourierMoney() + "（"+ mbussness.getG_City() +" 到 "+usercity+"）");

            //设置发布时间
            String substring = Utils.timeChange(mbussness.getG_UpTime());
            mTvUptime.setText(substring);

            //设置看过的人数
            mTvViewnumber.setText(  Utils.numberChange(mbussness.getG_Hits() + "")  );

            //设置宝贝参数
            String parameter = mbussness.getGoods_parameter();
            Log.i("zc", "initView:  看看宝贝参数" + parameter);
            if (!parameter.isEmpty()) {
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
            } else {
                mLlBabayparame.setVisibility(View.GONE);
                mLine5.setVisibility(View.GONE);
            }

            //设置优惠劵的数量  发送网络请求去获取优惠劵的数量
            getCanUseCouponNumber();

            //发送网络请求 去查看是否收藏过该商品
            isCollection();


            //查看是否有规格
            List<BussnessBean.GoodsSpecificationsBean> specifications = mbussness.getGoods_specifications();
            if (specifications == null || specifications.size() == 0) {
                mLlFormat.setVisibility(View.GONE);
            } else {
                // 有规格 显示默认的规格
                for (BussnessBean.GoodsSpecificationsBean specification : specifications) {

                    if (specification.isPS_IsDefaultSelected()) {

                        mTvGuige.setText(specification.getPS_AttributeValues().replace(",", "，")); //显示默认的规格
                        mbussness.setMsp(specification);
                    }
                }

            }

            //是否是商家 如果是商家  弹出的东西就要有变化 如果不是商家 直接就把bussness 传进去

            mObj = mbussness.getG_Member_OBJ();


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
        //设置商品详情中显示的图片
        String images = mbussness.getG_Images();
        if (images != null) {
            String[] split = images.split(",");
            if (split.length > 0) {
                for (String s : split) {
                    ImageView img = new ImageView(IdleDetailActivity.this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(0, 0, 0, 20);
                    img.setLayoutParams(params);
                    img.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    Glide.with(IdleDetailActivity.this).load(s).into(img);
                    mLlDetail.addView(img);

                }


            }


        }


        //设置商品评价
        mRvComment.setLayoutManager(new LinearLayoutManager(IdleDetailActivity.this));
        List<BussnessBean.OrderReviewListBean> list = mbussness.getOrder_review_list();
        if (list != null) {
            if (list.size() > 0 && list.size() < 4) {
                mAdapter = new DetailCommentAdapter(R.layout.review_item, list, mbussness, 0);
                mRvComment.setVisibility(View.VISIBLE);
                mTvNocomment.setVisibility(View.GONE);
                mBtMorecomment.setVisibility(View.VISIBLE);
                mRvComment.setAdapter(mAdapter);
            } else if (list.size() >= 4) {
                ArrayList<BussnessBean.OrderReviewListBean> been = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    been.add(list.get(i));
                }
                mAdapter = new DetailCommentAdapter(R.layout.review_item, been, mbussness, 0);
                mRvComment.setVisibility(View.VISIBLE);
                mTvNocomment.setVisibility(View.GONE);
                mBtMorecomment.setVisibility(View.VISIBLE);
                mRvComment.setAdapter(mAdapter);

            } else {
                mRvComment.setVisibility(View.GONE);
                mTvNocomment.setVisibility(View.VISIBLE);
                mBtMorecomment.setVisibility(View.GONE);
            }
        }
        if (mbussness.getOrder_review_list() != null) {
            mTvCommentnumber.setText("（" + mbussness.getOrder_review_list().size() + "）");
        }


        if (mbussness.getOrder_sellerfigure() != null) {
            mTvImpressionnumber.setText("（" + mbussness.getOrder_sellerfigure().size() + "）");
        }
        //展示相关cos作品  要发送网络请求 需要字段
        String cosworks = mbussness.getGoods_cosworks();
        String[] split = cosworks.split(",");
        if (split.length > 1) {
            mBtMorecos.setVisibility(View.VISIBLE);
        } else {
            mBtMorecos.setVisibility(View.GONE);
        }

        setCosWork(cosworks);

        mShare = new ArrayList<>();
        mShare.add(new ShareBean(R.mipmap.share_icon_qq, "手机QQ", QQ.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_qzone, "QQ空间", QZone.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_weibo, "新浪微博", SinaWeibo.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_weixin, "微信", Wechat.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_pyq, "微信朋友圈", WechatMoments.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_haoyou, "发给好友"));
        mShare.add(new ShareBean(R.mipmap.share_icon_lianjie, "复制链接"));


        initPopData(mWindow);

    }



    private void isCollection() {
        Call<String> isCollection = Aplication.mIinterface.operaisCollection("商品", mbussness.getId() + "", Utils.getloginuid());

        isCollection.enqueue(new Callback<String>() {
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
                            mIvCollect.setImageResource(R.mipmap.com_icon_fav);
                            mTvCollect.setText("已收藏");
                            isCollect = true;
                        } else if (retmsg.equals("false")) {
                            mIvCollect.setImageResource(R.mipmap.com_icon_fav_gra);
                            isCollect = false;
                            mTvCollect.setText("收藏");
                        }

                    }


                } else {
                    mIvCollect.setImageResource(R.mipmap.com_icon_fav_gra);
                    mTvCollect.setText("收藏");
                    isCollect = false;
                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mIvCollect.setImageResource(R.mipmap.com_icon_fav_gra);
                mTvCollect.setText("收藏");
                isCollect = false;
            }
        });


    }

    private void getCanUseCouponNumber() {
        Call<String> getcouponlist = Aplication.mIinterface.getcouponlist(mbussness.getMember_UID(), Utils.getloginuid());
        getcouponlist.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();

                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                    if (avatorBeen != null) {
                        AvatorBean bean = avatorBeen.get(0);
                        String retmsg = bean.getRetmsg();
                        if (retmsg != null && retmsg.contains("[")) {
                            String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));

                            if (substring != null) {
                                Type type1 = new TypeToken<ArrayList<CouponBean>>() {
                                }.getType();
                                ArrayList<CouponBean> couponlist = Utils.gson.fromJson(substring, type1);
                                int cancoupon = 0;

                                for (CouponBean couponBean : couponlist) {
                                    if (couponBean.getMember_use_get_status().equals("未领用")) {
                                        cancoupon = cancoupon + 1;
                                    }
                                }
                                mTvCoupon.setText(" " + cancoupon + " ");


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

    /**
     * 获取相关cos 并显示的方法
     *
     * @param cosworks
     */
    private void setCosWork(String cosworks) {
        Call<String> getcosworkbygoodsmodle = Aplication.mIinterface.getcosworkbygoodsmodle("商品", "", cosworks);
        getcosworkbygoodsmodle.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> been = Utils.gson.fromJson(body, type);
                    if (been != null) {
                        AvatorBean bean = been.get(0);
                        String retmsg = bean.getRetmsg();
                        if (retmsg.contains("[")) {
                            String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));
                            Type type1 = new TypeToken<ArrayList<CosBean>>() {
                            }.getType();

                            mCosBeen = Utils.gson.fromJson(substring, type1);
                            ArrayList<CosBean> cosBeen = new ArrayList<>();
                            cosBeen.add(mCosBeen.get(0));
                            if (mCosBeen.size() > 0) {

                                CosAdapter adapter = new CosAdapter(R.layout.item_cosshow, cosBeen);
                                mRvCos.setAdapter(adapter);
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

    @Override
    protected void onResume() {
        super.onResume();
        getCanUseCouponNumber();
        WindowManager.LayoutParams lp = IdleDetailActivity.this.getWindow()
                .getAttributes();
        lp.alpha = 1f;
        IdleDetailActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        IdleDetailActivity.this.getWindow().setAttributes(lp);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        mIvDetailTop = mIvDetail.getTop(); //简介
        mLineDetailTop = mLineDetail.getTop(); //详情

        mMLineCommentTop = mLineComment.getTop();


        mCostop = mRvCos.getBottom();
    }


    @OnClick({R.id.iv_top_back, R.id.ll_share, R.id.ll_babayparame, R.id.ll_coupon, R.id.iv_topmenu, R.id.bt_morecomment
            , R.id.ll_impression, R.id.ll_babaycomment, R.id.ll_callbussness, R.id.ll_shop, R.id.ll_collect, R.id.ll_shopcart
            , R.id.tv_makeorder, R.id.ll_format, R.id.iv_icontop_back, R.id.iv_menu, R.id.ll_top2, R.id.ll_top1
            , R.id.ll_top3, R.id.ll_top4,R.id.bt_morecos

    })
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.ll_top4:

                mMyscrollview.smoothScrollTo(0, mCostop);
                break;
            case R.id.ll_top3:

                mMyscrollview.smoothScrollTo(0, mMLineCommentTop);
                break;

            case R.id.ll_top1:

                mMyscrollview.smoothScrollTo(0, mIvDetailTop);
                break;

            case R.id.ll_top2:
                mMyscrollview.smoothScrollTo(0, mLineDetailTop);

                break;

            case R.id.iv_top_back:
                //返回按钮
                finish();

                break;

            case R.id.iv_icontop_back:

                finish();
                break;

            case R.id.ll_share:
                //分享按钮
                Intent shareIntent = new Intent(IdleDetailActivity.this, ShareActivity.class);
                shareIntent.putExtra("bussnessbean", mbussness);
                startActivity(shareIntent);

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
                if (Utils.isUserLogin()) {
                    if (mbussness.getShop_coupon_count() == 0) {
                        MyToast.show("本商品暂无可领优惠劵", R.mipmap.com_icon_cross_w);
                    } else {
                        String uid = mbussness.getMember_UID();
                        Intent couponintent = new Intent(IdleDetailActivity.this, BussnessCouponActivity.class);
                        couponintent.putExtra("uid", uid);
                        startActivity(couponintent);
                    }
                } else {
                    startActivity(new Intent(IdleDetailActivity.this, LoginActivity.class));
                }


                break;

            case R.id.iv_menu:

            case R.id.ll_format:


            case R.id.iv_topmenu:
                //顶部点击事件 和 规格点击事件相同

                showTopWindow();

                break;
            case R.id.bt_morecomment:
                //查看更多评论
                if (commenttype == 0) {
                    List<BussnessBean.OrderReviewListBean> list1 = mbussness.getOrder_review_list();
                    if (list1.size() > 0) {
                        mAdapter = new DetailCommentAdapter(R.layout.review_item, list1, mbussness, 0);
                        mRvComment.setVisibility(View.VISIBLE);
                        mTvNocomment.setVisibility(View.GONE);
                        mRvComment.setAdapter(mAdapter);
                        mBtMorecomment.setVisibility(View.GONE);
                    } else {
                        mRvComment.setVisibility(View.GONE);
                        mTvNocomment.setVisibility(View.VISIBLE);
                        mBtMorecomment.setVisibility(View.GONE);
                    }
                } else if (commenttype == 1) {

                    List<BussnessBean.OrderReviewListBean> list = mbussness.getOrder_sellerfigure();
                    if (list.size() > 0) {
                        mAdapter = new DetailCommentAdapter(R.layout.review_item, list, mbussness, 1);
                        mRvComment.setVisibility(View.VISIBLE);
                        mTvNocomment.setVisibility(View.GONE);
                        mBtMorecomment.setVisibility(View.GONE);
                        mRvComment.setAdapter(mAdapter);
                    } else {
                        mRvComment.setVisibility(View.GONE);
                        mTvNocomment.setVisibility(View.VISIBLE);
                        mBtMorecomment.setVisibility(View.GONE);
                    }


                }


                break;

            case R.id.ll_impression:
                //显示卖家印象的条目
                mIvComment.setImageResource(R.mipmap.com_icon_prd_comm_a_ept);
                mTvCommentnumber.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mTvCommentt.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvImpression.setImageResource(R.mipmap.com_icon_prd_comm_b);
                mTvImpressiont.setTextColor(getResources().getColor(R.color.maintextcolor));
                mTvImpressionnumber.setTextColor(getResources().getColor(R.color.maintextcolor));

                List<BussnessBean.OrderReviewListBean> list = mbussness.getOrder_sellerfigure();
                if (list.size() > 0 && list.size() < 4) {
                    mAdapter = new DetailCommentAdapter(R.layout.review_item, list, mbussness, 1);
                    mRvComment.setVisibility(View.VISIBLE);
                    mTvNocomment.setVisibility(View.GONE);
                    mBtMorecomment.setVisibility(View.VISIBLE);
                    mRvComment.setAdapter(mAdapter);
                } else if (list.size() >= 4) {
                    ArrayList<BussnessBean.OrderReviewListBean> been = new ArrayList<>();
                    for (int i = 0; i < 3; i++) {
                        been.add(list.get(i));
                    }
                    mAdapter = new DetailCommentAdapter(R.layout.review_item, been, mbussness, 1);
                    mRvComment.setVisibility(View.VISIBLE);
                    mTvNocomment.setVisibility(View.GONE);
                    mBtMorecomment.setVisibility(View.VISIBLE);
                    mRvComment.setAdapter(mAdapter);

                } else {
                    mRvComment.setVisibility(View.GONE);
                    mTvNocomment.setVisibility(View.VISIBLE);
                    mBtMorecomment.setVisibility(View.GONE);
                }
                commenttype = 1;


                break;

            case R.id.ll_babaycomment:
                //显示宝贝平价店条目
                mIvComment.setImageResource(R.mipmap.com_icon_prd_comm_a);
                mTvCommentnumber.setTextColor(getResources().getColor(R.color.maintextcolor));
                mTvCommentt.setTextColor(getResources().getColor(R.color.maintextcolor));
                mIvImpression.setImageResource(R.mipmap.com_icon_prd_comm_b_ept);
                mTvImpressiont.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mTvImpressionnumber.setTextColor(getResources().getColor(R.color.secondtextcolor));

                List<BussnessBean.OrderReviewListBean> list1 = mbussness.getOrder_review_list();
                if (list1.size() > 0 && list1.size() < 4) {
                    mAdapter = new DetailCommentAdapter(R.layout.review_item, list1, mbussness, 0);
                    mRvComment.setVisibility(View.VISIBLE);
                    mTvNocomment.setVisibility(View.GONE);
                    mBtMorecomment.setVisibility(View.VISIBLE);
                    mRvComment.setAdapter(mAdapter);
                } else if (list1.size() >= 4) {
                    ArrayList<BussnessBean.OrderReviewListBean> been = new ArrayList<>();
                    for (int i = 0; i < 3; i++) {
                        been.add(list1.get(i));
                    }
                    mAdapter = new DetailCommentAdapter(R.layout.review_item, been, mbussness, 0);
                    mRvComment.setVisibility(View.VISIBLE);
                    mTvNocomment.setVisibility(View.GONE);
                    mBtMorecomment.setVisibility(View.VISIBLE);
                    mRvComment.setAdapter(mAdapter);

                } else {
                    mRvComment.setVisibility(View.GONE);
                    mTvNocomment.setVisibility(View.VISIBLE);
                    mBtMorecomment.setVisibility(View.GONE);
                }

                commenttype = 0;

                break;

            case R.id.ll_callbussness:
                //  点击了 联系卖家  先判断用户是否登陆 如果登陆的话 就跳转到聊天的界面
                if (Utils.isUserLogin()) {

                    Intent intent = new Intent(mContext, EaseActivity.class);

                    intent.putExtra("userId", mbussness.getG_ContactPhone());
                    intent.putExtra("usercn", mShopBeen.get(0).getS_Name());
                    intent.putExtra("chatType", EMMessage.ChatType.Chat);
                    startActivity(intent);

                } else {
                    startActivity(new Intent(IdleDetailActivity.this, LoginActivity.class));
                }

                break;

            case R.id.ll_shop:
                // 点击了店铺
                Toast.makeText(this, "跳转到店铺的详情", Toast.LENGTH_SHORT).show();

                break;

            case R.id.ll_collect:
                //点击了收藏
                if (Utils.isUserLogin()) {
                    //去执行收藏的方法

                    Call<String> operacollectionfunction = Aplication.mIinterface.operacollectionfunction("商品", mbussness.getId() + "", Utils.getloginuid());
                    operacollectionfunction.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String body = response.body();
                            if (body != null) {
                                Type type = new TypeToken<ArrayList<AvatorBean>>() {
                                }.getType();

                                ArrayList<AvatorBean> been = Utils.gson.fromJson(body, type);
                                if (been != null) {
                                    String retmsg = been.get(0).getRetmsg();
                                    if (retmsg.equals("true")) {
                                        if (!isCollect) {
                                            MyToast.show("收藏成功", R.mipmap.com_icon_check_w);
                                            mIvCollect.setImageResource(R.mipmap.com_icon_fav);
                                            mTvCollect.setText("已收藏");
                                        } else {
                                            MyToast.show("取消收藏成功", R.mipmap.com_icon_check_w);
                                            mIvCollect.setImageResource(R.mipmap.com_icon_fav_gra);
                                            mTvCollect.setText("收藏");
                                        }
                                        isCollect = !isCollect;

                                    } else {
                                        MyToast.show("收藏失败，您可能已经收藏过该商品", R.mipmap.com_icon_cross_w);
                                    }


                                }

                            }


                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });


                } else {
                    startActivity(new Intent(IdleDetailActivity.this, LoginActivity.class));
                }


                break;

            case R.id.ll_shopcart:
                //点击了购物车

                if (Utils.isUserLogin()) {
                    Call<String> operaaddupdateshopcart = Aplication.mIinterface.operaaddupdateshopcart("", Utils.getloginuid(), mbussness.getG_UID(), mSpecificationId, mShopBean.getM_UID(), prdnumber + "");

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
                    startActivity(new Intent(IdleDetailActivity.this, LoginActivity.class));
                }
                break;

            case R.id.tv_makeorder:
                //点击了 立即下单  展示出来一个 popwindow  只有闲置会显示出来pop
                String s = mTvMakeorder.getText().toString();
                if (Utils.isUserLogin()) {
                    if (!s.equals("立即预约")) {
                        if (mType.equals("idle")) {
                            final PopupWindow bootomwindow = new PopupWindow(IdleDetailActivity.this);
                            final View inflate = View.inflate(IdleDetailActivity.this, R.layout.detail_bootom_popwindow, null);
                            ImageView change = (ImageView) inflate.findViewById(R.id.iv_change);
                            ImageView rent = (ImageView) inflate.findViewById(R.id.iv_rent);

                            LinearLayout llchange = (LinearLayout) inflate.findViewById(R.id.ll_change);
                            LinearLayout llrent = (LinearLayout) inflate.findViewById(R.id.ll_rent);
                            LinearLayout llbuy = (LinearLayout) inflate.findViewById(R.id.ll_buy);

                            TextView tvchange = (TextView) inflate.findViewById(R.id.tv_change);
                            TextView tvchange1 = (TextView) inflate.findViewById(R.id.tv_change1);
                            TextView tvrent = (TextView) inflate.findViewById(R.id.tv_rent);
                            TextView tvrent1 = (TextView) inflate.findViewById(R.id.tv_rent1);

                            //是否能租赁
                            if (mbussness.isG_IsChange()) {
                                change.setImageResource(R.mipmap.com_icon_excha);
                                tvchange.setTextColor(getResources().getColor(R.color.maintextcolor));
                                tvchange1.setText("正好有卖家想换的宝贝，通知TA");
                                llchange.setEnabled(true);
                            } else {
                                change.setImageResource(R.mipmap.com_icon_excha_ept);
                                tvchange.setTextColor(getResources().getColor(R.color.secondtextcolor));
                                tvchange1.setText("本宝贝暂不支持交换");
                                llchange.setEnabled(false);
                            }

                            if (mbussness.isG_IsRent()) {
                                rent.setImageResource(R.mipmap.com_icon_rent);
                                tvrent.setTextColor(getResources().getColor(R.color.maintextcolor));
                                tvrent1.setText("只需支付租金，用完后还给卖家就行啦");
                                llrent.setEnabled(true);
                            } else {
                                rent.setImageResource(R.mipmap.com_icon_rent_ept);
                                tvrent.setTextColor(getResources().getColor(R.color.secondtextcolor));
                                tvrent1.setText("本宝贝暂不支持租赁");
                                llrent.setEnabled(false);
                            }


                            WindowManager.LayoutParams lp = IdleDetailActivity.this.getWindow()
                                    .getAttributes();
                            lp.alpha = 0.4f;
                            IdleDetailActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                            IdleDetailActivity.this.getWindow().setAttributes(lp);
                            bootomwindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.home_bootompop_bg));
                            bootomwindow.setTouchable(true);
                            bootomwindow.setContentView(inflate);
                            bootomwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                            int i = Utils.dp2px(75);
                            bootomwindow.setHeight(i);
                            bootomwindow.setOutsideTouchable(true);
                            bootomwindow.update();
                            bootomwindow.showAtLocation(mRvTopline, Gravity.BOTTOM, 0, 0);
                            bootomwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                                @Override
                                public void onDismiss() {
                                    WindowManager.LayoutParams lp = IdleDetailActivity.this.getWindow()
                                            .getAttributes();
                                    lp.alpha = 1f;
                                    IdleDetailActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                                    IdleDetailActivity.this.getWindow().setAttributes(lp);

                                }
                            });


                            //下单的界面
                            llbuy.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //点击跳转到下单的界面
                                    Intent intent = new Intent(IdleDetailActivity.this, OrderImmediatelyActivity.class);

                                    intent.putExtra("detail", mbussness);
                                    intent.putExtra("type", "buy");
                                    intent.putExtra("frome", mType); //在这里区分东西
                                    //aa
                                    startActivity(intent);
                                    bootomwindow.dismiss();

                                }
                            });

                            //租赁的界面
                            llrent.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //点击到租的订单的界面
                                    Intent intent = new Intent(IdleDetailActivity.this, OrderImmediatelyActivity.class);

                                    intent.putExtra("detail", mbussness);
                                    intent.putExtra("type", "rent");
                                    startActivity(intent);
                                    bootomwindow.dismiss();
                                }
                            });
                            //交换的界面
                            llchange.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(IdleDetailActivity.this, ChangActivity.class);

                                    intent.putExtra("detail", mbussness);
                                    intent.putExtra("shop", mShopBean);
                                    startActivity(intent);
                                    bootomwindow.dismiss();


                                }
                            });
                        } else {

                            //点击跳转到下单的界面
                            Intent intent = new Intent(IdleDetailActivity.this, OrderImmediatelyActivity.class);

                            intent.putExtra("detail", mbussness);
                            intent.putExtra("type", "buy");
                            intent.putExtra("frome", mType); //在这里区分东西
                            //aa
                            startActivity(intent);


                        }


                    } else {
                        //立即预约的操作
                        Intent intent = new Intent(IdleDetailActivity.this, OrderImmediatelyActivity.class);

                        intent.putExtra("detail", mbussness);
                        intent.putExtra("type", "server");
                        startActivity(intent);


                    }
                } else {
                    startActivity(new Intent(IdleDetailActivity.this, LoginActivity.class));
                }


                break;
            case R.id.bt_morecos:
                //点击更多cos的操作

                CosAdapter adapter = new CosAdapter(R.layout.item_cosshow, mCosBeen);
                mRvCos.setAdapter(adapter);
                mBtMorecos.setVisibility(View.GONE);

                break;

        }
    }




    @Override
    protected void onPause() {
        super.onPause();
        WindowManager.LayoutParams lp = IdleDetailActivity.this.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        IdleDetailActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        IdleDetailActivity.this.getWindow().setAttributes(lp);
    }

    private void showTopWindow() {

        WindowManager.LayoutParams lp = IdleDetailActivity.this.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        IdleDetailActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        IdleDetailActivity.this.getWindow().setAttributes(lp);


        mWindow.showAtLocation(mRvTopline, Gravity.TOP, 0, 0);

        mWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
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

    private void initPopData(final PopupWindow window) {

        mWindow = new PopupWindow(IdleDetailActivity.this);
        View inflate = View.inflate(IdleDetailActivity.this, R.layout.detail_popwindow, null);

        ImageView ivbotoom = (ImageView) inflate.findViewById(R.id.iv_botoom);
        ImageView ivhome = (ImageView) inflate.findViewById(R.id.iv_home);
        ImageView ivshopcart = (ImageView) inflate.findViewById(R.id.iv_shopcart);
        ImageView ivmessage = (ImageView) inflate.findViewById(R.id.iv_message);
        ImageView ivusercenter = (ImageView) inflate.findViewById(R.id.iv_usercenter);
        final ImageView ivavator = (ImageView) inflate.findViewById(R.id.iv_avator);
        ImageView ivshop = (ImageView) inflate.findViewById(R.id.iv_shop);
        final ImageView ivmanor = (ImageView) inflate.findViewById(R.id.iv_manor);
        View line = inflate.findViewById(R.id.dashline);
        line.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        View close = inflate.findViewById(R.id.close);
        mWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.detail_toppop_bg));
        mWindow.setTouchable(true);
        mWindow.setContentView(inflate);
        mWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mWindow.setOutsideTouchable(true);
        mWindow.update();


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        ivbotoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });


        ivshopcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isUserLogin()) {
                    startActivity(new Intent(IdleDetailActivity.this, ShopCarActivity.class));
                } else {
                    startActivity(new Intent(IdleDetailActivity.this, LoginActivity.class));
                }
            }
        });

        ivmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isUserLogin()) {

                    Intent intent = new Intent(mContext, EaseActivity.class);
                    intent.putExtra("userId", mbussness.getG_ContactPhone());

                    if (mShopBeen != null) {
                        intent.putExtra("usercn", mShopBeen.get(0).getS_Name());
                    } else {
                        intent.putExtra("usercn", mbussness.getG_Member_OBJ().getM_UserName());
                    }
                    intent.putExtra("chatType", EMMessage.ChatType.Chat);
                    startActivity(intent);
                    window.dismiss();
                } else {
                    startActivity(new Intent(IdleDetailActivity.this, LoginActivity.class));
                    window.dismiss();
                }


            }
        });

        ivusercenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isUserLogin()) {
                    startActivity(new Intent(IdleDetailActivity.this, UserCenterActivity.class));
                } else {
                    startActivity(new Intent(IdleDetailActivity.this, LoginActivity.class));
                }


            }
        });
        ivhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(IdleDetailActivity.this, MainActivity.class));

            }
        });


        final TextView tvname = (TextView) inflate.findViewById(R.id.tv_name);
        final TextView tvdizhi = (TextView) inflate.findViewById(R.id.tv_dizhi);
        TextView tvnumber = (TextView) inflate.findViewById(R.id.tv_number);
        TextView tvjiufen = (TextView) inflate.findViewById(R.id.tv_jiufen);
        final TextView tvfirstscore = (TextView) inflate.findViewById(R.id.tv_firstscore);
        final TextView tvpro = (TextView) inflate.findViewById(R.id.tv_pro);
        final TextView tvsecandscore = (TextView) inflate.findViewById(R.id.tv_secandscore);
        final TextView tvserver = (TextView) inflate.findViewById(R.id.tv_server);
        final TextView tvthreescore = (TextView) inflate.findViewById(R.id.tv_threescore);
        final TextView tvfinishspeed = (TextView) inflate.findViewById(R.id.tv_finishspeed);
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
        final TextView tvprdnumber = (TextView) inflate.findViewById(R.id.tv_prdnumber);
        final TextView tvleav = (TextView) inflate.findViewById(R.id.tv_leav);


        final RelativeLayout btup = (RelativeLayout) inflate.findViewById(R.id.bt_up);
        RelativeLayout btdown = (RelativeLayout) inflate.findViewById(R.id.bt_down);
        View tagline = inflate.findViewById(R.id.tagline);

        LinearLayout llsearch = (LinearLayout) inflate.findViewById(R.id.ll_search);
        LinearLayout llzu = (LinearLayout) inflate.findViewById(R.id.ll_zu);

        final TagFlowLayout tag1 = (TagFlowLayout) inflate.findViewById(R.id.tag_1);
        final TagFlowLayout tag2 = (TagFlowLayout) inflate.findViewById(R.id.tag_2);

        btup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prdnumber = prdnumber + 1;
                if (prdnumber > mbussness.getG_StockNum()) {
                    prdnumber = mbussness.getG_StockNum();
                    MyToast.show("已经是最大库存了", R.mipmap.com_icon_cross_w);
                    tvprdnumber.setText(prdnumber + "");
                    mbussness.setBuyCount(prdnumber);
                    btup.setEnabled(false);
                } else {
                    tvprdnumber.setText(prdnumber + "");
                    mbussness.setBuyCount(prdnumber);
                }

            }
        });
        btdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prdnumber == 1) {
                } else {
                    btup.setEnabled(true);
                    prdnumber = prdnumber - 1;
                    tvprdnumber.setText((prdnumber) + "");
                    mbussness.setBuyCount(prdnumber);
                }

            }
        });


        //设置参数进去
        if (mbussness.getShowtype() == 2) {
            //是商家 设置商家的参数
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
                                if (mShopBeen != null && mShopBeen.size() > 0) {
                                    mShopBean = mShopBeen.get(0);
                                    if (mShopBean != null) {
                                        ViewGroup.LayoutParams params = ivavator.getLayoutParams();
                                        params.width = Utils.dp2px(104);
                                        params.height = Utils.dp2px(50);

                                        Glide.with(IdleDetailActivity.this).load(mShopBean.getS_Logo()).into(ivavator);
                                        tvname.setText(mShopBean.getS_Name());
                                        ivmanor.setVisibility(View.GONE);
                                        String province = mShopBean.getS_Com_Province();
                                        String city = mShopBean.getS_Com_City();
                                        String sheng = province.substring(0, province.lastIndexOf("省"));
                                        String shi = city.substring(0, city.lastIndexOf("市"));
                                        String text = sheng + "-" + shi;
                                        tvdizhi.setText(text);
                                        tvleav.setText("Lv." + mbussness.getG_Member_OBJ().getN_AllLevel());






                                    }
                                }

                            }


                        }


                    }


                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });


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
            String province = mObj.getM_Province();
            String city = mObj.getM_City();
            String sheng = null;
            if (province.contains("省")) {
                sheng = province.substring(0, province.lastIndexOf("省"));
            }
            String shi = null;
            if (city.contains("市")) {
                shi = city.substring(0, city.lastIndexOf("市"));
            }
            String text = "（" + sheng + "-" + shi + "）";
            tvdizhi.setText(text);

            if (mObj.getM_Sex().equals("男")) {
                ivmanor.setImageResource(R.mipmap.com_icon_male);
            } else {
                ivmanor.setImageResource(R.mipmap.com_icon_female);
            }

           /* tvfirstscore.setText("  宝贝靠谱度  ");
            tvpro.setText(mbussness.getBbkpd_member_value() + "  ");

            tvsecandscore.setText("  卖家靠谱度  ");
            tvserver.setText(mbussness.getMjkpd_member_value() + "  ");

            tvthreescore.setText("  出租人靠谱度  ");
            tvfinishspeed.setText(mbussness.getCzrkpd_member_value() + "  ");*/

        }

        //设置共通的参数


        if (mbussness.getG_Type().equals("新商品")) {
            tvfirstscore.setText("宝贝描述相符度  ");
            String value = mbussness.getBbmsxfd_member_value();
            if (value.equals("0")) {
                value = "0.0";
            }
            tvpro.setText(value + "  ");

            tvsecandscore.setText("  卖家服务态度  ");
            String value1 = mbussness.getMjfutd_member_value();
            if (value1.equals("0")) {
                value1 = "0.0";
            }
            tvserver.setText(value1 + "  ");

            tvthreescore.setText("  卖家发货速度  ");
            String value2 = mbussness.getMjfhsd_member_value();

            if (value2.equals("0")) {
                value2 = "0.0";
            }

            tvfinishspeed.setText(value2 + "  ");
        } else if (mbussness.getG_Type().equals("二手商品")) {
            tvfirstscore.setText("宝贝靠谱度  ");
            String value = mbussness.getBbkpd_member_value();
            if (value.equals("0")) {
                value = "0.0";
            }
            tvpro.setText(value + "  ");

            tvsecandscore.setText("  卖家靠谱度  ");
            String value1 = mbussness.getMjkpd_member_value();
            if (value1.equals("0")) {
                value1 = "0.0";
            }
            tvserver.setText(value1 + "  ");

            tvthreescore.setText("  出租人靠谱度  ");
            String value2 = mbussness.getCzrkpd_member_value();

            if (value2.equals("0")) {
                value2 = "0.0";
            }

            tvfinishspeed.setText(value2 + "  ");


        } else if (mbussness.getG_Type().equals("服务")) {
            tvfirstscore.setText("专业程度  ");
            String value = mbussness.getProfessionaldegree_value();
            if (value.equals("0")) {
                value = "0.0";
            }
            tvpro.setText(value + "  ");

            tvsecandscore.setText("  服务态度  ");
            String value1 = mbussness.getAttitude_number_value();
            if (value1.equals("0")) {
                value1 = "0.0";
            }
            tvserver.setText(value1 + "  ");

            tvthreescore.setText("  完成速度  ");
            String value2 = mbussness.getComplatespeed_value();

            if (value2.equals("0")) {
                value2 = "0.0";
            }

            tvfinishspeed.setText(value2 + "  ");




        }



        //设置分数mObj.getOrder_deal_count()
        tvnumber.setText(mObj.getOrder_deal_count() + "");
        tvjiufen.setText("（纠纷比例：" + mObj.getS_DisputeProportion() + "%）");


        final List<BussnessBean.GoodsSpecificationsBean> specifications = mbussness.getGoods_specifications();

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
                        TextView tv = (TextView) IdleDetailActivity.this.getLayoutInflater().inflate(R.layout.idle_detil_tv, tag1, false);
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
                            mTvGuige.setText(formatvale1.get(integer).replace(",", "，"));
                            //点击以后 要设置价格
                            for (BussnessBean.GoodsSpecificationsBean specification : specifications) {
                                if (formatvale1.get(integer).equals(specification.getPS_AttributeValues())) {
                                    //重新去设置价格

                                    String price = specification.getPS_FixedPrice();
                                    setFixPrice(tvprice, tvdecimal, price);
                                    String amount = specification.getPS_CorrespAmount();
                                    String renewal = specification.getPS_RenewalPrice();
                                    setRentPrice(tvzuprice, tvzuxiaoshu, tvzudayprice, tvzudayxiaoshu, tvzuday, amount, renewal);
                                    mSpecificationId = specification.getPS_UniqueID();
                                    mbussness.setMsp(specification);
                                }

                            }
                        }
                    }
                });
                mTvGuige.setText(formatvale1.get(selectid1).replace(",", "，"));
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
                        TextView tv = (TextView) IdleDetailActivity.this.getLayoutInflater().inflate(R.layout.idle_detil_tv, tag1, false);
                        tv.setText(formatvale1.get(position));
                        return tv;
                    }
                };
                tag1.setAdapter(adapter);

                TagAdapter adapter1 = new TagAdapter(formatvale2) {
                    @Override
                    public View getView(FlowLayout parent, int position, Object o) {
                        TextView tv = (TextView) IdleDetailActivity.this.getLayoutInflater().inflate(R.layout.idle_detil_tv, tag2, false);
                        tv.setText(formatvale2.get(position));
                        return tv;
                    }
                };
                tag2.setAdapter(adapter1);
                adapter.setSelectedList(selectid1);
                adapter1.setSelectedList(selectid2);
                mTvGuige.setText(formatvale1.get(selectid1) + "，" + formatvale2.get(selectid2));
                tag1.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                    @Override
                    public void onSelected(Set<Integer> selectPosSet) {
                        for (Integer integer : selectPosSet) {
                            selectid1 = integer;
                            mTvGuige.setText(formatvale1.get(integer).replace(",", "，"));
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
                                    mSpecificationId = specification.getPS_UniqueID();
                                    mbussness.setMsp(specification);
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
                                    mSpecificationId = specification.getPS_UniqueID();
                                    mbussness.setMsp(specification);
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


        tvzuday.setText("（" + mbussness.getG_BasicLease() + "天）+ ");
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
