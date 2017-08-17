package com.zpfan.manzhu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.reflect.TypeToken;
import com.hyphenate.chat.EMMessage;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zpfan.manzhu.adapter.CosAdapter;
import com.zpfan.manzhu.adapter.ShopTuiJianAdapter;
import com.zpfan.manzhu.adapter.UserSpacePopAdapter;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.BussnessBean;
import com.zpfan.manzhu.bean.CosBean;
import com.zpfan.manzhu.bean.UserBean;
import com.zpfan.manzhu.bean.identityBean;
import com.zpfan.manzhu.myui.EaseActivity;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSpaceActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rv_uspace)
    RecyclerView mRvUspace;
    @BindView(R.id.tv_empty)
    TextView mTvEmpty;
    @BindView(R.id.iv_icontop_back)
    ImageView mIvIcontopBack;
    @BindView(R.id.tv_icontop_text)
    TextView mTvIcontopText;
    @BindView(R.id.iv_topsex)
    ImageView mIvTopsex;
    @BindView(R.id.iv_icontop_menu)
    ImageView mIvIcontopMenu;
    @BindView(R.id.iv_top1)
    ImageView mIvTop1;
    @BindView(R.id.tv_top1)
    TextView mTvTop1;
    @BindView(R.id.ll_top1)
    LinearLayout mLlTop1;
    @BindView(R.id.iv_top2)
    ImageView mIvTop2;
    @BindView(R.id.tv_top2)
    TextView mTvTop2;
    @BindView(R.id.ll_top2)
    LinearLayout mLlTop2;
    @BindView(R.id.iv_top3)
    ImageView mIvTop3;
    @BindView(R.id.tv_top3)
    TextView mTvTop3;
    @BindView(R.id.ll_top3)
    LinearLayout mLlTop3;
    @BindView(R.id.ll_topmenu)
    LinearLayout mLlTopmenu;
    @BindView(R.id.ll_topmen)
    LinearLayout mLlTopmen;
    private String mUid;
    private UserBean mUser;
    private ArrayList<BussnessBean> mBussnessBeen;
    private ArrayList<CosBean> mCosBeen;
    private View mHeadView;
    private TextView mTvusername;
    private TextView mTvfansnumber;
    private ImageView mIvmanor;
    private CircleImageView mIvuserava;
    private TagFlowLayout mTfidentity;
    private ShopTuiJianAdapter mAdapter;
    private CosAdapter mCosadapter;
    private ImageView mIvcos;
    private ImageView mIvgood;
    private ImageView mIvserver;
    private TextView mTvcos;
    private TextView mTvgood;
    private TextView mTvserver;
    private ImageView mIvattention;
    private TextView mTvattention;
    private boolean isaddation = false;
    private PopupWindow mpopWindow;
    private ImageView mIvpopaddation;
    private TextView mTvpopaddation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_space);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        Intent intent = getIntent();
        mBussnessBeen = new ArrayList<>();
        mCosBeen = new ArrayList<>();

        mUid = intent.getStringExtra("m_uid");
        if (mUid == null) {
            mUid = "";
        } else {
            getUserInfo();
        }

        mRvUspace.setLayoutManager(new LinearLayoutManager(UserSpaceActivity.this));

        mAdapter = new ShopTuiJianAdapter(mBussnessBeen);
        mCosadapter = new CosAdapter(R.layout.item_cosshow, mCosBeen);
        mHeadView = View.inflate(UserSpaceActivity.this, R.layout.head_uspace, null);
        initHeadView();
        mRvUspace.setAdapter(mAdapter);
        mAdapter.addHeaderView(mHeadView);


        //setUserInfo(mUser);
        getUserCos();
        if (Utils.isUserLogin()) {
            isAttention();
        }




        //对头布局的监听
        mRvUspace.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Rect rect = new Rect();
                mHeadView.getWindowVisibleDisplayFrame(rect);
                if (rect.top == 0) {
                    mLlTopmen.setVisibility(View.VISIBLE);
                } else {
                    mLlTopmen.setVisibility(View.GONE);

                }


            }
        });


    }

    private void initPopMenu(final UserBean user) {
        ArrayList<identityBean> ident = new ArrayList<>();
        if (user != null) {
            mpopWindow = new PopupWindow(UserSpaceActivity.this);
            View inflate = View.inflate(UserSpaceActivity.this, R.layout.userspace_popwindow, null);
            ImageView ivbotoom = (ImageView) inflate.findViewById(R.id.iv_botoom);
            ImageView ivhome = (ImageView) inflate.findViewById(R.id.iv_home);
            ImageView ivshopcart = (ImageView) inflate.findViewById(R.id.iv_shopcart);
            ImageView ivmessage = (ImageView) inflate.findViewById(R.id.iv_message);
            ImageView ivusercenter = (ImageView) inflate.findViewById(R.id.iv_usercenter);
            final ImageView ivavator = (ImageView) inflate.findViewById(R.id.iv_avator);
            ImageView ivmanor = (ImageView) inflate.findViewById(R.id.iv_manor);
            View line = inflate.findViewById(R.id.dashline);
            line.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            View close = inflate.findViewById(R.id.close);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mpopWindow.dismiss();
                }
            });
            ivbotoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mpopWindow.dismiss();
                }
            });
            //设置头像 圆形
            RequestOptions options = new RequestOptions().centerCrop();

            Glide.with(this).asBitmap().load(Utils.imgUrl + user.getM_Avatar()).apply(options).into(new BitmapImageViewTarget(ivavator) {
                @Override
                protected void setResource(Bitmap resource) {

                    RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(UserSpaceActivity.this.getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    ivavator.setImageDrawable(circularBitmapDrawable);

                }
            });

            TextView tvleav = (TextView) inflate.findViewById(R.id.tv_leav);
            TextView tvname = (TextView) inflate.findViewById(R.id.tv_name);
            TextView tvdizhi = (TextView) inflate.findViewById(R.id.tv_dizhi);
            TextView tvuid = (TextView) inflate.findViewById(R.id.tv_uid);
            LinearLayout llpopaddation = (LinearLayout) inflate.findViewById(R.id.ll_popaddation);
            mIvpopaddation = (ImageView) inflate.findViewById(R.id.iv_popaddation);
            mTvpopaddation = (TextView) inflate.findViewById(R.id.tv_popaddation);


            tvleav.setText("Lv." +user.getN_AllLevel());
            tvname.setText(user.getM_UserName());
            tvdizhi.setText("（" + user.getM_Province() + "-"+ user.getM_City() +"）");
            tvuid.setText("UID  " + user.getCoser_figure_data().getId() + "  |  喜欢  " + user.getFollow_see_count() + "  |  粉丝  " +user.getFollow_see_count() );

            if (user.getM_Sex().equals("男")) {
                ivmanor.setImageResource(R.mipmap.com_icon_male);
            } else {
                ivmanor.setImageResource(R.mipmap.com_icon_female);
            }

            RecyclerView rvpop = (RecyclerView) inflate.findViewById(R.id.rv_userspace_pop);
            rvpop.setLayoutManager(new LinearLayoutManager(UserSpaceActivity.this));
            String identity = user.getMember_identity();
            String[] split = identity.split("\\|");
            for (String s : split) {
                String[] split1 = s.split(",");
                Log.i("zc", "initPopMenu:  看看数据" + "身份" + split1[0] + "专业" + split1[1] + "速度" + split1[2] + "态度" + split1[3]);
                ident.add(new identityBean(split1[0], split1[1], split1[2], split1[3]));
            }

            UserSpacePopAdapter adapter = new UserSpacePopAdapter(R.layout.item_uspace_ident, ident);
            rvpop.setAdapter(adapter);


            llpopaddation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getIsattention();


                }
            });

            ivhome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(UserSpaceActivity.this,MainActivity.class));
                }
            });


            ivshopcart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.isUserLogin()) {
                        startActivity(new Intent(UserSpaceActivity.this, ShopCarActivity.class));
                    } else {
                        startActivity(new Intent(UserSpaceActivity.this, LoginActivity.class));
                    }

                }
            });

            ivmessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Utils.isUserLogin()) {

                        Intent intent = new Intent(UserSpaceActivity.this, EaseActivity.class);
                        intent.putExtra("userId", user.getM_Phone());


                            intent.putExtra("usercn", user.getM_UserName());

                        intent.putExtra("chatType", EMMessage.ChatType.Chat);
                        startActivity(intent);
                        mpopWindow.dismiss();
                    } else {
                        startActivity(new Intent(UserSpaceActivity.this, LoginActivity.class));
                        mpopWindow.dismiss();
                    }


                }
            });

            ivusercenter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Utils.isUserLogin()) {
                        startActivity(new Intent(UserSpaceActivity.this, UserCenterActivity.class));
                    } else {
                        startActivity(new Intent(UserSpaceActivity.this, LoginActivity.class));
                    }
                }
            });




            mpopWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.detail_toppop_bg));
            mpopWindow.setTouchable(true);
            mpopWindow.setContentView(inflate);
            mpopWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            mpopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mpopWindow.setOutsideTouchable(true);
            mpopWindow.update();


            mpopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = UserSpaceActivity.this.getWindow()
                            .getAttributes();
                    lp.alpha = 1f;
                    UserSpaceActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    UserSpaceActivity.this.getWindow().setAttributes(lp);

                }
            });



        }


    }

    private void isAttention() {
        Call<String> call = Aplication.mIinterface.operaJudgeIsfollow(Utils.getloginuid(), mUid);

        call.enqueue(new Callback<String>() {
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
                        Log.i("zc", "onResponse:  看看数据" +  retmsg);
                        if (retmsg.equals("false")) {
                            mIvattention.setVisibility(View.VISIBLE);
                            mTvattention.setText("关注TA");
                            mIvpopaddation.setVisibility(View.VISIBLE);
                            mTvpopaddation.setText("关注TA");
                            isaddation = false;
                        } else {
                            mIvattention.setVisibility(View.GONE);
                            mTvattention.setText("已关注");
                            mIvpopaddation.setVisibility(View.GONE);
                            mTvpopaddation.setText("已关注");
                            isaddation = true;
                        }


                    }



                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }

    private void getIsattention() {
        if (Utils.isUserLogin()) {
            Call<String> operafollowmember = Aplication.mIinterface.operafollowmember(Utils.getloginuid(), mUid);

            operafollowmember.enqueue(new Callback<String>() {
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
                            if (bean.isRet()) {
                                if (isaddation) {
                                    //取消关注
                                    mIvattention.setVisibility(View.VISIBLE);
                                    mTvattention.setText("关注TA");
                                    mIvpopaddation.setVisibility(View.VISIBLE);
                                    mTvpopaddation.setText("关注TA");
                                    isaddation = false;
                                } else {
                                    //关注
                                    mIvattention.setVisibility(View.GONE);
                                    mTvattention.setText("已关注");
                                    mIvpopaddation.setVisibility(View.GONE);
                                    mTvpopaddation.setText("已关注");
                                    isaddation = true;
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

            startActivity(new Intent(this,LoginActivity.class));

        }



    }

    private void getUserCos() {
        Call<String> cos = Aplication.mIinterface.getUspaceCos(mUid);
        cos.enqueue(new Callback<String>() {
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
                        if (retmsg.equals("暂时没有信息！")) {
                            mCosBeen.clear();
                            mBussnessBeen.clear();

                            mTvEmpty.setVisibility(View.VISIBLE);

                        } else {
                            mTvEmpty.setVisibility(View.GONE);

                            String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));

                            Type type1 = new TypeToken<ArrayList<CosBean>>() {
                            }.getType();

                            mCosBeen = Utils.gson.fromJson(substring, type1);
                            mAdapter.removeAllHeaderView();
                            mCosadapter.setNewData(mCosBeen);
                            mCosadapter.notifyDataSetChanged();
                            int count = mCosadapter.getHeaderViewsCount();
                            if (count == 0) {
                            mCosadapter.addHeaderView(mHeadView);
                            }
                            mRvUspace.setAdapter(mCosadapter);

                        }


                    }


                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }

    private void setUserInfo(UserBean user) {
        if (user != null) {
            final ArrayList<String> ident = new ArrayList<>();
            mTvusername.setText(user.getM_UserName());
            mTvfansnumber.setText(user.getFollow_see_count());
            mTvIcontopText.setText(user.getM_UserName());

            Glide.with(this).load(Utils.imgUrl + user.getM_Avatar()).into(mIvuserava);
            String identity = user.getMember_identity();
            if (!identity.isEmpty()) {
                String[] split = identity.split("\\|");
                for (String s : split) {
                    String[] split1 = s.split(",");
                    ident.add(split1[0]);
                }

            }

            String sex = user.getM_Sex();
            if (sex.equals("女")) {
                mIvTopsex.setImageResource(R.mipmap.com_icon_female);
            } else {
                mIvTopsex.setImageResource(R.mipmap.com_icon_male);
            }




            TagAdapter<String> tagAdapter = new TagAdapter<String>(ident) {

                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) UserSpaceActivity.this.getLayoutInflater().inflate(R.layout.identity_tv, mTfidentity, false);
                    tv.setText(ident.get(position));
                    return tv;
                }
            };
            mTfidentity.setAdapter(tagAdapter);


        }
    }



    private void initHeadView() {
        ImageView ivtopback = (ImageView) mHeadView.findViewById(R.id.iv_top_back);
        ImageView ivtopmenu = (ImageView) mHeadView.findViewById(R.id.iv_topmenu);
        mIvcos = (ImageView) mHeadView.findViewById(R.id.iv_cos);
        mIvgood = (ImageView) mHeadView.findViewById(R.id.iv_good);
        mIvserver = (ImageView) mHeadView.findViewById(R.id.iv_server);
        mIvmanor = (ImageView) mHeadView.findViewById(R.id.iv_manor);
        mIvattention = (ImageView) mHeadView.findViewById(R.id.iv_attention);
        mIvuserava = (CircleImageView) mHeadView.findViewById(R.id.iv_userava);

        mTfidentity = (TagFlowLayout) mHeadView.findViewById(R.id.tf_identity);

        LinearLayout llattention = (LinearLayout) mHeadView.findViewById(R.id.ll_attention);
        LinearLayout llcos = (LinearLayout) mHeadView.findViewById(R.id.ll_cos);
        LinearLayout llserver = (LinearLayout) mHeadView.findViewById(R.id.ll_server);
        LinearLayout llgood = (LinearLayout) mHeadView.findViewById(R.id.ll_good);


        mTvusername = (TextView) mHeadView.findViewById(R.id.tv_username);
        mTvfansnumber = (TextView) mHeadView.findViewById(R.id.tv_fansnumber);
        mTvcos = (TextView) mHeadView.findViewById(R.id.tv_cos);
        mTvgood = (TextView) mHeadView.findViewById(R.id.tv_good);
        mTvserver = (TextView) mHeadView.findViewById(R.id.tv_server);
        mTvattention = (TextView) mHeadView.findViewById(R.id.tv_attention);



        ivtopback.setOnClickListener(this);
        ivtopmenu.setOnClickListener(this);
        llattention.setOnClickListener(this);
        llcos.setOnClickListener(this);
        llserver.setOnClickListener(this);
        llgood.setOnClickListener(this);
        mIvIcontopBack.setOnClickListener(this);
        mIvIcontopMenu.setOnClickListener(this);
        mLlTop1.setOnClickListener(this);
        mLlTop2.setOnClickListener(this);
        mLlTop3.setOnClickListener(this);



    }

    private void getUserInfo() {
        Call<String> getuspace = Aplication.mIinterface.getuspace(mUid);

        getuspace.enqueue(new Callback<String>() {
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
                        Type type1 = new TypeToken<ArrayList<UserBean>>() {
                        }.getType();

                        ArrayList<UserBean> userBeen = Utils.gson.fromJson(retmsg, type1);
                        if (userBeen != null && userBeen.size() > 0) {
                            mUser = userBeen.get(0);
                            setUserInfo(mUser);
                            initPopMenu(mUser);
                        }


                    }


                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_icontop_back:
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.iv_icontop_menu:
            case R.id.iv_topmenu:
                //下拉框的展示
                WindowManager.LayoutParams lp = UserSpaceActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 0.4f;
                UserSpaceActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                UserSpaceActivity.this.getWindow().setAttributes(lp);

                mpopWindow.showAtLocation(mRvUspace, Gravity.TOP, 0, 0);

                break;
            case R.id.ll_attention:
                //关注他的方法
                getIsattention();

                break;
            case R.id.ll_top1:
            case R.id.ll_cos:
                mTvEmpty.setVisibility(View.GONE);
                selectTag(0);
                getUserCos();


                break;
            case R.id.ll_top3:
            case R.id.ll_server:
                selectTag(2);
                mTvEmpty.setVisibility(View.GONE);
                mBussnessBeen.clear();
                mAdapter.notifyDataSetChanged();
                getUserGood("服务");

                break;
            case R.id.ll_top2:
            case R.id.ll_good:
                selectTag(1);
                mTvEmpty.setVisibility(View.GONE);
                mBussnessBeen.clear();
                mAdapter.notifyDataSetChanged();
                getUserGood("二手商品");

                break;


        }


    }

    private void getUserGood(final String gtype) {
        Call<String> good = Aplication.mIinterface.getUspaceGood(mUid, gtype);
        good.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("zc", "onResponse:  看看发送的请求 " + call.request().toString());
                String body = response.body();
                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);

                    if (avatorBeen != null && avatorBeen.size() > 0) {
                        AvatorBean bean = avatorBeen.get(0);
                        String retmsg = bean.getRetmsg();
                        if (bean.isRet()) {
                            String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));
                            Type type1 = new TypeToken<ArrayList<BussnessBean>>() {
                            }.getType();

                            mBussnessBeen = Utils.gson.fromJson(substring, type1);
                            for (BussnessBean bussnessBean : mBussnessBeen) {
                                if (gtype.equals("服务")) {
                                    bussnessBean.setItemType(BussnessBean.SERVER);
                                } else if (gtype.equals("二手商品")) {
                                    bussnessBean.setItemType(BussnessBean.IDLE);
                                }
                            }
                            int count = mAdapter.getHeaderLayoutCount();
                            if (count == 0) {
                                mCosadapter.removeAllHeaderView();
                                mAdapter.addHeaderView(mHeadView);
                            }

                            mAdapter.setNewData(mBussnessBeen);
                            mRvUspace.setAdapter(mAdapter);


                        } else {
                            mTvEmpty.setVisibility(View.VISIBLE);
                        }


                    }




                }



            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });



    }

    private void selectTag(int i) {
        switch (i) {
            case 0:
                mIvTop1.setImageResource(R.mipmap.com_icon_cos_elt);
                mIvcos.setImageResource(R.mipmap.com_icon_cos_elt);
                mTvTop1.setTextColor(getResources().getColor(R.color.maintextcolor));
                mTvcos.setTextColor(getResources().getColor(R.color.maintextcolor));

                mIvTop2.setImageResource(R.mipmap.com_icon_sh_ept);
                mIvgood.setImageResource(R.mipmap.com_icon_sh_ept);
                mTvTop2.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mTvgood.setTextColor(getResources().getColor(R.color.secondtextcolor));

                mIvTop3.setImageResource(R.mipmap.com_icon_serv_ept);
                mIvserver.setImageResource(R.mipmap.com_icon_serv_ept);
                mTvTop3.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mTvserver.setTextColor(getResources().getColor(R.color.secondtextcolor));

                break;

            case 1:

                mIvTop1.setImageResource(R.mipmap.com_icon_cos_elt_ept);
                mIvcos.setImageResource(R.mipmap.com_icon_cos_elt_ept);
                mTvTop1.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mTvcos.setTextColor(getResources().getColor(R.color.secondtextcolor));

                mIvTop2.setImageResource(R.mipmap.com_icon_sh);
                mIvgood.setImageResource(R.mipmap.com_icon_sh);
                mTvTop2.setTextColor(getResources().getColor(R.color.maintextcolor));
                mTvgood.setTextColor(getResources().getColor(R.color.maintextcolor));

                mIvTop3.setImageResource(R.mipmap.com_icon_serv_ept);
                mIvserver.setImageResource(R.mipmap.com_icon_serv_ept);
                mTvTop3.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mTvserver.setTextColor(getResources().getColor(R.color.secondtextcolor));


                break;


            case 2:
                mIvTop1.setImageResource(R.mipmap.com_icon_cos_elt_ept);
                mIvcos.setImageResource(R.mipmap.com_icon_cos_elt_ept);
                mTvTop1.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mTvcos.setTextColor(getResources().getColor(R.color.secondtextcolor));

                mIvTop2.setImageResource(R.mipmap.com_icon_sh_ept);
                mIvgood.setImageResource(R.mipmap.com_icon_sh_ept);
                mTvTop2.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mTvgood.setTextColor(getResources().getColor(R.color.secondtextcolor));

                mIvTop3.setImageResource(R.mipmap.com_icon_serv);
                mIvserver.setImageResource(R.mipmap.com_icon_serv);
                mTvTop3.setTextColor(getResources().getColor(R.color.maintextcolor));
                mTvserver.setTextColor(getResources().getColor(R.color.maintextcolor));

                break;

        }



    }
}
