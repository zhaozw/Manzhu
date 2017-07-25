package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.Aplication;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.FormatBean;
import com.zpfan.manzhu.bean.ShopCartbean;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class ShopGoodadapter extends BaseQuickAdapter<ShopCartbean.CarshoplistBean.CargoodslistBean,BaseViewHolder>  {

    private final List<ShopCartbean.CarshoplistBean.CargoodslistBean> data;

    BaseViewHolder mhelper;
    private boolean isedit = false;
    ArrayList<Integer> checedid = new ArrayList<>();



    public ShopGoodadapter(@LayoutRes int layoutResId, @Nullable List<ShopCartbean.CarshoplistBean.CargoodslistBean> data) {
        super(layoutResId, data);

        this.data = data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShopCartbean.CarshoplistBean.CargoodslistBean item) {
        mhelper = helper;
        helper.setText(R.id.tv_goodname, item.getGoods_model().getG_Title())
        .setText(R.id.tv_carcount,"x"+item.getCarCount());
        ImageView  mIvcheck = helper.getView(R.id.iv_check);
        TextView tvgoodname = helper.getView(R.id.tv_goodname);
        TextView tvformat = helper.getView(R.id.tv_format);
        LinearLayout llprice = helper.getView(R.id.ll_price);
        LinearLayout lledit = helper.getView(R.id.ll_edit);
        LinearLayout lldelete = helper.getView(R.id.ll_delete);
        ImageView ivcover = helper.getView(R.id.iv_shopcover);


        final String uid = item.getGoods_Spcification_UID();
        if (!uid.equals("")) {

            Call<String> getsprcification = Aplication.mIinterface.getsprcification(uid);
            getsprcification.enqueue(new Callback<String>() {
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
                            if (retmsg.contains("[")) {
                                Type type1 = new TypeToken<ArrayList<FormatBean>>() {
                                }.getType();


                                ArrayList<FormatBean> formatBeen = Utils.gson.fromJson(retmsg, type1);
                                FormatBean bean1 = formatBeen.get(0);

                                helper.setText(R.id.tv_format, bean1.getPS_AttributeValues());
                                helper.setText(R.id.tv_goodprice, bean1.getPS_FixedPrice());


                            }


                        }

                    }


                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });


        } else {
            helper.setText(R.id.tv_goodprice, item.getGoods_model().getG_FixedPrice());
        }




        final int position = helper.getPosition();


        helper.addOnClickListener(R.id.iv_check);

        if (data.get(position).isChecked()) {
            mIvcheck.setImageResource(R.mipmap.com_icon_multcheck_bl);
        } else {
            mIvcheck.setImageResource(R.mipmap.com_icon_multcheck_ept);
        }

        Glide.with(mContext).load(item.getGoods_model().getG_Cover()).into(ivcover);



        if (isedit) {
            //编辑的状态
            mIvcheck.setVisibility(View.GONE);
            tvgoodname.setVisibility(View.GONE);
            tvformat.setVisibility(View.GONE);
            llprice.setVisibility(View.GONE);

            lledit.setVisibility(View.VISIBLE);
            lldelete.setVisibility(View.VISIBLE);



        } else {
            mIvcheck.setVisibility(View.VISIBLE);
            tvgoodname.setVisibility(View.VISIBLE);
            tvformat.setVisibility(View.VISIBLE);
            llprice.setVisibility(View.VISIBLE);

            lledit.setVisibility(View.GONE);
            lldelete.setVisibility(View.GONE);
        }




    }
    
    
    protected void  editgood(boolean b){
        isedit = b;
       this.notifyDataSetChanged();
    }


}
