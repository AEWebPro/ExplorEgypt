package com.example.ae.smartvisit.modules;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Plan implements Parcelable {

    private String planName;
    private String planStartDate;
    private String planEndDate;
    private boolean isActive = false;
    private ArrayList<PairOfDayAndPlace> pairOfData = new ArrayList<>();

    public Plan(String planName, String planStartDate, String planEndDate, boolean isActive, ArrayList<PairOfDayAndPlace> pairOfData) {
        this.planName = planName;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;
        this.isActive = isActive;
        this.pairOfData = pairOfData;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(String planStartDate) {
        this.planStartDate = planStartDate;
    }

    public String getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(String planEndDate) {
        this.planEndDate = planEndDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ArrayList<PairOfDayAndPlace> getPairOfData() {
        return pairOfData;
    }

    public void setPairOfData(ArrayList<PairOfDayAndPlace> pairOfData) {
        this.pairOfData = pairOfData;
    }


    protected Plan(Parcel in) {
        planName = in.readString();
        planStartDate = in.readString();
        planEndDate = in.readString();
        isActive = in.readByte() != 0x00;
        if (in.readByte() == 0x01) {
            pairOfData = new ArrayList<PairOfDayAndPlace>();
            in.readList(pairOfData, PairOfDayAndPlace.class.getClassLoader());
        } else {
            pairOfData = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(planName);
        dest.writeString(planStartDate);
        dest.writeString(planEndDate);
        dest.writeByte((byte) (isActive ? 0x01 : 0x00));
        if (pairOfData == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(pairOfData);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Plan> CREATOR = new Parcelable.Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };
}