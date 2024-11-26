# Use a base image with JDK 17
FROM eclipse-temurin:17-jdk-alpine


# Copy the Spring Boot JAR file into the container
COPY ./target/ecom.jar  ecom.jar



# Set the entry point to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "ecom.jar"]