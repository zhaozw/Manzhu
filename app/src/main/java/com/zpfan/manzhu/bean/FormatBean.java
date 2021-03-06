package com.zpfan.manzhu.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/7/25 0025.
 */

public class FormatBean implements Parcelable {


    /**
     * PS_ArticleCode :
     * PS_AttributeNames : 尺寸,色彩
     * PS_AttributeValues : 175\/65A,奶奶灰
     * PS_BasicLease : 2
     * PS_CorrespAmount : 4.30
     * PS_DepositPrice : 0.00
     * PS_FixedPrice : 120.60
     * PS_Inventory : 37
     * PS_IsDefaultSelected : true
     * PS_IsShelves : false
     * PS_MarketingPrice : 200.00
     * PS_RenewalPrice : 2.20
     * PS_UniqueID : 201763141020143180
     * PS_Weight : 0
     * Product_UniqueID : 20169811263489221726
     * id : 408
     */

    private String PS_ArticleCode;
    private String PS_AttributeNames;
    private String PS_AttributeValues;
    private int PS_BasicLease;
    private String PS_CorrespAmount;
    private String PS_DepositPrice;
    private String PS_FixedPrice;
    private int PS_Inventory;
    private boolean PS_IsDefaultSelected;
    private boolean PS_IsShelves;
    private String PS_MarketingPrice;
    private String PS_RenewalPrice;
    private String PS_UniqueID;
    private int PS_Weight;
    private String Product_UniqueID;
    private int id;

    public String getPS_ArticleCode() {
        return PS_ArticleCode;
    }

    public void setPS_ArticleCode(String PS_ArticleCode) {
        this.PS_ArticleCode = PS_ArticleCode;
    }

    public String getPS_AttributeNames() {
        return PS_AttributeNames;
    }

    public void setPS_AttributeNames(String PS_AttributeNames) {
        this.PS_AttributeNames = PS_AttributeNames;
    }

    public String getPS_AttributeValues() {
        return PS_AttributeValues;
    }

    public void setPS_AttributeValues(String PS_AttributeValues) {
        this.PS_AttributeValues = PS_AttributeValues;
    }

    public int getPS_BasicLease() {
        return PS_BasicLease;
    }

    public void setPS_BasicLease(int PS_BasicLease) {
        this.PS_BasicLease = PS_BasicLease;
    }

    public String getPS_CorrespAmount() {
        return PS_CorrespAmount;
    }

    public void setPS_CorrespAmount(String PS_CorrespAmount) {
        this.PS_CorrespAmount = PS_CorrespAmount;
    }

    public String getPS_DepositPrice() {
        return PS_DepositPrice;
    }

    public void setPS_DepositPrice(String PS_DepositPrice) {
        this.PS_DepositPrice = PS_DepositPrice;
    }

    public String getPS_FixedPrice() {
        return PS_FixedPrice;
    }

    public void setPS_FixedPrice(String PS_FixedPrice) {
        this.PS_FixedPrice = PS_FixedPrice;
    }

    public int getPS_Inventory() {
        return PS_Inventory;
    }

    public void setPS_Inventory(int PS_Inventory) {
        this.PS_Inventory = PS_Inventory;
    }

    public boolean isPS_IsDefaultSelected() {
        return PS_IsDefaultSelected;
    }

    public void setPS_IsDefaultSelected(boolean PS_IsDefaultSelected) {
        this.PS_IsDefaultSelected = PS_IsDefaultSelected;
    }

    public boolean isPS_IsShelves() {
        return PS_IsShelves;
    }

    public void setPS_IsShelves(boolean PS_IsShelves) {
        this.PS_IsShelves = PS_IsShelves;
    }

    public String getPS_MarketingPrice() {
        return PS_MarketingPrice;
    }

    public void setPS_MarketingPrice(String PS_MarketingPrice) {
        this.PS_MarketingPrice = PS_MarketingPrice;
    }

    public String getPS_RenewalPrice() {
        return PS_RenewalPrice;
    }

    public void setPS_RenewalPrice(String PS_RenewalPrice) {
        this.PS_RenewalPrice = PS_RenewalPrice;
    }

    public String getPS_UniqueID() {
        return PS_UniqueID;
    }

    public void setPS_UniqueID(String PS_UniqueID) {
        this.PS_UniqueID = PS_UniqueID;
    }

    public int getPS_Weight() {
        return PS_Weight;
    }

    public void setPS_Weight(int PS_Weight) {
        this.PS_Weight = PS_Weight;
    }

    public String getProduct_UniqueID() {
        return Product_UniqueID;
    }

    public void setProduct_UniqueID(String Product_UniqueID) {
        this.Product_UniqueID = Product_UniqueID;
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
        dest.writeString(this.PS_ArticleCode);
        dest.writeString(this.PS_AttributeNames);
        dest.writeString(this.PS_AttributeValues);
        dest.writeInt(this.PS_BasicLease);
        dest.writeString(this.PS_CorrespAmount);
        dest.writeString(this.PS_DepositPrice);
        dest.writeString(this.PS_FixedPrice);
        dest.writeInt(this.PS_Inventory);
        dest.writeByte(this.PS_IsDefaultSelected ? (byte) 1 : (byte) 0);
        dest.writeByte(this.PS_IsShelves ? (byte) 1 : (byte) 0);
        dest.writeString(this.PS_MarketingPrice);
        dest.writeString(this.PS_RenewalPrice);
        dest.writeString(this.PS_UniqueID);
        dest.writeInt(this.PS_Weight);
        dest.writeString(this.Product_UniqueID);
        dest.writeInt(this.id);
    }

    public FormatBean() {
    }

    protected FormatBean(Parcel in) {
        this.PS_ArticleCode = in.readString();
        this.PS_AttributeNames = in.readString();
        this.PS_AttributeValues = in.readString();
        this.PS_BasicLease = in.readInt();
        this.PS_CorrespAmount = in.readString();
        this.PS_DepositPrice = in.readString();
        this.PS_FixedPrice = in.readString();
        this.PS_Inventory = in.readInt();
        this.PS_IsDefaultSelected = in.readByte() != 0;
        this.PS_IsShelves = in.readByte() != 0;
        this.PS_MarketingPrice = in.readString();
        this.PS_RenewalPrice = in.readString();
        this.PS_UniqueID = in.readString();
        this.PS_Weight = in.readInt();
        this.Product_UniqueID = in.readString();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<FormatBean> CREATOR = new Parcelable.Creator<FormatBean>() {
        @Override
        public FormatBean createFromParcel(Parcel source) {
            return new FormatBean(source);
        }

        @Override
        public FormatBean[] newArray(int size) {
            return new FormatBean[size];
        }
    };
}
