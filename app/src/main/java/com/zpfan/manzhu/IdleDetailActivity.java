package com.zpfan.manzhu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zpfan.manzhu.bean.BussnessBean;
import com.zpfan.manzhu.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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


    private boolean isshowbabyparame = false;


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
        BussnessBean id = intent.getParcelableExtra("id");

        mDashline.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        //设置封面
        Glide.with(this).load(id.getG_Cover()).into(mIvDetail);

        mTvTitle.setText(id.getG_Title());
        if (id.isG_IsFreeShip()) {
            //是包邮的操作
            mTvBaoyou.setVisibility(View.VISIBLE);

        } else {
            mTvBaoyou.setVisibility(View.GONE);
        }
        //设置价格
        String price = id.getG_FixedPrice();
        int index = price.indexOf(".");
        String fixedPrice = price.substring(0, index);
        String fixedPriceDecimal = price.substring(index);
        mTvPrice.setText(fixedPrice);
        mTvDecimal.setText(fixedPriceDecimal);
        //设置多价格
        if (id.isG_IsMorePrice()) {

            mTvMoreprice.setVisibility(View.VISIBLE);
        } else {

            mTvMoreprice.setVisibility(View.GONE);
        }

        //设置是否是租的商品
        if (id.isG_IsRent()) {
            ViewGroup.LayoutParams params = mLlZu.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            mLlZu.setLayoutParams(params);
            String amount = id.getG_CorrespAmount();
            int aount = amount.indexOf(".");
            String zuprice = amount.substring(0, aount);
            String zuxiaoshu = amount.substring(aount);

            mTvZuprice.setText(zuprice);
            mTvZuxiaoshu.setText(zuxiaoshu);
            String renewal = id.getG_RenewalPrice();

            int renewa = renewal.indexOf(".");
            String zudayprice = renewal.substring(0, renewa);
            String zudayxiaoshu = renewal.substring(renewa);


            mTvZuday.setText("(" + id.getG_BasicLease() + "天)+");
            mTvZudayprice.setText(zudayprice);
            mTvZudayxiaoshu.setText(zudayxiaoshu);


        } else {
            ViewGroup.LayoutParams params = mLlZu.getLayoutParams();
            params.height = 0;
            mLlZu.setLayoutParams(params);
        }

        //设置是否是换的商品
        if (id.isG_IsChange()) {
            ViewGroup.LayoutParams params = mLlChange.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            mLlChange.setLayoutParams(params);
            //设置换的商品   所有的 都要显示出来
            String fk = id.getDemand_FK();
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
        String degree = id.getG_NewOldDegree();
        Log.i("zc", "initView:   看看出问题的商品的id" + id.getId());
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
        mTvExpress.setText(id.getG_CourierMoney() + "(成都 到 北京）");

        //设置发布时间
        mTvUptime.setText(id.getG_UpTime().substring(0, 11));

        //设置看过的人数
        mTvViewnumber.setText(id.getG_Hits());


    }

    @OnClick({R.id.iv_top_back, R.id.ll_share, R.id.ll_babayparame})
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
        }
    }
}
