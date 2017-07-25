package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.ShopCartbean;
import com.zpfan.manzhu.myui.EditListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class ShopCartAdapter extends BaseQuickAdapter<ShopCartbean.CarshoplistBean,BaseViewHolder> {

    EditListener mListener ;
    private double price = 0.00;
    ArrayList<ShopCartbean.CarshoplistBean.CargoodslistBean> checeGood = new ArrayList<>();

    public ShopCartAdapter(@LayoutRes int layoutResId, @Nullable List<ShopCartbean.CarshoplistBean> data,EditListener listener) {
        super(layoutResId, data);

        mListener = listener;

    }


    @Override
    protected void convert(final BaseViewHolder helper, final ShopCartbean.CarshoplistBean item) {
        checeGood.clear();

        if (item.getCargoodslist().size() > 0) {
            helper.setText(R.id.tv_shopname, item.getMember_Name())
            .setText( R.id.tv_userlv,"Lv."  + item.getMember_Level());

            RecyclerView rv_shop = helper.getView(R.id.rv_shopcartitem);
            final ImageView ivcheckall = helper.getView(R.id.iv_checkall);


            rv_shop.setLayoutManager(new LinearLayoutManager(mContext));
            final List<ShopCartbean.CarshoplistBean.CargoodslistBean> cargoodslist = item.getCargoodslist();
            final ShopGoodadapter goodadapter = new ShopGoodadapter(R.layout.item_shopgoods, cargoodslist);
            rv_shop.setAdapter(goodadapter);

            goodadapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    boolean checked = cargoodslist.get(position).isChecked();
                    cargoodslist.get(position).setChecked(!checked);
                    if (!checked) {
                        checeGood.add(cargoodslist.get(position));
                    } else {
                        checeGood.remove(cargoodslist.get(position));
                    }
                    goodadapter.notifyDataSetChanged();
                    mListener.edit(checeGood);
                }
            });



            ivcheckall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    /*if (isCheckall) {
                        for (ShopCartbean.CarshoplistBean.CargoodslistBean bean : item.getCargoodslist()) {
                            bean.setChecked(false);

                            checeGood.remove(bean);
                        }
                        goodadapter.notifyDataSetChanged();
                        ivcheckall.setImageResource(R.mipmap.com_icon_multcheck_ept);

                    } else {
                        for (ShopCartbean.CarshoplistBean.CargoodslistBean bean : item.getCargoodslist()) {
                            bean.setChecked(true);
                            checeGood.add(bean);

                        }
                        goodadapter.notifyDataSetChanged();
                        ivcheckall.setImageResource(R.mipmap.com_icon_multcheck_bl);
                    }*/
                    for (ShopCartbean.CarshoplistBean.CargoodslistBean bean : item.getCargoodslist()) {
                        boolean checked = bean.isChecked();
                        bean.setChecked(!checked);

                        if (!checked) {
                            checeGood.add(bean);
                            ivcheckall.setImageResource(R.mipmap.com_icon_multcheck_bl);
                        } else {
                            checeGood.remove(bean);
                            ivcheckall.setImageResource(R.mipmap.com_icon_multcheck_ept);
                        }

                    }



                    goodadapter.notifyDataSetChanged();
                    mListener.edit(checeGood);
                }
            });






            final LinearLayout lledit = helper.getView(R.id.ll_edit);
            final LinearLayout llfinishedit = helper.getView(R.id.ll_finishedit);






            lledit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lledit.setVisibility(View.GONE);
                    llfinishedit.setVisibility(View.VISIBLE);
                    goodadapter.editgood(true);
                }
            });

            llfinishedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    llfinishedit.setVisibility(View.GONE);
                    lledit.setVisibility(View.VISIBLE);
                    goodadapter.editgood(false);
                }
            });



        }





    }


}
