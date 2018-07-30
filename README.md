Playing with Kotlin and SpringBoot
==================================

Swagger
-------

The application includes a Swagger exploration frontend written in HTML+JavaScript. It fetches information about the 
REST API as JSON data from a Swagger compatible backend.

* <http://localhost:8080/swagger-ui.html>               - Swagger UI Frontend URL
* <http://localhost:8080/v2/api-docs>                   - Swagger Backend
* <http://localhost:8080/swagger-resources/>            - (internal) Configuration for the Swagger UI
* <http://localhost:8080/webjars/springfox-swagger-ui/> - (internal) JS, CSS and other stuff for the Swagger UI

Actuator
--------

The SpringBoot Actuator module provides a mechanism to register URLs for health & performance monitoring.

* <http://localhost:8080/actuator/health>  - Returns "status=UP" as JSON

Tests
-----

Different HTTP Client libaries testet:
* Spring "TestRestTemplate" was more or less ok
* "Retrofit" was not as good; easy to forget .execute()
* "Fuel" could not be convinced to serialize a class to JSON even with the "fuel-jackson" dependency

Startup Time
------------
* original: Started KotlinSpringbootTestApplicationKt in 13.984 seconds (JVM running for 16.958)
* ComponentScan with LazyInit: Started KotlinSpringbootTestApplicationKt in 13.355 seconds (JVM running for 14.348) 