package com.zpfan.manzhu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pass_word);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {


    }


    @OnClick({R.id.ll_loginpw, R.id.ll_tradpw, R.id.ll_editpw, R.id.tv_kefu, R.id.bt_import})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_loginpw:
                //选择修改登陆密码
                if (!isloginpw) {
                    isloginpw = true;
                    mLlChangepw.setVisibility(View.GONE);
                    mLlNowpw.setVisibility(View.VISIBLE);
                    mBtImport.setVisibility(View.GONE);
                    mLlKefu.setVisibility(View.GONE);
                    //显示为当前的密码
                    mTvPwtype.setText("当前登陆密码");


                }


                break;
            case R.id.ll_tradpw:
                if (isloginpw) {
                    isloginpw = true;
                    mLlChangepw.setVisibility(View.GONE);
                    mLlNowpw.setVisibility(View.VISIBLE);
                    mBtImport.setVisibility(View.GONE);
                    mLlKefu.setVisibility(View.GONE);
                    mTvPwtype.setText("当前交易密码");
                }

                break;
            case R.id.ll_editpw:
                //切换到修改的界面




                break;
            case R.id.tv_kefu:
                //客服的按钮


                break;
            case R.id.bt_import:
                //提交的按钮
            

                break;
        }
    }
}
