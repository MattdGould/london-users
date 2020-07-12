package com.dwp.users.dwplondonusers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@SpringBootApplication
public class DwpLondonUsersApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(DwpLondonUsersApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DwpLondonUsersApplication.class, args);
    }

    @PostConstruct
    public void startupApplication() {
        LOGGER.info("Started application");
    }

    @PreDestroy
    public void shutdownApplication() {
        LOGGER.info("Application shutting down");
    }

}
