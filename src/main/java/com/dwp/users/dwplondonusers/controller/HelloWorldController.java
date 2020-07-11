package com.dwp.users.dwplondonusers.controller;

import com.dwp.users.dwplondonusers.model.DwpUserModel;
import com.dwp.users.dwplondonusers.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value="name") String name) {
        return "Hello " + name;
    }

    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public List<DwpUserModel> getUsers() {
        return usersService.findAll();
    }
}
