package com.zpfan.manzhu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
    }
}
