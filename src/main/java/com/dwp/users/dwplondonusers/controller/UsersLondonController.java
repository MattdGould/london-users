package com.dwp.users.dwplondonusers.controller;

import com.dwp.users.dwplondonusers.model.DwpUser;
import com.dwp.users.dwplondonusers.service.distance.UserToLondonDistanceService;
import com.dwp.users.dwplondonusers.service.user.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "London users Queries", value = "LondonUserQueries")
public class UsersLondonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersLondonController.class);
    public static final int MILES_IN_LONDON_CATCHMENT = 50;

    private final UserToLondonDistanceService userToLondonDistanceService;
    private final UsersService usersService;

    public UsersLondonController(UserToLondonDistanceService userToLondonDistanceService, UsersService usersService) {
        this.userToLondonDistanceService = userToLondonDistanceService;
        this.usersService = usersService;
    }

    @ApiOperation(value = "Finds all users within 50 miles of London")
    @GetMapping(value = "/users/London/catchment", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DwpUser> getUsersNearLondon() {
        LOGGER.info("entering /users/NearLondon");
        List<DwpUser> usersNearLondon = getUsersNearLondon(MILES_IN_LONDON_CATCHMENT);
        LOGGER.info("Users Near London Size: " + usersNearLondon.size());
        return usersNearLondon;
    }

    @ApiOperation(value = "Finds all users within requested miles of London")
    @GetMapping(value = "/users/London", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DwpUser> getUsersNearLondon(@RequestParam(value="distanceFrom") double distance) {
        LOGGER.info("entering /users/London");
        List<DwpUser> allUsers =  usersService.findAll();
        LOGGER.info("All users Size: " + allUsers.size());
        List<DwpUser> usersNearLondon = userToLondonDistanceService
                .findUsersWithinMilesOfLondon(allUsers, distance);
        LOGGER.info("Users within " + distance + " miles of London Size: " + usersNearLondon.size());
        return usersNearLondon;
    }
}
