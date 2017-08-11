package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zpfan.manzhu.adapter.IdelAdapter;
import com.zpfan.manzhu.adapter.NewAdapter;
import com.zpfan.manzhu.adapter.PhotoAdapter;
import com.zpfan.manzhu.adapter.ShopCouponAdapter;
import com.zpfan.manzhu.adapter.ShopTuiJianAdapter;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.BussnessBean;
import com.zpfan.manzhu.bean.ShopBean;
import com.zpfan.manzhu.myui.EaseActivity;
import com.zpfan.manzhu.myui.GlideImageLoader;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopDetialActivity extends AppCompatActivity {

    @BindView(R.id.iv_topback)
    ImageView mIvTopback;
    @BindView(R.id.iv_topmenu)
    ImageView mIvTopmenu;
    @BindView(R.id.iv_cover)
    RoundedImageView mIvCover;
    @BindView(R.id.tv_userlv)
    TextView mTvUserlv;
    @BindView(R.id.iv_shop)
    ImageView mIvShop;
    @BindView(R.id.tv_shopname)
    TextView mTvShopname;
    @BindView(R.id.tv_shopcity)
    TextView mTvShopcity;
    @BindView(R.id.ll_shopname)
    LinearLayout mLlShopname;
    @BindView(R.id.tv_shoptype1)
    TextView mTvShoptype1;
    @BindView(R.id.tv_shoptype2)
    TextView mTvShoptype2;
    @BindView(R.id.iv_shophome)
    ImageView mIvShophome;
    @BindView(R.id.tv_shophome)
    TextView mTvShophome;
    @BindView(R.id.ll_shophome)
    LinearLayout mLlShophome;
    @BindView(R.id.iv_shopnew)
    ImageView mIvShopnew;
    @BindView(R.id.tv_shopnew)
    TextView mTvShopnew;
    @BindView(R.id.ll_shopnew)
    LinearLayout mLlShopnew;
    @BindView(R.id.iv_shopserver)
    ImageView mIvShopserver;
    @BindView(R.id.tv_shopserver)
    TextView mTvShopserver;
    @BindView(R.id.ll_shopnserver)
    LinearLayout mLlShopnserver;
    @BindView(R.id.iv_shopidle)
    ImageView mIvShopidle;
    @BindView(R.id.tv_shopidle)
    TextView mTvShopidle;
    @BindView(R.id.ll_shopidle)
    LinearLayout mLlShopidle;
    @BindView(R.id.shophomebanner)
    Banner mShophomebanner;
    @BindView(R.id.rv_coupon)
    RecyclerView mRvCoupon;
    @BindView(R.id.line_detail)
    LinearLayout mLineDetail;
    @BindView(R.id.rv_tuijian)
    RecyclerView mRvTuijian;
    @BindView(R.id.ll_contactshop)
    LinearLayout mLlContactshop;
    @BindView(R.id.ll_introduction)
    LinearLayout mLlIntroduction;
    @BindView(R.id.iv_collection)
    ImageView mIvCollection;
    @BindView(R.id.tv_collection)
    TextView mTvCollection;
    @BindView(R.id.ll_collection)
    LinearLayout mLlCollection;
    @BindView(R.id.tv_notuijian)
    TextView mTvNotuijian;
    @BindView(R.id.rv_goods)
    RecyclerView mRvGoods;
    @BindView(R.id.rl_top)
    RelativeLayout mRlTop;
    private ShopBean mShopbean;
    private boolean isCollection = false;
    private String mGtype = "二手商品";
    private boolean isShowCoupon = false;
    private PopupWindow mWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detial);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        //对标题 图标 店铺名称和所在地 进行初始化
        Intent intent = getIntent();
        mShopbean = intent.getParcelableExtra("shopbean");
        mTvShopname.setText(mShopbean.getS_Name());
        mTvUserlv.setText("Lv." + mShopbean.getS_LevelNumber());
        mTvShopcity.setText("（" + mShopbean.getS_Com_City() + "）");
        String category = mShopbean.getShop_category();

        if (category.contains("|")) {
            String[] split = category.split("\\|");

            mTvShoptype1.setText(split[0]);
            mTvShoptype2.setText(split[1]);

        } else {
            mTvShoptype2.setVisibility(View.GONE);
            mTvShoptype1.setText(category);
        }

        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.mipmap.u583);
        images.add(R.mipmap.u684);
        images.add(R.mipmap.u5027);

        //设置图片加载器
        mShophomebanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mShophomebanner.setImages(images);
        mShophomebanner.setIndicatorGravity(BannerConfig.RIGHT);
        //banner设置方法全部调用完毕时最后调用
        mShophomebanner.isAutoPlay(true);
        mShophomebanner.start();


        //优惠劵的设置
        ArrayList<ShopBean.ShopcouponlistBean> shopcouponlist = mShopbean.getShopcouponlist();
        if (shopcouponlist.size() > 0) {
            LinearLayoutManager layout = new LinearLayoutManager(ShopDetialActivity.this);
            layout.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRvCoupon.setLayoutManager(layout);
            ShopCouponAdapter adapter = new ShopCouponAdapter(R.layout.item_shop_coupon, shopcouponlist);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (Utils.isUserLogin()) {

                        String uid = mShopbean.getM_UID();
                        Intent couponintent = new Intent(ShopDetialActivity.this, BussnessCouponActivity.class);
                        couponintent.putExtra("uid", uid);
                        startActivity(couponintent);

                    } else {
                        startActivity(new Intent(ShopDetialActivity.this, LoginActivity.class));
                    }
                }
            });
            mRvCoupon.setAdapter(adapter);
            isShowCoupon = true;

        } else {
            //隐藏优惠劵的列表
            mRvCoupon.setVisibility(View.GONE);
            isShowCoupon = false;
        }

        //看看店铺是否有推荐的商品
        boolean goods = mShopbean.isShop_is_recommed_goods();
        if (goods) {
            mRvTuijian.setVisibility(View.VISIBLE);
            mRvTuijian.setLayoutManager(new LinearLayoutManager(ShopDetialActivity.this));
            final ArrayList<BussnessBean> goodslist = mShopbean.getRecommended_goodslist();
            for (BussnessBean bean : goodslist) {
                String type = bean.getG_Type();
                Log.i("zc", "initView:   看看数据" + type);
                if (type.equals("二手商品")) {
                    bean.setItemType(BussnessBean.IDLE);
                } else if (type.equals("新商品")) {
                    bean.setItemType(BussnessBean.NEW);
                } else if (type.equals("服务")) {
                    bean.setItemType(BussnessBean.SERVER);
                }

            }

            ShopTuiJianAdapter adapter = new ShopTuiJianAdapter(goodslist);
            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    int type = adapter.getItemViewType(position);
                    if (type == BussnessBean.NEW) {
                        Intent intent = new Intent(ShopDetialActivity.this, IdleDetailActivity.class);
                        intent.putExtra("id", goodslist.get(position));
                        intent.putExtra("type", "new");
                        startActivity(intent);
                    } else if (type == BussnessBean.IDLE) {


                    } else if (type == BussnessBean.SERVER) {
                        Intent intent = new Intent(ShopDetialActivity.this, IdleDetailActivity.class);
                        intent.putExtra("id", goodslist.get(position));
                        intent.putExtra("type", "server");
                        startActivity(intent);

                    }


                }
            });
            mRvTuijian.setAdapter(adapter);


        } else {
            //显示没有设置推荐的页面
            mRvTuijian.setVisibility(View.GONE);
            mTvNotuijian.setVisibility(View.VISIBLE);
        }

        //获取用户是否有收藏这个店铺
        Call<String> iscollection = Aplication.mIinterface.operaisCollection("店铺", mShopbean.getId() + "", Utils.getloginuid());

        iscollection.enqueue(new Callback<String>() {
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
                            mIvCollection.setImageResource(R.mipmap.com_icon_fav);
                            mTvCollection.setText("已收藏");
                            isCollection = true;
                        } else if (retmsg.equals("false")) {
                            mIvCollection.setImageResource(R.mipmap.com_icon_fav_gra);
                            mTvCollection.setText("收藏店铺");
                            isCollection = false;
                        }
                    }

                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mIvCollection.setImageResource(R.mipmap.com_icon_fav_gra);
            }
        });


    }

    @OnClick({R.id.iv_topback, R.id.iv_topmenu, R.id.ll_shophome, R.id.ll_shopnew, R.id.ll_shopnserver, R.id.ll_shopidle, R.id.ll_contactshop, R.id.ll_introduction, R.id.ll_collection})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_topback:
                finish();
                break;
            case R.id.iv_topmenu:
                //和详情的操作一样 都是打开上面的布局
            case R.id.ll_introduction:
                showTopWindow();


                break;
            case R.id.ll_shophome:
                selectTag(0);
                showHome();

                break;
            case R.id.ll_shopnew:
                //展示店铺的新商品列表
                selectTag(1);
                hideHome();
                mGtype = "新商品";
                getshopGoodList();


                break;
            case R.id.ll_shopnserver:
                selectTag(2);
                hideHome();
                mGtype = "服务";
                getshopGoodList();
                break;
            case R.id.ll_shopidle:
                selectTag(3);
                hideHome();
                mGtype = "二手商品";
                getshopGoodList();
                break;
            case R.id.ll_contactshop:
                //联系店铺的操作
                if (Utils.isUserLogin()) {

                    Intent intent = new Intent(ShopDetialActivity.this, EaseActivity.class);

                    intent.putExtra("userId", mShopbean.getS_Com_SoS_ContactPhone());
                    intent.putExtra("usercn", mShopbean.getS_Name());
                    intent.putExtra("chatType", EMMessage.ChatType.Chat);
                    startActivity(intent);

                } else {
                    startActivity(new Intent(ShopDetialActivity.this, LoginActivity.class));
                }


                break;

            case R.id.ll_collection:
                //收藏店铺的操作
                if (Utils.isUserLogin()) {
                    Call<String> operacollectionfunction = Aplication.mIinterface.operacollectionfunction("店铺", mShopbean.getId() + "", Utils.getloginuid());

                    operacollectionfunction.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                            String body1 = response.body();
                            if (body1 != null) {
                                Type type2 = new TypeToken<ArrayList<AvatorBean>>() {
                                }.getType();

                                ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body1, type2);
                                if (avatorBeen != null && avatorBeen.size() > 0) {
                                    AvatorBean bean1 = avatorBeen.get(0);
                                    String retmsg1 = bean1.getRetmsg();
                                    if (retmsg1.equals("true")) {
                                        isCollection = !isCollection;
                                        if (isCollection) {
                                            mIvCollection.setImageResource(R.mipmap.com_icon_fav);
                                            mTvCollection.setText("已收藏");
                                        } else {
                                            mIvCollection.setImageResource(R.mipmap.com_icon_fav_gra);
                                            mTvCollection.setText("收藏店铺");
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
                    startActivity(new Intent(ShopDetialActivity.this, LoginActivity.class));
                }


                break;
        }
    }

    private void showTopWindow() {
        mWindow = new PopupWindow(ShopDetialActivity.this);
        View inflate = View.inflate(ShopDetialActivity.this, R.layout.shop_detail_popwindow, null);
        initPopData(inflate, mWindow);

        WindowManager.LayoutParams lp = ShopDetialActivity.this.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        ShopDetialActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ShopDetialActivity.this.getWindow().setAttributes(lp);

        mWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.detail_toppop_bg));
        mWindow.setTouchable(true);
        mWindow.setContentView(inflate);
        mWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mWindow.setOutsideTouchable(true);
        mWindow.update();
        mWindow.showAtLocation(mRlTop, Gravity.TOP, 0, 0);

        mWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = ShopDetialActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                ShopDetialActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                ShopDetialActivity.this.getWindow().setAttributes(lp);

            }
        });


    }

    private void initPopData(View inflate, final PopupWindow window) {
        ImageView ivcover = (ImageView) inflate.findViewById(R.id.iv_cover);
        ImageView ivhome = (ImageView) inflate.findViewById(R.id.iv_home);
        ImageView ivshopcart = (ImageView) inflate.findViewById(R.id.iv_shopcart);
        ImageView ivmessage = (ImageView) inflate.findViewById(R.id.iv_message);
        ImageView ivusercenter = (ImageView) inflate.findViewById(R.id.iv_usercenter);

        TextView tvuserlv = (TextView) inflate.findViewById(R.id.tv_userlv);
        TextView tvshopname = (TextView) inflate.findViewById(R.id.tv_shopname);
        TextView tvshopcity = (TextView) inflate.findViewById(R.id.tv_shopcity);
        TextView tvjianjie = (TextView) inflate.findViewById(R.id.tv_jianjie);
        TextView tvallsel = (TextView) inflate.findViewById(R.id.tv_allsel);
        TextView tvjfbl = (TextView) inflate.findViewById(R.id.tv_jfbl);

        //初始化一些数据上去
        tvshopname.setText(mShopbean.getS_Name());
        tvuserlv.setText("Lv." + mShopbean.getS_LevelNumber());
        tvshopcity.setText("（" +mShopbean.getS_Com_Province() + "-" + mShopbean.getS_Com_City() + "）");
        Glide.with(this).load(mShopbean.getS_Logo()).into(ivcover);
        tvjianjie.setText(mShopbean.getS_ShopIntroduce());
        tvallsel.setText(mShopbean.getS_AllSellNumber() + "");
        tvjfbl.setText(mShopbean.getS_DisputeProportion() + "%");


        ivshopcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isUserLogin()) {
                    startActivity(new Intent(ShopDetialActivity.this, ShopCarActivity.class));
                } else {
                    startActivity(new Intent(ShopDetialActivity.this, LoginActivity.class));
                }

            }
        });


        ivmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isUserLogin()) {

                    Intent intent = new Intent(ShopDetialActivity.this, EaseActivity.class);
                    intent.putExtra("userId", mShopbean.getS_Com_SoS_ContactPhone());

                    if (mShopbean != null) {
                        intent.putExtra("usercn", mShopbean.getS_Name());
                    } else {
                        intent.putExtra("usercn", mShopbean.getS_Name());
                    }
                    intent.putExtra("chatType", EMMessage.ChatType.Chat);
                    startActivity(intent);
                    window.dismiss();
                } else {
                    startActivity(new Intent(ShopDetialActivity.this, LoginActivity.class));
                    window.dismiss();
                }
            }
        });

        ivusercenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isUserLogin()) {
                    startActivity(new Intent(ShopDetialActivity.this, UserCenterActivity.class));
                } else {
                    startActivity(new Intent(ShopDetialActivity.this, LoginActivity.class));
                }



            }
        });


        ivhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ShopDetialActivity.this,MainActivity.class));

            }
        });







    }

    private void getshopGoodList() {
        Call<String> getgoodlistbyshop = Aplication.mIinterface.getgoodlistbyshop("1", mShopbean.getM_UID(), mGtype);

        getgoodlistbyshop.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("zc", "onResponse:  看看发送的请求" + call.request().toString());
                String body = response.body();
                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                    if (avatorBeen != null && avatorBeen.size() > 0) {
                        AvatorBean bean = avatorBeen.get(0);
                        if (bean.isRet()) {
                            //说明有产品
                            mTvNotuijian.setVisibility(View.GONE);
                            mRvGoods.setVisibility(View.VISIBLE);
                            mRvGoods.setLayoutManager(new LinearLayoutManager(ShopDetialActivity.this));
                            String retmsg = bean.getRetmsg();
                            if (retmsg != null && retmsg.length() > 4) {
                                String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));

                                Type type1 = new TypeToken<ArrayList<BussnessBean>>() {
                                }.getType();
                                ArrayList<BussnessBean> bussnessBeen = Utils.gson.fromJson(substring, type1);
                                if (mGtype.equals("二手商品")) {
                                    IdelAdapter adapter = new IdelAdapter(R.layout.item_idel, bussnessBeen);
                                    mRvGoods.setAdapter(adapter);


                                } else if (mGtype.equals("新商品")) {
                                    NewAdapter adapter = new NewAdapter(R.layout.item_new, bussnessBeen);
                                    mRvGoods.setAdapter(adapter);

                                } else if (mGtype.equals("服务")) {
                                    PhotoAdapter adapter = new PhotoAdapter(R.layout.item_photo, bussnessBeen);
                                    mRvGoods.setAdapter(adapter);

                                }


                            }


                        } else {
                            mRvGoods.setVisibility(View.GONE);
                            mTvNotuijian.setVisibility(View.VISIBLE);
                            mTvNotuijian.setText("该店铺展示没有" + mGtype);

                        }


                    }


                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }

    private void showHome() {
        mShophomebanner.setVisibility(View.VISIBLE);

        if (isShowCoupon) {
            mRvCoupon.setVisibility(View.VISIBLE);
        }
        mLineDetail.setVisibility(View.VISIBLE);
        mRvTuijian.setVisibility(View.VISIBLE);
        mRvGoods.setVisibility(View.GONE);

    }

    private void hideHome() {
        mShophomebanner.setVisibility(View.GONE);
        mRvCoupon.setVisibility(View.GONE);
        mLineDetail.setVisibility(View.GONE);
        mRvTuijian.setVisibility(View.GONE);
        mRvGoods.setVisibility(View.VISIBLE);
    }

    private void selectTag(int i) {
        switch (i) {
            case 0:
                mIvShophome.setImageResource(R.mipmap.type_icon_shop_2);
                mTvShophome.setTextColor(getResources().getColor(R.color.maintextcolor));
                mIvShopnew.setImageResource(R.mipmap.com_icon_new_prd_ept);
                mTvShopnew.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvShopidle.setImageResource(R.mipmap.com_icon_sh_ept);
                mTvShopidle.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvShopserver.setImageResource(R.mipmap.com_icon_serv_ept);
                mTvShopserver.setTextColor(getResources().getColor(R.color.secondtextcolor));
                break;

            case 1:
                mIvShophome.setImageResource(R.mipmap.type_icon_shop_2_ept);
                mTvShophome.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvShopnew.setImageResource(R.mipmap.com_icon_new_prd);
                mTvShopnew.setTextColor(getResources().getColor(R.color.maintextcolor));
                mIvShopidle.setImageResource(R.mipmap.com_icon_sh_ept);
                mTvShopidle.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvShopserver.setImageResource(R.mipmap.com_icon_serv_ept);
                mTvShopserver.setTextColor(getResources().getColor(R.color.secondtextcolor));

                break;

            case 2:

                mIvShophome.setImageResource(R.mipmap.type_icon_shop_2_ept);
                mTvShophome.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvShopnew.setImageResource(R.mipmap.com_icon_new_prd_ept);
                mTvShopnew.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvShopidle.setImageResource(R.mipmap.com_icon_sh_ept);
                mTvShopidle.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvShopserver.setImageResource(R.mipmap.com_icon_serv);
                mTvShopserver.setTextColor(getResources().getColor(R.color.maintextcolor));


                break;

            case 3:

                mIvShophome.setImageResource(R.mipmap.type_icon_shop_2_ept);
                mTvShophome.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvShopnew.setImageResource(R.mipmap.com_icon_new_prd_ept);
                mTvShopnew.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvShopidle.setImageResource(R.mipmap.com_icon_sh);
                mTvShopidle.setTextColor(getResources().getColor(R.color.maintextcolor));
                mIvShopserver.setImageResource(R.mipmap.com_icon_serv_ept);
                mTvShopserver.setTextColor(getResources().getColor(R.color.secondtextcolor));


                break;
        }


    }


}
