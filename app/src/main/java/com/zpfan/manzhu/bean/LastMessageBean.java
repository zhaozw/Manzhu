package com.zpfan.manzhu.bean;

/**
 * Created by Administrator on 2017/6/28 0028.
 */

public class LastMessageBean {
    /**
     * CL_ChatLog : aasdasd
     * CL_ChatTime : /Date(1498629924730+0800)/
     * CL_ChatlogType : txt
     * CL_IsRead : false
     * CL_OBJ_A_UID : 15555555555
     * CL_OBJ_B_UID : 15029498047
     * id : 136
     */

    private String CL_ChatLog;
    private String CL_ChatTime;
    private String CL_ChatlogType;
    private boolean CL_IsRead;
    private String CL_OBJ_A_UID;
    private String CL_OBJ_B_UID;
    private int id;

    public String getCL_ChatLog() {
        return CL_ChatLog;
    }

    public void setCL_ChatLog(String CL_ChatLog) {
        this.CL_ChatLog = CL_ChatLog;
    }

    public String getCL_ChatTime() {
        return CL_ChatTime;
    }

    public void setCL_ChatTime(String CL_ChatTime) {
        this.CL_ChatTime = CL_ChatTime;
    }

    public String getCL_ChatlogType() {
        return CL_ChatlogType;
    }

    public void setCL_ChatlogType(String CL_ChatlogType) {
        this.CL_ChatlogType = CL_ChatlogType;
    }

    public boolean isCL_IsRead() {
        return CL_IsRead;
    }

    public void setCL_IsRead(boolean CL_IsRead) {
        this.CL_IsRead = CL_IsRead;
    }

    public String getCL_OBJ_A_UID() {
        return CL_OBJ_A_UID;
    }

    public void setCL_OBJ_A_UID(String CL_OBJ_A_UID) {
        this.CL_OBJ_A_UID = CL_OBJ_A_UID;
    }

    public String getCL_OBJ_B_UID() {
        return CL_OBJ_B_UID;
    }

    public void setCL_OBJ_B_UID(String CL_OBJ_B_UID) {
        this.CL_OBJ_B_UID = CL_OBJ_B_UID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
