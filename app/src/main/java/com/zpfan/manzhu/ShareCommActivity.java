package com.zpfan.manzhu;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.adapter.ShareAdapter;
import com.zpfan.manzhu.adapter.ShareCommisssioinAdapter;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.BussnessBean;
import com.zpfan.manzhu.bean.ShareBean;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareCommActivity extends AppCompatActivity {

    @BindView(R.id.rv_share)
    RecyclerView mRvShare;
    @BindView(R.id.iv_topback)
    ImageView mIvTopback;
    @BindView(R.id.iv_moneyhight)
    ImageView mIvMoneyhight;
    @BindView(R.id.tv_moneyhight)
    TextView mTvMoneyhight;
    @BindView(R.id.ll_moneyhight)
    LinearLayout mLlMoneyhight;
    @BindView(R.id.iv_sharenumber)
    ImageView mIvSharenumber;
    @BindView(R.id.tv_sharenumber)
    TextView mTvSharenumber;
    @BindView(R.id.ll_sharenumber)
    LinearLayout mLlSharenumber;
    @BindView(R.id.ll_topmenu)
    LinearLayout mLlTopmenu;

    private String mStortType = "";
    private PopupWindow mPaywindow;
    private TextView mTvcancle;
    private TextView mTvshare;
    private RecyclerView mRvpopshare;
    private ArrayList<ShareBean> mShare;
    private ShareAdapter mpopAdapter;
    private View mHeadView;
    private ShareCommisssioinAdapter mAdapter;
    private ArrayList<BussnessBean> mBussnessBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_comm);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mHeadView = View.inflate(ShareCommActivity.this, R.layout.head_sharecomm, null);
        LinearLayout llback = (LinearLayout) mHeadView.findViewById(R.id.ll_back);
        llback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mRvShare.setLayoutManager(new GridLayoutManager(ShareCommActivity.this,2));
        mAdapter = new ShareCommisssioinAdapter(R.layout.item_sharecomm, mBussnessBeen);
        mAdapter.addHeaderView(mHeadView);
        //去获取分享提佣的商品列表
        getShareGoodList();

        mShare = new ArrayList<>();
        mShare.add(new ShareBean(R.mipmap.share_icon_qq, "手机QQ", QQ.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_qzone, "QQ空间", QZone.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_weibo, "新浪微博", SinaWeibo.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_weixin, "微信", Wechat.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_pyq, "微信朋友圈", WechatMoments.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_haoyou, "发给好友"));
        mShare.add(new ShareBean(R.mipmap.share_icon_lianjie, "复制链接"));
        initPop();


        //对头布局消失的的监听
        mRvShare.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Rect rect = new Rect();
                mHeadView.getWindowVisibleDisplayFrame(rect);
                if (rect.top == 0) {
                    //头布局 不可见的情况下
                    mLlTopmenu.setVisibility(View.VISIBLE);

                } else {
                    //头布局可见的情况下

                    mLlTopmenu.setVisibility(View.GONE);

                }


            }
        });


    }

    private void initPop() {

        mPaywindow = new PopupWindow(ShareCommActivity.this);
        View inflate = View.inflate(ShareCommActivity.this, R.layout.share_pop, null);
        mTvcancle = (TextView) inflate.findViewById(R.id.tv_cancle);
        mTvshare = (TextView) inflate.findViewById(R.id.tv_share);
        mRvpopshare = (RecyclerView) inflate.findViewById(R.id.rv_popshare);

        LinearLayoutManager layout = new LinearLayoutManager(ShareCommActivity.this);
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvpopshare.setLayoutManager(layout);
        mpopAdapter = new ShareAdapter(R.layout.item_share, mShare);

        mRvpopshare.setAdapter(mpopAdapter);


        mPaywindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.detail_toppop_bg));
        mPaywindow.setTouchable(true);
        mPaywindow.setContentView(inflate);
        mPaywindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        mPaywindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPaywindow.setOutsideTouchable(true);
        mPaywindow.setFocusable(true);
        mPaywindow.update();

        mPaywindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = ShareCommActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 1f;
                ShareCommActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                ShareCommActivity.this.getWindow().setAttributes(lp);

            }
        });

        mTvcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPaywindow.dismiss();
            }
        });


    }

    private void getShareGoodList() {
        Call<String> call = Aplication.mIinterface.getgoodlistbySharecomm("1", "", "", "", mStortType);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("zc", "onResponse:   看看数据" + call.request().toString());
                String body = response.body();
                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);

                    if (avatorBeen != null && avatorBeen.size() > 0) {
                        AvatorBean bean = avatorBeen.get(0);
                        String retmsg = bean.getRetmsg();
                        if (retmsg.contains("[") && retmsg.length() > 4) {
                            String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));
                            Type type1 = new TypeToken<ArrayList<BussnessBean>>() {
                            }.getType();

                            mBussnessBeen = Utils.gson.fromJson(substring, type1);

                            mAdapter.setNewData(mBussnessBeen);
                            mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                    //分享提佣的功能

                                    showShare(mBussnessBeen.get(position));
                                }
                            });
                            mRvShare.setAdapter(mAdapter);


                        }


                    }


                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }

    private void showShare(final BussnessBean bussnessBean) {
        WindowManager.LayoutParams lp = ShareCommActivity.this.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        ShareCommActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ShareCommActivity.this.getWindow().setAttributes(lp);

        mPaywindow.showAtLocation(mRvShare, Gravity.BOTTOM, 0, 0);

        String text = "分享本宝贝可得 " + bussnessBean.getG_CommissionMoney() + " 元 佣金哦~";
        SpannableString string = new SpannableString(text);
        string.setSpan(new ForegroundColorSpan(this.getResources().getColor(R.color.pricetextcolor)), 7, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        mTvshare.setText(string);

        mpopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShareBean bean = mShare.get(position);
                if (bean.getParpamName() != null) {


                    Platform.ShareParams sp = new Platform.ShareParams();


                    String type = bussnessBean.getG_Type();
                    if (type.equals("二手商品")) {
                        sp.setTitle("【猪排贩】COS闲置贩售");
                    } else if (type.equals("新商品")) {
                        sp.setTitle("【猪排贩】COS新品定做");
                    } else if (type.equals("服务")) {
                        sp.setTitle("【猪排贩】COS服务接单");
                    }

                    String url = "http://www.anipiggy.com/Home/productdetail.htm?" + "id=" + bussnessBean.getId() + "&share_member=" + Utils.getloginid();
                    sp.setTitleUrl(url); // 标题的超链接
                    sp.setText(bussnessBean.getG_Title());
                    sp.setImageUrl(bussnessBean.getG_Cover());
                    sp.setSite("猪排贩");
                    sp.setSiteUrl("www.zpfan.com");

                    if (bean.getParpamName().equals(Wechat.NAME)) {
                        if (type.equals("二手商品")) {
                            sp.setTitle("【猪排贩】COS闲置贩售");
                        } else if (type.equals("新商品")) {
                            sp.setTitle("【猪排贩】COS新品定做");
                        } else if (type.equals("服务")) {
                            sp.setTitle("【猪排贩】COS服务接单");
                        }
                        sp.setImageUrl(bussnessBean.getG_Cover());
                        sp.setText(bussnessBean.getG_Title());
                        sp.setShareType(Platform.SHARE_WEBPAGE);
                    }
                    if (bean.getParpamName().equals(SinaWeibo.NAME)) {
                        sp.setText(bussnessBean.getG_Title() + url);
                    }


                    Platform platform = ShareSDK.getPlatform(bean.getParpamName());

                    platform.setPlatformActionListener(new PlatformActionListener() {
                        public void onError(Platform arg0, int arg1, Throwable arg2) {
                            //失败的回调，arg:平台对象，arg1:表示当前的动作，arg2:异常信息
                            Log.i("zc", "onError:  分享失败");
                        }

                        public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                            //分享成功的回调
                            Log.i("zc", "onError:  分享成功");
                        }

                        public void onCancel(Platform arg0, int arg1) {
                            //取消分享的回调
                            Log.i("zc", "onError:  分享取消" + arg0 + "----" + arg1);
                        }
                    });
                    platform.share(sp);
                    mPaywindow.dismiss();

                }
            }
        });
    }


    @OnClick({R.id.iv_topback, R.id.ll_moneyhight, R.id.ll_sharenumber})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_topback:
                finish();
                break;
            case R.id.ll_moneyhight:
                mIvMoneyhight.setImageResource(R.mipmap.com_icon_order);
                mTvMoneyhight.setTextColor(getResources().getColor(R.color.maintextcolor));
                mIvSharenumber.setImageResource(R.mipmap.com_icon_order_ept);
                mTvSharenumber.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mStortType = "sharemorel_v";
                getShareGoodList();

                break;
            case R.id.ll_sharenumber:

                mIvMoneyhight.setImageResource(R.mipmap.com_icon_order_ept);
                mTvMoneyhight.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvSharenumber.setImageResource(R.mipmap.com_icon_order);
                mTvSharenumber.setTextColor(getResources().getColor(R.color.maintextcolor));
                mStortType = "pricelow_v";
                getShareGoodList();

                break;
        }
    }
}
