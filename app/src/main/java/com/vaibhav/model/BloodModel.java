package com.vaibhav.model;

public class BloodModel {

    private String name;
    private String bloodType;
    private String bloodUnit;

    private boolean isPlatelets;
    private String plateletsCount;

    private String location;
    private String hospital;

    private String time;

    private String mobile;

    public BloodModel(String name, String bloodType, String bloodUnit, boolean isPlatelets, String plateletsCount, String location, String hospital, String time, String mobile) {
        this.name = name;
        this.bloodType = bloodType;
        this.bloodUnit = bloodUnit;
        this.isPlatelets = isPlatelets;
        this.plateletsCount = plateletsCount;
        this.location = location;
        this.hospital = hospital;
        this.time = time;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getBloodUnit() {
        return bloodUnit;
    }

    public boolean isPlatelets() {
        return isPlatelets;
    }

    public String getPlateletsCount() {
        return plateletsCount;
    }

    public String getLocation() {
        return location;
    }

    public String getHospital() {
        return hospital;
    }

    public String getTime() {
        return time;
    }

    public String getMobile() {
        return mobile;
    }
}
