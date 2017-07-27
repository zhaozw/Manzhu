package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.zpfan.manzhu.adapter.OrderSureAdapter;
import com.zpfan.manzhu.bean.ShopCartbean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderSureActivity extends AppCompatActivity {

    @BindView(R.id.rv_ordersure)
    RecyclerView mRvOrdersure;
    @BindView(R.id.ll_importorder)
    LinearLayout mLlImportorder;

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
            Log.i("zc", "initView:   看看数据对不对" + morderlist.size());
            OrderSureAdapter adapter = new OrderSureAdapter(R.layout.item_ordersure, morderlist);
            View headview1 = View.inflate(this, R.layout.rv_order_sure_head, null);

            adapter.addHeaderView(headview1);
            mRvOrdersure.setAdapter(adapter);


        }

    }


}
