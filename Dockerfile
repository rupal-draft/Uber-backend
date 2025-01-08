FROM maven:3-eclipse-temurin-23-alpine AS build

WORKDIR /app

COPY .mvn/ .mvn

COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:23-jdk

COPY --from=build /app/target/Uber-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

CMD ["java", "-jar", "Uber-0.0.1-SNAPSHOT.jar"]