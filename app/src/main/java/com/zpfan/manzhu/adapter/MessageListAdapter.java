package com.zpfan.manzhu.adapter;

import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.zpfan.manzhu.Aplication;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.LastMessageBean;
import com.zpfan.manzhu.bean.UserBean;
import com.zpfan.manzhu.utils.LastMessage;
import com.zpfan.manzhu.utils.SPUtils;
import com.zpfan.manzhu.utils.TimeStringUtil;
import com.zpfan.manzhu.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Administrator on 2017/6/26 0026.
 */

public class MessageListAdapter extends BaseQuickAdapter<UserBean, BaseViewHolder> {
    private final ArrayList<UserBean> data;
    private LastMessage mMessage;
    private boolean ishaveUser;
    private Object mUserInfo;


    public MessageListAdapter(@LayoutRes int layoutResId, @Nullable ArrayList<UserBean> data) {
        super(layoutResId, data);
        EventBus.getDefault().register(this);
        this.data = data;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final UserBean item) {
        //去查询最后一条消息。。。

        helper.setText(R.id.tv_name, item.getM_UserName());
        helper.setText(R.id.tv_useralllv, "Lv." + item.getN_AllLevel());
        ImageView shop = helper.getView(R.id.iv_shop);


        if (item.isM_IsBusiness()) {
            shop.setImageResource(R.mipmap.type_icon_shop);
        } else {
            shop.setImageResource(R.mipmap.type_icon_user);
        }

        final ImageView avator = helper.getView(R.id.iv_avator);

        RequestOptions options = new RequestOptions().centerCrop();

        Glide.with(mContext).asBitmap().load(Utils.imgUrl + item.getM_Avatar()).apply(options).into(new BitmapImageViewTarget(avator) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                avator.setImageDrawable(circularBitmapDrawable);
            }
        });


        //未读消息数 设置
        int count = item.getUnredCount();
        TextView unred = helper.getView(R.id.tv_unred);
        if (count != 0) {
            helper.setText(R.id.tv_unred, count + "");
            unred.setVisibility(View.VISIBLE);

        } else {
            unred.setVisibility(View.INVISIBLE);
        }




        //设置消息发送的时间
        if (item.getLasttime() == 0) {
            helper.setText(R.id.tv_lasttime, item.getLastStringtime());
        } else {
            String tiem = TimeStringUtil.getFormatDateString(item.getLasttime() / 1000);
            helper.setText(R.id.tv_lasttime, tiem);
        }

        //设置最后一条消息的内容
        helper.setText(R.id.tv_lastmessage, item.getLastMessage());




    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getcount(EMMessage count) {


        /*
        mMessage = count;
        notifyDataSetChanged();
        EventBus.getDefault().post("收到了消息");*/

        for (UserBean bean : data) {


            int i = data.indexOf(bean);
            if (bean.getM_Phone().equals(count.getFrom())) {
                ishaveUser = true;
                data.remove(bean);

                String lastMessagebody = count.getBody().toString();
                if (lastMessagebody.contains("txt")) {
                    String substring = lastMessagebody.substring(lastMessagebody.indexOf("\""));
                    String replace = substring.replace("\"", "");
                    String lastMessage = replace.trim();
                    bean.setLastMessage(lastMessage);
                } else if (lastMessagebody.contains("im")) {
                    bean.setLastMessage("[图片]");
                } else if (lastMessagebody.contains("")) {
                    bean.setLastMessage("[语音]");
                }

                // Log.i("zc", "getcount:    收到新消息在第几位" + i);
                //发送消息到外面去移动条目


                bean.setLasttime(count.getMsgTime());



                EMConversation conversation = EMClient.getInstance().chatManager().getConversation(bean.getM_Phone());
                int tiem = conversation.getUnreadMsgCount();
                // Log.i("zc", "getcount:     看看 未读消息的数量" + tiem);
                bean.setUnredCount(tiem);
                data.add(0, bean);
                notifyDataSetChanged();
                //  notifyItemMoved(i,0);
            }

        }
        if (!ishaveUser) {

            addUserbean(count);
        }


    }

    private void addUserbean(final EMMessage emMessage) {

        //发送网络请求
        String from = emMessage.getFrom();
        //根据手机号去获取到用户信息 并刷新
        Call<String> seachuser = Aplication.mIinterface.seachuser("1",from);
        seachuser.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> been = Utils.gson.fromJson(body, type);
                    AvatorBean bean = been.get(0);
                    if (bean != null) {
                        String retmsg = bean.getRetmsg();
                        if (retmsg.contains("[")) {
                            String json = retmsg.substring(1, retmsg.lastIndexOf("]"));
                            Type type1 = new TypeToken<ArrayList<UserBean>>() {
                            }.getType();

                            ArrayList<UserBean> list = Utils.gson.fromJson(json, type1);
                            UserBean bean1 = list.get(0);

                            String lastMessagebody = emMessage.getBody().toString();
                            if (lastMessagebody.contains("txt")) {
                                String substring = lastMessagebody.substring(lastMessagebody.indexOf("\""));
                                String replace = substring.replace("\"", "");
                                String lastMessage = replace.trim();
                                bean1.setLastMessage(lastMessage);
                            } else if (lastMessagebody.contains("im")) {
                                bean1.setLastMessage("[图片]");
                            } else if (lastMessagebody.contains("")) {
                                bean1.setLastMessage("[语音]");
                            }

                            // Log.i("zc", "getcount:    收到新消息在第几位" + i);
                            //发送消息到外面去移动条目


                            bean1.setLasttime(emMessage.getMsgTime());
                            EMConversation conversation = EMClient.getInstance().chatManager().getConversation(bean1.getM_Phone());
                            int tiem = conversation.getUnreadMsgCount();
                            // Log.i("zc", "getcount:     看看 未读消息的数量" + tiem);
                            bean1.setUnredCount(tiem);

                            data.add(0,bean1);
                            notifyDataSetChanged();


                        }



                    }


                }







            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });








    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getlastMessage(String s) {
        String userphone = SPUtils.getInstance().getString("userphone", "");

        if (!userphone.equals("")) {
            for (final UserBean databean : data) {
                //要发送网络请求获取到每一个的最后一个消息

                Call<String> getlastMessage = Aplication.mIinterface.getlastMessage(userphone, databean.getM_Phone());

                getlastMessage.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        Type type = new TypeToken<ArrayList<AvatorBean>>() {
                        }.getType();

                        ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(response.body(), type);

                        if (avatorBeen != null) {
                            AvatorBean bean = avatorBeen.get(0);
                            if (bean != null) {
                                String retmsg = bean.getRetmsg();
                                Type type1 = new TypeToken<ArrayList<LastMessageBean>>() {
                                }.getType();

                                ArrayList<LastMessageBean> lastMessageBeen = Utils.gson.fromJson(retmsg, type1);
                                LastMessageBean bean1 = lastMessageBeen.get(0);

                                String tumlog = bean1.getCL_ChatLog();

                              //  String log = "";
                                if (tumlog != null) {
                                    if ( tumlog.contains("txt")) {
                                      String  aaa =   tumlog.substring(tumlog.indexOf("\"")).replace("\"","").trim();

                                        databean.setLastMessage(aaa);
                                    } else if (tumlog.contains("voice")) {

                                        databean.setLastMessage("[语音]");
                                    } else if (tumlog.contains("im")) {
                                        databean.setLastMessage("[图片]");
                                    } else {
                                        databean.setLastMessage(tumlog);
                                    }
                                }



                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                               // Long aLong = TimeStringUtil.getFormDataLong(bean1.getCL_ChatTime());
                                        String time1 = bean1.getCL_ChatTime();
                                if (time1 != null) {
                                    try {
                                        Date parse = format.parse(time1);
                                        long time = parse.getTime();

                                        databean.setLasttime(time);

                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                } else {

                                }


                                //设置外面的东西 变化
                                EMConversation conversation = EMClient.getInstance().chatManager().getConversation(databean.getM_Phone());
                                if (conversation != null) {
                                    int unredcount = conversation.getUnreadMsgCount();

                                    databean.setUnredCount(unredcount);
                                }
                                    notifyDataSetChanged();

                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });


            }


        }

    }

}
