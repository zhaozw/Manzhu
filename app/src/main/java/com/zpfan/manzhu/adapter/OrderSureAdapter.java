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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.chat.EMMessage;
import com.zpfan.manzhu.Aplication;
import com.zpfan.manzhu.LeaveMessageActivity;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.ShopCartbean;
import com.zpfan.manzhu.event.LeaveMessageEvent;
import com.zpfan.manzhu.myui.EaseActivity;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.EditListener;
import com.zpfan.manzhu.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        getCouponlist(item);
        mFootView = View.inflate(mContext, R.layout.order_footview, null);
        initFootView(item);
        mOrderGoodAdapter.notifyDataSetChanged();
        mOrderGoodAdapter.addFooterView(mFootView);
        mOrderGoodAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.ll_delete:
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
                                MyToast.show("最后一件商品不能删除",R.mipmap.com_icon_cross_w);
                            } else if (bean.getCheckgoodslist().size() > 1) {
                                bean.getCheckgoodslist().remove(position);

                            }
                            notifyDataSetChanged();

                        }


                        break;

                }


            }
        });
        rvorder.setAdapter(mOrderGoodAdapter);




    }

    private void getCouponlist(ShopCartbean.CarshoplistBean item) {
        Call<String> getordercouponlist = Aplication.mIinterface.getordercouponlist(Utils.getloginuid(), item.getCheckgoodslist().get(0).getBussiness_UID(), "0", "0", "0");
        Log.i("coupon", "getCouponlist:   看看数据" + Utils.getloginuid() + item.getCargoodslist().get(0).getBussiness_UID());
        getordercouponlist.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                Log.i("coupon", "onResponse:   看看发送的请求和返回" + call.request().toString());
                if (body != null) {

                    Log.i("coupon", "onResponse:   看看返回的数据是什么" + body);

                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("coupon", "onFailure:  看看返回" + call.request().toString());
            }
        });





    }

    private void initFootView(final ShopCartbean.CarshoplistBean item) {
        TextView tvcoupon = (TextView) mFootView.findViewById(R.id.tv_coupon);
        TextView tvonline = (TextView) mFootView.findViewById(R.id.tv_online);
        final TextView tvleavemessage = (TextView) mFootView.findViewById(R.id.tv_leavemessage);

        View other = mFootView.findViewById(R.id.other);
        View leavemessage = mFootView.findViewById(R.id.leavemessage);

        ImageView ivleavemessage = (ImageView) mFootView.findViewById(R.id.iv_leavemessage);
        ImageView ivother = (ImageView) mFootView.findViewById(R.id.iv_other);
        ImageView ivcoupon = (ImageView) mFootView.findViewById(R.id.iv_coupon);
        ImageView ivonline = (ImageView) mFootView.findViewById(R.id.iv_online);

        tvleavemessage.setText(item.getLiuyan());


        // 优惠劵的选择
        tvcoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        tvleavemessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editLiuyan(item, tvleavemessage);
            }
        });
        leavemessage.setOnClickListener(new View.OnClickListener() {
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
