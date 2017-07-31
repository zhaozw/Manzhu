package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        //发送请求去获取订单的详情
        if (avator.isRet()) {
            getorderDetil(avator.getRetmsg());
        }






    }

    private void getorderDetil(String retmsg) {
        Call<String> details = Aplication.mIinterface.getOrderDetails(retmsg);

        details.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("zc", "onResponse:   看看数据"  + call.request().toString());
                String body = response.body();

                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);



                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}
