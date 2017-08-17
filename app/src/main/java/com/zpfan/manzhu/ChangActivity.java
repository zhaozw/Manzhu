package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMMessage;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zpfan.manzhu.adapter.ChangeAdapter;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.BussnessBean;
import com.zpfan.manzhu.bean.ChangeBean;
import com.zpfan.manzhu.bean.ShopBean;
import com.zpfan.manzhu.myui.EaseActivity;
import com.zpfan.manzhu.myui.MyToast;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangActivity extends AppCompatActivity {

    @BindView(R.id.tv_shopname)
    TextView mTvShopname;
    @BindView(R.id.tv_userlv)
    TextView mTvUserlv;
    @BindView(R.id.iv_message)
    ImageView mIvMessage;
    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.ll_message)
    LinearLayout mLlMessage;
    @BindView(R.id.iv_shopcover)
    RoundedImageView mIvShopcover;
    @BindView(R.id.tv_goodtitle)
    TextView mTvGoodtitle;
    @BindView(R.id.tv_goodformat)
    TextView mTvGoodformat;
    @BindView(R.id.tv_goodprice)
    TextView mTvGoodprice;
    @BindView(R.id.tv_yajin)
    TextView mTvYajin;
    @BindView(R.id.tv_goodcount)
    TextView mTvGoodcount;
    @BindView(R.id.ll_good)
    LinearLayout mLlGood;
    @BindView(R.id.line_cos)
    LinearLayout mLineCos;
    @BindView(R.id.rv_change)
    RecyclerView mRvChange;
    @BindView(R.id.et_leavemessage)
    EditText mEtLeavemessage;
    @BindView(R.id.bt_notice)
    TextView mBtNotice;
    private BussnessBean mDetail;
    private ShopBean mShop;
    private ArrayList<ChangeBean> mChangeBeen = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();

        mDetail = intent.getParcelableExtra("detail");
        mShop = intent.getParcelableExtra("shop");

        if (mDetail != null || mShop != null) {
            if (mShop != null) {
                mTvShopname.setText(mShop.getS_Name());
                mTvUserlv.setText("Lv." + mShop.getS_LevelNumber());
            } else {
                mTvShopname.setText(mDetail.getG_Member_OBJ().getM_UserName());
                mTvUserlv.setText("Lv." + mDetail.getG_Member_OBJ().getN_AllLevel());
            }
            mTvGoodtitle.setText(mDetail.getG_Title());

            mTvGoodcount.setText("x " + mDetail.getBuyCount());


            List<BussnessBean.GoodsSpecificationsBean> specifications = mDetail.getGoods_specifications();
            if (specifications.size() == 0) {
                mTvGoodformat.setVisibility(View.GONE);
                mTvGoodprice.setText(mDetail.getG_FixedPrice());
            } else {
                BussnessBean.GoodsSpecificationsBean msp = mDetail.getMsp();
                if (msp == null) {
                    //选用默认的规格

                    for (BussnessBean.GoodsSpecificationsBean specification : specifications) {
                        if (specification.isPS_IsDefaultSelected()) {
                            mTvGoodformat.setText(specification.getPS_AttributeValues());
                            mTvGoodprice.setText(specification.getPS_FixedPrice());
                        }
                    }


                } else {
                    //展示选中的规格
                    mTvGoodformat.setText(msp.getPS_AttributeValues());
                    mTvGoodprice.setText(msp.getPS_FixedPrice());
                }



            }
            //展示要换的信息
            mRvChange.setLayoutManager(new LinearLayoutManager(ChangActivity.this));
            String fk = mDetail.getDemand_FK();
            String[] split = fk.split(",");
            for (String s : split) {
                String[] split1 = s.split("\\|");
                Log.i("zc", "initView:   看看数据" +split1[0] +  split1[1] +  split1[2] + split1[3] +  split1[4]+ split1[5] );
                mChangeBeen.add(new ChangeBean(split1[0], split1[1], split1[2], split1[3], split1[4], split1[5]));
            }
            ChangeAdapter adapter = new ChangeAdapter(R.layout.item_chang, mChangeBeen);
            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    boolean ischeck = mChangeBeen.get(position).ischeck();
                    mChangeBeen.get(position).setIscheck(!ischeck);
                    adapter.notifyDataSetChanged();
                }
            });
            mRvChange.setAdapter(adapter);



        }


    }

    @OnClick({R.id.ll_message, R.id.bt_notice})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_message:
                if (Utils.isUserLogin()) {

                    Intent intent = new Intent(ChangActivity.this, EaseActivity.class);

                    intent.putExtra("userId", mDetail.getG_ContactPhone());
                    intent.putExtra("usercn", mShop.getS_Name());
                    intent.putExtra("chatType", EMMessage.ChatType.Chat);
                    startActivity(intent);

                } else {
                    startActivity(new Intent(ChangActivity.this,LoginActivity.class));

                }
                break;
            case R.id.bt_notice:
                //发送网络请求
                String s = mEtLeavemessage.getText().toString();
                Map<String, String> map = new LinkedHashMap<>();
                map.put("goodid", "");
                for (ChangeBean bean : mChangeBeen) {
                    String goodid = map.get("goodid");
                    if (bean.ischeck()) {
                        if (goodid.isEmpty()) {
                            map.put("goodid", bean.getId());
                        } else {
                            map.put("goodid", goodid + ","+ bean.getId());
                        }
                    }


                }

                String goodid = map.get("goodid");

                Call<String> call = Aplication.mIinterface.submitorderChange(Utils.getloginuid(), mDetail.getG_UID(), s, goodid, "");

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i("zc", "onResponse:   看看发送的请求" + call.request().toString());
                        String body = response.body();
                        if (body != null) {

                            Type type = new TypeToken<ArrayList<AvatorBean>>() {
                            }.getType();

                            ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);

                            if (avatorBeen != null && avatorBeen.size() > 0) {

                                String retmsg = avatorBeen.get(0).getRetmsg();
                                if (retmsg.equals("True")) {

                                    MyToast.show("已成功通知卖家，请等待他与你联系",R.mipmap.com_icon_check_w);
                                    finish();
                                }

                            }



                        }



                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });



                break;
        }
    }
}
