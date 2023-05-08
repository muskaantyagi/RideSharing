package com.example.geektrust.models.common;

import com.example.geektrust.models.Driver;

public class DriverWithDistance extends Driver {
    private double distance;
    private Driver driver;

    //constructors
    public DriverWithDistance(Driver driver, double distance) {
        super(driver.getDriverId(), driver.getCoordinate(), driver.isAvailable());
        this.distance = distance;
    }

    //setters and getters

    public double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

}
