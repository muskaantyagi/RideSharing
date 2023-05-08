package com.example.geektrust.models;

import com.example.geektrust.models.common.Coordinate;

public class Rider {
    private String riderID;
    private Coordinate coordinate;

    //constructor

    public Rider(String riderID, Coordinate coordinate) {
        this.riderID = riderID;
        this.coordinate = coordinate;
    }

    //setters and getters
    public String getRiderID() {
        return riderID;
    }

    public void setRiderID(String riderID) {
        this.riderID = riderID;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
