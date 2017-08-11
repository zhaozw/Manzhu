package com.zpfan.manzhu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class EditPassWordActivity extends AppCompatActivity {

    @BindView(R.id.iv_loginpw)
    ImageView mIvLoginpw;
    @BindView(R.id.tv_loginpw)
    TextView mTvLoginpw;
    @BindView(R.id.ll_loginpw)
    LinearLayout mLlLoginpw;
    @BindView(R.id.iv_tradpw)
    ImageView mIvTradpw;
    @BindView(R.id.tv_tradpw)
    TextView mTvTradpw;
    @BindView(R.id.ll_tradpw)
    LinearLayout mLlTradpw;
    @BindView(R.id.tv_pwtype)
    TextView mTvPwtype;
    @BindView(R.id.tv_nowpw)
    TextView mTvNowpw;
    @BindView(R.id.ll_editpw)
    LinearLayout mLlEditpw;
    @BindView(R.id.ed_oldpw)
    EditText mEdOldpw;
    @BindView(R.id.ll_oldpw)
    LinearLayout mLlOldpw;
    @BindView(R.id.et_newpw)
    EditText mEtNewpw;
    @BindView(R.id.ll_newpw)
    LinearLayout mLlNewpw;
    @BindView(R.id.et_newpw2)
    EditText mEtNewpw2;
    @BindView(R.id.ll_newpw2)
    LinearLayout mLlNewpw2;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.bt_sendcode)
    TextView mBtSendcode;
    @BindView(R.id.et_verification)
    EditText mEtVerification;
    @BindView(R.id.ll_changepw)
    LinearLayout mLlChangepw;
    @BindView(R.id.tv_kefu)
    TextView mTvKefu;
    @BindView(R.id.ll_kefu)
    LinearLayout mLlKefu;
    @BindView(R.id.bt_import)
    Button mBtImport;
    @BindView(R.id.ll_nowpw)
    LinearLayout mLlNowpw;

    private boolean isloginpw = true;
    private String type = "登录密码";
    private String mUserphone;
    private boolean iswebcode = false;
    private boolean ishavetrdpw = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass_word);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //设置手机号上去
        mUserphone = SPUtils.getInstance().getString("userphone", "");
        String shou = mUserphone.substring(0, 3);
        String substring = mUserphone.substring(9);
        mTvPhone.setText(shou + "******" + substring);
        //先获取看该用户是否设置了交易密码 如果没有设置交易密码 直接就进入修改的界面，如果设置了交易的密码
        getisHavetrdpw();


    }

    private void getisHavetrdpw() {
        Call<String> getmemberoaypassword = Aplication.mIinterface.getmemberoaypassword(Utils.getloginuid());

        getmemberoaypassword.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String body = response.body();

                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                    if (avatorBeen != null && avatorBeen.size() > 0) {
                        AvatorBean bean = avatorBeen.get(0);
                        String retmsg = bean.getRetmsg();

                        if (retmsg.equals("True")) {
                            ishavetrdpw = true;

                        } else if (retmsg.equals("False")) {
                            ishavetrdpw = false;

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });




    }


    @OnClick({R.id.ll_loginpw, R.id.ll_tradpw, R.id.ll_editpw, R.id.tv_kefu, R.id.bt_import,R.id.bt_sendcode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_loginpw:
                //选择修改登陆密码
                    mIvLoginpw.setImageResource(R.mipmap.com_icon_pw_login);
                    mTvLoginpw.setTextColor(getResources().getColor(R.color.maintextcolor));
                    mIvTradpw.setImageResource(R.mipmap.com_icon_pw_trade_ept);
                    mTvTradpw.setTextColor(getResources().getColor(R.color.secondtextcolor));

                    isloginpw = true;
                    mLlChangepw.setVisibility(View.GONE);
                    mLlNowpw.setVisibility(View.VISIBLE);
                    mBtImport.setVisibility(View.GONE);
                    mLlKefu.setVisibility(View.GONE);
                    //显示为当前的密码
                    mTvPwtype.setText("当前登录密码");

                type = "登录密码";



                break;
            case R.id.ll_tradpw:


                type = "交易密码";
                isloginpw = false;
                mTvPwtype.setText("当前交易密码");

                //再走下面的逻辑
                if (ishavetrdpw) {
                    mIvLoginpw.setImageResource(R.mipmap.com_icon_pw_login_ept);
                    mTvLoginpw.setTextColor(getResources().getColor(R.color.secondtextcolor));
                    mIvTradpw.setImageResource(R.mipmap.com_icon_pw_trade);
                    mTvTradpw.setTextColor(getResources().getColor(R.color.maintextcolor));


                    mLlChangepw.setVisibility(View.GONE);
                    mLlNowpw.setVisibility(View.VISIBLE);
                    mBtImport.setVisibility(View.GONE);
                    mLlKefu.setVisibility(View.GONE);

                } else {
                    //没有交易密码
                    mIvLoginpw.setImageResource(R.mipmap.com_icon_pw_login_ept);
                    mTvLoginpw.setTextColor(getResources().getColor(R.color.secondtextcolor));
                    mIvTradpw.setImageResource(R.mipmap.com_icon_pw_trade);
                    mTvTradpw.setTextColor(getResources().getColor(R.color.maintextcolor));


                    mLlChangepw.setVisibility(View.VISIBLE);
                    mLlNowpw.setVisibility(View.GONE);
                    mBtImport.setVisibility(View.VISIBLE);
                    mLlKefu.setVisibility(View.VISIBLE);
                    mLlOldpw.setVisibility(View.GONE);



                }







                break;
            case R.id.ll_editpw:
                //切换到修改的界面
                mLlNowpw.setVisibility(View.GONE);
                mLlChangepw.setVisibility(View.VISIBLE);
                mLlKefu.setVisibility(View.VISIBLE);
                mBtImport.setVisibility(View.VISIBLE);


                break;
            case R.id.tv_kefu:
                //客服的按钮



                break;

            case R.id.bt_sendcode:
                //获取验证码的按钮
                Call<String> call = Aplication.mIinterface.getwebCode(mUserphone);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i("zc", "onResponse:    看看验证码的请求" + call.request().toString());
                        String body = response.body();
                        if (body != null) {
                            Type type = new TypeToken<ArrayList<AvatorBean>>() {
                            }.getType();

                            ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                            if (avatorBeen != null && avatorBeen.size() > 0) {
                                AvatorBean bean = avatorBeen.get(0);
                                String retmsg = bean.getRetmsg();
                                if (!retmsg.isEmpty()) {

                                    mBtImport.setClickable(true);
                                    iswebcode = true;

                                }




                            }




                        }




                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });




                break;

            case R.id.bt_import:
                //提交的按钮


                if (iswebcode) {

                        String oldpw = mEdOldpw.getText().toString();
                        if (oldpw.isEmpty()) {
                            oldpw = "";
                        }

                        String newpw = mEtNewpw.getText().toString();
                        if (newpw.isEmpty()) {
                            newpw = "";
                        }

                        String newpw2 = mEtNewpw2.getText().toString();
                        if (newpw2.isEmpty()) {
                            newpw2 = "";
                        }

                        String verification = mEtVerification.getText().toString();

                        if (verification.isEmpty()) {
                            verification = "";
                        }


                        Call<String> operaupdatepassword = Aplication.mIinterface.operaupdatepassword(Utils.getloginuid(), type, oldpw, newpw, newpw2, verification);

                        operaupdatepassword.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {

                                String body = response.body();

                                if (body != null) {
                                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                                    }.getType();

                                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                                    if (avatorBeen != null && avatorBeen.size() > 0) {

                                        AvatorBean bean = avatorBeen.get(0);
                                        if (bean.getRetmsg().equals("True")) {
                                            if (isloginpw) {
                                                MyToast.show("密码修改成功", R.mipmap.com_icon_check_w);
                                            } else {
                                                MyToast.show("交易密码设置或修改成功",R.mipmap.com_icon_check_w);
                                            }
                                        }


                                    }


                                }


                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });

                } else {
                    MyToast.show("请先获取验证码再提交",R.mipmap.com_icon_cross_w);
                    mBtImport.setClickable(false);
                }


                break;
        }
    }
}
