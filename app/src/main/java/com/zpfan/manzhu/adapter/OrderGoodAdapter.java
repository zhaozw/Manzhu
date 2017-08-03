package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.FormatBean;
import com.zpfan.manzhu.bean.ShopCartbean;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.EditListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class OrderGoodAdapter extends BaseQuickAdapter<ShopCartbean.CarshoplistBean.CargoodslistBean,BaseViewHolder> {


    private final List<ShopCartbean.CarshoplistBean.CargoodslistBean> data;
    EditListener mListener;

    public OrderGoodAdapter(@LayoutRes int layoutResId, @Nullable List<ShopCartbean.CarshoplistBean.CargoodslistBean> data,EditListener listener) {
        super(layoutResId, data);
        mListener = listener;
        this.data = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShopCartbean.CarshoplistBean.CargoodslistBean item) {

        final ArrayList<FormatBean> format = new ArrayList<>();

        ShopCartbean.CarshoplistBean.CargoodslistBean.GoodsModelBean model = item.getGoods_model();
        helper.setText(R.id.tv_goodname, model.getG_Title());
        View dashline = helper.getView(R.id.dashline);
        View dashline1 = helper.getView(R.id.dashline1);
        dashline.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        dashline1.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        helper.addOnClickListener(R.id.ll_delete);
        final LinearLayout lleditmoney = helper.getView(R.id.ll_editmoney); //价格的编辑
        final LinearLayout llcheckformate = helper.getView(R.id.ll_checkformate); // 规格的选择
        LinearLayout llfinishedit = helper.getView(R.id.ll_finishedit); // 完成编辑的按钮
        final LinearLayout llfinish = helper.getView(R.id.ll_finish); //删除 和完成编辑
        //删除商品的按钮
        LinearLayout  mLldelete = helper.getView(R.id.ll_delete);
        final LinearLayout llnormal = helper.getView(R.id.ll_normal); //删除商品的按钮
        final LinearLayout lledit = helper.getView(R.id.ll_edit); //删除商品的按钮
        final TextView tvformat = helper.getView(R.id.tv_format);
        final TextView tvcheckformat = helper.getView(R.id.tv_checkformate);
        final TextView tvgooprice = helper.getView(R.id.tv_goodprice);


        final ImageView  ivedit = helper.getView(R.id.iv_edit);
        ImageView ivshopcover = helper.getView(R.id.iv_shopcover);

        if (item.isNodelete()) {
            mLldelete.setVisibility(View.GONE);
        }



        Glide.with(mContext).load(model.getG_Cover()).into(ivshopcover);


        final EditText edgoodmoney = helper.getView(R.id.et_goodmoney);

           String uid = item.getGoods_Spcification_UID();
       if (uid.equals("")){
           format.clear();

           tvformat.setText("");
           tvcheckformat.setText("");
           helper.setText(R.id.tv_goodprice, model.getG_FixedPrice())
                   .setText(R.id.tv_kucun,"（库存：" + model.getG_StockNum() + "）");
           edgoodmoney.setText(model.getG_FixedPrice());
           item.setEditMoney(model.getG_FixedPrice());
            item.setKycun(model.getG_StockNum());
       }else {
           List<FormatBean> specifications = model.getGoods_specifications();
           for (FormatBean specification : specifications) {
               format.add(specification);

               if (specification.getPS_UniqueID().equals(uid)) {
                   tvformat.setText(specification.getPS_AttributeValues());
                   tvcheckformat.setText(specification.getPS_AttributeValues());
                   helper.setText(R.id.tv_goodprice, specification.getPS_FixedPrice())
                   .setText(R.id.tv_kucun,"（库存：" +specification.getPS_Inventory() + "）");
                    edgoodmoney.setText(specification.getPS_FixedPrice());
                   item.setEditMoney(specification.getPS_FixedPrice());
                    item.setKycun(specification.getPS_Inventory());
               }


           }

       }

        helper.setText(R.id.tv_carcount, "x" +item.getCarCount())
        .setText(R.id.tv_count,item.getCarCount() + "");

        final TextView tvcount = helper.getView(R.id.tv_count);
        final TextView tvcarcount = helper.getView(R.id.tv_carcount);
        RelativeLayout btup = helper.getView(R.id.bt_up);
        RelativeLayout btdown = helper.getView(R.id.bt_down);
        String s = edgoodmoney.getText().toString();
        edgoodmoney.setSelection(s.length());

        //如果有规格的话 就把规格的选项展示出来 如果没有的话 就不展示
        final List<FormatBean> specifications = item.getGoods_model().getGoods_specifications();

        if (specifications.size() == 0) {
            llcheckformate.setVisibility(View.GONE);

        } else {

            llcheckformate.setVisibility(View.VISIBLE);

        }



        llcheckformate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow(format);
            }
            private void showPopwindow(final ArrayList<FormatBean> format) {
                final PopupWindow popupWindow = new PopupWindow(mContext);
                final View pop = View.inflate(mContext, R.layout.format_popwindow, null);
                RecyclerView rvformat = (RecyclerView) pop.findViewById(R.id.rv_format);
                rvformat.setLayoutManager(new LinearLayoutManager(mContext));
                FormartBeanAdapter adapter = new FormartBeanAdapter(R.layout.item_location_popr, format);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        item.setGoods_Spcification_UID(format.get(position).getPS_UniqueID());
                        edgoodmoney.setText(format.get(position).getPS_FixedPrice());
                        popupWindow.dismiss();

                    }
                });
                rvformat.setAdapter(adapter);

                popupWindow.setContentView(pop);
                //int width = dp2px(LinearLayout.LayoutParams.WRAP_CONTENT);
                // int height = dp2px(LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(llcheckformate.getWidth());
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);

                popupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.home_toppop_bg));
                popupWindow.showAsDropDown(llcheckformate);
            }
        });

        helper.addOnClickListener(R.id.bt_up)
                .addOnClickListener(R.id.bt_down);


        btup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String count = tvcount.getText().toString();
                Integer integer = Integer.valueOf(count) + 1;

                int kycun = item.getKycun();

                if (integer > kycun) {

                    integer = kycun;
                    tvcarcount.setText("x" + integer);
                    tvcount.setText(integer + "");
                    item.setCarCount(integer);
                    MyToast.show("数量不能超过库存", R.mipmap.com_icon_cross_w);

                } else {
                    tvcarcount.setText("x" + integer);
                    tvcount.setText(integer + "");
                    item.setCarCount(integer);

                }
                mListener.edit(null);

            }
        });

        btdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String count = tvcount.getText().toString();

                Integer integer = Integer.valueOf(count) - 1;
                if (integer < 1) {
                    integer = 1;

                    tvcarcount.setText("x" + integer);
                    tvcount.setText(integer + "");
                    item.setCarCount(integer);

                } else {
                    tvcarcount.setText("x" + integer);
                    tvcount.setText(integer + "");
                    item.setCarCount(integer);
                }
                mListener.edit(null);



            }
        });





        llfinishedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //编辑栏要隐藏
                    llfinish.setVisibility(View.GONE);
                    lledit.setVisibility(View.GONE);
                String s1 = edgoodmoney.getText().toString();
                if (s1.length() == 0) {
                    s1 = "0";
                }
                item.setEditMoney(s1);
                tvgooprice.setText(s1);
                //改价格的时候有两种情况  一种是没有规格的价格 一种是有规格的价格
                item.getGoods_model().setG_FixedPrice(s1);
                String uid1 = item.getGoods_Spcification_UID();

                List<FormatBean> specifications1 = item.getGoods_model().getGoods_specifications();
                for (FormatBean bean : specifications1) {
                    if (bean.getPS_UniqueID().equals(uid1)) {
                        bean.setPS_FixedPrice(s1);
                    }
                }

                notifyDataSetChanged();
                mListener.edit(null);
                    llnormal.setVisibility(View.VISIBLE);
                    ivedit.setVisibility(View.VISIBLE);

            }
        });

        ivedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                llfinish.setVisibility(View.VISIBLE);
                lledit.setVisibility(View.VISIBLE);
                llnormal.setVisibility(View.GONE);



                ivedit.setVisibility(View.GONE);

            }
        });


    }






}
