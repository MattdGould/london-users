package com.dwp.users.dwplondonusers.cucumber;

import com.github.tomakehurst.wiremock.WireMockServer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class StepDefinition extends SpringIntegrationTest {

    private static final String APPLICATION_JSON = "application/json";
    private static final String DWP_SERVICE_URL = "/users";

    private final InputStream jsonInputStream = this.getClass().getClassLoader().getResourceAsStream("usersResponse.json");
    private final String jsonString = new Scanner(jsonInputStream, "UTF-8").useDelimiter("\\Z").next();

    private final WireMockServer wireMockServer = new WireMockServer(8082);
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    private String responseString;

    @When("^client makes a request to get information on the users within London the catchment$")
    public void usersGetInformationOnAProject(String projectName) throws IOException {
        wireMockServer.start();

        configureFor("localhost", wireMockServer.port());
        stubFor(get(urlEqualTo(DWP_SERVICE_URL))
                .willReturn(aResponse().withBody(jsonString).withHeader("Content-Type", APPLICATION_JSON)));

        HttpGet request = new HttpGet("http://localhost:" + 8080 + "/users/London/catchment");
        request.addHeader("accept", APPLICATION_JSON);
        HttpResponse httpResponse = httpClient.execute(request);
        responseString = convertResponseToString(httpResponse);
    }

    @Then("^the requested data is returned$")
    public void theRequestedDataIsReturned() {
        assertThat(responseString, containsString("[{\"id\":266,\"email\":\"agarnsworthy7d@seattletimes.com\",\"latitude\":51.6553959,\"longitude\":0.0572553,\"first_name\":\"Ancell\",\"last_name\":\"Garnsworthy\",\"ip_address\":\"67.4.69.137\"}]"));
        verify(getRequestedFor(urlEqualTo(DWP_SERVICE_URL)));

        wireMockServer.stop();
    }

    private String convertResponseToString(HttpResponse response) throws IOException {
        InputStream responseStream = response.getEntity().getContent();
        Scanner scanner = new Scanner(responseStream, "UTF-8");
        String responseString = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return responseString;
    }

}
