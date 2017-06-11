package com.example.ae.ExplorEgypt.modules;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class RecommandProgramsListModule implements Parcelable{
    String name;
    int image;
    List<PlaceDataModel> listOfPlacesInPlan;

    public RecommandProgramsListModule(String name, int image, List<PlaceDataModel> listOfPlacesInPlan)
    {
        this.name = name;
        this.image = image;
        this.listOfPlacesInPlan = listOfPlacesInPlan;
    }

    public RecommandProgramsListModule(Parcel source){
        name = source.readString();
        image = source.readInt();
        listOfPlacesInPlan = new ArrayList<>();
        source.readTypedList(listOfPlacesInPlan, PlaceDataModel.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(image);
        parcel.writeTypedList(listOfPlacesInPlan);

    }
    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public List<PlaceDataModel> getListOfPlacesInPlan() {
        return listOfPlacesInPlan;
    }


    public static final Creator<RecommandProgramsListModule> CREATOR = new Creator<RecommandProgramsListModule>() {
        @Override
        public RecommandProgramsListModule[] newArray(int size) {
            return new RecommandProgramsListModule[size];
        }

        @Override
        public RecommandProgramsListModule createFromParcel(Parcel source) {
            return new RecommandProgramsListModule(source);
        }
    };

}
