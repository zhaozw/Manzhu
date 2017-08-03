package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.LoginBean;
import com.zpfan.manzhu.bean.UserBean;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.myui.TopLin;
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

public class ForgetActivity extends AppCompatActivity  {


    @BindView(R.id.tl_forget)
    TopLin mTlForget;
    @BindView(R.id.tv_tishi)
    TextView mTvTishi;
    @BindView(R.id.ed_forgcn)
    EditText mEdForgcn;
    @BindView(R.id.ll_forgcn)
    LinearLayout mLlForgcn;
    @BindView(R.id.forg_lin2)
    View mForgLin2;
    @BindView(R.id.ed_phone)
    EditText mEdPhone;
    @BindView(R.id.ll_forgusername)
    LinearLayout mLlForgusername;
    @BindView(R.id.forg_lin1)
    View mForgLin1;
    @BindView(R.id.bt_find)
    Button mBtFind;
    @BindView(R.id.forg_lin3)
    View mForgLin3;
    @BindView(R.id.tv_result)
    TextView mTvResult;
    @BindView(R.id.ed_webcode)
    EditText mEdWebcode;
    @BindView(R.id.bt_webcode)
    Button mBtWebcode;
    @BindView(R.id.bt_resetpw)
    Button mBtResetpw;
    @BindView(R.id.ll_result)
    LinearLayout mLlResult;
    @BindView(R.id.forg_lin4)
    View mForgLin4;
    @BindView(R.id.ed_password)
    EditText mEdPassword;
    @BindView(R.id.iv_password)
    ImageView mIvPassword;
    @BindView(R.id.ll_password)
    LinearLayout mLlPassword;
    @BindView(R.id.forg_lin5)
    View mForgLin5;
    @BindView(R.id.ed_surepw)
    EditText mEdSurepw;
    @BindView(R.id.iv_surepw)
    ImageView mIvSurepw;
    @BindView(R.id.ll_surepw)
    LinearLayout mLlSurepw;
    @BindView(R.id.forg_lin6)
    View mForgLin6;
    @BindView(R.id.bt_import)
    Button mBtImport;
    @BindView(R.id.ll_reset)
    LinearLayout mLlReset;
    @BindView(R.id.tv_haikey)
    TextView mTvHaikey;
    @BindView(R.id.tv_registnew)
    TextView mTvRegistnew;
    private String mUid;

    private boolean ischeckpw;
    private boolean isright = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);
        setListener();

    }

    private void setListener() {

        mEdPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 6) {
                    mIvPassword.setVisibility(View.VISIBLE);

                } else {

                    mIvPassword.setVisibility(View.INVISIBLE);

                }
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
                ischeckpw = false;
                String s1 = s.toString();

                String pw = mEdPassword.getText().toString();

                if (s1.equals(pw)) {
                    ischeckpw = true;

                    mIvSurepw.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

                String s1 = s.toString();

                String pw = mEdPassword.getText().toString();

                if (s1.equals(pw)) {

                    mIvSurepw.setVisibility(View.VISIBLE);
                    ischeckpw = true;
                }

            }
        });



        mEdSurepw.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                String pw1 = mEdPassword.getText().toString();
                String supw = mEdSurepw.getText().toString();

                if (supw.equals(pw1)) {
                    mIvSurepw.setVisibility(View.VISIBLE);
                    ischeckpw = true;
                } else {
                    mIvSurepw.setVisibility(View.INVISIBLE);

                    ischeckpw = false;
                }


            }
        });






    }

    @OnClick({R.id.bt_find, R.id.bt_webcode, R.id.bt_resetpw, R.id.tv_registnew, R.id.bt_import})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_find:
                //  调用接口   查找信息

                String phon = mEdPhone.getText().toString();
                String cn = mEdForgcn.getText().toString();


                Call<String> findpassword = Aplication.mIinterface.findpassword(phon, cn);

                findpassword.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.body() == null) {
                            return;
                        }

                        String message = response.body().toString();

                        Type type = new TypeToken<ArrayList<LoginBean>>() {
                        }.getType();

                        ArrayList<LoginBean> list = Utils.gson.fromJson(message, type);

                        LoginBean bean = list.get(0);


                        Type type1 = new TypeToken<ArrayList<UserBean>>() {
                        }.getType();
                        String retmsg = bean.getRetmsg();
                        if (retmsg.contains("M_Account_Status")) {
                            ArrayList<UserBean> list1 = Utils.gson.fromJson(retmsg, type1);
                            UserBean bean1 = list1.get(0);

                            mUid = bean1.getM_UID();

                            mTvResult.setText(bean1.getM_Sex() + ", Lv" + bean1.getN_AllLevel() + " ," + bean1.getM_Province() + "-" + bean1.getM_City());


                            mLlResult.setVisibility(View.VISIBLE);
                        } else {

                            /*MyToast toast = new MyToast(ForgetActivity.this, retmsg, R.mipmap.com_icon_cross_w, 20);
                            toast.show();*/
                            MyToast.show(retmsg, R.mipmap.com_icon_cross_w);


                        }


                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });


                break;
            case R.id.bt_webcode:
                // 获取验证码

                String phone = mEdPhone.getText().toString();
                TimeCountUtil mTimeCountUtil = new TimeCountUtil(mBtWebcode, 10000, 1000);

                mTimeCountUtil.start();

                Call<String> getwebCode = Aplication.mIinterface.getwebCode(phone);

                getwebCode.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {


                        Type type = new TypeToken<ArrayList<AvatorBean>>() {
                        }.getType();

                        ArrayList<AvatorBean> list = Utils.gson.fromJson(response.body().toString(), type);
                        AvatorBean bean = list.get(0);

                        if (bean.isRet()) {
                            //验证码是正确的
                            isright = true;

                        } else {
                            isright = false;
                        }


                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });


                break;
            case R.id.bt_resetpw:
                // 重新设置密码
                String phone1 = mEdPhone.getText().toString();
                String webcoed = mEdWebcode.getText().toString();
                if (isright) {
                    Call<String> checkwebcode = Aplication.mIinterface.checkwebcode(phone1, webcoed);

                    checkwebcode.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {



                            Type type = new TypeToken<ArrayList<AvatorBean>>() {
                            }.getType();

                            ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(response.body().toString(), type);

                            AvatorBean bean = avatorBeen.get(0);

                            if (bean.isRet()) {

                                mLlResult.setVisibility(View.INVISIBLE);

                                mLlReset.setVisibility(View.VISIBLE);
                            } else {

                             /*   MyToast toast = new MyToast(ForgetActivity.this, bean.getRetmsg(), R.mipmap.com_icon_cross_w, 20);
                                toast.show();*/
                             MyToast.show(bean.getRetmsg(), R.mipmap.com_icon_cross_w);

                            }


                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                } else {

                    /*MyToast toast = new MyToast(ForgetActivity.this, "验证码错误", R.mipmap.com_icon_cross_w, 20);
                    toast.show();
*/
                    MyToast.show("验证码错误", R.mipmap.com_icon_cross_w);

                }

                break;
            case R.id.tv_registnew:
                // 注册新账户
                startActivity(new Intent(getBaseContext(), RegistActivity.class));

                break;


            case R.id.bt_import:


                if (mUid != null && ischeckpw) {



                    String surpw = mEdSurepw.getText().toString();

                    //提交
                    Call<String> importnewpw = Aplication.mIinterface.importnewpw(mUid, surpw);

                    importnewpw.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String message = response.body().toString();


                            Type type = new TypeToken<ArrayList<AvatorBean>>() {
                            }.getType();
                            if (message != null) {

                                ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(message, type);
                                AvatorBean bean = avatorBeen.get(0);

                                if (bean.isRet()) {
                                   /* MyToast toast = new MyToast(ForgetActivity.this, "密码修改成功", R.mipmap.com_icon_check_w, 20);
                                    toast.show();*/

                                   MyToast.show("密码修改成功", R.mipmap.com_icon_check_w);
                                } else {
                                 /*   MyToast toast = new MyToast(ForgetActivity.this, bean.getRetmsg(), R.mipmap.com_icon_check_w, 20);
                                    toast.show();*/

                                 MyToast.show(bean.getRetmsg(), R.mipmap.com_icon_check_w);
                                }

                            }




                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });





                }




                break;



        }


    }
}
