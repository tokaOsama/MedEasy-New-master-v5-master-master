package com.example.nihal.medeasy.Models;

public class PostModel {
    private String Title, Description, image;


    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public PostModel() {
    }

    public PostModel(String Title, String Description, String image) {
        this.Title = Title;
        this.Description = Description;
        this.image = image;
    }
}