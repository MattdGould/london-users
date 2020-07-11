package com.dwp.users.dwplondonusers.service;

import com.dwp.users.dwplondonusers.model.DwpUserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DwpUsersService implements UsersService<DwpUserModel> {

    private final RestTemplate restTemplate;

    @Value("${dwp.user.service.url}")
    private String dwpUserServiceUrl;

    public DwpUsersService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<DwpUserModel> findAll() {

        return getApi(dwpUserServiceUrl, HttpMethod.GET);
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
