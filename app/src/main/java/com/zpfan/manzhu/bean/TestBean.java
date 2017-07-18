package com.zpfan.manzhu.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/12 0012.
 */

public class TestBean {

    /**
     * code : 110000
     * sheng : 11
     * di : 00
     * xian : 00
     * name : 北京市
     * level : 1
     */

    private String code;
    private String sheng;
    private String di;
    private String xian;
    private String name;
    private ArrayList<String> shi = new ArrayList<>();

    public ArrayList<String> getShi() {
        return shi;
    }

    public void setShi(ArrayList<String> shi) {
        this.shi = shi;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "code='" + code + '\'' +
                ", sheng='" + sheng + '\'' +
                ", di='" + di + '\'' +
                ", xian='" + xian + '\'' +
                ", name='" + name + '\'' +
                ", level=" + level +
                '}';
    }

    private int level;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng;
    }

    public String getDi() {
        return di;
    }

    public void setDi(String di) {
        this.di = di;
    }

    public String getXian() {
        return xian;
    }

    public void setXian(String xian) {
        this.xian = xian;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
