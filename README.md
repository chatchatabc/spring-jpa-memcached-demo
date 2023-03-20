# Spring-Jpa-Memcached-Demo

Basic demo focused solely on the features and usage of memcached with spring boot 3, spring security 6, and jpa.

# Database Diagram

- Under development

# Requirements

- [Docker](https://docs.docker.com/get-docker/)
- [Postgresql (v15.0)](https://www.postgresql.org/download/)
- [Intellij (EAP) *preferred*](https://www.jetbrains.com/toolbox-app/)
- [Spring Boot (3.1.0-SNAPSHOT)](https://spring.io/quickstart)
- [Maven](https://maven.apache.org/index.html)
- [Java SDK (v19.0.2)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Github](https://git-scm.com/downloads)

# Features

* Under development

# Tools

* [Spring Framework/Boot 3](https://spring.io/)
* [Mapstruct](https://mapstruct.org/)
* [Spring Security 6](https://docs.spring.io/spring-security/reference/index.html)
* [Database Rider](https://github.com/database-rider/database-rider)
* [Thymeleaf](https://www.thymeleaf.org/)
* [Flywaydb](https://flywaydb.org/)

# Getting Started

1. Download and install Docker, Postgresql, Java with their appropriate versions.
2. Open the application then enter the command at terminal/shell
   ```sh
       docker-compose up
   ```
3. check application.properties in the `/src/main/resources` and configure the database connection
    ```  
       spring.jpa.hibernate.ddl-auto=create
       spring.jpa.show-sql=false
       spring.datasource.url=jdbc:postgresql://localhost:<port>/<db-name>
       spring.datasource.username=<username>
       spring.datasource.password=<password>
       spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    ```
4. Build then run the application (Automatically creates table needed to store data if it does not exist)

# Issues

- under development, not yet tested

# References
- [How to set up Thymeleaf](https://www.baeldung.com/thymeleaf-in-spring-mvc)
- [Spring Security Filters Guide](https://www.baeldung.com/security-none-filters-none-access-permitAll)
- [How to make registration with Spring Security](https://www.baeldung.com/registration-with-spring-mvc-and-spring-security)
- [Login with Spring Security](https://www.baeldung.com/spring-security-login)