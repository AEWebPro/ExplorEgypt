package com.example.ae.smartvisit.modules;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class PlaceDataModel implements Parcelable {
    private String name;
    private String description;
    private String imageUrl;
    private String city;
    private int locationCoordinates;
    private String contactNumber;
    private String website;


    public PlaceDataModel(String name, String description, String imageUrl, String city, int locationCoordinates, String contactNumber, String website) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.city = city;
        this.locationCoordinates = locationCoordinates;
        this.contactNumber = contactNumber;
        this.website = website;
    }

    public PlaceDataModel(Parcel source){
        name = source.readString();
        description = source.readString();
        imageUrl = source.readString();
        city = source.readString();
        locationCoordinates = source.readInt();
        contactNumber = source.readString();
        website = source.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(imageUrl);
        parcel.writeString(city);
        parcel.writeInt(locationCoordinates);
        parcel.writeString(contactNumber);
        parcel.writeString(website);
    }


    public String getContactNumber() {
        return contactNumber;
    }

    public String getWebsite() {
        return website;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCity() {
        return city;
    }

    public int getLocationCoordinates() {
        return locationCoordinates;
    }

    public static final Creator<PlaceDataModel> CREATOR = new Creator<PlaceDataModel>() {
        @Override
        public PlaceDataModel[] newArray(int size) {
            return new PlaceDataModel[size];
        }

        @Override
        public PlaceDataModel createFromParcel(Parcel source) {
            return new PlaceDataModel(source);
        }
    };
}
