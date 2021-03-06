package com.zpfan.manzhu.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

public class OrderCouponBean implements Parcelable {


    /**
     * MC_Cate : 仅购物
     * MC_IsLock : false
     * MC_MeetMoney : 120.00
     * MC_Number : 34
     * MC_OverdueTime : 2017-08-10 14:55
     * MC_PreferentialMoney : 1.00
     * MC_Remarks :
     * MC_ShortestRentDay : 2
     * MC_Status : 未使用
     * MC_StoreUID : 20169720335385547137
     * Member_UID : 201761214535744417547
     * id : 491
     */

    private String MC_Cate;
    private boolean MC_IsLock;
    private String MC_MeetMoney;
    private String MC_Number;
    private String MC_OverdueTime;
    private String MC_PreferentialMoney;
    private String MC_Remarks;
    private int MC_ShortestRentDay;
    private String MC_Status;
    private String MC_StoreUID;
    private String Member_UID;
    private int id;

    public String getMC_Cate() {
        return MC_Cate;
    }

    public void setMC_Cate(String MC_Cate) {
        this.MC_Cate = MC_Cate;
    }

    public boolean isMC_IsLock() {
        return MC_IsLock;
    }

    public void setMC_IsLock(boolean MC_IsLock) {
        this.MC_IsLock = MC_IsLock;
    }

    public String getMC_MeetMoney() {
        return MC_MeetMoney;
    }

    public void setMC_MeetMoney(String MC_MeetMoney) {
        this.MC_MeetMoney = MC_MeetMoney;
    }

    public String getMC_Number() {
        return MC_Number;
    }

    public void setMC_Number(String MC_Number) {
        this.MC_Number = MC_Number;
    }

    public String getMC_OverdueTime() {
        return MC_OverdueTime;
    }

    public void setMC_OverdueTime(String MC_OverdueTime) {
        this.MC_OverdueTime = MC_OverdueTime;
    }

    public String getMC_PreferentialMoney() {
        return MC_PreferentialMoney;
    }

    public void setMC_PreferentialMoney(String MC_PreferentialMoney) {
        this.MC_PreferentialMoney = MC_PreferentialMoney;
    }

    public String getMC_Remarks() {
        return MC_Remarks;
    }

    public void setMC_Remarks(String MC_Remarks) {
        this.MC_Remarks = MC_Remarks;
    }

    public int getMC_ShortestRentDay() {
        return MC_ShortestRentDay;
    }

    public void setMC_ShortestRentDay(int MC_ShortestRentDay) {
        this.MC_ShortestRentDay = MC_ShortestRentDay;
    }

    public String getMC_Status() {
        return MC_Status;
    }

    public void setMC_Status(String MC_Status) {
        this.MC_Status = MC_Status;
    }

    public String getMC_StoreUID() {
        return MC_StoreUID;
    }

    public void setMC_StoreUID(String MC_StoreUID) {
        this.MC_StoreUID = MC_StoreUID;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.MC_Cate);
        dest.writeByte(this.MC_IsLock ? (byte) 1 : (byte) 0);
        dest.writeString(this.MC_MeetMoney);
        dest.writeString(this.MC_Number);
        dest.writeString(this.MC_OverdueTime);
        dest.writeString(this.MC_PreferentialMoney);
        dest.writeString(this.MC_Remarks);
        dest.writeInt(this.MC_ShortestRentDay);
        dest.writeString(this.MC_Status);
        dest.writeString(this.MC_StoreUID);
        dest.writeString(this.Member_UID);
        dest.writeInt(this.id);
    }

    public OrderCouponBean() {
    }

    protected OrderCouponBean(Parcel in) {
        this.MC_Cate = in.readString();
        this.MC_IsLock = in.readByte() != 0;
        this.MC_MeetMoney = in.readString();
        this.MC_Number = in.readString();
        this.MC_OverdueTime = in.readString();
        this.MC_PreferentialMoney = in.readString();
        this.MC_Remarks = in.readString();
        this.MC_ShortestRentDay = in.readInt();
        this.MC_Status = in.readString();
        this.MC_StoreUID = in.readString();
        this.Member_UID = in.readString();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<OrderCouponBean> CREATOR = new Parcelable.Creator<OrderCouponBean>() {
        @Override
        public OrderCouponBean createFromParcel(Parcel source) {
            return new OrderCouponBean(source);
        }

        @Override
        public OrderCouponBean[] newArray(int size) {
            return new OrderCouponBean[size];
        }
    };
}
