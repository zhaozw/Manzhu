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
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMMessage;
import com.zpfan.manzhu.Aplication;
import com.zpfan.manzhu.LeaveMessageActivity;
import com.zpfan.manzhu.OtherActivity;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.FormatBean;
import com.zpfan.manzhu.bean.OrderCouponBean;
import com.zpfan.manzhu.bean.ShopCartbean;
import com.zpfan.manzhu.event.LeaveMessageEvent;
import com.zpfan.manzhu.myui.EaseActivity;
import com.zpfan.manzhu.myui.MyButtonDialog;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.EditListener;
import com.zpfan.manzhu.utils.SPUtils;
import com.zpfan.manzhu.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class OrderSureAdapter extends BaseQuickAdapter<ShopCartbean.CarshoplistBean,BaseViewHolder> {

    private OrderGoodAdapter mOrderGoodAdapter;
    List<ShopCartbean.CarshoplistBean> data;
    EditListener mListener;
    private View mFootView;
    private DecimalFormat mDf;


    public OrderSureAdapter(@LayoutRes int layoutResId, @Nullable List<ShopCartbean.CarshoplistBean> data, EditListener listener) {
        super(layoutResId, data);
        this.data = data;
        mListener = listener;
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShopCartbean.CarshoplistBean item) {
        mDf = new DecimalFormat("0.00");
        ArrayList<OrderCouponBean> mCouponBeanArrayList = new ArrayList<>();


        helper.setText(R.id.tv_shopname, item.getMember_Name()).setText(R.id.tv_userlv,"Lv."  +item.getMember_Level());


        LinearLayout llmessage = helper.getView(R.id.ll_message);

        llmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, EaseActivity.class);
                intent.putExtra("userId", item.getCargoodslist().get(0).getGoods_model().getG_ContactPhone());
                intent.putExtra("usercn", item.getMember_Name());
                intent.putExtra("chatType", EMMessage.ChatType.Chat);
                mContext.startActivity(intent);


            }
        });

        //展示商品的信息
        RecyclerView rvorder = helper.getView(R.id.rv_orderitem);
        rvorder.setLayoutManager(new LinearLayoutManager(mContext));
        Log.i("zc", "convert:   查看数据" + item.getCheckgoodslist().size());

        mOrderGoodAdapter = new OrderGoodAdapter(R.layout.item_ordergoods, item.getCheckgoodslist(),mListener);
        getCouponlist(item,mCouponBeanArrayList);
        mFootView = View.inflate(mContext, R.layout.order_footview, null);
        initFootView(item,mCouponBeanArrayList);
       // mOrderGoodAdapter.notifyDataSetChanged();
        mOrderGoodAdapter.addFooterView(mFootView);
        mOrderGoodAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, final View view, final int position) {
                switch (view.getId()) {
                    case R.id.ll_delete:
                        final MyButtonDialog dialog = new MyButtonDialog(mContext, R.style.Dialog, "订单管理", "确认删除该商品？");
                        dialog.show();
                        dialog.setoncancleClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        dialog.setonsureClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (data.size() > 1) {
                                    ShopCartbean.CarshoplistBean bean = data.get(helper.getPosition() -1);
                                    bean.getCheckgoodslist().remove(position);
                                    if (bean.getCheckgoodslist().size() == 0) {
                                        remove(helper.getPosition() - 1);

                                    }
                                    notifyDataSetChanged();
                                } else if (data.size() == 1) {
                                    ShopCartbean.CarshoplistBean bean = data.get(0);
                                    if (bean.getCheckgoodslist().size() == 1) {


                                    }else if (bean.getCheckgoodslist().size() == 2){
                                        bean.getCheckgoodslist().remove(position);
                                        ShopCartbean.CarshoplistBean.CargoodslistBean bean1 = bean.getCheckgoodslist().get(0);
                                        bean1.setNodelete(true);
                                    }else if (bean.getCheckgoodslist().size() > 1) {
                                        bean.getCheckgoodslist().remove(position);

                                    }
                                    notifyDataSetChanged();

                                }
                                dialog.dismiss();
                            }
                        });



                        break;

                }


            }
        });
        rvorder.setAdapter(mOrderGoodAdapter);

        //去查询交易方式
        String usesheng = SPUtils.getInstance().getString("usesheng", "");
        for (ShopCartbean.CarshoplistBean bean : data) {
            double allweight = 0;
            String uid = "";
            List<ShopCartbean.CarshoplistBean.CargoodslistBean> cargoodslist = bean.getCheckgoodslist();
            if (cargoodslist.size() > 0) {
                ShopCartbean.CarshoplistBean.CargoodslistBean bean1 = cargoodslist.get(0);
                for (ShopCartbean.CarshoplistBean.CargoodslistBean cargoodslistBean : cargoodslist) {
                    allweight = allweight + cargoodslistBean.getGoods_model().getG_Weight() * cargoodslistBean.getCarCount();
                    uid = uid +"," + bean1.getGoods_UID();
                }
                getbuyStyle(usesheng,mDf.format(allweight),uid,bean1.getGoods_model().getG_Province(),bean);
            }


        }




    }

    private void getbuyStyle(String usesheng, String format, String uid, String bussnesssheng, final ShopCartbean.CarshoplistBean item) {

        Call<String> getorderbydealstyle = Aplication.mIinterface.getorderbydealstyle("Car", "二手商品", Utils.getloginuid(),bussnesssheng, usesheng, format, uid);
        getorderbydealstyle.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("zc", "getbuyStyle:   看看发送的请求" + call.request().toString());
                String body = response.body();

                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);

                    if (avatorBeen != null && avatorBeen.size() > 0) {

                        AvatorBean bean = avatorBeen.get(0);


                        String retmsg = bean.getRetmsg();
                        item.setHuoqujiaoyi(retmsg);
                        //初始化交易方式 默认为线上
                        if (retmsg.contains("线上")) {
                            //设置为线上交易
                            item.setJiaoyi("线上交易");
                            //同时初始化运费
                            List<ShopCartbean.CarshoplistBean.CargoodslistBean> checkgoodslist = item.getCheckgoodslist();
                            double yunfei = 0;
                            for (ShopCartbean.CarshoplistBean.CargoodslistBean cargoodslistBean : checkgoodslist) {
                                String money = cargoodslistBean.getGoods_model().getG_CourierMoney();
                                Double aDouble = Double.valueOf(money);
                                yunfei = yunfei + aDouble;
                            }
                            item.setYunfei(yunfei);


                        } else {
                            item.setJiaoyi("线下交易");
                        }



                    }
                } else {

                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }


    private void getCouponlist(ShopCartbean.CarshoplistBean item, final ArrayList<OrderCouponBean> mCouponBeanArrayList) {
        double price = 0;
        for (ShopCartbean.CarshoplistBean.CargoodslistBean bean : item.getCheckgoodslist()) {

            if (!bean.getSpUid().equals("")) {
                List<FormatBean> specifications = bean.getGoods_model().getGoods_specifications();
                for (FormatBean specification : specifications) {
                    String price1 = specification.getPS_FixedPrice();
                    int count = bean.getCarCount();
                    double v = Double.valueOf(price1) * count;
                    price = price + v;
                }

            } else {
                String fixedPrice = bean.getGoods_model().getG_FixedPrice();
                Double aDouble = Double.valueOf(fixedPrice);

                price = price + aDouble;


            }



        }

        Log.i("zc", "getCouponlist:  看看总价格对不对" + price);



        Call<String> getordercouponlist = Aplication.mIinterface.getordercouponlist(Utils.getloginuid(), item.getCheckgoodslist().get(0).getBussiness_UID(), mDf.format(price), "仅购物", "");

        getordercouponlist.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();

                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> been = Utils.gson.fromJson(body, type);
                    if (been != null && been.size() > 0) {
                        AvatorBean bean = been.get(0);
                        String retmsg = bean.getRetmsg();

                        if (retmsg.contains("[")) {
                            String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));

                            if (substring != null) {
                                Type type1 = new TypeToken<ArrayList<OrderCouponBean>>() {
                                }.getType();

                                ArrayList<OrderCouponBean> been1 = Utils.gson.fromJson(substring, type1);

                                mCouponBeanArrayList.clear();
                                mCouponBeanArrayList.addAll(been1);

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

    private void initFootView(final ShopCartbean.CarshoplistBean item, final ArrayList<OrderCouponBean> mCouponBeanArrayList) {
        Log.i("zc", "initFootView:   初始化foot了吗");
        final TextView tvcoupon = (TextView) mFootView.findViewById(R.id.tv_coupon);
        final TextView tvonline = (TextView) mFootView.findViewById(R.id.tv_online);
        final TextView tvleavemessage = (TextView) mFootView.findViewById(R.id.tv_leavemessage);
        final TextView yunfei = (TextView) mFootView.findViewById(R.id.tv_yunfei);
        final TextView tvcouponmoney = (TextView) mFootView.findViewById(R.id.tv_couponmoney);

        View other = mFootView.findViewById(R.id.other);


        ImageView ivleavemessage = (ImageView) mFootView.findViewById(R.id.iv_leavemessage);
        ImageView ivother = (ImageView) mFootView.findViewById(R.id.iv_other);
        ImageView ivcoupon = (ImageView) mFootView.findViewById(R.id.iv_coupon);
        ImageView ivonline = (ImageView) mFootView.findViewById(R.id.iv_online);

        tvleavemessage.setText(item.getLiuyan());
        DecimalFormat df = new DecimalFormat("0.00");
        double zongyunfei = 0.00;
        double youhuijuan = 0.00;
        for (ShopCartbean.CarshoplistBean.CargoodslistBean bean : item.getCheckgoodslist()) {
            String money = bean.getGoods_model().getG_CourierMoney();
            Double aDouble = Double.valueOf(money);
            zongyunfei = zongyunfei + aDouble;
            yunfei.setText(df.format(zongyunfei));
            item.setYunfei(zongyunfei);
            mListener.edit(null);
        }
        yunfei.setText(df.format(zongyunfei));
        tvcouponmoney.setText(df.format(youhuijuan));





        // 优惠劵的选择
        tvcoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final PopupWindow couponWindow = new PopupWindow(mContext);
                final View couponpop = View.inflate(mContext, R.layout.format_popwindow, null);
                RecyclerView rvcoupon = (RecyclerView) couponpop.findViewById(R.id.rv_format);
                rvcoupon.setLayoutManager(new LinearLayoutManager(mContext));

                if (mCouponBeanArrayList.size() > 0) {
                    CouponpopAdapter couponadapter = new CouponpopAdapter(R.layout.item_location_popr, mCouponBeanArrayList);
                    couponadapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            OrderCouponBean bean = mCouponBeanArrayList.get(position);

                            item.setCouponid(bean.getId() + "");


                            String money = bean.getMC_PreferentialMoney();
                            Double aDouble = Double.valueOf(money);
                            tvcouponmoney.setText(mDf.format(aDouble));
                            item.setCoupon(mDf.format(aDouble));
                            tvcoupon.setText("满 ¥ " + bean.getMC_MeetMoney() + "减 ¥ " + bean.getMC_PreferentialMoney());

                            couponWindow.dismiss();
                            mListener.edit(null);
                        }
                    });

                    rvcoupon.setAdapter(couponadapter);
                    couponWindow.setContentView(couponpop);
                    // int height = dp2px(LinearLayout.LayoutParams.WRAP_CONTENT);
                    couponWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

                    couponWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                    couponWindow.setTouchable(true);
                    couponWindow.setOutsideTouchable(true);

                    couponWindow.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.home_toppop_bg));
                    couponWindow.showAsDropDown(tvcoupon);
                } else {
                    MyToast.show("暂无可用优惠劵",R.mipmap.com_icon_cross_w);
                    tvcoupon.setEnabled(false);
                }

            }
        });


        tvonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String jiaoyi = item.getHuoqujiaoyi();

                if (jiaoyi.length() > 5){
                    //线上交易和线下交易
                    Log.i("zc", "onClick:   进来了吗");
                    final PopupWindow onlineWindow = new PopupWindow(mContext);
                    final View onlinepop = View.inflate(mContext, R.layout.format_popwindow, null);
                    RecyclerView rvonline = (RecyclerView) onlinepop.findViewById(R.id.rv_format);
                    rvonline.setLayoutManager(new LinearLayoutManager(mContext));
                    ArrayList<String> location = new ArrayList<String>();
                    location.add("线上交易");
                    location.add("线下交易");
                    FormartAdapter onlineadapter = new FormartAdapter(R.layout.item_location_popr, location);
                    onlineadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            DecimalFormat df = new DecimalFormat("0.00");
                            double zongyunfei = 0.00;
                            if (position == 0) {
                                double zongyunfe = 0;
                                //线上交易  遍历　运费相加
                                for (ShopCartbean.CarshoplistBean.CargoodslistBean bean : item.getCheckgoodslist()) {

                                    Double aDouble = Double.valueOf(bean.getGoods_model().getG_CourierMoney());
                                    zongyunfe = zongyunfe + aDouble;
                                }
                                    Log.i("zc", "onItemClick:   看看总运费"+ zongyunfe);

                                yunfei.setText(df.format(zongyunfe));
                                item.setYunfei(zongyunfe);
                                tvonline.setText("线上交易");
                                item.setJiaoyi("线上交易");

                            } else {
                                //线下交易
                                tvonline.setText("线下交易");
                                zongyunfei = 0.00;
                                yunfei.setText(df.format(zongyunfei));
                                item.setYunfei(0.00);
                                item.setJiaoyi("线下交易");
                            }
                            mListener.edit(null);
                            onlineWindow.dismiss();
                        }
                    });
                    rvonline.setAdapter(onlineadapter);
                    onlineWindow.setContentView(onlinepop);
                    // int height = dp2px(LinearLayout.LayoutParams.WRAP_CONTENT);
                    onlineWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                    int i = Utils.dp2px(100);
                    onlineWindow.setWidth(i);
                    onlineWindow.setTouchable(true);
                    onlineWindow.setOutsideTouchable(true);

                    onlineWindow.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.home_toppop_bg));
                    onlineWindow.showAsDropDown(tvonline);
                }else {
                    double zongyunfei = 0.00;
                    if (jiaoyi.equals("线上交易|")) {
                        double zongyunfe = 0;
                        //线上交易  遍历　运费相加
                        for (ShopCartbean.CarshoplistBean.CargoodslistBean bean : item.getCheckgoodslist()) {
                            Double aDouble = Double.valueOf(bean.getGoods_model().getG_CourierMoney());
                            zongyunfe = zongyunfe + aDouble;

                            Log.i("zc", "onItemClick:   看看总运费"+ zongyunfe);
                        }
                        yunfei.setText(mDf.format(zongyunfe));
                        item.setYunfei(zongyunfe);
                        tvonline.setText("线上交易");
                        item.setJiaoyi("线上交易");
                        MyToast.show("本商品只支持线上交易",R.mipmap.com_icon_cross_w);
                    } else if (jiaoyi.equals("线下交易|")) {
                        tvonline.setText("线下交易");
                        zongyunfei = 0.00;
                        yunfei.setText(mDf.format(zongyunfei));
                        item.setYunfei(0.00);
                        item.setJiaoyi("线下交易");
                        MyToast.show("线下交易",R.mipmap.com_icon_cross_w);
                    }

                }





            }
        });





        tvleavemessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editLiuyan(item, tvleavemessage);
            }
        });


        ivleavemessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editLiuyan(item, tvleavemessage);
            }
        });

        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mContext.startActivity(new Intent(mContext, OtherActivity.class));
            }
        });


    }

    private void editLiuyan(ShopCartbean.CarshoplistBean item, TextView tvleavemessage) {
        Intent intent = new Intent(mContext, LeaveMessageActivity.class);
        intent.putExtra("item", item);
        intent.putExtra("a", tvleavemessage.getText().toString());
        mContext.startActivity(intent);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void getliuyan(LeaveMessageEvent event){
        Log.i("zc", "getliuyan:   接受到消息了吗");
        for (ShopCartbean.CarshoplistBean bean : data) {
            if (bean.getMember_UID().equals(event.getBussness())) {
                bean.setLiuyan(event.getMessagae());
                this.notifyAll();
            }
        }

    }





}
