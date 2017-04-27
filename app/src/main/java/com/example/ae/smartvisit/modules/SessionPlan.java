package com.example.ae.smartvisit.modules;


import java.util.ArrayList;

public class SessionPlan {
    private static SessionPlan sessionPlanInstance;
    private String planName;
    private String planStartDate;
    private String planEndDate;
    private ArrayList<PairOfDayAndPlace> pairOfData = new ArrayList<>();

    private SessionPlan(){
    }

    public static SessionPlan getSessionPlanInstance(){
        if(sessionPlanInstance == null){
            sessionPlanInstance = new SessionPlan();
        }
        return sessionPlanInstance;
    }

    public void destroyInstance(){
        sessionPlanInstance = null;
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

    public ArrayList<PairOfDayAndPlace> getPairOfData() {
        return pairOfData;
    }

    public void addPlaceToPlan(PairOfDayAndPlace addPair){
        pairOfData.add(addPair);
    }

    public void removePlaceFromPlan(String placeName){
        ArrayList<PairOfDayAndPlace> tempList = new ArrayList<>();
        for (PairOfDayAndPlace pairToSearch: pairOfData) {
            if (pairToSearch.getPlace().get(0).getName().equals(placeName)){
                tempList.add(pairToSearch);
            }
        }
        pairOfData.removeAll(tempList);
    }
    public void setPairOfData(ArrayList<PairOfDayAndPlace> pairOfData) {
        this.pairOfData = pairOfData;
    }
}
