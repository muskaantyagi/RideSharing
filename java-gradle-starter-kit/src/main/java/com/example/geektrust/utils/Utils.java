package com.example.geektrust.utils;

import com.example.geektrust.models.common.Coordinate;

import java.text.DecimalFormat;

public class Utils {
    private static final DecimalFormat decimalFormatter = new DecimalFormat("0.00");

    public static double getDistance(Coordinate firstCoordinate, Coordinate secondCoordinate) {

        double xCoordinateDiff = secondCoordinate.getxCoordinate() - firstCoordinate.getxCoordinate();
        double yCoordinateDiff = secondCoordinate.getyCoordinate() - firstCoordinate.getyCoordinate();
        double distance = Math.sqrt(xCoordinateDiff * xCoordinateDiff + yCoordinateDiff * yCoordinateDiff);
        return distance;
    }

    public static String roundTillTwoDecimalInString(double amount) {
        return decimalFormatter.format(amount);
    }

    public static double roundTillTwoDecimal(double amount) {

        String formattedAmount = decimalFormatter.format(amount);

        return Double.parseDouble(formattedAmount);
    }
}
