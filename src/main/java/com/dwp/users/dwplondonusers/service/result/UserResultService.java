package com.dwp.users.dwplondonusers.service.result;

import com.dwp.users.dwplondonusers.model.DwpUser;

import java.util.List;

public interface UserResultService {
    public static final int MILES_IN_LONDON_CATCHMENT = 50;

    List<DwpUser> getUsersNearLondon();

    List<DwpUser> getUsersNearLondon(double distanceFrom);
}
