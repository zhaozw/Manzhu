package com.zpfan.manzhu.bean;

/**
 * Created by Administrator on 2017/8/10 0010.
 */

public class ShopListFilterBean {
    private String filtername;
    private String filterparm;
    private boolean ischeck;

    public ShopListFilterBean() {
    }

    public String getFilterparm() {
        return filterparm;
    }

    public void setFilterparm(String filterparm) {
        this.filterparm = filterparm;
    }

    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getFiltername() {
        return filtername;
    }

    public void setFiltername(String filtername) {
        this.filtername = filtername;
    }

    public ShopListFilterBean(String filtername, String filterparm, boolean ischeck) {
        this.filtername = filtername;
        this.filterparm = filterparm;
        this.ischeck = ischeck;
    }
}
