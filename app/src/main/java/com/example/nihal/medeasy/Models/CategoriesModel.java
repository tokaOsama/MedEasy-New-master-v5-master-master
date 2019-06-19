package com.example.nihal.medeasy.Models;

public class CategoriesModel {
    String categoryName;
    String checkBox;

    public CategoriesModel(String categoryName, String checkBox) {
        this.categoryName = categoryName;
        this.checkBox = checkBox;

    }

    public CategoriesModel() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCheckBox() {
        return checkBox;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCheckBox(String checkBox) {
        this.checkBox = checkBox;
    }
}
