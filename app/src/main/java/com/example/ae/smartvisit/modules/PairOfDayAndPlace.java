package com.example.ae.smartvisit.modules;


import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PairOfDayAndPlace implements Parcelable, Comparable {
    private int day;
    private List<PlaceDataModel> places = new ArrayList<>();

    public PairOfDayAndPlace(){

    }

    public PairOfDayAndPlace(int day, List<PlaceDataModel> places) {
        this.day = day;
        this.places = places;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public List<PlaceDataModel> getPlace() {
        return places;
    }

    public void setPlace(List<PlaceDataModel> places) {
        this.places = places;
    }

    public void addPlaceToList(PlaceDataModel placeToAdd){
        places.add(placeToAdd);
    }

    protected PairOfDayAndPlace(Parcel in) {
        day = in.readInt();
        if (in.readByte() == 0x01) {
            places = new ArrayList<PlaceDataModel>();
            in.readList(places, PlaceDataModel.class.getClassLoader());
        } else {
            places = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(day);
        if (places == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(places);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PairOfDayAndPlace> CREATOR = new Parcelable.Creator<PairOfDayAndPlace>() {
        @Override
        public PairOfDayAndPlace createFromParcel(Parcel in) {
            return new PairOfDayAndPlace(in);
        }

        @Override
        public PairOfDayAndPlace[] newArray(int size) {
            return new PairOfDayAndPlace[size];
        }
    };

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}