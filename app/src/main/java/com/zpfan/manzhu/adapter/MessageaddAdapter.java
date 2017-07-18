package com.zpfan.manzhu.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMMessage;
import com.zpfan.manzhu.Aplication;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.UserBean;
import com.zpfan.manzhu.myui.EaseActivity;
import com.zpfan.manzhu.utils.SPUtils;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/6/22 0022.
 */

public class MessageaddAdapter extends BaseQuickAdapter<UserBean,BaseViewHolder> {


    private final List<UserBean> mList;

    public MessageaddAdapter(@LayoutRes int layoutResId, @Nullable List<UserBean> data) {
        super(layoutResId, data);
        mList = data;

    }

    @Override
    protected void convert(BaseViewHolder helper, final UserBean item) {
        helper.setText(R.id.tv_name, item.getM_UserName());
        helper.setText(R.id.tv_dizhi, item.getM_Province() + "-" + item.getM_City());
        helper.setText(R.id.tv_userlv, "Lv." +item.getN_AllLevel());


        ImageView view = helper.getView(R.id.iv_manor);
        if (item.getM_Sex().equals("男")) {
            view.setImageResource(R.mipmap.com_icon_male);
        } else {
            view.setImageResource(R.mipmap.com_icon_female);

        }

        ImageView shop = helper.getView(R.id.iv_shop);

        if (item.isM_IsBusiness()) {
            shop.setImageResource(R.mipmap.type_icon_shop);
        } else {
            shop.setImageResource(R.mipmap.type_icon_user);
        }

        final ImageView avator = helper.getView(R.id.iv_avator);

        RequestOptions options = new RequestOptions().centerCrop();

        Glide.with(mContext).asBitmap().load(Utils.imgUrl+ item.getM_Avatar()).apply(options).into(new BitmapImageViewTarget(avator) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                avator.setImageDrawable(circularBitmapDrawable);
            }
        });


        final LinearLayout llsend = helper.getView(R.id.ll_sendmessage);

                final TextView id = (TextView) llsend.findViewById(R.id.tv_sendmessage);
                final ImageView icon = (ImageView) llsend.findViewById(R.id.iv_sendicon);

        llsend.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        id.setTextColor(Color.WHITE);
                        icon.setImageResource(R.mipmap.com_icon_message_w);
                        break;

                    case MotionEvent.ACTION_UP:
                        id.setTextColor(mContext.getResources().getColor(R.color.secondbuttonbc));
                        icon.setImageResource(R.mipmap.com_icon_message_lb);


                        break;

                    case MotionEvent.ACTION_MOVE:

                        id.setTextColor(mContext.getResources().getColor(R.color.secondbuttonbc));
                        icon.setImageResource(R.mipmap.com_icon_message_lb);

                        break;



                }
                return false;
            }
        });


        llsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chatId = item.getM_Phone();
                Log.i(TAG, "onClick:      查看user" + chatId);
                Intent intent = new Intent(mContext, EaseActivity.class);
             /*   intent.putExtra("userphone", item.getM_Phone());

                intent.putExtra("chatType", EMMessage.ChatType.Chat);*/
                intent.putExtra("userId", chatId);
                intent.putExtra("usercn", item.getM_UserName());
                intent.putExtra("chatType", EMMessage.ChatType.Chat);

                saveChatobj(chatId);

                mContext.startActivity(intent);

            }


        });

    }

    private void saveChatobj(String chatId) {
        //保存聊天对象的接口
        String userphone = SPUtils.getInstance().getString("userphone", "");
        if (!userphone.equals("")) {
            //发送接口去保存对象
            Call<String> call = Aplication.mIinterface.saveChatbj(userphone, chatId);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String body = response.body();
                    if (body != null) {
                        Log.i("zc", "onResponse:     查看返回 response" + response.body());
                        Type type = new TypeToken<ArrayList<AvatorBean>>() {
                        }.getType();

                        ArrayList<AvatorBean> list = Utils.gson.fromJson(body, type);


                    }


                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });



        }




    }


}
