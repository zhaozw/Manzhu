package com.zpfan.manzhu.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/7/28 0028.
 */

public class AddressBean implements Parcelable {


    /**
     * MD_Address : aaaaaaaaaaaaaaaaaaa
     * MD_Area : 辰溪县
     * MD_City : 怀化市
     * MD_IsDefault : false
     * MD_Name : zzzz
     * MD_Phone : 13555555555
     * MD_Province : 湖南省
     * Member_UID : 201761214535744417547
     * id : 48
     */

    private String MD_Address;
    private String MD_Area;
    private String MD_City;
    private boolean MD_IsDefault;
    private String MD_Name;
    private String MD_Phone;
    private String MD_Province;
    private String Member_UID;
    private int id;
    private boolean ischeck = false;

    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getMD_Address() {
        return MD_Address;
    }

    public void setMD_Address(String MD_Address) {
        this.MD_Address = MD_Address;
    }

    public String getMD_Area() {
        return MD_Area;
    }

    public void setMD_Area(String MD_Area) {
        this.MD_Area = MD_Area;
    }

    public String getMD_City() {
        return MD_City;
    }

    public void setMD_City(String MD_City) {
        this.MD_City = MD_City;
    }

    public boolean isMD_IsDefault() {
        return MD_IsDefault;
    }

    public void setMD_IsDefault(boolean MD_IsDefault) {
        this.MD_IsDefault = MD_IsDefault;
    }

    public String getMD_Name() {
        return MD_Name;
    }

    public void setMD_Name(String MD_Name) {
        this.MD_Name = MD_Name;
    }

    public String getMD_Phone() {
        return MD_Phone;
    }

    public void setMD_Phone(String MD_Phone) {
        this.MD_Phone = MD_Phone;
    }

    public String getMD_Province() {
        return MD_Province;
    }

    public void setMD_Province(String MD_Province) {
        this.MD_Province = MD_Province;
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

    public AddressBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.MD_Address);
        dest.writeString(this.MD_Area);
        dest.writeString(this.MD_City);
        dest.writeByte(this.MD_IsDefault ? (byte) 1 : (byte) 0);
        dest.writeString(this.MD_Name);
        dest.writeString(this.MD_Phone);
        dest.writeString(this.MD_Province);
        dest.writeString(this.Member_UID);
        dest.writeInt(this.id);
        dest.writeByte(this.ischeck ? (byte) 1 : (byte) 0);
    }

    protected AddressBean(Parcel in) {
        this.MD_Address = in.readString();
        this.MD_Area = in.readString();
        this.MD_City = in.readString();
        this.MD_IsDefault = in.readByte() != 0;
        this.MD_Name = in.readString();
        this.MD_Phone = in.readString();
        this.MD_Province = in.readString();
        this.Member_UID = in.readString();
        this.id = in.readInt();
        this.ischeck = in.readByte() != 0;
    }

    public static final Creator<AddressBean> CREATOR = new Creator<AddressBean>() {
        @Override
        public AddressBean createFromParcel(Parcel source) {
            return new AddressBean(source);
        }

        @Override
        public AddressBean[] newArray(int size) {
            return new AddressBean[size];
        }
    };
}
