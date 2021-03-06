package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.adapter.BussnessCouponAdapter;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.CouponBean;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BussnessCouponActivity extends AppCompatActivity {

    @BindView(R.id.rv_bussnesscoupon)
    RecyclerView mRvBussnesscoupon;
    private BussnessCouponAdapter mAdapter;
    private ArrayList<CouponBean> mCouponlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bussness_coupon);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();

        final String uid = intent.getStringExtra("uid");
        mRvBussnesscoupon.setLayoutManager(new LinearLayoutManager(this));


        if (uid != null) {
            //发送请求去获取优惠劵列表
            Call<String> getcouponlist = Aplication.mIinterface.getcouponlist(uid,Utils.getloginuid());

            getcouponlist.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String body = response.body();

                    if (body != null) {
                        Type type = new TypeToken<ArrayList<AvatorBean>>() {
                        }.getType();

                        ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                        if (avatorBeen != null) {
                            AvatorBean bean = avatorBeen.get(0);
                            String retmsg = bean.getRetmsg();
                            if (retmsg != null && retmsg.contains("[")) {
                                String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));

                                if (substring != null) {
                                    Type type1 = new TypeToken<ArrayList<CouponBean>>() {
                                    }.getType();
                                    mCouponlist = Utils.gson.fromJson(substring, type1);
                                    mAdapter = new BussnessCouponAdapter(R.layout.item_bussness_coupon, mCouponlist);

                                    mRvBussnesscoupon.setAdapter(mAdapter);
                                    mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                            final CouponBean bean = mCouponlist.get(position);
                                            //领取优惠劵的方法
                                            if (bean.getMember_use_get_status().equals("未领用")) {
                                                Call<String> operashopcoupon = Aplication.mIinterface.operashopcoupon(Utils.getloginuid(), bean.getId() + "");

                                                operashopcoupon.enqueue(new Callback<String>() {
                                                    @Override
                                                    public void onResponse(Call<String> call, Response<String> response) {
                                                        String body = response.body();

                                                        if (body != null) {

                                                            Type type = new TypeToken<ArrayList<AvatorBean>>() {
                                                            }.getType();

                                                            ArrayList<AvatorBean> list = Utils.gson.fromJson(body, type);

                                                            if (list != null) {
                                                                AvatorBean bean1 = list.get(0);
                                                                if (bean1.getRetmsg().equals("True")) {
                                                                    bean.setMember_use_get_status("已领用");
                                                                    mAdapter.notifyDataSetChanged();
                                                                }


                                                            }


                                                        }


                                                    }

                                                    @Override
                                                    public void onFailure(Call<String> call, Throwable t) {

                                                    }
                                                });
                                            } else {
                                                MyToast.show("你已经领取过该优惠劵，请勿重复领取",R.mipmap.com_icon_cross_w);
                                                view.setEnabled(false);
                                            }

                                        }
                                    });

                                }


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
}
