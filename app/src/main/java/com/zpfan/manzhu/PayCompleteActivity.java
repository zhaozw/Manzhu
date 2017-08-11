package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.chat.EMMessage;
import com.zpfan.manzhu.adapter.ShopCarPayCompleteAdapter;
import com.zpfan.manzhu.bean.OrderGenerationBean;
import com.zpfan.manzhu.myui.EaseActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayCompleteActivity extends AppCompatActivity {

    @BindView(R.id.tv_detil)
    TextView mTvDetil;
    @BindView(R.id.bt_backhome)
    TextView mBtBackhome;
    @BindView(R.id.bt_usercenter)
    TextView mBtUsercenter;
    @BindView(R.id.tv_ordernumber)
    TextView mTvOrdernumber;
    @BindView(R.id.ll_getorderdetil)
    LinearLayout mLlGetorderdetil;
    @BindView(R.id.tv_buystyle)
    TextView mTvBuystyle;
    @BindView(R.id.tv_style)
    TextView mTvStyle;
    @BindView(R.id.tv_ordertime)
    TextView mTvOrdertime;
    @BindView(R.id.tv_shopname)
    TextView mTvShopname;
    @BindView(R.id.tv_userlv)
    TextView mTvUserlv;
    @BindView(R.id.iv_message)
    ImageView mIvMessage;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;
    @BindView(R.id.tv_allpay)
    TextView mTvAllpay;
    @BindView(R.id.ll_allpay)
    LinearLayout mLlAllpay;
    @BindView(R.id.ll_buy)
    LinearLayout mLlBuy;
    @BindView(R.id.rv_car)
    RecyclerView mRvCar;
    private DecimalFormat mDf;
    private OrderGenerationBean mOrderd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_complete);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        Intent intent = getIntent();
        boolean iscar = intent.getBooleanExtra("iscar", false);

            mDf = new DecimalFormat("0.00");

        if (!iscar) {
            mOrderd = intent.getParcelableExtra("orderd");
            String type = intent.getStringExtra("type");
            if (mOrderd != null) {
                //不为空的时候 设置参数进去
                mTvOrdernumber.setText(mOrderd.getO_UID());
                String pattern = mOrderd.getO_TradingPattern();
                mTvBuystyle.setText(pattern);
                if (pattern.equals("线下交易")) {
                    mTvStyle.setText("（买家付款后卖家即得到货款）");
                }

                mTvOrdertime.setText(mOrderd.getO_OrderTime().replace("-", " -  "));
                mTvShopname.setText(mOrderd.getStore_CN());
                mTvUserlv.setText("Lv." + mOrderd.getStore_Level());
                String all = mOrderd.getO_PayMoney() + "";
                Double allmoney = Double.valueOf(all);

                mTvAllpay.setText(mDf.format(allmoney));


            }

            if (type != null) {
                //不为空的时候 判断是租赁还是购买
                if (type.equals("new") || type.equals("idle")) {
                    //说明是购买的新商品

                    mTvDetil.setText("卖家需要在 X 天内确认发货，否则钱款将会退回您的平台个人账户。");

                } else if (type.equals("rent")) {

                    mTvDetil.setText("出租人需要在X天内确认发货，否则钱款将会退回您的平台个人账户。收货并确认后，租期计时开始。");

                } else if (type.equals("server")) {
                    mTvDetil.setText("服务商需要在 X 天内确认开工，否则钱款将会退回您的平台个人账户。");
                }


            }
        } else {
            //购物车过来以后 的逻辑
            mLlBuy.setVisibility(View.GONE);
            mRvCar.setVisibility(View.VISIBLE);
            mRvCar.setLayoutManager(new LinearLayoutManager(PayCompleteActivity.this));

            ArrayList<OrderGenerationBean> carorderd = intent.getParcelableArrayListExtra("carorderd");
            ShopCarPayCompleteAdapter adapter = new ShopCarPayCompleteAdapter(R.layout.item_carpaycomple, carorderd);
            mRvCar.setAdapter(adapter);
            double allprice = 0;
            for (OrderGenerationBean bean : carorderd) {
                allprice = allprice +  Double.valueOf(bean.getO_PayMoney());
            }
            mTvAllpay.setText(mDf.format(allprice));

            String type = intent.getStringExtra("type");

            if (type != null) {
                //不为空的时候 判断是租赁还是购买
                if (type.equals("new") || type.equals("idle")) {
                    //说明是购买的新商品

                    mTvDetil.setText("卖家需要在 X 天内确认发货，否则钱款将会退回您的平台个人账户。");

                } else if (type.equals("rent")) {

                    mTvDetil.setText("出租人需要在X天内确认发货，否则钱款将会退回您的平台个人账户。收货并确认后，租期计时开始。");

                } else if (type.equals("server")) {
                    mTvDetil.setText("服务商需要在 X 天内确认开工，否则钱款将会退回您的平台个人账户。");
                }


            }




        }


    }


    @OnClick({R.id.ll_getorderdetil, R.id.ll_message, R.id.bt_backhome, R.id.bt_usercenter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_getorderdetil:
                break;
            case R.id.ll_message:

                Intent intent = new Intent(PayCompleteActivity.this, EaseActivity.class);
                intent.putExtra("userId", mOrderd.getStore_Phone());
                intent.putExtra("usercn", mOrderd.getStore_CN());
                intent.putExtra("chatType", EMMessage.ChatType.Chat);
                startActivity(intent);

                break;

            case R.id.bt_backhome:

                startActivity(new Intent(PayCompleteActivity.this, MainActivity.class));
                break;
            case R.id.bt_usercenter:
                startActivity(new Intent(PayCompleteActivity.this, UserCenterActivity.class));
                break;


        }
    }
}
