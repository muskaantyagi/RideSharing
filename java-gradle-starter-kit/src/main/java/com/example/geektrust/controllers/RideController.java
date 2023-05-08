package com.example.geektrust.controllers;

import com.example.geektrust.enums.MatchingStatus;
import com.example.geektrust.models.Driver;
import com.example.geektrust.models.RideInfo;
import com.example.geektrust.models.Rider;
import com.example.geektrust.models.common.Coordinate;
import com.example.geektrust.models.common.DriverWithDistance;
import com.example.geektrust.utils.Utils;

import java.util.*;

public class RideController {
    public RideController() {
        drivers = new ArrayList<>();
        riders = new ArrayList<>();
        availableDriversOfRider = new HashMap<>();
        onGoingRides = new HashMap<>();
    }

    private List<Driver> drivers;
    private List<Rider> riders;

    private Map<Rider, List<DriverWithDistance>> availableDriversOfRider;

    private Map<String, RideInfo> onGoingRides;
    int maxNoOfKilometers = 11;

    public void addDriver(Driver driver) {

        if (!drivers.contains(driver)) {
            this.drivers.add(driver);
        }
    }


    public void addRider(Rider rider) {
        if (!riders.contains(rider)) {
            this.riders.add(rider);

        }
    }

    public MatchingStatus matchRide(String riderId) {

        Rider foundRider = null;
        for (Rider eachRider : riders) {
            if (eachRider.getRiderID().equalsIgnoreCase(riderId)) {
                foundRider = eachRider;
                break;
            }
        }

        if (foundRider != null) {
            List<DriverWithDistance> matchOfDrivers = getDriversWithinRange(foundRider, drivers);
            if (matchOfDrivers.isEmpty()) {

                return MatchingStatus.DRIVER_NOT_FOUND;
            }
            availableDriversOfRider.put(foundRider, matchOfDrivers);

            // int index = 1;
            String driverStr = "";
            for (DriverWithDistance element : matchOfDrivers) {
                driverStr += " ";
                driverStr += element.getDriverId();
//                System.out.println("index : " + index + "  Driver Id : " + element.getDriverId() + " Distance : " + Utils.roundTillTwoDecimalInString(element.getDistance()));
//                index++;
            }

            System.out.println("DRIVER_MATCHED " + driverStr);
            return MatchingStatus.DRIVER_MATCHED;
        }
        return MatchingStatus.INVALID_RIDER;
    }


    public boolean startRide(String rideId, int driverIndex, String riderID) {
        Rider riderObj = null;

        for (Rider ride : riders) {
            if (ride.getRiderID().equalsIgnoreCase(riderID)) {
                riderObj = ride;
                break;
            }
        }
        if (riderObj == null) {
            // invalid rider id
            System.out.println("INVALID_RIDE");
            return false;
        }
        if (availableDriversOfRider.get(riderObj).isEmpty())

            if (driverIndex < 1 || driverIndex > availableDriversOfRider.get(riderObj).size()) {
                // invalid driver index
                System.out.println("INVALID_RIDE");
                return false;
            }

        if (onGoingRides.get(rideId) != null) {
            // given ride id is already in ongoing state
            System.out.println("INVALID_RIDE");
            return false;
        }
        Driver selectedDriver = availableDriversOfRider.get(riderObj).get(driverIndex - 1);

        onGoingRides.put(rideId, new RideInfo(selectedDriver, riderObj, riderObj.getCoordinate()));


        // updating selected Driver status to Unavailable
        updateDriverAvailability(selectedDriver.getDriverId(), false);

        // cleaning up collection of nearby drivers of selected rider
        availableDriversOfRider.remove(riderObj);

        System.out.println("RIDE_STARTED " + rideId);

        return true;

    }

    public boolean stopRide(String rideId, Coordinate coordinate, int timeTaken) {

        if (onGoingRides.get(rideId) == null) {
            // invalid ride Id
            return false;
        }
        if (coordinate == null) {
            // invalid ride End Coordinate
            return false;
        }
        if (timeTaken < 1) {
            // invalid Taken time
            return false;
        }

        if (onGoingRides.get(rideId) == null) {
            // Ride not started even
            return false;
        }

        if (onGoingRides.get(rideId).getEndCoordinate() != null) {
            // Already Stopped, Nothing to Stop
            return false;
        }


        RideInfo updatedRideInfo = onGoingRides.get(rideId);

        updatedRideInfo.setEndCoordinate(coordinate);
        updatedRideInfo.setTimeTakenInMinute(timeTaken);
        onGoingRides.put(rideId, updatedRideInfo);
        System.out.println("RIDE_STOPPED "+rideId);
        return true;

    }

    public double bill(String rideId) {

        RideInfo rideInfo = onGoingRides.get(rideId);

        if (rideInfo == null) {
            // invalid Ride Id
            return -1;
        }
        // setting available to selected Driver to receive another ride
        updateDriverAvailability(rideInfo.getDriver().getDriverId(), true);

        double baseFare = 50;
        double perKmCharge = 6.5;
        double perMinCharge = 2;
        double serviceTax = 0.2;


        Coordinate source = rideInfo.getStartCoordinate();
        Coordinate destination = rideInfo.getEndCoordinate();
        int timeTaken = rideInfo.getTimeTakenInMinute();

        double distance = Utils.getDistance(source, destination);

        distance = Utils.roundTillTwoDecimal(distance);

        //  Ride ride=new Ride(rideId,source,destination,));
        //  RideInfo rideInfo=new RideInfo()

        // get this completed

        double grandTotal = baseFare + (perKmCharge * distance) + (perMinCharge * timeTaken);
        double netPayableAmount = grandTotal + (grandTotal * serviceTax);

        netPayableAmount = Utils.roundTillTwoDecimal(netPayableAmount);
        String netPayableAmountDisplay = Utils.roundTillTwoDecimalInString(netPayableAmount);


        System.out.println("BILL " + rideId + " " + rideInfo.getDriver().getDriverId() + " " + netPayableAmountDisplay);
        return netPayableAmount;
    }


    private List<DriverWithDistance> getDriversWithinRange(Rider rider, List<Driver> listOfDrivers) {


        List<DriverWithDistance> matchOfDrivers = new ArrayList<>();
        for (Driver eachDriver : listOfDrivers) {
            if (!eachDriver.isAvailable()) {
                continue;
            }

            double singleDistance = Utils.getDistance(eachDriver.getCoordinate(), rider.getCoordinate());
            singleDistance = Utils.roundTillTwoDecimal(singleDistance);

            if (singleDistance <= maxNoOfKilometers) {
                matchOfDrivers.add(new DriverWithDistance(eachDriver, singleDistance));
            }
        }

        matchOfDrivers.sort(Comparator.comparingDouble(DriverWithDistance::getDistance));
        //System.out.println(matchOfDrivers);
        return matchOfDrivers;

    }

    private void updateDriverAvailability(String driverId, boolean availableStatus) {
        for (int x = 0; x < drivers.size(); x++) {
            if (drivers.get(x).getDriverId().equalsIgnoreCase(driverId)) {
                drivers.get(x).setAvailable(availableStatus);
                break;

            }
        }
    }
}

