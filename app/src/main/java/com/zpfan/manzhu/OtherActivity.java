package com.zpfan.manzhu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherActivity extends AppCompatActivity {

    @BindView(R.id.dashline)
    View mDashline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mDashline.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }
}
