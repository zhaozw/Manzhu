package com.zpfan.manzhu.bean;

/**
 * Created by Administrator on 2017/8/12 0012.
 */

public class ShareBean {

    private  int imgs;
    private  String  platname;
    private  String  parpamName = null;



    public String getParpamName() {
        return parpamName;
    }

    public void setParpamName(String parpamName) {
        this.parpamName = parpamName;
    }

    public ShareBean(int imgs, String platname, String parpamName) {
        this.imgs = imgs;
        this.platname = platname;
        this.parpamName = parpamName;
    }

    public ShareBean(int imgs, String platname) {
        this.imgs = imgs;
        this.platname = platname;
    }

    public String getPlatname() {
        return platname;
    }

    public void setPlatname(String platname) {
        this.platname = platname;
    }

    public int getImgs() {
        return imgs;
    }

    public void setImgs(int imgs) {
        this.imgs = imgs;
    }
}
