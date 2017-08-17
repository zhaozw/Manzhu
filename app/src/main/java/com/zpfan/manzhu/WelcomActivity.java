package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

public class WelcomActivity extends AppCompatActivity {


    @BindView(R.id.bp_welcome)
    BGABanner mBpWelcome;
    @BindView(R.id.enter_guide)
    LinearLayout mEnterGuide;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        final ArrayList<Integer> welcom = new ArrayList<>();
        welcom.add(R.mipmap.s_c_guide_01);
        welcom.add(R.mipmap.s_c_guide_02);
        welcom.add(R.mipmap.s_c_guide_03);
        welcom.add(R.mipmap.s_c_guide_04);

        mBpWelcome.setData(R.mipmap.s_c_guide_01, R.mipmap.s_c_guide_02, R.mipmap.s_c_guide_03, R.mipmap.s_c_guide_04);
        mBpWelcome.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == welcom.size() - 1) {
                    mEnterGuide.setVisibility(View.VISIBLE);
                } else {
                    mEnterGuide.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
        mBpWelcome.setEnterSkipViewIdAndDelegate(R.id.enter_guide, 0, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                startActivity(new Intent(WelcomActivity.this, MainActivity.class));
                finish();

            }
        });


    }
}
