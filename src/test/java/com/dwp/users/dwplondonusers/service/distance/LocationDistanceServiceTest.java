package com.dwp.users.dwplondonusers.service.distance;

import com.dwp.users.dwplondonusers.model.Location;
import com.dwp.users.dwplondonusers.service.distance.LocationDistanceService;
import com.dwp.users.dwplondonusers.service.distance.LocationDistanceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocationDistanceServiceTest {

    private final LocationDistanceService locationDistanceService = new LocationDistanceServiceImpl();

    private Location londonLocation;

    @BeforeEach
    public void setUp() {
        //Longitude and Latitude of London based on https://www.latlong.net/place/london-the-uk-14153.html
        londonLocation = new Location();
        londonLocation.setLatitude(51.509865);
        londonLocation.setLongitude(-0.118092);
    }

    @Test
    void metersDistanceBetweenLocationsPositiveLatAndLong() {
        Location userLocation = new Location();
        userLocation.setLatitude(5.7204203);
        userLocation.setLongitude(10.901604);

        double distanceBetween = locationDistanceService
                .metersDistanceBetweenLocations(londonLocation, userLocation);

        Assertions.assertEquals(5192275.813814454, distanceBetween);
    }

    @Test
    void metersDistanceBetweenLocationsPositiveLatAndNegativeLong() {
        Location userLocation = new Location();
        userLocation.setLatitude(37.13);
        userLocation.setLongitude(-84.08);

        double distanceBetween = locationDistanceService
                .metersDistanceBetweenLocations(londonLocation, userLocation);

        Assertions.assertEquals(6488722.138067871, distanceBetween);
    }

    @Test
    void metersDistanceBetweenLocationsNegativeLatAndPositiveLong() {
        Location userLocation = new Location();
        userLocation.setLatitude(-6.7098551);
        userLocation.setLongitude(111.3479498);

        double distanceBetween = locationDistanceService
                .metersDistanceBetweenLocations(londonLocation, userLocation);

        Assertions.assertEquals(1.206699261509932E7, distanceBetween);
    }

    @Test
    void metersDistanceBetweenLocationsNegativeLatAndLong() {
        Location userLocation = new Location();
        userLocation.setLatitude(-5.2368931);
        userLocation.setLongitude(-75.6557206);

        double distanceBetween = locationDistanceService
                .metersDistanceBetweenLocations(londonLocation, userLocation);

        Assertions.assertEquals(9475932.60350923, distanceBetween);
    }

    @Test
    void milesDistanceBetweenLocationsPositiveLatAndLong() {
        Location userLocation = new Location();
        userLocation.setLatitude(5.7204203);
        userLocation.setLongitude(10.901604);

        double distanceBetween = locationDistanceService
                .milesDistanceBetweenLocations(londonLocation, userLocation);

        Assertions.assertEquals(3226.3386318704897, distanceBetween);
    }

    @Test
    void milesDistanceBetweenLocationsPositiveLatAndNegativeLong() {
        Location userLocation = new Location();
        userLocation.setLatitude(37.13);
        userLocation.setLongitude(-84.08);

        double distanceBetween = locationDistanceService
                .milesDistanceBetweenLocations(londonLocation, userLocation);

        Assertions.assertEquals(4031.915032291419, distanceBetween);
    }

    @Test
    void milesDistanceBetweenLocationsNegativeLatAndPositiveLong() {
        Location userLocation = new Location();
        userLocation.setLatitude(-6.7098551);
        userLocation.setLongitude(111.3479498);

        double distanceBetween = locationDistanceService
                .milesDistanceBetweenLocations(londonLocation, userLocation);

        Assertions.assertEquals(7498.100224377273, distanceBetween);
    }

    @Test
    void milesDistanceBetweenLocationsNegativeLatAndLong() {
        Location userLocation = new Location();
        userLocation.setLatitude(-5.2368931);
        userLocation.setLongitude(-75.6557206);

        double distanceBetween = locationDistanceService
                .milesDistanceBetweenLocations(londonLocation, userLocation);

        Assertions.assertEquals(5888.086174151659, distanceBetween);
    }
}