package com.dwp.users.dwplondonusers.service.distance;

import com.dwp.users.dwplondonusers.model.DwpUser;
import com.dwp.users.dwplondonusers.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserToLondonDistanceServiceImpl implements UserToLondonDistanceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserToLondonDistanceServiceImpl.class);
    public static final double LONDON_LATITUDE = 51.509865;
    public static final double LONDON_LONGITUDE = -0.118092;

    private final Location londonLocation;

    private final LocationDistanceService locationDistanceService;

    public UserToLondonDistanceServiceImpl(LocationDistanceService locationDistanceService) {
        londonLocation = new Location();
        londonLocation.setLatitude(LONDON_LATITUDE);
        londonLocation.setLongitude(LONDON_LONGITUDE);
        this.locationDistanceService = locationDistanceService;
    }

    @Override
    public List<DwpUser> findUsersWithinMilesOfLondon(List<DwpUser> users, double milesFromLondon) {
        LOGGER.debug("Finding all users within miles of london: " + milesFromLondon);
        List<DwpUser> usersWithinMilesFromLondon = new ArrayList<>();

        for (DwpUser user : users) {
            double distanceToLondon = milesDistanceBetweenUserAndLondon(user);
            if(distanceToLondon <= milesFromLondon) {
                usersWithinMilesFromLondon.add(user);
            }
        }
        LOGGER.debug("Number of users found within " + milesFromLondon + " " + usersWithinMilesFromLondon.size());
        return usersWithinMilesFromLondon;
    }

    @Override
    public double milesDistanceBetweenUserAndLondon(DwpUser userModel) {
        LOGGER.debug("Finding miles distance between user and London, user is: " + userModel);
        Location userLocation = new Location();
        userLocation.setLongitude(userModel.getLongitude());
        userLocation.setLatitude(userModel.getLatitude());

        return locationDistanceService.milesDistanceBetweenLocations(userLocation, londonLocation);
    }
}
