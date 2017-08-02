package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.adapter.ShopCartAdapter;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.FormatBean;
import com.zpfan.manzhu.bean.ShopCartbean;
import com.zpfan.manzhu.event.DeleteGoodEvent;
import com.zpfan.manzhu.myui.MyButtonDialog;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.EditListener;
import com.zpfan.manzhu.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

public class ShopCarActivity extends AppCompatActivity {


    @BindView(R.id.iv_top_back)
    ImageView mIvTopBack;
    @BindView(R.id.tv_top_text)
    TextView mTvTopText;
    @BindView(R.id.iv_clearall)
    ImageView mIvClearall;
    @BindView(R.id.tv_clearall)
    TextView mTvClearall;
    @BindView(R.id.iv_searchtop1)
    ImageView mIvSearchtop1;
    @BindView(R.id.tv_searchtop1)
    TextView mTvSearchtop1;
    @BindView(R.id.ll_searchtop1)
    LinearLayout mLlSearchtop1;
    @BindView(R.id.iv_searchtop2)
    ImageView mIvSearchtop2;
    @BindView(R.id.tv_searchtop2)
    TextView mTvSearchtop2;
    @BindView(R.id.ll_searchtop2)
    LinearLayout mLlSearchtop2;
    @BindView(R.id.iv_searchshaixuan)
    ImageView mIvSearchshaixuan;
    @BindView(R.id.tv_searchshaixuan)
    TextView mTvSearchshaixuan;
    @BindView(R.id.ll_searchtop3)
    LinearLayout mLlSearchtop3;
    @BindView(R.id.ll_searchtopmenu)
    LinearLayout mLlSearchtopmenu;
    @BindView(R.id.rv_shopcart)
    RecyclerView mRvShopcart;
    @BindView(R.id.iv_checkall)
    ImageView mIvCheckall;
    @BindView(R.id.tv_prdnumber)
    TextView mTvPrdnumber;
    @BindView(R.id.tv_allprice)
    TextView mTvAllprice;
    @BindView(R.id.tv_settlement)
    TextView mTvSettlement;
    @BindView(R.id.tv_empty)
    TextView mTvEmpty;
    private String prdtype = "闲置";
    private ArrayList<ShopCartbean.CarshoplistBean> mShopCartList = new ArrayList<>();
    private ShopCartAdapter mShopCartAdapter;
    private double checeprice = 0.00;
    private double mPrice;
    private int mcount;
    private boolean ischeckall = false;
    private String mGetloginuid;
    private ShopCartbean mCartbean;
    private ArrayList<ShopCartbean.CarshoplistBean.CargoodslistBean> morderlist = new ArrayList<>();
    private ArrayList<ShopCartbean.CarshoplistBean>  mcheckshoplist  = new ArrayList<>();
    private String mleixin = "闲置" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void initView() {

        mRvShopcart.setLayoutManager(new LinearLayoutManager(ShopCarActivity.this));


        //发送网络请求去获取数据
        mGetloginuid = Utils.getloginuid();
        Log.i("zc", "initView:   看看mambe uid" + mGetloginuid);
        if (mGetloginuid != null) {
            getshopcartlist(mGetloginuid,"闲置");
        }


    }

    /**
     * 获取购物车数据的方法
     *
     * @param getloginuid
     */
    private void getshopcartlist(String getloginuid,String type) {
        mShopCartList.clear();
        Call<String> getshopcarlist = Aplication.mIinterface.getshopcarlist(getloginuid, type);
        final DecimalFormat df = new DecimalFormat("#.00");
        getshopcarlist.enqueue(new Callback<String>() {
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
                        if (retmsg != null) {
                            Type type1 = new TypeToken<ArrayList<ShopCartbean>>() {
                            }.getType();


                             ArrayList<ShopCartbean> shoplist = Utils.gson.fromJson(retmsg, type1);
                            mCartbean = shoplist.get(0);

                            mTvSearchtop1.setText("闲置（" + mCartbean.getIdle_number() + "）");
                            mTvSearchtop2.setText("新品（" + mCartbean.getNew_number() + "）");
                            mTvSearchshaixuan.setText("约单（" + mCartbean.getServer_number() + "）");
                            ArrayList<ShopCartbean.CarshoplistBean> carshoplist = mCartbean.getCarshoplist();

                            if (carshoplist != null && carshoplist.size() > 0) {
                                mRvShopcart.setVisibility(View.VISIBLE);
                                mTvEmpty.setVisibility(View.GONE);
                                for (ShopCartbean.CarshoplistBean carshoplistBean : carshoplist) {
                                    if (carshoplistBean.getCargoodslist().size() > 0) {
                                        mShopCartList.add(carshoplistBean);
                                    }
                                }
                            } else if (  carshoplist == null   ||  carshoplist.size() == 0  ) {
                                mRvShopcart.setVisibility(View.GONE);
                                mTvEmpty.setVisibility(View.VISIBLE);
                            }
                            if (mShopCartList.size() == 0) {
                                mRvShopcart.setVisibility(View.GONE);
                                mTvEmpty.setVisibility(View.VISIBLE);
                            }


                            mShopCartAdapter = new ShopCartAdapter(R.layout.item_shopcatlist, mShopCartList,mleixin, new EditListener() {
                                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                                @Override
                                public void edit(ArrayList<ShopCartbean.CarshoplistBean.CargoodslistBean> checeGood) {
                                    morderlist.clear();
                                    morderlist.addAll(checeGood);
                                    mPrice = 0.00;
                                    mcount = 0;
                                    if (checeGood.size() > 0) {
                                        for (ShopCartbean.CarshoplistBean.CargoodslistBean cargoodslistBean : checeGood) {
                                            String uid = cargoodslistBean.getGoods_Spcification_UID();

                                            if (uid.equals("")) {
                                                String fixedPrice = cargoodslistBean.getGoods_model().getG_FixedPrice();
                                                Double aDouble = Double.valueOf(fixedPrice);
                                                int count = cargoodslistBean.getCarCount();

                                                mPrice = mPrice + (aDouble * count);
                                                mcount = mcount + 1 * count;
                                            } else {
                                                //拿到uid 去查询
                                                List<FormatBean> specifications = cargoodslistBean.getGoods_model().getGoods_specifications();
                                                for (FormatBean specification : specifications) {
                                                    if (uid.equals(specification.getPS_UniqueID())) {
                                                        String price = specification.getPS_FixedPrice();
                                                        Double aDouble = Double.valueOf(price);
                                                        int count = cargoodslistBean.getCarCount();
                                                        mPrice = mPrice + (aDouble * count);
                                                        mcount = mcount + 1 * count;

                                                    }
                                                }


                                            }


                                        }
                                        mTvAllprice.setText(df.format(mPrice));
                                        mTvPrdnumber.setText(mcount + "");
                                        mTvSettlement.setBackground(getResources().getDrawable(R.color.maintextcolor));
                                        mTvSettlement.setEnabled(true);

                                    } else {

                                        mPrice = 0.00;
                                        mcount = 0;
                                        mTvAllprice.setText("0.00");
                                        mTvPrdnumber.setText(mcount + "");
                                        mTvSettlement.setBackground(getResources().getDrawable(R.color.weaklin));
                                        mTvSettlement.setEnabled(false);
                                    }


                                }
                            });
                            mRvShopcart.setAdapter(mShopCartAdapter);

                        }


                    }


                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }


    @OnClick({R.id.iv_top_back, R.id.iv_clearall, R.id.tv_clearall, R.id.iv_checkall, R.id.tv_settlement,R.id.ll_searchtop1,R.id.ll_searchtop2,R.id.ll_searchtop3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.iv_clearall:

            case R.id.tv_clearall:
                //清楚全部购物车的操作
                final MyButtonDialog dialog = new MyButtonDialog(ShopCarActivity.this, R.style.Dialog, "购物车管理", "确认清空全部商品？");
                dialog.show();
                dialog.setonsureClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clearAllshopcart(mGetloginuid);
                        dialog.dismiss();
                    }
                });

                dialog.setoncancleClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });


                break;

            case R.id.iv_checkall:
                //全选的操作
                ischeckall = !ischeckall;
                Log.i("alcheck", "onViewClicked:   看看全选还是不是全选" + ischeckall);

                for (ShopCartbean.CarshoplistBean bean : mShopCartList) {
                    bean.setIscheckallgood(ischeckall);

                }

                mShopCartAdapter.allcheck(ischeckall);

                if (ischeckall) {
                    mIvCheckall.setImageResource(R.mipmap.com_icon_multcheck_bl);
                } else {
                    mIvCheckall.setImageResource(R.mipmap.com_icon_multcheck_ept);
                }



                break;

            case R.id.tv_settlement:
                //结算的操作  跳转到订单确认的界面
                mcheckshoplist.clear();
                Map<String, String> map = new LinkedHashMap<>();
                map.put("guid", "");
                if (morderlist.size() > 0) {
                    final Intent intent = new Intent(this, OrderSureActivity.class);
                    for (ShopCartbean.CarshoplistBean bean : mShopCartList) {
                        if (bean.getCheckgoodslist().size() > 0) {
                            mcheckshoplist.add(bean);
                        }
                    }

                    for (ShopCartbean.CarshoplistBean bean : mcheckshoplist) {
                        ArrayList<ShopCartbean.CarshoplistBean.CargoodslistBean> checkgoodslist = bean.getCheckgoodslist();
                        for (ShopCartbean.CarshoplistBean.CargoodslistBean cargoodslistBean : checkgoodslist) {
                            String guid = map.get("guid");
                            if (guid.isEmpty()) {
                                map.put("guid", cargoodslistBean.getGoods_UID());
                            } else {
                                map.put("guid", guid + "," + cargoodslistBean.getGoods_UID());
                            }
                        }


                    }

                    intent.putParcelableArrayListExtra("shopcat", mcheckshoplist);
                    intent.putExtra("type","sopcart");
                    //发送请求去查看购物车里的物品是否失效
                    Call<String> guid = Aplication.mIinterface.checkGoodnormalSattus(map.get("guid"));
                    guid.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.i("zc", "onResponse:  看看返回 "  +  call.request().toString());
                            String body = response.body();

                            if (body != null) {
                                Type type = new TypeToken<ArrayList<AvatorBean>>() {
                                }.getType();

                                ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                                if (avatorBeen != null && avatorBeen.size() > 0) {
                                    AvatorBean bean = avatorBeen.get(0);
                                    String retmsg = bean.getRetmsg();
                                    if (retmsg.equals("true")) {
                                        //没有失效的宝贝
                                        startActivity(intent);

                                    } else {
                                        MyToast.show("您的结算订单中包含已下架商品（服务），请删除后再结算",R.mipmap.com_icon_cross_w);
                                    }

                                }




                            }



                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });







                } else {
                    MyToast.show("你还没有选择商品，无法结算",R.mipmap.com_icon_cross_w);
                }


                break;
            case R.id.ll_searchtop1:
                //点击了闲置
                mIvSearchtop1.setImageResource(R.mipmap.com_icon_sh);
                mTvSearchtop1.setTextColor(getResources().getColor(R.color.maintextcolor));
                mIvSearchtop2.setImageResource(R.mipmap.com_icon_new_prd_ept);
                mTvSearchtop2.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvSearchshaixuan.setImageResource(R.mipmap.com_icon_serv_ept);
                mTvSearchshaixuan.setTextColor(getResources().getColor(R.color.secondtextcolor));

                mleixin = "闲置";
                getshopcartlist(mGetloginuid,"闲置");
                mIvCheckall.setImageResource(R.mipmap.com_icon_multcheck_ept);
                ischeckall = false;
                break;

            case R.id.ll_searchtop2:
                //点击了新品
                mIvSearchtop1.setImageResource(R.mipmap.com_icon_sh_ept);
                mTvSearchtop1.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvSearchtop2.setImageResource(R.mipmap.com_icon_new_prd);
                mTvSearchtop2.setTextColor(getResources().getColor(R.color.maintextcolor));
                mIvSearchshaixuan.setImageResource(R.mipmap.com_icon_serv_ept);
                mTvSearchshaixuan.setTextColor(getResources().getColor(R.color.secondtextcolor));

                mleixin = "新品";
                getshopcartlist(mGetloginuid, "新品");
                mIvCheckall.setImageResource(R.mipmap.com_icon_multcheck_ept);
                ischeckall = false;
                break;

            case R.id.ll_searchtop3:
                //点击了约单

                mIvSearchtop1.setImageResource(R.mipmap.com_icon_sh_ept);
                mTvSearchtop1.setTextColor(getResources().getColor(R.color.secondbuttonbc));
                mIvSearchtop2.setImageResource(R.mipmap.com_icon_new_prd_ept);
                mTvSearchtop2.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvSearchshaixuan.setImageResource(R.mipmap.com_icon_serv);
                mTvSearchshaixuan.setTextColor(getResources().getColor(R.color.maintextcolor));

                mleixin = "服务";
                getshopcartlist(mGetloginuid,"服务");
                mIvCheckall.setImageResource(R.mipmap.com_icon_multcheck_ept);
                ischeckall = false;

                break;



        }
    }

    private void clearAllshopcart(String uid) {
        Call<String> clerarallshopcart = Aplication.mIinterface.clerarallshopcart(uid);

        clerarallshopcart.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();

                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> been = Utils.gson.fromJson(body, type);
                    AvatorBean bean = been.get(0);
                    String retmsg = bean.getRetmsg();

                    if (retmsg.equals("true")) {
                        mShopCartList.clear();
                        mShopCartAdapter.notifyDataSetChanged();
                        mTvSearchtop1.setText("闲置（" + 0 + "）");
                        mTvSearchtop2.setText("新品（" + 0 + "）");
                        mTvSearchshaixuan.setText("约单（" + 0 + "）");

                    }



                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void deletGood(DeleteGoodEvent event) {


        String leixin = event.getLeixin();
        if (leixin.equals("闲置")) {
            mTvSearchtop1.setText("闲置（" + mShopCartList.size() + "）");
        } else if (leixin.equals("新品")) {
            mTvSearchtop2.setText("新品（" + mShopCartList.size() + "）");
        } else if (leixin.equals("约单")) {
            mTvSearchshaixuan.setText("约单（" + mShopCartList.size() + "）");
        }




    }


}
