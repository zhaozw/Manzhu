package com.zpfan.manzhu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.LoginBean;
import com.zpfan.manzhu.bean.UserBean;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.APIinterface;
import com.zpfan.manzhu.utils.SPUtils;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.ed_username)
    EditText mEdUsername;
    @BindView(R.id.ll_username)
    LinearLayout mLlUsername;
    @BindView(R.id.lin2)
    View mLin2;
    @BindView(R.id.ed_password)
    EditText mEdPassword;
    @BindView(R.id.ll_password)
    LinearLayout mLlPassword;
    @BindView(R.id.lin1)
    View mLin1;
    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.tv_forgetpw)
    TextView mTvForgetpw;
    @BindView(R.id.tv_regist)
    TextView mTvRegist;
    @BindView(R.id.ll_zhuce)
    LinearLayout mLlZhuce;
    @BindView(R.id.iv_qq)
    ImageView mIvQq;
    @BindView(R.id.iv_wb)
    ImageView mIvWb;
    @BindView(R.id.iv_wx)
    ImageView mIvWx;
    @BindView(R.id.ll_quick)
    LinearLayout mLlQuick;
    @BindView(R.id.tv_fuwu)
    TextView mTvFuwu;
    @BindView(R.id.ll_fuwu)
    LinearLayout mLlFuwu;
    @BindView(R.id.iv_userimg)
    ImageView mIvUserimg;
    private Button mBt_login;
    private Retrofit mRetrofit;
    private APIinterface mIinterface;
    private MyToast mToast;
    private MyToast mToast1;
    private String mUserAvator;
    private String mCn;
    private String mUid;
    private String mIntegral;
    private String mAvailableMoney;
    private String mProvince;
    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        //限制username 只能输入数字
        mEdUsername.setInputType(InputType.TYPE_CLASS_PHONE);

        mToast1 = new MyToast(LoginActivity.this);

        //状态栏沉浸式
        Utils.setTranslucentStatus(LoginActivity.this);

        //当输入密码的时候，获取到用户的头像
        mEdPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                   // Toast.makeText(LoginActivity.this, "去查询头像、", Toast.LENGTH_SHORT).show();
                  //  getUserAvator();
                    //查询用户是否存在
                    checkUser();




                }
            }
        });



        mEdUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                setUserDefAvator();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }

    private void checkUser() {
        //检查用户是否存在的方法
        String phone = mEdUsername.getText().toString();
        Call<String> getphonuseable = Aplication.mIinterface.getphonuseable(phone);
        getphonuseable.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                    AvatorBean bean = avatorBeen.get(0);
                    String retmsg = bean.getRetmsg();
                    if (retmsg.equals("true")) {

                        getUserAvator();

                    } else {

                        MyToast.show("该用户不存在，请重新输入",R.mipmap.com_icon_cross_w);
                        setUserDefAvator();




                    }



                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });




    }

    private void setUserDefAvator() {
        RequestOptions override = new RequestOptions().override(200, 200);

        SimpleTarget target = new SimpleTarget<Drawable>() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {

                mIvUserimg.setBackground(resource);

            }
        };

        Glide.with(LoginActivity.this)
                .load(R.mipmap.default_avator)
                .apply(override)
                .into(target);
    }

    private void getUserAvator() {
        // 获取到用户的头像 设置到头像框里面
        String userName = mEdUsername.getText().toString();
        if (userName != null) {
            Call<String> getavator = Aplication.mIinterface.getavator(userName);
            getavator.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    //获取到头像的网址 然后加载给控件
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(response.body(), type);
                    AvatorBean bean = avatorBeen.get(0);

                    RequestOptions override = new RequestOptions().override(200, 200);

                    SimpleTarget target = new SimpleTarget<Drawable>() {

                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {

                            mIvUserimg.setBackground(resource);

                        }
                    };

                    Glide.with(LoginActivity.this)
                            .load(bean.getRetmsg())
                            .apply(override)
                            .into(target);



                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });


        }
    }


    @OnClick({R.id.ed_username, R.id.ed_password, R.id.bt_login, R.id.tv_forgetpw, R.id.tv_regist, R.id.iv_qq, R.id.iv_wb, R.id.iv_wx, R.id.tv_fuwu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_username:
                break;
            case R.id.ed_password:
                break;
            case R.id.bt_login:
                //发送网络请求，去登陆
                String userName = mEdUsername.getText().toString();
                String password = mEdPassword.getText().toString();


                final Call<String> login = Aplication.mIinterface.login(userName, password);
                login.enqueue(new Callback<String>() {

                    private String mUserPhone;

                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String body = response.body();
                        if (body != null) {
                            String json = body;

                            Type type = new TypeToken<ArrayList<LoginBean>>() {
                            }.getType();

                            ArrayList<LoginBean> loginBeens = Utils.gson.fromJson(json, type);
                            final String message = loginBeens.get(0).getRetmsg();

                            if (message.contains("[")) {
                                Type type1 = new TypeToken<ArrayList<UserBean>>() {
                                }.getType();

                                ArrayList<UserBean> userbean = Utils.gson.fromJson(message, type1);
                                final UserBean user = userbean.get(0);
                                if (user != null) {
                                    mUserAvator = user.getM_Avatar();
                                    mUserPhone = user.getM_Phone();
                                    mCn = user.getM_UserName();
                                    mUid = user.getM_UID();
                                    mId = user.getCoser_figure_data().getId();
                                    mIntegral = user.getMember_AvailableIntegral();
                                    mAvailableMoney = user.getMember_AvailableMoney();
                                    mProvince = user.getM_Province();

                                }
                                SPUtils.getInstance().put("user", message);

                                if (message.contains("M_Account_Status")) {

                                    EMClient.getInstance().login(user.getM_Phone(), "123", new EMCallBack() {
                                        @Override
                                        public void onSuccess() {



                                            EMClient.getInstance().groupManager().loadAllGroups();
                                            EMClient.getInstance().chatManager().loadAllConversations();
                                            //保存用户的信息
                                            SPUtils.getInstance().put("usercn", mCn);
                                            SPUtils.getInstance().put("userphone", mUserPhone);
                                            SPUtils.getInstance().put("usericon", mUserAvator);
                                            SPUtils.getInstance().put("userinfo", message);
                                            SPUtils.getInstance().put("userlogin", true);
                                            SPUtils.getInstance().put("useruid", mUid);
                                            SPUtils.getInstance().put("userid", mUid);
                                            SPUtils.getInstance().put("userjifen",mIntegral);
                                            SPUtils.getInstance().put("useryue",mAvailableMoney);
                                            SPUtils.getInstance().put("usesheng",mProvince);
                                            //判断用户是否是第一次登陆

                                            EaseUI instance = EaseUI.getInstance();

                                            EaseUI.EaseUserProfileProvider userProvider = new EaseUI.EaseUserProfileProvider() {
                                                @Override
                                                public EaseUser getUser(String username) {
                                                    EaseUser user1 = new EaseUser(mUserPhone);
                                                    user1.setAvatar(Utils.imgUrl + user.getM_Avatar());

                                                    return user1;
                                                }
                                            };
                                            instance.setUserProfileProvider(userProvider);


                                            EaseUserUtils.setLoginuser(mUserPhone);
                                            //


                                            // EventBus.getDefault().postSticky();


                                            finish();


                                        }

                                        @Override
                                        public void onError(int code, String error) {

                                        }

                                        @Override
                                        public void onProgress(int progress, String status) {

                                        }
                                    });

                                } else {
                                    //登陆失败 用toast 提示用户

                                      /*  mToast = new MyToast(LoginActivity.this, message, R.mipmap.com_icon_cross_w, 20);
                                    mToast.show();*/

                                    if (mToast1 != null) {
                                        mToast1.cancel();
                                        mToast1.show(message, R.mipmap.com_icon_cross_w);
                                    }


                                }
                            } else {

                              /*  //登陆失败 用toast 提示用户
                                MyToast toast = new MyToast(LoginActivity.this, message, R.mipmap.com_icon_cross_w, 20);
                                toast.show();*/
                                if (mToast1 != null) {
                                    mToast1.cancel();
                                    mToast1.show(message, R.mipmap.com_icon_cross_w);
                                }

                            }
                        } else {
                            MyToast.show("服务器正忙，请稍后再试~~",R.mipmap.com_icon_cross_w);
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();
                    }
                });


                break;
            case R.id.tv_forgetpw:
                startActivity(new Intent(this, ForgetActivity.class));
                break;
            case R.id.tv_regist:
                // 新用户注册

                startActivity(new Intent(LoginActivity.this,RegistActivity.class));

                break;
            case R.id.iv_qq:
                break;
            case R.id.iv_wb:
                break;
            case R.id.iv_wx:
                break;
            case R.id.tv_fuwu:
                break;
        }
    }


}
