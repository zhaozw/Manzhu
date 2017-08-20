package com.zpfan.manzhu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.adapter.CosLocationAdapter;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.CosLocationBean;
import com.zpfan.manzhu.myui.IconTopLin;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CosLocationActivity extends AppCompatActivity {

    @BindView(R.id.rv_coslocation)
    RecyclerView mRvCoslocation;
    @BindView(R.id.icontoplin)
    IconTopLin mIcontoplin;
    private View mCoslhead;
    private ImageView mIvlgf;
    private ImageView mIvnjd;
    private ImageView mIvwjd;
    private ArrayList<CosLocationBean> mCoslocationlist;
    private CosLocationAdapter mCosLocationAdapter;
    private LinkedHashMap<String, String> mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cos_location);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mRvCoslocation.setLayoutManager(new LinearLayoutManager(this));
        mCoslhead = View.inflate(this, R.layout.head_coslocation, null);
        mIvlgf = (ImageView) mCoslhead.findViewById(R.id.iv_lgf); //练功房
        mIvnjd = (ImageView) mCoslhead.findViewById(R.id.iv_njd); //内景地
        mIvwjd = (ImageView) mCoslhead.findViewById(R.id.iv_wjd); //外景地
        mCoslocationlist = new ArrayList<>();
        mCosLocationAdapter = new CosLocationAdapter(R.layout.item_coslocation, mCoslocationlist);
        mCosLocationAdapter.bindToRecyclerView(mRvCoslocation);
        mCosLocationAdapter.setEmptyView(R.layout.rv_emptyview);
        mCosLocationAdapter.setHeaderAndEmpty(true);
        mCosLocationAdapter.isUseEmpty(false);
        mCosLocationAdapter.addHeaderView(mCoslhead);
        mRvCoslocation.setAdapter(mCosLocationAdapter);

        mMap = new LinkedHashMap<>();
        mMap.put("Page","1");
        mMap.put("search_name","");
        mMap.put("sort_value","");
        mMap.put("is_localhost","");
        mMap.put("is_free","");
        mMap.put("is_cos","");
        mMap.put("l_type","");
        mMap.put("now_province_value","");

        getCoslocationList();



    }

    private void getCoslocationList() {
        Call<String> list = Aplication.mIinterface.getCoslocationList(mMap);

        list.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();

                if (body != null) {
                    AvatorBean result = Utils.getResult(body);
                    String retmsg = result.getRetmsg();
                    if (retmsg.contains("[")) {
                        String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));
                        Type type = new TypeToken<ArrayList<CosLocationBean>>() {
                        }.getType();

                        mCoslocationlist = Utils.gson.fromJson(substring, type);
                        mCosLocationAdapter.setNewData(mCoslocationlist);

                    } else {
                        mCoslocationlist.clear();
                        mCosLocationAdapter.isUseEmpty(true);
                        mCosLocationAdapter.notifyDataSetChanged();
                    }


                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });



    }
}
