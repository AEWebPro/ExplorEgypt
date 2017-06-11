package com.example.ae.ExplorEgypt.modules;

public class RecommandListModule {
    String name;
    String time;
    int image;


    public RecommandListModule(String name, String time, int image){
        this.name=name;
        this.time=time;
        this.image=image;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getImage() {
        return image;
    }

}
