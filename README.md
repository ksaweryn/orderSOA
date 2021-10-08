# Order SOA project

## Pre requisities

1. This project runs on JAVA EE server.
2. It was tested using Wildfly 25 final
3. Java EE 8
4. Maven 3+
5. Swagger 
6. JUnit 5 and Mockito 4

### Datasource configuration

It is also required to have a Data Source configuration named: __java:/commerceDS__

## Context path

This service runs over __/commerce__ context root

## Swagger

Please be sure __swagger-ui__ is included the generated __commecer.war package__ with maven command:

`mvn package`

Otherwise please run:

`mvn com.googlecode.maven-download-plugin:download-maven-plugin:wget ` 


then create package.


### Accessing swagger documentation

To access documentation please go to 
__http://localhost:8080/commerce/docs/__

search by `commerce/api/swagger.json`


## To-Do

- There are missing some functionalities regarding order management.
- Implementation `CriteriaQuery` to control deletetion of data.
- Correct JUnit 5 & Mockito 4 not mocking Interfaces. Test __disabled__. 

