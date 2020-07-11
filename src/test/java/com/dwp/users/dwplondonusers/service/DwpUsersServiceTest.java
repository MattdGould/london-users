package com.dwp.users.dwplondonusers.service;

import com.dwp.users.dwplondonusers.model.DwpUserModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownContentTypeException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DwpUsersServiceTest {

    @Mock
    private RestTemplate restTemplateMock;

    @InjectMocks
    private DwpUsersService dwpUsersService;

    private List<DwpUserModel> dwpUserModels = new ArrayList<>();

    @Test
    public void findAllWithRestClientExceptionReturned() {
        RestClientException exception = new RestClientException("Exception message");

        Mockito.when(restTemplateMock.exchange(
                ArgumentMatchers.eq("https://bpdts-test-app.herokuapp.com/users"),
                ArgumentMatchers.eq(HttpMethod.GET),
                ArgumentMatchers.<HttpEntity<List<DwpUserModel>>>any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<DwpUserModel>>>any()))
                .thenThrow(exception);

        Assertions.assertThrows(RestClientException.class, () -> {
            dwpUsersService.findAll();;
        });
    }

    @Test
    public void findAllWithUnknownContentTypeReturned() {
        HttpHeaders responseHeaders = new HttpHeaders();
        UnknownContentTypeException exception = new UnknownContentTypeException(null, MediaType.APPLICATION_ATOM_XML,
        200, "OK", responseHeaders, new byte[3]);

        Mockito.when(restTemplateMock.exchange(
                ArgumentMatchers.eq("https://bpdts-test-app.herokuapp.com/users"),
                ArgumentMatchers.eq(HttpMethod.GET),
                ArgumentMatchers.<HttpEntity<List<DwpUserModel>>>any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<DwpUserModel>>>any()))
                .thenThrow(exception);

        Assertions.assertThrows(UnknownContentTypeException.class, () -> {
            dwpUsersService.findAll();;
        });
    }

    @Test
    public void findAllWith404ErrorReturned() {
        HttpClientErrorException exception = new HttpClientErrorException(HttpStatus.NOT_FOUND);

        Mockito.when(restTemplateMock.exchange(
                ArgumentMatchers.eq("https://bpdts-test-app.herokuapp.com/users"),
                ArgumentMatchers.eq(HttpMethod.GET),
                ArgumentMatchers.<HttpEntity<List<DwpUserModel>>>any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<DwpUserModel>>>any()))
                .thenThrow(exception);

        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            dwpUsersService.findAll();;
        });
    }

    @Test
    public void findAllWithNoResultsReturned() {
        ResponseEntity<List<DwpUserModel>> responseEntity = new ResponseEntity(dwpUserModels, HttpStatus.OK);
        Mockito.when(restTemplateMock.exchange(
                ArgumentMatchers.eq("https://bpdts-test-app.herokuapp.com/users"),
                ArgumentMatchers.eq(HttpMethod.GET),
                ArgumentMatchers.<HttpEntity<List<DwpUserModel>>>any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<DwpUserModel>>>any()))
                .thenReturn(responseEntity);

        List<DwpUserModel> dwpUserModelsResult = dwpUsersService.findAll();

        assertEquals(0, dwpUserModelsResult.size());
    }

    @Test
    public void findAllWithResultsReturned() {
        setUpUserModelsListWithResults();
        ResponseEntity<List<DwpUserModel>> responseEntity = new ResponseEntity(dwpUserModels, HttpStatus.OK);
        Mockito.when(restTemplateMock.exchange(
                ArgumentMatchers.eq("https://bpdts-test-app.herokuapp.com/users"),
                ArgumentMatchers.eq(HttpMethod.GET),
                ArgumentMatchers.<HttpEntity<List<DwpUserModel>>>any(),
                ArgumentMatchers.<ParameterizedTypeReference<List<DwpUserModel>>>any()))
                .thenReturn(responseEntity);

        List<DwpUserModel> dwpUserModelsResult = dwpUsersService.findAll();

        assertEquals(2, dwpUserModelsResult.size());
    }

    private void setUpUserModelsListWithResults() {
        DwpUserModel userModelOne = new DwpUserModel();
        DwpUserModel userModelTwo = new DwpUserModel();

        dwpUserModels.add(userModelOne);
        dwpUserModels.add(userModelTwo);
    }
}