package com.dwp.users.dwplondonusers.controller;

import com.dwp.users.dwplondonusers.model.DwpUserModel;
import com.dwp.users.dwplondonusers.service.UserToLondonDistanceService;
import com.dwp.users.dwplondonusers.service.UsersService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UsersLondonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserToLondonDistanceService distanceService;

    @MockBean
    private UsersService usersService;

    @Test
    public void getUsersNearLondonFindsResults() throws Exception {
        EasyRandom easyRandom = new EasyRandom();
        DwpUserModel userOne = easyRandom.nextObject(DwpUserModel.class);
        DwpUserModel userTwo = easyRandom.nextObject(DwpUserModel.class);
        List<DwpUserModel> userModelList = Arrays.asList(userOne, userTwo);

        when(usersService.findAll()).thenReturn(userModelList);

        when(distanceService.findUsersWithinMilesOfLondon(userModelList, 50))
                .thenReturn(userModelList);

        String expectedResult = "[{\"id\":-1188957731,\"firstName\":\"eOMtThyhVNLWUZNRcBaQKxI\",\"lastName\":\"yedUsFwdkelQbxeTeQOvaScfqIOOmaa\",\"email\":\"JxkyvRnL\",\"ipAddress\":\"RYtGKbgicZaHCBRQDSx\",\"latitude\":0.7231742029971469,\"longitude\":0.9908988967772393},{\"id\":1018954901,\"firstName\":\"VLhpfQGTMDYpsBZxvfBoeygjb\",\"lastName\":\"UMaAIKKIkknjWEXJUfPxxQHeWKEJ\",\"email\":\"dpHYZGhtgdntugzvvKAXLhM\",\"ipAddress\":\"LlN\",\"latitude\":0.25329310557439133,\"longitude\":0.6088003703785169}]";

        this.mockMvc.perform(get("/users/London/catchment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResult)));
    }

    @Test
    public void getUsersNearLondonFindsNoResults() throws Exception {
        EasyRandom easyRandom = new EasyRandom();
        DwpUserModel userOne = easyRandom.nextObject(DwpUserModel.class);
        DwpUserModel userTwo = easyRandom.nextObject(DwpUserModel.class);
        List<DwpUserModel> userModelList = Arrays.asList(userOne, userTwo);

        when(usersService.findAll()).thenReturn(userModelList);

        when(distanceService.findUsersWithinMilesOfLondon(userModelList, 50))
                .thenReturn(Collections.emptyList());

        String expectedResult = "[]";

        this.mockMvc.perform(get("/users/London/catchment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResult)));
    }

    @Test
    public void testGetUsersNearLondonFindsResults() throws Exception {
        EasyRandom easyRandom = new EasyRandom();
        DwpUserModel userOne = easyRandom.nextObject(DwpUserModel.class);
        DwpUserModel userTwo = easyRandom.nextObject(DwpUserModel.class);
        List<DwpUserModel> userModelList = Arrays.asList(userOne, userTwo);

        when(usersService.findAll()).thenReturn(userModelList);

        when(distanceService.findUsersWithinMilesOfLondon(userModelList, 30))
                .thenReturn(userModelList);

        String expectedResult = "[{\"id\":-1188957731,\"firstName\":\"eOMtThyhVNLWUZNRcBaQKxI\",\"lastName\":\"yedUsFwdkelQbxeTeQOvaScfqIOOmaa\",\"email\":\"JxkyvRnL\",\"ipAddress\":\"RYtGKbgicZaHCBRQDSx\",\"latitude\":0.7231742029971469,\"longitude\":0.9908988967772393},{\"id\":1018954901,\"firstName\":\"VLhpfQGTMDYpsBZxvfBoeygjb\",\"lastName\":\"UMaAIKKIkknjWEXJUfPxxQHeWKEJ\",\"email\":\"dpHYZGhtgdntugzvvKAXLhM\",\"ipAddress\":\"LlN\",\"latitude\":0.25329310557439133,\"longitude\":0.6088003703785169}]";

        this.mockMvc.perform(get("/users/London")
                .param("distanceFrom", "30"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResult)));
    }

    @Test
    public void testGetUsersNearLondonFindsNoResults() throws Exception {
        EasyRandom easyRandom = new EasyRandom();
        DwpUserModel userOne = easyRandom.nextObject(DwpUserModel.class);
        DwpUserModel userTwo = easyRandom.nextObject(DwpUserModel.class);
        List<DwpUserModel> userModelList = Arrays.asList(userOne, userTwo);

        when(usersService.findAll()).thenReturn(userModelList);

        when(distanceService.findUsersWithinMilesOfLondon(userModelList, 30))
                .thenReturn(Collections.emptyList());

        String expectedResult = "[]";

        this.mockMvc.perform(get("/users/London")
                .param("distanceFrom", "30"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(expectedResult)));
    }
}