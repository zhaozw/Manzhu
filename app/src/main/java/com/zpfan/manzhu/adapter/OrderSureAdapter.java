package com.zpfan.manzhu.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.chat.EMMessage;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.ShopCartbean;
import com.zpfan.manzhu.myui.EaseActivity;
import com.zpfan.manzhu.myui.MyToast;

import java.util.List;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class OrderSureAdapter extends BaseQuickAdapter<ShopCartbean.CarshoplistBean,BaseViewHolder> {

    private OrderGoodAdapter mOrderGoodAdapter;
    List<ShopCartbean.CarshoplistBean> data;

    public OrderSureAdapter(@LayoutRes int layoutResId, @Nullable List<ShopCartbean.CarshoplistBean> data) {
        super(layoutResId, data);
        this.data = data;
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

        mOrderGoodAdapter = new OrderGoodAdapter(R.layout.item_ordergoods, item.getCheckgoodslist());
        View footView = View.inflate(mContext, R.layout.order_footview, null);
        initFootView();
        mOrderGoodAdapter.addFooterView(footView);
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

    private void initFootView() {



    }


}
