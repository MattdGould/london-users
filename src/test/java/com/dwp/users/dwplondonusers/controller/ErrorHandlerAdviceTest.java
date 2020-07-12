package com.dwp.users.dwplondonusers.controller;

import com.dwp.users.dwplondonusers.service.UserToLondonDistanceService;
import com.dwp.users.dwplondonusers.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
public class ErrorHandlerAdviceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserToLondonDistanceService distanceService;

    @MockBean
    private UsersService usersService;

    @Test
    public void handleRunTimeException() throws Exception {
        when(usersService.findAll()).thenThrow(new RuntimeException());

        this.mockMvc.perform(get("/users/London/catchment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("/error/error"));
    }

}