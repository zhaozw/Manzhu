package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.identityBean;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class UserSpacePopAdapter extends BaseQuickAdapter<identityBean,BaseViewHolder> {
    public UserSpacePopAdapter(@LayoutRes int layoutResId, @Nullable List<identityBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, identityBean item) {
        helper.setText(R.id.tv_shenfen, item.getIdent());

        ImageView ivstar1 = helper.getView(R.id.iv_start1);
        ImageView ivstar2 = helper.getView(R.id.iv_start2);
        ImageView ivstar3 = helper.getView(R.id.iv_start3);
        ImageView ivstar4 = helper.getView(R.id.iv_start4);
        ImageView ivstar5 = helper.getView(R.id.iv_start5);



        String sudu = item.getSudu();
        if (sudu.equals("0")) {
            sudu = "0.0";

        }

        helper.setText(R.id.tv_sudu, sudu);

        String taidu = item.getTaidu();
        if (taidu.equals("0")) {
            taidu = "0.0";

        }

        helper.setText(R.id.tv_taidu, taidu);
        //设置评级
        String value =  item.getZhunye();
        if (value.equals("0")) {
            value = "0.0";
        }
            helper.setText(R.id.tv_zhuanye, value);

        if (value.equals("0.5")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_half);
            ivstar2.setImageResource(R.mipmap.com_icon_star_off);
            ivstar3.setImageResource(R.mipmap.com_icon_star_off);
            ivstar4.setImageResource(R.mipmap.com_icon_star_off);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);
        } else if (value.equals("1.0")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_off);
            ivstar3.setImageResource(R.mipmap.com_icon_star_off);
            ivstar4.setImageResource(R.mipmap.com_icon_star_off);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);
        } else if (value.equals("0.0")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_off);
            ivstar2.setImageResource(R.mipmap.com_icon_star_off);
            ivstar3.setImageResource(R.mipmap.com_icon_star_off);
            ivstar4.setImageResource(R.mipmap.com_icon_star_off);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);
        } else if (value.equals("1.5")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_half);
            ivstar3.setImageResource(R.mipmap.com_icon_star_off);
            ivstar4.setImageResource(R.mipmap.com_icon_star_off);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);
        } else if (value.equals("2")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_on);
            ivstar3.setImageResource(R.mipmap.com_icon_star_off);
            ivstar4.setImageResource(R.mipmap.com_icon_star_off);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);
        } else if (value.equals("2.5")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_on);
            ivstar3.setImageResource(R.mipmap.com_icon_star_half);
            ivstar4.setImageResource(R.mipmap.com_icon_star_off);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);
        } else if (value.equals("3.0")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_on);
            ivstar3.setImageResource(R.mipmap.com_icon_star_on);
            ivstar4.setImageResource(R.mipmap.com_icon_star_off);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);
        } else if (value.equals("3.5")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_on);
            ivstar3.setImageResource(R.mipmap.com_icon_star_on);
            ivstar4.setImageResource(R.mipmap.com_icon_star_half);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);
        } else if (value.equals("4.0")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_on);
            ivstar3.setImageResource(R.mipmap.com_icon_star_on);
            ivstar4.setImageResource(R.mipmap.com_icon_star_on);
            ivstar5.setImageResource(R.mipmap.com_icon_star_off);
        } else if (value.equals("4.5")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_on);
            ivstar3.setImageResource(R.mipmap.com_icon_star_on);
            ivstar4.setImageResource(R.mipmap.com_icon_star_on);
            ivstar5.setImageResource(R.mipmap.com_icon_star_half);
        } else if (value.equals("5.0")) {
            ivstar1.setImageResource(R.mipmap.com_icon_star_on);
            ivstar2.setImageResource(R.mipmap.com_icon_star_on);
            ivstar3.setImageResource(R.mipmap.com_icon_star_on);
            ivstar4.setImageResource(R.mipmap.com_icon_star_on);
            ivstar5.setImageResource(R.mipmap.com_icon_star_on);
        }

    }
}
