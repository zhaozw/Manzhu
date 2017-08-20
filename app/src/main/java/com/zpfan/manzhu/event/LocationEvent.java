package com.zpfan.manzhu.event;

/**
 * Created by Administrator on 2017/8/19 0019.
 */

public class LocationEvent {
    private String locatinopr;
    private String locatinocity;


    public String getLocatinocity() {
        return locatinocity;
    }

    public void setLocatinocity(String locatinocity) {
        this.locatinocity = locatinocity;
    }

    public String getLocatinopr() {

        return locatinopr;
    }

    public void setLocatinopr(String locatinopr) {
        this.locatinopr = locatinopr;
    }


    public LocationEvent(String locatinopr, String locatinocity) {
        this.locatinopr = locatinopr;
        this.locatinocity = locatinocity;
    }

    public LocationEvent(String locatinocity) {
        this.locatinocity = locatinocity;
    }

    public LocationEvent() {
    }
}
