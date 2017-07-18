package com.zpfan.manzhu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.zpfan.manzhu.utils.SPUtils;

public class SplashActivity extends AppCompatActivity implements BDLocationListener {


    private static final int REQUEST_LOCATIONALL = 1;
    private static final int REQUSET_LOCATION = 2;
    public LocationClient mLocationClient = null;
    private LocationClientOption locationClientOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initLocation();
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);

                startActivity(intent);
                finish();
            }


        }, 2000);

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

        }else {
            mLocationClient.start();
        }


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_LOCATIONALL) {
            Log.i("per", "onRequestPermissionsResult:   看看哪两个没搞定 + "+  grantResults[0] + grantResults[1]  +grantResults[2] + PackageManager.PERMISSION_GRANTED);
            if (grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {

                SPUtils.getInstance().put("isper",true);

            }
        }


    }

    @Override
    public void onReceiveLocation(final BDLocation location) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("location", "run:   回调到的地址" + location.getCity());
            }
        });


    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }
}
