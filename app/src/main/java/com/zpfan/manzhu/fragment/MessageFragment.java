package com.zpfan.manzhu.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.easeui.widget.EaseConversationList;
import com.hyphenate.util.NetUtils;
import com.zpfan.manzhu.Aplication;
import com.zpfan.manzhu.MessageAddActivity;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.adapter.MessageListAdapter;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.UserBean;
import com.zpfan.manzhu.myui.EaseActivity;
import com.zpfan.manzhu.utils.SPUtils;
import com.zpfan.manzhu.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/6/14 0014.
 */

public class MessageFragment extends Fragment implements EMConnectionListener, EMMessageListener {
    @BindView(R.id.iv_usericon)
    ImageView mIvUsericon;
    @BindView(R.id.tv_username)
    TextView mTvUsername;
    @BindView(R.id.tv_shengfen)
    TextView mTvShengfen;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;
    @BindView(R.id.rl_top)
    RelativeLayout mRlTop;
    Unbinder unbinder;
    @BindView(R.id.rv_message)
    RecyclerView mRvMessage;


    private View mView;
    private boolean mUserlogin;
    private List<UserBean> conversationList;
    private UserBean mBean;
    private MessageListAdapter mMessageListAdapter;
    private EaseConversationList mConversationListView;
    private ArrayList<UserBean> mUserlist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_message, container, false);
        }


        mUserlogin = SPUtils.getInstance().getBoolean("userlogin", false);
        conversationList = new ArrayList<>();
        unbinder = ButterKnife.bind(this, mView);

        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mUserlogin) {
            getUserData();
            initrv();

            EMClient.getInstance().chatManager().addMessageListener(this);
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // EventBus.getDefault().register(this);

    }

    private void initrv() {
        conversationList.clear();
          mRvMessage.setLayoutManager(new LinearLayoutManager(getContext()));

        final Call<String> getchatobjlist = Aplication.mIinterface.getchatobjlist(mBean.getM_Phone());


                getchatobjlist.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {


                        Type type = new TypeToken<ArrayList<AvatorBean>>() {
                        }.getType();

                        ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(response.body(), type);
                        if (avatorBeen != null && avatorBeen.size() != 0) {
                        AvatorBean bean = avatorBeen.get(0);
                            String retmsg = bean.getRetmsg();

                            if (retmsg.contains("[")) {
                                //先去掉外面的一层数组
                                String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));

                                Type type1 = new TypeToken<ArrayList<UserBean>>() {
                                }.getType();

                                mUserlist = Utils.gson.fromJson(substring, type1);


                                mMessageListAdapter = new MessageListAdapter(R.layout.message_item, mUserlist);
                                mMessageListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                                        Intent intent = new Intent(getContext(), EaseActivity.class);
                                        intent.putExtra(EaseConstant.EXTRA_USER_ID, mUserlist.get(position).getM_Phone());
                                        intent.putExtra("usercn", mUserlist.get(position).getM_UserName());
                                        intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EMMessage.ChatType.Chat);
                                        intent.putExtra("myphone", mBean.getM_Phone());

                                        startActivity(intent);

                                    }
                                });
                                mRvMessage.setAdapter(mMessageListAdapter);
                                EventBus.getDefault().post("");


                            }
                        }
                    }


                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (mUserlogin) {
            getUserData();
            //  initrv();

        }

    }




    @Override
    public void onResume() {
        super.onResume();
        if (mUserlogin) {
            getUserData();
            initrv();
            EaseUI instance = EaseUI.getInstance();

            instance.setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
                @Override
                public EaseUser getUser(String username) {
                    EaseUser user1 = new EaseUser(username);
                    user1.setAvatar(Utils.imgUrl + mBean.getM_Avatar());


                    return user1;
                }
            });

            EaseUserUtils.setLoginuser(mBean.getM_Phone());

        }

        if (mMessageListAdapter != null) {
            mMessageListAdapter.notifyDataSetChanged();
        }

    }


    private void getUserData() {
        //去拿到个人的消息 和头像地址

        String user = SPUtils.getInstance().getString("user", "");
        if (!user.equals("")) {
            Type type1 = new TypeToken<ArrayList<UserBean>>() {
            }.getType();

            ArrayList<UserBean> userbean = Utils.gson.fromJson(user, type1);
            mBean = userbean.get(0);

            mTvUsername.setText(mBean.getM_UserName());
            mTvShengfen.setText(mBean.getM_Province() + "-" + mBean.getM_City());


            RequestOptions options = new RequestOptions().centerCrop();

            Glide.with(getContext()).asBitmap().load(Utils.imgUrl + mBean.getM_Avatar()).apply(options).into(new BitmapImageViewTarget(mIvUsericon) {
                @Override
                protected void setResource(Bitmap resource) {
                    if (isAdded()) {
                        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(MessageFragment.this.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        mIvUsericon.setImageDrawable(circularBitmapDrawable);
                    }
                }
            });


        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.iv_add)
    public void onViewClicked() {
        //点击去添加的操作
        startActivity(new Intent(getContext(), MessageAddActivity.class));


    }


    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected(int errorCode) {
        //连接失败的时候

        if (errorCode == EMError.USER_REMOVED) {
            // 显示帐号已经被移除
            Log.i("zc", "onDisconnected:     帐号已经被移除");
        } else if (errorCode == EMError.USER_LOGIN_ANOTHER_DEVICE) {
            // 显示帐号在其他设备登录
            Log.i("zc", "onDisconnected:       帐号再其它设备登陆");
        } else if (NetUtils.hasNetwork(getContext())) {
            //连接不到聊天服务器
            Log.i("zc", "onDisconnected:     连接不到聊天服务器");
        } else {
            //当前网络不可用，请检查网络设置
            Log.i("zc", "onDisconnected:    网络不可用");

        }
    }


    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        //收到消息

        EMMessage message = messages.get(0);
        int size = messages.size();
        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(message.getFrom());
        int count = conversation.getUnreadMsgCount();
        // 刷新最后一条消息，然后刷新未读消息的数量
        EventBus.getDefault().post(message);
        for (UserBean bean : mUserlist) {
            if (bean.getM_Phone().equals(message.getFrom())) {
                int i = mUserlist.indexOf(bean);
            }
        }



    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {
        //收到透传消息
    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {
        //收到已读回执
    }

    @Override
    public void onMessageDelivered(List<EMMessage> messages) {
        //收到已送达回执
    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {





    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(this);
      //  EventBus.getDefault().unregister(this);
    }





}

