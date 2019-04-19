package com.vaibhav.model;

public class AvailableDonorModel {

    private String donorId, donorName, donorLocation, donorMobile;


    public AvailableDonorModel(String donorId, String donorName, String donorLocation, String donorMobile) {
        this.donorId = donorId;
        this.donorName = donorName;
        this.donorLocation = donorLocation;
        this.donorMobile = donorMobile;
    }


    public String getDonorId() {
        return donorId;
    }

    public String getDonorName() {
        return donorName;
    }

    public String getDonorLocation() {
        return donorLocation;
    }

    public String getDonorMobile() {
        return donorMobile;
    }
}
