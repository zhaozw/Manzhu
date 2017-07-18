package com.zpfan.manzhu.bean;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/7/15 0015.
 */

public class FilterBean {
    private String condition ;  //条件
    private LinkedHashMap<String,String> filter ; //要发送的字段
    private String filte;

    public String getFilte() {
        return filte;
    }

    public FilterBean(String condition, LinkedHashMap<String, String> filter, String filte) {
        this.condition = condition;
        this.filter = filter;
        this.filte = filte;
    }

    public void setFilte(String filte) {
        this.filte = filte;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public LinkedHashMap<String, String> getFilter() {
        return filter;
    }

    public void setFilter(LinkedHashMap<String, String> filter) {
        this.filter = filter;
    }

    public FilterBean(String condition, LinkedHashMap<String, String> filter) {
        this.condition = condition;
        this.filter = filter;
    }
}
