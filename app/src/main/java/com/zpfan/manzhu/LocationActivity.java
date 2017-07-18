package com.zpfan.manzhu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.adapter.LocationpoprAdapter;
import com.zpfan.manzhu.adapter.LocationpopshiAdapter;
import com.zpfan.manzhu.bean.TestBean;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationActivity extends AppCompatActivity implements BDLocationListener {

    private static final int REQUSET_LOCATION = 1;
    @BindView(R.id.tv_mylocation)
    TextView mTvMylocation;
    @BindView(R.id.ll_getlocation)
    LinearLayout mLlGetlocation;
    @BindView(R.id.tv_province)
    TextView mTvProvince;
    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.bt_import)
    Button mBtImport;


    public LocationClient mLocationClient = null;
    @BindView(R.id.ll_chose)
    LinearLayout mLlChose;
    private LocationClientOption locationClientOption;
    private Object mJsonData;
    private RecyclerView mRvlocationpr;
    private ArrayList<TestBean> mSheng;
    private boolean ischeckSheng = false;
    private int posation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.bind(this);

        initLBS();
        getjsonData();


    }

    private void getjsonData() {
        //获取数据

        InputStreamReader reader = new InputStreamReader(this.getClassLoader().getResourceAsStream("assets/" + "city.json"));
        BufferedReader bufferedReader = new BufferedReader(reader);
        Type type = new TypeToken<ArrayList<TestBean>>() {
        }.getType();
        mSheng = new ArrayList<>();
        ArrayList<TestBean> arrayList = Utils.gson.fromJson(bufferedReader, type);
        for (TestBean bean : arrayList) {
            if (bean.getLevel() == 1) {
                mSheng.add(bean);
            }
        }

        for (TestBean s : mSheng) {
            //根据省的数据  去拿市的数据


            for (TestBean bean : arrayList) {
                if (bean.getSheng().equals(s.getSheng()) && bean.getLevel() == 2) {
                    s.getShi().add(bean.getName());
                }
            }



        }

    }

    private void initLBS() {
        //初始化定位的方法

        mLocationClient = new LocationClient(Aplication.mContext);
        //声明LocationClient类

        mLocationClient.setLocOption(getDefaultLocationClientOption());
        mLocationClient.registerLocationListener(this);
        //注册监听函数
        mLocationClient.requestLocation();

        String version = mLocationClient.getVersion();
        Intent intent = getIntent();
      String location =  intent.getStringExtra("location");
        if (location != null) {
            mTvMylocation.setText(location);
        }

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


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.tv_mylocation, R.id.ll_getlocation, R.id.tv_province, R.id.tv_city, R.id.bt_import})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_mylocation:


                break;
            case R.id.ll_getlocation:

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (ActivityCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(LocationActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUSET_LOCATION);

                    } else {
                        mLocationClient.start();
                    }

                } else {
                    mLocationClient.start();
                }
                break;
            case R.id.tv_province:
                //点击来选择省
                final PopupWindow window = new PopupWindow(LocationActivity.this);
                LinearLayout view1 = (LinearLayout) View.inflate(LocationActivity.this,R.layout.location_poppr, null);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(20,0,20,0);
               view1.setLayoutParams(lp);


                mRvlocationpr = (RecyclerView) view1.findViewById(R.id.rv_locationpr);
                mRvlocationpr.setLayoutManager(new LinearLayoutManager(LocationActivity.this));

                LocationpoprAdapter adapter = new LocationpoprAdapter(R.layout.item_location_popr, mSheng);
                mRvlocationpr.setAdapter(adapter);

                window.setContentView(view1);
                window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

                int height = Utils.dp2px(300);
                window.setHeight(height);
                window.setBackgroundDrawable(getResources().getDrawable(R.drawable.home_toppop_bg));
                window.setTouchable(true);
                window.setOutsideTouchable(true);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                        mTvProvince.setText(mSheng.get(position).getName());
                        mTvProvince.setTextColor(getResources().getColor(R.color.maintextcolor));
                        ischeckSheng = true;
                        posation = position;
                        window.dismiss();
                    }
                });
                window.showAsDropDown(mLlChose);




                break;
            case R.id.tv_city:
                //点击来选择市
                if (ischeckSheng) {
                    final PopupWindow shiwindow = new PopupWindow(LocationActivity.this);
                    View shiview = View.inflate(LocationActivity.this, R.layout.location_poppr, null);
                    mRvlocationpr = (RecyclerView) shiview.findViewById(R.id.rv_locationpr);
                    mRvlocationpr.setLayoutManager(new LinearLayoutManager(LocationActivity.this));

                    final ArrayList<String> shi = mSheng.get(posation).getShi();
                    LocationpopshiAdapter shiadapter = new LocationpopshiAdapter(R.layout.item_location_popr, shi);
                    mRvlocationpr.setAdapter(shiadapter);

                    shiwindow.setContentView(shiview);
                    shiwindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                    int height1 = Utils.dp2px(300);
                    shiwindow.setHeight(height1);
                    shiwindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.home_toppop_bg));
                    shiwindow.setTouchable(true);
                    shiwindow.setOutsideTouchable(true);
                    shiadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                            mTvCity.setText(shi.get(position));
                            mTvCity.setTextColor(getResources().getColor(R.color.maintextcolor));
                            ischeckSheng = true;
                            shiwindow.dismiss();
                        }
                    });
                    shiwindow.showAsDropDown(mLlChose);
                } else {
                    MyToast.show("请先选择省，再选择市",R.mipmap.com_icon_cross_w);
                }


                break;
            case R.id.bt_import:
                //发送消息到首页去修改定位
                    String event = mTvCity.getText().toString();
                String   location = mTvMylocation.getText().toString();
                if (!event.equals("选择市")) {
                    EventBus.getDefault().post(event);
                } else {
                    if (location != null) {
                       String shi =  location.substring(location.indexOf("-") + 1);
                        EventBus.getDefault().post(shi);
                    } else {
                        MyToast.show("定位出现问题，请稍后再试",R.mipmap.com_icon_cross_w);
                    }
                }
                finish();


                break;
        }
    }

    @Override
    public void onReceiveLocation(final BDLocation location) {
        //获取定位结果
        StringBuffer sb = new StringBuffer(256);

        sb.append("time : ");
        sb.append(location.getTime());    //获取定位时间

        sb.append("\nerror code : ");
        sb.append(location.getLocType());    //获取类型类型

        sb.append("\nlatitude : ");
        sb.append(location.getLatitude());    //获取纬度信息

        sb.append("\nlontitude : ");
        sb.append(location.getLongitude());    //获取经度信息

        sb.append("\nradius : ");
        sb.append(location.getRadius());    //获取定位精准度

        if (location.getLocType() == BDLocation.TypeGpsLocation) {

            // GPS定位结果
            sb.append("\nspeed : ");
            sb.append(location.getSpeed());    // 单位：公里每小时

            sb.append("\nsatellite : ");
            sb.append(location.getSatelliteNumber());    //获取卫星数

            sb.append("\nheight : ");
            sb.append(location.getAltitude());    //获取海拔高度信息，单位米

            sb.append("\ndirection : ");
            sb.append(location.getDirection());    //获取方向信息，单位度

            sb.append("\naddr : ");
            sb.append(location.getAddrStr());    //获取地址信息

            sb.append("\ndescribe : ");
            sb.append("gps定位成功");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTvMylocation.setText(location.getProvince() + "-" + location.getCity());
                    MyToast.show("gps定位成功", R.mipmap.com_icon_check_w);
                }
            });
        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

            // 网络定位结果
            sb.append("\naddr : ");
            sb.append(location.getAddrStr());    //获取地址信息

            sb.append("\noperationers : ");
            sb.append(location.getOperators());    //获取运营商信息

            sb.append("\ndescribe : ");
            sb.append("网络定位成功");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTvMylocation.setText(location.getProvince() + "-" + location.getCity());
                    MyToast.show("网络定位成功", R.mipmap.com_icon_check_w);
                }
            });
        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

            // 离线定位结果
            sb.append("\ndescribe : ");
            sb.append("离线定位成功，离线定位结果也是有效的");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTvMylocation.setText(location.getProvince() + "-" + location.getCity());
                    MyToast.show("离线定位成功", R.mipmap.com_icon_check_w);
                }
            });
        } else if (location.getLocType() == BDLocation.TypeServerError) {

            sb.append("\ndescribe : ");
            sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MyToast.show("服务端网络定位失败", R.mipmap.com_icon_cross_w);
                }
            });
        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

            sb.append("\ndescribe : ");
            sb.append("网络不同导致定位失败，请检查网络是否通畅");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MyToast.show("网络不同导致定位失败，请检查网络是否通畅", R.mipmap.com_icon_cross_w);
                }
            });
        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

            sb.append("\ndescribe : ");
            sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MyToast.show("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机", R.mipmap.com_icon_cross_w);
                }
            });
        }

        sb.append("\nlocationdescribe : ");
        sb.append(location.getLocationDescribe());    //位置语义化信息





    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        if (requestCode == REQUSET_LOCATION) {

            if (grantResults.length == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                mLocationClient.start();
            }

        }


    }


}
