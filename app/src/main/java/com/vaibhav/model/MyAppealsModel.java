package com.vaibhav.model;

public class MyAppealsModel {

    private String appealId;

    private String appealName;

    private String appealTime;

    private String appealBloodGroup;

    private String appealBloodUnit;

    private int noOfAvailableDonor;

    public MyAppealsModel(String appealId, String appealName, String appealTime, String appealBloodGroup, String appealBloodUnit, int noOfAvailableDonor) {
        this.appealId = appealId;
        this.appealName = appealName;
        this.appealTime = appealTime;
        this.appealBloodGroup = appealBloodGroup;
        this.appealBloodUnit = appealBloodUnit;
        this.noOfAvailableDonor = noOfAvailableDonor;
    }

    public String getAppealId() {
        return appealId;
    }

    public String getAppealName() {
        return appealName;
    }

    public String getAppealTime() {
        return appealTime;
    }

    public String getAppealBloodGroup() {
        return appealBloodGroup;
    }

    public String getAppealBloodUnit() {
        return appealBloodUnit;
    }

    public int getNoOfAvailableDonor() {
        return noOfAvailableDonor;
    }
}
