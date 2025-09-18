# syntax=docker/dockerfile:1

### ---- Build stage ----

FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Kopiér kun det, der skal til for at cache dependencies
COPY pom.xml .
RUN mvn dependency:resolve

# Nu først koden, så vi udnytter cache ovenfor
COPY src ./src
RUN mvn -B -q clean package -DskipTests

### ---- Run stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app

# Kopiér fat-jar fra build stage
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

# Spring Boot-port
EXPOSE 8080