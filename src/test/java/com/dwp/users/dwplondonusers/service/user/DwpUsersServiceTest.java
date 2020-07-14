package com.dwp.users.dwplondonusers.service.user;

import com.dwp.users.dwplondonusers.model.DwpUser;
import com.dwp.users.dwplondonusers.service.user.DwpUsersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
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

    private String testUrl = "https://myTestHost.com/testEndpoint";
    private List<DwpUser> dwpUsers = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(dwpUsersService, "dwpUserServiceUrl", testUrl);
    }

    @Test
    public void findAllWithRestClientExceptionReturned() {
        RestClientException exception = new RestClientException("Exception message");

        Mockito.when(restTemplateMock.getForEntity(
                ArgumentMatchers.eq(testUrl),
                ArgumentMatchers.any()))
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

        Mockito.when(restTemplateMock.getForEntity(
                ArgumentMatchers.eq(testUrl),
                ArgumentMatchers.any()))
                .thenThrow(exception);

        Assertions.assertThrows(UnknownContentTypeException.class, () -> {
            dwpUsersService.findAll();;
        });
    }

    @Test
    public void findAllWith404ErrorReturned() {
        HttpClientErrorException exception = new HttpClientErrorException(HttpStatus.NOT_FOUND);

        Mockito.when(restTemplateMock.getForEntity(
                ArgumentMatchers.eq(testUrl),
                ArgumentMatchers.any()))
                .thenThrow(exception);

        Assertions.assertThrows(HttpClientErrorException.class, () -> {
            dwpUsersService.findAll();
        });
    }

    @Test
    public void findAllWithNoResultsReturned() {
        ResponseEntity<DwpUser> responseEntity = new ResponseEntity(dwpUsers.toArray(), HttpStatus.OK);
        DwpUser[] array = new DwpUser[dwpUsers.size()];
        array = dwpUsers.toArray(array);
        Mockito.when(restTemplateMock.getForEntity(
                ArgumentMatchers.eq(testUrl),
                ArgumentMatchers.any()))
                .thenReturn(new ResponseEntity(array, HttpStatus.OK));

        List<DwpUser> dwpUserModelsResult = dwpUsersService.findAll();

        assertEquals(0, dwpUserModelsResult.size());
    }

    @Test
    public void findAllWithResultsReturned() {
        setUpUserModelsListWithResults();
        DwpUser[] array = new DwpUser[dwpUsers.size()];
        array = dwpUsers.toArray(array);
        Mockito.when(restTemplateMock.getForEntity(
                ArgumentMatchers.eq(testUrl),
                ArgumentMatchers.any()))
                .thenReturn(new ResponseEntity(array, HttpStatus.OK));

        List<DwpUser> dwpUserModelsResult = dwpUsersService.findAll();

        assertEquals(2, dwpUserModelsResult.size());
    }

    private void setUpUserModelsListWithResults() {
        DwpUser userModelOne = new DwpUser();
        DwpUser userModelTwo = new DwpUser();

        dwpUsers.add(userModelOne);
        dwpUsers.add(userModelTwo);
    }
}