package com.zpfan.manzhu.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zpfan.manzhu.Aplication;
import com.zpfan.manzhu.IdleActivity;
import com.zpfan.manzhu.LaterActivity;
import com.zpfan.manzhu.LocationActivity;
import com.zpfan.manzhu.LoginActivity;
import com.zpfan.manzhu.MakeUpActivity;
import com.zpfan.manzhu.NewActivity;
import com.zpfan.manzhu.PhotoActivity;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.SearchCnResultActivity;
import com.zpfan.manzhu.SearchResultsActivity;
import com.zpfan.manzhu.ShopCarActivity;
import com.zpfan.manzhu.bean.AvatorBean;
import com.zpfan.manzhu.bean.SearchBean;
import com.zpfan.manzhu.bean.TypeBean;
import com.zpfan.manzhu.utils.MyScrollView;
import com.zpfan.manzhu.utils.ScrollViewListener;
import com.zpfan.manzhu.utils.Utils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.hyphenate.chat.EMGCMListenerService.TAG;
import static com.zpfan.manzhu.utils.Utils.dp2px;

/**
 * Created by Administrator on 2017/6/14 0014.
 */

public class HomeFragment extends Fragment implements BDLocationListener {
    private static final int REQUSET_LOCATION = 1;
    private static final int REQUEST_PRODUCT = 0;
    private static final int REQUEST_CN = 1;
    private static final int REQUEST_SERVICE = 2;
    @BindView(R.id.iv_location)
    ImageView mIvLocation;
    @BindView(R.id.tv_location)
    TextView mTvLocation;
    @BindView(R.id.iv_shopcart)
    ImageView mIvShopcart;
    @BindView(R.id.ll_hometop)
    LinearLayout mLlHometop;
    @BindView(R.id.tv_shopcart)
    TextView mTvShopcart;
    @BindView(R.id.tv_treasure)
    TextView mTvTreasure;
    @BindView(R.id.tv_role)
    TextView mTvRole;
    @BindView(R.id.tv_cn)
    TextView mTvCn;
    @BindView(R.id.tv_arrange)
    TextView mTvArrange;
    @BindView(R.id.ll_hometag)
    LinearLayout mLlHometag;
    @BindView(R.id.ed_seach)
    EditText mEdSeach;
    @BindView(R.id.iv_seach)
    ImageView mIvSeach;
    @BindView(R.id.ll_secondhand)
    LinearLayout mLlSecondhand;
    @BindView(R.id.ll_photograp)
    LinearLayout mLlPhotograp;
    @BindView(R.id.ll_makeup)
    LinearLayout mLlMakeup;
    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.ll_later)
    LinearLayout mLlLater;
    @BindView(R.id.ll_new)
    LinearLayout mLlNew;
    @BindView(R.id.ll_cos)
    LinearLayout mLlCos;
    @BindView(R.id.ll_toshoot)
    LinearLayout mLlToshoot;
    @BindView(R.id.ll_fans)
    LinearLayout mLlFans;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tv_allbussness)
    TextView mTvAllbussness;
    @BindView(R.id.ll_cloth)
    LinearLayout mLlCloth;
    @BindView(R.id.ll_props)
    LinearLayout mLlProps;
    @BindView(R.id.ll_wig)
    LinearLayout mLlWig;
    @BindView(R.id.ll_other)
    LinearLayout mLlOther;
    @BindView(R.id.ll_studio)
    LinearLayout mLlStudio;
    Unbinder unbinder;
    @BindView(R.id.rl_homeseach)
    RelativeLayout mRlHomeseach;
    @BindView(R.id.myscroll)
    MyScrollView mMyscroll;
    @BindView(R.id.tv_keyword)
    TextView mTvKeyword;
    @BindView(R.id.ed_topsearch)
    EditText mEdTopsearch;
    @BindView(R.id.iv_topsearch)
    ImageView mIvTopsearch;
    @BindView(R.id.ll_topsearch)
    LinearLayout mLlTopsearch;
    @BindView(R.id.tv_btshopcart)
    TextView mTvBtshopcart;
    @BindView(R.id.rl_shopcart)
    RelativeLayout mRlShopcart;
    @BindView(R.id.rl_up)
    RelativeLayout mRlUp;
    @BindView(R.id.ll_homeseach)
    LinearLayout mLlHomeseach;
    private View mView;
    public LocationClient mLocationClient = null;
    private LocationClientOption locationClientOption;
    private String mProvince;
    private PopupWindow mSearchwindow;
    private ImageView mIvclose;
    private View mContentView;
    private TagFlowLayout mTagFlowLayout;
    private TagAdapter mTagAdapter;
    private LayoutInflater mInflater;
    private ArrayList<String> taglist;
    private ArrayList<String> typelist;
    private ArrayList<String> typeidlist;

    private String typenumber = "2";
    private int typeid = REQUEST_PRODUCT;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, container, false);
        }
        mInflater = getLayoutInflater(savedInstanceState);
        taglist = new ArrayList<>();
        typeidlist = new ArrayList<>();
        typelist = new ArrayList<>();
        unbinder = ButterKnife.bind(this, mView);
        initView();
        initLBS();
        EventBus.getDefault().register(this);
        return mView;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void initper() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUSET_LOCATION);

            } else {

                mLocationClient.start();
            }

        } else {
            mLocationClient.start();
        }


    }

    @Override
    public void onResume() {
        mEdSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (typeid != REQUEST_SERVICE) {
                    mEdSeach.setFocusableInTouchMode(true);
                    mEdSeach.setFocusable(true);
                    mEdSeach.setEnabled(true);
                    mEdSeach.requestFocus();
                    realtimesearch(s);
                    Log.i("zc", "onTextChanged:   走了这里了吗");
                } else {
                    if (mSearchwindow != null && mSearchwindow.isShowing()) {
                        mSearchwindow.dismiss();
                    }
                    //设置不克可输入的状态
                    mEdSeach.setEnabled(false);
                    reservation();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mEdSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typeid == REQUEST_SERVICE) {
                    reservation();
                    mEdSeach.setText("");
                    mEdSeach.setEnabled(false);
                }
            }
        });


        super.onResume();
    }

    private void reservation() {
        //单独弹出约单的内容
        if (mSearchwindow == null) {
            mSearchwindow = new PopupWindow(getContext());
            mContentView = View.inflate(getContext(), R.layout.home_seach_pop, null);
            mIvclose = (ImageView) mContentView.findViewById(R.id.iv_close);
            mTagFlowLayout = (TagFlowLayout) mContentView.findViewById(R.id.flowlayout);
        }
        mIvclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchwindow.dismiss();
            }
        });
        mSearchwindow.setContentView(mContentView);
        mSearchwindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.home_toppop_bg));
        mSearchwindow.setTouchable(true);
        mSearchwindow.setWidth(mLlHomeseach.getWidth());
        mSearchwindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mSearchwindow.setOutsideTouchable(true);
        mSearchwindow.update();
        Call<String> getproducttype = Aplication.mIinterface.getproducttype("服务", "");
        getproducttype.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                if (body != null) {
                    final Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> been = Utils.gson.fromJson(body, type);
                    if (been.size() > 0) {
                        String retmsg = been.get(0).getRetmsg();
                        if (retmsg.contains("[") && retmsg.length() > 2) {
                            Type type1 = new TypeToken<ArrayList<TypeBean>>() {
                            }.getType();
                            String substring = retmsg.substring(1, retmsg.lastIndexOf("]"));
                            typelist.clear();
                            typeidlist.clear();
                            final ArrayList<TypeBean> been1 = Utils.gson.fromJson(substring, type1);
                            if (been1 != null) {
                                for (TypeBean bean : been1) {
                                    typelist.add(bean.getPT_Title());
                                    typeidlist.add(bean.getId()+"");
                                }


                                mTagAdapter = new TagAdapter(typelist) {
                                    @Override
                                    public View getView(FlowLayout parent, int position, Object o) {
                                        TextView tv = (TextView) mInflater.inflate(R.layout.home_search_tv, mTagFlowLayout, false);
                                        tv.setText(typelist.get(position));
                                        return tv;
                                    }
                                };
                                //标签的点击事件的监听
                                mTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                                    @Override
                                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                                        //直接发送搜索的字段 和 type 到 搜索结果的列表里面
                                        Intent intent = new Intent(getContext(), PhotoActivity.class);
                                        intent.putExtra("typetitle", been1.get(position).getPT_Title());
                                        intent.putExtra("type", "search");
                                        intent.putExtra("typeid", been1.get(position).getId());
                                        intent.putExtra("location", mProvince);

                                        startActivity(intent);
                                        return false;
                                    }
                                });
                                mTagFlowLayout.setAdapter(mTagAdapter);
                                mSearchwindow.showAsDropDown(mLlHomeseach);
                            }
                        }
                    }

                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });





    }

    private void realtimesearch(CharSequence s) {
        if (mSearchwindow == null) {
            Log.i(TAG, "realtimesearch:  创建了吗");
            mSearchwindow = new PopupWindow(getContext());
            mContentView = View.inflate(getContext(), R.layout.home_seach_pop, null);
            mIvclose = (ImageView) mContentView.findViewById(R.id.iv_close);
            mTagFlowLayout = (TagFlowLayout) mContentView.findViewById(R.id.flowlayout);
        }
        mIvclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchwindow.dismiss();
            }
        });

        mSearchwindow.setContentView(mContentView);
        mSearchwindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.home_toppop_bg));
        mSearchwindow.setTouchable(true);
        mSearchwindow.setWidth(mLlHomeseach.getWidth());
        mSearchwindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        mSearchwindow.setOutsideTouchable(true);
        mSearchwindow.update();




        Call<String> searchkeyword = Aplication.mIinterface.searchkeyword(typenumber, s.toString(), "");
        searchkeyword.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String body = response.body();
                if (body != null) {
                    Type type = new TypeToken<ArrayList<AvatorBean>>() {
                    }.getType();

                    ArrayList<AvatorBean> avatorBeen = Utils.gson.fromJson(body, type);
                    AvatorBean bean = avatorBeen.get(0);
                    if (bean != null) {
                        String retmsg = bean.getRetmsg();
                        if (retmsg.contains("[") && retmsg.length() >2) {
                            //去解析
                            Type type1 = new TypeToken<ArrayList<SearchBean>>() {
                            }.getType();

                            ArrayList<SearchBean> searchBeen = Utils.gson.fromJson(retmsg, type1);
                            taglist.clear();
                            for (SearchBean searchBean : searchBeen) {
                                final List<String> property = searchBean.getLinkDataProperty();
                                String s1 = property.get(0);
                                Log.i("zc", "onResponse:   看看数据" + s1);
                                taglist.add(s1);

                            }
                                mTagAdapter = new TagAdapter(taglist) {
                                    @Override
                                    public View getView(FlowLayout parent, int position, Object o) {
                                        TextView tv = (TextView) mInflater.inflate(R.layout.home_search_tv, mTagFlowLayout, false);
                                        tv.setText(taglist.get(position));
                                        return tv;
                                    }
                                };

                                mTagFlowLayout.setAdapter(mTagAdapter);
                            mTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                                @Override
                                public boolean onTagClick(View view, int position, FlowLayout parent) {
                                    String keyword = taglist.get(position);
                                    if (typenumber.equals("7")) {
                                        //说明是点击了搜索cn的按钮
                                        searchCn(keyword);
                                    }else if (typenumber.equals("2")) {
                                        //说明是点击了宝贝和角色的按钮
                                        String[] split = null;
                                        if (keyword.contains("-")) {
                                            split = keyword.split("-");
                                        }

                                        if (split != null) {
                                            searchProduct(split[0]);
                                        }


                                    }


                                    return false;
                                }
                            });



                            mSearchwindow.showAsDropDown(mLlHomeseach);
                        } else if (retmsg.contains("[") && retmsg.length() == 2) {

                            mSearchwindow.dismiss();

                        }


                    }

                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    /**
     *  搜索商品的方法 宝贝 和 角色
     * @param s
     */
    private void searchProduct(String s) {
        Intent prodcutIntent = new Intent(getContext(), SearchResultsActivity.class);
        prodcutIntent.putExtra("key", s);
        startActivity(prodcutIntent);

    }

    private void searchCn(String s) {
        //点击cn标签 跳转到cn 搜索界面的方法

        Intent cnintent = new Intent(getContext(), SearchCnResultActivity.class);
        cnintent.putExtra("key", s);
        startActivity(cnintent);



    }

    private void initLBS() {
        //初始化定位的方法

        mLocationClient = new LocationClient(Aplication.mContext);
        //声明LocationClient类

        mLocationClient.setLocOption(getDefaultLocationClientOption());
        mLocationClient.registerLocationListener(this);
        //注册监听函数
        mLocationClient.requestLocation();
        initper();
        String version = mLocationClient.getVersion();


    }

    private void initView() {
        ScrollViewListener listener = new ScrollViewListener() {
            @Override
            public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
                Rect rect = new Rect();
                boolean rect1 = mRlHomeseach.getGlobalVisibleRect(rect);
                if (rect1) {
                    showTopBottom();
                } else {
                    hideTopBottom();
                }

            }
        };

        mMyscroll.setListener(listener);


    }

    private void hideTopBottom() {
        mLlTopsearch.setVisibility(View.VISIBLE);
        mRlShopcart.setVisibility(View.VISIBLE);
        mRlUp.setVisibility(View.VISIBLE);
    }

    private void showTopBottom() {
        mLlTopsearch.setVisibility(View.GONE);
        mRlShopcart.setVisibility(View.INVISIBLE);
        mRlUp.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick({R.id.tv_location, R.id.iv_shopcart, R.id.tv_treasure, R.id.tv_role, R.id.tv_cn, R.id.tv_arrange, R.id.iv_seach, R.id.ll_secondhand, R.id.ll_photograp, R.id.ll_makeup, R.id.ll_later, R.id.ll_new, R.id.ll_cos, R.id.ll_toshoot, R.id.ll_fans, R.id.tv_allbussness, R.id.ll_cloth, R.id.ll_props, R.id.ll_wig, R.id.ll_other, R.id.ll_studio, R.id.tv_keyword, R.id.iv_topsearch, R.id.rl_shopcart, R.id.rl_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_location:
                //定位文字  跳转到我的位置界面
                Intent intent = new Intent(getContext(), LocationActivity.class);
                String location = mTvLocation.getText().toString();
                intent.putExtra("location", location);
                startActivity(intent);

                break;
            case R.id.iv_shopcart:
                //购物车的按钮
                if (Utils.isUserLogin()) {
                    startActivity(new Intent(getContext(), ShopCarActivity.class));
                } else {
                    startActivity(new Intent(getContext(),LoginActivity.class));
                }

                break;
            case R.id.tv_treasure:
                //搜宝贝的按钮  先切换背景 然后设置typenumber
                mEdSeach.setEnabled(true);
                mTvTreasure.setBackground(getResources().getDrawable(R.drawable.home_tg_bg));
                mTvRole.setBackground(getResources().getDrawable(R.drawable.home_tag1_bg));
                mTvCn.setBackground(getResources().getDrawable(R.drawable.home_tag1_bg));
                mTvArrange.setBackground(getResources().getDrawable(R.drawable.home_tag1_bg));

                typenumber = "2";
                typeid = REQUEST_PRODUCT;


                break;
            case R.id.tv_role:
                //搜角色的按钮
                mEdSeach.setEnabled(true);
                mTvTreasure.setBackground(getResources().getDrawable(R.drawable.home_tag1_bg));
                mTvRole.setBackground(getResources().getDrawable(R.drawable.home_tg_bg));
                mTvCn.setBackground(getResources().getDrawable(R.drawable.home_tag1_bg));
                mTvArrange.setBackground(getResources().getDrawable(R.drawable.home_tag1_bg));

                typenumber = "2";
                typeid = REQUEST_PRODUCT;
                break;
            case R.id.tv_cn:
                //搜cn的按钮
                mEdSeach.setEnabled(true);
                mTvTreasure.setBackground(getResources().getDrawable(R.drawable.home_tag1_bg));
                mTvRole.setBackground(getResources().getDrawable(R.drawable.home_tag1_bg));
                mTvCn.setBackground(getResources().getDrawable(R.drawable.home_tg_bg));
                mTvArrange.setBackground(getResources().getDrawable(R.drawable.home_tag1_bg));

                typenumber = "7";
                typeid = REQUEST_CN;
                break;
            case R.id.tv_arrange:
                // 搜约单的按钮
                mEdSeach.setEnabled(true);
                mTvTreasure.setBackground(getResources().getDrawable(R.drawable.home_tag1_bg));
                mTvRole.setBackground(getResources().getDrawable(R.drawable.home_tag1_bg));
                mTvCn.setBackground(getResources().getDrawable(R.drawable.home_tag1_bg));
                mTvArrange.setBackground(getResources().getDrawable(R.drawable.home_tg_bg));
                typeid = REQUEST_SERVICE;


                break;
            case R.id.iv_seach:
                // 搜索按钮   根据不同的type 去打开不同的界面
                if (typeid == REQUEST_PRODUCT) {
                    //搜宝贝和角色的界面
                    String s = mEdSeach.getText().toString();
                    searchProduct(s);
                } else if (typeid == REQUEST_CN) {
                    //搜cn的界面


                } else if (typeid == REQUEST_SERVICE) {
                    //搜约单的界面



                }







                break;
            case R.id.ll_secondhand:
                //闲置二手的按钮
                startActivity(new Intent(getContext(), IdleActivity.class));

                break;
            case R.id.ll_photograp:
                // 找摄影的按钮
                Intent photointent = new Intent(getContext(), PhotoActivity.class);
                if (mProvince != null) {
                    photointent.putExtra("location", mProvince);
                }

                startActivity(photointent);


                break;
            case R.id.ll_makeup:
                //找妆娘的按钮
                Intent makeupintent = new Intent(getContext(), MakeUpActivity.class);
                if (mProvince != null) {
                    makeupintent.putExtra("location", mProvince);
                }

                startActivity(makeupintent);

                break;
            case R.id.ll_later:
                //  找后期的按钮
                Intent laterintent = new Intent(getContext(), LaterActivity.class);
                if (mProvince != null) {
                    laterintent.putExtra("location", mProvince);
                }

                startActivity(laterintent);

                break;
            case R.id.ll_new:
                //cos新品的按钮
                startActivity(new Intent(getContext(), NewActivity.class));


                break;
            case R.id.ll_cos:
                //cos秀的按钮


                break;
            case R.id.ll_toshoot:
                //  cos拍摄地的按钮


                break;
            case R.id.ll_fans:
                //Fans社区的按钮


                break;
            case R.id.tv_allbussness:
                //所有精选商家的按钮


                break;
            case R.id.ll_cloth:
                //服装的按钮


                break;
            case R.id.ll_props:
                //道具的按钮


                break;
            case R.id.ll_wig:
                //假发的按钮

                break;
            case R.id.ll_other:
                //其它的按钮

                break;
            case R.id.ll_studio:
                //摄影工作室的按钮


                break;

            case R.id.tv_keyword:
                //顶部搜索框点击后 弹出一个pop
                final PopupWindow popupWindow = new PopupWindow(getContext());
                final View pop = View.inflate(getContext(), R.layout.home_pop1, null);
                LinearLayout llbaobei = (LinearLayout) pop.findViewById(R.id.ll_baobei);
                LinearLayout lljiaose = (LinearLayout) pop.findViewById(R.id.ll_jiaose);
                LinearLayout llcn = (LinearLayout) pop.findViewById(R.id.ll_cn);
                LinearLayout llyuedan = (LinearLayout) pop.findViewById(R.id.ll_yuedan);

                llbaobei.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTvKeyword.setText("宝贝");
                        popupWindow.dismiss();
                    }
                });
                lljiaose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTvKeyword.setText("角色");
                        popupWindow.dismiss();
                    }
                });
                llcn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTvKeyword.setText("CN");
                        popupWindow.dismiss();
                    }
                });
                llyuedan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTvKeyword.setText("约单");
                        popupWindow.dismiss();
                    }
                });


                popupWindow.setContentView(pop);
                int width = dp2px(85);
                int height = Utils.dp2px(140);
                popupWindow.setHeight(height);
                popupWindow.setWidth(width);
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);

                popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.home_toppop_bg));
                popupWindow.showAsDropDown(mTvKeyword);


                break;
            case R.id.iv_topsearch:
                //顶部搜索按钮


                break;


            case R.id.rl_shopcart:
                //底部的购物车的按钮
                if (Utils.isUserLogin()) {
                    startActivity(new Intent(getContext(), ShopCarActivity.class));
                } else {
                    startActivity(new Intent(getContext(),LoginActivity.class));
                }

                break;
            case R.id.rl_up:
                //滑动到顶部
                mMyscroll.fullScroll(ScrollView.FOCUS_UP);
                break;


        }
    }


    /***
     * 配置参数
     *
     * @return DefaultLocationClientOption
     */
    public LocationClientOption getDefaultLocationClientOption() {
        if (locationClientOption == null) {
            locationClientOption = new LocationClientOption();
            locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            locationClientOption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
            locationClientOption.setScanSpan(3000);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
            locationClientOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
            locationClientOption.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
            locationClientOption.setNeedDeviceDirect(true);//可选，设置是否需要设备方向结果
            locationClientOption.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            locationClientOption.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
            locationClientOption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
            locationClientOption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
            locationClientOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集

            locationClientOption.setIsNeedAltitude(false);//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        }
        return locationClientOption;
    }


    @Override
    public void onReceiveLocation(final BDLocation location) {
        //  Log.i("location", "onReceiveLocation:     看看回调的地址" + location.getCity());


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProvince = location.getProvince();
                Log.i("zc", "onReceiveLocation:   回调的省市" + mProvince);
                mTvLocation.setText(location.getCity());
            }
        });

    }

    @Override
    public void onConnectHotSpotMessage(String s, int i) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUSET_LOCATION) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mLocationClient.start();
            }

        }


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLocation(String city) {

        Log.i("location", "getLocation:    看看获取到的城市" + city);
        mTvLocation.setText(city);

    }


}
