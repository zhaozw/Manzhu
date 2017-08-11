package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.ChangeBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class ChangeAdapter extends BaseQuickAdapter<ChangeBean,BaseViewHolder> {
    public ChangeAdapter(@LayoutRes int layoutResId, @Nullable List<ChangeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChangeBean item) {
        ImageView cover = helper.getView(R.id.iv_shopcover);

        Glide.with(mContext).load(item.getCover()).into(cover);

        helper.setText(R.id.tv_type, item.getType())
        .setText(R.id.tv_changename,item.getCharacter() + " - 《" + item.getAnime()+ "》")
        .setText(R.id.tv_introduction,item.getPresent());

        final TextView tvintroduc = helper.getView(R.id.tv_introduction);


        helper.addOnClickListener(R.id.iv_check);
            ImageView check = helper.getView(R.id.iv_check);

        if (item.ischeck()) {

            check.setImageResource(R.mipmap.com_icon_multcheck_bl);
        } else {
            check.setImageResource(R.mipmap.com_icon_multcheck_ept);
        }

        tvintroduc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvintroduc.setSingleLine(false);




            }
        });





    }
}
