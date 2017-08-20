package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.CosLocationBean;
import com.zpfan.manzhu.utils.Utils;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20 0020.
 */

public class CosLocationAdapter extends BaseQuickAdapter<CosLocationBean,BaseViewHolder> {
    public CosLocationAdapter(@LayoutRes int layoutResId, @Nullable List<CosLocationBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CosLocationBean item) {

        helper.setText(R.id.tv_locationname, item.getSL_Name());
        ImageView coslocationimg = helper.getView(R.id.iv_cosloation);
        Glide.with(mContext).load(Utils.imgUrl + item.getSL_Image()).into(coslocationimg);


    }
}
