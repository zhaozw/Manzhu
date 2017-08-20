package com.zpfan.manzhu;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zpfan.manzhu.adapter.CosAdapter;
import com.zpfan.manzhu.bean.CosBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

public class CosActivity extends AppCompatActivity {

    @BindView(R.id.iv_icontop_back)
    ImageView mIvIcontopBack;
    @BindView(R.id.iv_menu)
    ImageView mIvMenu;
    @BindView(R.id.tv_icontop_text)
    TextView mTvIcontopText;
    @BindView(R.id.iv_top1)
    ImageView mIvTop1;
    @BindView(R.id.tv_top1)
    TextView mTvTop1;
    @BindView(R.id.ll_top1)
    LinearLayout mLlTop1;
    @BindView(R.id.iv_top3)
    ImageView mIvTop3;
    @BindView(R.id.tv_top3)
    TextView mTvTop3;
    @BindView(R.id.ll_top3)
    LinearLayout mLlTop3;
    @BindView(R.id.iv_shaixuan)
    ImageView mIvShaixuan;
    @BindView(R.id.tv_shaixuan)
    TextView mTvShaixuan;
    @BindView(R.id.ll_top4)
    LinearLayout mLlTop4;
    @BindView(R.id.ll_topmenu)
    LinearLayout mLlTopmenu;
    @BindView(R.id.rv_coslist)
    RecyclerView mRvCoslist;
    private CosAdapter mAdapter;
    private View mCoslistheadview;
    private BGABanner mBpcoslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cos);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mRvCoslist.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<CosBean> coslist = new ArrayList<>();
        mAdapter = new CosAdapter(R.layout.item_cosshow, coslist);
        mCoslistheadview = View.inflate(CosActivity.this, R.layout.head_coslist, null);
        mBpcoslist = (BGABanner) mCoslistheadview.findViewById(R.id.bp_coslist);
        mAdapter.addHeaderView(mCoslistheadview);
        mAdapter.bindToRecyclerView(mRvCoslist);
        mAdapter.setEmptyView(R.layout.rv_emptyview);
        mAdapter.isUseEmpty(false);
        mAdapter.setHeaderAndEmpty(true);
        mRvCoslist.setAdapter(mAdapter);

        //对rv的滑动进行监听
        mRvCoslist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Rect rect = new Rect();
                mCoslistheadview.getWindowVisibleDisplayFrame(rect);
                if (rect.top == 0) {
                    Log.i("zc", "onScrolled:  看看数据 这是头部可见");
                } else {
                    Log.i("zc", "onScrolled:  蛰是头部不可见");
                }
            }
        });

        //对头部轮播图进行设置，
        mBpcoslist.setData(R.mipmap.s_i_home_02,R.mipmap.s_i_home_03);

        //发送请求去获取列表数据

        

    }
}
