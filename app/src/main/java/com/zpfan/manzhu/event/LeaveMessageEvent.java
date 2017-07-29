package com.zpfan.manzhu.event;

/**
 * Created by Administrator on 2017/7/29 0029.
 */

public class LeaveMessageEvent {
    String messagae ;
    public String bussness;

    public String getBussness() {
        return bussness;
    }

    public void setBussness(String bussness) {
        this.bussness = bussness;
    }

    public LeaveMessageEvent(String messagae) {
        this.messagae = messagae;
    }

    public String getMessagae() {
        return messagae;
    }

    public void setMessagae(String messagae) {
        this.messagae = messagae;
    }
}
