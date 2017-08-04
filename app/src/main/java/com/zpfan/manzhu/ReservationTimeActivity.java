package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.BussnessBean;
import com.zpfan.manzhu.bean.ReservationTimeBean;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationTimeActivity extends AppCompatActivity {

    @BindView(R.id.fl_time)
    TagFlowLayout mFlTime;
    @BindView(R.id.bt_import)
    Button mBtImport;
    @BindView(R.id.tv_firsttime)
    TextView mTvFirsttime;
    @BindView(R.id.tv_firstprice)
    TextView mTvFirstprice;
    @BindView(R.id.tv_secandtime)
    TextView mTvSecandtime;
    @BindView(R.id.tv_secandprice)
    TextView mTvSecandprice;
    @BindView(R.id.ll_secand)
    LinearLayout mLlSecand;
    @BindView(R.id.tv_threetime)
    TextView mTvThreetime;
    @BindView(R.id.tv_threeprice)
    TextView mTvThreeprice;
    @BindView(R.id.ll_three)
    LinearLayout mLlThree;
    @BindView(R.id.tv_minitime)
    TextView mTvMinitime;
    @BindView(R.id.tv_checkcount)
    TextView mTvCheckcount;
    @BindView(R.id.tv_checkprice)
    TextView mTvCheckprice;
    @BindView(R.id.tv_chosetime)
    TextView mTvChosetime;
    private BussnessBean mBussness;
    private LayoutInflater mInflater;
    ArrayList<ReservationTimeBean> checkData = new ArrayList<>();
    private DecimalFormat mDf;
    Set<Integer> checkids = new LinkedHashSet<>();
    private String mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_time);
        ButterKnife.bind(this);
        mInflater = getLayoutInflater();
        initView();
    }

    private void initView() {
        mDf = new DecimalFormat("0.00");
        Intent intent = getIntent();

        final ArrayList<ReservationTimeBean> data = intent.getParcelableArrayListExtra("data");
        mBussness = intent.getParcelableExtra("buss");
        mDate = intent.getStringExtra("date");

        mTvChosetime.setText("（当前选择日期："+ mDate.replace("-"," - ") +"）");
        ArrayList<String> checkid = intent.getStringArrayListExtra("checkid");
        for (int i = 0; i < data.size(); i++) {
            for (String s : checkid) {
                if (data.get(i).getTimeid().equals(s)) {
                    checkids.add(i);
                }
            }

        }




        //设置基本的信息
        int value_1 = mBussness.getG_appointment_time_value_1();
        int value_2 = mBussness.getG_appointment_time_value_2();
        if (value_1 != 0 && value_2 != 0) {
            mTvFirsttime.setText(value_1 + ":00" + " ~ " + value_2 + ":00");
            mTvFirstprice.setText(mBussness.getG_appointment_price_1());
        }

        int value_3 = mBussness.getG_appointment_time_value_3();
        int value_4 = mBussness.getG_appointment_time_value_4();

        if (value_3 != 0 && value_4 != 0) {
            mTvSecandtime.setText(value_3 + ":00" + " ~ " + value_4 + ":00");
            mTvSecandprice.setText(mBussness.getG_appointment_price_2());
        } else {
            mLlSecand.setVisibility(View.GONE);

        }

        int value_5 = mBussness.getG_appointment_time_value_5();
        int value_6 = mBussness.getG_appointment_time_value_6();

        if (value_5 != 0 && value_6 != 0) {
            mTvThreetime.setText(value_5 + ":00" + " ~ " + value_6 + ":00");
            mTvThreeprice.setText(mBussness.getG_appointment_price_3());
        } else {
            mLlThree.setVisibility(View.GONE);
        }

        mTvMinitime.setText("*" + mBussness.getG_appointment_mintime_value() + "小时起租");

        TagAdapter adapter = new TagAdapter(data) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv_time, mFlTime, false);
                tv.setText(data.get(position).getTime());
                return tv;
            }
        };

        mFlTime.setOnSelectListener(new TagFlowLayout.OnSelectListener() {

            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                checkData.clear();
                double allprice = 0;
                for (Integer integer : selectPosSet) {
                    checkData.add(data.get(integer));
                }

                //计算价格
                mTvCheckcount.setText(" " + checkData.size() + " ");
                for (ReservationTimeBean bean : checkData) {
                    String price = bean.getPrice();
                    Double aDouble = Double.valueOf(price);
                    allprice = allprice + aDouble;
                }

                mTvCheckprice.setText(mDf.format(allprice));


            }
        });


        mFlTime.setAdapter(adapter);

        adapter.setSelectedList(checkids);







    }


    @OnClick(R.id.bt_import)
    public void onViewClicked() {
        //提交的时候 设置数据  发送网络请求去检查有没有被别人占用的时间
        Map<String, String> map = new LinkedHashMap<>();
         map.put("time", "");
        for (ReservationTimeBean bean : checkData) {
            String time1 = map.get("time");
            if (time1.isEmpty()) {
                map.put("time", bean.getTime());
            } else {
                map.put("time", time1 + "," + bean.getTime());
            }


        }


        Call<String> time = Aplication.mIinterface.checkSercerTimeExit(mBussness.getG_UID(), mDate, map.get("time"));
        time.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("zc", "onResponse:  看看请求和返回" + call.request().toString());

                String body = response.body();
                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                    if (avatorBeen != null && avatorBeen.size() > 0) {

                        AvatorBean bean = avatorBeen.get(0);
                        Log.i("zc", "onResponse:   Kk shuju " + bean.getRetmsg());
                        if (bean.getRetmsg().equals("yes,成功")) {
                            Intent intent = new Intent();
                            intent.putParcelableArrayListExtra("checkdata", checkData);

                            ReservationTimeActivity.this.setResult(2, intent);
                            finish();
                        } else {
                            MyToast.show("你选择的时间段可能已被预约，请重新选择后提交",R.mipmap.com_icon_cross_w);
                        }

                    }

                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }
}
