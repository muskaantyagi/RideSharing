package com.example.geektrust.models.common;

public class Coordinate {
    private int xCoordinate;
    private int yCoordinate;

    //constructor
    public Coordinate(int xCoordinate, int yCoordinate){
        this.xCoordinate=xCoordinate;
        this.yCoordinate=yCoordinate;
    }

    //setters and getters functions
    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
