package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MessageSendActivity extends AppCompatActivity implements EMMessageListener {

    @BindView(R.id.iv_top_back)
    ImageView mIvTopBack;
    @BindView(R.id.tv_top_text)
    TextView mTvTopText;
    @BindView(R.id.iv_baohu)
    ImageView mIvBaohu;
    @BindView(R.id.iv_dianpu)
    ImageView mIvDianpu;
    @BindView(R.id.ll_top)
    LinearLayout mLlTop;
    @BindView(R.id.ed_message)
    EditText mEdMessage;
    @BindView(R.id.iv_biaoqing)
    ImageView mIvBiaoqing;
    @BindView(R.id.iv_yuyin)
    ImageView mIvYuyin;
    @BindView(R.id.iv_tupian)
    ImageView mIvTupian;
    @BindView(R.id.iv_zhaoxiang)
    ImageView mIvZhaoxiang;
    @BindView(R.id.bt_send)
    Button mBtSend;
    @BindView(R.id.ll_bootmlin)
    LinearLayout mLlBootmlin;
    @BindView(R.id.rv_message)
    RecyclerView mRvMessage;
    private EMMessageListener mMsgListener;
    private String mUserphone;
    private String mCn;
    private ArrayList<EMMessage> mMessageList;
    private EMConversation mConversation;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_send);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {
        Intent intent = getIntent();
        mUserphone = intent.getStringExtra("userphone");
        mCn = intent.getStringExtra("usercn");

        mTvTopText.setText(mCn);


        mMessageList = new ArrayList<>();

        mMsgListener = this;


        mRvMessage.setLayoutManager(new LinearLayoutManager(MessageSendActivity.this));

        mConversation = EMClient.getInstance().chatManager().getConversation(mUserphone);


    }


    @Override
    protected void onResume() {
        super.onResume();
        EMClient.getInstance().chatManager().addMessageListener(mMsgListener);
        showMessage();

    }

    @Override
    protected void onStop() {

        super.onStop();
        EMClient.getInstance().chatManager().removeMessageListener(mMsgListener);
    }

    private void showMessage() {


    }


    @OnClick({R.id.iv_top_back, R.id.iv_biaoqing, R.id.iv_yuyin, R.id.iv_tupian, R.id.iv_zhaoxiang, R.id.bt_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_top_back:
                break;
            case R.id.iv_biaoqing:
                break;
            case R.id.iv_yuyin:
                break;
            case R.id.iv_tupian:
                break;
            case R.id.iv_zhaoxiang:
                break;
            case R.id.bt_send:
                //发送消息的 按钮

                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage(mEdMessage.getText().toString(), mUserphone);
                //如果是群聊，设置chattype，默认是单聊
                message.setChatType(EMMessage.ChatType.Chat);
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);

                break;
        }
    }

    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        //收到新的消息

        for (EMMessage message : messages) {
            if (message.getFrom().equals(mUserphone)) {
                //说明是和我聊天的对象  发来的消息
                Toast.makeText(this, message.getBody().toString(), Toast.LENGTH_SHORT).show();



            }


        }






    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {

    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> messages) {

    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {

    }
}
