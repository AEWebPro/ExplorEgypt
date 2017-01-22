package com.example.ae.smartvisit.modules;

public class Restaurants {
    private String name;
    private String description;
    private String imageUrl;
    private String city;
    private int locationCoordinates;
    private String contactNumber;
    private String website;


    public Restaurants(String name, String description, String imageUrl, String city, int locationCoordinates, String contactNumber, String website) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.city = city;
        this.locationCoordinates = locationCoordinates;
        this.contactNumber = contactNumber;
        this.website = website;
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
}
