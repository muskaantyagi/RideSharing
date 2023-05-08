package com.example.geektrust.models;

import com.example.geektrust.models.common.Coordinate;

public class RideInfo {
    private Driver driver;
    private Rider rider;
    private Coordinate startCoordinate;
    private Coordinate endCoordinate;
    private int timeTakenInMinute;

    //constructor
    public RideInfo(Driver driver, Rider rider, Coordinate startCoordinate) {
        this.driver = driver;
        this.rider = rider;
        this.startCoordinate = startCoordinate;
    }

    public Coordinate getEndCoordinate() {
        return endCoordinate;
    }

    public void setEndCoordinate(Coordinate endCoordinate) {
        this.endCoordinate = endCoordinate;
    }

    public int getTimeTakenInMinute() {
        return timeTakenInMinute;
    }

    public void setTimeTakenInMinute(int timeTakenInMinute) {
        this.timeTakenInMinute = timeTakenInMinute;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Coordinate getStartCoordinate() {
        return startCoordinate;
    }

    public void setStartCoordinate(Coordinate startCoordinate) {
        this.startCoordinate = startCoordinate;
    }
}
