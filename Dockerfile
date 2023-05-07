#
# Build stage
#
FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
# Copy the Maven configuration files
COPY pom.xml .
COPY src ./src

# Copy the built JAR file from the previous stage
COPY --from=build /target/employee-management-api-0.0.4-SNAPSHOT.jar employee-api.jar

# Set the entry point to run the JAR file
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "employee-api.jar"]
