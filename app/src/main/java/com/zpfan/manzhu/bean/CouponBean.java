package com.zpfan.manzhu.bean;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class CouponBean {

    @Override
    public String toString() {
        return "CouponBean{" +
                "DC_Cate='" + DC_Cate + '\'' +
                ", DC_CouconStatus='" + DC_CouconStatus + '\'' +
                ", DC_FailureTime='" + DC_FailureTime + '\'' +
                ", DC_IsLock=" + DC_IsLock +
                ", DC_MeetMoney=" + DC_MeetMoney +
                ", DC_Number=" + DC_Number +
                ", DC_OverdueDays=" + DC_OverdueDays +
                ", DC_PreferentialMoney='" + DC_PreferentialMoney + '\'' +
                ", DC_RemainingNumber=" + DC_RemainingNumber +
                ", DC_Remarks='" + DC_Remarks + '\'' +
                ", DC_ShortestRentDay=" + DC_ShortestRentDay +
                ", DC_Time='" + DC_Time + '\'' +
                ", Member_UID='" + Member_UID + '\'' +
                ", id=" + id +
                ", member_use_get_status='" + member_use_get_status + '\'' +
                '}';
    }

    /**
     * DC_Cate : 仅租赁
     * DC_CouconStatus : 有效
     * DC_FailureTime : 2017-01-28 05:50
     * DC_IsLock : false
     * DC_MeetMoney : 100
     * DC_Number : 50
     * DC_OverdueDays : 10
     * DC_PreferentialMoney : 0.5
     * DC_RemainingNumber : 37
     * DC_Remarks :
     * DC_ShortestRentDay : 2
     * DC_Time : 2017-01-18 05:50
     * Member_UID : 20169720335385547137
     * id : 36
     * member_use_get_status
     */

    private String DC_Cate;
    private String DC_CouconStatus;
    private String DC_FailureTime;
    private boolean DC_IsLock;
    private int DC_MeetMoney;
    private int DC_Number;
    private int DC_OverdueDays;
    private String DC_PreferentialMoney;
    private int DC_RemainingNumber;
    private String DC_Remarks;
    private int DC_ShortestRentDay;
    private String DC_Time;
    private String Member_UID;
    private int id;
    private String member_use_get_status;

    public String getMember_use_get_status() {
        return member_use_get_status;
    }

    public void setMember_use_get_status(String member_use_get_status) {
        this.member_use_get_status = member_use_get_status;
    }

    public String getDC_Cate() {
        return DC_Cate;
    }

    public void setDC_Cate(String DC_Cate) {
        this.DC_Cate = DC_Cate;
    }

    public String getDC_CouconStatus() {
        return DC_CouconStatus;
    }

    public void setDC_CouconStatus(String DC_CouconStatus) {
        this.DC_CouconStatus = DC_CouconStatus;
    }

    public String getDC_FailureTime() {
        return DC_FailureTime;
    }

    public void setDC_FailureTime(String DC_FailureTime) {
        this.DC_FailureTime = DC_FailureTime;
    }

    public boolean isDC_IsLock() {
        return DC_IsLock;
    }

    public void setDC_IsLock(boolean DC_IsLock) {
        this.DC_IsLock = DC_IsLock;
    }

    public int getDC_MeetMoney() {
        return DC_MeetMoney;
    }

    public void setDC_MeetMoney(int DC_MeetMoney) {
        this.DC_MeetMoney = DC_MeetMoney;
    }

    public int getDC_Number() {
        return DC_Number;
    }

    public void setDC_Number(int DC_Number) {
        this.DC_Number = DC_Number;
    }

    public int getDC_OverdueDays() {
        return DC_OverdueDays;
    }

    public void setDC_OverdueDays(int DC_OverdueDays) {
        this.DC_OverdueDays = DC_OverdueDays;
    }

    public String getDC_PreferentialMoney() {
        return DC_PreferentialMoney;
    }

    public void setDC_PreferentialMoney(String DC_PreferentialMoney) {
        this.DC_PreferentialMoney = DC_PreferentialMoney;
    }

    public int getDC_RemainingNumber() {
        return DC_RemainingNumber;
    }

    public void setDC_RemainingNumber(int DC_RemainingNumber) {
        this.DC_RemainingNumber = DC_RemainingNumber;
    }

    public String getDC_Remarks() {
        return DC_Remarks;
    }

    public void setDC_Remarks(String DC_Remarks) {
        this.DC_Remarks = DC_Remarks;
    }

    public int getDC_ShortestRentDay() {
        return DC_ShortestRentDay;
    }

    public void setDC_ShortestRentDay(int DC_ShortestRentDay) {
        this.DC_ShortestRentDay = DC_ShortestRentDay;
    }

    public String getDC_Time() {
        return DC_Time;
    }

    public void setDC_Time(String DC_Time) {
        this.DC_Time = DC_Time;
    }

    public String getMember_UID() {
        return Member_UID;
    }

    public void setMember_UID(String Member_UID) {
        this.Member_UID = Member_UID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
