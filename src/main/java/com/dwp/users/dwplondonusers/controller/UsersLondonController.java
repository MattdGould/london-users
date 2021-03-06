package com.dwp.users.dwplondonusers.controller;

import com.dwp.users.dwplondonusers.model.DwpUser;
import com.dwp.users.dwplondonusers.service.result.UserResultService;
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

    private final UserResultService userResultService;

    public UsersLondonController(UserResultService userResultService) {
        this.userResultService = userResultService;
    }

    @ApiOperation(value = "Finds all users within 50 miles of London")
    @GetMapping(value = "/users/London/catchment", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DwpUser> getUsersNearLondon() {
        LOGGER.info("entering /users/London/catchment");
        List<DwpUser> usersNearLondon = getUsersNearLondon(UserResultService.MILES_IN_LONDON_CATCHMENT);
        LOGGER.info("Users Near London Size: " + usersNearLondon.size());
        return usersNearLondon;
    }

    @ApiOperation(value = "Finds all users within requested miles of London")
    @GetMapping(value = "/users/London", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DwpUser> getUsersNearLondon(@RequestParam(value="distanceFrom") double distance) {
        LOGGER.info("entering /users/London");
        List<DwpUser> usersNearLondon = userResultService.getUsersNearLondon(distance);
        LOGGER.info("Users within " + distance + " miles of London Size: " + usersNearLondon.size());
        return usersNearLondon;
    }
}
