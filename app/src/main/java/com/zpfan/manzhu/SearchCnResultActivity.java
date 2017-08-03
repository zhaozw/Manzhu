package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.adapter.MessageaddAdapter;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.UserBean;
import com.zpfan.manzhu.myui.SearchTopLin;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchCnResultActivity extends AppCompatActivity {

    @BindView(R.id.rv_cn)
    RecyclerView mRvCn;
    @BindView(R.id.st_cn)
    SearchTopLin mStCn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cn_result);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        mRvCn.setLayoutManager(new LinearLayoutManager(SearchCnResultActivity.this));

        TextView id = (TextView) mStCn.findViewById(R.id.tv_searchkeyword);
        id.setText(" | " + key);
        searchCn(key);
    }

    private void searchCn(String key) {


        Call<String> seachuser = Aplication.mIinterface.seachuser("1", key);

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
                    mRvCn.setAdapter(messageaddAdapter);
                } else {

                    Toast.makeText(SearchCnResultActivity.this, retmsg, Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}
