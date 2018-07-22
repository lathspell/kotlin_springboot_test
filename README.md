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

