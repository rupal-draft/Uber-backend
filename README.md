
# Uber Backend Project

## Low-Level Design
![LLD](https://github.com/user-attachments/assets/LLD.png)

## Overview
This project is an Uber backend system implementation using Spring Boot, PostgreSQL, and various Spring modules. The APIs are well-documented using Swagger and Postman.

## Key Features
- **Spring Framework Core Features:**
  - Spring IOC Container
  - Beans
  - AutoConfigurations
- **Dependency Injection:** Used extensively for managing dependencies.
- **Spring MVC Concepts:** Implemented to structure the application.
- **Spring Boot Internals:** Utilized Spring Boot for rapid application development.
- **Lombok:** Simplified Java code with Lombok annotations.
- **Database:**
  - PostgreSQL used for database management.
  - H2 DB for in-memory testing.
- **Spring Bean Data Validation:** Implemented validation mechanisms.
- **Exception Handling:** Managed exceptions within the application.
- **Spring Boot REST APIs:** Built RESTful APIs for the system.
- **Spring Data JPA:**
  - Used for database interactions.
  - Custom queries implemented for complex operations.
- **Hibernate ORM:** Configured with JDBC for ORM functionality.
- **Spring Boot Actuator:** Monitored application health and metrics.
- **Swagger & Postman Documentation:** API documentation provided through Swagger and Postman.
- **Spring Dev Tools:** Utilized for improving the development process.
- **Logging & Auditing:** Implemented to keep track of activities and changes using log4j.
- **Spring Security:**
  - Secured APIs with role-based access control.
  - Implemented password encoding with Bcrypt and session management.
- **Deployment(in progess):**
  - CI/CD pipelines created using AWS CodePipeline.
  - Deployed on Elastic Beanstalk using CodeDeploy.
- **Email Notifications:**
  - OTP and ride acceptance notifications sent through emails.
  - Currently for email service I am using JavaMailSender.  

## API Documentation

### Auth APIs
| Endpoint                   | HTTP Method | Purpose                                          |
|----------------------------|-------------|--------------------------------------------------|
| /auth/signup               | POST        | Signup a new user                                |
| /auth/onboardDriver/{userId} | POST        | Onboard a new driver (Admin role)               |
| /auth/login                | POST        | Login a user and provide tokens                  |
| /auth/refresh              | POST        | Refresh the authentication token                 |

### Driver APIs
| Endpoint                   | HTTP Method | Purpose                                          |
|----------------------------|-------------|--------------------------------------------------|
| /driver/getMyProfile       | GET         | Get driver's profile                             |
| /driver/getMyRides         | GET         | Get driver's ride history                        |
| /driver/acceptRide/{rideRequestId} | POST   | Accept a ride request                           |
| /driver/startRide/{rideId} | POST        | Start a ride                                     |
| /driver/endRide/{rideId}   | POST        | End a ride                                       |
| /driver/cancelRide/{rideId}| POST        | Cancel a ride                                    |
| /driver/rateRider/{rideId} | POST        | Rate a rider                                     |

### Rider APIs
| Endpoint                   | HTTP Method | Purpose                                          |
|----------------------------|-------------|--------------------------------------------------|
| /rider/getMyProfile        | GET         | Get rider's profile                              |
| /rider/getMyRides          | GET         | Get rider's ride history                         |
| /rider/requestRide         | POST        | Request a new ride                               |
| /rider/cancelRide/{rideId} | POST        | Cancel a ride                                    |
| /rider/rateDriver/{rideId} | POST        | Rate a driver                                    |

## Documentation Links
- **Swagger UI:** [Link to Swagger Documentation]
- **Postman Collection:** [Link to Postman Documentation](https://documenter.getpostman.com/view/30415721/2sAYJAdxZj)
- **Swagger UI:** [Link to Actuator]

## Installation
1. Clone the repository.
    ```sh
    git clone https://github.com/rupal-draft/Uber-backend.git
    ```

2. Configure PostgreSQL database.
    ```sh
    spring.datasource.url=[Your database url]
    spring.datasource.username=[Your username]
    spring.datasource.password=[Your password]
    spring.jpa.hibernate.ddl-auto=[update/create-drop]
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true  
    ```
2. Configure your email sender.
    ```sh
    spring.mail.password=[Your password]
    spring.mail.username=[Your email address]
    spring.mail.host=[Email host]
    spring.mail.port=[Port]
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true  
    ```
2. Configure your log4j.
    ```sh
    log4j.rootLogger=INFO, stdout, file
    log4j.appender.stdout=org.apache.log4j.ConsoleAppender
    log4j.appender.stdout.Target=System.out
    log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
    log4j.appender.stdout.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} [%t] %-5p %c - %m%n
    log4j.appender.file=org.apache.log4j.RollingFileAppender
    log4j.appender.file.File=logs/uber_application.log
    log4j.appender.file.MaxFileSize=10MB
    log4j.appender.file.MaxBackupIndex=5
    log4j.appender.file.layout=org.apache.log4j.PatternLayout
    log4j.appender.file.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss} [%t] %-5p %c - %m%n
    log4j.logger.com.project.uber.Uber=DEBUG, stdout, file
    log4j.additivity.com.project.uber.Uber=false 
    ```          
3. Run the application using the following command:
   ```sh
   ./mvnw spring-boot:run
   ```


## Contact
For any inquiries, please reach out to rupalpaultmsl@gmail.com.
