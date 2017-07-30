package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.AddressBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/30 0030.
 */

public class OrderLocationAdapter extends BaseQuickAdapter<AddressBean,BaseViewHolder> {
    public OrderLocationAdapter(@LayoutRes int layoutResId, @Nullable List<AddressBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean item) {

        helper.setText(R.id.tv_name, item.getMD_Name())
        .setText(R.id.tv_phone,item.getMD_Phone())
        .setText(R.id.tv_location,item.getMD_Province() + item.getMD_City() + item.getMD_Area() + item.getMD_Address());
        ImageView check = helper.getView(R.id.iv_check);
        if (item.ischeck()) {
            check.setVisibility(View.VISIBLE);
        } else {
            check.setVisibility(View.INVISIBLE);
        }


    }
}
