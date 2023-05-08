# Use an OpenJDK base image with Maven for building the project
FROM maven:3.8.4-openjdk-11-slim AS build
WORKDIR /app

# Copy the Maven configuration files
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests

# Use an OpenJDK base image for running the application
FROM openjdk:11-jre-slim
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/employee-management-api-0.0.4-SNAPSHOT.jar employee-api.jar

# Set the entry point to run the JAR file
# EXPOSE 8080
ENTRYPOINT ["java", "-jar", "employee-api.jar"]
