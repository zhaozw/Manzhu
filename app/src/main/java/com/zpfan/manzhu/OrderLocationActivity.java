package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zpfan.manzhu.adapter.OrderLocationAdapter;
import com.zpfan.manzhu.bean.AddressBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderLocationActivity extends AppCompatActivity {


    @BindView(R.id.iv_top_back)
    ImageView mIvTopBack;
    @BindView(R.id.tv_top_text)
    TextView mTvTopText;
    @BindView(R.id.iv_clearall)
    ImageView mIvClearall;
    @BindView(R.id.tv_clearall)
    TextView mTvClearall;
    @BindView(R.id.tv_moren)
    TextView mTvMoren;
    @BindView(R.id.tv_mophone)
    TextView mTvMophone;
    @BindView(R.id.tv_molocation)
    TextView mTvMolocation;
    @BindView(R.id.imageView2)
    ImageView mImageView2;
    @BindView(R.id.rv_orderloctaion)
    RecyclerView mRvOrderloctaion;
    @BindView(R.id.ll_defult)
    LinearLayout mLlDefult;

    private ArrayList<AddressBean> other = new ArrayList<>();
    private AddressBean defult = new AddressBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_location);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        final Intent intent = getIntent();
        ArrayList<AddressBean> locations = intent.getParcelableArrayListExtra("location");

        for (AddressBean location : locations) {
            if (location.isMD_IsDefault()) {
                defult = location;
                mTvMoren.setText(location.getMD_Name());
                mTvMophone.setText(location.getMD_Phone());
                mTvMolocation.setText(location.getMD_Province() + location.getMD_City() + location.getMD_Area() + location.getMD_Address());
                if (location.ischeck()) {
                    mImageView2.setVisibility(View.VISIBLE);
                } else {
                    mImageView2.setVisibility(View.VISIBLE);
                }
            } else {
                other.add(location);
            }
        }


        for (AddressBean bean : other) {
            if (bean.ischeck()) {
                mImageView2.setVisibility(View.INVISIBLE);
            }
        }

        mRvOrderloctaion.setLayoutManager(new LinearLayoutManager(this));
        OrderLocationAdapter adapter = new OrderLocationAdapter(R.layout.item_order_location, other);
        mRvOrderloctaion.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent1 = new Intent();

                AddressBean value = other.get(position);
                value.setIscheck(true);
                intent1.putExtra("location", value);
                OrderLocationActivity.this.setResult(1, intent1);

                finish();

            }
        });

    }


    @OnClick({R.id.iv_top_back, R.id.iv_clearall, R.id.tv_clearall,R.id.ll_defult})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_top_back:
                finish();
                break;
            case R.id.iv_clearall:


            case R.id.tv_clearall:
                //去管理地址的界面

                break;
            case R.id.ll_defult:
                Intent intent1 = new Intent();
                defult.setIscheck(true);
                intent1.putExtra("location", defult);
                OrderLocationActivity.this.setResult(1, intent1);
                finish();

                break;


        }
    }
}
