package com.zpfan.manzhu.event;

/**
 * Created by Administrator on 2017/7/27 0027.
 */

public class DeleteGoodEvent  {
    String scuid;

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
