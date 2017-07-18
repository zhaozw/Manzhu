package com.hyphenate.easeui.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.easeui.Apiinterface;
import com.hyphenate.easeui.AvatorBean;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.EaseUI.EaseUserProfileProvider;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.domain.EaseUser;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class EaseUserUtils {
    
    static EaseUserProfileProvider userProvider;
    static Gson gson;
    public static String loginuser;

    static {
        userProvider = EaseUI.getInstance().getUserProfileProvider();
        gson = new Gson();
    }

    private static String retmsg;
    private static String useravatar;


    public static String getLoginuser() {
        return loginuser;
    }

    public static void setLoginuser(String loginuser) {
        EaseUserUtils.loginuser = loginuser;
    }

    /**
     * get EaseUser according username
     * @param username
     * @return
     */
    public static EaseUser getUserInfo(String username){

        EaseUser user = new EaseUser(username);

        new Thread(new Runnable() {
            @Override
            public void run() {




            }
        });

        return user;

    }
    
    /**
     * set user avatar
     * @param username
     */
    public static void setUserAvatar(final Context context, String username, final ImageView imageView){
        Log.i("zc", "setUserAvatar:    userName"  + username);
    	EaseUser user = getUserInfo(username);
        if(user != null ){
         Log.i("zc", "setUserAvatar:     成功" + user.getAvatar());
            Log.i("juti", "setUserAvatar:    看看具体的数据" + user.getUsername() + user.getAvatar());

            String usericon = SPUtils.getInstance().getString("usericon");
            Log.i("juti", "setUserAvatar:    看看usericon" + usericon);
            if (user.getUsername().equals(loginuser)) {
                String userAcatar = user.getAvatar();
                Log.i("juti", "setUserAvatar:     try" + user.getUsername() +  "登陆人的头像地址" + userAcatar);

                RequestOptions options = new RequestOptions().centerCrop();

                Glide.with(context).asBitmap().load("http://www.anipiggy.com"+usericon).apply(options).into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });


            } else {
                Log.i("juti", "setUserAvatar:     try" + user.getUsername() + "聊天对象的头像地址" + user.getAvatar());

                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.anipiggy.com/AppWebService.asmx/").addConverterFactory(SimpleXmlConverterFactory.create()).build();

                Apiinterface apiinterface = retrofit.create(Apiinterface.class);

                Call<String> getavator = apiinterface.getavator(username);
                getavator.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i("zc", "onResponse:   -----------网络请求结果" + response.body().toString());
                        Type type = new TypeToken<ArrayList<AvatorBean>>() {
                        }.getType();

                        ArrayList<AvatorBean> been = gson.fromJson(response.body().toString(), type);

                        AvatorBean bean = been.get(0);
                        retmsg = bean.getRetmsg();

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                if (retmsg != null) {

                    RequestOptions options = new RequestOptions().centerCrop();

                    Glide.with(context).asBitmap().load(retmsg).apply(options).into(new BitmapImageViewTarget(imageView) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            RoundedBitmapDrawable circularBitmapDrawable =
                                    RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                            circularBitmapDrawable.setCircular(true);
                            imageView.setImageDrawable(circularBitmapDrawable);
                        }
                    });


                }

            }




        }else{
            Log.i("zc", "setUserAvatar:     完全失败"  );

            RequestOptions options = new RequestOptions().centerCrop();
            Glide.with(context).asBitmap().load(R.drawable.default_avator).apply(options).into(new BitmapImageViewTarget(imageView) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    imageView.setImageDrawable(circularBitmapDrawable);
                }
            });


        }
    }
    
    /**
     * set user's nickname
     */
    public static void setUserNick(String username,TextView textView){
        if(textView != null){
        	EaseUser user = getUserInfo(username);
        	if(user != null && user.getNick() != null){
        		textView.setText(user.getNick());
        	}else{
        		textView.setText(username);
        	}
        }
    }
    
}
