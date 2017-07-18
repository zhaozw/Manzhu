package com.zpfan.manzhu.utils;

/**
 * Created by Administrator on 2017/6/28 0028.
 */

public class LastMessage {
    public  final String lastmessage;
    public final int unredcount;
    public final  Long lasttime;


    public String getLastmessage() {
        return lastmessage;
    }

    public int getUnredcount() {
        return unredcount;
    }

    public Long getLasttime() {
        return lasttime;
    }


    public LastMessage(String lastmessage, int unredcount, Long lasttime) {
        this.lastmessage = lastmessage;
        this.unredcount = unredcount;
        this.lasttime = lasttime;
    }

    @Override
    public String toString() {
        return "LastMessage{" +
                "lastmessage='" + lastmessage + '\'' +
                ", unredcount=" + unredcount +
                ", lasttime=" + lasttime +
                '}';
    }
}
