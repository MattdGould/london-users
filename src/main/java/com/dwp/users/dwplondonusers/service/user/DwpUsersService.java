package com.dwp.users.dwplondonusers.service.user;

import com.dwp.users.dwplondonusers.model.DwpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DwpUsersService implements UsersService<DwpUser> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DwpUsersService.class);

    private final RestTemplate restTemplate;

    @Value("${dwp.user.service.url}")
    private String dwpUserServiceUrl;

    public DwpUsersService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<DwpUser> findAll() {

        LOGGER.debug("Finding all users");
        return getUsers();
    }

    protected List<DwpUser> getUsers() {
        LOGGER.debug("Finding all users from endpoint " + dwpUserServiceUrl);
        ResponseEntity<DwpUser[]> response =
                restTemplate.getForEntity(
                        dwpUserServiceUrl,
                        DwpUser[].class);
        DwpUser[] employees = response.getBody();
        List<DwpUser> users = Arrays.asList(employees);
        LOGGER.debug("Found" + users.size() + " users from endpoint " + dwpUserServiceUrl);
        return users;
    }

    protected <T> List<T> getApi(final String path, final HttpMethod method) {

        final ResponseEntity<List<T>> response = restTemplate.exchange(
                path,
                method,
                null,
                new ParameterizedTypeReference<List<T>>() {
                });
        List<T> list = response.getBody();
        return list;
    }
}
