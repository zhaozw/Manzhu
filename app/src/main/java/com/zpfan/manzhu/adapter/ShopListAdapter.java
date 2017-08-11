package com.zpfan.manzhu.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zpfan.manzhu.Aplication;
import com.zpfan.manzhu.IdleDetailActivity;
import com.zpfan.manzhu.LoginActivity;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.BussnessBean;
import com.zpfan.manzhu.bean.ShopBean;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public class ShopListAdapter extends BaseQuickAdapter<ShopBean,BaseViewHolder> {
    public ShopListAdapter(@LayoutRes int layoutResId, @Nullable List<ShopBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShopBean item) {
        RoundedImageView cover = helper.getView(R.id.iv_cover);
        final ImageView ivcollect = helper.getView(R.id.iv_collect);
        final TextView tvcollect = helper.getView(R.id.tv_collect);
        LinearLayout llcollect = helper.getView(R.id.ll_collect);
        TextView tvshoptype2 = helper.getView(R.id.tv_shoptype2);
        RecyclerView rvshopgood = helper.getView(R.id.rv_shopgood);

        helper.addOnClickListener(R.id.rl_todetil);

        helper.setText(R.id.tv_shopname, item.getS_Name())
                .setText(R.id.tv_userlv, "Lv." + item.getS_LevelNumber())
                .setText(R.id.tv_allsale, "总销量  " + item.getS_AllSellNumber())
                .setText(R.id.tv_shopprv, item.getS_Com_Province() + "-" + item.getS_Com_City())
        ;

        String category = item.getShop_category();

        if (category.contains("|")) {
            String[] split = category.split("\\|");
            helper.setText(R.id.tv_shoptype1, split[0])
                    .setText(R.id.tv_shoptype2, split[1]);


        } else {
            tvshoptype2.setVisibility(View.GONE);
            helper.setText(R.id.tv_shoptype1, category);
        }



        Glide.with(mContext).load(item.getS_Logo()).into(cover);
        //判断对象是否收藏过该店铺

        isCollect(item, ivcollect, tvcollect);
        //设置店铺的商品上去
        LinearLayoutManager layout = new LinearLayoutManager(mContext);
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvshopgood.setLayoutManager(layout);
        //将店铺的东西显示出来
        final List<BussnessBean> goodslist = item.getRecommended_goodslist();
        ShopGoodListAdapter adapter = new ShopGoodListAdapter(R.layout.item_shopgood_hor, goodslist);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, IdleDetailActivity.class);
                BussnessBean value = goodslist.get(position);
                intent.putExtra("id", value);
                if (value.getG_Type().equals("二手商品")) {
                intent.putExtra("type", "idle");
                } else if (value.getG_Type().equals("服务")) {
                    intent.putExtra("type", "server");
                } else if (value.getG_Type().equals("新商品")) {
                    intent.putExtra("type", "new");
                }
                mContext.startActivity(intent);

            }
        });
        rvshopgood.setAdapter(adapter);



        llcollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //收藏该店铺
                if (Utils.isUserLogin()) {
                    Call<String> operacollectionfunction = Aplication.mIinterface.operacollectionfunction("店铺", item.getId() + "", Utils.getloginuid());

                    operacollectionfunction.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String body = response.body();

                            if (body != null) {
                                Type type = new TypeToken<ArrayList<AvatorBean>>() {
                                }.getType();

                                ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);

                                if (avatorBeen != null && avatorBeen.size() > 0) {
                                    AvatorBean bean = avatorBeen.get(0);
                                    String retmsg = bean.getRetmsg();
                                    Log.i("zc", "onResponse:   看看返回的数据" + retmsg);
                                    if (retmsg.equals("true")) {
                                      notifyDataSetChanged();
                                    } else {
                                        MyToast.show("由于未知原因，收藏失败",R.mipmap.com_icon_cross_w);
                                        ivcollect.setImageResource(R.mipmap.com_icon_fav_2_w);
                                        tvcollect.setText("收藏店铺");
                                    }


                                }



                            }



                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });


                } else {
                    mContext.sendBroadcast(new Intent(mContext, LoginActivity.class));
                }

            }
        });




    }

    private void getShopGood(RecyclerView rvshopgood, String uid) {
        Call<String> getgoodlistbyshop = Aplication.mIinterface.getgoodlistbyshop("1", uid, "");

        getgoodlistbyshop.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("zc", "onResponse:  看看发送的请求" + call.request().toString());
                String body = response.body();
                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);

                    if (avatorBeen != null && avatorBeen.size() > 0) {
                        AvatorBean bean = avatorBeen.get(0);
                        String retmsg = bean.getRetmsg();
                        Log.i("zc", "onResponse:   看看返回的数据" + retmsg);


                    }



                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });



    }

    private void isCollect(ShopBean item, final ImageView ivcollect, final TextView tvcollect) {
        Call<String> iscollect = Aplication.mIinterface.operaisCollection("店铺", item.getId() + "", Utils.getloginuid());

        iscollect.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                    if (avatorBeen != null && avatorBeen.size() > 0) {
                        AvatorBean bean = avatorBeen.get(0);
                        String retmsg = bean.getRetmsg();
                        if (retmsg.equals("false")) {
                            ivcollect.setImageResource(R.mipmap.com_icon_fav_2_w);
                            tvcollect.setText("收藏店铺");

                        } else if (retmsg.equals("true")) {

                            ivcollect.setImageResource(R.mipmap.com_icon_fav_w);
                            tvcollect.setText("已收藏");

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
