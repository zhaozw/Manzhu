package com.zpfan.manzhu.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/8/4 0004.
 */

public class ReservationTimeBean implements Parcelable {


    private String time;
    private String price;
    private String isReservation;
    private String timeid;
    private boolean ischeck = false;




    public boolean ischeck() {
        return ischeck;
    }

    public void setIscheck(boolean ischeck) {
        this.ischeck = ischeck;
    }

    public String getIsReservation() {
        return isReservation;
    }

    public void setIsReservation(String isReservation) {
        this.isReservation = isReservation;
    }

    public ReservationTimeBean(String time, String price, String isReservation, String timeid) {
        this.time = time;
        this.price = price;
        this.isReservation = isReservation;
        this.timeid = timeid;
    }

    @Override
    public String toString() {
        return "ReservationTimeBean{" +
                "time='" + time + '\'' +
                ", price='" + price + '\'' +
                ", isReservation=" + isReservation +
                ", timeid='" + timeid + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String isReservation() {
        return isReservation;
    }

    public void setReservation(String reservation) {
        isReservation = reservation;
    }

    public String getTimeid() {
        return timeid;
    }

    public void setTimeid(String timeid) {
        this.timeid = timeid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.time);
        dest.writeString(this.price);
        dest.writeString(this.isReservation);
        dest.writeString(this.timeid);
        dest.writeByte(this.ischeck ? (byte) 1 : (byte) 0);
    }

    protected ReservationTimeBean(Parcel in) {
        this.time = in.readString();
        this.price = in.readString();
        this.isReservation = in.readString();
        this.timeid = in.readString();
        this.ischeck = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ReservationTimeBean> CREATOR = new Parcelable.Creator<ReservationTimeBean>() {
        @Override
        public ReservationTimeBean createFromParcel(Parcel source) {
            return new ReservationTimeBean(source);
        }

        @Override
        public ReservationTimeBean[] newArray(int size) {
            return new ReservationTimeBean[size];
        }
    };
}
