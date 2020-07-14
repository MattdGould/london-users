package com.dwp.users.dwplondonusers.service.result;

import com.dwp.users.dwplondonusers.model.DwpUser;
import com.dwp.users.dwplondonusers.service.distance.UserToLondonDistanceService;
import com.dwp.users.dwplondonusers.service.user.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserResultServiceImpl implements UserResultService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResultServiceImpl.class);


    private final UserToLondonDistanceService userToLondonDistanceService;
    private final UsersService usersService;

    public UserResultServiceImpl(UserToLondonDistanceService userToLondonDistanceService, UsersService usersService) {
        this.userToLondonDistanceService = userToLondonDistanceService;
        this.usersService = usersService;
    }

    @Override
    public List<DwpUser> getUsersNearLondon() {
        LOGGER.info("Getting all users within the pre-defined London catchment");
        return getUsersNearLondon(MILES_IN_LONDON_CATCHMENT);
    }

    @Override
    public List<DwpUser> getUsersNearLondon(double distanceFrom) {
        List<DwpUser> allUsers = new ArrayList<>();
        allUsers.addAll( usersService.findAll() );
        LOGGER.info("All users Size: " + allUsers.size());
        List<DwpUser> usersNearLondon = userToLondonDistanceService
                .findUsersWithinMilesOfLondon(allUsers, distanceFrom);
        LOGGER.info("Users within " + distanceFrom + " miles of London Size: " + usersNearLondon.size());
        return allUsers;
    }

}
