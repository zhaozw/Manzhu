package com.zpfan.manzhu;

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
import com.zpfan.manzhu.myui.EditListener;
import com.zpfan.manzhu.utils.Utils;

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
    private String prdtype = "闲置";
    private ArrayList<ShopCartbean.CarshoplistBean> mShopCartList = new ArrayList<>();
    private ShopCartAdapter mShopCartAdapter;
    private double checeprice = 0.00;
    private double mPrice;
    private  int mcount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        mRvShopcart.setLayoutManager(new LinearLayoutManager(ShopCarActivity.this));





        //发送网络请求去获取数据
        String getloginuid = Utils.getloginuid();
        Log.i("zc", "initView:   看看数据有问题吗" + getloginuid);
        if (getloginuid != null) {
            getshopcartlist(getloginuid);
        }


    }

    /**
     * 获取购物车数据的方法
     *
     * @param getloginuid
     */
    private void getshopcartlist(String getloginuid) {
        Call<String> getshopcarlist = Aplication.mIinterface.getshopcarlist(getloginuid, prdtype);
        Log.i("zc", "getshopcartlist:   看看数据" + getloginuid +  prdtype);
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
                            final ArrayList<ShopCartbean> shoplist = Utils.gson.fromJson(retmsg, type1);
                            ShopCartbean cartbean = shoplist.get(0);

                            mTvSearchtop1.setText("闲置（" + cartbean.getIdle_number() + "）");
                            mTvSearchtop2.setText("新品（" + cartbean.getNew_number() + "）");
                            mTvSearchshaixuan.setText("约单（" + cartbean.getServer_number() + "）");
                            ArrayList<ShopCartbean.CarshoplistBean> carshoplist = cartbean.getCarshoplist();
                            for (ShopCartbean.CarshoplistBean carshoplistBean : carshoplist) {
                                Log.i("zc", "onResponse:   看看size"  +carshoplistBean.getCargoodslist().size() );
                                if (carshoplistBean.getCargoodslist().size() > 0) {
                                    mShopCartList.add(carshoplistBean);
                                }
                            }


                            mShopCartAdapter = new ShopCartAdapter(R.layout.item_shopcatlist, mShopCartList, new EditListener() {
                                @Override
                                public void edit(ArrayList<ShopCartbean.CarshoplistBean.CargoodslistBean> checeGood) {
                                    mPrice = 0.00;
                                    mcount = 0;
                                    if (checeGood.size() > 0) {
                                        for (ShopCartbean.CarshoplistBean.CargoodslistBean cargoodslistBean : checeGood) {
                                            String uid = cargoodslistBean.getGoods_Spcification_UID();
                                            mcount = mcount +1;
                                            if (uid.equals("")) {
                                                String fixedPrice = cargoodslistBean.getGoods_model().getG_FixedPrice();
                                                Double aDouble = Double.valueOf(fixedPrice);
                                                int count = cargoodslistBean.getCarCount();
                                                Log.i("zc", "edit:  看看价格" + aDouble);
                                               mPrice =  mPrice + aDouble * count;

                                            } else {
                                                //拿到uid 去查询
                                                List<FormatBean> specifications = cargoodslistBean.getGoods_model().getGoods_specifications();
                                                for (FormatBean specification : specifications) {
                                                    if (uid.equals(specification.getPS_UniqueID())) {
                                                        String price = specification.getPS_FixedPrice();
                                                        Double aDouble = Double.valueOf(price);
                                                        int count = cargoodslistBean.getCarCount();
                                                        mPrice =  mPrice + aDouble * count;


                                                    }
                                                }



                                            }


                                        }

                                        Log.i("zc", "edit:   看看总价格" + df.format(mPrice) + mcount);

                                        mTvAllprice.setText(df.format(mPrice));
                                        mTvPrdnumber.setText(mcount+"");


                                    } else {

                                        mPrice = 0.00;
                                        mcount = 0;
                                        mTvAllprice.setText("0.00");
                                        mTvPrdnumber.setText(mcount+"");
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


    @OnClick({R.id.iv_top_back, R.id.iv_clearall, R.id.tv_clearall, R.id.iv_checkall, R.id.tv_settlement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.iv_clearall:

            case R.id.tv_clearall:
                //清楚全部购物车的操作


                break;

            case R.id.iv_checkall:
                //全选的操作

                break;

            case R.id.tv_settlement:
                //结算的操作

                break;

        }
    }




}
