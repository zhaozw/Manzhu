package com.zpfan.manzhu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.adapter.MessageaddAdapter;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.UserBean;
import com.zpfan.manzhu.myui.TopLin;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageAddActivity extends AppCompatActivity {

    @BindView(R.id.add_top)
    TopLin mAddTop;
    @BindView(R.id.ed_seach)
    EditText mEdSeach;
    @BindView(R.id.iv_seach)
    ImageView mIvSeach;
    @BindView(R.id.ll_seach)
    LinearLayout mLlSeach;
    @BindView(R.id.rv_add)
    RecyclerView mRvAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_add);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        // Utils.setTranslucentStatus(MessageAddActivity.this);
        mRvAdd.setLayoutManager(new LinearLayoutManager(MessageAddActivity.this));
        


    }

    @OnClick(R.id.iv_seach)
    public void onViewClicked() {
        //搜索按钮的业务

        String seach = mEdSeach.getText().toString();

        Call<String> seachuser = Aplication.mIinterface.seachuser(seach);

        seachuser.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                Type type = new TypeToken<ArrayList<AvatorBean>>() {
                }.getType();


                ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(response.body().toString(), type);

                String retmsg = avatorBeen.get(0).getRetmsg();

                if (retmsg.contains("[")) {
                    String json = retmsg.substring(1, retmsg.lastIndexOf("]"));
                    Type type1 = new TypeToken<ArrayList<UserBean>>() {
                    }.getType();

                    ArrayList<UserBean> userBeen = Utils.gson.fromJson(json, type1);

                    MessageaddAdapter messageaddAdapter = new MessageaddAdapter(R.layout.message_add_item, userBeen);
                    mRvAdd.setAdapter(messageaddAdapter);
                } else {

                    Toast.makeText(MessageAddActivity.this, retmsg, Toast.LENGTH_SHORT).show();

                }












            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });



    }
}
