package com.zpfan.manzhu.bean;

/**
 * Created by Administrator on 2017/6/15 0015.
 */



public class AvatorBean {
    /**
     * moredata : false
     * pagename :
     * ret : true
     * retmsg : http://www.anipiggy.com/App_Themes/UI/image/default_avator.png
     */

    private boolean moredata;
    private String pagename;
    private boolean ret;
    private String retmsg;

    public boolean isMoredata() {
        return moredata;
    }

    public void setMoredata(boolean moredata) {
        this.moredata = moredata;
    }

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }
}
