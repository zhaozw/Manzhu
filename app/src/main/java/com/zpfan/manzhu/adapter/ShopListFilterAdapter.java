package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.ShopListFilterBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public class ShopListFilterAdapter extends BaseQuickAdapter<ShopListFilterBean,BaseViewHolder> {
    public ShopListFilterAdapter(@LayoutRes int layoutResId, @Nullable List<ShopListFilterBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopListFilterBean item) {
        helper.setText(R.id.tv_sheng, item.getFiltername());
        ImageView checked = helper.getView(R.id.iv_sheng_checked);
        if (item.ischeck()) {
            checked.setVisibility(View.VISIBLE);
        }


    }
}
