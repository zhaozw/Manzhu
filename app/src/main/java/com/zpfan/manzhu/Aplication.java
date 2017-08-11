package com.zpfan.manzhu;


import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.tencent.bugly.crashreport.CrashReport;
import com.zpfan.manzhu.utils.APIinterface;
import com.zpfan.manzhu.utils.FontUtils;
import com.zpfan.manzhu.utils.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import static com.hyphenate.easeui.utils.EaseUserUtils.getUserInfo;


/**
 * Created by Administrator on 2017/6/13 0013.
 */

public class Aplication extends Application {


    public static Context mContext ;
    public static APIinterface mIinterface;


    @Override
    public void onCreate() {
        super.onCreate();
        //对全局的字体进行替换
        mContext = getApplicationContext();

        FontUtils.getInstance().replaceSystemDefaultFontFromAsset(mContext,"fonts/FZLT_QH.ttf");


        Retrofit retrofit = new Retrofit.Builder().baseUrl(Utils.baseUrl).addConverterFactory(SimpleXmlConverterFactory.create()).build();
        mIinterface = retrofit.create(APIinterface.class);

        //环信的初始化

         EaseUI.getInstance().init(this, null);
        EaseUI easeui = EaseUI.getInstance();

        easeui.setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
            @Override
            public EaseUser getUser(String username) {

                return getUserInfo(username);
            }
        });

        //EMOptions options = new EMOptions();
    //默认添加好友时，是不需要验证的，改成需要验证
        //ptions.setAcceptInvitationAlways(true);
    //初始化
      //  EMClient.getInstance().init(mContext, initOptions());
    //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        //EMClient.getInstance().setDebugMode(true);
        CrashReport.initCrashReport(getApplicationContext(), "5395a802aa", true);


        //看看动态申请 sk卡的 读写权限 有效没有


        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }




    }

    /**
     * SDK初始化的一些配置
     * 关于 EMOptions 可以参考官方的 API 文档
     * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1chat_1_1_e_m_options.html
     */
    private EMOptions initOptions() {

        EMOptions options = new EMOptions();
        // 设置Appkey，如果配置文件已经配置，这里可以不用设置
        // options.setAppKey("lzan13#hxsdkdemo");
        // 设置自动登录
        options.setAutoLogin(false);
        // 设置是否需要发送已读回执
        options.setRequireAck(true);
        // 设置是否需要发送回执，
        options.setRequireDeliveryAck(true);
        // 设置是否需要服务器收到消息确认
        options.setRequireDeliveryAck(true);
        // 设置是否根据服务器时间排序，默认是true
        options.setSortMessageByServerTime(false);
        // 收到好友申请是否自动同意，如果是自动同意就不会收到好友请求的回调，因为sdk会自动处理，默认为true
        options.setAcceptInvitationAlways(false);
        // 设置是否自动接收加群邀请，如果设置了当收到群邀请会自动同意加入
        options.setAutoAcceptGroupInvitation(false);
        // 设置（主动或被动）退出群组时，是否删除群聊聊天记录
        options.setDeleteMessagesAsExitGroup(false);
        // 设置是否允许聊天室的Owner 离开并删除聊天室的会话
        options.allowChatroomOwnerLeave(true);
        // 设置google GCM推送id，国内可以不用设置
        // options.setGCMNumber(MLConstants.ML_GCM_NUMBER);
        // 设置集成小米推送的appid和appkey
        // options.setMipushConfig(MLConstants.ML_MI_APP_ID, MLConstants.ML_MI_APP_KEY);

        return options;



    }





}
