package com.zpfan.manzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.zpfan.manzhu.adapter.IdelAdapter;
import com.zpfan.manzhu.adapter.IdelpopAdapter;
import com.zpfan.manzhu.adapter.IdleFilterAdapter;
import com.zpfan.manzhu.adapter.NewAdapter;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.BussnessBean;
import com.zpfan.manzhu.bean.FilterBean;
import com.zpfan.manzhu.myui.SearchTopLin;
import com.zpfan.manzhu.utils.SPUtils;
import com.zpfan.manzhu.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hyphenate.easeui.model.EaseDefaultEmojiconDatas.getData;


/**
 * 搜索结果的展示界面。。。。
 */
public class SearchResultsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.rv_product)
    RecyclerView mRvProduct;
    @BindView(R.id.st_product)
    SearchTopLin mStProduct;
    @BindView(R.id.tv_notfind)
    TextView mTvNotfind;
    private String mKey;
    private HashMap<String, String> mMap;
    private ArrayList<BussnessBean> mBussnessBeen;
    private NewAdapter mNewAdapter;
    private IdelAdapter mIdleAdapter;
    private PopupWindow mPopupWindow;
    private RecyclerView mRvpop;
    private TextView mTvpaixu;
    private LinearLayout mOrde;
    private LinearLayout mFilter;
    private ImageView mIvorder;
    private ImageView mIvfilter;
    private TextView mTvorder;
    private TextView mTvfilter;
    private RecyclerView mRvfilter;
    private LinearLayout mLlbutton;
    private Button mBtcancel;
    private Button mBtimport;
    private String mShaixuan;
    private ImageView mIvsearchtop1;
    private ImageView mIvsearchtop2;
    private TextView mTvsearchtop1;
    private TextView mTvsearchtop2;
    private TextView mTvsearchkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);

        initView();

    }

    private void initView() {

        Intent intent = getIntent();
        mKey = intent.getStringExtra("key");
        LinearLayout llsearchtop1 = (LinearLayout) mStProduct.findViewById(R.id.ll_searchtop1);
        LinearLayout llsearchtop2 = (LinearLayout) mStProduct.findViewById(R.id.ll_searchtop2);
        LinearLayout llsearchtop3 = (LinearLayout) mStProduct.findViewById(R.id.ll_searchtop3);
        mIvsearchtop1 = (ImageView) mStProduct.findViewById(R.id.iv_searchtop1);
        mIvsearchtop2 = (ImageView) mStProduct.findViewById(R.id.iv_searchtop2);
        mTvsearchtop1 = (TextView) mStProduct.findViewById(R.id.tv_searchtop1);
        mTvsearchtop2 = (TextView) mStProduct.findViewById(R.id.tv_searchtop2);
        mTvsearchkey = (TextView) mStProduct.findViewById(R.id.tv_searchkeyword);
        mTvsearchkey.setText(mKey);

        mRvProduct.setLayoutManager(new LinearLayoutManager(SearchResultsActivity.this));
        llsearchtop1.setOnClickListener(this);
        llsearchtop2.setOnClickListener(this);
        llsearchtop3.setOnClickListener(this);

        mBussnessBeen = new ArrayList<>();


        mMap = new HashMap<>();
        mMap.put("page", "1");
        mMap.put("G_TYPE", "二手商品");
        mMap.put("TYPE_ID", "");
        mMap.put("TYPE_CHILD_ID", "");
        mMap.put("KEYWORD", mKey);
        mMap.put("BRANDID", "");
        mMap.put("price_filter", "");
        mMap.put("time_filter", "");
        mMap.put("new_filter", "");
        mMap.put("professionaldegree_filter", "");
        mMap.put("is_now", "");
        mMap.put("is_sale", "");
        mMap.put("is_rent", "");
        mMap.put("is_change", "");
        mMap.put("ser_no_money", "");
        mMap.put("is_package", "");
        mMap.put("sort_type", "");
        mMap.put("ROLE_WORK_TYPE", "");
        mMap.put("ROLE_WORK_ID", "");
        mMap.put("now_province", "");
        mMap.put("is_deposit", "");

        getProductData();


    }


    /**
     * 获取商品信息的方法
     */
    private void getProductData() {
        Call<String> getgoodslist = Aplication.mIinterface.getgoodslist(mMap);
        getgoodslist.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                if (body != null) {

                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> been = Utils.gson.fromJson(body, type);
                    if (been != null) {
                        AvatorBean bean = been.get(0);
                        mBussnessBeen.clear();
                        String retmsg = bean.getRetmsg();
                        if (retmsg.contains("[")) {
                            mRvProduct.setVisibility(View.VISIBLE);
                            mTvNotfind.setVisibility(View.GONE);
                            String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));
                            if (substring != null) {
                                Type type1 = new TypeToken<ArrayList<BussnessBean>>() {
                                }.getType();
                                mBussnessBeen = Utils.gson.fromJson(substring, type1);
                                if (mMap.get("G_TYPE").equals("二手商品")) {
                                    // 去加载二手商品

                                    mIdleAdapter = new IdelAdapter(R.layout.item_idel, mBussnessBeen);
                                    mRvProduct.setAdapter(mIdleAdapter);


                                } else if (mMap.get("G_TYPE").equals("新商品")) {
                                    //就去加载新商品


                                    mNewAdapter = new NewAdapter(R.layout.item_new, mBussnessBeen);
                                    mRvProduct.setAdapter(mNewAdapter);

                                }


                            }
                        } else {

                            mRvProduct.setVisibility(View.GONE);
                            mTvNotfind.setVisibility(View.VISIBLE);
                        }


                    }

                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_searchtop1:
                // 闲置二手的商品数据的加载
                mMap.put("G_TYPE", "二手商品");
                mIvsearchtop1.setImageResource(R.mipmap.com_icon_sh);
                mTvsearchtop1.setTextColor(getResources().getColor(R.color.maintextcolor));
                mIvsearchtop2.setImageResource(R.mipmap.com_icon_new_prd_ept);
                mTvsearchtop2.setTextColor(getResources().getColor(R.color.secondtextcolor));
                getProductData();
                break;

            case R.id.ll_searchtop2:
                //新商品的数据的加载

                mMap.put("G_TYPE", "新商品");
                mIvsearchtop1.setImageResource(R.mipmap.com_icon_sh_ept);
                mTvsearchtop1.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvsearchtop2.setImageResource(R.mipmap.com_icon_new_prd);
                mTvsearchtop2.setTextColor(getResources().getColor(R.color.maintextcolor));


                getProductData();
                break;

            case R.id.ll_searchtop3:
                //筛选排序的数据的加载

                mPopupWindow = new PopupWindow(SearchResultsActivity.this);
                View inflate = View.inflate(SearchResultsActivity.this, R.layout.idle_pop, null);
                initPopView(inflate);
                WindowManager.LayoutParams lp = SearchResultsActivity.this.getWindow()
                        .getAttributes();
                lp.alpha = 0.4f;
                SearchResultsActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                SearchResultsActivity.this.getWindow().setAttributes(lp);

                mShaixuan = SPUtils.getInstance().getString("paixu","全部");
                mOrde.setOnClickListener(this);
                mFilter.setOnClickListener(this);
                mTvpaixu.setText("当前排序：" + mShaixuan);
                mPopupWindow.setContentView(inflate);
                mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.home_toppop_bg));
                mPopupWindow.setTouchable(true);
                mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                mPopupWindow.setOutsideTouchable(true);
                initOrderRv(mPopupWindow);
                initFilterRv(mPopupWindow);
                mPopupWindow.update();
                mPopupWindow.showAtLocation(mStProduct, Gravity.NO_GRAVITY,0,0);
                mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = SearchResultsActivity.this.getWindow()
                                .getAttributes();
                        lp.alpha = 1f;
                        SearchResultsActivity.this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                        SearchResultsActivity.this.getWindow().setAttributes(lp);

                    }
                });





                break;

            case R.id.ll_order:
                //切换到排序的界面
                mIvorder.setImageResource(R.mipmap.com_icon_order);
                mTvorder.setTextColor(getResources().getColor(R.color.maintextcolor));
                mIvfilter.setImageResource(R.mipmap.com_icon_screen_ept);
                mTvfilter.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mTvpaixu.setText("当前排序：" + mShaixuan);
                mRvpop.setVisibility(View.VISIBLE);
                mRvfilter.setVisibility(View.GONE);
                mLlbutton.setVisibility(View.GONE);
                break;

            case R.id.ll_filter:
                //切换到筛选的界面
                mTvpaixu.setText("当前筛选条件：");
                mIvorder.setImageResource(R.mipmap.com_icon_order_ept);
                mTvorder.setTextColor(getResources().getColor(R.color.secondtextcolor));
                mIvfilter.setImageResource(R.mipmap.com_icon_screen);
                mTvfilter.setTextColor(getResources().getColor(R.color.maintextcolor));
                mRvpop.setVisibility(View.GONE);
                mRvfilter.setVisibility(View.VISIBLE);
                mLlbutton.setVisibility(View.VISIBLE);

                break;


        }
    }


    private void initPopView(View inflate) {
        mRvpop = (RecyclerView) inflate.findViewById(R.id.rv_idlepop);
        mTvpaixu = (TextView) inflate.findViewById(R.id.tv_paixu);
        mOrde = (LinearLayout) inflate.findViewById(R.id.ll_order);
        mFilter = (LinearLayout) inflate.findViewById(R.id.ll_filter);
        mIvorder = (ImageView) inflate.findViewById(R.id.iv_order);
        mIvfilter = (ImageView) inflate.findViewById(R.id.iv_filter);
        mTvorder = (TextView) inflate.findViewById(R.id.tv_order);
        mTvfilter = (TextView) inflate.findViewById(R.id.tv_filter);
        mRvfilter = (RecyclerView) inflate.findViewById(R.id.rv_idlefilter);
        mLlbutton = (LinearLayout) inflate.findViewById(R.id.ll_button);
        mBtcancel = (Button)inflate.findViewById(R.id.bt_cancel);
        mBtimport = (Button) inflate.findViewById(R.id.bt_import);





        mBtcancel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()  ) {
                    case MotionEvent.ACTION_DOWN:
                        mBtcancel.setTextColor(getResources().getColor(R.color.buttontext));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mBtcancel.setTextColor(getResources().getColor(R.color.buttontext));
                        break;
                    case MotionEvent.ACTION_UP:
                        mBtcancel.setTextColor(getResources().getColor(R.color.secondbuttonbc));
                        break;

                }
                return false;
            }
        });

        mBtimport.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()  ) {
                    case MotionEvent.ACTION_DOWN:
                        mBtimport.setTextColor(getResources().getColor(R.color.buttontext));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mBtimport.setTextColor(getResources().getColor(R.color.buttontext));
                        break;
                    case MotionEvent.ACTION_UP:
                        mBtimport.setTextColor(getResources().getColor(R.color.maintextcolor));
                        break;

                }
                return false;
            }
        });




    }

    private void initOrderRv(final PopupWindow window) {
        mRvpop.setLayoutManager(new LinearLayoutManager(this));
        final ArrayList<String> strings = new ArrayList<>();
        strings.add("全部");
        strings.add("本地优先");
        strings.add("最新发布");
        strings.add("价格最低");
        strings.add("包邮优先");
        strings.add("卖家等级");
        strings.add("成色最新");

        final ArrayList<String> types = new ArrayList<>();
        types.add("all");
        types.add("localhost_v");
        types.add("newtime_v");
        types.add("pricelow_v");
        types.add("package_v");
        types.add("selllevel_v");
        types.add("colour_v");

        final IdelpopAdapter popadapter = new IdelpopAdapter(R.layout.item_location_popr, strings);
        popadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                popadapter.cleanCheck();
                popadapter.setCheck(position);
                SPUtils.getInstance().put("check",position);
                mTvpaixu.setText("当前排序：" + strings.get(position));
                SPUtils.getInstance().put("paixu",strings.get(position));
                //发送请求
                mMap.put("sort_type", types.get(position));
                getData();

                window.dismiss();
            }
        });

        mRvpop.setAdapter(popadapter);

    }
    private void initFilterRv(final PopupWindow window) {
        mRvfilter.setLayoutManager(new LinearLayoutManager(SearchResultsActivity.this));
        ArrayList<FilterBean> filters = new ArrayList<>();
        LinkedHashMap<String, String> brandMap = new LinkedHashMap<>();
        brandMap.put("全部", "");
        brandMap.put("主宰者", "5");
        filters.add(new FilterBean("品牌",brandMap,"BRANDID"));
        LinkedHashMap<String, String> priceMap = new LinkedHashMap<>();
        priceMap.put("全部", "");
        priceMap.put("50元以下", "0-50");
        priceMap.put("50-100元", "50-100");
        priceMap.put("100-200元", "100-200");
        priceMap.put("200-500元", "200-500");
        priceMap.put("500-1000元", "500-1000");
        priceMap.put("1000元以上", "1000-0");
        filters.add(new FilterBean("价格",priceMap,"price_filter"));
        LinkedHashMap<String, String> newMap = new LinkedHashMap<>();
        newMap.put("全部", "");
        newMap.put("9成新以上", "9");
        newMap.put("8成新以上", "8");
        newMap.put("7成新以上", "7");
        newMap.put("6成新以上", "6");
        newMap.put("5成新以上", "5");
        filters.add(new FilterBean("成色",newMap,"new_filter"));
        LinkedHashMap<String, String> timeMap = new LinkedHashMap<>();
        timeMap.put("全部", "");
        timeMap.put("1天以内", "1");
        timeMap.put("3天以内", "3");
        timeMap.put("1周以内", "7");
        timeMap.put("2周以内", "14");
        timeMap.put("3周以内", "21");
        timeMap.put("1月以内", "30");
        timeMap.put("3月以内", "90");

        filters.add(new FilterBean("发布时间",timeMap,"time_filter"));
        IdleFilterAdapter filterAdapter = new IdleFilterAdapter(R.layout.item_idle_pop, filters);

        mRvfilter.setAdapter(filterAdapter);
        mBtcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.getInstance().put("品牌",0);
                SPUtils.getInstance().put("价格",0);
                SPUtils.getInstance().put("成色",0);
                SPUtils.getInstance().put("发布时间",0);
                window.dismiss();
            }
        });

        mBtimport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String brandid = SPUtils.getInstance().getString("BRANDID", "");
                String pricefilter = SPUtils.getInstance().getString("price_filter", "");
                String newfilter = SPUtils.getInstance().getString("new_filter", "");
                String timefilter = SPUtils.getInstance().getString("time_filter", "");

                mMap.put("BRANDID", brandid);
                mMap.put("price_filter", pricefilter);
                mMap.put("new_filter", newfilter);
                mMap.put("time_filter", timefilter);
                Call<String> getgoodslist = Aplication.mIinterface.getgoodslist(mMap);
                getgoodslist.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {


                        String body = response.body();
                        if (body != null) {
                            Type type = new TypeToken<ArrayList<AvatorBean>>() {
                            }.getType();

                            ArrayList<AvatorBean> been = Utils.gson.fromJson(body, type);
                            if (been != null) {
                                String retmsg = been.get(0).getRetmsg();

                                if (retmsg.contains("未查询到")) {

                                    mBussnessBeen.clear();
                                    mRvProduct.setVisibility(View.GONE);
                                    mTvNotfind.setVisibility(View.VISIBLE);
                                } else if (retmsg.contains("[")){
                                    mRvProduct.setVisibility(View.VISIBLE);
                                    mTvNotfind.setVisibility(View.GONE);

                                    String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));

                                    if (substring != null) {

                                        Type type1 = new TypeToken<ArrayList<BussnessBean>>() {
                                        }.getType();

                                        mBussnessBeen = Utils.gson.fromJson(substring, type1);

                                        if (mMap.get("G_TYPE").equals("二手商品")) {

                                            mIdleAdapter.notifyDataSetChanged();

                                        } else if (mMap.get("G_TYPE").equals("新商品")) {

                                            mNewAdapter.setNewData(mBussnessBeen);
                                            mNewAdapter.notifyDataSetChanged();

                                        }

                                    }

                                }


                            }


                        }


                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });



                window.dismiss();


            }
        });





    }

}
