package com.dwp.users.dwplondonusers.cucumber;

import com.dwp.users.dwplondonusers.DwpLondonUsersApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = DwpLondonUsersApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class SpringIntegrationTest {

}
