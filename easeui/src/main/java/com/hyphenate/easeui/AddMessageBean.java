package com.hyphenate.easeui;

/**
 * Created by Administrator on 2017/6/29 0029.
 */

public class AddMessageBean {
    /**
     * CL_ChatLog : txt:"..."
     * CL_ChatTime : 2017-06-29 16:35:07
     * CL_ChatlogType : txt
     * CL_IsRead : false
     * CL_OBJ_A_UID : 19999999999
     * CL_OBJ_B_UID : 16666666666
     * id : 341
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
