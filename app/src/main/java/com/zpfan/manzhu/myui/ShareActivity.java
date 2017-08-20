package com.zpfan.manzhu.myui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.adapter.ShareAdapter;
import com.zpfan.manzhu.bean.BussnessBean;
import com.zpfan.manzhu.bean.ShareBean;
import com.zpfan.manzhu.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class ShareActivity extends AppCompatActivity {


    private static final String APP_ID = "wx13e7d2a913587256";
    public static IWXAPI api;

    @BindView(R.id.iv_sharepig)
    ImageView mIvSharepig;
    @BindView(R.id.tv_share)
    TextView mTvShare;
    @BindView(R.id.rv_popshare)
    RecyclerView mRvPopshare;
    @BindView(R.id.line)
    View mLine;
    @BindView(R.id.tv_cancle)
    TextView mTvCancle;
    @BindView(R.id.bootm_line)
    View mBootmLine;
    @BindView(R.id.close_tag)
    View mCloseTag;


    private ArrayList<ShareBean> mShare;
    private ShareAdapter mpopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
        mShare = new ArrayList<>();
        mShare.add(new ShareBean(R.mipmap.share_icon_qq, "手机QQ", QQ.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_qzone, "QQ空间", QZone.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_weibo, "新浪微博", SinaWeibo.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_weixin, "微信", Wechat.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_pyq, "微信朋友圈", WechatMoments.NAME));
        mShare.add(new ShareBean(R.mipmap.share_icon_haoyou, "发给好友"));
        mShare.add(new ShareBean(R.mipmap.share_icon_lianjie, "复制链接"));
        initpop();
        regToWx();

    }

    private void initpop() {
        // mPaywindow = new PopupWindow(ShareActivity.this);
        //  View inflate = View.inflate(ShareActivity.this, R.layout.share_pop, null);

        LinearLayoutManager layout = new LinearLayoutManager(ShareActivity.this);
        layout.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRvPopshare.setLayoutManager(layout);
        mpopAdapter = new ShareAdapter(R.layout.item_share, mShare);

        mRvPopshare.setAdapter(mpopAdapter);


        mTvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        mCloseTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Intent intent = getIntent();
        BussnessBean bussnessbean = intent.getParcelableExtra("bussnessbean");
        showShare(bussnessbean);
    }


    private void regToWx() {
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        api.registerApp(APP_ID);

    }


    public void showShare(final BussnessBean bussnessBean) {
      /*  WindowManager.LayoutParams lp = ShareActivity.this.getWindow()
                .getAttributes();
        lp.alpha = 0.4f;
        ShareActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ShareActivity.this.getWindow().setAttributes(lp);*/
        String text = null;
        if (bussnessBean.getG_CommissionMoney().equals("0.00")) {
            text = "求分享，求扩散~~~";
            mTvShare.setText(text);
        } else {
            text = "分享本宝贝可得 " + bussnessBean.getG_CommissionMoney() + " 元 佣金哦~";
            SpannableString string = new SpannableString(text);
            string.setSpan(new ForegroundColorSpan(this.getResources().getColor(R.color.pricetextcolor)), 7, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTvShare.setText(string);
        }


        mpopAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShareBean bean = mShare.get(position);
                if (bean.getParpamName() != null) {
                    String type = bussnessBean.getG_Type();
                    String title = "";
                    if (type.equals("二手商品")) {
                        title = "【猪排贩】COS闲置贩售";
                    } else if (type.equals("新商品")) {
                        title = "【猪排贩】COS新品定做";
                    } else if (type.equals("服务")) {
                        title = "【猪排贩】COS服务接单";
                    }
                    String url = "http://www.anipiggy.com/Home/productdetail.htm?" + "id=" + bussnessBean.getId() + "&share_member=" + Utils.getloginid();
                    if (bean.getParpamName().equals(Wechat.NAME) || bean.getParpamName().equals(WechatMoments.NAME)) {

                        WXWebpageObject webpag = new WXWebpageObject();
                        webpag.webpageUrl = url;
                        WXMediaMessage msg = new WXMediaMessage(webpag);
                        msg.title = title;
                        Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.app_logo);
                        msg.setThumbImage(thumb);
                        msg.description = bussnessBean.getG_Title();
                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = String.valueOf(System.currentTimeMillis());
                        req.message = msg;
                        if (bean.getParpamName().equals(Wechat.NAME)) {
                            req.scene = SendMessageToWX.Req.WXSceneSession;
                        } else {
                            req.scene = SendMessageToWX.Req.WXSceneTimeline;
                        }
                        boolean b = api.sendReq(req);
                        Log.i("zc", "onItemClick:  看看数据" + b);
                        finish();

                    } else {
                        Platform.ShareParams sp = new Platform.ShareParams();
                        sp.setTitle(title);
                        sp.setTitleUrl(url); // 标题的超链接
                        sp.setText(bussnessBean.getG_Title());
                        sp.setImageUrl(bussnessBean.getG_Cover());
                        sp.setSite("猪排贩");
                        sp.setSiteUrl("www.zpfan.com");

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
                        finish();

                    }
                }
            }
        });
    }


}
