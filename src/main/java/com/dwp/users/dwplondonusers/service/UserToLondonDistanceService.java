package com.dwp.users.dwplondonusers.service;

import com.dwp.users.dwplondonusers.model.DwpUserModel;

import java.util.List;

public interface UserToLondonDistanceService{

    List<DwpUserModel> findUsersWithinMilesOfLondon(List<DwpUserModel> users, double milesFromLondon);

    double milesDistanceBetweenUserAndLondon(DwpUserModel userModel);

}
