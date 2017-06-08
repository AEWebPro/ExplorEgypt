package com.example.ae.smartvisit.modules;

/**
 * Created by ahmed.E on 6/2/2017.
 */

public class CategoryItem {
    private int image;
    private String title;

    public CategoryItem(int image, String title) {
        this.image = image;
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
