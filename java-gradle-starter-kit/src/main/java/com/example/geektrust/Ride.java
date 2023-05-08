package com.example.geektrust;

import com.example.geektrust.models.common.Coordinate;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ride {
    private String rideId;
    private Coordinate source;
    private Coordinate destination;
    private double distance;
    private double bill;

    public Ride(String rideId, Coordinate source, Coordinate destination, double distance, double bill ){
        this.rideId=rideId;
        this.source= new Coordinate(source.getxCoordinate(), source.getyCoordinate());
        this.destination=new Coordinate(source.getxCoordinate(), source.getyCoordinate());
        this.distance=distance;
        this.bill=bill;
    }


   // public ArrayList<Driver> match(String riderID){}

    public String start_ride(String rideID, int indexOfMatchedDriver, String riderID){
        return "pass";

    }

    public  String stop_ride(String rideID, int indexOfMatchedDriver, String riderID){
        return "pass";

    }
    public int bill(String rideId){
        return 0;

    }
}
