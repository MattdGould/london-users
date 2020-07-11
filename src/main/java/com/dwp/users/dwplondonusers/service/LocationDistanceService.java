package com.dwp.users.dwplondonusers.service;

import com.dwp.users.dwplondonusers.model.Location;

public interface LocationDistanceService {

    double milesDistanceBetweenLocations(Location locationOne, Location locationTwo);

    double metersDistanceBetweenLocations(Location locationOne, Location locationTwo);

}
