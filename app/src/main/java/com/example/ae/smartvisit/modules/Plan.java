package com.example.ae.smartvisit.modules;


import java.util.ArrayList;
import java.util.Date;

public class Plan {
    private static Plan planInstance;
    private String planName;
    private String planStartDate;
    private String planEndDate;
    private ArrayList<PlaceDataModel> targetPlaces;

    private Plan (){}

    public static Plan getPlanInstance(){
        if(planInstance == null){
            planInstance = new Plan();
        }
        return planInstance;
    }

    public void destroyInstance(){
        planInstance = null;
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

    public ArrayList<PlaceDataModel> getTargetPlaces() {
        return targetPlaces;
    }

    public void addPlaceToPlan(PlaceDataModel addPlace){
        targetPlaces.add(addPlace);
    }
}
