package com.zpfan.manzhu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zpfan.manzhu.utils.SPUtils;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity implements BDLocationListener {


    private static final int REQUEST_LOCATIONALL = 1;
    private static final int REQUSET_LOCATION = 2;
    public LocationClient mLocationClient = null;
    @BindView(R.id.logo)
    RoundedImageView mLogo;
    @BindView(R.id.iv_title)
    ImageView mIvTitle;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.ll_skip)
    LinearLayout mLlSkip;
    @BindView(R.id.iv_cover)
    ImageView mIvCover;
    private LocationClientOption locationClientOption;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        Random random = new Random();//指定种子数字
        ArrayList<Integer> imgs = new ArrayList<>();
        imgs.add(R.mipmap.s_c_adv_01);
        imgs.add(R.mipmap.s_c_adv_02);
        mIvCover.setImageResource(imgs.get(random.nextInt(2)));


        initLocation();
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 88888:
                        int value = (int) msg.obj;
                        mTvTime.setText(String.valueOf(value / 1000) + " s  跳过");
                        msg = Message.obtain();//重新获取消息
                        msg.arg1 = 0;
                        msg.arg2 = 1;
                        msg.what = 88888;
                        msg.obj = value - 1000;
                        if (value > 0) {
                            sendMessageDelayed(msg, 1000);
                        } else {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        break;


                }


            }
        };

        Message message = handler.obtainMessage();
        message.arg1 = 0;
        message.arg2 = 1;
        message.what = 88888;
        message.obj = 5000;
        handler.sendMessageDelayed(message, 1000);


    }

    private void initLocation() {
        mLocationClient = new LocationClient(Aplication.mContext);
        //声明LocationClient类

        mLocationClient.setLocOption(getDefaultLocationClientOption());
        mLocationClient.registerLocationListener(this);
        //注册监听函数
        mLocationClient.requestLocation();
        askPermis();
        String version = mLocationClient.getVersion();

    }

    /***
     * 配置参数
     *
     * @return DefaultLocationClientOption
     */
    public LocationClientOption getDefaultLocationClientOption() {
        if (locationClientOption == null) {
            locationClientOption = new LocationClientOption();
            locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            locationClientOption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
            locationClientOption.setScanSpan(3000);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
            locationClientOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
            locationClientOption.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
            locationClientOption.setNeedDeviceDirect(true);//可选，设置是否需要设备方向结果
            locationClientOption.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            locationClientOption.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
            locationClientOption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
            locationClientOption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
            locationClientOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
            locationClientOption.setIsNeedAltitude(false);//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        }
        return locationClientOption;
    }

    @Override
    protected void onResume() {
        askPermis();
        super.onResume();
    }

    private void askPermis() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ActivityCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUSET_LOCATION);

            } else {
                mLocationClient.start();
            }

        } else {
            mLocationClient.start();
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_LOCATIONALL) {

            if (grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {

                SPUtils.getInstance().put("isper", true);

            }
        }


    }

    @Override
    public void onReceiveLocation(final BDLocation location) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });


    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    @OnClick(R.id.ll_skip)
    public void onViewClicked() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
