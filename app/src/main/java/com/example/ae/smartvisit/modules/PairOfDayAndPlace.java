package com.example.ae.smartvisit.modules;


public class PairOfDayAndPlace {
    private int day;
    private PlaceDataModel place;

    public PairOfDayAndPlace(int day, PlaceDataModel place) {
        this.day = day;
        this.place = place;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public PlaceDataModel getPlace() {
        return place;
    }

    public void setPlace(PlaceDataModel place) {
        this.place = place;
    }
}
