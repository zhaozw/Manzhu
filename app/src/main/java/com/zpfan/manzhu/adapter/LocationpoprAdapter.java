package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.TestBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class LocationpoprAdapter extends BaseQuickAdapter<TestBean,BaseViewHolder> {


    private final List<TestBean> data;

    public LocationpoprAdapter(@LayoutRes int layoutResId, @Nullable List<TestBean> data) {
        super(layoutResId, data);
        this.data = data;

    }

    @Override
    protected void convert(BaseViewHolder helper, TestBean item) {

        String name = item.getName();
        helper.setText(R.id.tv_sheng,name);



    }
}
