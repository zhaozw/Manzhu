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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.adapter.ShopcartOrderGeneraAdapter;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.OrderGenerationBean;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopCartOrderGeneraActivity extends AppCompatActivity {

    @BindView(R.id.ll_right)
    LinearLayout mLlRight;
    @BindView(R.id.tv_failure)
    TextView mTvFailure;
    @BindView(R.id.rv_shopcartgenera)
    RecyclerView mRvShopcartgenera;
    private ArrayList<OrderGenerationBean> mGenerationBeen;
    private boolean isfinish = false;
    private DecimalFormat mDf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart_order_genera);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        AvatorBean avator = intent.getParcelableExtra("avator");
        mDf = new DecimalFormat("0.00");
        if (avator.isRet()) {
            //下单成功
            mRvShopcartgenera.setLayoutManager(new LinearLayoutManager(this));
            //发送请求去获取数据
            getorderDetil(avator.getRetmsg());




        } else {
            //下单失败
            mLlRight.setVisibility(View.VISIBLE);
            mTvFailure.setVisibility(View.VISIBLE);
            mTvFailure.setText("由于" + avator.getRetmsg() + "原因导致下单失败");

        }


    }

    private void getorderDetil(final String retmsg) {
        Call<String> details = Aplication.mIinterface.getordersubmitpaysuccess(retmsg);

        details.enqueue(new Callback<String>() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String body = response.body();

                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                    if (avatorBeen != null && avatorBeen.size() > 0) {
                        AvatorBean bean = avatorBeen.get(0);
                        String retmsg1 = bean.getRetmsg();

                        if (retmsg1.contains("[")) {
                            Log.i("zc", "onResponse:   看看数据" + retmsg1);
                            String substring = retmsg1.substring(1, retmsg1.lastIndexOf("]"));

                            Type type1 = new TypeToken<ArrayList<OrderGenerationBean>>() {
                            }.getType();
                            mGenerationBeen = Utils.gson.fromJson(substring, type1);
                            ShopcartOrderGeneraAdapter adapter = new ShopcartOrderGeneraAdapter(R.layout.item_shopcartorder, mGenerationBeen);
                            View headview = View.inflate(ShopCartOrderGeneraActivity.this, R.layout.shopcart_ordergenera_head, null);
                            View footView = View.inflate(ShopCartOrderGeneraActivity.this, R.layout.shopcart_ordergenera_foot, null);
                            initFootView(footView,mGenerationBeen);
                            adapter.addHeaderView(headview);

                            adapter.addFooterView(footView);
                            mRvShopcartgenera.setAdapter(adapter);

                        }


                    }

                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void initFootView(View footView, ArrayList<OrderGenerationBean> generationBeen) {
        TextView tvallpay = (TextView) footView.findViewById(R.id.tv_allpay);
        TextView tvjifen = (TextView) footView.findViewById(R.id.tv_jifen);
        int allpay = 0;
        int alljifen = 0;

        for (OrderGenerationBean bean : generationBeen) {
            allpay = allpay + bean.getO_PayMoney();
            alljifen =  alljifen + Integer.valueOf(bean.getO_DeductionIntegralMoney()) ;
        }

        String s1 = alljifen + "";

        String s = allpay + "";
        Double aDouble = Double.valueOf(s);
        Double aDouble1 = Double.valueOf(s1);


        tvallpay.setText(mDf.format(aDouble));
        tvjifen.setText(mDf.format(aDouble1));
    }
}
