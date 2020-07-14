package com.dwp.users.dwplondonusers.service.distance;

import com.dwp.users.dwplondonusers.model.DwpUser;

import java.util.List;

public interface UserToLondonDistanceService{

    List<DwpUser> findUsersWithinMilesOfLondon(List<DwpUser> users, double milesFromLondon);

    double milesDistanceBetweenUserAndLondon(DwpUser userModel);

}
