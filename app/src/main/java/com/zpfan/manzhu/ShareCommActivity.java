package com.zpfan.manzhu;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.onekeyshare.OnekeyShare;
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

    private String mStortType = "";
    private PopupWindow mPaywindow;
    private TextView mTvcancle;
    private TextView mTvshare;
    private RecyclerView mRvpopshare;
    private ArrayList<ShareBean> mShare;
    private ShareAdapter mpopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_comm);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        View headView = View.inflate(ShareCommActivity.this, R.layout.head_sharecomm, null);
        mRvShare.setLayoutManager(new GridLayoutManager(ShareCommActivity.this,2));
        //去获取分享提佣的商品列表
        getShareGoodList();

        mShare = new ArrayList<>();
        mShare.add(new ShareBean(R.mipmap.share_icon_qq, "手机QQ", QQ.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_qzone, "QQ空间", QZone.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_weibo, "新浪微博",SinaWeibo.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_weixin, "微信", Wechat.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_pyq, "微信朋友圈", WechatMoments.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_haoyou, "发给好友"));
        mShare.add(new ShareBean(R.mipmap.share_icon_lianjie, "复制链接"));
        initPop();


    }

    private void initPop() {

        mPaywindow = new PopupWindow(ShareCommActivity.this);
        View inflate = View.inflate(ShareCommActivity.this, R.layout.share_pop, null);
        mTvcancle = (TextView) inflate.findViewById(R.id.tv_cancle);
        mTvshare = (TextView) inflate.findViewById(R.id.tv_share);
        mRvpopshare = (RecyclerView) inflate.findViewById(R.id.rv_share);
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

                            final ArrayList<BussnessBean> bussnessBeen = Utils.gson.fromJson(substring, type1);
                            ShareCommisssioinAdapter adapter = new ShareCommisssioinAdapter(R.layout.item_sharecomm, bussnessBeen);
                            adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                @Override
                                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        //分享提佣的功能
                                    showShare(bussnessBeen.get(position));

                                }
                            });
                            mRvShare.setAdapter(adapter);



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

        mPaywindow.showAtLocation(mRvShare, Gravity.BOTTOM,0,0 );

        String text = "分享本宝贝可得 " + bussnessBean.getG_CommissionMoney() + " 元 佣金哦~";
        SpannableString string = new SpannableString(text);
        string.setSpan(new ForegroundColorSpan(this.getResources().getColor(R.color.pricetextcolor)),7,11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );

        mTvshare.setText(text);

        mpopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShareBean bean = mShare.get(position);
                if (bean.getParpamName() != null) {
                    OnekeyShare oks = new OnekeyShare();

                    oks.setImageUrl(bussnessBean.getG_Cover());
                    oks.setTitleUrl("http://www.anipiggy.com/Home/productdetail.htm?" +"id="+bussnessBean.getId() +"&share_member=" + Utils.getloginid());
                        String remarks = bussnessBean.getG_DetailRemarks();
                    if (remarks.length() > 51) {
                        oks.setText(remarks.substring(0, 51));
                    } else {
                        oks.setText(remarks);
                    }
                    String type = bussnessBean.getG_Type();
                    if (type.equals("二手商品")) {
                        oks.setTitle("【猪排贩】COS闲置贩售");
                    } else if (type.equals("新商品")) {
                        oks.setTitle("【猪排贩】COS新品定做");
                    } else if (type.equals("服务")) {
                        oks.setTitle("【猪排贩】COS服务接单");
                    }
                    oks.setPlatform(bean.getParpamName());
                    oks.show(ShareCommActivity.this);
                }
            }
        });
    }


}
