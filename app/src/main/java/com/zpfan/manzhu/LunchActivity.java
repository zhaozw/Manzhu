package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zpfan.manzhu.utils.SPUtils;

public class LunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);
        boolean isfirst = SPUtils.getInstance().getBoolean("isfirst", true);
        if (isfirst) {
            //进入引导页
            startActivity(new Intent(LunchActivity.this,WelcomActivity.class));

            SPUtils.getInstance().put("isfirst", false);
            finish();
        } else {
            //进入splash 页面
        startActivity(new Intent(LunchActivity.this,SplashActivity.class));
            finish();
            SPUtils.getInstance().put("isfirst", false);
        }




    }
}
