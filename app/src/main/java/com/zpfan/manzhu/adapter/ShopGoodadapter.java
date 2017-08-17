package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.zpfan.manzhu.utils.DeleteListener;
import com.zpfan.manzhu.utils.GoodChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class ShopGoodadapter extends BaseQuickAdapter<ShopCartbean.CarshoplistBean.CargoodslistBean,BaseViewHolder>  {

    private final List<ShopCartbean.CarshoplistBean.CargoodslistBean> data;

    BaseViewHolder mhelper;
    private boolean isedit = false;
    ArrayList<Integer> checedid = new ArrayList<>();
    GoodChangeListener mListener ;
    DeleteListener mDeleteListener;
    private boolean ischang = true;
    private  int max ;
    private  int mCount;






    public ShopGoodadapter(@LayoutRes int layoutResId, @Nullable List<ShopCartbean.CarshoplistBean.CargoodslistBean> data, GoodChangeListener listener, DeleteListener deleteListener) {
        super(layoutResId, data);
        mListener = listener;
        this.data = data;
        mDeleteListener = deleteListener;

    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShopCartbean.CarshoplistBean.CargoodslistBean item) {
        mhelper = helper;

         final ArrayList<String> format = new ArrayList<>();
         final ArrayList<String> formatid = new ArrayList<>();
        final ShopCartbean.CarshoplistBean.CargoodslistBean.GoodsModelBean model = item.getGoods_model();
        helper.setText(R.id.tv_goodname, model.getG_Title())
        .setText(R.id.tv_carcount,"x "+item.getCarCount());
        ImageView  mIvcheck = helper.getView(R.id.iv_check);
        TextView tvgoodname = helper.getView(R.id.tv_goodname);
        TextView tvformat = helper.getView(R.id.tv_format);
        LinearLayout llprice = helper.getView(R.id.ll_price);
        LinearLayout lledit = helper.getView(R.id.ll_edit);
        LinearLayout lldelete = helper.getView(R.id.ll_delete);
        ImageView ivcover = helper.getView(R.id.iv_shopcover);
        RelativeLayout btup = helper.getView(R.id.bt_up);
        RelativeLayout btdown = helper.getView(R.id.bt_down);
        final TextView tvcount = helper.getView(R.id.tv_count);
        TextView tvcheckformate = helper.getView(R.id.tv_checkformate);
        final TextView carcount = helper.getView(R.id.tv_carcount);
        final LinearLayout llformat = helper.getView(R.id.ll_checkformate);
            List<FormatBean> specifications = model.getGoods_specifications();
        if (specifications.size() == 0) {
            llformat.setVisibility(View.GONE);
        } else {
            llformat.setVisibility(View.VISIBLE);
        }

        //编辑数量
        tvcount.setText(item.getCarCount() + "");
        mCount = item.getCarCount();

        if (model.getG_Type().equals("新商品")) {
            if (model.isG_IsDepositDeal()) {
                //是定金的商品
                helper.setText(R.id.tv_other, "（含定金：" + model.getG_DepositPrice() + "）");
            }

        } else if (model.getG_Type().equals("服务")){
            helper.setText(R.id.tv_other, " / " +model.getServer_unit_string());
        }




        //初始化规格的显示  先查看该商品是否有规格  如果有规格的话就显示选中的规格  如果没有规格 就选中默认的规格
        List<FormatBean> specifications1 = model.getGoods_specifications();
            final String uid = item.getGoods_Spcification_UID();
        if (specifications1 != null && specifications1.size() > 0) {
            if (!uid.equals("")) {
                //有选中的规格
                for (FormatBean specification : specifications) {
                    format.add(specification.getPS_AttributeValues());
                    formatid.add(specification.getPS_UniqueID());

                    if (specification.getPS_UniqueID().equals(uid)) {
                        helper.setText(R.id.tv_format, specification.getPS_AttributeValues().replace(",","，"));
                        helper.setText(R.id.tv_goodprice, specification.getPS_FixedPrice());
                        helper.setText(R.id.tv_checkformate, specification.getPS_AttributeValues());
                        max = specification.getPS_Inventory();

                    }


                }

            } else {
                //没有选中的规格  选中默认的规格
                for (FormatBean specification : specifications) {
                    if (specification.isPS_IsDefaultSelected()) {
                        helper.setText(R.id.tv_format, specification.getPS_AttributeValues().replace(",","，"));
                        helper.setText(R.id.tv_goodprice, specification.getPS_FixedPrice());
                        helper.setText(R.id.tv_checkformate, specification.getPS_AttributeValues());
                        max = specification.getPS_Inventory();
                        item.setGoods_Spcification_UID(specification.getPS_UniqueID());
                    }
                }


            }

        } else {
            format.clear();
            formatid.clear();
            helper.setText(R.id.tv_goodprice, model.getG_FixedPrice());
        }




        btup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数量增加  但是最大不能超过max
                if (uid.equals("")) {
                    max = model.getG_StockNum();
                }

                String s = tvcount.getText().toString();
                Integer integer = Integer.valueOf(s);
                int i = integer + 1;
                if (i > max) {
                    i = max;

                    tvcount.setText(i + "");
                    item.setCarCount(i);
                    MyToast.show("已经是最大库存了",R.mipmap.com_icon_cross_w);
                } else {
                    tvcount.setText(i + "");
                    item.setCarCount(i);
                }
                mCount = i;

            }
        });

        btdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = tvcount.getText().toString();
                Integer integer = Integer.valueOf(s);
                int i = integer - 1;
                if (i <= 1) {
                    i = 1;
                    tvcount.setText(i + "");
                   item.setCarCount(i);
                } else {
                    tvcount.setText(i + "");
                    item.setCarCount(i);
                }
                mCount = i;

            }
        });

        //弹出规格的列表
        llformat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopwindow(format,formatid);

            }

            private void showPopwindow(final ArrayList<String> format, final ArrayList<String> formatid) {
                final PopupWindow popupWindow = new PopupWindow(mContext);
                final View pop = View.inflate(mContext, R.layout.format_popwindow, null);
                RecyclerView rvformat = (RecyclerView) pop.findViewById(R.id.rv_format);
                rvformat.setLayoutManager(new LinearLayoutManager(mContext));
                FormartAdapter adapter = new FormartAdapter(R.layout.item_location_popr, format);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                        item.setGoods_Spcification_UID(formatid.get(position));
                        item.setCarCount(1);
                        tvcount.setText("1");
                        notifyDataSetChanged();
                        popupWindow.dismiss();
                    }
                });
                rvformat.setAdapter(adapter);

                popupWindow.setContentView(pop);
                //int width = dp2px(LinearLayout.LayoutParams.WRAP_CONTENT);
                // int height = dp2px(LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setWidth(llformat.getWidth());
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);

                popupWindow.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.home_toppop_bg));
                popupWindow.showAsDropDown(llformat);
            }
        });








        final int position = helper.getPosition();


        helper.addOnClickListener(R.id.iv_check)
                .addOnClickListener(R.id.ll_delete)
                ;

        if (data.get(position).isChecked()) {
            mIvcheck.setImageResource(R.mipmap.com_icon_multcheck_bl);
        } else {
            mIvcheck.setImageResource(R.mipmap.com_icon_multcheck_ept);
        }

        Glide.with(mContext).load(model.getG_Cover()).into(ivcover);




        if (isedit) {
            //编辑的状态
            mIvcheck.setVisibility(View.GONE);
            tvgoodname.setVisibility(View.GONE);
            tvformat.setVisibility(View.GONE);
            llprice.setVisibility(View.GONE);

            lledit.setVisibility(View.VISIBLE);
            lldelete.setVisibility(View.VISIBLE);



        } else {
            //完成编辑的状态
            mIvcheck.setVisibility(View.VISIBLE);
            tvgoodname.setVisibility(View.VISIBLE);
            tvformat.setVisibility(View.VISIBLE);
            llprice.setVisibility(View.VISIBLE);

            lledit.setVisibility(View.GONE);
            lldelete.setVisibility(View.GONE);
        }


        if (!ischang) {
            //发送请求去修改购物车的数据
            mListener.goodchange(item.getSC_UID(),item.getGoods_UID(),item.getGoods_Spcification_UID(),item.getBussiness_UID(), item.getCarCount() + "  ");
        }



    }
    
    
    protected void  editgood(boolean b){
        isedit = b;
        ischang = b;

       this.notifyDataSetChanged();
    }


}
