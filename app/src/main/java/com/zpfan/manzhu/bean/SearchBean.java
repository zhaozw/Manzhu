package com.zpfan.manzhu.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 */

public class SearchBean {
    private List<String> linkDataProperty;
    private List<String> linkDataWork;

    public List<String> getLinkDataProperty() {
        return linkDataProperty;
    }

    public void setLinkDataProperty(List<String> linkDataProperty) {
        this.linkDataProperty = linkDataProperty;
    }

    public List<String> getLinkDataWork() {
        return linkDataWork;
    }

    public void setLinkDataWork(List<String> linkDataWork) {
        this.linkDataWork = linkDataWork;
    }
}
