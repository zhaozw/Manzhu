package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.FormatBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/2 0002.
 */

public class FormartBeanAdapter extends BaseQuickAdapter<FormatBean,BaseViewHolder> {
    public FormartBeanAdapter(@LayoutRes int layoutResId, @Nullable List<FormatBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FormatBean item) {
        helper.setText(R.id.tv_sheng, item.getPS_AttributeValues());
    }
}
