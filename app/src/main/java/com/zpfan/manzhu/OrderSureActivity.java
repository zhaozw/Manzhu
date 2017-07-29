package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderSureActivity extends AppCompatActivity {

    @BindView(R.id.rv_ordersure)
    RecyclerView mRvOrdersure;
    @BindView(R.id.ll_importorder)
    LinearLayout mLlImportorder;
    private TextView mTvaddrname;
    private TextView mTvaddrlocation;
    private TextView mTvphone;
    private View mFootView;
    private ArrayList<ShopCartbean.CarshoplistBean> mMorderlist;
    private double allprice  = 0;
    private double yunfeni  = 0;
    private int allnumber = 0;
    private OrderSureAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_sure);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
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
                    Log.i("zc", "onClick:   跳转到地址选择的界面");
                }
            });
            
            
            mAdapter.addHeaderView(headview);
            mAdapter.addFooterView(mFootView);
            mRvOrdersure.setAdapter(mAdapter);


        }

    }

    private void initFootView() {
        allprice = 0;
        yunfeni = 0;
        allnumber = 0;
        TextView tvallcount = (TextView) mFootView.findViewById(R.id.tv_allcount);
        TextView tvallprice = (TextView) mFootView.findViewById(R.id.tv_allprice);
        TextView tvyunfei = (TextView) mFootView.findViewById(R.id.tv_yunfei);
        TextView tvyouhuijuan = (TextView) mFootView.findViewById(R.id.tv_youhuijuan);
        TextView tvjifen = (TextView) mFootView.findViewById(R.id.tv_jifen);
        TextView tvpayprice = (TextView) mFootView.findViewById(R.id.tv_payprice);


        for (ShopCartbean.CarshoplistBean bean : mMorderlist) {
            for (ShopCartbean.CarshoplistBean.CargoodslistBean cargoodslistBean : bean.getCheckgoodslist()) {
                allnumber = allnumber + 1 * cargoodslistBean.getCarCount();
                yunfeni = yunfeni + Double.valueOf(cargoodslistBean.getGoods_model().getG_CourierMoney());
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

        DecimalFormat df = new DecimalFormat("0.00");
        tvallprice.setText(df.format(allprice));
        //运费的计算
        tvyunfei.setText(df.format(yunfeni));







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

                            ArrayList<AddressBean> addressBeen = Utils.gson.fromJson(substring, type1);

                            if (addressBeen.size() > 0) {
                                for (AddressBean bean : addressBeen) {
                                    if (bean.isMD_IsDefault()) {
                                        mTvaddrname.setText("收货人：" + bean.getMD_Name());
                                        mTvphone.setText(bean.getMD_Phone());
                                        mTvaddrlocation.setText(bean.getMD_Province() + bean.getMD_City() + bean.getMD_Area() + bean.getMD_Address());
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



}
