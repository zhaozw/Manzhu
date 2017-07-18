package com.zpfan.manzhu.bean;

/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class MessageUserBean {
    private String userName;
    private String userAllLv;
    private String userAvator;
    private boolean isbussness;
    private String lastMessage;
    private Long lasetTiem;
    private int unred;
    private String myPhone;
    private String sendPhone;

    public String getMyPhone() {
        return myPhone;
    }

    public void setMyPhone(String myPhone) {
        this.myPhone = myPhone;
    }

    public String getSendPhone() {
        return sendPhone;
    }

    public void setSendPhone(String sendPhone) {
        this.sendPhone = sendPhone;
    }

    public MessageUserBean(String userName, String userAllLv, String userAvator, boolean isbussness, String lastMessage, Long lasetTiem, int unred) {
        this.userName = userName;
        this.userAllLv = userAllLv;
        this.userAvator = userAvator;
        this.isbussness = isbussness;
        this.lastMessage = lastMessage;
        this.lasetTiem = lasetTiem;
        this.unred = unred;
    }

    public MessageUserBean(String userName, String userAllLv, String userAvator, boolean isbussness) {
        this.userName = userName;
        this.userAllLv = userAllLv;
        this.userAvator = userAvator;
        this.isbussness = isbussness;
    }

    public MessageUserBean(String userName, String userAllLv, String userAvator, boolean isbussness, String myPhone) {
        this.userName = userName;
        this.userAllLv = userAllLv;
        this.userAvator = userAvator;
        this.isbussness = isbussness;
        this.myPhone = myPhone;
    }

    public MessageUserBean() {
    }

    @Override
    public String toString() {
        return "MessageUserBean{" +
                "userName='" + userName + '\'' +
                ", userAllLv='" + userAllLv + '\'' +
                ", userAvator='" + userAvator + '\'' +
                ", isbussness=" + isbussness +
                ", lastMessage='" + lastMessage + '\'' +
                ", lasetTiem='" + lasetTiem + '\'' +
                ", unred=" + unred +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAllLv() {
        return userAllLv;
    }

    public void setUserAllLv(String userAllLv) {
        this.userAllLv = userAllLv;
    }

    public String getUserAvator() {
        return userAvator;
    }

    public void setUserAvator(String userAvator) {
        this.userAvator = userAvator;
    }

    public boolean isbussness() {
        return isbussness;
    }

    public void setIsbussness(boolean isbussness) {
        this.isbussness = isbussness;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Long getLasetTiem() {
        return lasetTiem;
    }

    public void setLasetTiem(Long lasetTiem) {
        this.lasetTiem = lasetTiem;
    }

    public int getUnred() {
        return unred;
    }

    public void setUnred(int unred) {
        this.unred = unred;
    }
}
