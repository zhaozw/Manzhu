package com.zpfan.manzhu.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zpfan.manzhu.R;
import com.zpfan.manzhu.bean.FilterBean;
import com.zpfan.manzhu.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class IdleFilterAdapter extends BaseQuickAdapter<FilterBean,BaseViewHolder> {
    public IdleFilterAdapter(@LayoutRes int layoutResId, @Nullable List<FilterBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, final FilterBean item) {
        helper.setText(R.id.tv_tiaojian, item.getCondition()); //设置条件
        final TagFlowLayout tag = helper.getView(R.id.flowlayout);
        Set<String> strings = item.getFilter().keySet();
        final ArrayList<String> tagname = new ArrayList<>();
        final ArrayList<String> filter = new ArrayList<>();
        for (String string : strings) {
            tagname.add(string);
            filter.add(item.getFilter().get(string));
        }

        TagAdapter adapter = new TagAdapter(tagname) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {

                TextView tv = (TextView) mLayoutInflater.inflate(R.layout.tv, tag, false);
                tv.setText(tagname.get(position));
                return tv;
            }
        };
        tag.setAdapter(adapter);
        int anInt = SPUtils.getInstance().getInt(item.getCondition(),0);
        adapter.setSelectedList(anInt);
        tag.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                for (Integer integer : selectPosSet) {
                    SPUtils.getInstance().put(item.getFilte(),filter.get(integer)); //发送的参数的保存
                    SPUtils.getInstance().put(item.getCondition(),integer);  //获取发送的参数
                    Log.i("zc", "onSelected:   看看发送的字段和值" + item.getFilte()+ "========" + filter.get(integer));
                }

            }
        });



    }






}
