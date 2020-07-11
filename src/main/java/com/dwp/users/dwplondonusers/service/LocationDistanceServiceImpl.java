package com.dwp.users.dwplondonusers.service;

import com.dwp.users.dwplondonusers.model.Location;
import org.springframework.stereotype.Service;

@Service
public class LocationDistanceServiceImpl implements LocationDistanceService {

    @Override
    public double milesDistanceBetweenLocations(Location locationOne, Location locationTwo) {
        double distanceInMeters = metersDistanceBetweenLocations(locationOne, locationTwo);
        double distanceInMiles = distanceInMeters / 1609.34;
        return distanceInMiles;
    }

    @Override
    public double metersDistanceBetweenLocations(Location locationOne, Location locationTwo) {
        return distance(locationOne.getLatitude(),
                locationTwo.getLatitude(),
                locationOne.getLongitude(),
                locationTwo.getLongitude(),
                0,
                0);
    }

    /**
     * Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     *
     * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
     * el2 End altitude in meters
     * @returns Distance in Meters
     */
    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        //distance in meters
        return Math.sqrt(distance);
    }



/*    @Override
    public double distanceBetweenLocations(Location locationOne, Location locationTwo) {

        double a = locationOne.getLongitude() - locationTwo.getLongitude();
        double b = locationOne.getLatitude() - locationTwo.getLatitude();

        double aSquared = square(a);
        double bSquared = square(b);

        double aAndBSquared = aSquared + bSquared;

        double c = Math.sqrt(aAndBSquared);

        return c;
    }

    private double square(double numberToSquare) {
        return Math.pow(numberToSquare, 2);
    }*/
}
