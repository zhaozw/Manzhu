package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zpfan.manzhu.bean.ShopCartbean;
import com.zpfan.manzhu.event.LeaveMessageEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeaveMessageActivity extends AppCompatActivity {

    @BindView(R.id.et_message)
    EditText mEtMessage;
    @BindView(R.id.bt_import)
    Button mBtImport;
    private ShopCartbean.CarshoplistBean mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_message);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mItem = intent.getParcelableExtra("item");
        String a = intent.getStringExtra("a");
        if (a.equals("给卖家的补充说明都可以写在这里")) {
            mEtMessage.setHint("给卖家的补充说明都可以写在这里");
        } else {
        mEtMessage.setText(a);
        mEtMessage.setSelection(a.length());

        }


    }


    @OnClick({R.id.et_message, R.id.bt_import})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_message:
                break;
            case R.id.bt_import:
                String s = mEtMessage.getText().toString();
                LeaveMessageEvent event = new LeaveMessageEvent(s);
                event.bussness = mItem.getMember_UID();
                EventBus.getDefault().post(event);
                finish();
                break;
        }
    }





}
