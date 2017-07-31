package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zpfan.manzhu.bean.AvatorBean;

public class OrderGenerationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_generation);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();

        AvatorBean avator = intent.getParcelableExtra("avator");
        String type = intent.getStringExtra("type");
        if (type.equals("idle")) {
            //闲置的界面
        } else if (type.equals("new")) {
            //新商品的界面
        } else if (type.equals("server")) {
            //服务的界面


        }






    }
}
