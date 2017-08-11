package com.zpfan.manzhu.bean;

/**
 * Created by Administrator on 2017/8/9 0009.
 */

public class ChangeBean {

    //-id|角色封面|类型|角色名|动漫名|介绍
    private String id;
    private String cover;
    private String type;
    private String character;
    private String anime;
    private String present;
    private boolean ischeck = false;

    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public ChangeBean() {
    }

    public ChangeBean(String id, String cover, String type, String character, String anime, String present) {
        this.id = id;
        this.cover = cover;
        this.type = type;
        this.character = character;
        this.anime = anime;
        this.present = present;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getAnime() {
        return anime;
    }

    public void setAnime(String anime) {
        this.anime = anime;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }
}
