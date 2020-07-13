Feature: Testing user catchment API
  Users should be able to submit GET requests to a DWP User web service, represented by WireMock

  Scenario: Data retrieval from the users web service is refined into just data within the London area
    When client makes a request to get information on the users within London the catchment
    Then the requested data is returned