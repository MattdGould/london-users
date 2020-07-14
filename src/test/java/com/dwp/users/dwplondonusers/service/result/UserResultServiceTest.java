package com.dwp.users.dwplondonusers.service.result;

import com.dwp.users.dwplondonusers.model.DwpUser;
import com.dwp.users.dwplondonusers.service.distance.UserToLondonDistanceService;
import com.dwp.users.dwplondonusers.service.user.UsersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserResultServiceTest {

    @Mock
    private UserToLondonDistanceService distanceServiceMock;

    @Mock
    private UsersService usersServiceMock;

    @InjectMocks
    private UserResultServiceImpl userResultService;

    private List<DwpUser> dwpUsers;

    @BeforeEach
    public void setUp() {
        dwpUsers = new ArrayList<>();
        DwpUser userModelOne = new DwpUser();
        DwpUser userModelTwo = new DwpUser();

        dwpUsers.add(userModelOne);
        dwpUsers.add(userModelTwo);
    }

    @Test
    public void testGetUsersNearLondon() {
        int milesDistanceToLondon = 30;
        Mockito.when(usersServiceMock.findAll()).thenReturn(dwpUsers);
        Mockito.when(distanceServiceMock.findUsersWithinMilesOfLondon(dwpUsers, milesDistanceToLondon))
                .thenReturn(dwpUsers);

        List<DwpUser> resultUsers = userResultService.getUsersNearLondon(milesDistanceToLondon);

        Assertions.assertEquals(dwpUsers, resultUsers);
    }

    @Test
    public void getUsersNearLondon() {
        Mockito.when(usersServiceMock.findAll()).thenReturn(dwpUsers);
        Mockito.when(distanceServiceMock.findUsersWithinMilesOfLondon(dwpUsers, UserResultServiceImpl.MILES_IN_LONDON_CATCHMENT))
                .thenReturn(dwpUsers);

        List<DwpUser> resultUsers = userResultService.getUsersNearLondon();

        Assertions.assertEquals(dwpUsers, resultUsers);
    }

}