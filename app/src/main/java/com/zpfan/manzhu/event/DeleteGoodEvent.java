package com.zpfan.manzhu.event;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class DeleteGoodEvent  {
    String scuid;
    String leixin;

    public DeleteGoodEvent(String scuid, String leixin) {
        this.scuid = scuid;
        this.leixin = leixin;
    }

    public String getLeixin() {
        return leixin;
    }

    public void setLeixin(String leixin) {
        this.leixin = leixin;
    }

    public DeleteGoodEvent(String s) {
        scuid = s;
    }


    public String getScuid() {
        return scuid;
    }

    public void setScuid(String scuid) {
        this.scuid = scuid;
    }
}
