package com.zpfan.manzhu.bean;

/**
 * Created by Administrator on 2017/6/9 0009.
 */

public class LoginBean {


    @Override
    public String toString() {
        return "LoginBean{" +
                "moredata=" + moredata +
                ", pagename='" + pagename + '\'' +
                ", ret=" + ret +
                ", retmsg='" + retmsg + '\'' +
                '}';
    }

    /**
     * 用户登陆后拿到的信息
     * moredata : false
     * pagename :
     * ret : true
     * retmsg : 134081
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
