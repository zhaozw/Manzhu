package com.zpfan.manzhu.adapter;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.CosBean;
import com.zpfan.manzhu.utils.Utils;

import java.util.List;

/**
 * Created by Administrator on 2017/7/22 0022.
 */

public class CosAdapter extends BaseQuickAdapter<CosBean,BaseViewHolder> {
    public CosAdapter(@LayoutRes int layoutResId, @Nullable List<CosBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CosBean item) {
        ImageView ivcos = helper.getView(R.id.iv_cos);
        final ImageView ivavator = helper.getView(R.id.iv_avator);
         ImageView ivmanor = helper.getView(R.id.iv_manor);

        Glide.with(mContext).load(item.getCW_Cover()).into(ivcos);
        ViewGroup.LayoutParams params2 = ivcos.getLayoutParams();
        params2.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        ivcos.setLayoutParams(params2);

        RequestOptions options = new RequestOptions().centerCrop();

        Glide.with(mContext).asBitmap().load(item.getMaster_avator()).apply(options).into(new BitmapImageViewTarget(ivavator) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                ivavator.setImageDrawable(circularBitmapDrawable);
            }
        });

        helper.setText(R.id.tv_name, item.getMaster_cn()).setText(R.id.tv_likecount,item.getCW_LikeCount() + "")
        .setText(R.id.tv_commentcount,item.getCos_review_count() + "").setText(R.id.tv_goodscount,item.getCos_goods_count() + "")
        .setText(R.id.tv_cosrole,item.getCos_role_arry())
        ;
        String sex = item.getMaster_sex();
        if (sex.equals("男")) {

            ivmanor.setImageResource(R.mipmap.com_icon_male);
        } else {
            ivmanor.setImageResource(R.mipmap.com_icon_female);
        }

        LinearLayout lltag = helper.getView(R.id.ll_tag);
        String tags = item.getCW_Tags();
        if (tags != null) {
            String[] split = tags.split(",");
            for (String s : split) {
                final TextView inflate = (TextView) mLayoutInflater.inflate(R.layout.change_tv, null);
                inflate.setText(s);
                final int i = Utils.dp2px(20);
                // TODO: 2017/7/20 0020   动态修改宽高
                inflate.setHeight(i);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(Utils.dp2px(0),0,Utils.dp2px(10),Utils.dp2px(10));

                inflate.setLayoutParams(params);
                inflate.setTextSize(9);
                lltag.addView(inflate);
                ViewGroup.LayoutParams params1 = lltag.getLayoutParams();
                params1.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                lltag.setLayoutParams(params1);

            }
        }
    }
}
