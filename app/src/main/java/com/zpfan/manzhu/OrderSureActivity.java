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
import com.zpfan.manzhu.bean.ShopCartbean;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

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
            ArrayList<ShopCartbean.CarshoplistBean> morderlist = intent.getParcelableArrayListExtra("shopcat");
            for (ShopCartbean.CarshoplistBean bean : morderlist) {
                Log.i("zc", "initView:   看看数据对不对" + bean.getCheckgoodslist().size());
            }
            OrderSureAdapter adapter = new OrderSureAdapter(R.layout.item_ordersure, morderlist);
            View headview1 = View.inflate(this, R.layout.rv_order_sure_head, null);
            mTvaddrname = (TextView) headview1.findViewById(R.id.tv_addrname);
            mTvaddrlocation = (TextView) headview1.findViewById(R.id.tv_addrlocation);
            mTvphone = (TextView) headview1.findViewById(R.id.tv_phone);

            getLocation(Utils.getloginuid());
            
            headview1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("zc", "onClick:   跳转到地址选择的界面");
                }
            });
            
            
            adapter.addHeaderView(headview1);
            mRvOrdersure.setAdapter(adapter);


        }

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


}
