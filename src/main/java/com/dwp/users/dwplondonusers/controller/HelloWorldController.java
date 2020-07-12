package com.dwp.users.dwplondonusers.controller;

import com.dwp.users.dwplondonusers.model.DwpUserModel;
import com.dwp.users.dwplondonusers.service.UserToLondonDistanceService;
import com.dwp.users.dwplondonusers.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    private final UserToLondonDistanceService userToLondonDistanceService;
    private final UsersService usersService;

    public HelloWorldController(UserToLondonDistanceService userToLondonDistanceService, UsersService usersService) {
        this.userToLondonDistanceService = userToLondonDistanceService;
        this.usersService = usersService;
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value="name") String name) {
        return "Hello " + name;
    }

    @GetMapping("/users")
    public List<DwpUserModel> getUsers() {
        return usersService.findAll();
    }

    @GetMapping("/usersNearLondon")
    public List<DwpUserModel> getUsersNearLondon() {
        LOGGER.info("entering /usersNearLondon");
        List<DwpUserModel> allUsers =  usersService.findAll();
        LOGGER.info("All users Size: " + allUsers.size());
        List<DwpUserModel> usersNearLondon = userToLondonDistanceService
                .findUsersWithinMilesOfLondon(allUsers, 50);
        LOGGER.info("Users Near London Size: " + usersNearLondon.size());
        return usersNearLondon;
    }
}
