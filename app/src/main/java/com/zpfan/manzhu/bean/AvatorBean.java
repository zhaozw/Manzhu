package com.zpfan.manzhu.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/6/15 0015.
 */



public class AvatorBean implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.moredata ? (byte) 1 : (byte) 0);
        dest.writeString(this.pagename);
        dest.writeByte(this.ret ? (byte) 1 : (byte) 0);
        dest.writeString(this.retmsg);
    }

    public AvatorBean() {
    }

    protected AvatorBean(Parcel in) {
        this.moredata = in.readByte() != 0;
        this.pagename = in.readString();
        this.ret = in.readByte() != 0;
        this.retmsg = in.readString();
    }

    public static final Parcelable.Creator<AvatorBean> CREATOR = new Parcelable.Creator<AvatorBean>() {
        @Override
        public AvatorBean createFromParcel(Parcel source) {
            return new AvatorBean(source);
        }

        @Override
        public AvatorBean[] newArray(int size) {
            return new AvatorBean[size];
        }
    };
}
