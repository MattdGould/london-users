# DWP London Userss

Project to retrieve users that are within 50 miles of London based on their
longitude and latitude coordinates

## Getting Started

Maven build will build this project to your local M2 (mvn clean install)
The output Spring Boot jar can be run on the command line (java -jar mg-dwp-london-users-1.0.0.RELEASE)
Current default port is 80802 and configured in application.properties

### Prerequisites

The following a list of skills and knowledge that may be useful
```
Java
Spring
JUnit
Cucumber
Mockito
Wiremock
```

### Installing

The following endpoints are setup:

```
Swagger docs at: /swagger-ui.html
For the London catchment area (within 50 miles): /users/London/catchment
For a specific distance users should be from London (specified as a query param): /users/London?distanceFrom=30 
```

## Authors

* **Matt Gould** - *Initial work* - [london-users](https://github.com/MattdGould/london-users)

## License

This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/MattdGould/london-users/blob/master/LICENCE.md) file for details