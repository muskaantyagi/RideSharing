package com.example.geektrust.models;


import com.example.geektrust.models.common.Coordinate;

public class Driver {

    private String driverId;
    private Coordinate coordinate;
    private boolean isAvailable;

//setter and getter functions
    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    //constructor

    public Driver(String driveId, Coordinate coordinate, boolean isAvailable) {
        this.driverId = driveId;
        this.coordinate = coordinate;
        this.isAvailable = isAvailable;
    }

}
