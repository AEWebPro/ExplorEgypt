package com.example.ae.ExplorEgypt.modules;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
public class PlaceDataModel implements Parcelable {


    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("images")
    private String imageUrl;

    @SerializedName("address")
    private String address;

    @SerializedName("city")
    private String city;

    @SerializedName("location")
    private String locationCoordinates;

    @SerializedName("phone")
    private String contactNumber;

    @SerializedName("website")
    private String website;

    @SerializedName("social")
    private String social;

    @SerializedName("rate")
    private float rate;

    @SerializedName("id")
    private int id;

    @SerializedName("type_id")
    private int groupId;

    public PlaceDataModel(String name, String description, String imageUrl, String address, String city, String locationCoordinates, String contactNumber, String website, String social, float rate) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.address = address;
        this.city = city;
        this.locationCoordinates = locationCoordinates;
        this.contactNumber = contactNumber;
        this.website = website;
        this.rate = rate;
        this.social = social;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
        social = in.readString();
        id = in.readInt();
        groupId = in.readInt();
        rate = in.readFloat();
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
        dest.writeString(social);
        dest.writeInt(id);
        dest.writeInt(groupId);
        dest.writeFloat(rate);
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