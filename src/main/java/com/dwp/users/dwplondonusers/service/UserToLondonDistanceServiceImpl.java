package com.dwp.users.dwplondonusers.service;

import com.dwp.users.dwplondonusers.model.DwpUserModel;
import com.dwp.users.dwplondonusers.model.Location;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserToLondonDistanceServiceImpl implements UserToLondonDistanceService {

    private final Location londonLocation;

    private final LocationDistanceService locationDistanceService;

    public UserToLondonDistanceServiceImpl(LocationDistanceService locationDistanceService) {
        londonLocation = new Location();
        londonLocation.setLatitude(51.509865);
        londonLocation.setLongitude(-0.118092);
        this.locationDistanceService = locationDistanceService;
    }

    @Override
    public List<DwpUserModel> findUsersWithinMilesOfLondon(List<DwpUserModel> users, double milesFromLondon) {
        List<DwpUserModel> usersWithinMilesFromLondon = new ArrayList<>();

        for (DwpUserModel user : users) {
            double distanceToLondon = milesDistanceBetweenUserAndLondon(user);
            if(distanceToLondon <= milesFromLondon) {
                usersWithinMilesFromLondon.add(user);
            }
        }
        return usersWithinMilesFromLondon;
    }

    @Override
    public double milesDistanceBetweenUserAndLondon(DwpUserModel userModel) {
        Location userLocation = new Location();
        userLocation.setLongitude(userModel.getLongitude());
        userLocation.setLatitude(userModel.getLatitude());

        return locationDistanceService.milesDistanceBetweenLocations(userLocation, londonLocation);
    }
}
