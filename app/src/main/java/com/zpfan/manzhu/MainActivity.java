package com.zpfan.manzhu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.zpfan.manzhu.bean.UserBean;
import com.zpfan.manzhu.fragment.EaseConversationListFragment;
import com.zpfan.manzhu.fragment.HomeFragment;
import com.zpfan.manzhu.fragment.HuodongFragment;
import com.zpfan.manzhu.fragment.MessageFragment;
import com.zpfan.manzhu.fragment.UserFragment;
import com.zpfan.manzhu.myui.EaseActivity;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.SPUtils;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements EaseConversationListFragment.EaseConversationListItemClickListener {


    @BindView(R.id.iv_home)
    ImageView mIvHome;
    @BindView(R.id.tv_home)
    TextView mTvHome;
    @BindView(R.id.ll_home)
    LinearLayout mLlHome;
    @BindView(R.id.iv_huodong)
    ImageView mIvHuodong;
    @BindView(R.id.tv_houdong)
    TextView mTvHoudong;
    @BindView(R.id.ll_huodong)
    LinearLayout mLlHuodong;
    @BindView(R.id.iv_message)
    ImageView mIvMessage;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;
    @BindView(R.id.iv_my)
    ImageView mIvMy;
    @BindView(R.id.tv_my)
    TextView mTvMy;
    @BindView(R.id.ll_my)
    LinearLayout mLlMy;
    @BindView(R.id.iv_plus)
    ImageView mIvPlus;
    private FragmentManager mFm;
    private FragmentTransaction mFt;
    private HomeFragment mHomeFragment;
    private HuodongFragment mHuodongFragment;
    private MessageFragment mMessageFragment;
    private UserFragment mUserFragment;
    private boolean mUserlogin;
    private EaseConversationListFragment conversationListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    private void initView() {

        mFm = getSupportFragmentManager();

       /* Typeface iconFont = FontManager.getTypeface(this, FontManager.FONTAWESOME);
        FontManager.markAsIconContainer(findViewById(R.id.tv_plus), iconFont);*/



        if (conversationListFragment != null && mUserlogin) {
            String userinfo = SPUtils.getInstance().getString("userinfo", "");
            if (userinfo.equals("")) {
                startActivity(new Intent(this,LoginActivity.class));
            } else {
                EaseUI instance = EaseUI.getInstance();
                Type type1 = new TypeToken<ArrayList<UserBean>>() {
                }.getType();

                final ArrayList<UserBean> userbean = Utils.gson.fromJson(userinfo, type1);
                instance.setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
                    @Override
                    public EaseUser getUser(String username) {
                        EaseUser user1 = new EaseUser(username);
                        user1.setAvatar(Utils.imgUrl +userbean.get(0).getM_Avatar());

                        return user1;
                    }
                });



            }


        }
        setTabSelection(0);


    }

    @Override
    protected void onResume() {

        mUserlogin = Utils.isUserLogin();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // EMClient.getInstance().logout(true);
    }

    @OnClick({R.id.ll_home, R.id.ll_huodong, R.id.iv_plus, R.id.ll_message, R.id.ll_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                setTabSelection(0);
                break;
            case R.id.ll_huodong:
                setTabSelection(1);
                break;
            case R.id.iv_plus:

                break;

            case R.id.ll_message:

                if (mUserlogin) {
                    setTabSelection(2);

                } else {
                     startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }


                break;
            case R.id.ll_my:
               // setTabSelection(3);
                //先进行登陆的操作
                if (mUserlogin) {
                    MyToast.show("该模块还在开发中", R.mipmap.com_icon_cross_w);
                } else {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }

                break;
        }
    }


    private void setTabSelection(int i) {
        mFt = mFm.beginTransaction();
        //清除选中状态
        clearnres();
        // 清楚掉fragment
        clearnfrgment();
        //  对不通的fragment 进行切换
        switch (i) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    mFt.add(R.id.fl_content, mHomeFragment);
                } else {
                    mFt.show(mHomeFragment);
                }

                mIvHome.setImageResource(R.mipmap.com_icon_home);
                mTvHome.setTextColor(Color.BLACK);

                break;

            case 1:
                if (mHuodongFragment == null) {
                    mHuodongFragment = new HuodongFragment();
                    mFt.add(R.id.fl_content, mHuodongFragment);
                } else {
                    mFt.show(mHuodongFragment);
                }

                mIvHuodong.setImageResource(R.mipmap.com_icon_act);
                mTvHoudong.setTextColor(Color.BLACK);

                break;

            case 2:
                if (mMessageFragment == null) {
                    mMessageFragment = new MessageFragment();
                    mFt.add(R.id.fl_content, mMessageFragment);
                } else {
                    mFt.show(mMessageFragment);
                }

                    mIvMessage.setImageResource(R.mipmap.com_icon_msg);
                mTvMessage.setTextColor(Color.BLACK);

                break;

            case 3:
                if (mUserFragment == null) {
                    mUserFragment = new UserFragment();
                    mFt.add(R.id.fl_content, mUserFragment);
                } else {
                    mFt.show(mHomeFragment);
                }

                mIvMy.setImageResource(R.mipmap.com_icon_uc);
                mTvMy.setTextColor(Color.BLACK);

                break;


        }

        mFt.commit();


    }

    private void clearnfrgment() {

        if (mHomeFragment != null) {
            mFt.hide(mHomeFragment);
        }

        if (mHuodongFragment != null) {
            mFt.hide(mHuodongFragment);
        }

        /*if (conversationListFragment != null) {
            mFt.hide(conversationListFragment);
        }*/
        if (mMessageFragment != null) {
            mFt.hide(mMessageFragment);
        }

        if (mUserFragment != null) {
            mFt.hide(mUserFragment);
        }

    }

    private void clearnres() {

        mIvHome.setImageResource(R.mipmap.com_icon_home_ept);
        mTvHome.setTextColor(Color.GRAY);
        mIvHuodong.setImageResource(R.mipmap.com_icon_act_ept);
        mTvHoudong.setTextColor(Color.GRAY);
        mIvMessage.setImageResource(R.mipmap.com_icon_msg_ept);
        mTvMessage.setTextColor(Color.GRAY);
        mIvMy.setImageResource(R.mipmap.com_icon_uc_ept);
        mTvMy.setTextColor(Color.GRAY);

    }


    /**
     * 点击两次应用退出应用
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            //exit();   按两次 退出的操作
            finish();
            EMClient.getInstance().logout(true, new EMCallBack() {
                @Override
                public void onSuccess() {
                    if (mUserlogin) {
                        SPUtils.getInstance().put("userlogin",false);
                        SPUtils.getInstance().remove("userid");
                        Log.i("zc", "onSuccess:   退出登陆成功");
                        MyToast.show("退出登陆成功",R.mipmap.com_icon_check_w);
                    }
                }

                @Override
                public void onError(int code, String error) {

                }

                @Override
                public void onProgress(int progress, String status) {

                }
            });
            return true;
        }


        return super.onKeyDown(keyCode, event);
    }

    private long touchTime = 0;
    private void exit() {
        if ((System.currentTimeMillis() - touchTime) > 2000) {
        /*    MyToast toast = new MyToast(MainActivity.this, "按两次返回键后 退出猪排饭", R.mipmap.com_icon_info_w, 20);
            toast.show();*/
        MyToast.show("按两次返回键后 退出猪排饭", R.mipmap.com_icon_info_w);
            touchTime = System.currentTimeMillis();

        } else {
            finish();
            EMClient.getInstance().logout(true, new EMCallBack() {
                @Override
                public void onSuccess() {
                    Log.i("zc", "onSuccess:   退出登陆成功");
                    MyToast.show("退出登陆成功",R.mipmap.com_icon_check_w);
                }

                @Override
                public void onError(int code, String error) {

                }

                @Override
                public void onProgress(int progress, String status) {

                }
            });
        }


    }

    @Override
    public void onListItemClicked(EMConversation conversation) {
       // String userphone = SPUtils.getInstance().getString("userphone", null);
        Intent intent = new Intent(MainActivity.this, EaseActivity.class);
        intent.putExtra(EaseConstant.EXTRA_USER_ID, conversation.getLastMessage().getUserName());
      /*  if (userphone != null) {
            intent.putExtra("myphone", userphone);
        }*/
        startActivity(intent);
    }


}
