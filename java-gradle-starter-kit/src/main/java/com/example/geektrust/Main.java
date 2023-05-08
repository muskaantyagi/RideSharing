package com.example.geektrust;

import com.example.geektrust.controllers.RideController;
import com.example.geektrust.enums.MatchingStatus;
import com.example.geektrust.models.Driver;
import com.example.geektrust.models.Rider;
import com.example.geektrust.models.common.Coordinate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static RideController rideController;

    public static void main(String[] args) {

        /*
        Sample code to read from file passed as command line argument

         */
        if (isInvalidArguments(args)) {
            System.out.println("Invalid arguments ...");
            return;
        }



        rideController = new RideController();

        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read

            StringBuilder displayValues;

            while (sc.hasNextLine()) {
                //Add your code here to process input commands
                String eachLine = sc.nextLine();
                String[] commandArgs = eachLine.split(" ");
                displayValues = new StringBuilder();

                switch (commandArgs[0]) {
                    case "ADD_DRIVER":
                        Coordinate coordinateDriver = new Coordinate(
                                Integer.parseInt(commandArgs[2]),
                                Integer.parseInt(commandArgs[3])
                        );

                        Driver driver = new Driver(commandArgs[1], coordinateDriver, true);

                        rideController.addDriver(driver);

                        break;

                    case "ADD_RIDER":
                        Coordinate coordinateRider = new Coordinate(
                                Integer.parseInt(commandArgs[2]),
                                Integer.parseInt(commandArgs[3])
                        );

                        Rider rider = new Rider(commandArgs[1], coordinateRider);

                        rideController.addRider(rider);


                        break;

                    case "MATCH":
                        MatchingStatus matchStatus = rideController.matchRide(commandArgs[1]);

                        //System.out.println("Match Status is => " + matchStatus.name() + "\n");
                        if (matchStatus != MatchingStatus.DRIVER_MATCHED) {
                            return;
                        }

                        break;

                    case "START_RIDE":

                        boolean started = rideController.startRide(
                                commandArgs[1],
                                Integer.parseInt(commandArgs[2]),
                                commandArgs[3]
                        );
                       // System.out.println((started ? "Ride Started" : "Unable to start Ride") + "\n");
                        if (!started) {
                            return;
                        }
                        break;

                    case "STOP_RIDE":


                        boolean stopped = rideController.stopRide(
                                commandArgs[1],
                                new Coordinate(
                                        Integer.parseInt(commandArgs[2]),
                                        Integer.parseInt(commandArgs[3])
                                ),
                                Integer.parseInt(commandArgs[4])


                        );
                       // System.out.println((stopped ? "Ride Stopped" : "Unable to stop Ride") + "\n");
                        if (!stopped) {
                            return;
                        }
                        break;

                    case "BILL":
                        rideController.bill(commandArgs[1]);
                        //System.out.println(" => Command Configs are => " + displayValues + "\n");
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + commandArgs[0]);
                }


            }
            sc.close(); // closes the scanner
        } catch (IOException e) {
        }

    }

    static boolean isInvalidArguments(String[] args) {
        if (args == null) {
            return true;
        } else return args.length != 1;
    }


}
