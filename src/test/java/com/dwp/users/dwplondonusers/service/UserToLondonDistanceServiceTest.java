package com.dwp.users.dwplondonusers.service;

import com.dwp.users.dwplondonusers.model.DwpUser;
import com.dwp.users.dwplondonusers.model.Location;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserToLondonDistanceServiceTest {

    @Mock
    private LocationDistanceService locationDistanceServiceMock;

    @InjectMocks
    private UserToLondonDistanceServiceImpl distanceService;

    @Test
    public void findUsersWithinMilesOfLondonFindsNoUsers() {
        EasyRandom easyRandom = new EasyRandom();
        DwpUser userOne = easyRandom.nextObject(DwpUser.class);
        DwpUser userTwo = easyRandom.nextObject(DwpUser.class);
        List<DwpUser> userModelList = Arrays.asList(userOne, userTwo);
        double milesFromLondon = 50;

        double distanceFromLondon = 60;
        Mockito.when(locationDistanceServiceMock.milesDistanceBetweenLocations(
                ArgumentMatchers.any(Location.class),
                ArgumentMatchers.any(Location.class)
        )).thenReturn(distanceFromLondon);

        List<DwpUser> usersWithinMilesOfLondon = distanceService.findUsersWithinMilesOfLondon(userModelList, milesFromLondon);

        Assertions.assertTrue(usersWithinMilesOfLondon.isEmpty());
        Mockito.verify(locationDistanceServiceMock, Mockito.times(userModelList.size())).milesDistanceBetweenLocations(
                ArgumentMatchers.any(Location.class),
                ArgumentMatchers.any(Location.class)
        );
    }

    @Test
    public void findUsersWithinMilesOfLondonFindsUsers() {
        EasyRandom easyRandom = new EasyRandom();
        DwpUser userOne = easyRandom.nextObject(DwpUser.class);
        DwpUser userTwo = easyRandom.nextObject(DwpUser.class);
        List<DwpUser> userModelList = Arrays.asList(userOne, userTwo);
        double milesFromLondon = 50;

        double distanceFromLondon = 30;
        Mockito.when(locationDistanceServiceMock.milesDistanceBetweenLocations(
                ArgumentMatchers.any(Location.class),
                ArgumentMatchers.any(Location.class)
        )).thenReturn(distanceFromLondon);

        List<DwpUser> usersWithinMilesOfLondon = distanceService.findUsersWithinMilesOfLondon(userModelList, milesFromLondon);

        Assertions.assertEquals(2, usersWithinMilesOfLondon.size());
        Mockito.verify(locationDistanceServiceMock, Mockito.times(userModelList.size())).milesDistanceBetweenLocations(
                ArgumentMatchers.any(Location.class),
                ArgumentMatchers.any(Location.class)
        );
    }

    @Test
    public void milesDistanceBetweenUserAndLondon() {
        DwpUser userModel = new DwpUser();
        userModel.setLatitude(5.7204203);
        userModel.setLongitude(10.901604);
        double expectedDistanceBetweenLocations = 50.55;

        Mockito.when(locationDistanceServiceMock.milesDistanceBetweenLocations(
                ArgumentMatchers.any(Location.class),
                ArgumentMatchers.any(Location.class)
        )).thenReturn(expectedDistanceBetweenLocations);

        double distanceBetween = distanceService
                .milesDistanceBetweenUserAndLondon(userModel);

        Assertions.assertEquals(expectedDistanceBetweenLocations, distanceBetween);
    }

}