package com.example.ae.smartvisit.modules;

import android.os.Parcel;
import android.os.Parcelable;
public class PlaceDataModel implements Parcelable {

    private String name;
    private String description;
    private String imageUrl;
    private String address;
    private String city;
    private String locationCoordinates;
    private String contactNumber;
    private String website;

    public PlaceDataModel(String name, String description, String imageUrl, String address, String city, String locationCoordinates, String contactNumber, String website) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.address = address;
        this.city = city;
        this.locationCoordinates = locationCoordinates;
        this.contactNumber = contactNumber;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocationCoordinates() {
        return locationCoordinates;
    }

    public void setLocationCoordinates(String locationCoordinates) {
        this.locationCoordinates = locationCoordinates;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    protected PlaceDataModel(Parcel in) {
        name = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        address = in.readString();
        city = in.readString();
        locationCoordinates = in.readString();
        contactNumber = in.readString();
        website = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeString(address);
        dest.writeString(city);
        dest.writeString(locationCoordinates);
        dest.writeString(contactNumber);
        dest.writeString(website);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PlaceDataModel> CREATOR = new Parcelable.Creator<PlaceDataModel>() {
        @Override
        public PlaceDataModel createFromParcel(Parcel in) {
            return new PlaceDataModel(in);
        }

        @Override
        public PlaceDataModel[] newArray(int size) {
            return new PlaceDataModel[size];
        }
    };
}