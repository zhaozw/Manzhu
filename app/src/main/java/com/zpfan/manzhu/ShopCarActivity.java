package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
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
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.EditListener;
import com.zpfan.manzhu.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
                            Log.i("zc", "onResponse: 去获取数据了吗" + body + call.request().toString());

                             ArrayList<ShopCartbean> shoplist = Utils.gson.fromJson(retmsg, type1);
                            mCartbean = shoplist.get(0);
                            mcheckshoplist.clear();
                            mcheckshoplist.addAll(mCartbean.getCarshoplist());
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


                            mShopCartAdapter = new ShopCartAdapter(R.layout.item_shopcatlist, mShopCartList, new EditListener() {
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


                                    } else {

                                        mPrice = 0.00;
                                        mcount = 0;
                                        mTvAllprice.setText("0.00");
                                        mTvPrdnumber.setText(mcount + "");
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

                clearAllshopcart(mGetloginuid);

                break;

            case R.id.iv_checkall:
                //全选的操作
                mShopCartAdapter.allCheck = true;
                mShopCartAdapter.notifyDataSetChanged();
                ischeckall = !ischeckall;
                if (ischeckall) {
                    mIvCheckall.setImageResource(R.mipmap.com_icon_multcheck_bl);
                } else {
                    mIvCheckall.setImageResource(R.mipmap.com_icon_multcheck_ept);
                }


                break;

            case R.id.tv_settlement:
                //结算的操作  跳转到订单确认的界面
                if (morderlist.size() > 0) {
                    Intent intent = new Intent(this, OrderSureActivity.class);

                    intent.putParcelableArrayListExtra("shopcat", mShopCartList);


                    intent.putExtra("type","sopcart");
                    startActivity(intent);
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

                getshopcartlist(mGetloginuid,"闲置");

                break;

            case R.id.ll_searchtop2:
                //点击了新品
                mIvSearchtop1.setImageResource(R.mipmap.com_icon_sh_ept);
                mTvSearchtop1.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvSearchtop2.setImageResource(R.mipmap.com_icon_new_prd);
                mTvSearchtop2.setTextColor(getResources().getColor(R.color.maintextcolor));
                mIvSearchshaixuan.setImageResource(R.mipmap.com_icon_serv_ept);
                mTvSearchshaixuan.setTextColor(getResources().getColor(R.color.secondtextcolor));

                getshopcartlist(mGetloginuid, "新品");


                break;

            case R.id.ll_searchtop3:
                //点击了约单

                mIvSearchtop1.setImageResource(R.mipmap.com_icon_sh_ept);
                mTvSearchtop1.setTextColor(getResources().getColor(R.color.secondbuttonbc));
                mIvSearchtop2.setImageResource(R.mipmap.com_icon_new_prd_ept);
                mTvSearchtop2.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvSearchshaixuan.setImageResource(R.mipmap.com_icon_serv);
                mTvSearchshaixuan.setTextColor(getResources().getColor(R.color.maintextcolor));

                getshopcartlist(mGetloginuid,"服务");

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
                    Log.i("zc", "onResponse:   看看删除购物车方法的返回" + bean.getRetmsg());
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
        mTvSearchtop1.setText("闲置（" + mShopCartList.size() + "）");
    }


}
