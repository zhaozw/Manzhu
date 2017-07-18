package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.TimeCountUtil;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    @BindView(R.id.iv_regsist)
    ImageView mIvRegsist;
    @BindView(R.id.ed_phone)
    EditText mEdPhone;
    @BindView(R.id.iv_phone)
    ImageView mIvPhone;
    @BindView(R.id.ll_phone)
    LinearLayout mLlPhone;
    @BindView(R.id.regist_lin1)
    View mRegistLin1;
    @BindView(R.id.ed_cn)
    EditText mEdCn;
    @BindView(R.id.iv_cn)
    ImageView mIvCn;
    @BindView(R.id.ll_cn)
    LinearLayout mLlCn;
    @BindView(R.id.regist_lin2)
    View mRegistLin2;
    @BindView(R.id.ed_asdsf)
    EditText mEdAsdsf;
    @BindView(R.id.ll_asdsf)
    LinearLayout mLlAsdsf;
    @BindView(R.id.regist_lin3)
    View mRegistLin3;
    @BindView(R.id.ed_password)
    EditText mEdPassword;
    @BindView(R.id.iv_password)
    ImageView mIvPassword;
    @BindView(R.id.ll_password)
    LinearLayout mLlPassword;
    @BindView(R.id.regist_lin4)
    View mRegistLin4;
    @BindView(R.id.ed_surepw)
    EditText mEdSurepw;
    @BindView(R.id.iv_surepw)
    ImageView mIvSurepw;
    @BindView(R.id.ll_surepw)
    LinearLayout mLlSurepw;
    @BindView(R.id.regist_lin5)
    View mRegistLin5;
    @BindView(R.id.ed_webcode)
    EditText mEdWebcode;
    @BindView(R.id.bt_webcode)
    Button mBtWebcode;
    @BindView(R.id.ll_webcode)
    LinearLayout mLlWebcode;
    @BindView(R.id.bt_regist)
    Button mBtRegist;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.tv_kefu)
    TextView mTvKefu;
    @BindView(R.id.ll_kefu)
    LinearLayout mLlKefu;


    private boolean isshowphone = false;
    private boolean isshwocn = false;
    private boolean isshowpw = false;
    private boolean isshowsurepw = false;
    private boolean iswebcode = false;



    private CharSequence mPhon;
    private CharSequence mCn;
    private CharSequence mPassword;
    private TimeCountUtil mTimeCountUtil;
    private String mWebcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        initView();
        setListener();

    }

    private void setListener() {
        /**
         * 对edittext失去焦点 进行判断
         */
        mEdPhone.setOnFocusChangeListener(this);
        mEdCn.setOnFocusChangeListener(this);
        mEdPassword.setOnFocusChangeListener(this);
        mEdSurepw.setOnFocusChangeListener(this);



        mEdPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mIvPhone.setVisibility(View.INVISIBLE);
                isshowphone = false;
                //checkPhone();
            }

            @Override
            public void afterTextChanged(Editable s) {
                checkPhone();

            }
        });
        mEdCn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mIvCn.setVisibility(View.INVISIBLE);
                isshwocn = false;

            }

            @Override
            public void afterTextChanged(Editable s) {



            }
        });
        mEdPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mIvPassword.setVisibility(View.INVISIBLE);
                    isshowpw = false;
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        mEdSurepw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mIvSurepw.setVisibility(View.INVISIBLE);
                isshowsurepw = false;

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });







    }

    private void initView() {
        //状态栏沉浸式
        Utils.setTranslucentStatus(RegistActivity.this);

        //限制username 只能输入数字
        mEdPhone.setInputType(InputType.TYPE_CLASS_PHONE);
        mTimeCountUtil = new TimeCountUtil(mBtWebcode, 10000, 1000);


    }

    @OnClick({R.id.bt_webcode, R.id.bt_regist, R.id.tv_login, R.id.tv_kefu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_webcode:
                //获取验证码
                String password = mEdPassword.getText().toString();
                String surepw = mEdSurepw.getText().toString();
                if (  password.equals(surepw)) {

                    mIvSurepw.setVisibility(View.VISIBLE);


                } else {

                  /*  MyToast toast = new MyToast(RegistActivity.this, "两次密码必须相同", R.mipmap.com_icon_cross_w, 20);
                    toast.show();*/
                  MyToast.show("两次密码必须相同", R.mipmap.com_icon_cross_w);

                    mIvSurepw.setVisibility(View.INVISIBLE);

                }

                if (password.length() == 0 && surepw.length() == 0) {

                    mIvSurepw.setVisibility(View.INVISIBLE);

                }


                getwebCoede();





                break;
            case R.id.bt_regist:
                //调用接口注册
                boolean b = canGetWebcode();

                mBtRegist.setClickable(false);
                String phone = mEdPhone.getText().toString();
                String webcode = mEdWebcode.getText().toString().trim();

                Call<String> checkwebcode = Aplication.mIinterface.checkwebcode(phone, webcode);

                checkwebcode.enqueue(new Callback<String>() {



                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {

                        Log.i("zc", "onResponse:    看看 返回的数据" +  response.body().toString());

                        Type type = new TypeToken<ArrayList<AvatorBean>>() {
                        }.getType();

                        ArrayList<AvatorBean> mArray = Utils.gson.fromJson(response.body().toString(), type);

                        AvatorBean bean = mArray.get(0);
                        if (bean.isRet()) {

                            toregist(true);

                        } else {
                            mBtRegist.setClickable(true);

                            /*MyToast toast = new MyToast(RegistActivity.this, bean.getRetmsg(), R.mipmap.com_icon_cross_w, 20);
                            toast.show();*/
                            MyToast.show(bean.getRetmsg(), R.mipmap.com_icon_cross_w);

                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });




               // toregist(b);


                break;
            case R.id.tv_login:
                //登陆

                startActivity(new Intent(RegistActivity.this,LoginActivity.class));

                break;
            case R.id.tv_kefu:
                //客服


                break;
        }
    }

    private void toregist(boolean b) {
        if (b ) {

            String phone = mEdPhone.getText().toString();
            String cn = mEdCn.getText().toString();
            String pw = mEdSurepw.getText().toString();
            String fcode = mEdAsdsf.getText().toString();
            String webcode = mEdWebcode.getText().toString();

            Call<String> register = Aplication.mIinterface.register(phone, cn, fcode, pw, webcode);

            register.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(response.body().toString(), type);

                    AvatorBean bean = avatorBeen.get(0);

                    if (bean.isRet()) {

                      /*  MyToast toast = new MyToast(RegistActivity.this, "注册成功", R.mipmap.com_icon_check_w, 20);
                        toast.show();*/
                      MyToast.show("注册成功", R.mipmap.com_icon_check_w);
                        Log.i("zc", "onResponse:   注册成功 弹了 几次toast");
                        finish();
                    } else {

                        /*MyToast toast = new MyToast(RegistActivity.this, bean.getRetmsg(), R.mipmap.com_icon_cross_w, 20);
                        toast.show();*/
                        MyToast.show(bean.getRetmsg(), R.mipmap.com_icon_cross_w);


                    }




                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });


        }
    }



    /**
     * 获取验证码的方法
     */
    private void getwebCoede() {
        mTimeCountUtil.start();
        //从网络上获取到webcode
        //检查前面  的有没有问题

        //boolean b = canGetWebcode();


            String phone = mEdPhone.getText().toString();
            Call<String> getwebCode = Aplication.mIinterface.getwebCode(phone);
            getwebCode.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> list = Utils.gson.fromJson(response.body().toString(), type);

                    mWebcode = list.get(0).getRetmsg();

                    Log.i("zc", "onResponse:     查看验证码" + mWebcode);

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });



    }


    /**
     * 检查之前的项目 有没有问题
     *
     */
    private boolean canGetWebcode() {
        if (!isshowphone) {

           /* MyToast toast = new MyToast(RegistActivity.this, "请输入手机号，或检查手机号是否被注册过", R.mipmap.com_icon_cross_w, 20);
            toast.show();*/
           MyToast.show("请输入手机号，或检查手机号是否被注册过", R.mipmap.com_icon_cross_w);

            return false;

        } else if (!isshwocn) {
           /* MyToast toast = new MyToast(RegistActivity.this, "请检查cn是否正确", R.mipmap.com_icon_cross_w, 20);
            toast.show();*/
           MyToast.show("请检查cn是否正确", R.mipmap.com_icon_cross_w);

            return false;

        } else if (!isshowpw) {

            /*MyToast toast = new MyToast(RegistActivity.this, "请检查密码是否大于6位", R.mipmap.com_icon_cross_w, 20);
            toast.show();*/
            MyToast.show("请检查密码是否大于6位", R.mipmap.com_icon_cross_w);

            return false;

        } else if (!isshowsurepw) {

         /*   MyToast toast = new MyToast(RegistActivity.this, "请检查两次密码是否一致", R.mipmap.com_icon_cross_w, 20);
            toast.show();*/
         MyToast.show("请检查两次密码是否一致", R.mipmap.com_icon_cross_w);

            return false;

        } else if (mWebcode.length() == 0) {

           /* MyToast toast = new MyToast(RegistActivity.this, "请填写验证码", R.mipmap.com_icon_cross_w, 20);
            toast.show();*/
           MyToast.show("请填写验证码", R.mipmap.com_icon_cross_w);


            return false;
        } else {

            return true;

        }



    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        switch (v.getId()) {

            case R.id.ed_phone:

                if (!hasFocus) {
                    checkPhone();
                }
                break;

            case R.id.ed_cn:
                // 当入cn的时候 失去焦点的话  判断cn

                if (!hasFocus) {

                    checkcn();

                }


                break;



            case R.id.ed_password:

                if (!hasFocus) {

                    checkpassword();

                }



                break;

            case R.id.ed_surepw:
                //当失去焦点的时候 判断两次输入的密码 是否一样
                if (!hasFocus) {
                    checksurepw();

                }




                break;

            case R.id.ed_webcode:

                break;





        }



    }

    private void checksurepw() {
        String pw = mEdPassword.getText().toString();

        String surpw = mEdSurepw.getText().toString();

        if (pw.equals(surpw) && surpw.length() != 0) {
            mIvSurepw.setImageResource(R.mipmap.com_icon_check_g);
            mIvSurepw.setVisibility(View.VISIBLE);
            isshowsurepw = true;
        } else if (surpw.length() == 0) {


            mIvSurepw.setVisibility(View.INVISIBLE);
            isshowsurepw = false;

        } else {
            mIvSurepw.setImageResource(R.mipmap.com_icon_cross_r);
            mIvSurepw.setVisibility(View.VISIBLE);
         /*   MyToast toast = new MyToast(RegistActivity.this, "请检查两次输入的密码是否一致", R.mipmap.com_icon_cross_w, 20);
            toast.show();*/
         MyToast.show("请检查两次输入的密码是否一致", R.mipmap.com_icon_cross_w);

            isshowsurepw = false;
        }
    }

    /**
     * 检查密码
     */
    private void checkpassword() {
        String pw = mEdPassword.getText().toString();
        if (pw.length() >= 6) {

            mIvPassword.setImageResource(R.mipmap.com_icon_check_g);
            mIvPassword.setVisibility(View.VISIBLE);
            isshowpw = true;

        } else if (pw.length() == 0) {

           /* MyToast toast = new MyToast(RegistActivity.this, "请检查密码是否大于6位", R.mipmap.com_icon_cross_w, 20);
            toast.show();

            mIvPassword.setImageResource(R.mipmap.com_icon_cross_r);
            mIvPassword.setVisibility(View.VISIBLE);

            isshowpw = false;*/

            mIvPassword.setVisibility(View.INVISIBLE);
            isshowpw = false;


        } else {
          /*   MyToast toast = new MyToast(RegistActivity.this, "请检查密码是否大于6位", R.mipmap.com_icon_cross_w, 20);
            toast.show();*/
          MyToast.show("请检查密码是否大于6位", R.mipmap.com_icon_cross_w);

            mIvPassword.setImageResource(R.mipmap.com_icon_cross_r);
            mIvPassword.setVisibility(View.VISIBLE);

            isshowpw = false;

        }



    }

    private void checkcn() {
        String cn = mEdCn.getText().toString().trim();
        Log.i("zc", "checkcn:    看看 cn  的长度 " +  cn.length());
        if (cn.length() != 0) {

            mIvCn.setVisibility(View.VISIBLE);
            isshwocn = true;

            Call<String> getcnuseable = Aplication.mIinterface.getcnuseable(cn);
            getcnuseable.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String body = response.body();
                    if (body != null) {
                        Type type = new TypeToken<ArrayList<AvatorBean>>() {
                        }.getType();

                        ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body.toString(), type);

                        AvatorBean bean = avatorBeen.get(0);

                        if (bean.getRetmsg().equals("false")) {

                            mIvCn.setImageResource(R.mipmap.com_icon_check_g);
                            mIvCn.setVisibility(View.VISIBLE);
                            isshwocn = true;

                        } else {

                            /*MyToast toast = new MyToast(RegistActivity.this,  "该cn已被注册" , R.mipmap.com_icon_cross_w, 20);
                            toast.show();*/
                            MyToast.show("该cn已被注册" , R.mipmap.com_icon_cross_w);
                            mIvCn.setImageResource(R.mipmap.com_icon_cross_r);
                            mIvCn.setVisibility(View.VISIBLE);
                            isshwocn = false;


                        }



                    }


                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });



        } else {

            mIvCn.setVisibility(View.INVISIBLE);
            isshwocn = false;

        }


    }

    /**
     * 检查手机号  是否 正确
     */
    private void checkPhone() {
        String phon = mEdPhone.getText().toString();
        Log.i("zc", "checkPhone:      检查手机号");
        boolean b = Utils.checkPhoneNumber(phon);

        if (b) {

            Log.i("zc", "checkPhone:     查看手机号" + phon);
            Call<String> getphonuseable = Aplication.mIinterface.getphonuseable(phon);
            getphonuseable.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(response.body().toString(), type);
                    Log.i("zc", "onResponse:      " + response.body().toString());
                    AvatorBean bean = avatorBeen.get(0);
                    Log.i("zc", "onResponse: =======查看是否注册过,查看集合里的数据"  + bean.getRetmsg()  + avatorBeen.size());

                    if (bean.getRetmsg().equals("false")) {
                        mIvPhone.setImageResource(R.mipmap.com_icon_check_g);
                        mIvPhone.setVisibility(View.VISIBLE);
                        isshowphone = true;
                        avatorBeen.clear();
                    } else {

                       /* MyToast toast = new MyToast(RegistActivity.this,  "请输入手机号，或该手机号已经被注册" , R.mipmap.com_icon_cross_w, 20);
                        toast.show();*/
                       MyToast.show("请输入手机号，或该手机号已经被注册" , R.mipmap.com_icon_cross_w);

                        mIvPhone.setImageResource(R.mipmap.com_icon_cross_r);
                        mIvPhone.setVisibility(View.VISIBLE);
                        isshowphone = false;
                        avatorBeen.clear();
                    }



                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            });



        }
    }


}
