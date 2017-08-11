package com.zpfan.manzhu;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.adapter.ShopListAdapter;
import com.zpfan.manzhu.adapter.ShopListFilterAdapter;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.ShopBean;
import com.zpfan.manzhu.bean.ShopListFilterBean;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopListActivity extends AppCompatActivity {

    @BindView(R.id.ll_topback)
    LinearLayout mLlTopback;
    @BindView(R.id.iv_allshop)
    ImageView mIvAllshop;
    @BindView(R.id.tv_allshop)
    TextView mTvAllshop;
    @BindView(R.id.ll_allshop)
    LinearLayout mLlAllshop;
    @BindView(R.id.iv_brandshop)
    ImageView mIvBrandshop;
    @BindView(R.id.tv_brandshop)
    TextView mTvBrandshop;
    @BindView(R.id.ll_brandshop)
    LinearLayout mLlBrandshop;
    @BindView(R.id.iv_filter)
    ImageView mIvFilter;
    @BindView(R.id.tv_filter)
    TextView mTvFilter;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;
    @BindView(R.id.ll_topmenu)
    LinearLayout mLlTopmenu;
    @BindView(R.id.rv_shoplist)
    RecyclerView mRvShoplist;
    @BindView(R.id.tv_noshop)
    TextView mTvNoshop;

    private ArrayList<ShopBean> mShopBeen;
    private View mHeadview;
    private String mCate;
    private ShopListAdapter mAdapter;
    private String busniess_shoptype = "0";
    private String sort_type = "";
    private ArrayList<ShopListFilterBean> mFilterBeen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

        mFilterBeen = new ArrayList<>();
        mFilterBeen.add(new ShopListFilterBean("默认", "", true));
        mFilterBeen.add(new ShopListFilterBean("本城市优先", "city", false));
        mFilterBeen.add(new ShopListFilterBean("卖家等级优先", "level", false));
        mFilterBeen.add(new ShopListFilterBean("总销量优先", "sellnumber", false));
        mFilterBeen.add(new ShopListFilterBean("纠纷比例最少", "jiufen", false));


        mHeadview = View.inflate(this, R.layout.head_shoplist, null);
        initHeadView();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRvShoplist.setLayoutManager(manager);
        Intent intent = getIntent();
        mCate = intent.getStringExtra("busniess_cate");
        mAdapter = new ShopListAdapter(R.layout.item_shop, mShopBeen);
        mAdapter.addHeaderView(mHeadview);
        mRvShoplist.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent shopDetial = new Intent(ShopListActivity.this, ShopDetialActivity.class);
                shopDetial.putExtra("shopbean", mShopBeen.get(position));
                startActivity(shopDetial);



            }
        });
        getShoplist();
        mRvShoplist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Rect rect = new Rect();
                mHeadview.getWindowVisibleDisplayFrame(rect);
                if (rect.top == 0) {
                    //头布局 不可见的情况下
                    mLlTopmenu.setVisibility(View.VISIBLE);
                    ViewGroup.LayoutParams params = mLlTopmenu.getLayoutParams();
                    int i = Utils.dp2px(40);
                    params.height = i;
                    mLlTopmenu.setLayoutParams(params);

                } else {
                    //头布局可见的情况下
                    ViewGroup.LayoutParams params = mLlTopmenu.getLayoutParams();
                    params.height = 0;
                    mLlTopmenu.setLayoutParams(params);
                }




            }
        });


    }

    private void initHeadView() {
        LinearLayout ll_back = (LinearLayout) mHeadview.findViewById(R.id.ll_back);

        ImageView ivfz = (ImageView) mHeadview.findViewById(R.id.iv_fz);
        ImageView ivdj = (ImageView) mHeadview.findViewById(R.id.iv_dj);
        ImageView ivsy = (ImageView) mHeadview.findViewById(R.id.iv_sy);
        ImageView ivhq = (ImageView) mHeadview.findViewById(R.id.iv_hq);
        ImageView ivzb = (ImageView) mHeadview.findViewById(R.id.iv_zb);
        ImageView ivjf = (ImageView) mHeadview.findViewById(R.id.iv_jf);

        ivfz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCate = "服装工作室";
                getShoplist();
            }
        });

        ivdj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCate = "道具工作室";
                getShoplist();
            }
        });
        ivhq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCate = "后期工作室";
                getShoplist();
            }
        });
        ivzb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCate = "动漫周边";
                getShoplist();
            }
        });
        ivjf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCate = "假发代理";
                getShoplist();
            }
        });

        ivsy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCate = "摄影工作室";
                getShoplist();
            }
        });

    ll_back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });

    }

    private void getShoplist() {

        Call<String> getshoplist = Aplication.mIinterface.getshoplist("1", mCate, busniess_shoptype, "", "", "", "",sort_type);

        getshoplist.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
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

                            Type type1 = new TypeToken<ArrayList<ShopBean>>() {
                            }.getType();
                            mShopBeen = Utils.gson.fromJson(substring, type1);

                            mAdapter.setNewData(mShopBeen);
                        } else if (retmsg.equals("未查询到相关店铺信息！")) {
                            mRvShoplist.setVisibility(View.GONE);
                            mTvNoshop.setVisibility(View.VISIBLE);
                        }


                    }


                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }


    @OnClick({R.id.ll_topback, R.id.ll_allshop, R.id.ll_brandshop, R.id.ll_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_topback:
                finish();
                break;
            case R.id.ll_allshop:
                //全部商家
                selecttag(0);
                mCate = "";
                busniess_shoptype = "0";
                getShoplist();
                break;
            case R.id.ll_brandshop:
                //品牌商家
                busniess_shoptype = "2";
                getShoplist();
                selecttag(1);

                break;
            case R.id.ll_filter:
                //筛选排序
                selecttag(2);
                final PopupWindow  mPopupWindow = new PopupWindow(ShopListActivity.this);
                View inflate = View.inflate(ShopListActivity.this, R.layout.shoplist_pop, null);
                RecyclerView rvfilter = (RecyclerView) inflate.findViewById(R.id.rv_idlepop);
                rvfilter.setLayoutManager(new LinearLayoutManager(ShopListActivity.this));
                ShopListFilterAdapter filterAdapter = new ShopListFilterAdapter(R.layout.item_location_popr, mFilterBeen);
                rvfilter.setAdapter(filterAdapter);
                filterAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        for (ShopListFilterBean bean : mFilterBeen) {
                            bean.setIscheck(false);
                        }
                        ShopListFilterBean bean = mFilterBeen.get(position);
                        bean.setIscheck(true);
                        sort_type = bean.getFilterparm();

                        getShoplist();
                        mPopupWindow.dismiss();
                    }
                });

                WindowManager.LayoutParams lp = ShopListActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 0.4f;
                ShopListActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                ShopListActivity.this.getWindow().setAttributes(lp);

                mPopupWindow.setContentView(inflate);
                mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.home_toppop_bg));
                mPopupWindow.setTouchable(true);
                mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                mPopupWindow.setOutsideTouchable(true);

                mPopupWindow.update();
                mPopupWindow.showAtLocation(mLlFilter, Gravity.NO_GRAVITY,0,0);
                mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = ShopListActivity.this.getWindow()
                                .getAttributes();
                        lp.alpha = 1f;
                        ShopListActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        ShopListActivity.this.getWindow().setAttributes(lp);

                    }
                });

                break;

        }
    }

    private void selecttag(int i) {
        switch (i) {
            case 0:
                //选中了全部商品的效果
                mIvAllshop.setImageResource(R.mipmap.type_icon_shop_2);
                mTvAllshop.setTextColor(getResources().getColor(R.color.maintextcolor));
                mIvBrandshop.setImageResource(R.mipmap.type_icon_shop_2_elt_ept);
                mTvBrandshop.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvFilter.setImageResource(R.mipmap.com_icon_order_ept);
                mTvFilter.setTextColor(getResources().getColor(R.color.secondtextcolor));

                break;

            case 1:
                //选中了品牌商家的效果
                mIvAllshop.setImageResource(R.mipmap.type_icon_shop_2_ept);
                mTvAllshop.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvBrandshop.setImageResource(R.mipmap.type_icon_shop_2_elt);
                mTvBrandshop.setTextColor(getResources().getColor(R.color.maintextcolor));
                mIvFilter.setImageResource(R.mipmap.com_icon_order_ept);
                mTvFilter.setTextColor(getResources().getColor(R.color.secondtextcolor));



                break;
            case 2:
                //选中了筛选的效果

                mIvAllshop.setImageResource(R.mipmap.type_icon_shop_2_ept);
                mTvAllshop.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvBrandshop.setImageResource(R.mipmap.type_icon_shop_2_elt_ept);
                mTvBrandshop.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvFilter.setImageResource(R.mipmap.com_icon_order);
                mTvFilter.setTextColor(getResources().getColor(R.color.maintextcolor));

                break;



        }


    }
}
