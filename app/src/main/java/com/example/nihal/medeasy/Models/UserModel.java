package com.example.nihal.medeasy.Models;

public class UserModel {

    private String UserName, YearOfBirth, Address, Occupation, FamilyHistoryLink
            , Weight, Height, Password, PhoneNumber,type,gender,userID;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getOccupation() {
        return Occupation;
    }

    public void setOccupation(String occupation) {
        Occupation = occupation;
    }

    public String getFamilyHistoryLink() {
        return FamilyHistoryLink;
    }

    public void setFamilyHistoryLink(String familyHistoryLink) {
        FamilyHistoryLink = familyHistoryLink;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getYearOfBirth() {
        return YearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        YearOfBirth = yearOfBirth;
    }

    public UserModel(String userName, String yearOfBirth, String address, String occupation, String familyHistoryLink, String weight, String height, String password, String phoneNumber, String type, String gender, String userID) {
        UserName = userName;
        YearOfBirth = yearOfBirth;
        Address = address;
        Occupation = occupation;
        FamilyHistoryLink = familyHistoryLink;
        Weight = weight;
        Height = height;
        Password = password;
        PhoneNumber = phoneNumber;
        this.type = type;
        this.gender = gender;
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UserModel() {

    }
}

