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
import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.Aplication;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.ShopCartbean;
import com.zpfan.manzhu.event.DeleteGoodEvent;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.DeleteListener;
import com.zpfan.manzhu.utils.EditListener;
import com.zpfan.manzhu.utils.GoodChangeListener;
import com.zpfan.manzhu.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class ShopCartAdapter extends BaseQuickAdapter<ShopCartbean.CarshoplistBean, BaseViewHolder> {

    EditListener mListener;
    private double price = 0.00;
    ArrayList<ShopCartbean.CarshoplistBean.CargoodslistBean> checeGood = new ArrayList<>();
    public boolean allCheck = false;
    List<ShopCartbean.CarshoplistBean> data;

    public ShopCartAdapter(@LayoutRes int layoutResId, @Nullable List<ShopCartbean.CarshoplistBean> data, EditListener listener) {
        super(layoutResId, data);
        checeGood.clear();
        mListener = listener;
        this.data = data;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final ShopCartbean.CarshoplistBean item) {


        if (item.getCargoodslist().size() > 0) {
            helper.setText(R.id.tv_shopname, item.getMember_Name())
                    .setText(R.id.tv_userlv, "Lv." + item.getMember_Level());

            RecyclerView rv_shop = helper.getView(R.id.rv_shopcartitem);
            final ImageView ivcheckall = helper.getView(R.id.iv_checkall);


            rv_shop.setLayoutManager(new LinearLayoutManager(mContext));
            final List<ShopCartbean.CarshoplistBean.CargoodslistBean> cargoodslist = item.getCargoodslist();
            //发送请求 我能拿到的数据有 uid
            GoodChangeListener listener = new GoodChangeListener() {
                @Override
                public void goodchange(String scuid, String gooduid, String goods_ps_uid, String bussiness_uid, String car_count) {
                    String uid = item.getMember_UID();
                    Call<String> operaaddupdateshopcart = Aplication.mIinterface.operaaddupdateshopcart(scuid, uid, gooduid, goods_ps_uid, bussiness_uid, car_count);

                    operaaddupdateshopcart.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String body = response.body();
                            if (body != null) {
                                Type type = new TypeToken<ArrayList<AvatorBean>>() {
                                }.getType();

                                ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                                AvatorBean bean = avatorBeen.get(0);
                                if (bean != null) {

                                    String retmsg = bean.getRetmsg();
                                    if (retmsg.equals("true")) {

                                    } else {
                                        MyToast.show("购物车变更失败", R.mipmap.com_icon_cross_w);
                                    }


                                }


                            }


                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });


                }
            };
            //删除方法的回调监听
            DeleteListener deleteListener = new DeleteListener() {
                @Override
                public void delete(String scuid) {
                    deleteGood(scuid, cargoodslist);
                }
            };
            final ShopGoodadapter goodadapter = new ShopGoodadapter(R.layout.item_shopgoods, cargoodslist, listener, deleteListener);
            rv_shop.setAdapter(goodadapter);

            goodadapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                    switch (view.getId()) {
                        case R.id.iv_check:
                            boolean checked = cargoodslist.get(position).isChecked();
                            cargoodslist.get(position).setChecked(!checked);
                            if (!checked) {
                                checeGood.add(cargoodslist.get(position));
                            } else {
                                checeGood.remove(cargoodslist.get(position));
                            }
                            goodadapter.notifyDataSetChanged();
                            mListener.edit(checeGood);
                            break;
                        case R.id.ll_delete:
                            ShopCartbean.CarshoplistBean.CargoodslistBean bean = cargoodslist.get(position);

                            Call<String> operadeleteshop = Aplication.mIinterface.operadeleteshop(bean.getSC_UID());
                            operadeleteshop.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {


                                    String body = response.body();

                                    if (body != null) {
                                        Type type = new TypeToken<ArrayList<AvatorBean>>() {
                                        }.getType();

                                        ArrayList<AvatorBean> been  = Utils.gson.fromJson(body, type);

                                        AvatorBean bean = been.get(0);
                                        if (bean != null) {
                                            String retmsg = bean.getRetmsg();
                                            if (retmsg.equals("true")) {
                                                cargoodslist.remove(position);
                                                if (cargoodslist.size() == 0) {
                                                    remove(helper.getPosition());
                                                }
                                                goodadapter.notifyDataSetChanged();
                                                EventBus.getDefault().post(new DeleteGoodEvent(data.size() + ""));
                                            } else {
                                                MyToast.show("由于未知原因，删除失败",R.mipmap.com_icon_cross_w);

                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });





                            break;
                    }


                }
            });


            ivcheckall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAll(item, ivcheckall, goodadapter);
                }
            });


            if (allCheck) {
                checkAll(item, ivcheckall, goodadapter);
            }


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

    private void deleteGood(String scuid, List<ShopCartbean.CarshoplistBean.CargoodslistBean> cargoodslist) {
        for (ShopCartbean.CarshoplistBean.CargoodslistBean bean : cargoodslist) {
            if (bean.getSC_UID().equals(scuid)) {
                ArrayList<ShopCartbean.CarshoplistBean.CargoodslistBean> been = new ArrayList<>();
                cargoodslist.removeAll(been);
               // goodadapter.notifyDataSetChanged();

            }
        }
    }

    public void checkAll(ShopCartbean.CarshoplistBean item, ImageView ivcheckall, ShopGoodadapter goodadapter) {
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




    }
