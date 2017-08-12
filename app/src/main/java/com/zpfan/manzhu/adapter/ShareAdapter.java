package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.ShareBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/12 0012.
 */

public class ShareAdapter extends BaseQuickAdapter <ShareBean,BaseViewHolder> {
    public ShareAdapter(@LayoutRes int layoutResId, @Nullable List<ShareBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShareBean item) {
        ImageView ivshare = helper.getView(R.id.iv_share);
        ivshare.setImageResource(item.getImgs());

        helper.setText(R.id.tv_share, item.getPlatname());



    }
}
