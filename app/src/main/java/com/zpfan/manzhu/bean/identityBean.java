package com.zpfan.manzhu.bean;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class identityBean {
    private String  ident;
    private String  zhunye;
    private String  sudu;
    private String  taidu;


    public identityBean() {
    }

    public identityBean(String ident, String zhunye, String sudu, String taidu) {
        this.ident = ident;
        this.zhunye = zhunye;
        this.sudu = sudu;
        this.taidu = taidu;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getZhunye() {
        return zhunye;
    }

    public void setZhunye(String zhunye) {
        this.zhunye = zhunye;
    }

    public String getSudu() {
        return sudu;
    }

    public void setSudu(String sudu) {
        this.sudu = sudu;
    }

    public String getTaidu() {
        return taidu;
    }

    public void setTaidu(String taidu) {
        this.taidu = taidu;
    }
}
